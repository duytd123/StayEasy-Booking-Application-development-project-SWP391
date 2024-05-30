
<section class="dashboard"><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="Model.Account"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
            <!-- font awesome cdn link  -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

            <!-- custom admin css file link  -->
            <link href="css/Admin_style.css" rel="stylesheet" type="text/css"/>
            <!-- Boostrap 5 -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>  

            <!-- jquery -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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
        </head>
        <body>
            <header class="header">

                <div class="flex">

                    <a href="home" class="logo">Admin<span>Panel</span></a>

                    <nav class="navbar">
                        <a href="DashboardServlet"><span>Home</span></a>
                        <a href="ListHouseServlet">Room</a>
                        <a href="ListBillServlet">Orders</a>
                        <a href="ListAccountServlet">Users</a>
                        <a href="ListAddService">Service</a>
                        <a href="ListCommentServlet">Messages</a>
                    </nav>

                    <div class="icons">
                        <div id="menu-btn" class="fas fa-bars"></div>
                        <div id="user-btn" class="fas fa-user"></div>
                    </div>

                    <div class="account-box">
                        <p>username : <span>${fullname}</span></p>
                        <a href="LogoutServlet" class="delete-btn">logout</a>

                    </div>

                </div>
            </header>

            <!-- check  the comment the char-->
            <!-- admin dashboard section starts  -->
            <h1 class="title">dashboard</h1>
            <div class="title">Top 3 House Booking</div>
            <div class="top3House-containner">
                <c:forEach items="${listHouse}" var="house">
                    <div class="Housetopcontainner">
                        <div class="box">
                            <h3>${house.housename}</h3>
                            <p>Total cost: 200000 <i class="fa-solid fa-dong-sign"></i></p>
                            <p>${house.numberBill} Select</p>
                        </div>
                    </div>
                </c:forEach>
            </div>

            <div class="title">Top 3 Users</div>
            <div class="top3House-containner">
                <c:forEach items="${listAcount}" var="acount">
                    <div class="Housetopcontainner">
                        <div class="box">
                            <h3>${acount.username}</h3>
                            <p>Total cost: ${acount.total} <i class="fa-solid fa-dong-sign"></i></p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <div class="box-container"> 
                <div class="title">Type Users and total accounts
                    <div class="box">
                        <h3></h3>
                        <p>Number of normal users: ${countUser} <i class="fas fa-user"></i></p>
                    </div>
                    <div class="box">
                        <h3></h3>
                        <p>Number of admin users: ${countAdmin} <i class="fas fa-user"></i></p>
                    </div>

                    <div class="box">
                        <h3></h3>
                        <p>total accounts: ${countAdmin + countUser} <i class="fas fa-user"></i></p>
                    </div>
                </div>
            </div>
</section>
</body>
<script src="admin_script.js"></script>
</html>