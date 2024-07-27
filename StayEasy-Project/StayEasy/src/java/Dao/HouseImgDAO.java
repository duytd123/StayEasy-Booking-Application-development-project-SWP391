/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connect.DBContext;
import Model.HouseImg;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HouseImgDAO {

    Connection con;

    public HouseImgDAO() {
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public List<HouseImg> getHouseImg() {
        String sql = "select * from House_img";
        List<HouseImg> list = new ArrayList<>();
        try {

            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int imgid = resultSet.getInt(1);
                String imglink = resultSet.getString(2);
                int houseid = resultSet.getInt(3);
                HouseImg h = new HouseImg(imgid, imglink, houseid);
                list.add(h);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public List<HouseImg> getHouseImgByHostIdAndHouseId(int hostId, int houseId) {
        String sql = "SELECT hi.img_id, hi.img_link, hi.house_id "
                + "FROM House_img hi "
                + "JOIN House h ON hi.house_id = h.house_id "
                + "WHERE h.host_id = ? AND hi.house_id = ?";
        List<HouseImg> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, hostId);
            pre.setInt(2, houseId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int imgId = resultSet.getInt("img_id");
                String imgLink = resultSet.getString("img_link");
                int houseIdFromDb = resultSet.getInt("house_id");
                HouseImg houseImg = new HouseImg(imgId, imgLink, houseIdFromDb);
                list.add(houseImg);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }

    public List<HouseImg> getHouseImgbyID(int id) {
        String sql = "select * from House_img where house_id = ?";
        List<HouseImg> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int imgid = resultSet.getInt(1);
                String imglink = resultSet.getString(2);
                int houseid = resultSet.getInt(3);
                HouseImg h = new HouseImg(imgid, imglink, houseid);
                list.add(h);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public void editHouse(HouseImg houseimg) {
        String sql = "UPDATE [dbo].[House_img]\n"
                + "   SET [img_link] = ?\n"
                + " WHERE house_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            pre.setString(1, houseimg.getImglink());
            pre.setInt(2, houseimg.getHouseid());
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void deleteHouse(int id) {
        String sql = "DELETE FROM [dbo].[House_img]\n"
                + "      WHERE house_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void addHouseImg(HouseImg houseimg) {
        String sql = "INSERT INTO [dbo].[House_img]\n"
                + "           ([img_link]\n"
                + "           ,[house_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, houseimg.getImglink());
            pre.setInt(2, houseimg.getHouseid());

            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public HouseImg getHouseImgbyIDs(int id) {
        String sql = "select * from House_img where house_id = ?";
        HouseImg h = new HouseImg();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int imgid = resultSet.getInt(1);
                String imglink = resultSet.getString(2);
                int houseid = resultSet.getInt(3);
                h = new HouseImg(imgid, imglink, houseid);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return h;
    }

    public void updateHouseImage(int houseId, String imagePath) {
        String insertHouseImgSql = "INSERT INTO [dbo].[House_img] ([img_link], [house_id]) VALUES (?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(insertHouseImgSql)) {
            stmt.setString(1, imagePath);
            stmt.setInt(2, houseId);
            stmt.executeUpdate();
            System.out.println("House image updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error updating house image: " + e.getMessage());
        }
    }

    public void deleteHouseImage(int imgId) {
        String deleteHouseImgSql = "DELETE FROM [dbo].[House_img] WHERE [img_id] = ?";

        try (PreparedStatement stmt = con.prepareStatement(deleteHouseImgSql)) {
            stmt.setInt(1, imgId);
            stmt.executeUpdate();
            System.out.println("House image deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error deleting house image: " + e.getMessage());
        }
    }
}
