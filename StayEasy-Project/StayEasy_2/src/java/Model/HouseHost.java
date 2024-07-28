package Model;

import java.util.Date;
import java.util.List;

public class HouseHost extends House {

    private int houseid;
    private Date postdate;
    private String housename;
    private String review;
    private float houseprice;
    private int status;
    private String address;
    private String description;
    private double discount;
    private int number_of_guest; 
    private Location location;
    private Menu menu;
    private List<String> images;

    public HouseHost() {
    }

    public HouseHost(int houseid, Date postdate, String housename, String review, float houseprice, int status, String address, String description, double discount, int number_of_guest, Location location, Menu menu, List<String> images) {
        this.houseid = houseid;
        this.postdate = postdate;
        this.housename = housename;
        this.review = review;
        this.houseprice = houseprice;
        this.status = status;
        this.address = address;
        this.description = description;
        this.discount = discount;
        this.number_of_guest = number_of_guest; 
        this.location = location;
        this.menu = menu;
        this.images = images;
    }

    public int getHouseid() {
        return houseid;
    }

    public void setHouseid(int houseid) {
        this.houseid = houseid;
    }

    public Date getPostdate() {
        return postdate;
    }

    public void setPostdate(Date postdate) {
        this.postdate = postdate;
    }

    public String getHousename() {
        return housename;
    }

    public void setHousename(String housename) {
        this.housename = housename;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public float getHouseprice() {
        return houseprice;
    }

    public void setHouseprice(float houseprice) {
        this.houseprice = houseprice;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getNumber_of_guest() {
        return number_of_guest;
    }

    public void setNumber_of_guest(int number_of_guest) {
        this.number_of_guest = number_of_guest;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
