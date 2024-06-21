/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class Bill {
    private int billid;
    private Date date;
    private float total;
    private int status;
    private int userid;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public Bill() {
    }

    public Bill(int billid, float total, int status, int userid,String UserName) {
        this.billid = billid;
        this.total = total;
        this.status = status;
        this.userid = userid;
        this.userName = UserName;
    }

    
    public Bill(int billid, Date date, float total, int status, int userid) {
        this.billid = billid;
        this.date = date;
        this.total = total;
        this.status = status;
        this.userid = userid;
    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    
}
