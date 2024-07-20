<%-- 
    Document   : bookingConfirmation
    Created on : Jul 5, 2024, 5:26:01 PM
    Author     : HP
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House Booking</title>
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
                max-width: 1200px;
                padding: 20px;
                border: 1px solid #ccc;
            }
            .success-message {
                color: green;
                font-weight: bold;
            }
            .error-message {
                color: red;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div class="container" style="margin-top: 100px;">
            <div class="message-container">
                <c:choose>
                    <c:when test="${param.message == 'success'}">
                        <img src="https://cdn-icons-png.freepik.com/512/5610/5610944.png" width="100px" height="100px" alt="alt"/>
                        <h1 class="success-message">Booking Successful!</h1>
                        <p>Your booking has been confirmed.</p>
                    </c:when>
                    <c:otherwise>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ3gJy5xGIg2F1fTXxMbDOkkcppaDCUVRLfIQ&s" width="100px" height="100px" alt="alt"/>
                        <h1 class="error-message">Booking Failed!</h1>
                        <p>There was an error processing your booking.</p>
                    </c:otherwise>
                </c:choose>
                        <a href="history-booking" class="btn btn-primary mt-3">Back to history booking</a>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="Housepage.js" type="text/javascript"></script>
    </body>
</html>
