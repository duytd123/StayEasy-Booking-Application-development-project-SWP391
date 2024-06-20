package Controller;

import Dao.HouseDAO;
import Model.Account;
import Model.House;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author badao
 */
public class HouseHost extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the logged-in user's account from session
        Account loggedInUser = (Account) request.getSession().getAttribute("acc");

        if (loggedInUser != null) {
            // Assuming you can get host ID from the logged-in user
            int hostId = loggedInUser.getUserid(); // Replace with actual method to get host ID

            // Retrieve houses for the logged-in host
            HouseDAO houseDAO = new HouseDAO();
            List<House> houseList = houseDAO.getHousesByHost(hostId); // Adjust method name if necessary

            // Log houses retrieved
            System.out.println("Number of houses retrieved: " + houseList.size());

            // Set the house list as a request attribute
            request.setAttribute("HouseList", houseList);

            // Forward to ListHouse.jsp to display the houses
            request.getRequestDispatcher("/HostIndex.jsp").forward(request, response);
        } else {
            // If user is not logged in, handle accordingly (redirect to login or show error)
            response.sendRedirect("LoginServlet"); // Adjust login servlet URL
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}

