/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.AccountDAO;
import Dao.BillDAO;
import Dao.BillDetailDAO;
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

@MultipartConfig
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

        if (acc == null) {
            request.setAttribute("mess", "User session not found.");
            request.getRequestDispatcher("user.jsp").forward(request, response);
            return;
        }

        int userid = acc.getUserid();
        String username = acc.getUsername();
        String pass = acc.getPass();
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        String errorMessage = null;

        // Validation checks
        if (phone == null || !phone.matches("\\d{10}")) {
            errorMessage = "Phone number must be exactly 10 digits.";
        }

        if (fullname == null || !fullname.matches("[A-Za-z0-9\\s]+")) {
            errorMessage = "Full name must contain only letters and spaces.";
        }

        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$")) {
            errorMessage = "Invalid email address format.";
        }

        if (errorMessage != null) {
            request.setAttribute("mess", errorMessage);
            request.getRequestDispatcher("user.jsp").forward(request, response);
            return;
        }

        Account a = new Account(userid, fullname, acc.getUserimg(), username, pass, email, phone, 1, acc.getRole());
        AccountDAO dao = new AccountDAO();

        try {
            dao.editAccount(a);
            session.setAttribute("acc", a);
            request.setAttribute("mess", "Profile updated successfully!");
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            request.setAttribute("mess", "An error occurred while updating the profile.");
        }

        request.getRequestDispatcher("user.jsp").forward(request, response);
    }

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

        request.getRequestDispatcher("user.jsp").forward(request, response);
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
            default:
                throw new AssertionError();
        }

    }

    protected void uploadPic(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("acc");
            String uploadFolder = "C:/Users/badao/Desktop/StayEasy-Booking--Application-development-project-SWP391/StayEasy-Project/StayEasy/web/Images/userimgs";
            Path uploadPath = Paths.get(uploadFolder);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Part imgPart = request.getPart("userimage");
            String imageFileName = Paths.get(imgPart.getSubmittedFileName()).getFileName().toString();
            Path filePath = uploadPath.resolve(imageFileName);

            imgPart.write(filePath.toString());

            AccountDAO dao = new AccountDAO();
            String relativeImagePath = "Images/userimgs/" + imageFileName;
            dao.updatePic(relativeImagePath, acc.getUserid());
            acc.setUserimg(relativeImagePath); 
            session.setAttribute("acc", acc);

            request.setAttribute("mess", "Upload image success!");
            request.getRequestDispatcher("user.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mess", "Upload image failed: " + e.getMessage());
            request.getRequestDispatcher("user.jsp").forward(request, response);
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
