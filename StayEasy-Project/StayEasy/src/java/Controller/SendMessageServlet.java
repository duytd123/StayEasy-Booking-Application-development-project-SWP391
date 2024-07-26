/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.Email;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author badao
 */
@WebServlet(name = "SendMessageServlet", urlPatterns = {"/SendMessageServlet"})
public class SendMessageServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(SendMessageServlet.class.getName());

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
            out.println("<title>Servlet SendMessageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SendMessageServlet at " + request.getContextPath() + "</h1>");
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
        response.sendRedirect("Index.jsp");
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
        // Get form parameters
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        String messageText = request.getParameter("Message");

        logger.log(Level.INFO, "Name: {0}", name);
        logger.log(Level.INFO, "Email: {0}", email);
        logger.log(Level.INFO, "Message: {0}", messageText);

        Email emailUtil = new Email();

        if (!emailUtil.isValidEmail(email)) {
            request.setAttribute("errorMessage", "Invalid email address.");
            request.getRequestDispatcher("Index.jsp").forward(request, response);
            return;
        }

        String subject = "New Message from " + name;
        String messageContent = emailUtil.formatUserMessage(name, email, messageText);

        try {
            emailUtil.sendEmail(subject, messageContent, "stayeasyfpt@gmail.com");
            request.setAttribute("successMessage", "Your message has been sent successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to send email", e);
            request.setAttribute("errorMessage", "Failed to send message. Please try again later.");
        }

        request.getRequestDispatcher("Index.jsp").forward(request, response);
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
