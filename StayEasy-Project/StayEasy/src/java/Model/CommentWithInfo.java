package Model;

import java.util.Date;

public class CommentWithInfo {

    private int cid;
    private int userid;
    private int houseid;
    private String comment;
    private Date date;
    private String fullname;
    private String phone;
    private String email;
    private String houseName;
    private String reply;

    public CommentWithInfo(int cid, int userid, int houseid, String comment, Date date, String fullname, String phone, String email, String houseName, String reply) {
        this.cid = cid;
        this.userid = userid;
        this.houseid = houseid;
        this.comment = comment;
        this.date = date;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.houseName = houseName;
        this.reply = reply;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    
}
