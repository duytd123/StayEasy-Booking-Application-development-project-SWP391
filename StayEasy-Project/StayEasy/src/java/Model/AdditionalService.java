/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class AdditionalService {
    private int serviceid;
    private String servicename;
    private String servicedesc;

    public AdditionalService() {
    }

    public AdditionalService(int serviceid, String servicename, String servicedesc) {
        this.serviceid = serviceid;
        this.servicename = servicename;
        this.servicedesc = servicedesc;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getServicedesc() {
        return servicedesc;
    }

    public void setServicedesc(String servicedesc) {
        this.servicedesc = servicedesc;
    }
    
    
    
}
