<%@page import="Model.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Role"%>
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
        Account a = new Account();
            if (request.getAttribute("account") != null) {
                a = (Account) request.getAttribute("account");
            }
        %>
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
        <section class="add-products">
            <h1 class="title">Update</h1>

            <form action="EditAccountServlet" method="post" >
                <!--        <h3>Update Account</h3>-->
                <input
                    type="text"
                    name="id"
                    value="<%=a.getUserid() %>"
                    class="box"
                    placeholder="Enter Account ID"
                    hidden="true"
                    />
                <input
                    type="file"
                    name="userimg"
                    value="<%=a.getUserimg() %>"
                    class="box"
                    hidden="true"
                    />
                <h2>Full Name</h2>
                <input
                    type="text"
                    name="fullname"
                    value="<%=a.getFullname() %>"
                    class="box"
                    placeholder="Enter Full Name"
                    />
                <h2>Username</h2>
                <input
                    type="text"
                    name="username"
                    value="<%=a.getUsername() %>"
                    class="box"
                    placeholder="Enter Username"
                    />
                <h2>Password</h2>
                <input
                    type="password"
                    name="password"
                    value="<%=a.getPass() %>"
                    class="box"
                    placeholder="Enter Password"
                    />
                <h2>Phone</h2>
                <input
                    type="number"
                    name="phone"
                    value="<%=a.getPhone() %>"
                    class="box"
                    placeholder="Enter phone number"
                    />
                <input
                    type="number"
                    name="status"
                    value="<%=a.getStatus() %>"
                    class="box"
                    placeholder="Enter status"
                    hidden="true"
                    />
                <input
                    type="number"
                    name="role"
                    value="<%=a.getRole().getId() %>"
                    class="box"
                    placeholder="Enter role"
                    hidden="true"
                    />
                <input class="Update-btn" type="submit" value="Update" name="Update" />
            </form>
        </section>

    </body>
    <script src="admin_script.js"></script>
</html>
