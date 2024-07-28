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
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        
        if (username == null || username.trim().isEmpty()) {
            setErrorAndForward(request, response, "input username!", username, fullname, email, phone);
            return;
        }
        
        if (password == null || password.trim().isEmpty()) {
            setErrorAndForward(request, response, "input password and repeat password!", username, fullname, email, phone);
            return;
        }
        
        if (email == null || email.trim().isEmpty()) {
            setErrorAndForward(request, response, "Please input email!", username, fullname, email, phone);
            return;
        }
        
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        
        if (!pattern.matcher(email).matches()) {
            setErrorAndForward(request, response, "Invalid email format!", username, fullname, email, phone);
            return;
        }
        
        if (!password.equals(repassword)) {
            setErrorAndForward(request, response, "repeat password is wrong!", username, fullname, email, phone);
            return;
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
    
    @Override
    public String getServletInfo() {
        return "Sign Up Servlet";
    }
}
