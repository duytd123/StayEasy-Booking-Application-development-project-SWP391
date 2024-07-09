/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author HP
 */
@WebServlet(name = "SearchRoomServlet", urlPatterns = {"/SearchRoomServlet"})
public class SearchRoomServlet extends HttpServlet {

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
            out.println("<title>Servlet SearchRoomServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SearchRoomServlet at " + request.getContextPath() + "</h1>");
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
        String whereTo = request.getParameter("whereTo");
        String guests = request.getParameter("guests");
        String arrivals = request.getParameter("arrivals");
        String leaving = request.getParameter("leaving");
        String[] selectedLocations = request.getParameterValues("location");
        String[] selectedTypes = request.getParameterValues("menu");

        // Build URL parameters
        StringBuilder urlParams = new StringBuilder();
        urlParams.append("whereTo=").append(whereTo);
        urlParams.append("&guests=").append(guests);
        urlParams.append("&arrivals=").append(arrivals);
        urlParams.append("&leaving=").append(leaving);
        if (selectedLocations != null && selectedLocations.length > 0) {
            String locationsParam = Arrays.stream(selectedLocations).collect(Collectors.joining(","));
            urlParams.append("&location=").append(locationsParam);
        }
        if (selectedTypes != null && selectedTypes.length > 0) {
            String typesParam = Arrays.stream(selectedTypes).collect(Collectors.joining(","));
            urlParams.append("&menu=").append(typesParam);
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        System.out.println(urlParams.toString());
        response.getWriter().write(urlParams.toString());
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
