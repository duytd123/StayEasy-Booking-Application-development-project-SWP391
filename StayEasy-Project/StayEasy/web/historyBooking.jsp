<%-- 
    Document   : history-order
    Created on : Jul 5, 2024, 5:58:56 PM
    Author     : HP
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History booking</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
        <style>
            body {
                margin-top: 50px;
            }
            .container {
                display: flex;
                justify-content: center;
            }
            .form-group {
                margin-bottom: 1rem;
            }
            .message-container {
                max-width: 500px;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 8px;
                background-color: #f8f9fa;
            }
            .success-message {
                color: green;
                font-weight: bold;
            }
            .error-message {
                color: red;
                font-weight: bold;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            th, td {
                border: 1px solid #ddd;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container" style="margin-top: 150px;">
         <h2>Order History</h2>
         </div>
        <div class="container" style="margin-top: 30px;">
            <table class="table table-bordered table-striped">
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Full Name</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Date</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${bills}" var="order">
                        <tr>
                            <td>${order.billid}</td>
                            <td>${order.fullname}</td>
                            <td>${order.phone}</td>
                            <td>${order.email}</td>
                            <td>${order.date}</td>
                            <td>${order.total}</td>
                            <td>${order.status == 0 ? "Pending" : order.status == 1 ? "Completed" : "Canceled"}</td>
                            <th>
                                <a class="btn btn-primary" href="history-booking?action=view&bill_id=${order.billid}">View</a>
                            </th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <%@ include file="footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="Housepage.js" type="text/javascript"></script>
    </body>
</html>