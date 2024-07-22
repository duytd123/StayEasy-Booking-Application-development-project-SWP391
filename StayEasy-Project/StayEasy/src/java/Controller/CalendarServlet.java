package Controller;

import Model.BillDetail;
import Dao.HouseDAO;
import Model.Account;
import Model.Booking;
import Model.House;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CalendarServlet", urlPatterns = {"/calendar"})
public class CalendarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        Account loggedInUser = (Account) request.getSession().getAttribute("acc");

        if (loggedInUser != null) {
            int hostId = loggedInUser.getUserid();
            HouseDAO houseDAO = new HouseDAO();

            String action = request.getParameter("action");

            if ("blockDate".equals(action)) {
                try {
                    int houseId = Integer.parseInt(request.getParameter("houseId"));
                    String date = request.getParameter("date");

                    houseDAO.blockDate(hostId, houseId, date);

                    response.getWriter().write("{\"success\": \"Date blocked successfully\"}");
                } catch (NumberFormatException e) {
                    response.getWriter().write("{\"error\": \"Invalid house ID or date format\"}");
                }
                return;
            }

            List<Booking> bookings = houseDAO.getBookingDetailll(hostId);
            List<House> blockedHouses = houseDAO.getBlockedHouses(hostId);

            Gson gson = new Gson();
            String bookingsJson = gson.toJson(bookings);
            String blockedHousesJson = gson.toJson(blockedHouses);

            request.setAttribute("bookings", bookingsJson);
            request.setAttribute("blockedHouses", blockedHousesJson);

            request.getRequestDispatcher("dashboardhost/calendar.jsp").forward(request, response);
        } else {
            response.getWriter().write("{\"error\": \"User not logged in\"}");
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
}
