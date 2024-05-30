<%@page import="Model.House"%>
<%@page import="Model.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Location"%>
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
        <!-- Bootstrap 5 -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>  

        <!-- jquery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </head>
    <body>
        <%
            List<Location> llist = new ArrayList<Location>();
            if (request.getAttribute("llist") != null) {
                llist = (List<Location>) request.getAttribute("llist");
            }
            List<Menu> mlist = new ArrayList<Menu>();
            if (request.getAttribute("mlist") != null) {
                mlist = (List<Menu>) request.getAttribute("mlist");
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
            <h1 class="title">Add House</h1>
            <form action="AddHouseServlet" method="post" enctype="multipart/form-data">
                <h2>Post Date</h2>
                <input type="date" name="postdate" class="box" required />

                <h2>House Name</h2>
                <input type="text" name="housename" class="box" placeholder="Enter House Name" required />

                <!-- Remove review field if not needed -->
                <!-- <input type="text" name="review" class="box" placeholder="Enter Review" hidden /> -->

                <h2>House Price</h2>
                <input type="number" name="houseprice" class="box" placeholder="Enter Houseprice" required />

                <h2>Address</h2>
                <input type="text" name="address" class="box" placeholder="Enter Address" required />

                <input type="number" name="status" value="<%= h.getStatus() %>" class="box" hidden />

                <h2>Description</h2>
                <input type="text" name="description" class="box" placeholder="Enter role" />

                <h2>Location</h2>
                <select class="box" name="location">
                    <%
                    for (Location l : llist) {
                    %>
                    <option value="<%= l.getId() %>"><%= l.getName() %></option>
                    <%
                    }
                    %>
                </select>

                <h2>Menu</h2>
                <select class="box" name="menu">
                    <%
                    for (Menu m : mlist) {
                    %>
                    <option value="<%= m.getId() %>"><%= m.getName() %></option>
                    <%
                    }
                    %>
                </select>

                <h2>Upload Image</h2>
                <div class="card-body text-center">
                    <!-- Profile picture image-->
                    <!-- Define a default image path here or use userimg variable -->
                    <img class="img-account-profile rounded-circle mb-2" src="path/to/default-image.png" alt="House Image" />
                    <div class="small font-italic text-muted mb-4"><a>JPG or PNG no larger than 5 MB</a><!-- comment -->
                        <div class="btn_upload">
                            <input type="file" id="upload_file" name="userimage" size="50">
                            Upload Image
                        </div>
                    </div>

                    <!-- Remove the houseid field if relying on auto-increment for IDs -->
                    <!-- <h2>House ID</h2> -->
                    <!-- <input type="text" name="houseid" value="<%= h.getHouseid() + 1 %>" class="box" placeholder="Enter HouseId" /> -->

                    <input class="Update-btn" type="submit" value="Add" name="Add House" />
            </form>
        </section>
    </body>
    <script src="admin_script.js"></script>
</html>

