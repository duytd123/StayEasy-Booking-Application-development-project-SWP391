/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.AccountDAO;
import Model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cusername")) {
                    request.setAttribute("usernameCookie", cookie.getValue());
                } else if (cookie.getName().equals("cpassword")) {
                    request.setAttribute("passwordCookie", cookie.getValue());
                } else if (cookie.getName().equals("rememberme")) {
                    request.setAttribute("rememberMeCookie", cookie.getValue());
                }
            }
        }

        // Forward the request to login.jsp
        request.getRequestDispatcher("/login.jsp").forward(request, response);
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
        // Set the request character encoding to UTF-8
        request.setCharacterEncoding("UTF-8");

        // Retrieve form parameters
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = request.getParameter("rememberme") != null;

        // Use UserDAO to check user credentials and WalletDAO to retrieve wallet information
        AccountDAO dao = new AccountDAO();
        Account a = dao.getAccountLogin(username, password);

        if (a != null) {
            // If credentials are valid
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            session.setAttribute("username", a.getUsername());
            session.setAttribute("email", a.getEmail());
            session.setAttribute("rememberme", rememberMe);

            // Manage cookies based on the "remember me" flag
            if (rememberMe) {
                Cookie cUsername = new Cookie("cusername", a.getUsername());
                Cookie cPassword = new Cookie("cpassword", a.getPass());
                cUsername.setMaxAge(60 * 60 * 24); // 24 hours
                cPassword.setMaxAge(60 * 60 * 24); // 24 hours
                response.addCookie(cUsername);
                response.addCookie(cPassword);
            } else {
                // Clear cookies if "remember me" is not checked
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("cusername") || cookie.getName().equals("cpassword")) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
            }
            
            // Redirect to the home page on successful login
            if (a.getRole().getId() == 0) {
                // Admin role
                response.sendRedirect("DashboardServlet");
            } else if (a.getRole().getId() == 1 && a.getStatus() == 1) {
                //Host role
                response.sendRedirect("DashboardHostServlet"); // Modify as needed
            } else if (a.getRole().getId() == 2 && a.getStatus() == 1) {
                // Customer role
                response.sendRedirect("home");
            } else {
                response.sendRedirect("Error.jsp");
            }
        } else {
            request.setAttribute("mess", "Wrong username or password!!!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
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
