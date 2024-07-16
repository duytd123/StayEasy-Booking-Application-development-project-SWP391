    <section class="dashboard">
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="Model.Account"%>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Admin Dashboard</title>
            <!-- Font Awesome CDN Link -->
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
            
            <!-- Bootstrap 5 -->
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
            
            <!-- jQuery -->
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
            
            <!-- Custom CSS -->
            <link href="css/Admin_style.css" rel="stylesheet" type="text/css"/>
        </head>
        <body>
            <header class="header bg-dark text-white p-3 mb-4">
                <div class="container d-flex justify-content-between align-items-center">
                    <a   style="font-size: large" href="home" class="logo text-white">Admin<span>Panel</span></a>
                    <nav class="navbar">
                        <a style="font-size: large"  href="DashboardServlet" class="text-white mx-5">Home</a>
                        <a  style="font-size: large" href="ListHouseServlet" class="text-white mx-5">Room</a>
                        <a  style="font-size: large" href="ListBillServlet" class="text-white mx-5">Orders</a>
                        <a  style="font-size: large" href="ListAccountServlet" class="text-white mx-5">Users</a>
                        <a  style="font-size: large" href="ListAddService" class="text-white mx-5">Service</a>
                        <a  style="font-size: large" href="ListCommentServlet" class="text-white mx-5">Messages</a>
                    </nav>
                    <div class="icons">
                        <div id="menu-btn" class="fas fa-bars"></div>
                        <div id="user-btn" class="fas fa-user"></div>
                    </div>
                    <div class="account-box bg-white p-3 rounded">
                        <p>Username: <span>${fullname}</span></p>
                        <a href="LogoutServlet" class="btn btn-danger">Logout</a>
                    </div>
                </div>
            </header>

            <!-- Admin Dashboard Section Starts -->
            <div class="container">
                <h1 class="title mb-4">Dashboard</h1>

                <div class="section mb-5">
                    <h2 class="title mb-3">Top 3 House Booking</h2>
                    <div class="row">
                        <c:forEach items="${listHouse}" var="house">
                            <div class="col-md-4 mb-4">
                                <div class="card p-3">
                                    <h1>${house.housename}</h1>
                                    
                                    <p style="font-size: large" >${house.numberBill} Select</p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="section mb-5">
                    <h2 class="title mb-3">Top 3 Users</h2>
                    <div class="row">
                        <c:forEach items="${listBill}" var="bill">
                            <div class="col-md-4 mb-4">
                                <div class="card p-3">
                                    <h3 >${bill.userName}</h3>
                                    <p style="font-size: large">Total cost: ${bill.total} <i class="fa-solid fa-dong-sign"></i></p>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>

                <div class="section mb-5">
                    <h2 class="title mb-3">Type Users and Total Accounts</h2>
                    <div class="row">
                        <div class="col-md-4 mb-4">
                            <div class="card p-3">
                                <p style="font-size: large">Number of normal users: ${countUser} <i class="fas fa-user"></i></p>
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="card p-3">
                                <p style="font-size: large" >Number of admin users: ${countAdmin} <i class="fas fa-user"></i></p>
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="card p-3">
                                <p style="font-size: large" >Total accounts: ${countAdmin + countUser} <i class="fas fa-user"></i></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>
        <script src="admin_script.js"></script>
    </html>
</section>
