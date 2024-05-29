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

    public List<House> searchHouse(String whereTo, String arrivals) {
        String sql = "select * \n"
                + "from House \n"
                + "where address like '%"+whereTo+"%' \n"
                + "and post_date like '%"+arrivals+"%' ";
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
