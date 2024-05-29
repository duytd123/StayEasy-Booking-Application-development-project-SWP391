<%-- 
    Document   : UpdateAdditionalService
    Created on : Oct 27, 2022, 10:19:21 PM
    Author     : Admin
--%>
<%@page import="Model.HouseAdditionalService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.AdditionalService"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
        HouseAdditionalService h = new HouseAdditionalService();
            if (request.getAttribute("houseadditionalservice") != null) {
                h = (HouseAdditionalService) request.getAttribute("houseadditionalservice");
            }
            List<AdditionalService> list = new ArrayList<>();
            if (request.getAttribute("list") != null) {
                list = (List<AdditionalService>) request.getAttribute("list");
            }       
        %>
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
        <section class="add-products">
            <h1 class="title">Add Service</h1>

            <form action="EditHouseAdditionalServiceServlet" method="post" >
                <input
                    type="text"
                    name="houseaddserviceid"
                    value="<%=h.getHouseaddserviceid() %>"
                    class="box"
                    placeholder="Enter HouseAdditionalServiceID"
                    hidden="true"
                    />
                <h2>House_ID</h2>
                <input
                    type="text"
                    name="houseid"
                    value="<%=h.getHouseid() %>"
                    class="box"
                    placeholder="Enter HouseID"
                    required=""
                    />
                <h2>Add_Service_ID</h2>
                <select class="box"  name="serviceid">
                    <%
                       for(AdditionalService a : list){
                    %>
                    <option value="<%=a.getServiceid() %>" ><%=a.getServicename() %></option>
                    <%
                }
                    %>
                </select>
                <h2>Add_Service_Status</h2>
                <input
                    type="text"
                    name="status"
                    value="<%=h.getServicestatus() %>"
                    class="box"
                    placeholder="Enter Status"
                    required=""
                    />
                <h2>Add_Service_Price</h2>
                <input
                    type="number"
                    name="price"
                    value="<%=h.getServiceprice() %>"
                    class="box"
                    placeholder="Enter Price"
                    required=""
                    />

                <input class="Update-btn" type="submit" value="   Update   " name="Update Service" />
            </form>
        </section>
    </body>
    <script src="admin_script.js"></script>
</html>
