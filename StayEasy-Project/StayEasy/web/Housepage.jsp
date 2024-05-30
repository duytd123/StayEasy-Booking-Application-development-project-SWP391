
<%@page import="Model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.House"%>
<%@page import="java.util.List"%>
<%@page import="Model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House Details</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link href="stylesheet" rel="stylesheet">
        <link rel="stylesheet" href="list.css">
        <link rel="stylesheet" href="housepage.css">
        <link rel="stylesheet" href="css/list_house_main.css">
        <link rel="stylesheet" href="assets/css/style.min.css">
        <link rel="stylesheet" href="assets/css/dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/dist/css/bootstrap_1.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="user.jsp">
        <link rel="stylesheet" href="list_house_main.css">
        <link rel="stylesheet" href="StyleSheet.css">
        <link rel="stylesheet" href="css/housepage.css">
        <style>
            body {
                margin-top: 100px;
            }
        </style>
    </head>
    <%@ include file="header.jsp" %>

    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-8">
                    <h1 class="display-4">${house.housename}</h1>
                    <p class="lead"><i class="fas fa-map-marker-alt"></i> ${house.address}</p>
                    <p>Check-in Date: ${house.postdate}</p>
                    <p class="text-primary font-weight-bold">${house.houseprice}</p>
                    <div class="hotel-images mb-4">
                        <c:forEach items="${listImage}" var="image">
                            <img src="${image.imglink}" alt="House Image" class="img-fluid mb-2" />
                        </c:forEach>
                    </div>
                    <h2>Description</h2>
                    <p>${house.description}</p>
                    <div class="hotel-services mt-4">
                        <h2>Services</h2>
                        <div class="form-group">
                            <label>Beefsteak:</label>
                            <input type="number" class="form-control" />
                        </div>
                        <div class="form-group">
                            <label>Drink:</label>
                            <input type="number" class="form-control" />
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <button class="btn btn-primary btn-block mb-3" id="open-bill">Reserve or Book Now</button>
                    <form action="housepage" method="post" class="form-group">
                        <input type="hidden" name="houseid" value="${house.houseid}" />
                        <label>Start Date:</label>
                        <input type="date" name="startdate" class="form-control mb-2" required />
                        <label>End Date:</label>
                        <input type="date" name="enddate" class="form-control mb-2" required />
                        <label>Note:</label>
                        <input type="text" name="note" class="form-control mb-2" />
                        <button type="submit" class="btn btn-success btn-block">Reserve or Book Now</button>
                    </form>
                </div>
            </div>

            <% 
            Account a = (Account) request.getAttribute("account");
            String username = (String)session.getAttribute("username");
            if (username == null) {
            %>
            <div class="alert alert-warning mt-4" role="alert">
                Please <a href="login.jsp" class="alert-link">login</a> to comment.
            </div>
            <% } else { %>
            <section class="comments-container mt-5">
                <h1 class="heading">Leave us your comment</h1>
                <form action="AddCommentmainServlet" method="post" class="form-group">
                    <div class="form-group">
                        <label>Name: ${username}</label>
                        <input type="hidden" name="userid" value="<%= a.getUserid() %>" />
                        <input type="hidden" name="houseid" value="${house.houseid}" />
                    </div>
                    <div class="form-group">
                        <label>Date:</label>
                        <input type="date" name="date" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label>Comment:</label>
                        <textarea name="comment" class="form-control" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Comment</button>
                </form>
            </section>

            <h1 class="heading mt-5">Comments</h1>
            <%
                List<Comment> list = (List<Comment>) request.getAttribute("Comment");
                for (Comment c : list) {
            %>
            <section class="comment mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">From: ${house.housename}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">By: ${account.fullname}</h6>
                        <p class="card-text">${c.getComment()}</p>
                        <p class="card-text"><small class="text-muted">${c.getDate()}</small></p>
                        <a href="NextEditCommentServlet?id=<%= c.getCid() %>" class="btn btn-warning">Edit</a>
                        <a href="DeleteCommentmainServlet?id=<%= c.getCid() %>" class="btn btn-danger">Delete</a>
                    </div>
                </div>
            </section>
            <% } %>
            <% } %>
        </div>

        <%@ include file="footer.jsp" %>

        <script>
            var mess = '${mess}';
            if (mess) {
                alert(mess);
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="Housepage.js" type="text/javascript"></script>
    </body>
</html>
