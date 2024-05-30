/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.AccountDAO;
import Model.Account;
import Model.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author kiety
 */
@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

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
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String userimg = "";
        int status = 1;
        int roleid = 2;
        //pass != repass
        if (username == null || username.equals("")) {
            request.setAttribute("mess", "Input user name!");
            request.setAttribute("username", username);
            request.setAttribute("fullname", fullname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }if(password == null | password.equals("")){
            request.setAttribute("mess", "Input passwword end repeat password!");
            request.setAttribute("username", username);
            request.setAttribute("fullname", fullname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        if(email == null | email.equals("")){
            request.setAttribute("mess", "Input email!");
            request.setAttribute("username", username);
            request.setAttribute("fullname", fullname);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        else {
            //condition
            if (!password.equals(repassword)) {
                request.setAttribute("username", username);
                request.setAttribute("fullname", fullname);
                request.setAttribute("email", email);
                request.setAttribute("phone", phone);
                request.setAttribute("mess", "Repeat password is wrong!");
                request.getRequestDispatcher("signup.jsp").forward(request, response);
            } else {
                Dao.AccountDAO adao = new AccountDAO();
                Model.Account a = adao.checkAccountExist(username);
                if (a == null) {
                    Account aemail = adao.checkAccountByEmail(email);
                    if (aemail != a) {
                        request.setAttribute("username", username);
                        request.setAttribute("fullname", fullname);
                        request.setAttribute("email", email);
                        request.setAttribute("phone", phone);
                        request.setAttribute("mess", "Email already exist");
                        request.getRequestDispatcher("signup.jsp").forward(request, response);
                    } else {
                        //signup
                        Role role = new Role(roleid, "customer");
                        Account acc = new Account(-1, fullname, userimg, username, password, email, phone, status, role);
                        adao.signupAccount(acc);
                        response.sendRedirect("Index.jsp");
                    }

                } else {
                    request.setAttribute("username", username);
                    request.setAttribute("fullname", fullname);
                    request.setAttribute("email", email);
                    request.setAttribute("phone", phone);
                    request.setAttribute("mess", "Username has already existed!");
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                }
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
