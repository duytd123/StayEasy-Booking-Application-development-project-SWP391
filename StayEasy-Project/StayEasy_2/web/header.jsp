<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Stay Ease Booking</title>
    <link rel="stylesheet" href="path/to/font-awesome/css/font-awesome.min.css">
    <style>
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 20px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        header .logo {
            font-size: 1.5em;
            font-weight: bold;
            color: #333;
        }

        header .navbar a {
            margin: 0 10px;
            color: #333;
            text-decoration: none;
            font-size: 1em; /* Adjust font size as needed */
        }

        header .navbar a:hover {
            color: #007bff;
        }

        header .icons {
            display: flex;
            align-items: center;
        }

        header .icons .fas {
            margin-left: 15px;
            cursor: pointer;
        }

        header .user-fullname {
            font-weight: bold;
            font-family: Arial;
            color: #fd7e14;
            margin-left: 10px; 
        }

        .icon-link {
            color: #333;
        }

        .icon-link:hover {
            color: #007bff;
        }
    </style>
</head>

<body>
    <header>
        <div id="menu-bar" class="fas fa-bars"></div>
        <a href="home" class="logo"><span>Welcome</span>Stay<span>Ease</span>Booking</a>
        <nav class="navbar">
            <a href="#book">Book</a>
            <a href="aboutus">About Us</a>
            <a href="#gallery">Hot Place</a>
            <a href="#contact">Contact</a>

            <c:if test="${sessionScope.acc != null}">
                <a href="history-booking">History booking</a>
                
            </c:if>
            <c:if test="${sessionScope.acc != null}">
                <c:choose>
                    <c:when test="${acc.role.id == 0}">
                        <a href="DashboardServlet">Dashboard</a>
                    </c:when>
                    <c:when test="${acc.role.id == 1}">
                        <a href="host">Dashboard</a>
                    </c:when>
                    <c:otherwise>
                        <a href="hostregister">Register to host</a>
                    </c:otherwise>
                </c:choose>
            </c:if>
        </nav>
        <div class="icons">
            <c:if test="${sessionScope.acc == null}">
                <a href="LoginServlet" class="icon-link">
                    <i class="fas fa-user" id="user-btn"></i>
                </a>
            </c:if>
            <c:if test="${sessionScope.acc != null}">
                <span class="user-fullname">${acc.fullname}</span>
                <a href="LogoutServlet" class="icon-link">
                    <i class="fas fa-sign-out-alt" id="logout-btn"></i>
                </a>
                <a href="UserServlet" class="icon-link">
                    <i class="fas fa-user-circle" id="profile-btn"></i>
                </a>
            </c:if>
        </div>
    </header>
</body>

</html>
