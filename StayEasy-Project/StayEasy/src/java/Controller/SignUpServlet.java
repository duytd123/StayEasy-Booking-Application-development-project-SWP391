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
import java.util.regex.Pattern;

@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {

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
        if (phone != null && !phone.trim().isEmpty() && !phone.matches("\\d{10}")) {
            setErrorAndForward(request, response, "phone number must be  10 digits!", username, fullname, email, phone);
            return;
        }

        AccountDAO adao = new AccountDAO();
        Account existingAccount = adao.checkAccountExist(username);

        if (existingAccount != null) {
            setErrorAndForward(request, response, "username already exists!", username, fullname, email, phone);
            return;
        }

        Account existingEmail = adao.checkAccountByEmail(email);

        if (existingEmail != null) {
            setErrorAndForward(request, response, "email already exists!", username, fullname, email, phone);
            return;
        }

        Role role = new Role(2, "customer");
        Account newAccount = new Account(-1, fullname, "", username, password, email, phone, 1, role);
        adao.signupAccount(newAccount);

        response.sendRedirect("home");
    }

    private void setErrorAndForward(HttpServletRequest request, HttpServletResponse response, String message, String username, String fullname, String email, String phone)
            throws ServletException, IOException {
        request.setAttribute("mess", message);
        request.setAttribute("username", username);
        request.setAttribute("fullname", fullname);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.getRequestDispatcher("signup.jsp").forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "Sign Up Servlet";
    }
}
