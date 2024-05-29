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
        String sql = "UPDATE [dbo].[House_additional_service]\n" +
                        "   SET [add_service_id] = ?\n" +
                        "      ,[house_id] = ?\n" +
                        "      ,[add_service_status] = ?\n" +
                        "      ,[add_service_price] = ?\n" +
                        " WHERE house_add_service_id = ?";
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
        String sql = "INSERT INTO [dbo].[House_additional_service]\n" +
                    "           ([add_service_id]\n" +
                    "           ,[house_id]\n" +
                    "           ,[add_service_status]\n" +
                    "           ,[add_service_price])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            pre.setInt(1, hs.getServiceid());
            pre.setInt(2, hs.getHouseid());
            pre.setInt(3, hs.getServicestatus());
            pre.setFloat(4, hs.getServiceprice());
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }
    
    public void deleteHouseAdditionalService(int id) {
        String sql = "DELETE FROM [dbo].[House_additional_service]\n" +
                    "      WHERE house_add_service_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }
    
    public List<HouseAdditionalService> getHouseAdditionalServicebyID(int id){
        String sql = "select * from House_additional_service where house_id = ?";
        List<HouseAdditionalService> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                int houseaddserviceid = resultSet.getInt(1);
                int serviceid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                int servicestatus = resultSet.getInt(4);
                float serviceprice = resultSet.getInt(5);
                HouseAdditionalService hs = new HouseAdditionalService(houseaddserviceid, serviceid, houseid, servicestatus, serviceprice);
                list.add(hs);
            }
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        
        return list;
    }
    
    public HouseAdditionalService getHouseAdditionalServicebyIDs(int id){
        String sql = "select * from House_additional_service where house_add_service_id = ?";
        HouseAdditionalService h = new HouseAdditionalService();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                int houseaddserviceid = resultSet.getInt(1);
                int serviceid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                int servicestatus = resultSet.getInt(4);
                float serviceprice = resultSet.getInt(5);
                h = new HouseAdditionalService(houseaddserviceid, serviceid, houseid, servicestatus, serviceprice);
            }
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        
        return h;
    }
    
    
    
}
