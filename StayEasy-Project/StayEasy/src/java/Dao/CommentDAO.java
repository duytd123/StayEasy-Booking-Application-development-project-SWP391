/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connect.DBContext;
import Model.Comment;
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
    public CommentDAO(){
        DBContext dbcontext = new DBContext();
        try {
            con = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
    }
    
    public List<Comment> getComment(){
        String sql = "select * from Comment";
        List<Comment> list = new ArrayList<>();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
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
            System.out.println("error: "+e);
        }
        
        return list;
    }
    
    public Comment  getComments(){
        String sql = "select * from Comment";
        Comment m = new Comment();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                int cid = resultSet.getInt(1);
                int userid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                String comment = resultSet.getString(4);
                Date date = resultSet.getDate(5);

                //tạo model hứng giữ liệu
                m = new Comment(cid, userid, houseid, comment, date);
            }
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        
        return m;
    }
    
    public void deleteComment(int id){
        String sql = "DELETE FROM [dbo].[Comment]\n" +
                    "      WHERE cid = ? ";
        try {
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            pre.executeUpdate();

        } catch (Exception e) {
            System.out.println("error :  " + e);
        }
    }
    
    public void addComment(Comment c) {
        String sql = "INSERT INTO [dbo].[Comment]\n" +
                    "           ([userid]\n" +
                    "           ,[houseid]\n" +
                    "           ,[comment]\n" +
                    "           ,[date])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?\n" +
                    "           ,?)";
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
    
        public void editComment(Comment c){
        String sql = "UPDATE [dbo].[Comment]\n" +
                    "   SET [userid] = ?\n" +
                    "      ,[houseid] = ?\n" +
                    "      ,[comment] = ?\n" +
                    "      ,[date] = ?\n" +
                    " WHERE cid = ?";
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
    
   public Comment  getCommentbyid(int id){
        String sql = "select * from [dbo].[Comment] where cid = ?";
        Comment m = new Comment();
        try {
            //tạo khay chứa câu lệnh
            PreparedStatement pre = con.prepareStatement(sql);
            pre.setInt(1, id);
            //chạy câu lệnh và tạo khay chứa kết quả câu lệnh
            ResultSet resultSet = pre.executeQuery();
            while(resultSet.next()){
                int cid = resultSet.getInt(1);
                int userid = resultSet.getInt(2);
                int houseid = resultSet.getInt(3);
                String comment = resultSet.getString(4);
                Date date = resultSet.getDate(5);

                //tạo model hứng giữ liệu
                m = new Comment(cid, userid, houseid, comment, date);
            }
        } catch (Exception e) {
            System.out.println("error: "+e);
        }
        
        return m;
    } 
    
    
}

    
