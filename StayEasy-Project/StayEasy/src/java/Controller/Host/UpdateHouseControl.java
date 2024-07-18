/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Host;

import Dao.HouseDAO;
import Dao.LocationDAO;
import Dao.MenuDAO;
import Model.Account;
import Model.Location;
import Model.Menu;
import Model.HouseHost;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateHouseControl", urlPatterns = {"/updatehouse"})
public class UpdateHouseControl extends HttpServlet {

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

        Account loggedInUser = (Account) request.getSession().getAttribute("acc");
        if (loggedInUser != null) {
            try {
                int hostId = loggedInUser.getUserid();
                String id_raw = request.getParameter("hid");
                int id = Integer.parseInt(id_raw);
                HouseDAO daoH = new HouseDAO();
                HouseHost h = daoH.getHouseByHouseIDandHost(id, hostId);
                
                LocationDAO daoL = new LocationDAO();
                MenuDAO daoM = new MenuDAO();
                List<Location> listL = daoL.getLocation();
                List<Menu> listM = daoM.getMenu();

                request.setAttribute("detail", h);
                request.setAttribute("listLoca", listL);
                request.setAttribute("listMenu", listM);
                request.getRequestDispatcher("dashboardhost/updatehouse.jsp").forward(request, response);

            } catch (NumberFormatException e) {
               
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            response.sendRedirect("LoginServlet");
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
