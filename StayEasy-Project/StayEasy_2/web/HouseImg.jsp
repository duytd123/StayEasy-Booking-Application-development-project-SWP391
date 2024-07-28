
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.HouseImg"%>
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
        List<HouseImg> list = new ArrayList<>();
        if(request.getAttribute("hid") != null){
            list = (List<HouseImg>) request.getAttribute("hid");
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

                    <a href="home" class="logo">Admin<span>Panel</span></a>

                    <nav class="navbar">
                        <a href="home"><span>Home</span></a>
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
                <thead>
                    <tr>
                        <th>Img_ID</th>
                        <th>Img_Link</th>
                        <th>House_ID</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <%
                for(HouseImg h : list){
                %>
                <tr>
                    <td><%=h.getImgid() %></td>
                    <td><%=h.getImglink() %></td>
                    <td><%=h.getHouseid() %></td>
                    <td>
                        <span class="action_btn">
                            <a href="NextEditHouseImgServlet?id=<%=h.getHouseid() %>">Update</a>
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