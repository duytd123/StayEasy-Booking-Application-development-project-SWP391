/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.HouseDAO;
import Dao.LocationDAO;
import Dao.MenuDAO;
import Model.House;
import Model.HouseImg;
import Model.Location;
import Model.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NextAddHouseServlet extends HttpServlet {

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
        try {
            String houseIdStr = request.getParameter("id"); // Get the house ID from the request
            if (houseIdStr != null && !houseIdStr.isEmpty()) {
                int houseId = Integer.parseInt(houseIdStr); // Convert the string ID to integer
                HouseDAO houseDAO = new HouseDAO();
                House house = houseDAO.getHousebyId(houseId); // Fetch the house by its ID
                if (house != null) {
                    request.setAttribute("house", house);
                    request.getRequestDispatcher("EditHouse.jsp").forward(request, response);
                } else {
                    response.getWriter().println("House not found");
                }
            } else {
                response.getWriter().println("House ID is missing");
            }
        } catch (NumberFormatException e) {

            response.getWriter().println("Invalid house ID");
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
