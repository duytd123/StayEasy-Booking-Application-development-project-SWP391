
<%@page import="Model.HouseAdditionalService"%>
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
        List<HouseAdditionalService> list = new ArrayList<HouseAdditionalService>();
        if(request.getAttribute("List") != null){
            list = (List<HouseAdditionalService>) request.getAttribute("List");
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

                    <a href="DashboardServlet" class="logo">Admin<span>Panel</span></a>

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

            <table>
                <form action="SearchHouseServlet" class="search-bar-container"  method="post"  >
                    <input style="height: 60px;font-size: 40px;" type="text" name="search" id="search-bar" placeholder="Search here..."">
                    <button class="fas fa-search" style="height: 60px; font-size: 40px; background-color: #fff" value="search" type="submit"></button>
                </form>
                <thead>
                    <tr>
                        <th>House_Add_Service_ID</th>
                        <th>Add_Service_ID</th>
                        <th>House_ID</th>
                        <th>Add_Service_Status</th>
                        <th>Add_Service_Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <%
                    for(HouseAdditionalService h : list){
                %>
                <tr>
                    <td><%=h.getHouseaddserviceid() %></td>
                    <td><a href="getAdditionalServiceServlet?id=<%=h.getServiceid() %>"><%=h.getServiceid() %></a></td>
                    <td><%=h.getHouseid() %></td>
                    <td><%=h.getServicestatus() %></td>
                    <td><%=h.getServiceprice() %></td>
                    <td>
                        <span class="action_btn">
                            <a href="NextEditHouseAdditionalServiceServlet?id=<%=h.getHouseaddserviceid() %>">Update</a>
                            <a href="DeleteHouseAdditionalServiceServlet?id=<%=h.getHouseid() %>">Delete</a>
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
