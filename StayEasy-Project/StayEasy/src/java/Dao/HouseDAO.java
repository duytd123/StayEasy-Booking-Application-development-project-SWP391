/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Connect.DBContext;
import Model.BillDetail;
import Model.HouseHost;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Connect.DBContext;
import Model.Booking;
import Model.House;
import Model.HouseImg;
import Model.Location;
import Model.Menu;
import java.time.LocalDate;
import java.sql.PreparedStatement;

/**
 *
 * @author Admin
 */
public class HouseDAO {

    Connection con;

    public LocationDAO ld = new LocationDAO();
    public MenuDAO md = new MenuDAO();
    public DecimalFormat df = new DecimalFormat("###.##");

    public HouseDAO() {
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public List<House> getNameThreeHouseBest() {
        String sql = "select top 3 House.house_id  ,House.house_name , COUNT (*)\n"
                + "from Bill_detail , House\n"
                + "where Bill_detail.house_id  = House.house_id\n"
                + "group by House.house_id ,House.house_name\n"
                + "";
        List<House> list = new ArrayList<House>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt(1);
                String houseName = resultSet.getString(2);
                int numberBill = resultSet.getInt(3);
                House house = new House();
                house.setHouseid(houseid);
                house.setHousename(houseName);
                house.setNumberBill(numberBill);
                list.add(house);

            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public List<House> getHouse() {
        String sql = "select * from dbo.House";
        List<House> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt(1);
                Date postdate = resultSet.getDate(2);
                String housename = resultSet.getString(3);
                String review = resultSet.getString(4);
                float price = resultSet.getFloat(5);
                int status = resultSet.getInt(6);
                String address = resultSet.getString(7);
                String description = resultSet.getString(8);
                int locationid = resultSet.getInt(9);
                int menuid = resultSet.getInt(10);

                //tạo model hứng giữ liệu
                Menu menu = new Menu(menuid, null);
                Location location = new Location(locationid, null);
                House h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
                list.add(h);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public List<BillDetail> getBookingDetails(int hostId) throws SQLException {
        List<BillDetail> bookings = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // SQL query to retrieve booking details for houses controlled by the host
            String sql = "SELECT bd.bill_detail_id, bd.bill_id, bd.house_id, bd.start_date, bd.end_date, bd.note "
                    + "FROM Bill_detail bd "
                    + "INNER JOIN House h ON bd.house_id = h.house_id "
                    + "WHERE h.host_id = ?";
            ps = con.prepareStatement(sql);
            ps.setInt(1, hostId);

            // Execute query and process result set
            rs = ps.executeQuery();
            while (rs.next()) {
                int billDetailId = rs.getInt("bill_detail_id");
                int billId = rs.getInt("bill_id");
                int houseId = rs.getInt("house_id");
                Calendar startDate = Calendar.getInstance();
                startDate.setTime(rs.getDate("start_date"));
                Calendar endDate = Calendar.getInstance();
                endDate.setTime(rs.getDate("end_date"));
                String note = rs.getString("note");

                BillDetail booking = new BillDetail(billDetailId, billId, houseId, startDate.getTime(), endDate.getTime(), note);
                bookings.add(booking);
            }
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return bookings;
    }
    
     public List<Booking> getBookingDetailll(int hostId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.bill_id, b.date AS bill_date, b.total, b.status AS bill_status, "
                + "b.user_id, b.fullname, b.phone, bd.start_date, bd.end_date, "
                + "h.house_name, h.status AS house_status "
                + "FROM Bill b "
                + "JOIN Users u ON b.user_id = u.user_id "
                + "JOIN Bill_detail bd ON b.bill_id = bd.bill_id "
                + "JOIN House h ON bd.house_id = h.house_id "
                + "WHERE h.host_id = ? "
                + "ORDER BY bd.start_date";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, hostId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBillId(rs.getInt("bill_id"));
                    booking.setDate(rs.getDate("bill_date"));
                    booking.setTotal(rs.getDouble("total"));
                    booking.setStatus(rs.getInt("bill_status"));
                    booking.setUserId(rs.getInt("user_id"));
                    booking.setFullname(rs.getString("fullname"));
                    booking.setPhone(rs.getString("phone"));
                    booking.setStartDate(rs.getDate("start_date"));
                    booking.setEndDate(rs.getDate("end_date"));
                    booking.setHouseName(rs.getString("house_name"));
                    booking.setHouseStatus(rs.getInt("house_status"));
                    bookings.add(booking);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Consider logging this exception
        }
        return bookings;
    }
     
      public void blockDate(int hostId, int houseId, String date) {
        String insertBlockedDateSql = "INSERT INTO BlockedDates (host_id, house_id, date) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(insertBlockedDateSql)) {
            ps.setInt(1, hostId);
            ps.setInt(2, houseId);
            ps.setString(3, date);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
      
      public List<House> getBlockedHouses(int hostId) {
        List<House> blockedHouses = new ArrayList<>();
        String sql = "SELECT house_id, house_name FROM House WHERE host_id = ? AND status = 2";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, hostId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                House house = new House();
                house.setHouseid(rs.getInt("house_id"));
                house.setHousename(rs.getString("house_name"));
                blockedHouses.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blockedHouses;
    }

    public List<HouseHost> getHousesByHost(int hostId) {
        String sql = "SELECT h.house_id, h.post_date, h.house_name, h.review, h.house_price, h.status, h.address, "
                + "h.description, h.discount, h.loca_id, h.menu_id, STRING_AGG(hi.img_link, ',') AS images "
                + "FROM House h "
                + "LEFT JOIN House_img hi ON h.house_id = hi.house_id "
                + "WHERE h.host_id = ? "
                + "GROUP BY h.house_id, h.post_date, h.house_name, h.review, h.house_price, h.status, h.address, "
                + "h.description, h.discount, h.loca_id, h.menu_id";

        List<HouseHost> list = new ArrayList<>();
        PreparedStatement pre = null;
        ResultSet rs = null;

        try {
            pre = con.prepareStatement(sql);
            pre.setInt(1, hostId);
            rs = pre.executeQuery();

            while (rs.next()) {
                int houseId = rs.getInt("house_id");
                Date postDate = rs.getDate("post_date");
                String houseName = rs.getString("house_name");
                String review = rs.getString("review");
                float housePrice = rs.getFloat("house_price");
                int status = rs.getInt("status");
                String address = rs.getString("address");
                String description = rs.getString("description");
                double discount = rs.getDouble("discount");
                int locationId = rs.getInt("loca_id");
                int menuId = rs.getInt("menu_id");
                String image = rs.getString("images");

                String[] imageArray = (image != null) ? image.split(",") : new String[0];

                // Assuming ld and md are your LocationDAO and MenuDAO instances
                Location l = ld.getLocationById(locationId); // Implement ld as your LocationDAO
                Menu m = md.getMenuById(menuId); // Implement md as your MenuDAO

                List<String> imagesList = new ArrayList<>();
                if (imageArray.length > 0) {
                    imagesList.addAll(Arrays.asList(imageArray));
                }

                HouseHost house = new HouseHost(houseId, postDate, houseName, review, housePrice, status, address, description, discount, l, m, imagesList);
                list.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception appropriately
        }
        return list;
    }

    public List<HouseHost> getListByPage(List<HouseHost> list, int start, int end) {
        ArrayList<HouseHost> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public HouseHost getHouseByHouseIDandHost(int houseId, int hostId) {
        String sql = "SELECT h.house_id, h.post_date, h.house_name, h.review, h.house_price, h.status, h.address, "
                + "h.description, h.discount, h.loca_id, h.menu_id, STRING_AGG(hi.img_link, ',') AS images "
                + "FROM House h "
                + "LEFT JOIN House_img hi ON h.house_id = hi.house_id "
                + "WHERE h.house_id = ? AND h.host_id = ? "
                + "GROUP BY h.house_id, h.post_date, h.house_name, h.review, h.house_price, h.status, h.address, "
                + "h.description, h.discount, h.loca_id, h.menu_id";

        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, houseId);
            pre.setInt(2, hostId);
            ResultSet rs = pre.executeQuery();

            if (rs.next()) {
                int retrievedHouseId = rs.getInt("house_id");
                Date postDate = rs.getDate("post_date");
                String houseName = rs.getString("house_name");
                String review = rs.getString("review");
                float housePrice = rs.getFloat("house_price");
                int status = rs.getInt("status");
                String address = rs.getString("address");
                String description = rs.getString("description");
                double discount = rs.getDouble("discount");
                int locationId = rs.getInt("loca_id");
                int menuId = rs.getInt("menu_id");
                String image = rs.getString("images");

                String[] imageArray = (image != null) ? image.split(",") : new String[0];

                Location l = ld.getLocationById(locationId);
                Menu m = md.getMenuById(menuId);

                List<String> imagesList = new ArrayList<>();
                if (imageArray.length > 0) {
                    imagesList.addAll(Arrays.asList(imageArray));
                }

                return new HouseHost(retrievedHouseId, postDate, houseName, review, housePrice, status, address, description, discount, l, m, imagesList);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching house by house_id and host_id: " + e.getMessage());
        }
        return null;
    }

    public List<HouseHost> getHouseWithInfoByHouseNameAndHost(String txtSearch, int hostId) {
        String sql = "SELECT h.house_id, h.post_date, h.house_name, h.review, h.house_price, h.status, "
                + "h.address, h.description, h.discount, h.loca_id, h.menu_id, STRING_AGG(hi.img_link, ',') AS images "
                + "FROM House h "
                + "LEFT JOIN House_img hi ON h.house_id = hi.house_id "
                + "WHERE h.house_name LIKE ? AND h.host_id = ? "
                + "GROUP BY h.house_id, h.post_date, h.house_name, h.review, h.house_price, h.status, "
                + "h.address, h.description, h.discount, h.loca_id, h.menu_id";

        List<HouseHost> houseList = new ArrayList<>();

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, "%" + txtSearch + "%");
            pre.setInt(2, hostId);

            ResultSet resultSet = pre.executeQuery();

            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                Date postDate = resultSet.getDate("post_date");
                String houseName = resultSet.getString("house_name");
                String review = resultSet.getString("review");
                float housePrice = resultSet.getFloat("house_price");
                int status = resultSet.getInt("status");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");
                double discount = resultSet.getDouble("discount");
                int locaId = resultSet.getInt("loca_id");
                int menuId = resultSet.getInt("menu_id");
                String imgLink = resultSet.getString("images");

                Location location = new Location();
                Menu menu = new Menu();

                List<String> images = new ArrayList<>();
                if (imgLink != null) {
                    String[] imageArray = imgLink.split(",");
                    images.addAll(Arrays.asList(imageArray));
                }

                HouseHost house = new HouseHost(houseId, postDate, houseName, review, housePrice,
                        status, address, description, discount, location, menu, images);

                houseList.add(house);
            }

            resultSet.close();
            pre.close();

        } catch (SQLException e) {
            System.out.println("Error in getHouseWithInfoByHouseNameAndHost: " + e.getMessage());
        }

        return houseList;
    }

//    public void insertHouse(String name, String image, double price, String description, String address,
//            String date, double discount, int hostId, int locationId, int menuId) {
//        String sql = "INSERT INTO [dbo].[Houses] (\n"
//                + "    [house_name], \n"
//                + "    [Image], \n"
//                + "    [Price], \n"
//                + "    [Description], \n"
//                + "    [Address], \n"
//                + "    [ReleaseDate], \n"
//                + "    [Discount], \n"
//                + "    [HostID], \n"
//                + "    [LocationID], \n"
//                + "    [MenuID]\n"
//                + ")\n"
//                + "VALUES (N'" + name + "', '" + image + "', '" + price + "', N'" + description + "', N'" + address + "', '"
//                + date + "', '" + discount + "', '" + hostId + "', '" + locationId + "', '" + menuId + "')";
//
//        try (PreparedStatement stmt = con.prepareStatement(sql)) {
//            stmt.executeUpdate();
//            System.out.println("House inserted successfully.");
//        } catch (SQLException e) {
//            System.out.println("Error inserting house: " + e.getMessage());
//        }
//    }
    public void insertHouse(String name, double price, String description, String address,
            String date, double discount, int hostId, int locationId, int menuId, String imageLinks) {
        String insertHouseSql = "INSERT INTO [dbo].[House] ([house_name], [house_price], [description], [address], "
                + "[post_date], [discount], [host_id], [loca_id], [menu_id]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        String insertHouseImgSql = "INSERT INTO [dbo].[House_img] ([img_link], [house_id]) "
                + "VALUES (?, ?)";

        try {
            try (PreparedStatement insertHouseStmt = con.prepareStatement(insertHouseSql, Statement.RETURN_GENERATED_KEYS)) {
                insertHouseStmt.setString(1, name);
                insertHouseStmt.setDouble(2, price);
                insertHouseStmt.setString(3, description);
                insertHouseStmt.setString(4, address);
                insertHouseStmt.setString(5, date);
                insertHouseStmt.setDouble(6, discount);
                insertHouseStmt.setInt(7, hostId);
                insertHouseStmt.setInt(8, locationId);
                insertHouseStmt.setInt(9, menuId);

                int rowsAffected = insertHouseStmt.executeUpdate();

                if (rowsAffected > 0) {

                    try (ResultSet generatedKeys = insertHouseStmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            int houseId = generatedKeys.getInt(1);

                            try (PreparedStatement insertHouseImgStmt = con.prepareStatement(insertHouseImgSql)) {
                                for (String imageLink : imageLinks.split(",")) {
                                    insertHouseImgStmt.setString(1, imageLink);
                                    insertHouseImgStmt.setInt(2, houseId);
                                    insertHouseImgStmt.executeUpdate();
                                }
                                System.out.println("House and House_img inserted successfully.");
                            }
                        }
                    }
                } else {
                    System.out.println("Error inserting House.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting house: " + e.getMessage());
        }
    }

    public void editHouse(int houseId, String name, double price, String description, String address,
            String date, double discount, int hostId, int locationId, int menuId) {
        String updateHouseSql = "UPDATE [dbo].[House] "
                + "SET [house_name] = ?, [house_price] = ?, [description] = ?, [address] = ?, "
                + "[post_date] = ?, [discount] = ?, [host_id] = ?, [loca_id] = ?, [menu_id] = ? "
                + "WHERE [house_id] = ?";

        try {
            PreparedStatement updateHouseStmt = con.prepareStatement(updateHouseSql);
            updateHouseStmt.setString(1, name);
            updateHouseStmt.setDouble(2, price);
            updateHouseStmt.setString(3, description);
            updateHouseStmt.setString(4, address);
            updateHouseStmt.setString(5, date);
            updateHouseStmt.setDouble(6, discount);
            updateHouseStmt.setInt(7, hostId);
            updateHouseStmt.setInt(8, locationId);
            updateHouseStmt.setInt(9, menuId);
            updateHouseStmt.setInt(10, houseId);
            updateHouseStmt.executeUpdate();

            System.out.println("House updated successfully.");

        } catch (SQLException e) {
            System.out.println("Error updating house: " + e.getMessage());
        }
    }

    public int countHousesByHost(int hostId) {
        String sql = "SELECT COUNT(*) FROM House WHERE host_id = ?";
        int count = 0;
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, hostId);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return count;
    }

    public List<House> searchHouse(String whereTo, String arrivals) {
        String sql = "select * \n"
                + "from House \n"
                + "where address like '%" + whereTo + "%' \n"
                + "and post_date like '%" + arrivals + "%' ";
        List<House> list = new ArrayList<>();
        try {

            PreparedStatement pre = con.prepareStatement(sql);

            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt(1);
                Date postdate = resultSet.getDate(2);
                String housename = resultSet.getString(3);
                String review = resultSet.getString(4);
                float price = resultSet.getFloat(5);
                int status = resultSet.getInt(6);
                String address = resultSet.getString(7);
                String description = resultSet.getString(8);
                int locationid = resultSet.getInt(9);
                int menuid = resultSet.getInt(10);

                Menu menu = new Menu(menuid, null);
                Location location = new Location(locationid, null);
                House h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
                list.add(h);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }



//    public List<House> searchfindHouse(String whereTo, Date arrivals, String guests, Date leaving, int locationId, int menuId) {
//        String sql = "select * from House as H1 where 1 = 1";
//        if (locationId >= 0) {
//            sql += " and H1.loca_id=? ";
//        }
//        if (menuId >= 0) {
//            sql += " and H1.menu_id =?";
//        }
//        sql += " and H1.house_name like ?";
//
//        List<House> list = new ArrayList<>();
//        try {
//            PreparedStatement pr = con.prepareStatement(sql);
//            int i = 1;
//            if (locationId >= 0) {
//                pr.setInt(i++, locationId);
//            }
//            if (menuId >= 0) {
//                pr.setInt(i++, menuId);
//            }
//            pr.setString(i++, "%" + whereTo + "%");
//            ResultSet resultSet = pr.executeQuery();
//            while (resultSet.next()) {
//                int houseid = resultSet.getInt(1);
//                Date postdate = resultSet.getDate(2);
//                String housename = resultSet.getString(3);
//                String review = resultSet.getString(4);
//                float price = resultSet.getFloat(5);
//                int status = resultSet.getInt(6);
//                String address = resultSet.getString(7);
//                String description = resultSet.getString(8);
//                int locationid = resultSet.getInt(9);
//                int menuid = resultSet.getInt(10);
//                MenuDAO menuDao = new MenuDAO();
//                Menu m = menuDao.getMenuById(menuid);
//                Menu menu = m;
//                LocationDAO lDao = new LocationDAO();
//                Location location = lDao.getLocationById(locationid);
//                House h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
//                h.setRentHouse(this.isHouseRented(houseid, postdate, postdate));
//                list.add(h);
//            }
//
//        } catch (Exception e) {
//            System.out.println("List err");
//            System.out.println(e);
//        }
//        return list;
//    }

//    public boolean isHouseRented(int houseId, Date startDate, Date endDate) {
//        String sql = "SELECT COUNT(*) FROM Bill_detail AS BD "
//                + "JOIN Bill AS B ON BD.bill_id = B.bill_id "
//                + "WHERE BD.house_id = ? "
//                + "AND ((BD.start_date >= ? AND BD.start_date <= ?) "
//                + "OR (BD.end_date >= ? AND BD.end_date <= ?))";
//
//        try (PreparedStatement pr = con.prepareStatement(sql)) {
//            pr.setInt(1, houseId);
//            pr.setDate(2, startDate);
//            pr.setDate(3, endDate);
//            pr.setDate(4, startDate);
//            pr.setDate(5, endDate);
//
//            ResultSet resultSet = pr.executeQuery();
//            if (resultSet.next()) {
//                int count = resultSet.getInt(1);
//                return count > 0;
//            }
//        } catch (SQLException e) {
//            System.out.println("Error checking rental status for house: " + e.getMessage());
//        }
//        return false;
//    }
//

    public List<House> searchfindHouse(String whereTo, Date arrivals, String guests, Date leaving, int locationId, int menuId) {
        String sql = "select * from House as H1 where 1 = 1";
        if (locationId >= 0) {
            sql += " and H1.loca_id=? ";
        }
        if (menuId >= 0) {
            sql += " and H1.menu_id =?";
        }
        sql += " and H1.house_name like ?";

        List<House> list = new ArrayList<>();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            int i = 1;
            if (locationId >= 0) {
                pr.setInt(i++, locationId);
            }
            if (menuId >= 0) {
                pr.setInt(i++, menuId);
            }
            pr.setString(i++, "%" + whereTo + "%");
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt(1);
                Date postdate = resultSet.getDate(2);
                String housename = resultSet.getString(3);
                String review = resultSet.getString(4);
                float price = resultSet.getFloat(5);
                int status = resultSet.getInt(6);
                String address = resultSet.getString(7);
                String description = resultSet.getString(8);
                int locationid = resultSet.getInt(9);
                int menuid = resultSet.getInt(10);
                MenuDAO menuDao = new MenuDAO();
                Menu m = menuDao.getMenuById(menuid);
                Menu menu = m;
                LocationDAO lDao = new LocationDAO();
                Location location = lDao.getLocationById(locationid);
                House h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
                h.setRentHouse(this.isHouseRented(houseid, postdate, postdate));
                list.add(h);
            }

        } catch (Exception e) {
            System.out.println("List err");
            System.out.println(e);
        }
        return list;
    }

    public boolean isHouseRented(int houseId, Date startDate, Date endDate) {
        String sql = "SELECT COUNT(*) FROM Bill_detail AS BD "
                + "JOIN Bill AS B ON BD.bill_id = B.bill_id "
                + "WHERE BD.house_id = ? "
                + "AND ((BD.start_date >= ? AND BD.start_date <= ?) "
                + "OR (BD.end_date >= ? AND BD.end_date <= ?))";

        try (PreparedStatement pr = con.prepareStatement(sql)) {
            pr.setInt(1, houseId);
            pr.setDate(2, startDate);
            pr.setDate(3, endDate);
            pr.setDate(4, startDate);
            pr.setDate(5, endDate);

            ResultSet resultSet = pr.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking rental status for house: " + e.getMessage());
        }
        return false;
    }


    public House getHousebyId(int id) {
        String sql = "select * from dbo.House where house_id = ?";
        House h = new House();

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt(1);
                Date postdate = resultSet.getDate(2);
                String housename = resultSet.getString(3);
                String review = resultSet.getString(4);
                float price = resultSet.getFloat(5);
                int status = resultSet.getInt(6);
                String address = resultSet.getString(7);
                String description = resultSet.getString(8);
                int locationid = resultSet.getInt(9);
                int menuid = resultSet.getInt(10);

                //tạo model hứng giữ liệu
                Menu menu = new Menu(menuid, null);
                Location location = new Location(locationid, null);
                h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return h;
    }

    public void editHouse(House house) {
        String sql = "UPDATE [dbo].[House]\n"
                + "   SET [post_date] = ?\n"
                + "      ,[house_name] = ?\n"
                + "      ,[review] = ?\n"
                + "      ,[house_price] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[address] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[loca_id] = ?\n"
                + "      ,[menu_id] = ?\n"
                + " WHERE house_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            java.sql.Date DateSql = new java.sql.Date(house.getPostdate().getTime());
            pre.setDate(1, DateSql);
            pre.setString(2, house.getHousename());
            pre.setString(3, house.getReview());
            pre.setFloat(4, house.getHouseprice());
            pre.setInt(5, house.getStatus());
            pre.setString(6, house.getAddress());
            pre.setString(7, house.getDescription());
            pre.setInt(8, house.getLocation().getId());
            pre.setInt(9, house.getMenu().getId());
            pre.setInt(10, house.getHouseid());
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void editHouseByHost(House house, int hostId) {
        String sql = "UPDATE House "
                + "SET post_date = ?, "
                + "    house_name = ?, "
                + "    review = ?, "
                + "    house_price = ?, "
                + "    status = ?, "
                + "    address = ?, "
                + "    description = ?, "
                + "    loca_id = ?, "
                + "    menu_id = ? "
                + "WHERE house_id = ? "
                + "    AND host_id = ?";

        try {

            PreparedStatement pre = con.prepareStatement(sql);

            java.sql.Date dateSql = new java.sql.Date(house.getPostdate().getTime());
            pre.setDate(1, dateSql);
            pre.setString(2, house.getHousename());
            pre.setString(3, house.getReview());
            pre.setFloat(4, house.getHouseprice());
            pre.setInt(5, house.getStatus());
            pre.setString(6, house.getAddress());
            pre.setString(7, house.getDescription());
            pre.setInt(8, house.getLocation().getId());
            pre.setInt(9, house.getMenu().getId());
            pre.setInt(10, house.getHouseid());
            pre.setInt(11, hostId);

            int rowsUpdated = pre.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("House updated successfully.");
            } else {
                System.out.println("Failed to update house. House not found or unauthorized access.");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public void deleteHouse(int id) {
        String sql = "DELETE FROM [dbo].[House]\n"
                + "      WHERE house_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

//    public void addHouse(House house) {
//        String sql = "INSERT INTO [dbo].[House]\n"
//                + "           ([post_date]\n"
//                + "           ,[house_name]\n"
//                + "           ,[review]\n"
//                + "           ,[house_price]\n"
//                + "           ,[status]\n"
//                + "           ,[address]\n"
//                + "           ,[description]\n"
//                + "           ,[loca_id]\n"
//                + "           ,[menu_id])\n"
//                + "     VALUES\n"
//                + "           (?\n"
//                + "           ,?\n"
//                + "           ,?\n"
//                + "           ,?\n"
//                + "           ,?\n"
//                + "           ,?\n"
//                + "           ,?\n"
//                + "           ,?\n"
//                + "           ,?)";
//        try {
//            PreparedStatement pre = con.prepareStatement(sql);
//            java.sql.Date DateSql = new java.sql.Date(house.getPostdate().getTime());
//            pre.setDate(1, DateSql);
//            pre.setString(2, house.getHousename());
//            pre.setString(3, house.getReview());
//            pre.setFloat(4, house.getHouseprice());
//            pre.setInt(5, house.getStatus());
//            pre.setString(6, house.getAddress());
//            pre.setString(7, house.getDescription());
//            pre.setInt(8, house.getLocation().getId());
//            pre.setInt(9, house.getMenu().getId());
//
//            pre.executeUpdate();
//
//        } catch (Exception e) {
//            System.out.println("error :  " + e);
//        }
//    }
    public void addHouse(House house, int hostId) {
        String sql = "INSERT INTO House (post_date, house_name, review, house_price, status, address, description, loca_id, menu_id, host_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = con.prepareStatement(sql);

            statement.setDate(1, new java.sql.Date(house.getPostdate().getTime()));
            statement.setString(2, house.getHousename());
            statement.setString(3, house.getReview());
            statement.setFloat(4, house.getHouseprice());
            statement.setInt(5, house.getStatus());
            statement.setString(6, house.getAddress());
            statement.setString(7, house.getDescription());
            statement.setInt(8, house.getLocation().getId());
            statement.setInt(9, house.getMenu().getId());
            statement.setInt(10, hostId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error occurred while adding house: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getLastInsertedHouseId() {
        String sql = "SELECT SCOPE_IDENTITY() AS last_id";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet rs = pre.executeQuery();
            if (rs.next()) {
                return rs.getInt("last_id");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return -1; // Return -1 if there was an error
    }

    public List<House> getHousebyName(String name) {
        String sql = "select * from House where house_name like '%" + name + "%'";
        List<House> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt(1);
                Date postdate = resultSet.getDate(2);
                String housename = resultSet.getString(3);
                String review = resultSet.getString(4);
                float price = resultSet.getFloat(5);
                int status = resultSet.getInt(6);
                String address = resultSet.getString(7);
                String description = resultSet.getString(8);
                int locationid = resultSet.getInt(9);
                int menuid = resultSet.getInt(10);

                //tạo model hứng giữ liệu
                Menu menu = new Menu(menuid, null);
                Location location = new Location(locationid, null);
                House h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
                list.add(h);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    /////////////////////////////
    public List<House> getHousebyNameAndHost(String name, int hostId) {
        String sql = "SELECT * FROM House WHERE house_name LIKE ? AND host_id = ?";
        List<House> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, "%" + name + "%");
            pre.setInt(2, hostId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt("house_id");

                House house = new House(
                        houseid,
                        resultSet.getDate("post_date"),
                        resultSet.getString("house_name"),
                        resultSet.getString("review"),
                        resultSet.getFloat("house_price"),
                        resultSet.getInt("status"),
                        resultSet.getString("address"),
                        resultSet.getString("description"),
                        new Location(resultSet.getInt("loca_id"), null),
                        new Menu(resultSet.getInt("menu_id"), null)
                );
                list.add(house);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }
//////////////////
 public List<House> getHousesByHostId(int hostId) {
        String sql = "SELECT * FROM House WHERE host_id = ?";
        List<House> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, hostId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseId = resultSet.getInt("house_id");
                Date postDate = resultSet.getDate("post_date");
                String houseName = resultSet.getString("house_name");
                String review = resultSet.getString("review");
                float housePrice = resultSet.getFloat("house_price");
                int status = resultSet.getInt("status");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");
                int locaId = resultSet.getInt("loca_id");
                int menuId = resultSet.getInt("menu_id");

                // Assuming Location and Menu constructors that take IDs and other parameters.
                Location location = new Location(locaId, null);
                Menu menu = new Menu(menuId, null);

                House house = new House(
                        houseId,
                        postDate,
                        houseName,
                        review,
                        housePrice,
                        status,
                        address,
                        description,
                        location,
                        menu
                );
                list.add(house);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }
 ///////////////////////////////
    public House getHouses() {
        String sql = "select * from dbo.House";
        House h = new House();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt(1);
                Date postdate = resultSet.getDate(2);
                String housename = resultSet.getString(3);
                String review = resultSet.getString(4);
                float price = resultSet.getFloat(5);
                int status = resultSet.getInt(6);
                String address = resultSet.getString(7);
                String description = resultSet.getString(8);
                int locationid = resultSet.getInt(9);
                int menuid = resultSet.getInt(10);

                //tạo model hứng giữ liệu
                Menu menu = new Menu(menuid, null);
                Location location = new Location(locationid, null);
                h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return h;
    }

    public HouseImg getHouseImgByHouseId(int houseId) {
        String sql = "SELECT * FROM dbo.HouseImg WHERE houseid = ?";
        HouseImg houseImg = null;
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, houseId);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                int imgid = resultSet.getInt(1);
                String imglink = resultSet.getString(2);
                int houseid = resultSet.getInt(3);
                houseImg = new HouseImg(imgid, imglink, houseid);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return houseImg;
    }

    public boolean removeHouseById(int houseId) {
        try {
            String sql = "UPDATE House SET status = 0 WHERE house_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, houseId);

            int rowsUpdated = stmt.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}



//    public List<House> searchHouse1(String whereTo, Date arrivals, String guests, Date leaving, int locationId, int menuId) {
//
//        String sql = "select * from House as H1 where 1 = 1";
//        if (locationId >= 0) {
//            sql += " and H1.loca_id=? ";
//        }
//        if (menuId >= 0) {
//            sql += " and H1.menu_id =?";
//        }
//        sql += " and H1.house_name like ? and H1.house_id Not in "
//                + "(select H.house_id from Bill as B "
//                + "join Bill_detail as BD on BD.bill_id = B.bill_id "
//                + "join House as H on H.house_id = BD.house_id "
//                + "where BD.start_date >= ? and BD.end_date <= ?"
//                + ")";
//        List<House> list = new ArrayList<>();
//        try {
//            PreparedStatement pr = con.prepareStatement(sql);
//            int i = 1;
//            if (locationId >= 0) {
//                pr.setInt(i++, locationId);
//            }
//            if (menuId >= 0) {
//                pr.setInt(i++, menuId);
//            }
//            pr.setString(i++, "%" + whereTo + "%");
//            pr.setDate(i++, arrivals);
//            pr.setDate(i++, leaving);
//            ResultSet resultSet = pr.executeQuery();
//            while (resultSet.next()) {
//                int houseid = resultSet.getInt(1);
//                Date postdate = resultSet.getDate(2);
//                String housename = resultSet.getString(3);
//                String review = resultSet.getString(4);
//                float price = resultSet.getFloat(5);
//                int status = resultSet.getInt(6);
//                String address = resultSet.getString(7);
//                String description = resultSet.getString(8);
//                int locationid = resultSet.getInt(9);
//                int menuid = resultSet.getInt(10);
//                MenuDAO menuDao = new MenuDAO();
//                Menu m = menuDao.getMenuById(menuid);
//                Menu menu = m;
//                LocationDAO lDao = new LocationDAO();
//                Location location = lDao.getLocationById(locationid);
//                House h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
//                list.add(h);
//            }
//
//        } catch (Exception e) {
//            System.out.println("List err");
//            System.out.println(e);
//        }
//        return list;
//
//    }

    public List<House> searchHouse1(String whereTo, Date arrivals, String guests, Date leaving, int locationId, int menuId) {

        String sql = "select * from House as H1 where 1 = 1";
        if (locationId >= 0) {
            sql += " and H1.loca_id=? ";
        }
        if (menuId >= 0) {
            sql += " and H1.menu_id =?";
        }
        sql += " and H1.house_name like ? and H1.house_id Not in "
                + "(select H.house_id from Bill as B "
                + "join Bill_detail as BD on BD.bill_id = B.bill_id "
                + "join House as H on H.house_id = BD.house_id "
                + "where BD.start_date >= ? and BD.end_date <= ?"
                + ")";
        List<House> list = new ArrayList<>();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            int i = 1;
            if (locationId >= 0) {
                pr.setInt(i++, locationId);
            }
            if (menuId >= 0) {
                pr.setInt(i++, menuId);
            }
            pr.setString(i++, "%" + whereTo + "%");
            pr.setDate(i++, arrivals);
            pr.setDate(i++, leaving);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt(1);
                Date postdate = resultSet.getDate(2);
                String housename = resultSet.getString(3);
                String review = resultSet.getString(4);
                float price = resultSet.getFloat(5);
                int status = resultSet.getInt(6);
                String address = resultSet.getString(7);
                String description = resultSet.getString(8);
                int locationid = resultSet.getInt(9);
                int menuid = resultSet.getInt(10);
                MenuDAO menuDao = new MenuDAO();
                Menu m = menuDao.getMenuById(menuid);
                Menu menu = m;
                LocationDAO lDao = new LocationDAO();
                Location location = lDao.getLocationById(locationid);
                House h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
                list.add(h);
            }

        } catch (Exception e) {
            System.out.println("List err");
            System.out.println(e);
        }
        return list;

    }
    
     public int countHousesWithPendingBookings(int hostId) {
        int count = 0;
        String sql = "SELECT COUNT(*) "
                + "FROM House h "
                + "WHERE h.status = 1 AND h.host_id = ?";


        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, hostId);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }

        return count;
    }
}
