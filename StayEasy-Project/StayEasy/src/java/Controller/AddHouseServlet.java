package Controller;

import Dao.HouseDAO;
import Dao.HouseImgDAO;
import Dao.LocationDAO;
import Dao.MenuDAO;
import Model.Account;
import Model.House;
import Model.Location;
import Model.Menu;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class AddHouseServlet extends HttpServlet {
  private static final String STRING_PATTERN = "^(?!\\s*$).{6,19}$";
      public static final String ADDRESS_VALID = "[a-zA-Z0-9.{6,19} ]+";

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
            out.println("<title>Servlet AddHouseServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddHouseServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Location> locations = locationDAO.getLocation();
        List<Menu> menus = menuDAO.getMenu();

        request.setAttribute("llist", locations);
        request.setAttribute("mlist", menus);

        request.getRequestDispatcher("AddHouse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        String dateString = request.getParameter("postdate");
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = formatDate.parse(dateString);
        } catch (Exception e) {
            response.getWriter().print("error : " + e);
            return;

        }
        int hostId = account.getUserid();

        // Automatically set postdate to current date
        Date postDate = new Date();

        // Retrieve parameters
        String houseName = request.getParameter("housename");
        String review = "";  // Assuming review is not provided in the form, set to empty string
        String housePriceStr = request.getParameter("houseprice");
        String address = request.getParameter("address");
        String description = request.getParameter("description");
      
        int locationid = Integer.parseInt(request.getParameter("location"));
        int menuid = Integer.parseInt(request.getParameter("menu"));
        Location location = new Location(locationid, null);
        Menu menu = new Menu(menuid, null);
        String imglink = request.getParameter("imglink");
        int houseid = Integer.parseInt(request.getParameter("houseid"));

        if (!housename.matches(STRING_PATTERN)) {
            response.getWriter().print("Error: Housename cannot be left blank or Cannot be less than 5 and greater than 20 characters ");
            return;
        }
        if (!address.matches(ADDRESS_VALID)) {
            response.getWriter().print("Error: Address cannot be left blank.Cannot be less than 5 and greater than 20 characters");
            return;
        }

        if (!description.matches(STRING_PATTERN)) {
            response.getWriter().print("Error: Description cannot be left blank.Cannot be less than 5 and greater than 20 characters");
            return;
        }
        if(price <= 0.0){
            response.getWriter().print("Error: Price cannot <0");
            return;
        }

        House h = new House(-1, date, housename, review, price, status, address, description, location, menu);
        HouseDAO dao = new HouseDAO();
        dao.addHouse(h);

        HouseImg hi = new HouseImg(-1, imglink, houseid);
        HouseImgDAO hdao = new HouseImgDAO();
        hdao.addHouseImg(hi);
        response.sendRedirect("ListHouseServlet");

    }
}


    @Override
    public String getServletInfo() {
        return "AddHouseServlet";
    }
}
