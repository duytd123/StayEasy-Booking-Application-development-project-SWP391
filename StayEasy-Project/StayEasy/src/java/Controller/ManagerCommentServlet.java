/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.CommentDAO;
import Model.Account;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import Model.CommentWithInfo;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ManagerCommentServlet", urlPatterns = {"/commentmanager"})
public class ManagerCommentServlet extends HttpServlet {

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
            Account loggedInUser = (Account) request.getSession().getAttribute("acc");
            if (loggedInUser != null) {
                int hostId = loggedInUser.getUserid();
                CommentDAO dao = new CommentDAO();
                List<CommentWithInfo> list = dao.getCommentsByHouseId(hostId);
                request.setAttribute("commentList", list);
                request.getRequestDispatcher("dashboardhost/mngcomment.jsp").forward(request, response);
            } else {
                response.sendRedirect("LoginServlet"); 
            }
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
            Account loggedInUser = (Account) request.getSession().getAttribute("acc");
            if (loggedInUser != null) {
                int hostId = loggedInUser.getUserid();
                String txtSearch = request.getParameter("valueSearch");
                
                CommentDAO dao = new CommentDAO();

                List<CommentWithInfo> commentList = dao.getCommentsWithInfoByHouseNameAndHost(txtSearch, hostId);

                request.setAttribute("commentList", commentList);
                request.getRequestDispatcher("dashboardhost/mngcomment.jsp").forward(request, response);

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
