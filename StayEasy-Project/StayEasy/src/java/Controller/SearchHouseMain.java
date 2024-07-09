/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.HouseDAO;
import Dao.HouseImgDAO;
import Dao.LocationDAO;
import Dao.MenuDAO;
import Model.House;
import Model.Location;
import Model.Menu;
import configs.Validate;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Mr D
 */
public class SearchHouseMain extends HttpServlet {

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
            out.println("<title>Servlet SearchHouseMain</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchHouseMain at " + request.getContextPath() + "</h1>");
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
        String location = request.getParameter("location");
        String menu = request.getParameter("menu");
        int locationId = -1;
        int menuId = -1;
        try {
            locationId = Integer.parseInt(location);
            request.setAttribute("currentLocation", locationId);
        } catch (Exception e) {
            System.out.println("Location error: " + e);
        }
        try {
            menuId = Integer.parseInt(menu);
            request.setAttribute("currentMenu", menuId);
        } catch (Exception e) {
            System.out.println("Location error: " + e);
        }

        LocationDAO locationDAO = new LocationDAO();
        MenuDAO menuDao = new MenuDAO();
        List<Menu> menus = menuDao.getMenu();
        List<Location> locations = locationDAO.getLocation();

        String whereTo = request.getParameter("whereTo");
        String arrivals = request.getParameter("arrivals");
        String guests = request.getParameter("guests");
        String leaving = request.getParameter("leaving");

        if (!Validate.checkDate(arrivals) || !Validate.checkDate(leaving)) {
            response.sendRedirect("error-page.jsp");
            return;
        }
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(arrivals, dateFormatter);
        LocalDate end = LocalDate.parse(leaving, dateFormatter);
        Date sqlStartDate = Date.valueOf(start);
        Date sqlEndDate = Date.valueOf(end);
        HouseDAO dao = new HouseDAO();
        List<House> listHouse = dao.searchfindHouse(whereTo, sqlStartDate, guests, sqlEndDate, locationId, menuId);
        HouseImgDAO dao2 = new HouseImgDAO();

        request.setAttribute("list", listHouse);
        request.setAttribute("menus", menus);
        request.setAttribute("locations", locations);
        request.getRequestDispatcher("Listhousemain.jsp").forward(request, response);
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

    public static void main(String[] args) {
//        HouseDAO dao = new HouseDAO();
//        List<House> listHouse = dao.searchHouse("o", "2024-05-30", "2", "2024-05-31");
//        for (House h : listHouse) {
//            System.out.println(h.getHouseid());
//        }
    }

}
