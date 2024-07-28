package Controller;

import Dao.CommentDAO;
import Model.Account;
import Model.Comment;
import Model.CommentWithInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "AddCommentServlet", urlPatterns = {"/AddCommentServlet"})
public class AddCommentServlet extends HttpServlet {

    private CommentDAO commentDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int houseId = Integer.parseInt(request.getParameter("houseid"));
        List<CommentWithInfo> comments = commentDAO.getCommentsByHouseId2(houseId);
        request.setAttribute("commentt", comments);
        request.getRequestDispatcher("Housepage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("mess");
        Account loggedInUser = (Account) session.getAttribute("acc");

        if (loggedInUser == null) {
            response.sendRedirect("LoginServlet");
            return;
        }
        int userid = loggedInUser.getUserid();       
        int houseId = Integer.parseInt(request.getParameter("houseid"));
        String comment = request.getParameter("comment");
        Date date = new Date();

        Comment c = new Comment(0, userid, houseId, comment, date);
        commentDAO.addComment(c);

        response.sendRedirect("housepage?houseid=" + houseId);
    }

//    private void addComment(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        int userid = Integer.parseInt(request.getParameter("userid"));
//        int houseId = Integer.parseInt(request.getParameter("houseid"));
//        String comment = request.getParameter("comment");
//        Date date = new Date();
//
//        Comment c = new Comment(0, userid, houseId, comment, date);
//        commentDAO.addComment(c);
//
//        response.sendRedirect("housepage?houseid=" + houseId);
}
