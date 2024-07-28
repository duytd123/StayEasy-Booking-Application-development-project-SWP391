/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connect.DBContext;
import Model.Account;
import Model.Comment;
import Model.CommentWithInfo;
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
public class CommentDAO {

    Connection con;

    public CommentDAO() {
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public List<Comment> getComment() {
        String sql = "select * from Comment";
        List<Comment> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int cid = resultSet.getInt(1);
                int userid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                String comment = resultSet.getString(4);
                Date date = resultSet.getDate(5);

                //tạo model hứng giữ liệu
                Comment m = new Comment(cid, userid, houseid, comment, date);
                list.add(m);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return list;
    }

    public Comment getComments() {
        String sql = "select * from Comment";
        Comment m = new Comment();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int cid = resultSet.getInt(1);
                int userid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                String comment = resultSet.getString(4);
                Date date = resultSet.getDate(5);

                //tạo model hứng giữ liệu
                m = new Comment(cid, userid, houseid, comment, date);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return m;
    }

    public void deleteComment(int id) {
        String sql = "DELETE FROM [dbo].[Comment]\n"
                + "      WHERE cid = ? ";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public void addComment(Comment c) {
        String sql = "INSERT INTO [dbo].[Comment] ([userid], [houseid], [comment], [date]) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, c.getUserid());
            pre.setInt(2, c.getHouseid());
            pre.setString(3, c.getComment());
            pre.setDate(4, new java.sql.Date(c.getDate().getTime()));

            pre.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public List<CommentWithInfo> getCommentsByHouseId2(int houseId) {
        String sql = "SELECT c.cid, c.userid, c.houseid, c.comment, c.date, c.reply, "
                + "u.fullname, u.phone, u.email, h.house_name "
                + "FROM Comment c "
                + "INNER JOIN House h ON c.houseid = h.house_id "
                + "INNER JOIN Users u ON c.userid = u.user_id "
                + "WHERE h.house_id = ?";
        List<CommentWithInfo> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, houseId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int cid = resultSet.getInt("cid");
                int userid = resultSet.getInt("userid");
                int houseid = resultSet.getInt("houseid");
                String comment = resultSet.getString("comment");
                Date date = resultSet.getDate("date");
                String fullname = resultSet.getString("fullname");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String houseName = resultSet.getString("house_name");
                String reply = resultSet.getString("reply");
                CommentWithInfo m = new CommentWithInfo(cid, userid, houseid, comment, date, fullname, phone, email, houseName, reply);
                list.add(m);
            }
            System.out.println("Comments retrieved: " + list.size()); 
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return list;
    }

    public void editComment(Comment c) {
        String sql = "UPDATE [dbo].[Comment]\n"
                + "   SET [userid] = ?\n"
                + "      ,[houseid] = ?\n"
                + "      ,[comment] = ?\n"
                + "      ,[date] = ?\n"
                + " WHERE cid = ?";
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //set gia tri cho dau ? 
            pre.setInt(1, c.getUserid());
            pre.setInt(2, c.getHouseid());
            pre.setString(3, c.getComment());
            java.sql.Date DateSql = new java.sql.Date(c.getDate().getTime());
            pre.setDate(4, DateSql);
            pre.setInt(5, c.getCid());

            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }

    public Comment getCommentbyid(int id) {
        String sql = "select * from [dbo].[Comment] where cid = ?";
        Comment m = new Comment();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int cid = resultSet.getInt(1);
                int userid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                String comment = resultSet.getString(4);
                Date date = resultSet.getDate(5);

                //tạo model hứng giữ liệu
                m = new Comment(cid, userid, houseid, comment, date);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }

        return m;
    }

    public Comment getCommentById1(int commentId) {
        String sql = "SELECT * FROM Comment WHERE cid = ?";
        Comment comment1 = null;
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, commentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int cid = rs.getInt("cid");
                int userid = rs.getInt("userid");
                int houseid = rs.getInt("houseid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");

                comment1 = new Comment(cid, userid, houseid, comment, date);

            }
        } catch (Exception e) {
            System.out.println("Error fetching comment: " + e);
        }
        return comment1;
    }

    public List<CommentWithInfo> getAllCommentsWithUserInfo() {
        String sql = "SELECT c.cid, c.userid, c.houseid, c.comment, c.date, c.reply, "
                + "u.fullname, u.phone, u.email, h.house_name "
                + "FROM Comment c "
                + "INNER JOIN House h ON c.houseid = h.house_id "
                + "INNER JOIN Users u ON c.userid = u.user_id";

        List<CommentWithInfo> commentList = new ArrayList<>();

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int cid = rs.getInt("cid");
                int userid = rs.getInt("userid");
                int houseid = rs.getInt("houseid");
                String comment = rs.getString("comment");
                Date date = rs.getDate("date");
                String reply = rs.getString("reply");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String houseName = rs.getString("house_name");

                CommentWithInfo commentWithInfo = new CommentWithInfo(cid, userid, houseid, comment, date, fullname, phone, email, houseName, reply);
                commentList.add(commentWithInfo);
            }
        } catch (Exception e) {
            System.out.println("Error fetching comments: " + e);
        }

        return commentList;
    }

    public List<CommentWithInfo> getCommentsByHouseId(int hostId) {
        String sql = "SELECT c.cid, c.userid, c.houseid, c.comment, c.date, c.reply, "
                + "u.fullname, u.phone, u.email, h.house_name "
                + "FROM Comment c "
                + "INNER JOIN House h ON c.houseid = h.house_id "
                + "INNER JOIN Users u ON c.userid = u.user_id "
                + "WHERE h.host_id = ?";
        List<CommentWithInfo> list = new ArrayList<>();
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, hostId);
            ResultSet resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int cid = resultSet.getInt("cid");
                int userid = resultSet.getInt("userid");
                int houseid = resultSet.getInt("houseid");
                String comment = resultSet.getString("comment");
                Date date = resultSet.getDate("date");
                String fullname = resultSet.getString("fullname");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String houseName = resultSet.getString("house_name");
                String reply = resultSet.getString("reply");
                CommentWithInfo m = new CommentWithInfo(cid, userid, houseid, comment, date, fullname, phone, email, houseName, reply);
                list.add(m);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return list;
    }

    public List<CommentWithInfo> getCommentsWithInfoByHouseNameAndHost(String txtSearch, int hostId) {
        String sql = "SELECT c.cid, c.userid, c.houseid, c.comment, c.date, c.reply, "
                + "u.fullname, u.phone, u.email, h.house_name "
                + "FROM Comment c "
                + "INNER JOIN Users u ON c.userid = u.user_id "
                + "INNER JOIN House h ON c.houseid = h.house_id "
                + "WHERE h.house_name LIKE ? AND h.host_id = ?";
        List<CommentWithInfo> commentList = new ArrayList<>();

        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setString(1, "%" + txtSearch + "%");
            pre.setInt(2, hostId);
            try (ResultSet resultSet = pre.executeQuery()) {
                while (resultSet.next()) {
                    int cid = resultSet.getInt("cid");
                    int userid = resultSet.getInt("userid");
                    int houseid = resultSet.getInt("houseid");
                    String comment = resultSet.getString("comment");
                    Date date = resultSet.getDate("date");
                    String fullname = resultSet.getString("fullname");
                    String phone = resultSet.getString("phone");
                    String email = resultSet.getString("email");
                    String houseNameResult = resultSet.getString("house_name");
                    String reply = resultSet.getString("reply");
                    CommentWithInfo m = new CommentWithInfo(cid, userid, houseid, comment, date, fullname, phone, email, houseNameResult, reply);
                    commentList.add(m);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in getCommentsWithInfoByHouseNameAndHost: " + e.getMessage());
        }
        return commentList;
    }

    public void addReplyToComment(int commentId, String reply) {
        String sql = "UPDATE Comment SET reply = ? WHERE cid = ?";
        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setString(1, reply);
            pre.setInt(2, commentId);
            int rowsUpdated = pre.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No comment found with ID: " + commentId);

            }
        } catch (Exception e) {
            System.out.println("Error in addReplyToComment: " + e.getMessage());

        }
    }

    public CommentWithInfo getReplyCommentDetail(int commentId) {
        CommentWithInfo comment = null;
        String sql = "SELECT c.cid, c.userid, c.houseid, c.comment, c.date, u.fullname, u.email, u.phone, c.reply, h.house_name "
                + "FROM Comment c "
                + "JOIN Users u ON c.userid = u.user_id "
                + "JOIN House h ON c.houseid = h.house_id "
                + "WHERE c.cid = ?";
        try (PreparedStatement pre = con.prepareStatement(sql)) {
            pre.setInt(1, commentId);
            try (ResultSet rs = pre.executeQuery()) {
                if (rs.next()) {
                    int cid = rs.getInt("cid");
                    int userid = rs.getInt("userid");
                    int houseid = rs.getInt("houseid");
                    String commentText = rs.getString("comment");
                    Date date = rs.getDate("date");
                    String fullname = rs.getString("fullname");
                    String email = rs.getString("email");
                    String phone = rs.getString("phone");
                    String houseName = rs.getString("house_name");
                    String reply = rs.getString("reply");

                    comment = new CommentWithInfo(cid, userid, houseid, commentText, date, fullname, phone, email, houseName, reply);
                }
            }
        } catch (Exception e) {
            System.out.println("Error in getReplyCommentDetail: " + e.getMessage());
        }
        return comment;
    }

}
