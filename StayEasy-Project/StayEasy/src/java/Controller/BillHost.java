/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.BillDAO;
import Model.Account;
import Model.Bill;
import Model.Bill1;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author badao
 */
@WebServlet(name = "BillHost", urlPatterns = {"/billhost"})
public class BillHost extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();
        session.removeAttribute("mess");
        Account loggedInUser = (Account) session.getAttribute("acc");

        if (loggedInUser == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        int hostId = loggedInUser.getUserid();
        BillDAO bdao = new BillDAO();

        List<Bill1> list = bdao.getBillsByHostId(hostId);

        float rs = 0;

        for (Bill1 bill : list) {
            if (bill.getStatus() == 1) {
                rs += bill.getTotal();
            }
        }

        request.setAttribute("rs", rs);
        request.setAttribute("list", list);
        request.getRequestDispatcher("dashboardhost/bill.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

