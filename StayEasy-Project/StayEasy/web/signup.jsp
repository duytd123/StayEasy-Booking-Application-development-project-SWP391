<%-- 
    Document   : Register
    Created on : Oct 1, 2022, 1:35:40 AM
    Author     : Admin
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
        <title>JSP Page</title>

        <!-- font awesome cdn link  -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <!------ Include the above in your HEAD tag ---------->

        <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
        <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="login_style.css">
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-5 mx-auto">
                    <div class="myform form ">
                        <div class="logo mb-3">
                            <div class="col-md-12 text-center">
                                <h1 >Signup</h1>
                            </div>
                        </div>
                        <form action="SignUpServlet" method="post" name="registration">
                            <!--mess-->
                            <p class="text-danger">${mess}</p>
                            <div class="form-group">
                                <label for="exampleInputEmail1">User name*</label>
                                <input type="text"  name="username" value="${username}" class="form-control" id="username" aria-describedby="emailHelp" placeholder="Enter username">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Password*</label>
                                <input type="password" name="password" id="password"  class="form-control" aria-describedby="emailHelp" placeholder="Enter Password">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Repeat Password*</label>
                                <input type="password" name="repassword" id="repassword"  class="form-control" aria-describedby="emailHelp" placeholder="Enter Repeat Password">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Full Name</label>
                                <input type="text"  name="fullname" value="${fullname}" class="form-control" id="fullname" aria-describedby="emailHelp" placeholder="Enter Full name">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Email address*</label>
                                <input type="email" name="email" value="${email}" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Phone</label>
                                <input type="text" name="phone" value="${phone}" class="form-control" id="phone" aria-describedby="emailHelp" placeholder="Phone">
                            </div>

                            <div class="col-md-12 text-center mb-3">
                                <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">SIGN UP</button>
                            </div>
                            <div class="col-md-12 ">
                                <div class="form-group">
                                    <p class="text-center"><a href="login.jsp" id="signin">Already have an account?</a></p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>  

</body>
</html>
