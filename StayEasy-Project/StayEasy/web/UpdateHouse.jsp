
<%@page import="Model.Menu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Location"%>
<%@page import="java.util.List"%>
<%@page import="Model.House"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">

        <link rel="stylesheet" href="admin_style.css">
        <!-- Boostrap 5 -->
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
        House h = new House();
            if (request.getAttribute("house") != null) {
                h = (House) request.getAttribute("house");
            }
        %>


        <section class="add-products">
            <h1 class="title">Update</h1>

            <form action="EditHouseServlet" method="post" >
                <input
                    type="text"
                    name="houseid"
                    value="<%=h.getHouseid() %>"
                    class="box"
                    placeholder="Enter Account ID"
                    hidden="true"
                    />
                <h2>Post Date</h2>
                <input
                    type="date"
                    name="postdate"
                    value="<%=h.getPostdate() %>"
                    class="box"
                    required=""
                    />
                <h2>House Name</h2>
                <input
                    type="text"
                    name="housename"
                    value="<%=h.getHousename() %>"
                    class="box"
                    placeholder="Enter Full Name"
                    required=""
                    />
                <input
                    type="text"
                    name="review"
                    value="<%=h.getReview() %>"
                    class="box"
                    placeholder="Enter Username"
                    hidden="true"
                    />
                <h2>House Price</h2>
                <input
                    type="number"
                    name="houseprice"
                    value="<%=h.getHouseprice() %>"
                    class="box"
                    placeholder="Enter House Price"
                    required=""
                    />
                <h2>Address</h2>
                <input
                    type="text"
                    name="address"
                    value="<%=h.getAddress() %>"
                    class="box"
                    placeholder="Enter phone number"
                    required=""
                    />
                <input
                    type="number"
                    name="status"
                    value="<%=h.getStatus() %>"
                    class="box"
                    placeholder="Enter status"
                    hidden="true"
                    />
                <h2>Description</h2>
                <input
                    type="text"
                    name="description"
                    value="<%=h.getDescription() %>"
                    class="box"
                    placeholder="Enter role"
                    />
                <h2>Location</h2>
                <select class="box"  name="location">
                    <%
                       for(Location l : llist){
                    %>
                    <option value="<%=l.getId() %>" ><%=l.getName() %></option>
                    <%
                }
                    %>
                </select>
                <h2>Menu</h2>
                <select class="box" name="menu">
                    <%
                        for(Menu m : mlist){
                    %>
                    <option value="<%=m.getId() %>" ><%=m.getName() %></option>
                    <%
                }
                    %>
                </select>



                <input class="Update-btn" type="submit" value="Update" name="Update" />
            </form>
        </section>

    </body>
    <!-- custom admin js file link  -->
    <script src="admin_script.js"></script>
</html>
