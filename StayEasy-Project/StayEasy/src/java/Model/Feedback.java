/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Model;

import java.util.Date;

/**
 *
 * @author HP
 */
public class Feedback {
    private int id;
    private String content;
    private int star;
    private int bill_id;
    private Date date;
    private int user_id;
    private String fullname;

    public Feedback() {
    }

    public Feedback(int id, String content, int star, int bill_id, Date date) {
        this.id = id;
        this.content = content;
        this.star = star;
        this.bill_id = bill_id;
        this.date = date;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
