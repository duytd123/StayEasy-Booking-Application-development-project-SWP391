/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.BillDAO;
import Dao.HouseDAO;
import Model.Account;
import Model.House;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "DashBoardServlet", urlPatterns = {"/host"})
public class DashBoardServletHost extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("mess");
        Account loggedInUser = (Account) session.getAttribute("acc");

        int hostId = loggedInUser.getUserid();
        HouseDAO houseDAO = new HouseDAO();
        BillDAO billDAO = new BillDAO();
        int houses = houseDAO.countHousesByHost(hostId);
        int status = houseDAO.countHousesWithPendingBookings(hostId);
        double bills = billDAO.calculateTotalMoneyForHost(hostId);
        int bilsa = billDAO.countPendingBillsForHost(hostId);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        int startOfWeek = calendar.get(Calendar.DAY_OF_MONTH) - dayOfWeek + 1;
        int endOfWeek = startOfWeek + 6;

        double totalMoneyWeek = billDAO.totalMoneyWeek(dayOfWeek, startOfWeek, endOfWeek, year, month, hostId);
        double totalMoneyMonth = billDAO.totalMoneyMonth(month, year, hostId);

        request.setAttribute("houses", houses);
        request.setAttribute("status", status);
        request.setAttribute("bills", bills);
        request.setAttribute("bilsa", bilsa);
        request.setAttribute("totalMoneyWeek", totalMoneyWeek);
        request.setAttribute("totalMoneyMonth", totalMoneyMonth);

        request.getRequestDispatcher("dashboardhost/dashboard.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
