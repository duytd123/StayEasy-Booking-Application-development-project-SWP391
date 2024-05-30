<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html class="no-js" lang="">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Forgot</title>
        <link rel="icon" href="Images/logo1.png"/>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="style_1.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="assets/css/dist/css/bootstrap.css">
    </head>

    <body>
        <section class="fxt-template-animation fxt-template-layout1">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-6 col-12 fxt-bg-color">
                        <div class="fxt-content">
                            <div class="fxt-header">
                                <a href="home"class="fxt-logo"><div>Home</div></a>
                                <div class="fxt-page-switcher">
                                    <a href="LoginServlet" class="switcher-text1">Log In</a>
                                    <a href="SignUpServlet" class="switcher-text1">Register</a>
                                </div>
                            </div>
                            <div class="fxt-form">
                                <h2 style="color: red">Forgot Password</h2>
                                <c:if test="${requestScope.check == null}">
                                    <p>Enter your email to recover your password</p>
                                </c:if>
                                <c:if test="${requestScope.check != null}">
                                    <c:if test="${requestScope.check == 'true' && !(requestScope.message == 'Sorry, reset code incorrect')}">
                                        <p style="color: red">${requestScope.message}</p>
                                    </c:if>
                                    <c:if test="${requestScope.check == 'false'}">
                                        <p style="color: red">${requestScope.message}</p>
                                    </c:if>
                                    <c:if test="${requestScope.check == 'true' && requestScope.message == 'Sorry, reset code incorrect'}">
                                        <p style="color: red">${requestScope.message}</p>
                                    </c:if>
                                </c:if>
                                <form action="forgot" method="post">
                                    <c:if test="${requestScope.check == null || requestScope.check == 'false'}">
                                        <div class="form-group">
                                            <div class="fxt-transformY-50 fxt-transition-delay-1">
                                                <input type="email" class="form-control" name="email" placeholder="Email Address" required="required" value="${requestScope.email}">
                                                <i class="flaticon-envelope"></i>
                                            </div>
                                        </div>
                                    </c:if>
                                    <c:if test="${requestScope.check == null || requestScope.check == 'false'}">
                                        <div class="form-group">
                                            <div class="fxt-transformY-50 fxt-transition-delay-2">
                                                <button type="submit" class="fxt-btn-fill">Send Me Email</button>
                                            </div>
                                        </div>
                                    </c:if>
                                </form>

                                <c:if test="${requestScope.check != null && requestScope.check == 'true'}">
                                    <form action="confirmresetcode" method="post">
                                        <input name="email" value="${requestScope.email}" type="hidden">
                                        <div class="form-group">
                                            <div class="fxt-transformY-50 fxt-transition-delay-1">
                                                <input type="text" class="form-control" name="resetcode" placeholder="xxxxxx" required="required" value="${requestScope.code}">
                                                <i class="flaticon-envelope"></i>
                                            </div>
                                        </div>
                                        <c:if test="${requestScope.check != null && requestScope.check == 'true'}">
                                            <div class="form-group">
                                                <div class="fxt-transformY-50 fxt-transition-delay-2">
                                                    <button type="submit" class="fxt-btn-fill">Confirm Reset Code</button>
                                                </div>
                                            </div>
                                        </c:if>
                                    </form>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-12 fxt-none-767 fxt-bg-img" data-bg-image="Images/aa.jpg"></div>
                </div>
            </div>
        </section>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/imagesloaded.pkgd.min.js"></script>
        <script src="js/validator.min.js"></script>
        <script src="js/main_1.js"></script>
    </body>
</html>

