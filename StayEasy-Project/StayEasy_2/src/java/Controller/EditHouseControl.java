/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.HouseDAO;
import Model.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "EditHouseControl", urlPatterns = {"/edithouse"})
public class EditHouseControl extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
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
       request.getRequestDispatcher("dashboardhost/mnhouse.jsp").forward(request, response);
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

        Account loggedInUser = (Account) request.getSession().getAttribute("acc");
        if (loggedInUser == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        int hostId = loggedInUser.getUserid();

        String pid_raw = request.getParameter("id");
        String pname = request.getParameter("name");
        String pprice_raw = request.getParameter("price");
        String pdescribe = request.getParameter("description");
        String paddress = request.getParameter("address");
        String pdate = request.getParameter("date");
        String pdiscount_raw = request.getParameter("discount");
        String plocation_raw = request.getParameter("location");
        String pmenu_raw = request.getParameter("menu");
        String pnumberOfGuests_raw = request.getParameter("numberOfGuest");

        double pprice = 0.0;
        double pdiscount = 0.0;
        int plocation = 0;
        int pmenu = 0;
        int pid = 0;
        int pnumberOfGuests = 0;

        try {
            pid = Integer.parseInt(pid_raw);
            pprice = Double.parseDouble(pprice_raw);
            pdiscount = Double.parseDouble(pdiscount_raw);
            plocation = Integer.parseInt(plocation_raw);
            pmenu = Integer.parseInt(pmenu_raw);
            pnumberOfGuests = Integer.parseInt(pnumberOfGuests_raw);

            HouseDAO dao = new HouseDAO();
            dao.editHouse(pid, pname, pprice, pdescribe, paddress, pdate, pdiscount, pnumberOfGuests, hostId, plocation, pmenu);

            request.getSession().setAttribute("mess1", "Edit successfully!");

            response.sendRedirect("manager");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format.");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
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
