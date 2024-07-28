/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class HouseAdditionalService {
    private int houseaddserviceid;
    private int serviceid;
    private int houseid;
    private int servicestatus;
    private float serviceprice;
    private AdditionalService addService;

    public HouseAdditionalService() {
    }

    public HouseAdditionalService(int houseaddserviceid, int serviceid, int houseid, int servicestatus, float serviceprice) {
        this.houseaddserviceid = houseaddserviceid;
        this.serviceid = serviceid;
        this.houseid = houseid;
        this.servicestatus = servicestatus;
        this.serviceprice = serviceprice;
    }

    public int getHouseaddserviceid() {
        return houseaddserviceid;
    }

    public void setHouseaddserviceid(int houseaddserviceid) {
        this.houseaddserviceid = houseaddserviceid;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }

    public int getServicestatus() {
        return servicestatus;
    }

    public void setServicestatus(int servicestatus) {
        this.servicestatus = servicestatus;
    }

    public float getServiceprice() {
        return serviceprice;
    }

    public void setServiceprice(float serviceprice) {
        this.serviceprice = serviceprice;
    }

    public AdditionalService getAddService() {
        return addService;
    }

    public void setAddService(AdditionalService addService) {
        this.addService = addService;
    }
}
