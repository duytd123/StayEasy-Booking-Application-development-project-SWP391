/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.User;

import Dao.AdditionalServiceDAO;
import Model.AdditionalService;
import Dao.AccountDAO;
import Dao.BillDAO;
import Dao.BillDetailDAO;
import Dao.CommentDAO;
import Dao.FeedbackDAO;
import Dao.HouseAdditionalServiceDAO;
import Dao.HouseDAO;
import Dao.HouseImgDAO;
import Dao.LocationDAO;
import Model.Account;
import Model.Bill;
import Model.BillDetail;
import Model.Comment;
import Model.Feedback;
import Model.House;
import Model.HouseAdditionalService;
import Model.HouseImg;
import Model.Location;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HousePageServlet extends HttpServlet {

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
            out.println("<title>Servlet HousePageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HousePageServlet at " + request.getContextPath() + "</h1>");
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
        int houseId = Integer.parseInt(request.getParameter("houseId"));

        HouseDAO hdao = new HouseDAO();
        House house = hdao.getHousebyId(houseId);
        HouseImgDAO houImageDAO = new HouseImgDAO();
        List<HouseImg> listImage = houImageDAO.getHouseImgbyID(houseId);
        CommentDAO cdao = new CommentDAO();
        List<Comment> list = cdao.getComment();
        AccountDAO dao = new AccountDAO();
        LocationDAO locationDao = new LocationDAO();
        FeedbackDAO feedbackDao = new FeedbackDAO();
        List<Feedback> feedbacks = feedbackDao.getAllFeedbacks(houseId);
        Account a = dao.getAccounts();
        request.setAttribute("feedbacks", feedbacks);
        request.setAttribute("Comment", list);
        request.setAttribute("account", a);
        request.setAttribute("house", house);
        request.setAttribute("listImage", listImage);
        request.getRequestDispatcher("Housepage.jsp").forward(request, response);
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
        //check login
        if (request.getSession().getAttribute("username") == null) {
            request.setAttribute("mess", "Please Enter login");
            request.getRequestDispatcher("Housepage.jsp").forward(request, response);
            return;
        }
        //get userId
        AccountDAO adao = new AccountDAO();
        Account user = adao.getAccountbyUsername(request.getSession().getAttribute("username").toString());

        int houseId = Integer.parseInt(request.getParameter("houseid"));
        HouseDAO hdao = new HouseDAO();
        House house = hdao.getHousebyId(houseId);
        float total = house.getHouseprice();
        //TODO : total + list price service
        HouseAdditionalServiceDAO asdao = new HouseAdditionalServiceDAO();
        List<HouseAdditionalService> listService = asdao.getHouseAdditionalServicebyID(houseId);
        for (HouseAdditionalService additionalService : listService) {
            total += additionalService.getServiceprice();
        }

        //add bill
        Bill bill = new Bill(-1, new Date(), total, 1, user.getUserid());
        BillDAO bdao = new BillDAO();
        int idBill = bdao.addBill(bill);

        //check add sucess
        if (idBill == -1) {
            request.setAttribute("mess", "Add bill fail");
            request.getRequestDispatcher("Housepage.jsp").forward(request, response);
            return;
        }

        //add bill detail
        //get value 
        String note = request.getParameter("note");
        Date startdate = null;
        Date enddate = null;
        try {
            startdate = covertDate(request.getParameter("startdate"));
            enddate = covertDate(request.getParameter("enddate"));
        } catch (ParseException ex) {
            request.setAttribute("mess", "covert date fail");
            Logger.getLogger(HousePageServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.getRequestDispatcher("Housepage.jsp").forward(request, response);
            return;
        }

        //check start, end date
        BillDetailDAO biBillDetailDAO = new BillDetailDAO();
        BillDetail billDetailCheck = biBillDetailDAO.getBillDeatailbyhouId(houseId);
        if (billDetailCheck != null) {
            if (checkDateExit(billDetailCheck, startdate)) {
                request.setAttribute("mess", "start date exist");
                request.getRequestDispatcher("Housepage.jsp").forward(request, response);
                return;
            }

            if (checkDateExit(billDetailCheck, enddate)) {
                request.setAttribute("mess", "end date exist");
                request.getRequestDispatcher("Housepage.jsp").forward(request, response);
                return;
            }
            if (startdate.before(billDetailCheck.getStartdate()) && enddate.after(billDetailCheck.getStartdate())) {
                request.setAttribute("mess", "start date exist && end date exist");
                request.getRequestDispatcher("Housepage.jsp").forward(request, response);
                return;
            }
        }

        BillDetail billDetail = new BillDetail(-1, idBill, houseId, startdate, enddate, note);

        int idBillDetail = biBillDetailDAO.addBillDetail(billDetail);

        //check add sucess
        if (idBillDetail == -1) {
            request.setAttribute("mess", "Add bill detail fail");
            request.getRequestDispatcher("Housepage.jsp").forward(request, response);
            return;
        }

        request.setAttribute("mess", "Add bill sucess");
        request.getRequestDispatcher("Housepage.jsp").forward(request, response);
    }

    private boolean checkDateExit(BillDetail billDetail, Date date) {
        boolean check = false;
        if (date.after(billDetail.getStartdate()) && date.before(billDetail.getEnddate())) {
            check = true;
        }
        return check;

    }

    private Date covertDate(String dateString) throws ParseException {
        //process date 
        //date=2022-09-09 || yyyy-MM-dd
        // String -> Date
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        // String -> Date
        return format.parse(dateString);
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

//copy hết mấy file xanh và xanh duong thì trong file có doạn này màu xanh lá thì copy hết nha
//cop xanh la kieu j a