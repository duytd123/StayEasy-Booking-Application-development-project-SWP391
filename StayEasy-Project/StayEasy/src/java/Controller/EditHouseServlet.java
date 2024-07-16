/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.HouseDAO;
import Model.House;
import Model.Location;
import Model.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class EditHouseServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditHouseServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditHouseServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        request.getRequestDispatcher("UpdateHouse.jsp").forward(request, response);
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
        //processRequest(request, response);
        int houseid = Integer.parseInt(request.getParameter("houseid"));
        String dateString =request.getParameter("postdate");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatDate.parse(dateString);
        } catch (Exception e) {
            response.getWriter().print("error : "+e);
            return;
        }
        String housename = request.getParameter("housename");
        String review = request.getParameter("review");
        float price = Float.parseFloat(request.getParameter("houseprice"));
        int status = Integer.parseInt(request.getParameter("status"));
        String address = request.getParameter("address");
        String description = request.getParameter("description");
        int locationid = Integer.parseInt(request.getParameter("location"));
        int menuid = Integer.parseInt(request.getParameter("menu"));
        
        Location location = new Location(locationid, null);
        Menu menu = new Menu(menuid, null);
        
        House h = new House(houseid, date, housename, review, price, status, address, description, location, menu);
        HouseDAO dao = new HouseDAO();
        dao.editHouse(h);
        response.sendRedirect("DashboardHostServlet");
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
