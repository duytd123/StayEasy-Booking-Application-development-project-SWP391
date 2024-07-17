/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.util.List;

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
    private String fullname;
    private String phone;
    private String email;
    private String reason;
    private String payment;
    
    private List<BillDetail> billDetail;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Bill() {
    }

    public Bill(int billid, float total, int status, int userid, String UserName) {
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

    public Bill(int billid, Date date, float total, int status, int userid, String userName, String fullname, String phone, String email, List<BillDetail> billDetail) {
        this.billid = billid;
        this.date = date;
        this.total = total;
        this.status = status;
        this.userid = userid;
        this.userName = userName;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.billDetail = billDetail;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    
    public List<BillDetail> getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(List<BillDetail> billDetail) {
        this.billDetail = billDetail;
    }
    
    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }
}
