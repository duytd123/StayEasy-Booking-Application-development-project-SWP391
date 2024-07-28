/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Connect.DBContext;
import Model.Feedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author HP
 */
public class FeedbackDAO {

    Connection conn;

    public FeedbackDAO() {
        DBContext dbcontext = new DBContext();
        try {
            conn = dbcontext.connection;
            System.out.println("Successful");
        } catch (Exception e) {
            System.out.println("error: " + e);
        }
    }

    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedback";
        try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setContent(rs.getString("content"));
                feedback.setStar(rs.getInt("star"));
                feedback.setBill_id(rs.getInt("bill_id"));
                feedback.setDate(rs.getDate("date"));
                feedback.setUser_id(rs.getInt("user_id"));
                feedbacks.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    public List<Feedback> getAllFeedbacks(int houseId) {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT F.*, A.fullname FROM Feedback AS F "
                + "JOIN Bill_detail AS B ON B.bill_id = F.bill_id "
                + "JOIN users AS A ON A.user_id = F.user_id "
                + "WHERE B.house_id = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, houseId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(rs.getInt("id"));
                feedback.setContent(rs.getString("content"));
                feedback.setStar(rs.getInt("star"));
                feedback.setBill_id(rs.getInt("bill_id"));
                feedback.setDate(rs.getDate("date"));
                feedback.setUser_id(rs.getInt("user_id"));
                feedback.setFullname(rs.getString("fullname"));
                feedbacks.add(feedback);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedbacks;
    }

    public Feedback getFeedbackById(int id) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    feedback = new Feedback();
                    feedback.setId(rs.getInt("id"));
                    feedback.setContent(rs.getString("content"));
                    feedback.setStar(rs.getInt("star"));
                    feedback.setBill_id(rs.getInt("bill_id"));
                    feedback.setDate(rs.getDate("date"));
                    feedback.setUser_id(rs.getInt("user_id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public Feedback getFeedbackByBillId(int billid) {
        Feedback feedback = null;
        String sql = "SELECT * FROM Feedback WHERE bill_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, billid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    feedback = new Feedback();
                    feedback.setId(rs.getInt("id"));
                    feedback.setContent(rs.getString("content"));
                    feedback.setStar(rs.getInt("star"));
                    feedback.setBill_id(rs.getInt("bill_id"));
                    feedback.setDate(rs.getDate("date"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return feedback;
    }

    public int addFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback (content, star, bill_id, date, user_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, feedback.getContent());
            stmt.setInt(2, feedback.getStar());
            stmt.setInt(3, feedback.getBill_id());
            stmt.setDate(4, new java.sql.Date(feedback.getDate().getTime()));
            stmt.setInt(5, feedback.getUser_id());
            return stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int updateFeedback(Feedback feedback) {
        String sql = "UPDATE Feedback SET content = ?, star = ?, bill_id = ?, date = ?, user_id=? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, feedback.getContent());
            stmt.setInt(2, feedback.getStar());
            stmt.setInt(3, feedback.getBill_id());
            stmt.setDate(4, new java.sql.Date(feedback.getDate().getTime()));
            stmt.setInt(5, feedback.getUser_id());
            stmt.setInt(6, feedback.getId());
            return stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void deleteFeedback(int id) {
        String sql = "DELETE FROM Feedback WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
