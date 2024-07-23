package Model;

import java.util.Date;
import java.util.List;

public class Bill1 {
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

    public Bill1() {
    }

    public Bill1(int billid, Date date, float total, int status, int userid, String userName, String fullname, String phone, String email, String reason, String payment, List<BillDetail> billDetail) {
        this.billid = billid;
        this.date = date;
        this.total = total;
        this.status = status;
        this.userid = userid;
        this.userName = userName;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.reason = reason;
        this.payment = payment;
        this.billDetail = billDetail;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<BillDetail> getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(List<BillDetail> billDetail) {
        this.billDetail = billDetail;
    }

   

    
}