/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Host;

import Dao.AccountDAO;
import Dao.HouseDAO;
import Model.Account;
import Model.House;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author badao
 */
@WebServlet(name = "DashboardHostServlet", urlPatterns = {"/DashboardHostServlet"})
public class DashboardHostServlet extends HttpServlet {

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

        // Get the logged-in user's account from session
        Account loggedInUser = (Account) request.getSession().getAttribute("acc");

        if (loggedInUser != null) {
           
            int hostId = loggedInUser.getUserid(); 
         
            HouseDAO houseDAO = new HouseDAO();
        //    List<House> houseList = houseDAO.getHousesByHost(hostId); 

       //     System.out.println("Number of houses retrieved: " + houseList.size());

            // Set the house list as a request attribute
        //    request.setAttribute("HouseList", houseList);

            // Forward to ListHouse.jsp to display the houses
            request.getRequestDispatcher("/HostIndex.jsp").forward(request, response);
        } else {
            // If user is not logged in, handle accordingly (redirect to login or show error)
            response.sendRedirect("LoginServlet"); // Adjust login servlet URL
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
        //        processRequest(request, response);
        processRequest(request, response);
        //get 3 house best 
//        HouseDAO hdao = new HouseDAO();
//        List<House> listHouse = hdao.getNameThreeHouseBest();
//        // Adding new House to the database need to access to the Admin
//        request.authenticate(response);
//        request.setAttribute("listHouse", listHouse);
//
//        request.getRequestDispatcher("HostIndex.jsp").forward(request, response);
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
