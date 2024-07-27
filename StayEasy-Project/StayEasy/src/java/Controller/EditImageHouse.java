package Controller;

import Dao.HouseImgDAO;
import Model.Account;
import Model.HouseImg;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;

@MultipartConfig
@WebServlet(name = "EditImageHouse", urlPatterns = {"/editimage"})
public class EditImageHouse extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Account loggedInUser = (Account) request.getSession().getAttribute("acc");
        if (loggedInUser != null) {
            try {
                int hostId = loggedInUser.getUserid();
                String id_raw = request.getParameter("hid");
                int id = Integer.parseInt(id_raw);
                HouseImgDAO daoH = new HouseImgDAO();
                List<HouseImg> houseImages = daoH.getHouseImgByHostIdAndHouseId(hostId, id);

                request.setAttribute("detail", houseImages);
                request.setAttribute("houseId", id);

                request.getRequestDispatcher("dashboardhost/updateimage.jsp").forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("manager");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Account loggedInUser = (Account) request.getSession().getAttribute("acc");
        if (loggedInUser != null) {
            try {
                String id_raw = request.getParameter("hid");
                int id = Integer.parseInt(id_raw);

                HouseImgDAO daoH = new HouseImgDAO();

                Part filePart = request.getPart("image");
                if (filePart != null && filePart.getSize() > 0) {
                    String uploadFolder = getServletContext().getRealPath("/Images/houseimgs");
                    Path uploadPath = Paths.get(uploadFolder);

                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }

                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                    Path filePath = uploadPath.resolve(fileName);

                    try (InputStream fileContent = filePart.getInputStream()) {
                        Files.copy(fileContent, filePath, StandardCopyOption.REPLACE_EXISTING);
                    }

                    String relativeImagePath = "Images/houseimgs/" + fileName;
                    daoH.insertHouseImage(id, relativeImagePath);

                    response.sendRedirect("editimage?hid=" + id);
                } else {
                    response.sendRedirect("manager");
                }

            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("manager");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            }
        } else {
            response.sendRedirect("LoginServlet");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        Account loggedInUser = (Account) request.getSession().getAttribute("acc");
        if (loggedInUser != null) {
            try {
                StringBuilder json = new StringBuilder();
                try (BufferedReader reader = request.getReader()) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        json.append(line);
                    }
                }

                String jsonString = json.toString();
                int imgId = Integer.parseInt(jsonString.replaceAll("[^0-9]", ""));

                HouseImgDAO daoH = new HouseImgDAO();
                daoH.deleteHouseImage(imgId);
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid image ID.");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            }
        } else {
            response.sendRedirect("LoginServlet");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet for editing house images.";
    }
}
