
package Controller;

import Dao.AccountDAO;
import Model.Account;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cusername")) {
                    request.setAttribute("usernameCookie", cookie.getValue());
                } else if (cookie.getName().equals("cpassword")) {
                    request.setAttribute("passwordCookie", cookie.getValue());
                } else if (cookie.getName().equals("rememberme")) {
                    request.setAttribute("rememberMeCookie", cookie.getValue());
                }
            }
        }

        // Forward the request to login.jsp
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        boolean rememberMe = request.getParameter("remember") != null;

        AccountDAO dao = new AccountDAO();
        Account a = dao.getAccountLogin(username, password);

        if (a != null) {
            HttpSession session = request.getSession();
            session.setAttribute("acc", a);
            session.setAttribute("username", a.getUsername());
            session.setAttribute("email", a.getEmail());
            session.setAttribute("rememberme", rememberMe);
            session.setAttribute("imageUser", a.getUserimg());

            if (rememberMe) {
                Cookie cUsername = new Cookie("cusername", a.getUsername());
                Cookie cPassword = new Cookie("cpassword", a.getPass());
                Cookie cRememberMe = new Cookie("rememberme", "true");

                cUsername.setMaxAge(60 * 60 * 24 * 30); // 30 days
                cPassword.setMaxAge(60 * 60 * 24 * 30); // 30 days
                cRememberMe.setMaxAge(60 * 60 * 24 * 30); // 30 days

                response.addCookie(cUsername);
                response.addCookie(cPassword);
                response.addCookie(cRememberMe);
            } else {
                // Clear cookies if "remember me" is not checked
                Cookie[] cookies = request.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("cusername") || cookie.getName().equals("cpassword") || cookie.getName().equals("rememberme")) {
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                }
            }

            if (a.getRole().getId() == 0) {
                response.sendRedirect("DashboardServlet");
            } else if (a.getRole().getId() == 1 && a.getStatus() == 1) {
                response.sendRedirect("host");
            } else if (a.getRole().getId() == 2 && a.getStatus() == 1) {
                response.sendRedirect("home");
            } else {
                response.sendRedirect("error");
            }
        } else {
            request.setAttribute("mess", "Wrong username or password!!!");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
