<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <style>
            .table thead th, .table tbody td {
                white-space: nowrap;
            }
            .table-container {
                overflow-x: auto;
            }
            .table-container table {
                width: 100%;
                min-width: 1000px;
            }

        </style>
    </head>
    <body>
        <%
            List<Account> list = new ArrayList<Account>();
            if (request.getAttribute("AccountList") != null) {
                list = (List<Account>) request.getAttribute("AccountList");
            }
        %>       
        <div class="header_fixed">
            <header class="header">
                <div class="flex">
                    <a href="DashboardServlet" class="logo">Admin<span>Panel</span></a>
                    <nav class="navbar">
                        <a href="DashboardServlet"><span>Home</span></a>
                        <a href="ListHouseServlet">Room</a>
                        <a href="ListBillServlet">Orders</a>
                        <a href="ListAccountServlet?page=1&search=">Users</a>
                        <a href="ListAddService?page=1&search=">Service</a>
                        <a href="ListCommentServlet"></a>
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
                    <form action="ListAccountServlet" style="display: flex ; border: 1px solid #ccc; border-radius: 10px; width: 20vw;; padding: 0" class="mb-5">
                        <input type="hidden" name="page" value="1">
                        <input style="    width: 90%; border-radius: 10px; padding: 0 20px;" type="text" name="search" id="search-bar" placeholder="Search here...">
                        <button style="    width: 10%;
                                text-align: center;
                                margin: 0;" class=" btn btn-primary" value="search" type="submit">
                            <i class="fas fa-search pr-3" ></i>
                        </button>


                    </form>



                    <div class="mt-5 table-responsive table-container">
                        <c:if test="${err != null}">
                            <div class="alert alert-warning">${err}</div>
                        </c:if>
                        <table class="table  table-bordered">
                            <thead>
                                <tr>
                                    <th>ID</th>

                                    <th>Full Name</th>
                                    <th>Username</th>

                                    <th>Phone</th>
                                    <th>Status</th>
                                    <th>Role</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (Account a : list) {
                                %>
                                <tr>
                                    <td><%= a.getUserid() %></td>

                                    <td><%= a.getFullname() %></td>
                                    <td><%= a.getUsername() %></td>

                                    <td><%= a.getPhone() %></td>
                                    <td><%= (a.getStatus() == 1) ? "Active" : "inActive" %></td>
                                    <td><%= (a.getRole().getId() == 0) ? "Admin" : "User" %></td>
                                    <td>
                                        <span class="action_btn">
                                            <a href="AddAccount.jsp">Add</a>
                                            <a href="NextEditAccountServlet?id=<%= a.getUserid() %>">Update</a>
                                            <a href="DeleteAccountServlet?id=<%= a.getUserid() %>">Delete</a>

                                            <%
                                                if (a.getRole().getId() == 1 || a.getRole().getId()==2 ) {
                                            %>
                                            <form action="UpdateStatusAccountServlet" method="post">
                                                <input type="text" name="username" value="<%= a.getUsername() %>" hidden="true">
                                                <input type="number" name="status" value="<%= a.getStatus() %>" hidden="true">
                                                <input type="submit" value="Block/UnBlock" class="action_btn">
                                            </form>
                                            <%
                                                }
                                            %>
                                        </span>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                        <nav aria-label="Page navigation example">
                            <ul class="pagination">

                                <c:forEach begin="1" end="${count}" var="i" >
                                    <li class="page-item"><a class="page-link" href="ListAccountServlet?page=${i}&search=${search}">${i}</a></li>
                                    </c:forEach>




                            </ul>
                        </nav>
                    </div>
                </div>
            </section>
        </div>
    </body>
    <script src="admin_script.js"></script>
</html>
