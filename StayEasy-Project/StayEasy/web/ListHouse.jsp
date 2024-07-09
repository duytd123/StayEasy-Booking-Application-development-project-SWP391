2   
<%@page import="Model.House"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="adminuser_style.css"/>
        <!-- font awesome cdn link  -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

        <!-- custom admin css file link  -->
        <link rel="stylesheet" href="admin_style.css">
        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>  

        <!-- jquery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>


    </head>
    <body>

        <%
        List<House> list = new ArrayList<House>();
        if (request.getAttribute("HouseList") != null) {
            list = (List<House>) request.getAttribute("HouseList");
        }
        %>

        <div class="header_fixed">
            <header class="header">
                <div class="flex">
                    <a href="DashboardServlet" class="logo">Admin<span>Panel</span></a>
                    <nav class="navbar">
                        <a href="DashboardServlet"><span>Home</span></a>
                        <a href="ListHouseServlet">House</a>
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

            <section class="dashboard">
                <div class="container-fluid">
                    <form action="SearchHouseServlet"  method="post">
                        <input type="text" name="search"  placeholder="Search here...">
                        <button  value="search" type="submit"></button>
                    </form>
                    <div >
                        <a href="NextAddHouseServlet">Add</a>
                    </div>
                    <div class="table-responsive table-container">
                        <table class="table table-striped table-bordered">
                            <thead>
                                <tr>
                                    <th>House_ID</th>
                                    <th>House_name</th>
                                    <th>Post_Date</th>
                                    <th>Review</th>
                                    <th>Price</th>
                                    <th>Status</th>
                                    <th>Address</th>
                                    <th>Description</th>
                                    <th>Location</th>
                                    <th>Menu</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (House h : list) {
                                %>
                                <tr>
                                    <td><a href="getHouseImgServlet?id=<%= h.getHouseid() %>"><%= h.getHouseid() %></a></td>
                                    <td><%= h.getHousename() %></td>
                                    <td><%= h.getPostdate() %></td>
                                    <td><%= h.getReview() %></td>
                                    <td><%= h.getHouseprice() %></td>
                                    <td><%= h.getStatus() %></td>
                                    <td><span class="address"><%= h.getAddress() %></span></td>
                                    <td><%= h.getDescription() %></td>
                                    <td><%= h.getLocation().getName() %></td>
                                    <td><%= h.getMenu().getName() %></td>
                                    <td>
                                        <span class="action_btn">
                                            <a href="NextEditHouseServlet?id=<%= h.getHouseid() %>">Update</a>
                                            <a href="DeleteHouseServlet?id=<%= h.getHouseid() %>">Delete</a>
                                            
                                            <a href="ListServiceServlet?id=<%= h.getHouseid() %>">View Service</a>
                                            <a href="NextAddServiceServlet?id=<%= h.getHouseid() %>">Add Service</a>
                                        </span>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
            </section>
        </div>
                                 
    </body>
    <script src="admin_script.js"></script>
</html>
