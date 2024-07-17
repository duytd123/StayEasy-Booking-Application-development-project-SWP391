/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.BillDAO;
import Dao.BillDetailDAO;
import Dao.FeedbackDAO;
import Dao.HouseDAO;
import Dao.HouseImgDAO;
import Model.Account;
import Model.Bill;
import Model.BillDetail;
import Model.Feedback;
import Model.House;
import Model.HouseImg;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "HistoryOrderController", urlPatterns = {"/history-booking"})
public class HistoryOrderController extends HttpServlet {

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
            out.println("<title>Servlet HistoryOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HistoryOrderController at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "view":
                this.viewDetail(request, response);
                break;
            default:
                this.history(request, response);
        }

    }

    private void viewDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Account accountLogin = (Account) session.getAttribute("acc");
            if (accountLogin == null) {
                response.sendRedirect("LoginServlet?error=Please login before booking");
                return;
            }
            int billId = Integer.parseInt(request.getParameter("bill_id"));
            BillDAO billDao = new BillDAO();
            Bill bill = billDao.getBillByIdUser(billId, accountLogin.getUserid());
            if (bill != null) {
                BillDetailDAO billDetailDao = new BillDetailDAO();
                BillDetail billDetail = billDetailDao.getBillDeatailbyBillID(billId);
                HouseDAO houseDao = new HouseDAO();
                FeedbackDAO feedbackDao = new FeedbackDAO();
                House house = houseDao.getHousebyId(billDetail.getHouseid());
                HouseImgDAO houImageDAO = new HouseImgDAO();
                List<HouseImg> listImage = houImageDAO.getHouseImgbyID(billDetail.getHouseid());
                Feedback feedback = feedbackDao.getFeedbackByBillId(billId);
                request.setAttribute("feedback", feedback);
                request.setAttribute("billDetail", billDetail);
                request.setAttribute("account", accountLogin);
                request.setAttribute("house", house);
                request.setAttribute("listImage", listImage);
                request.setAttribute("bill", bill);
                request.setAttribute("billNote", billDetail.getNote());
                request.getRequestDispatcher("detailBooking.jsp").forward(request, response);
            } else {
                response.sendRedirect("history-booking?error=Can not found this booking");
            }
        } catch (Exception e) {
            System.err.println("Detail: " + e);
            response.sendRedirect("history-booking?error=Have a error with booking");
        }
    }

    private void history(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            HttpSession session = request.getSession();
            Account accountLogin = (Account) session.getAttribute("acc");
            if (accountLogin == null) {
                response.sendRedirect("LoginServlet?error=Please login before booking");
                return;
            }
            BillDAO billDao = new BillDAO();
            List<Bill> bills = billDao.getHistoryBill(accountLogin.getUserid());
            request.setAttribute("bills", bills);
            request.getRequestDispatcher("historyBooking.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("Have a error: " + e);
        }
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
        String action = request.getParameter("action");
        action = action != null ? action : "";
        switch (action) {
            case "feedback":
                this.addFeedback(request, response);
                break;
            case "edit-feedback":
                this.editFeedback(request, response);
                break;
            case "cancel":
                this.cancelBooking(request, response);
                break;
            default:
                response.sendRedirect(request.getContextPath() + "/history-booking");
        }
    }

    private void cancelBooking(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String billString = request.getParameter("bill_id");
        HttpSession session = request.getSession();
        Account accountLogin = (Account) session.getAttribute("acc");
        if (accountLogin == null) {
            response.sendRedirect("LoginServlet?error=Please login before booking");
            return;
        }
        try {
            String reason = request.getParameter("cancelReason");
            int billId = Integer.parseInt(billString);
            BillDAO billDao = new BillDAO();
            Bill bill = billDao.getBillByIdUser(billId, accountLogin.getUserid());
            if (bill != null) {
                if (bill.getStatus() != 1) {
                    int result = billDao.cancelBill(billId, reason);
                    if (result > 0) {
                        response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&success=Cancel booking successfully");
                    } else {
                        response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&error=Cancel booking fail. Try again");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&error=This booking can not cancel");
                }
            } else {

            }
        } catch (Exception e) {
            System.out.println("Have a error: " + e);
            response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&error=Have a error with data");
        }
    }

    private void addFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Account accountLogin = (Account) session.getAttribute("acc");
        if (accountLogin == null) {
            response.sendRedirect("LoginServlet?error=Please login before booking");
            return;
        }
        String billString = request.getParameter("bill_id");
        try {
            int billId = Integer.parseInt(billString);
            String content = request.getParameter("content");
            int start = Integer.parseInt(request.getParameter("star"));
            Date date = new Date();
            FeedbackDAO feedbackDao = new FeedbackDAO();
            Feedback newFeedback = new Feedback();
            newFeedback.setContent(content);
            newFeedback.setStar(start);
            newFeedback.setBill_id(billId);
            newFeedback.setDate(date);
            newFeedback.setUser_id(accountLogin.getUserid());
            int result = feedbackDao.addFeedback(newFeedback);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&success=Feedback successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&error=Feedback fail. Try again");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&error=Have a error with data");
        }

    }

    private void editFeedback(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Account accountLogin = (Account) session.getAttribute("acc");
        if (accountLogin == null) {
            response.sendRedirect("LoginServlet?error=Please login before booking");
            return;
        }
        String idString = request.getParameter("feedbackId");
        String billString = request.getParameter("bill_id");
        try {
            int id = Integer.parseInt(idString);
            int billId = Integer.parseInt(billString);
            String content = request.getParameter("content");
            int star = Integer.parseInt(request.getParameter("star"));
            Date date = new Date();

            FeedbackDAO feedbackDao = new FeedbackDAO();
            Feedback feedback = new Feedback();
            feedback.setId(id);
            feedback.setContent(content);
            feedback.setStar(star);
            feedback.setBill_id(billId);
            feedback.setDate(date);
            feedback.setUser_id(accountLogin.getUserid());

            int result = feedbackDao.updateFeedback(feedback);
            if (result > 0) {
                response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&success=Feedback updated successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&error=Feedback update failed. Try again");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect(request.getContextPath() + "/history-booking?action=view&bill_id=" + billString + "&error=An error occurred with data");
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
