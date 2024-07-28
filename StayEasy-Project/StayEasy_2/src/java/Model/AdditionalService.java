package Model;

/**
 *
 * @author Admin
 */
public class AdditionalService {

    private int serviceid;
    private String servicename;
    private String servicedesc;
    private String imageUrl;

    public AdditionalService() {
    }

    public AdditionalService(int serviceid, String servicename, String servicedesc, String imageUrl) {
        this.serviceid = serviceid;
        this.servicename = servicename;
        this.servicedesc = servicedesc;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
