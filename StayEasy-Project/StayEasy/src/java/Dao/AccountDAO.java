/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Connect.DBContext;
import Model.Account;

import Model.Bill;
import Model.Bill1;

import Model.Role;
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
public class AccountDAO {

    Connection con;

    public AccountDAO() {
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public Account check(String username, String password) {
        String sql = "SELECT * FROM Users WHERE UserName = ? and Password = ? and [status] = 1";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int userid = rs.getInt("UserID");
                String fullname = rs.getString("FullName");
                String userimg = rs.getString("Image");
                String usernameFromDb = rs.getString("UserName");
                String pass = rs.getString("Password");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                int status = rs.getInt("status");
                int roleId = rs.getInt("RoleID");
                Role role = getRoleById(roleId, con);

                Account u = new Account(userid, fullname, userimg, usernameFromDb, pass, email, phone, status, role);
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Role getRoleById(int roleId, Connection con) {
        String sql = "SELECT * FROM Role WHERE role_id = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, roleId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("role_id");
                String name = rs.getString("name");
                return new Role(id, name);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    
    

    public void changePassword(Account s) {
        String sql = "Update Users set password = ? where username = ? and [status] = 1";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, s.getPass());
            st.setString(2, s.getUsername());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public String getUserNameByEmail(String email) {
        String sql = "SELECT Top 1 UserName FROM [dbo].[Users] WHERE Email = ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            //set ?
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                String name = rs.getString(1);
                return name;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Account getUserByUserName(String userName) {
        String sql = "SELECT * FROM [dbo].[Users] where UserName=? and [status] = 1";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            //set ?
            st.setString(1, userName);
            ResultSet rs = st.executeQuery();
            //1
            if (rs.next()) {
                int userid = rs.getInt("UserID");
                String fullname = rs.getString("FullName");
                String userimg = rs.getString("Image");
                String usernameFromDb = rs.getString("UserName");
                String pass = rs.getString("Password");
                String email = rs.getString("Email");
                String phone = rs.getString("Phone");
                int status = rs.getInt("status");
                int roleId = rs.getInt("RoleID");
                Role role = getRoleById(roleId, con);

                Account u = new Account(userid, fullname, userimg, usernameFromDb, pass, email, phone, status, role);
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void updatePassByUserName(String pass, String username) {
        String sql = "update Users set Password = ? where UserName= ?";
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, username);
            st.executeUpdate();
        } catch (Exception e) {
        }
    }
    
     public String checkEmailExist(String email) {
        try {
            String sql = "SELECT * FROM Users WHERE Email = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return email;
            }
        } catch (SQLException e) {
        }
        return null;
    }


//    public List<Account> getThreeUserMaxBill() {
//        String sql = "select top 3 Users.user_id,username, MAX(Bill.total)\n"
//                + "from Bill ,Users\n"
//                + "where Bill.user_id = Users.user_id\n"
//                + "group by Users.user_id ,username";
//        List<Account> list = new ArrayList<>();
//        try {
//            //tạo khay chứa câu lệnh
//            PreparedStatement pre = con.prepareStatement(sql);
//            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
//            ResultSet resultSet = pre.executeQuery();
//            while (resultSet.next()) {
//                int userid = resultSet.getInt(1);
//                String username = resultSet.getString(2);
//                float total = resultSet.getFloat(3);
//
//                //tạo model hứng giữ liệu
//                Account account = new Account();
//                account.setUserid(userid);
//                account.setUsername(username);
//                account.setTotal(total);
//                list.add(account);
//            }
//        } catch (Exception e) {
//            System.out.println("error: " + e);
//        }
//
//        return list;
//    }


    public int countAccountByRole(int role) {
        String sql = "select count(*) from Users where role_id = ?";
        int count = 0;

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, role);
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

    public int countAccount() {
        String sql = "select count(*) from Users ";
        int count = 0;

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

    public List<Account> getAllAccount() {
        String sql = "select * from dbo.Users";
        List<Account> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int userid = resultSet.getInt(1);
                String fullname = resultSet.getString(2);
                String userimg = resultSet.getString(3);
                String username = resultSet.getString(4);
                String password = resultSet.getString(5);
                String email = resultSet.getString(6);
                String phone = resultSet.getString(7);
                int status = resultSet.getInt(8);
                int roleid = resultSet.getInt(9);

                //tạo model hứng giữ liệu
                Role role = new Role(roleid, null);
                Account a = new Account(userid, fullname, userimg, username, password, email, phone, status, role);
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public Account getAccountbyId(int id) {
        String sql = "select * from dbo.Users where user_id = ?";
        Account a = new Account();

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int userid = resultSet.getInt(1);
                String fullname = resultSet.getString(2);
                String userimg = resultSet.getString(3);
                String username = resultSet.getString(4);
                String password = resultSet.getString(5);
                String email = resultSet.getString(6);
                String phone = resultSet.getString(7);
                int status = resultSet.getInt(8);
                int roleid = resultSet.getInt(9);

                //tạo model hứng giữ liệu
                Role role = new Role(roleid, null);
                a = new Account(userid, fullname, userimg, username, password, email, phone, status, role);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return a;
    }

    public Account getAccountbyEmail(String emailInput) {
        String sql = "select * from dbo.Users where email = ?";
        Account a = new Account();

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, emailInput);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int userid = resultSet.getInt(1);
                String fullname = resultSet.getString(2);
                String userimg = resultSet.getString(3);
                String username = resultSet.getString(4);
                String password = resultSet.getString(5);
                String email = resultSet.getString(6);
                String phone = resultSet.getString(7);
                int status = resultSet.getInt(8);
                int roleid = resultSet.getInt(9);

                //tạo model hứng giữ liệu
                Role role = new Role(roleid, null);
                a = new Account(userid, fullname, userimg, username, password, email, phone, status, role);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return a;
    }

    public Account getAccountbyUsername(String usernameInput) {
        String sql = "select * from dbo.Users where username = ?";
        Account a = new Account();

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, usernameInput);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int userid = resultSet.getInt(1);
                String fullname = resultSet.getString(2);
                String userimg = resultSet.getString(3);
                String username = resultSet.getString(4);
                String password = resultSet.getString(5);
                String email = resultSet.getString(6);
                String phone = resultSet.getString(7);
                int status = resultSet.getInt(8);
                int roleid = resultSet.getInt(9);

                //tạo model hứng giữ liệu
                Role role = new Role(roleid, null);
                a = new Account(userid, fullname, userimg, username, password, email, phone, status, role);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return a;
    }
        public int getPage(String search){
        String sql = "";
        if(search.isEmpty()){
          sql ="  select count(*) from Users";
        }
        else{
            sql ="  select count(*) from Users where fullname like ?";
        }
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            if(!search.isEmpty()){
                ps.setString(1, "%"+search +"%");
            }
            ResultSet rs=  ps.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println( e);
        }
        return -1;
    }
        public List<Account> getpagination(int index, String search) {
        List<Account> list = new ArrayList<>();
        System.out.println("hihi" + search);
        String sql = "with p as (select ROW_NUMBER() over (order by user_id asc) as num, * from Users where fullname like ?) \n"
                + "select * from p where num between ? * 5 - (5-1) and ? * 5";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
             ps.setString(1, "%"+ search +"%");
            ps.setInt(2, index );
            ps.setInt(3, index);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                int userid = resultSet.getInt(2);
                String fullname = resultSet.getString(3);
                String userimg = resultSet.getString(4);
                String username = resultSet.getString(5);
                String password = resultSet.getString(6);
                String email = resultSet.getString(7);
                String phone = resultSet.getString(8);
                int status = resultSet.getInt(9);
                int roleid = resultSet.getInt(10);

                //tạo model hứng giữ liệu
                Role role = new Role(roleid, null);
                Account a = new Account(userid, fullname, userimg, username, password, email, phone, status, role);
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public Account checkAccountByEmail(String emailInput) {
        try {
            String sql = "select email from dbo.Users where email = ?  ";

            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, emailInput);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                Account a = new Account(resultSet.getString(1));
                return a;
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return null;
    }

    public void editAccount(Account account) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [fullname] = ?\n"
                + "      ,[avatar] = ?\n"
                + "      ,[username] = ?\n"
                + "      ,[password] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[status] = ?\n"
                + "      ,[role_id] = ?\n"
                + " WHERE user_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            pre.setString(1, account.getFullname());
            pre.setString(2, account.getUserimg());
            pre.setString(3, account.getUsername());
            pre.setString(4, account.getPass());
            pre.setString(5, account.getEmail());
            pre.setString(6, account.getPhone());
            pre.setInt(7, account.getStatus());
            pre.setInt(8, account.getRole().getId());
            pre.setInt(9, account.getUserid());
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void deleteAccount(int id) {
        String sql = "DELETE FROM [dbo].[Users]\n"
                + "      WHERE user_id = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public Account checkAccountExist(String usernameInput) {
        String sql = "select * from dbo.Users where username = ?";
        Account a = null;
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, usernameInput);

            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int userid = resultSet.getInt(1);
                String fullname = resultSet.getString(2);
                String userimg = resultSet.getString(3);
                String username = resultSet.getString(4);
                String password = resultSet.getString(5);
                String email = resultSet.getString(6);
                String phone = resultSet.getString(7);
                int status = resultSet.getInt(8);
                int roleid = resultSet.getInt(9);
                Role role = new Role(roleid, null);
                a = new Account(userid, fullname, userimg, username, password, email, phone, status, role);
            }

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }

        return a;
    }

    public void signupAccount(Account a) {
        String sql = "INSERT INTO [dbo].[Users]\n"
                + "           ([fullname]\n"
                + "           ,[avatar]\n"
                + "           ,[username]\n"
                + "           ,[password]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[status]\n"
                + "           ,[role_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, a.getFullname());
            pre.setString(2, a.getUserimg());
            pre.setString(3, a.getUsername());
            pre.setString(4, a.getPass());
            pre.setString(5, a.getEmail());
            pre.setString(6, a.getPhone());
            pre.setInt(7, a.getStatus());
            pre.setInt(8, a.getRole().getId());
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public Account getAccountLogin(String usernameInput, String passwordInput) {
        String sql = "select * from dbo.Users where username = ? and password = ?";
        Account a = null;

        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, usernameInput);
            pre.setString(2, passwordInput);

            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int userid = resultSet.getInt(1);
                String fullname = resultSet.getString(2);
                String userimg = resultSet.getString(3);
                String username = resultSet.getString(4);
                String password = resultSet.getString(5);
                String email = resultSet.getString(6);
                String phone = resultSet.getString(7);
                int status = resultSet.getInt(8);
                int roleid = resultSet.getInt(9);

                //tạo model hứng giữ liệu
                Role role = new Role(roleid, null);
                a = new Account(userid, fullname, userimg, username, password, email, phone, status, role);

            }

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }

        return a;
    }

    public List<Account> getAccountbyName(String name) {
        String sql = "select * from Users where fullname like '%" + name + "%'";
        ArrayList<Account> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int userid = resultSet.getInt(1);
                String fullname = resultSet.getString(2);
                String userimg = resultSet.getString(3);
                String username = resultSet.getString(4);
                String password = resultSet.getString(5);
                String email = resultSet.getString(6);
                String phone = resultSet.getString(7);
                int status = resultSet.getInt(8);
                int roleid = resultSet.getInt(9);

                //tạo model hứng giữ liệu
                Role role = new Role(roleid, null);
                Account a = new Account(userid, fullname, userimg, username, password, email, phone, status, role);
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public boolean updateAccountStatus(int status, String username) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [status] = ?\n"
                + " WHERE username = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, status);
            pre.setString(2, username);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return false;
    }

    public Account getAccounts() {
        String sql = "select * from dbo.Users";
        Account a = new Account();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int userid = resultSet.getInt(1);
                String fullname = resultSet.getString(2);
                String userimg = resultSet.getString(3);
                String username = resultSet.getString(4);
                String password = resultSet.getString(5);
                String phone = resultSet.getString(6);
                int status = resultSet.getInt(7);
                int roleid = resultSet.getInt(8);

                //tạo model hứng giữ liệu
                Role role = new Role(roleid, null);
                a = new Account(userid, fullname, userimg, username, phone, sql, phone, status, role);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return a;
    }

    public void updateAccountPass(String pass, int user_id) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [password] = ?\n"
                + " WHERE user_id = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, pass);
            pre.setInt(2, user_id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public void updatePic(String imageFileName, int userId) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [avatar] = ?\n"
                + " WHERE [user_id] = ?";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, imageFileName);;
            pre.setInt(2, userId);
            pre.executeUpdate();

        } catch (Exception e) {
        }
    }

    public List<Bill1> getThreeUserMaxBill() {
        String sql =" SELECT TOP 5\n" +
"               	Max(Bill.bill_id) AS bill_id,\n" +
"                  Bill.user_id,\n" +
"               	Bill.status,\n" +
"               Users.fullname,\n" +
"			  Users.phone,\n" +
"			  Users.username,\n" +
"                 SUM(Bill.total) AS total_amount\n" +
"              FROM \n" +
"                  [dbo].[Bill]\n" +
"           	join Users on Bill.user_id = Users.user_id\n" +
"          GROUP BY \n" +
"             Bill.user_id, Bill.status,Users.fullname ,  Users.phone,Users.username order by total_amount desc";
        List<Bill1> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int billId = resultSet.getInt(1);
                int userid = resultSet.getInt(2);
                int status = resultSet.getInt(3);
                String fullname = resultSet.getString(4);
                String phone = resultSet.getString(5);
                String username = resultSet.getString(6);
                float totalmoney = resultSet.getFloat(7);

                Bill1 bill = new Bill1(billId,totalmoney, status, userid, fullname, phone, username);
                list.add(bill);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

//    public static void main(String[] args) {
//        AccountDAO a = new AccountDAO();
//        List<Bill1> b = a.getThreeUserMaxBill();
//        for (int i = 0; i < b.size(); i++) {
//            System.out.println(b.get(i));
//        }
//    }
}
