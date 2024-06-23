/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Connect.DBContext;
import Model.House;
import Model.Location;
import Model.Menu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
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
                Date postdate = resultSet.getDate(2);
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

    public List<House> searchHouse(String whereTo, Date arrivals, String guests, Date leaving, int locationId, int menuId) {
//    String sql = "select h.*, bd.*\n" 
//            +"from House h\n" 
//            +" left join bill_detail bd on h.house_id = bd.house_id";
//
//            
//  // Build dynamic WHERE clause based on provided parameters
//  List<String> conditions = new ArrayList<>();
//  if (whereTo != null && !whereTo.isEmpty()) {
//    conditions.add("address like '%" + whereTo + "%'");
//  }
//  if (arrivals != null && !arrivals.isEmpty()) {
//    conditions.add("NOT EXISTS (SELECT * FROM bill_detail bd2 WHERE bd2.house_id = h.house_id AND (('"+ arrivals +"' BETWEEN bd2.start_date AND bd2.end_date) OR ('"+ leaving +"' BETWEEN bd2.start_date AND bd2.end_date) OR (bd2.start_date BETWEEN '"+ arrivals +"' AND '"+ leaving +"') OR (bd2.end_date BETWEEN '"+ arrivals +"' AND '"+ leaving +"'))");
//  }
//  // Add conditions for guests (assuming a 'capacity' field in House table)
//  if (guests != null) {
//    conditions.add("capacity >= " + guests);
//  }
//  // Add conditions for leaving date (assuming a 'departure_date' field)
//  if (leaving != null && !leaving.isEmpty()) {
//    conditions.add("end_date like '%" + leaving + "%'"); // Consider date comparison for better efficiency
//  }
//
//  // Append conditions to the WHERE clause (if any)
//  if (!conditions.isEmpty()) {
//    sql += " WHERE " + String.join(" AND ", conditions);
//  }
//
//  List<House> list = new ArrayList<>();
//  try (PreparedStatement pre = con.prepareStatement(sql)) {
//    // No parameters needed if no conditions were added
//    if (!conditions.isEmpty()) {
//      // Set parameters based on the number of conditions
//      for (int i = 0; i < conditions.size(); i++) {
//        pre.setString(i + 1, conditions.get(i));
//      }
//    }
//
//    ResultSet resultSet = pre.executeQuery();
//    while (resultSet.next()) {
//      int houseid = resultSet.getInt(1);
//      Date postdate = resultSet.getDate(2);
//      String housename = resultSet.getString(3);
//      String review = resultSet.getString(4);
//      float price = resultSet.getFloat(5);
//      int status = resultSet.getInt(6);
//      String address = resultSet.getString(7);
//      String description = resultSet.getString(8);
//      int locationid = resultSet.getInt(9);
//      int menuid = resultSet.getInt(10);
//
//      Location location = locationid > 0 ? new Location(locationid, null) : null;
//            Menu menu = resultSet.getInt(10) > 0 ? new Menu(menuid, null) : null;
//
//            House h = new House(houseid, postdate, housename, review, price, status, address, description, location, menu);
//            list.add(h);
//        }
//    } catch (Exception e) {
//        System.out.println("Error: " + e);
//    }
//
//    return list;
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

    public void addHouse(House house) {
        String sql = "INSERT INTO [dbo].[House]\n"
                + "           ([post_date]\n"
                + "           ,[house_name]\n"
                + "           ,[review]\n"
                + "           ,[house_price]\n"
                + "           ,[status]\n"
                + "           ,[address]\n"
                + "           ,[description]\n"
                + "           ,[loca_id]\n"
                + "           ,[menu_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
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

            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
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

    public static void main(String[] args) {
        HouseDAO h = new HouseDAO();
        System.out.println(h.getHouse());
    }
}
