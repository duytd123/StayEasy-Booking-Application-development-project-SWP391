<%-- 
    Document   : ListAddService
    Created on : May 28, 2024, 11:11:43 PM
    Author     : hungm
--%>

<%@page import="Model.Account"%>
<%@page import="Model.AdditionalService"%>
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
        <!-- Boostrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>  

        <!-- jquery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>
    <body>
        <%
        List<AdditionalService> list = new ArrayList<>();
        if(request.getAttribute("list") != null){
            list = (List<AdditionalService>) request.getAttribute("list");
        }
        %>
        <div class="header_fixed">
            <!--       <h1>Manage House</h1>
                    <form action="mainController" method="get">
                        Name : <input type="text" name="name" >
                        <input type="submit" value="search" name="action">
                    </form>-->


            <header class="header">

                <div class="flex">

                    <a href="AdminIndex.jsp" class="logo">Admin<span>Panel</span></a>

                    <nav class="navbar">
                        <a href="AdminIndex.jsp"><span>Home</span></a>
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

            <table>
                <form action="SearchAddServiceServlet" class="search-bar-container"  method="post"  >
                    <input style="height: 60px;font-size: 40px;" type="text" name="search" id="search-bar" placeholder="Search here..."">
                    <button class="fas fa-search" style="height: 60px; font-size: 40px; background-color: #fff" value="search" type="submit"></button>
                </form>
                <thead>
                    <tr>
                        <th>Additional_Service_ID</th>
                        <th>Additional_Service_Name</th>
                        <th>Additional_Service_Desc</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <%
                    for(AdditionalService a : list){
                %>
                <tr>
                    <td><%=a.getServiceid() %></td>
                    <td><%=a.getServicename() %></td>
                    <td><%=a.getServicedesc() %></td>
                    <td>
                        <span class="action_btn">
                            <a href="NextEditAdditionalServiceServlet?id=<%=a.getServiceid() %>">Update</a>
                            <a href="DeleteAdditionalServiceServlet?id=<%=a.getServiceid() %>">Delete</a>
                            <a href="AddAddService.jsp">Add</a>
                        </span>
                    </td>
                </tr>
                <%
                }
                %>
            </table>
        </div>
    </body>
    <script src="admin_script.js"></script>
</html>
