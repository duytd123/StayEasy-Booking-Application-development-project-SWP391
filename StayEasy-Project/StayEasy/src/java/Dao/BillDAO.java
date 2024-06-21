/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connect.DBContext;
import Model.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BillDAO {

    Connection con;

    public BillDAO() {
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }
    

    public List<Bill> getBill() {
        String sql = "select * from Bill";
        List<Bill> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int billid = resultSet.getInt(1);
                Date date = resultSet.getDate(2);
                float total = resultSet.getFloat(3);
                int status = resultSet.getInt(4);
                int userid = resultSet.getInt(5);

                //tạo model hứng giữ liệu
                Bill b = new Bill(billid, date, total, status, userid);

                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public Bill getBills() {
        String sql = "select * from Bill";
        Bill b = new Bill();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int billid = resultSet.getInt(1);
                Date date = resultSet.getDate(2);
                float total = resultSet.getFloat(3);
                int status = resultSet.getInt(4);
                int userid = resultSet.getInt(5);

                //tạo model hứng giữ liệu
                b = new Bill(billid, date, total, status, userid);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return b;
    }

    public void editBill(Bill bill) {
        String sql = "UPDATE [dbo].[Bill]\n"
                + "   SET [date] = ?\n"
                + "      ,[total] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[user_id] = ?\n"
                + " WHERE bill_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            java.sql.Date DateSql = new java.sql.Date(bill.getDate().getTime());
            pre.setDate(1, DateSql);
            pre.setFloat(2, bill.getTotal());
            pre.setInt(3, bill.getStatus());
            pre.setInt(4, bill.getUserid());
            pre.setInt(5, bill.getBillid());
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void deleteBill(int id) {
        String sql = "DELETE FROM [dbo].[Bill]\n"
                + "      WHERE bill_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public int addBill(Bill bill) {
        String sql = "INSERT INTO [dbo].[Bill]\n"
                + "           ([date]\n"
                + "           ,[total]\n"
                + "           ,[status]\n"
                + "           ,[user_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        int id = -1;

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //set gia tri cho dau ? 
            java.sql.Date DateSql = new java.sql.Date(bill.getDate().getTime());
            pre.setDate(1, DateSql);
            pre.setFloat(2, bill.getTotal());
            pre.setInt(3, bill.getStatus());
            pre.setInt(4, bill.getUserid());
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();

            //get id
            ResultSet generatedKeys = pre.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            } else {
                throw new Exception("Creating bill failed, no ID obtained.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public Bill getBillbyId(int id) {
        String sql = "select * from Bill where bill_id = ?";
        Bill b = new Bill();

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int billid = resultSet.getInt(1);
                Date date = resultSet.getDate(2);
                float total = resultSet.getFloat(3);
                int status = resultSet.getInt(4);
                int userid = resultSet.getInt(5);

                //tạo model hứng giữ liệu
                b = new Bill(billid, date, total, status, userid);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return b;
    }
public List<Bill> getBillByDate(String dateString){
        String sql = "select * from Bill where date = '"+dateString+"'";
        List<Bill> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                int billid = resultSet.getInt(1);
                Date date = resultSet.getDate(2);
                float total = resultSet.getFloat(3);
                int status = resultSet.getInt(4);
                int userid = resultSet.getInt(5);

                //tạo model hứng giữ liệu
                Bill b = new Bill(billid, date, total, status, userid);
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        
        return list;
    }

    public List<Bill> getBillbyUserId(int user_id){
        String sql = "select * from Bill where user_id = ?";
        List<Bill> list = new ArrayList<>();
        try{
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, user_id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                int billid = resultSet.getInt(1);
                Date date = resultSet.getDate(2);
                float total = resultSet.getFloat(3);
                int status = resultSet.getInt(4);
                int userid = resultSet.getInt(5);

                //tạo model hứng giữ liệu
                Bill b = new Bill(billid, date, total, status, userid);
               
               list.add(b);
            }
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        
        return list;
    }

    
    
    public void updatebillStatus(int billid){
        String sql = "UPDATE [dbo].[Bill]\n" +
                "   SET [status] = ?" +
                " WHERE bill_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, 0);
            pre.setInt(2, billid);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }
    
    
    public List<Bill> getBillsByHostId(int hostId) {
        List<Bill> bills = new ArrayList<>();
        String sql = "SELECT b.bill_id, b.date, b.total, b.status, b.user_id " +
                     "FROM Bill b " +
                     "INNER JOIN Bill_detail bd ON b.bill_id = bd.bill_id " +
                     "INNER JOIN House h ON bd.house_id = h.house_id " +
                     "WHERE h.host_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, hostId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt("bill_id");
                Date date = resultSet.getDate("date");
                float total = resultSet.getFloat("total");
                int status = resultSet.getInt("status");
                int userId = resultSet.getInt("user_id");
                Bill bill = new Bill(billId, date, total, status, userId);
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }
    

}
