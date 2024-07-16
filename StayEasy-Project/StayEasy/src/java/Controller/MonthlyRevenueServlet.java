/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.BillDAO;
import Model.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MonthlyRevenueServlet", urlPatterns = {"/mothlyrevenue"})
public class MonthlyRevenueServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        Account loggedInUser = (Account) request.getSession().getAttribute("acc");

        if (loggedInUser != null) {
            int hostId = loggedInUser.getUserid();
            String year_raw = request.getParameter("year");
            int year = (year_raw == null ? 2024 : Integer.parseInt(year_raw));

            BillDAO dao = new BillDAO();
            double totalMoneyMonth1 = dao.totalMoneyMonth(1, year, hostId);
            double totalMoneyMonth2 = dao.totalMoneyMonth(2, year, hostId);
            double totalMoneyMonth3 = dao.totalMoneyMonth(3, year, hostId);
            double totalMoneyMonth4 = dao.totalMoneyMonth(4, year, hostId);
            double totalMoneyMonth5 = dao.totalMoneyMonth(5, year, hostId);
            double totalMoneyMonth6 = dao.totalMoneyMonth(6, year, hostId);
            double totalMoneyMonth7 = dao.totalMoneyMonth(7, year, hostId);
            double totalMoneyMonth8 = dao.totalMoneyMonth(8, year, hostId);
            double totalMoneyMonth9 = dao.totalMoneyMonth(9, year, hostId);
            double totalMoneyMonth10 = dao.totalMoneyMonth(10, year, hostId);
            double totalMoneyMonth11 = dao.totalMoneyMonth(11, year, hostId);
            double totalMoneyMonth12 = dao.totalMoneyMonth(12, year, hostId);

            request.setAttribute("totalMoneyMonth1", totalMoneyMonth1);
            request.setAttribute("totalMoneyMonth2", totalMoneyMonth2);
            request.setAttribute("totalMoneyMonth3", totalMoneyMonth3);
            request.setAttribute("totalMoneyMonth4", totalMoneyMonth4);
            request.setAttribute("totalMoneyMonth5", totalMoneyMonth5);
            request.setAttribute("totalMoneyMonth6", totalMoneyMonth6);
            request.setAttribute("totalMoneyMonth7", totalMoneyMonth7);
            request.setAttribute("totalMoneyMonth8", totalMoneyMonth8);
            request.setAttribute("totalMoneyMonth9", totalMoneyMonth9);
            request.setAttribute("totalMoneyMonth10", totalMoneyMonth10);
            request.setAttribute("totalMoneyMonth11", totalMoneyMonth11);
            request.setAttribute("totalMoneyMonth12", totalMoneyMonth12);
            request.setAttribute("year", year);

            request.getRequestDispatcher("dashboardhost/monthlyrevenue.jsp").forward(request, response);
        } else {
            // If user is not logged in, handle accordingly (redirect to login or show error)
            response.sendRedirect("mothlyrevenue"); // Adjust login servlet URL
        }
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
        processRequest(request, response);
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
