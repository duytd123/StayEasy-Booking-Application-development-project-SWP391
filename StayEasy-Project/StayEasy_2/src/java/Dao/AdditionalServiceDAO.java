/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connect.DBContext;
import Model.AdditionalService;
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
public class AdditionalServiceDAO {

    Connection con;

    public AdditionalServiceDAO() {
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public List<AdditionalService> getAdditionalServicee() {
        String sql = "SELECT * FROM Additional_service";
        List<AdditionalService> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int serviceid = resultSet.getInt("add_service_id");
                String servicename = resultSet.getString("add_serviceName");
                String servicedesc = resultSet.getString("add_serviceDesc");
                String imageUrl = resultSet.getString("image_url"); // New field
                AdditionalService as = new AdditionalService(serviceid, servicename, servicedesc, imageUrl);
                list.add(as);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public List<AdditionalService> getAdditionalService(int page, String s) {
        String sql = "WITH p AS ("
                + "   SELECT ROW_NUMBER() OVER (ORDER BY add_service_id ASC) AS num, * "
                + "   FROM Additional_service "
                + "   WHERE add_serviceName LIKE ?"
                + ") "
                + "SELECT * FROM p "
                + "WHERE num BETWEEN ? * 5 - (5 - 1) AND ? * 5";

        List<AdditionalService> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, "%" + s + "%");
            pre.setInt(2, page);
            pre.setInt(3, page);

            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int serviceid = resultSet.getInt("add_service_id");
                String servicename = resultSet.getString("add_serviceName");
                String servicedesc = resultSet.getString("add_serviceDesc");
                String imageUrl = resultSet.getString("image_url"); // Retrieve imageUrl

                AdditionalService as = new AdditionalService(serviceid, servicename, servicedesc, imageUrl);
                list.add(as);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public void editAdditionalService(AdditionalService as) {
        String sql = "UPDATE [dbo].[Additional_service]\n"
                + "   SET [add_serviceName] = ?\n"
                + "      ,[add_serviceDesc] = ?\n"
                + " WHERE add_service_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            pre.setString(1, as.getServicename());
            pre.setString(2, as.getServicedesc());
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void addAdditionalService(AdditionalService as) {
        String sql = "INSERT INTO [dbo].[Additional_service]\n"
                + "           ([add_serviceName]\n"
                + "           ,[add_serviceDesc])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?)";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            pre.setString(1, as.getServicename());
            pre.setString(2, as.getServicedesc());
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void deleteHouseAdditionalService(int id) {
        String sql = "DELETE FROM [dbo].[Additional_service]\n"
                + "      WHERE add_service_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public AdditionalService getAdditionalServicebyID(int id) {
        String sql = "SELECT * FROM Additional_service WHERE add_service_id = ?";
        AdditionalService as = new AdditionalService();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                int serviceid = resultSet.getInt("add_service_id");
                String servicename = resultSet.getString("add_serviceName");
                String servicedesc = resultSet.getString("add_serviceDesc");
                String imageUrl = null;
                as = new AdditionalService(serviceid, servicename, servicedesc, imageUrl);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return as;
    }

    public List<AdditionalService> getAdditionalServicebyName(String name) {
        String sql = "SELECT * FROM Additional_service WHERE add_serviceName LIKE ?";
        List<AdditionalService> list = new ArrayList<>();
        try {
            // Prepare SQL statement
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, "%" + name + "%");

            // Execute query and get results
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int serviceid = resultSet.getInt("add_service_id");
                String servicename = resultSet.getString("add_serviceName");
                String servicedesc = resultSet.getString("add_serviceDesc");
                String imageUrl = resultSet.getString("image_url"); // Retrieve image URL

                AdditionalService as = new AdditionalService(serviceid, servicename, servicedesc, imageUrl);
                list.add(as);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public int getNumber(String s) {
        String sql = "select count(*) from Additional_service where add_serviceName like ? ";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, "%" + s + "%");
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public List<AdditionalService> getAdditionalServicee1() {
        String sql = "SELECT * FROM Additional_service";
        List<AdditionalService> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int serviceId = resultSet.getInt("add_service_id");
                String serviceName = resultSet.getString("add_serviceName");
                String serviceDesc = resultSet.getString("add_serviceDesc");
                String imageUrl = resultSet.getString("image_url"); 

                AdditionalService as = new AdditionalService(serviceId, serviceName, serviceDesc, imageUrl);
                list.add(as);
            }
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return list;
    }

    public int createService(String serviceName, String serviceDesc) {
        String sql = "INSERT INTO Additional_service (add_serviceName, add_serviceDesc) VALUES (?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, serviceName);
            pre.setString(2, serviceDesc);
            pre.executeUpdate();

            ResultSet generatedKeys = pre.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return -1; // Return -1 if insertion fails
    }

}
