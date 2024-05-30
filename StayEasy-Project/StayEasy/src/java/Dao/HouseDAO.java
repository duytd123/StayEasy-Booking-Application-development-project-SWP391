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
import java.util.ArrayList;
import java.util.Date;
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

    public List<House> searchHouse(String whereTo, String arrivals, String guests, String leaving) {
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

        String sql = "with r as (select * from house where house.house_id  not in ( select h.house_id from house as h\n"
                + "join Bill_detail as b on h.house_id = b.house_id\n"
                + "where house_name like ? and start_date <= ? and end_date >= ?))\n"
                + "select r.house_id,r.post_date,r.house_name,r.review,\n"
                + "r.house_price,r.status,r.address,r.description\n"
                + ", m.*, l.* from r \n"
                + "join Menu as m on m.menu_id = r.menu_id\n"
                + "join Location as l on l.loca_id = r.loca_id\n"
                + "where house_name like ?";
        List<House> list = new ArrayList<>();
        try {
            PreparedStatement pr = con.prepareStatement(sql);
            pr.setString(1, "%" + whereTo + "%");
            pr.setString(2, arrivals);
            pr.setString(3, leaving);
            pr.setString(4, "%" + whereTo + "%");
            ResultSet rs = pr.executeQuery();
            while(rs.next()) {
                House h = new House(rs.getInt(1), rs.getDate(2), rs.getString(3), rs.getString(4), rs.getFloat(5), rs.getInt(6), rs.getString(7),
                         rs.getString(8), new Location(rs.getInt(9), rs.getString(10)), new Menu(rs.getInt(11), rs.getString(12)));
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

}
