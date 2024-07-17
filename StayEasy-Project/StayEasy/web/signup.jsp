<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>

        <!-- Bootstrap CSS -->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


        <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" crossorigin="anonymous">


        <link rel="stylesheet" href="login_style.css">
        <script>

            function getUrlParameter(name) {
                name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
                var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
                var results = regex.exec(location.search);
                return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
            }

            $(document).ready(function () {
                var email = getUrlParameter('email');
                if (email) {
                    $('#email').val(email);
                }
            });

        </script>
        <style>
            h1{
                font-family: Arial;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-5 mx-auto">
                    <div class="myform form">
                        <div class="logo mb-3">
                            <div class="col-md-12 text-center">
                                <h1>Signup</h1>
                            </div>
                        </div>
                        <form action="SignUpServlet" method="post" name="registration">
                            <p name="mess" class="text-danger">${mess}</p>

                            <div class="form-group">
                                <label for="username">Username*</label>
                                <input type="text" name="username" value="${username}" class="form-control" id="username" placeholder="Enter username">
                            </div>

                            <div class="form-group">
                                <label for="password">Password*</label>
                                <input type="password" name="password" id="password" class="form-control" placeholder="Enter password">
                            </div>

                            <div class="form-group">
                                <label for="repassword">Repeat Password*</label>
                                <input type="password" name="repassword" id="repassword" class="form-control" placeholder="Enter repeat password">
                            </div>

                            <div class="form-group">
                                <label for="fullname">Full Name</label>
                                    <input type="text" name="fullname" value="${fullname}" class="form-control" id="fullname" placeholder="Enter full name">
                            </div>

                            <div class="form-group">
                                <label for="email">Email address*</label>
                                <input type="email" name="email" value="${email}" class="form-control" id="email" placeholder="Enter email">
                            </div>

                            <div class="form-group">
                                <label for="phone">Phone</label>
                                <input type="text" name="phone" value="${phone}" class="form-control" id="phone" placeholder="Enter phone number">
                            </div>

                            <div class="col-md-12 text-center mb-3">
                                <button type="submit" name="submit" class="btn btn-block mybtn btn-primary tx-tfm">SIGN UP</button>
                            </div>

                            <div class="col-md-12 text-center">
                                <p class="text-center"><a href="LoginServlet" id="signin">Already have an account?</a></p>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
