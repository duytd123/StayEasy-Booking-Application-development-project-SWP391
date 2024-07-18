/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.User;

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
        request.getRequestDispatcher("user.jsp").forward(request, response);

    }

    protected void rejectBill(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int billid = Integer.parseInt(request.getParameter("billid"));
        int userid = Integer.parseInt(request.getParameter("userid"));
        BillDAO dao = new BillDAO();
        dao.deleteBill(billid);
        List<Bill> list = dao.getBillbyUserId(userid);
        session.setAttribute("list", list);
        request.setAttribute("mess", "reject success");
        request.getRequestDispatcher("bill.jsp").forward(request, response);
    }

    protected void viewBill(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            int billid = Integer.parseInt(request.getParameter("billid"));
            HttpSession session = request.getSession();
            BillDetail b = new BillDetail();
            BillDetailDAO dao = new BillDetailDAO();
            b = dao.getBillDeatailbyBillID(billid);
            if (b != null) {
                session.setAttribute("bill", b);
                request.getRequestDispatcher("billDetailUser.jsp").forward(request, response);
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
            case "reject":
                rejectBill(request, response);
                break;
            case "view":
                viewBill(request, response);
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
            String uploadFolder = "C:/Users/Admin/OneDrive/Desktop/HouseBookingSystem_SWP391/HouseBookingSystem_SWP391/web/Images/userimgs";
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
            request.getRequestDispatcher("user.jsp").forward(request, response);
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
