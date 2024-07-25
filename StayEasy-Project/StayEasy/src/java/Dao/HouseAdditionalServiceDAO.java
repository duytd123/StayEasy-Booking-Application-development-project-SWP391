/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connect.DBContext;
import Model.HouseAdditionalService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HouseAdditionalServiceDAO {

    Connection con;

    public HouseAdditionalServiceDAO() {
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public List<HouseAdditionalService> getHouseAdditionalService() {
        String sql = "select * from House_additional_service";
        List<HouseAdditionalService> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseaddserviceid = resultSet.getInt(1);
                int serviceid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                int servicestatus = resultSet.getInt(4);
                float serviceprice = resultSet.getInt(5);
                HouseAdditionalService hs = new HouseAdditionalService(houseaddserviceid, serviceid, houseid, servicestatus, serviceprice);
                list.add(hs);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public void editHouseAdditionalService(HouseAdditionalService hs) {
        String sql = "UPDATE [dbo].[House_additional_service]\n"
                + "   SET [add_service_id] = ?\n"
                + "      ,[house_id] = ?\n"
                + "      ,[add_service_status] = ?\n"
                + "      ,[add_service_price] = ?\n"
                + " WHERE house_add_service_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            pre.setInt(1, hs.getServiceid());
            pre.setInt(2, hs.getHouseid());
            pre.setInt(3, hs.getServicestatus());
            pre.setFloat(4, hs.getServiceprice());
            pre.setInt(5, hs.getHouseaddserviceid());
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void addHouseAdditionalService(HouseAdditionalService hs) {
        String sql = "INSERT INTO [dbo].[House_additional_service] "
                + "([add_service_id], [house_id], [add_service_status], [add_service_price]) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, hs.getServiceid());
            pre.setInt(2, hs.getHouseid());
            pre.setInt(3, hs.getServicestatus());
            pre.setFloat(4, hs.getServiceprice());
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }
    
    public void updateServicePrice(int houseAddServiceId, float price) {
        String sql = "UPDATE [dbo].[House_additional_service] SET [add_service_price] = ? WHERE [house_add_service_id] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setFloat(1, price);
            pre.setInt(2, houseAddServiceId);
            
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void deleteHouseAdditionalService(int id) {
        String sql = "DELETE FROM [dbo].[House_additional_service]\n"
                + "      WHERE house_add_service_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public List<HouseAdditionalService> getHouseAdditionalServicebyID(int id) {
        String sql = "select * from House_additional_service where house_id = ?";
        List<HouseAdditionalService> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseaddserviceid = resultSet.getInt(1);
                int serviceid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                int servicestatus = resultSet.getInt(4);
                float serviceprice = resultSet.getInt(5);
                HouseAdditionalService hs = new HouseAdditionalService(houseaddserviceid, serviceid, houseid, servicestatus, serviceprice);
                list.add(hs);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public HouseAdditionalService getHouseAdditionalServicebyIDs(int id) {
        String sql = "select * from House_additional_service where house_add_service_id = ?";
        HouseAdditionalService h = new HouseAdditionalService();
        try {

            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);

            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseaddserviceid = resultSet.getInt(1);
                int serviceid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                int servicestatus = resultSet.getInt(4);
                float serviceprice = resultSet.getInt(5);
                h = new HouseAdditionalService(houseaddserviceid, serviceid, houseid, servicestatus, serviceprice);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return h;
    }

    public void addServiceToHouse(int houseId, int serviceId) {
        String sql = "INSERT INTO House_additional_service (add_service_id, house_id, add_service_status, add_service_price) VALUES (?, ?, 1, 0)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, serviceId);
            pre.setInt(2, houseId);
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public List<HouseAdditionalService> getHouseAdditionalServiceForHouse(int houseId) {
        String sql = "SELECT * FROM House_additional_service WHERE house_id = ?";
        List<HouseAdditionalService> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, houseId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int houseAddServiceId = resultSet.getInt("house_add_service_id");
                int serviceId = resultSet.getInt("add_service_id");
                int houseIdResult = resultSet.getInt("house_id");
                int serviceStatus = resultSet.getInt("add_service_status");
                float servicePrice = resultSet.getFloat("add_service_price");
                HouseAdditionalService has = new HouseAdditionalService(houseAddServiceId, serviceId, houseIdResult, serviceStatus, servicePrice);
                list.add(has);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return list;
    }

}
