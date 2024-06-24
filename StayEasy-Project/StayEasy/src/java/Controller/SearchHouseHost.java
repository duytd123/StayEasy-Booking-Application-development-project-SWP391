package Controller;

import Dao.HouseDAO;
import Model.Account;
import Model.House;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchHouseHost extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            // You can handle processing logic here if needed
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("search");

        // Retrieve logged-in user (host) from session
        Account loggedInUser = (Account) request.getSession().getAttribute("acc");

        if (loggedInUser != null) {
            int hostId = loggedInUser.getUserid();

            // Call DAO to fetch houses based on search name and host_id
            HouseDAO houseDAO = new HouseDAO();
            List<House> houseList = houseDAO.getHousebyNameAndHost(searchName, hostId);

            // Set the retrieved list as a request attribute
            request.setAttribute("HouseList", houseList);
        }

        // Forward the request to HostIndex.jsp
        request.getRequestDispatcher("HostIndex.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
