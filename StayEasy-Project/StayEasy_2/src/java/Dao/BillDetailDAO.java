/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connect.DBContext;
import Model.BillDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BillDetailDAO {

    Connection con;

    public BillDetailDAO() {
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public BillDetail getBillDeatailbyhouId(int id) {
        String sql = "select * from Bill_detail where house_id = ?";
        BillDetail b = null;

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int billdetailid = resultSet.getInt(1);
                int billid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                Date startdate = resultSet.getDate(4);
                Date enđate = resultSet.getDate(5);
                String note = resultSet.getString(6);

                //tạo model hứng giữ liệu
                b = new BillDetail(billdetailid, billid, houseid, startdate, enđate, note);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return b;
    }

    public List<BillDetail> getBillDeatai() {
        String sql = "select * from Bill_detail";
        List<BillDetail> list = new ArrayList<>();

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int billdetailid = resultSet.getInt(1);
                int billid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                Date startdate = resultSet.getDate(4);
                Date enđate = resultSet.getDate(5);
                String note = resultSet.getString(6);

                //tạo model hứng giữ liệu
                BillDetail b = new BillDetail(billdetailid, billid, houseid, startdate, enđate, note);
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public BillDetail getBillDeatailbyID(int id) {
        String sql = "select * from Bill_detail where billdetail_id = ?";
        BillDetail b = new BillDetail();

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int billdetailid = resultSet.getInt(1);
                int billid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                Date startdate = resultSet.getDate(4);
                Date enđate = resultSet.getDate(5);
                String note = resultSet.getString(6);

                //tạo model hứng giữ liệu
                b = new BillDetail(billdetailid, billid, houseid, startdate, enđate, note);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return b;
    }

    public void editBillDetail(BillDetail billdetail) {
        String sql = "UPDATE [dbo].[Bill_detail]\n"
                + "   SET [bill_id] = ?\n"
                + "      ,[house_id] = ?\n"
                + "      ,[start_date] = ?\n"
                + "      ,[end_date] = ?\n"
                + "      ,[note] = ?\n"
                + " WHERE billdetail_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            pre.setInt(1, billdetail.getBillid());
            pre.setInt(2, billdetail.getHouseid());
            java.sql.Date DateSql = new java.sql.Date(billdetail.getStartdate().getTime());
            pre.setDate(3, DateSql);
            java.sql.Date DateSq2 = new java.sql.Date(billdetail.getEnddate().getTime());
            pre.setDate(4, DateSq2);
            pre.setString(5, billdetail.getNote());
            pre.setInt(6, billdetail.getBillid());
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void deleteBillDetail(int id) {
        String sql = "DELETE FROM [dbo].[Bill_detail]\n"
                + "      WHERE billdetail_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }
    
    
    public void deleteBillDetailByBillId(int id) {
        String sql = "DELETE FROM [dbo].[Bill_detail]\n"
                + "      WHERE bill_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public int addBillDetail(BillDetail billdetail) {
        String sql = "INSERT INTO [dbo].[Bill_detail]\n"
                + "           ([bill_id]\n"
                + "           ,[house_id]\n"
                + "           ,[start_date]\n"
                + "           ,[end_date]\n"
                + "           ,[note])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        int id = -1;
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //set gia tri cho dau ? 
            pre.setInt(1, billdetail.getBillid());
            pre.setInt(2, billdetail.getHouseid());
            java.sql.Date DateSql = new java.sql.Date(billdetail.getStartdate().getTime());
            pre.setDate(3, DateSql);
            java.sql.Date DateSq2 = new java.sql.Date(billdetail.getEnddate().getTime());
            pre.setDate(4, DateSq2);
            pre.setString(5, billdetail.getNote());
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
            System.out.println("error :  " + e);
        }

        return id;
    }

    public BillDetail getBillDeatailbyBillID(int billID) {
        String sql = "select * from Bill_detail where bill_id = ?";
        BillDetail b = new BillDetail();

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, billID);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int billdetailid = resultSet.getInt(1);
                int billid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                Date startdate = resultSet.getDate(4);
                Date enđate = resultSet.getDate(5);
                String note = resultSet.getString(6);

                //tạo model hứng giữ liệu
                b = new BillDetail(billdetailid, billid, houseid, startdate, enđate, note);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return b;
    }

    public boolean isHouseAvailable(int houseId, java.sql.Date startDate, java.sql.Date endDate) {
        String sql = "SELECT COUNT(*) FROM Bill_detail bd "
                + "JOIN Bill b ON bd.bill_id = b.bill_id "
                + "WHERE bd.house_id = ? "
                + "AND ((b.status <> 1 AND (bd.start_date <= ? AND bd.end_date >= ?)) "
                + "OR (b.status <> 1 AND (bd.start_date <= ? AND bd.end_date >= ?)))";

        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, houseId);
            statement.setDate(2, new java.sql.Date(endDate.getTime()));
            statement.setDate(3, new java.sql.Date(startDate.getTime()));
            statement.setDate(4, new java.sql.Date(endDate.getTime()));
            statement.setDate(5, new java.sql.Date(startDate.getTime()));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) == 0;
                }
            }
        } catch (Exception e) {
            System.out.println("isHouseAvailable: " + e);
        }
        return false;
    }

    public void insertBillDetail(BillDetail billDetail) {
        String sql = "INSERT INTO BillDetail (billid, houseid, startdate, enddate, note) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, billDetail.getBillid());
            statement.setInt(2, billDetail.getHouseid());
            statement.setDate(3, new java.sql.Date(billDetail.getStartdate().getTime()));
            statement.setDate(4, new java.sql.Date(billDetail.getEnddate().getTime()));
            statement.setString(5, billDetail.getNote());
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Insert bill detail: " + e);
        }
    }

//        public static void main(String[] args) {
//        BillDetailDAO a = new BillDetailDAO();
//    }
}
