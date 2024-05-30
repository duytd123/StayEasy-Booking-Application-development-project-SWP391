package Controller;

import Dao.AccountDAO;
import Model.Account;
import Model.Role;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SecurityServlet", urlPatterns = {"/SecurityServlet"})
public class SecurityServlet extends HttpServlet {

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
                throw new AssertionError();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
            request.getRequestDispatcher("security.jsp").forward(request, response);
        } else {
            if (curPass.equals(acc.getPass())) {
                if (newPass == null || newPass.equals("") || rePass == null || rePass.equals("")) {
                    request.setAttribute("mess", "Enter new pass and confirm pass");
                    request.getRequestDispatcher("security.jsp").forward(request, response);
                } else {
                    if (newPass.equals(rePass)) {
                        AccountDAO dao = new AccountDAO();
                        dao.updateAccountPass(newPass, acc.getUserid());
                        acc.setPass(rePass);
                        request.setAttribute("mess", "password has been changed");
                        request.getRequestDispatcher("security.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mess", "confirm pass is wrong");
                        request.getRequestDispatcher("security.jsp").forward(request, response);
                    }
                }
            } else {
                request.setAttribute("mess", "current pass is wrong");
                request.getRequestDispatcher("security.jsp").forward(request, response);
            }
        }
    }

    public String getServletInfo() {
        return "Short description";
    }
}
