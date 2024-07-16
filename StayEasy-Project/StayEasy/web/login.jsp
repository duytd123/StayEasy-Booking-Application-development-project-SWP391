
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <jsp:include page="layout/head.jsp"/>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link href="login_style.css" rel="stylesheet"/>
    <style>
        h1{
            font-family: Arial;
        }
        #rememberMeCheckbox {
            margin-right: 0px;
            width: 15px;
            height: 15px;
            cursor: pointer;
        }
        label {
            margin-top: 10px;
            margin-right: 180px;
            font-size: 15px;
            cursor: pointer;
        }
    </style>

    <body>
        <% String username = ""; String password = ""; Cookie[] cookies = request.getCookies(); if (cookies != null) { for (Cookie cookie: cookies) { if ((cookie.getName()).equals("cusername")) { username = cookie.getValue(); } else if ((cookie.getName()).equals("cpassword")) { password = cookie.getValue(); } } } %>
        <div class="back-to-home rounded d-none d-sm-block">
            <a href="home" class="btn btn-icon btn-primary"><i data-feather="home" class="icons"></i></a>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-5 mx-auto">
                    <div id="first">
                        <div class="myform form ">
                            <div class="logo mb-3">
                                <div class="col-md-12 text-center">
                                    <h1>Login</h1>
                                </div>
                            </div>
                            <form action="LoginServlet" method="POST" name="login" class="login-form mt-4" onSubmit="document.getElementById('submit').disabled = true;">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">User name <span class="text-danger">*</span></label>
                                    <input type="text" name="username" value="<%=username %>" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter User name" autocapitalize="none" autocomplete="off" required="">
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Password <span class="text-danger">*</span></label>
                                    <div class="input-group">
                                        <input type="password" name="password" value="<%=password %>" id="password" class="form-control" aria-describedby="emailHelp" placeholder="Enter Password" autocapitalize="none" autocomplete="off" required="">
                                        <div class="input-group-append">
                                            <span class="input-group-text">
                                                <i class="fa fa-eye-slash toggle-password" style="cursor: pointer;"></i>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="myformother">
                                    <input type="checkbox" ${(cookie.rem.value eq 'ON')?"checked":""} name="remember" id="rememberMeCheckbox" >
                                    <label for="rememberMeCheckbox">Remember me</label>

                                    <a style="color: black" href="forgot" class="switcher-text2">Forgot Password</a>
                                </div>
                                <p style="color: red; align-content: center;">
                                    ${mess}
                                </p>
                                <p style="color: blue; align-content: center;">
                                    ${requestScope.success}
                                </p>
                                <div class="col-md-12 text-center">
                                    <button class="btn btn-block mybtn btn-primary tx-tfm" id="submit">Login</button>
                                </div>
                                <div class="col-md-12">
                                    <div class="login-or">
                                        <hr class="hr-or">
                                        <span class="span-or">or</span>
                                    </div>
                                </div>
                                <div class="col-md-12 mb-3">
                                    <p class="text-center">
                                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email profile openid&redirect_uri=http://localhost:8080/HouseBookingSystem2_SWP391/LoginGoogleServlet&response_type=code
                                           &client_id=648783440514-mikfekirkbn1goptja9i44mj7mivcn01.apps.googleusercontent.com&approval_prompt=force" class="btn btn-lg btn-danger">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="10" height="10" fill="currentColor" class="bi bi-google" viewBox="0 0 10 10">
                                            <path d="M15.545 6.558a9.42 9.42 0 0 1 .139 1.626c0 2.434-.87 4.492-2.384 5.885h.002C11.978 15.292 10.158 16 8 16A8 8 0 1 1 8 0a7.689 7.689 0 0 1 5.352 2.082l-2.284 2.284A4.347 4.347 0 0 0 8 3.166c-2.087 0-3.86 1.408-4.492 3.304a4.792 4.792 0 0 0 0 3.063h.003c.635 1.893 2.405 3.301 4.492 3.301 1.078 0 2.004-.276 2.722-.764h-.003a3.702 3.702 0 0 0 1.599-2.431H8v-3.08h7.545z" />
                                            </svg>
                                            <span class="ms-2 fs-6">Sign in with Google</span>
                                        </a>
                                    </p>
                                </div>
                                <div class="form-group" id="d">
                                    <p class="text-center">Don't have account? <a href="SignUpServlet" id="signup">Sign up here</a></p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="assets/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/feather.min.js"></script>
        <script src="assets/js/app.js"></script>
        <script >
            <script>
    $(docu  ment).ready(function() {
                                        $(".toggle-password").click(function() {
                                $(this).toggleClass("fa-eye fa-eye-slash");
                                        var input = $($(this).parent().prev("input"));
                                        if (input.attr("type") === "password") {
                                input.attr("type", "text");
                                } else {
                                input.attr("type", "password");
                                }
                                });
            });
            </script>

    </script>
</body>

</html>
