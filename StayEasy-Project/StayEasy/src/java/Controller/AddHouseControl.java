/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import Dao.HouseDAO;
import Model.Account;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.util.List;

@WebServlet(name = "AddHouseControl", urlPatterns = {"/addhouse"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50)
public class AddHouseControl extends HttpServlet {

    private static final long serialVersionUID = 1L;

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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        // Check if user is logged in
        Account loggedInUser = (Account) request.getSession().getAttribute("acc");

        if (loggedInUser == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        int hostId = loggedInUser.getUserid();

        String pname = request.getParameter("name");
        List<Part> pimageParts = request.getParts().stream()
                .filter(part -> "image".equals(part.getName()) && part.getSize() > 0)
                .toList();
        String pprice_raw = request.getParameter("price");
        String pdescribe = request.getParameter("description");
        String paddress = request.getParameter("address");
        String pdate = request.getParameter("date");
        String pdiscount_raw = request.getParameter("discount");
        String plocation_raw = request.getParameter("location");
        String pmenu_raw = request.getParameter("menu");

        double pprice = 0.0;
        double pdiscount = 0.0;
        int plocation = 0;
        int pmenu = 0;

        try {
            pprice = Double.parseDouble(pprice_raw);
            pdiscount = Double.parseDouble(pdiscount_raw);
            plocation = Integer.parseInt(plocation_raw);
            pmenu = Integer.parseInt(pmenu_raw);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing number: " + e.getMessage());
        }

        StringBuilder imageLinks = new StringBuilder();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "Images" + File.separator + "houseimgs";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        for (Part part : pimageParts) {
            String fileName = getSubmittedFileName(part);
            String filePath = uploadPath + File.separator + fileName;
            part.write(filePath);
            imageLinks.append("Images/houseimgs/").append(fileName).append(",");
        }

        if (imageLinks.length() > 0) {
            imageLinks.setLength(imageLinks.length() - 1);
        }

        HouseDAO dao = new HouseDAO();
        dao.insertHouse(pname, pprice, pdescribe, paddress, pdate, pdiscount, hostId, plocation, pmenu, imageLinks.toString());
        request.setAttribute("mess", "House Added!");
        request.getRequestDispatcher("dashboardhost/mnhouse.jsp").forward(request, response);
    }

    private String getSubmittedFileName(Part part) {
        String header = part.getHeader("content-disposition");
        for (String cd : header.split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
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
    //request.getRequestDispatcher("dashboardhosthost/mghouse.jsp").forward(request, response);
}
