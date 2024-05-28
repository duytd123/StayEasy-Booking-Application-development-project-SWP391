/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.AccountDAO;
import Model.Account;
import Model.Bill;
import Model.BillDetail;
import Model.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author kiety
 */
@MultipartConfig//đc gửi nhiều dữ liệu
@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

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
        doPost(request, response);
//        String action = request.getParameter("sub");
//        switch (action) {
//            
//            default:
//                throw new AssertionError();
//        }
    }

    protected void saveChanges(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        int userid = acc.getUserid();
        String username = acc.getUsername();
        String pass = acc.getPass();
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        Role role = acc.getRole();
        Account a = new Account(userid, fullname, acc.getUserimg(), username, pass, email, phone, 1, role);
        AccountDAO dao = new AccountDAO();
        dao.editAccount(a);
        session.setAttribute("acc", a);
        request.setAttribute("mess", "save profile success!!");
        request.getRequestDispatcher("/user.jsp").forward(request, response);

    }

    protected void resetPass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String curPass = request.getParameter("curPass");
        String newPass = request.getParameter("newPass");
        String rePass = request.getParameter("rePass");

        Account acc = (Account) session.getAttribute("acc");
        if (curPass == null || curPass.equals("")) {
            request.setAttribute("mess", "Enter current pass");
            request.getRequestDispatcher("/security.jsp").forward(request, response);
        } else {
            if (curPass.equals(acc.getPass())) {
                if (newPass == null || newPass.equals("") || rePass == null || rePass.equals("")) {
                    request.setAttribute("mess", "Enter new pass and confirm pass");
                    request.getRequestDispatcher("/security.jsp").forward(request, response);
                } else {
                    if (newPass.equals(rePass)) {
                        AccountDAO dao = new AccountDAO();
                        dao.updateAccountPass(newPass, acc.getUserid());
                        acc.setPass(rePass);
                        request.setAttribute("mess", "password has been changed");
                        request.getRequestDispatcher("/security.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mess", "confirm pass is wrong");
                        request.getRequestDispatcher("/security.jsp").forward(request, response);
                    }
                }
            } else {
                request.setAttribute("mess", "current pass is wrong");
                request.getRequestDispatcher("/security.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
//        processRequest(request, response);
        String action = request.getParameter("sub");
        switch (action) {
            case "uploadPic":
                uploadPic(request, response);
                break;
            case "save":
                saveChanges(request, response);
                break;
            case "resetPass":
                resetPass(request, response);
                break;
            default:
                throw new AssertionError();
        }

    }

    protected void uploadPic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");
            String uploadFolder = "C:/Users/Admin/OneDrive/Desktop/HouseBookingSystem2_SWP391/HouseBookingSystem_SWP391/web/Images/userimgs";
            Path uploadPath = Paths.get(uploadFolder);
            if (!Files.exists(uploadPath)) {
                Files.createDirectory(uploadPath);
            }
            Part imgPart = request.getPart("userimage");
            String imageFileName = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
            imgPart.write(Paths.get(uploadPath.toString(), imageFileName).toString());
            AccountDAO dao = new AccountDAO();
            dao.updatePic(imageFileName, acc.getUserid());
            request.setAttribute("mess", "upload image success!!");
            acc.setUserimg(imageFileName);
            session.setAttribute("acc", acc);
            request.getRequestDispatcher("/user.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
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
