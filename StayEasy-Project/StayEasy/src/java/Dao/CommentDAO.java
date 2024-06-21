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
        String sql = "INSERT INTO [dbo].[Comment]\n"
                + "           ([userid]\n"
                + "           ,[houseid]\n"
                + "           ,[comment]\n"
                + "           ,[date])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, c.getUserid());
            pre.setInt(2, c.getHouseid());
            pre.setString(3, c.getComment());
            java.sql.Date DateSql = new java.sql.Date(c.getDate().getTime());
            pre.setDate(4, DateSql);

            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
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
        String sql = "SELECT c.cid, c.userid, c.houseid, c.comment, c.date, "
                + "u.fullname, h.house_name "
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
                String username = rs.getString("username");
                String houseName = rs.getString("house_name");

                Comment commentObj = new Comment(cid, userid, houseid, comment, date);
                CommentWithInfo commentWithInfo = new CommentWithInfo(commentObj, username, houseName);
                commentList.add(commentWithInfo);
            }
        } catch (Exception e) {
            System.out.println("Error fetching comments: " + e);
        }

        return commentList;
    }

    public List<Comment> getCommentsByHouseId(int hostId) {
        String sql = "SELECT c.cid, c.userid, c.houseid, c.comment, c.date "
                + "FROM Comment c "
                + "INNER JOIN House h ON c.houseid = h.house_id "
                + "WHERE h.host_id = ?";
        List<Comment> list = new ArrayList<>();
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
                Comment m = new Comment(cid, userid, houseid, comment, date);
                list.add(m);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
        return list;
    }
}
