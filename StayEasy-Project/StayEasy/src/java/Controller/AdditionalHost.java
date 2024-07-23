/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.AdditionalServiceDAO;
import Dao.HouseAdditionalServiceDAO;
import Dao.HouseDAO;
import Model.Account;
import Model.AdditionalService;
import Model.House;
import Model.HouseAdditionalService;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author badao
 */
@WebServlet(name = "AdditionalHost", urlPatterns = {"/additional1"})
public class AdditionalHost extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account loggedInUser = (Account) session.getAttribute("acc");

        if (loggedInUser == null) {
            response.sendRedirect("LoginServlet");
            return;
        }
        int hostId = loggedInUser.getUserid();

        HouseDAO houseDAO = new HouseDAO();
        List<House> houses = houseDAO.getHousesByHostId(hostId);
        System.out.println("Houses: " + houses);

        AdditionalServiceDAO asDAO = new AdditionalServiceDAO();
        List<AdditionalService> services = asDAO.getAdditionalServicee();
        System.out.println("Services: " + services);

        int houseId = 0;
        String houseIdParam = request.getParameter("houseId");
        if (houseIdParam != null && !houseIdParam.isEmpty()) {
            try {
                houseId = Integer.parseInt(houseIdParam);
            } catch (NumberFormatException e) {
                houseId = 0;
            }
        }
        if (houseId == 0 && !houses.isEmpty()) {
            houseId = houses.get(0).getHouseid();
        }
        System.out.println("Selected House ID: " + houseId);

        HouseAdditionalServiceDAO hasDAO = new HouseAdditionalServiceDAO();
        List<HouseAdditionalService> houseServices = hasDAO.getHouseAdditionalServiceForHouse(houseId);
        System.out.println("House Services: " + houseServices);

        request.setAttribute("houses", houses);
        request.setAttribute("services", services);
        request.setAttribute("houseId", houseId);
        request.setAttribute("houseServices", houseServices);

        request.getRequestDispatcher("dashboardhost/additional.jsp").forward(request, response);
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
