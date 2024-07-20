package Controller;

import Model.BillDetail;
import Dao.HouseDAO;
import Model.Account;
import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "CalendarServlet", urlPatterns = {"/calendar"})
public class CalendarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Account loggedInUser = (Account) request.getSession().getAttribute("acc");
            if (loggedInUser != null) {
                int hostId = loggedInUser.getUserid();

                HouseDAO houseDAO = new HouseDAO();
                List<BillDetail> bookings = houseDAO.getBookingDetails(hostId);

                Gson gson = new Gson();
                String json = gson.toJson(bookings);

                response.setCharacterEncoding("UTF-8");
                out.print(json);
                out.flush();
                
            } else {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "User not logged in");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CalendarServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error");
        }
    }
}
