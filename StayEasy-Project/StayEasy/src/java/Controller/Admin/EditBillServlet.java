/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Dao.BillDAO;
import Dao.BillDetailDAO;
import Model.Bill;
import Model.BillDetail;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class EditBillServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditBillServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditBillServlet at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        int id = Integer.parseInt(request.getParameter("id"));
        int bid = Integer.parseInt(request.getParameter("bid"));
        String dateString =request.getParameter("date");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatDate.parse(dateString);
        } catch (Exception e) {
            response.getWriter().print("error : "+e);
            return;
        }
        float total = Float.parseFloat(request.getParameter("total"));
        int status = Integer.parseInt(request.getParameter("status"));
        int userid = Integer.parseInt(request.getParameter("userid"));
        int houseid = Integer.parseInt(request.getParameter("houseid"));
        String dateString1 =request.getParameter("startdate");
        SimpleDateFormat formatDate1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = new Date();
        try {
            date1 = formatDate1.parse(dateString1);
        } catch (Exception e) {
            response.getWriter().print("error : "+e);
            return;
        }
        String dateString2 =request.getParameter("enddate");
        SimpleDateFormat formatDate2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = new Date();
        try {
            date2 = formatDate2.parse(dateString2);
        } catch (Exception e) {
            response.getWriter().print("error : "+e);
            return;
        }
        String note = request.getParameter("note");
        BillDetail bd = new BillDetail(houseid, bid, houseid, date, date, note);
        BillDetailDAO ddao = new BillDetailDAO();
        ddao.editBillDetail(bd);
        Bill b = new Bill(id, date, total, status, userid);
        BillDAO dao = new BillDAO();
        dao.editBill(b);
        response.sendRedirect("ListBillServlet");
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
