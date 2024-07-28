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
public class BillDetail {

    private int billdetailid;
    private int billid;
    private int houseid;
    private Date startdate;
    private Date enddate;
    private String note;

    public BillDetail() {
    }

    public BillDetail(int billdetailid, int billid, int houseid, Date startdate, Date enddate, String note) {
        this.billdetailid = billdetailid;
        this.billid = billid;
        this.houseid = houseid;
        this.startdate = startdate;
        this.enddate = enddate;
        this.note = note;
    }

    public int getBilldetailid() {
        return billdetailid;
    }

    public void setBilldetailid(int billdetailid) {
        this.billdetailid = billdetailid;
    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
