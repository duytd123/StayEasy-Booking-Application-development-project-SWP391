
<%@page import="Model.HouseImg"%>
<%@page import="Model.House"%>
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
        HouseImg hi = new HouseImg();
            if (request.getAttribute("houseimg") != null) {
                hi = (HouseImg) request.getAttribute("houseimg");
            }
        House h = new House();
            if (request.getAttribute("house") != null) {
                h = (House) request.getAttribute("house");
            }
            <script type="text/javascript">
            var btnUpload = $("#upload_file"),
                    btnOuter = $(".button_outer");
            btnUpload.on("change", function (e) {
                var ext = btnUpload.val().split('.').pop().toLowerCase();
                if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                    $(".error_msg").text("Not an Image...");
                } else {
                    $(".error_msg").text("");
                    btnOuter.addClass("file_uploading");
                    setTimeout(function () {
                        btnOuter.addClass("file_uploaded");
                    }, 3000);
                    var uploadedFile = URL.createObjectURL(e.target.files[0]);
                    setTimeout(function () {
                        $("#uploaded_view").append('<img src="' + uploadedFile + '" />').addClass("show");
                    }, 3500);
                }
            });
            $(".file_remove").on("click", function (e) {
                $("#uploaded_view").removeClass("show");
                $("#uploaded_view").find("img").remove();
                btnOuter.removeClass("file_uploading");
                btnOuter.removeClass("file_uploaded");
            });
        </script>
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
            <h1 class="title">Update</h1>

            <form action="EditHouseImgServlet" method="post" >
                <input
                    type="text"
                    name="imgid"
                    value="<%=hi.getImgid() %>"
                    class="box"
                    hidden="true"
                    />
                <form action="UserServlet" method="post" enctype="multipart/form-data">

                    <div class="card-body text-center">
                        <!-- Profile picture image-->
                        <img class="img-account-profile rounded-circle mb-2"  src="<%=userimg%>" >

                        <!-- Profile picture help block-->
                        <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>
                        <!-- Profile picture upload button--> 

                        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
                        <main class="main_full">
                            <div class="container">
                                <div class="panel">
                                    <div class="button_outer">
                                        <div class="btn_upload">
                                            <input type="file" id="upload_file" name="userimage" size="50">
                                            Upload Image
                                        </div>
                                        <div class="processing_bar"></div>
                                        <div class="success_box"></div>
                                    </div>
                                </div>
                                <div class="error_msg"></div>
                                <div class="uploaded_file_view" id="uploaded_view">
                                    <span class="file_remove">X</span>
                                </div>
                            </div>
                            <h2>House ID</h2>
                            <input
                                type="text"
                                name="houseid"
                                value="<%=hi.getHouseid() %>"
                                class="box"
                                placeholder="Enter Full Name"
                                required=""
                                />

                            <input class="Update-btn" type="submit" value="Update" name="Update" />
                            </form>
                            </section>
                            </body>
                            <!-- custom admin js file link  -->
                            <script src="admin_script.js"></script>
                            <script type="text/javascript">
                                var btnUpload = $("#upload_file"),
                                        btnOuter = $(".button_outer");
                                btnUpload.on("change", function (e) {
                                    var ext = btnUpload.val().split('.').pop().toLowerCase();
                                    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
                                        $(".error_msg").text("Not an Image...");
                                    } else {
                                        $(".error_msg").text("");
                                        btnOuter.addClass("file_uploading");
                                        setTimeout(function () {
                                            btnOuter.addClass("file_uploaded");
                                        }, 3000);
                                        var uploadedFile = URL.createObjectURL(e.target.files[0]);
                                        setTimeout(function () {
                                            $("#uploaded_view").append('<img src="' + uploadedFile + '" />').addClass("show");
                                        }, 3500);
                                    }
                                });
                                $(".file_remove").on("click", function (e) {
                                    $("#uploaded_view").removeClass("show");
                                    $("#uploaded_view").find("img").remove();
                                    btnOuter.removeClass("file_uploading");
                                    btnOuter.removeClass("file_uploaded");
                                });
                            </script>
                            </html>
