/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dao.AccountDAO;
import Model.Account;
import Model.Constants;
import Model.Role;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;

@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/LoginGoogleServlet"})
public class LoginGoogleServlet extends HttpServlet {

    /**
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        Account a = null;
        if (code == null || code.isEmpty()) {
            response.sendRedirect("login.jsp");
        } else {
            String accessToken = getToken(code);
            Model.UserGoogle user = getUserInfo(accessToken);
            String email = user.getEmail();
            AccountDAO adao = new AccountDAO();
            Account aemail = adao.checkAccountByEmail(email);
            if (aemail == null) {
                Role role = new Role(2, "customer");
                a = new Account(-1, user.getName(), user.getPicture(), user.getEmail(), "1", email, null, 1, role);
                adao.signupAccount(a);
            } else {
                a = adao.getAccountbyEmail(email);
            }
            session.setAttribute("acc", a);
            session.setAttribute("rememberme", true);
            request.setAttribute("fullname", user.getName());
            request.getRequestDispatcher("Index.jsp").forward(request, response);

        }
    }

    //function lấy authentication token từ google
    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Model.Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");

        return accessToken;
    }

    //gửi request lên gg -> gg trả về dữ liệu ng dùng
    public static Model.UserGoogle getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        // lay thong tin cua nguoi dung
        String link = Model.Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        Model.UserGoogle googlePojo = new Gson().fromJson(response, Model.UserGoogle.class);

        return googlePojo;
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
        processRequest(request, response);
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
