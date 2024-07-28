/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.util.Date;

/**
 *
 * @author Admin
 */
public class House {

    private int houseid;
    private Date postdate;
    private String housename;
    private String review;
    private float houseprice;
    private int status;
    private String address;
    private String description;
    private Location location;
    private Menu menu;
    private int numberBill;
    private boolean rentHouse;
    private double discountBook;
   

    public House() {
    }

    public House(int houseid, Date postdate, String housename, String review, float houseprice, int status, String address, String description, Location location, Menu menu) {
        this.houseid = houseid;
        this.postdate = postdate;
        this.housename = housename;
        this.review = review;
        this.houseprice = houseprice;
        this.status = status;
        this.address = address;
        this.description = description;
        this.location = location;
        this.menu = menu;
        
    }

    public int getNumberBill() {
        return numberBill;
    }

    public void setNumberBill(int numberBill) {
        this.numberBill = numberBill;
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

    public boolean isRentHouse() {
        return rentHouse;
    }

    public void setRentHouse(boolean rentHouse) {
        this.rentHouse = rentHouse;
    }

    public double getDiscountBook() {
        return discountBook;
    }

    public void setDiscountBook(double discount) {
        this.discountBook = discount;
    }
}
