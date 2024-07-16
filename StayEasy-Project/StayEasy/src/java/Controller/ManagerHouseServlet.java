/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AdditionalServiceDAO;
import Dao.HouseDAO;
import Dao.LocationDAO;
import Dao.MenuDAO;
import Model.Account;
import Model.HouseHost;
import Model.Menu;
import Model.AdditionalService;
import Model.Location;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ManagerControl", urlPatterns = {"/manager"})
public class ManagerHouseServlet extends HttpServlet {

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
            int hostId = loggedInUser.getUserid();
            HouseDAO daoH = new HouseDAO();
            LocationDAO daoL = new LocationDAO();
            MenuDAO daoM = new MenuDAO();
            AdditionalServiceDAO daoA = new AdditionalServiceDAO();
            
            List<HouseHost> list = daoH.getHousesByHost(hostId);
            List<Location> listL = daoL.getLocation();
            List<Menu> listM = daoM.getMenu();
            List<AdditionalService> listA = daoA.getAdditionalService();
            
            int page = 1, numPerPage = 5;
            int size = list.size();
            int numberpage = ((size % numPerPage == 0) ? (size / 5) : (size / 5) + 1);
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start, end;
            start = (page - 1) * 5;
            end = Math.min(page * numPerPage, size);

            List<HouseHost> listByPage = daoH.getListByPage(list, start, end);
         
            request.setAttribute("page", page);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("numberpage", numberpage);
            request.setAttribute("listC", listL);
            request.setAttribute("listB", listM);
            request.setAttribute("listByPage", listByPage);
            request.setAttribute("list", listA);

            request.getRequestDispatcher("dashboardhost/mnhouse.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        Account loggedInUser = (Account) request.getSession().getAttribute("acc");

        if (loggedInUser != null) {
            int hostId = loggedInUser.getUserid();
            String txtSearch = request.getParameter("valueSearch");

            HouseDAO daoH = new HouseDAO();
            List<HouseHost> houseList = daoH.getHouseWithInfoByHouseNameAndHost(txtSearch, hostId);

            int page = 1, numPerPage = 5;
            int size = houseList.size();
            int numberpage = ((size % numPerPage == 0) ? (size / numPerPage) : (size / numPerPage) + 1);
            String xpage = request.getParameter("page");
            if (xpage != null) {
                try {
                    page = Integer.parseInt(xpage);
                } catch (NumberFormatException e) {                 
                    page = 1;
                }
            }
            int start = (page - 1) * numPerPage;
            int end = Math.min(page * numPerPage, size);

            List<HouseHost> listByPage = houseList.subList(start, end);

            request.setAttribute("page", page);
            request.setAttribute("start", start);
            request.setAttribute("end", end);
            request.setAttribute("numberpage", numberpage);
            request.setAttribute("listByPage", listByPage);
            request.setAttribute("searchValue", txtSearch);

            request.getRequestDispatcher("dashboardhost/mnhouse.jsp").forward(request, response);
        } else {
            response.sendRedirect("LoginServlet");
        }
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
