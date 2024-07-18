package Controller.Host;

import Dao.HouseDAO;
import Dao.HouseImgDAO;
import Dao.LocationDAO;
import Dao.MenuDAO;
import Model.Account;
import Model.House;
import Model.Location;
import Model.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

public class AddHouseServlet extends HttpServlet {

    private final LocationDAO locationDAO = new LocationDAO();
    private final MenuDAO menuDAO = new MenuDAO();
    private final HouseDAO houseDAO = new HouseDAO();
    private final HouseImgDAO houseImgDAO = new HouseImgDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Location> locations = locationDAO.getLocation();
        List<Menu> menus = menuDAO.getMenu();

        request.setAttribute("llist", locations);
        request.setAttribute("mlist", menus);

        request.getRequestDispatcher("AddHouse.jsp").forward(request, response);
    }

    @Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    try {
        // Retrieve logged-in user's ID (host ID)
        Account account = (Account) request.getSession().getAttribute("acc");
        if (account == null) {
            throw new IllegalArgumentException("User is not logged in.");
        }
        int hostId = account.getUserid();

        // Automatically set postdate to current date
        Date postDate = new Date();

        // Retrieve parameters
        String houseName = request.getParameter("housename");
        String review = "";  // Assuming review is not provided in the form, set to empty string
        String housePriceStr = request.getParameter("houseprice");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        String locationIdStr = request.getParameter("location");
        String menuIdStr = request.getParameter("menu");

        // Validate required fields
        if (houseName == null || houseName.trim().isEmpty()
                || housePriceStr == null || housePriceStr.trim().isEmpty()
                || address == null || address.trim().isEmpty()
                || locationIdStr == null || locationIdStr.trim().isEmpty()
                || menuIdStr == null || menuIdStr.trim().isEmpty()) {
            throw new IllegalArgumentException("House name, price, address, location, and menu are required.");
        }

        float housePrice;
        int locationId;
        int menuId;
        try {
            housePrice = Float.parseFloat(housePriceStr);
            locationId = Integer.parseInt(locationIdStr);
            menuId = Integer.parseInt(menuIdStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for house price, location, or menu.");
        }

        if (housePrice <= 0) {
            throw new IllegalArgumentException("House price must be a positive number.");
        }

        // Retrieve Location and Menu objects using DAO classes
        Location location = locationDAO.getLocationById(locationId);
        if (location == null) {
            throw new IllegalArgumentException("Invalid location selected.");
        }

        Menu menu = menuDAO.getMenuById(menuId);
        if (menu == null) {
            throw new IllegalArgumentException("Invalid menu selected.");
        }

        // Create House object without hostId
        House house = new House(-1, postDate, houseName, review, housePrice, 0, address, description, location, menu);

        // Pass House object and hostId to HouseDAO method
        houseDAO.addHouse(house, hostId);

        // Redirect to dashboardhosthosthost or another page
        response.sendRedirect("DashboardHostServlet");
    } catch (IllegalArgumentException e) {
        response.getWriter().print("Error: " + e.getMessage());
    } catch (Exception e) {
        response.getWriter().print("Error: Failed to add house. Please try again later.");
        e.printStackTrace();
    }
}


    @Override
    public String getServletInfo() {
        return "AddHouseServlet";
    }
}
