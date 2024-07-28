package Controller;

import Dao.AdditionalServiceDAO;
import Dao.HouseAdditionalServiceDAO;
import Dao.HouseDAO;
import Model.Account;
import Model.AdditionalService;
import Model.House;
import Model.HouseAdditionalService;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "AdditionalHost", urlPatterns = {"/additional1", "/updatePrice"})
public class AdditionalHost extends HttpServlet {

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loggedInUser = (Account) session.getAttribute("acc");

        if (loggedInUser == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        int hostId = loggedInUser.getUserid();
        HouseDAO houseDAO = new HouseDAO();
        List<House> houses = houseDAO.getHousesByHostId(hostId);

        AdditionalServiceDAO asDAO = new AdditionalServiceDAO();
        List<AdditionalService> services = asDAO.getAdditionalServicee();

        int houseId = getHouseIdFromRequestOrDefault(request, houses);

        HouseAdditionalServiceDAO hasDAO = new HouseAdditionalServiceDAO();
        List<HouseAdditionalService> houseServices = hasDAO.getHouseAdditionalServiceForHouse(houseId);

        request.setAttribute("houses", houses);
        request.setAttribute("services", services);
        request.setAttribute("houseId", houseId);
        request.setAttribute("houseServices", houseServices);

        request.getRequestDispatcher("dashboardhost/additional.jsp").forward(request, response);
    }

    private int getHouseIdFromRequestOrDefault(HttpServletRequest request, List<House> houses) {
        String houseIdParam = request.getParameter("houseId");
        int houseId = 0;
        if (houseIdParam != null && !houseIdParam.isEmpty()) {
            try {
                houseId = Integer.parseInt(houseIdParam);
            } catch (NumberFormatException e) {
                houseId = 0;
            }
        }
        if (houseId == 0 && !houses.isEmpty()) {
            houseId = houses.get(0).getHouseid();
        }
        return houseId;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        if ("/updatePrice".equals(action)) {
            updatePrice(request, response);
        } else {
            handleAddService(request, response);
        }
    }

    private void handleAddService(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loggedInUser = (Account) session.getAttribute("acc");

        if (loggedInUser == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        int houseId = Integer.parseInt(request.getParameter("houseId"));
        int serviceId = Integer.parseInt(request.getParameter("serviceId"));

        HouseAdditionalService newService = new HouseAdditionalService();
        newService.setHouseid(houseId);
        newService.setServiceid(serviceId);
        newService.setServicestatus(1);
        newService.setServiceprice(0);

        HouseAdditionalServiceDAO hasDAO = new HouseAdditionalServiceDAO();
        hasDAO.addHouseAdditionalService(newService);

        response.sendRedirect("additional1?houseId=" + houseId);
    }

    private void updatePrice(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int houseAddServiceId = Integer.parseInt(request.getParameter("houseAddServiceId"));
            float price = Float.parseFloat(request.getParameter("price"));
            System.out.println("Updating price for houseAddServiceId: " + houseAddServiceId + " with price: " + price);

            HouseAdditionalServiceDAO hasDAO = new HouseAdditionalServiceDAO();
            hasDAO.updateServicePrice(houseAddServiceId, price);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Error: " + e.getMessage());
            System.out.println("Error updating price: " + e.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
