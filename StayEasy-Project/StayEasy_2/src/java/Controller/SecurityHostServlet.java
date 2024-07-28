package Controller;

import Dao.AccountDAO;
import Model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SecurityHostServlet", urlPatterns = {"/securityhost"})
public class SecurityHostServlet extends HttpServlet {


   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("sub");
        switch (action) {
            case "resetPass":
                resetPass(request, response);
                break;
            default:
                throw new ServletException("Unknown action");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("dashboardhost/security.jsp").forward(request, response);
    }

    protected void resetPass(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String curPass = request.getParameter("curPass");
        String newPass = request.getParameter("newPass");
        String rePass = request.getParameter("rePass");

        Account acc = (Account) session.getAttribute("acc");
        if (acc == null) {
            request.setAttribute("mess", "User session not found.");
            request.getRequestDispatcher("dashboardhost/security.jsp").forward(request, response);
            return;
        }

        if (curPass == null || curPass.trim().isEmpty()) {
            request.setAttribute("mess", "Enter current password.");
            request.getRequestDispatcher("dashboardhost/security.jsp").forward(request, response);
            return;
        }

        if (!curPass.equals(acc.getPass())) {
            request.setAttribute("mess", "Current password is incorrect.");
            request.getRequestDispatcher("dashboardhost/security.jsp").forward(request, response);
            return;
        }

        if (newPass == null || newPass.trim().isEmpty() || rePass == null || rePass.trim().isEmpty()) {
            request.setAttribute("mess", "Enter new password and confirm password.");
            request.getRequestDispatcher("dashboardhost/security.jsp").forward(request, response);
            return;
        }

        if (!newPass.equals(rePass)) {
            request.setAttribute("mess", "New password and confirmation do not match.");
            request.getRequestDispatcher("dashboardhost/security.jsp").forward(request, response);
            return;
        }


        try {
            AccountDAO dao = new AccountDAO();
            dao.updateAccountPass(newPass, acc.getUserid());
            acc.setPass(newPass);
            session.setAttribute("acc", acc);
            request.setAttribute("mess", "Password has been changed successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mess", "An error occurred while updating the password.");
        }

        request.getRequestDispatcher("dashboardhost/security.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for handling security-related actions, including password reset.";
    }
}
