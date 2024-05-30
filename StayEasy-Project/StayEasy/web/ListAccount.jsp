<%-- 
    Document   : newjsp
    Created on : Sep 30, 2022, 7:06:50 PM
    Author     : Admin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Account"%>
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
        List<Account> list = new ArrayList<Account>();
        if(request.getAttribute("AccountList") != null){
            list = (List<Account>) request.getAttribute("AccountList");
        }
        %>       
    <div class="header_fixed">
<!--        <h1>Manage Account</h1>S
        <form action="mainController" method="get">
            Name : <input type="text" name="name" >
            <input type="submit" value="search" name="action">
        </form>
      -->
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
                <section class="dashboard">
                    <table>
            
         <form action="SearchAccountServlet" class="search-bar-container"  method="post">
        <input style="height: 60px;font-size: 40px;" type="text" name="search" id="search-bar" placeholder="Search here..."">
        <button class="fas fa-search" style="height: 60px; font-size: 40px; background-color: #fff" value="search" type="submit"></button>
        </form>
            <thead>
                <tr>
                <th>ID</th>
                <th>Image</th>
                <th>Full Name</th>
                <th>Username</th>
                <th>Password</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Role</th>
                <th>Action</th>
                </tr>
            </thead>

            <%
                for(Account a : list){
                %>
                <tbody>
                    <tr>
                        <td><%=a.getUserid() %></td>
                        <td><%=a.getUserimg(        ) %></td>
                        <td><%=a.getFullname() %></td>
                        <td><%=a.getUsername() %></td>
                        <td><%=a.getPass() %></td>
                        <td><%=a.getPhone() %></td>
                        <%
                        if(a.getStatus() == 1){
                        %>
                        <td>Active</td>
                        <%
                            }
                        else{
                        %>
                        <td>inActive</td>
                        <%
                        }
                        %>
                        <%
                        if(a.getRole().getId() == 0){
                        %>
                        <td>Admin</td>
                        <%
                            }
                        else{
                        %>
                        <td>User</td>
                        <%
                        }
                        %>
                        <td>
                            <span class="action_btn">
                                <a href="NextEditAccountServlet?id=<%=a.getUserid() %>">Update</a>
                                <a href="DeleteAccountServlet?id=<%=a.getUserid() %>">Delete</a>
                                <a href="AddAccount.jsp">Add</a>
                                <%
                                if(a.getRole().getId() == 1){
                                %>
                                <form action="UpdateStatusAccountServlet" method="post">
                                    <input type="text" name="username" value="<%=a.getUsername() %>" hidden="true">
                                    <input type="number" name="status" value="<%=a.getStatus() %>" hidden="true">
                                    <input type="submit" value="Block/UnBlock" class="action_btn">
                                </form>
                                <%
                                    }
                                %>
                            </span>
                        </td>
                    </tr>
                </tbody>
                <%
                }
                %>
                
        </table>
    </div>
                </section>
        
       
    </body>
    <script src="admin_script.js"></script>
</html>