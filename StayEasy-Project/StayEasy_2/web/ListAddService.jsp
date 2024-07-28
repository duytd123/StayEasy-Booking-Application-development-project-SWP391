
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="Model.Account"%>
<%@page import="Model.AdditionalService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
        <%
        List<AdditionalService> list = new ArrayList<>();
        if(request.getAttribute("list") != null){
            list = (List<AdditionalService>) request.getAttribute("list");
        }
        %>
        <div class="header_fixed">
            <header class="header">
                <div class="flex">

                    <a href="DashboardServlet" class="logo">Admin<span>Panel</span></a>

                    <nav class="navbar">
                        <a href="DashboardServlet"><span>Home</span></a>
                        <a href="ListHouseServlet">Room</a>
                        <a href="ListAccountServlet?page=1&search=">Users</a>
                        <a href="ListAddService?page=1&search=">Service</a>
  
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
                        

            <table style="width: 100px; margin: auto">
                <form action="ListAddService?page=1" class="search-bar-container  ml-3 my-53 "  method="GET"  >
                    <input style="height: 60px;font-size: 40px;" type="text" name="search" id="search-bar" placeholder="Search here..."">
                    <button class="fas fa-search" style="height: 60px; font-size: 40px; background-color: #fff" value="search" type="submit"></button>
                </form>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Service Name</th>
                        <th>Service Desc</th>
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
                            <a href="AddService.jsp">Add</a>
                        </span>
                    </td>
                </tr>
                <%
                }
                %>
            </table>
            <nav class="mx-5 mt-3" aria-label="Page navigation example">
                <ul class="pagination">
                    <c:forEach var="i" begin="1" end="${count}">
                        <li class="page-item"><a class="page-link" href="ListAddService?page=${i}&search=${search}">${i}</a></li>
                    </c:forEach>
                   
                   
                    
                </ul>
            </nav>
        </div>
    </body>
</html>
