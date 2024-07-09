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
import Model.Location;
import Model.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;

/**
 *
 * @author Admin
 */
public class ListHouseServlet extends HttpServlet {
 
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HouseDAO dao = new HouseDAO();
            LocationDAO locationDAO = new LocationDAO();
            MenuDAO menuDAO = new MenuDAO();
            List<House> list = dao.getHouse();
            List<Location> locationList = locationDAO.getLocation();
            List<Menu> menuList = menuDAO.getMenu();
            request.setAttribute("Location",locationList);
            request.setAttribute("HouseList", list);
            request.setAttribute("MenuList", menuList);
            request.getRequestDispatcher("ListHouse.jsp").forward(request, response);  
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
        HouseDAO dao = new HouseDAO();
            LocationDAO locationDAO = new LocationDAO();
            MenuDAO menuDAO = new MenuDAO();
            List<House> list = dao.getHouse();
            request.setAttribute("HouseList", list);
            List<Location> locationList = locationDAO.getLocation();
            List<Menu> menuList = menuDAO.getMenu();
            request.setAttribute("LocationList",locationList);
            request.setAttribute("MenuList", menuList);
            request.getRequestDispatcher("ListHouse.jsp").forward(request, response); 
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
