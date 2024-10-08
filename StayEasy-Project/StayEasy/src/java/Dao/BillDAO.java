/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connect.DBContext;
import Model.Bill;
import Model.Bill1;
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

    public float TotalBill() {
        String sql = "  select sum (Bill.total) from Bill ";
        float count = 0;

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

    public List<Bill> getBillByDate(String dateString) {
        String sql = "select * from Bill where date = '" + dateString + "'";
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

    public List<Bill> getBillbyUserId(int user_id) {
        String sql = "select * from Bill where user_id = ?";
        List<Bill> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, user_id);
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

    public List<Bill> getHistoryBill(int userId) {
        String sql = "SELECT * FROM Bill WHERE user_id = ?";
        List<Bill> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, userId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Bill b = new Bill();
                b.setBillid(resultSet.getInt("bill_id"));
                b.setDate(resultSet.getDate("date"));
                b.setTotal(resultSet.getFloat("total"));
                b.setStatus(resultSet.getInt("status"));
                b.setUserid(resultSet.getInt("user_id"));
                b.setUserName("");
                b.setFullname(resultSet.getString("fullname"));
                b.setPhone(resultSet.getString("phone"));
                b.setEmail(resultSet.getString("email"));
                b.setReason(resultSet.getString("reason"));
                b.setPayment(resultSet.getString("payment_type"));
                list.add(b);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);

        }

        return list;
    }

    public Bill getBillByIdUser(int billId, int userId) {
        Bill bill = null;
        String sql = "SELECT * FROM Bill WHERE bill_id = ? and user_id=?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, billId);
            pre.setInt(2, userId);
            ResultSet resultSet = pre.executeQuery();
            if (resultSet.next()) {
                bill = new Bill();
                bill.setBillid(resultSet.getInt("bill_id"));
                bill.setDate(resultSet.getDate("date"));
                bill.setTotal(resultSet.getFloat("total"));
                bill.setStatus(resultSet.getInt("status"));
                bill.setUserid(resultSet.getInt("user_id"));
                bill.setUserName("");
                bill.setFullname(resultSet.getString("fullname"));
                bill.setPhone(resultSet.getString("phone"));
                bill.setEmail(resultSet.getString("email"));
                bill.setReason(resultSet.getString("reason"));
                bill.setPayment(resultSet.getString("payment_type"));
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return bill;
    }

    public int cancelBill(int billid, String reason) {
        String sql = "UPDATE [dbo].[Bill]\n"
                + "   SET [status] = ?, reason=?"
                + " WHERE bill_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, 2);
            pre.setString(2, reason);
            pre.setInt(3, billid);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            return pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
        return 0;
    }

    public void updatebillStatus(int billid) {
        String sql = "UPDATE [dbo].[Bill]\n"
                + "   SET [status] = ?"
                + " WHERE bill_id = ?";
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

    public List<Bill1> getBillsByHostId(int hostId) {
        List<Bill1> bills = new ArrayList<>();
        String sql = "SELECT b.bill_id, b.date, b.total, b.status, b.user_id, b.payment_type, b.reason, b.fullname, b.phone, b.email "
                + "FROM Bill b "
                + "INNER JOIN Bill_detail bd ON b.bill_id = bd.bill_id "
                + "INNER JOIN House h ON bd.house_id = h.house_id "
                + "WHERE h.host_id = ?";
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
                String paymentType = resultSet.getString("payment_type");
                String reason = resultSet.getString("reason");
                String fullname = resultSet.getString("fullname");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");

                Bill1 bill = new Bill1(billId, date, total, status, userId, null, fullname, phone, email, reason, paymentType, null);
                bills.add(bill);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }

    public int bookingBill(Bill bill) {
        String sql = "INSERT INTO Bill (date, total, status, user_id, fullname, phone, email, payment_type) VALUES (?,?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            statement.setDate(i++, new java.sql.Date(bill.getDate().getTime()));
            statement.setFloat(i++, bill.getTotal());
            statement.setInt(i++, bill.getStatus());
            statement.setInt(i++, bill.getUserid());
            statement.setString(i++, bill.getFullname());
            statement.setString(i++, bill.getPhone());
            statement.setString(i++, bill.getEmail());
            statement.setString(i++, bill.getPayment());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Booking dao: " + e);
        }
        return -1;
    }

    public double calculateTotalMoneyForHost(int hostId) {
        double totalMoney = 0.0;
        String sql = "SELECT SUM(b.total) AS total_money "
                + "FROM Bill b "
                + "INNER JOIN Bill_detail bd ON b.bill_id = bd.bill_id "
                + "INNER JOIN House h ON bd.house_id = h.house_id "
                + "WHERE b.status = 1 AND h.host_id = ?";
        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, hostId);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    totalMoney = rs.getDouble("total_money");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalMoney;
    }

    public int countPendingBillsForHost(int hostId) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS pending_count "
                + "FROM Bill_detail bd "
                + "INNER JOIN Bill b ON bd.bill_id = b.bill_id "
                + "INNER JOIN House h ON bd.house_id = h.house_id "
                + "WHERE b.status = 0 AND h.host_id = ?";

        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, hostId);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("pending_count");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public double totalMoneyWeek(int day, int from, int to, int year, int month, int hostId) {
        String sql = "";
        if (from > to) {
            sql = "SELECT SUM(b.total) AS TotalRevenue\n"
                    + "FROM [HouseBooking2].[dbo].[Bill] b\n"
                    + "JOIN [HouseBooking2].[dbo].[Bill_detail] bd ON b.bill_id = bd.bill_id\n"
                    + "JOIN [HouseBooking2].[dbo].[House] h ON bd.house_id = h.house_id\n"
                    + "WHERE h.host_id = ?\n"
                    + "  AND ((DAY(b.[date]) >= ? AND MONTH(b.[date]) = ?) OR (DAY(b.[date]) <= ? AND MONTH(b.[date]) = ?))\n"
                    + "  AND YEAR(b.[date]) = ?\n"
                    + "  AND DATEPART(dw, b.[date]) = ?";
        } else {
            sql = "SELECT SUM(b.total) AS TotalRevenue\n"
                    + "FROM [HouseBooking2].[dbo].[Bill] b\n"
                    + "JOIN [HouseBooking2].[dbo].[Bill_detail] bd ON b.bill_id = bd.bill_id\n"
                    + "JOIN [HouseBooking2].[dbo].[House] h ON bd.house_id = h.house_id\n"
                    + "WHERE h.host_id = ?\n"
                    + "  AND DAY(b.[date]) BETWEEN ? AND ?\n"
                    + "  AND MONTH(b.[date]) = ?\n"
                    + "  AND YEAR(b.[date]) = ?\n"
                    + "  AND DATEPART(dw, b.[date]) = ?";
        }
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, hostId);
            if (from > to) {
                st.setInt(2, from);
                st.setInt(3, month);
                st.setInt(4, to);
                st.setInt(5, (month + 1));
                st.setInt(6, year);
                st.setInt(7, day);
            } else {
                st.setInt(2, from);
                st.setInt(3, to);
                st.setInt(4, month);
                st.setInt(5, year);
                st.setInt(6, day);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double totalMoneyMonth(int month, int year, int hostId) {
        String sql = "SELECT SUM(b.total) AS TotalRevenue\n"
                + "FROM [HouseBooking2].[dbo].[Bill] b\n"
                + "JOIN [HouseBooking2].[dbo].[Bill_detail] bd ON b.bill_id = bd.bill_id\n"
                + "JOIN [HouseBooking2].[dbo].[House] h ON bd.house_id = h.house_id\n"
                + "WHERE h.host_id = ?\n"
                + "  AND MONTH(b.[date]) = ?\n"
                + "  AND YEAR(b.[date]) = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, hostId);
            st.setInt(2, month);
            st.setInt(3, year);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
