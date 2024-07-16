/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Connect.DBContext;
import Model.House;
import Model.HouseImg;
import Model.Location;
import Model.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;


/**
 *
 * @author Admin
 */
public class HouseDAO {

    Connection con;

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

    public List<House> getHousesByHost(int hostId) {
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
                float price = resultSet.getFloat("house_price");
                int status = resultSet.getInt("status");
                String address = resultSet.getString("address");
                String description = resultSet.getString("description");
                int locationId = resultSet.getInt("loca_id");
                int menuId = resultSet.getInt("menu_id");

                Menu menu = new Menu(menuId, null);
                Location location = new Location(locationId, null);
                House house = new House(houseId, postDate, houseName, review, price, status, address, description, location, menu);
                list.add(house);
            }
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return list;
    }

    public List<House> searchHouse(String whereTo, String arrivals) {
        String sql = "select * \n"
                + "from House \n"
                + "where address like '%" + whereTo + "%' \n"
                + "and post_date like '%" + arrivals + "%' ";
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

       public List<House> searchfindHouse(String whereTo, Date arrivals, String guests, Date leaving, int locationId, int menuId) {
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
                MenuDAO menuDao = new MenuDAO();
                Menu m = menuDao.getMenuById(menuid);
                Menu menu = m;
                LocationDAO lDao = new LocationDAO();
                Location location = lDao.getLocationById(locationid);
                h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return h;
    }


//    public House getHousebyId(int id) {
//        String sql = "select * from dbo.House where house_id = ?";
//        House h = new House();
//
//        try {
//            //tạo khay chứa câu lệnh
//            PreparedStatement pre = con.prepareStatement(sql);
//            pre.setInt(1, id);
//            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
//            ResultSet resultSet = pre.executeQuery();
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
//
//                //tạo model hứng giữ liệu
//                Menu menu = new Menu(menuid, null);
//                Location location = new Location(locationid, null);
//                h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
//            }
//        } catch (Exception e) {
//            System.out.println("error: " + e);
//        }
//
//        return h;
//    }

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
                + "    AND host_id = ?";  // Ensure only the host can update their house

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
            pre.setInt(11, hostId);  // Bind host ID

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
        String sql = "INSERT INTO House (postdate, housename, review, houseprice, status, address, description, locationid, menuid, hostid) "
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
    public List<House> getHouse1() {
        String sql = "SELECT  distinct  house_id,post_date,house_name,review,house_price,status,address,description,Location.loca_id,Location.name,Menu.menu_id,\n"
                + "               Menu.name\n"
                + "                FROM[dbo].[House]\n"
                + "                \n"
                + "                join [Location] on House.loca_id = [Location].loca_id join Menu on House.menu_id = Menu.menu_id";
        List<House> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseid = resultSet.getInt(1);
                java.util.Date postdate = resultSet.getDate(2);
                String housename = resultSet.getString(3);
                String review = resultSet.getString(4);
                float price = resultSet.getFloat(5);
                int status = resultSet.getInt(6);
                String address = resultSet.getString(7);
                String description = resultSet.getString(8);

                //tạo model hứng giữ liệu
                Menu menu = new Menu(resultSet.getInt(11), resultSet.getString(12));
                Location location = new Location(resultSet.getInt(9), resultSet.getString(10));
                House h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
                list.add(h);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }
    
    public int countHouse() {
        String sql = "select * from dbo.House ";
        int count = 0;

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);

            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return count;
    }

}
