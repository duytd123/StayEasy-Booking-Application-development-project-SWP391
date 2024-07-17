/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.BillDAO;
import Dao.BillDetailDAO;
import Dao.HouseDAO;
import Dao.HouseImgDAO;
import Model.Account;
import Model.Bill;
import Model.BillDetail;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP
 */
@WebServlet(name = "BookingUserController", urlPatterns = {"/booking"})
public class BookingUserController extends HttpServlet {

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
            out.println("<title>Servlet BookingUserController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingUserController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account accountLogin = (Account) session.getAttribute("acc");
        if (accountLogin != null) {
            String houseIdStr = request.getParameter("houseId");
            int houseId = Integer.parseInt(houseIdStr);
            HouseDAO houseDao = new HouseDAO();
            House house = houseDao.getHousebyId(houseId);
            HouseImgDAO houImageDAO = new HouseImgDAO();
            List<HouseImg> listImage = houImageDAO.getHouseImgbyID(houseId);
            request.setAttribute("account", accountLogin);
            request.setAttribute("house", house);
            request.setAttribute("listImage", listImage);
            request.getRequestDispatcher("booking.jsp").forward(request, response);
        } else {
            response.sendRedirect("LoginServlet?error=Please login before booking");
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
        this.booking(request, response);
    }

    private void booking(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Account accountLogin = (Account) session.getAttribute("acc");
        if (accountLogin == null) {
            response.sendRedirect("LoginServlet?error=Please login before booking");
            return;
        }
        String houseId = request.getParameter("houseid").trim();
        try {
            String fullName = request.getParameter("fullname").trim();
            String phone = request.getParameter("phone").trim();
            String email = request.getParameter("email").trim();
            String startDateStr = request.getParameter("startdate").trim();
            String endDateStr = request.getParameter("enddate").trim();
            String note = request.getParameter("note").trim();
            String payment = request.getParameter("payment");
            String paymentText = payment.equals("1") ? "VNPAY" : "CASH";

            if (fullName.length() == 0) {
                String error = "Fullname can not empty.";
                response.sendRedirect("booking?houseId=" + houseId + "&error=" + error);
                return;
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                String error = "Invalid email format.";
                response.sendRedirect("booking?houseId=" + houseId + "&error=" + error);
                return;
            }

            if (!phone.matches("\\d{10}")) {
                String error = "Invalid phone format. It should be 10 digits.";
                response.sendRedirect("booking?houseId=" + houseId + "&error=" + error);
                return;
            }

            LocalDate today = LocalDate.now();
            LocalDate startDate = LocalDate.parse(startDateStr);
            LocalDate endDate = LocalDate.parse(endDateStr);

            if (startDate.isBefore(today)) {
                String error = "Start date must be greater than today.";
                response.sendRedirect("booking?houseId=" + houseId + "&error=" + error);
                return;
            }

            if (endDate.isBefore(startDate)) {
                String error = "End date must be greater than start date.";
                response.sendRedirect("booking?houseId=" + houseId + "&error=" + error);
                return;
            }

            long totalDays = ChronoUnit.DAYS.between(startDate, endDate);
            float housePrice = Float.parseFloat(request.getParameter("housePrice"));
            float total = housePrice * totalDays;

            BillDAO billDao = new BillDAO();
            BillDetailDAO billDetailDao = new BillDetailDAO();

            java.sql.Date sqlStartDate = java.sql.Date.valueOf(startDate);
            java.sql.Date sqlEndDate = java.sql.Date.valueOf(endDate);

            if (billDetailDao.isHouseAvailable(Integer.parseInt(houseId), sqlStartDate, sqlEndDate)) {
                Bill bill = new Bill();
                bill.setFullname(fullName);
                bill.setPhone(phone);
                bill.setEmail(email);
                bill.setTotal(total);
                bill.setUserid(accountLogin.getUserid());
                bill.setStatus(0);
                bill.setDate(new Date());
                bill.setPayment(paymentText);
                List<BillDetail> billDetails = new ArrayList<>();
                BillDetail billDetail = new BillDetail();
                billDetail.setEnddate(Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                billDetail.setStartdate(Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                billDetail.setHouseid(Integer.parseInt(houseId));
                billDetail.setNote(note);
                billDetails.add(billDetail);
                bill.setBillDetail(billDetails);
                int idBill = billDao.bookingBill(bill);
                if (idBill > 0) {
                    billDetail.setBillid(idBill);
                    int id = billDetailDao.addBillDetail(billDetail);
                    if(payment.equals("1")) {
                        session.setAttribute("bookingId", idBill);
                        response.sendRedirect("vnpay?amount=" + (int)total);
                        return;
                    }
                    if (id > 0) {
                        response.sendRedirect("bookingConfirmation.jsp?message=success");
                    } else {
                        response.sendRedirect("bookingConfirmation.jsp?message=fail");
                    }
                }
            } else {
                response.sendRedirect("booking?houseId=" + houseId + "&error=This time is not free for booking");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
            response.sendRedirect("booking?houseId=" + houseId + "&error=Error processing booking");
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
