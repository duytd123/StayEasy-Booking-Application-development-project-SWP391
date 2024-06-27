<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Account"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--  All snippets are MIT license http://bootdey.com/license -->
        <title>bs5 edit profile account details - Bootdey.com</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>

    </head>
    <body>

        <%
        session = request.getSession();
        Account acc = (Account) session.getAttribute("acc");
        String userimg = null;
        if(acc.getUserimg() != null){
        if(acc.getUserimg().contains("https")){
             userimg = acc.getUserimg();
        }else{
            userimg = "Images/userimgs/" + acc.getUserimg();
        }
        }
        
        %>

        <div class="container-xl px-4 mt-4">

            <nav class="nav nav-borders">
                <a class="nav-link" href="home" target="">Home</a>
                <a class="nav-link active" href="UserServlet"  target="">Profile</a>
                <a class="nav-link" href="BillUserServlet"target="">Billing</a>
                <a class="nav-link" href="security.jsp" target="">Security</a>
            </nav>
            <hr class="mt-0 mb-4">
            <div class="row">
                <div class="col-xl-4">
                    <div class="card mb-4 mb-xl-0">
                        <div class="card-header">Profile Picture</div>
                        <form action="UserServlet" method="post" enctype="multipart/form-data">

                            <div class="card-body text-center">
                                <img class="img-account-profile rounded-circle mb-2"  src="<%=userimg%>" >

                                <div class="small font-italic text-muted mb-4">JPG or PNG no larger than 5 MB</div>

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

                                </main>
                                <button class="btn btn-primary" type="submit" name="sub" value="uploadPic">Upload</button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-xl-8">
                    <p class="text-danger">${mess}</p>
                    <div class="card mb-4">
                        <div class="card-header">Account Details</div>
                        <div class="card-body">
                            <form  action="UserServlet" method="post" >
                                <div class="mb-3">
                                    <label class="small mb-1" for="inputFullname">Full Name (how your name will appear to other users on the site)</label>
                                    <input class="form-control" name="fullname" id="fullname" type="text" placeholder="Enter your username" value="${acc.fullname}">
                                </div>

                                <div class="mb-3">
                                    <label class="small mb-1" for="inputEmailAddress">Email address</label>
                                    <input class="form-control" name="email" id="email" type="email" placeholder="Enter your email address" value="${acc.email}">
                                </div>

                                <div class="row gx-3 mb-3">
                                    <div class="col-md-6">
                                        <label class="small mb-1" for="inputPhone">Phone number</label>
                                        <input class="form-control" name="phone" id="phone" type="text" placeholder="Enter your phone number" value="${acc.phone}">
                                    </div>
                                </div>

                                <a onclick="showConFirmModal()" class="btn btn-primary" type="button" id="sub" name="sub" value="save">Save changes</a>
                            </form>
                        </div>
                    </div>
                </div>
                <script>
                    function showConFirmModal() {
                        const fullname = document.getElementById('fullname').value;
                        const email = document.getElementById('email').value;
                        const phone = document.getElementById('phone').value;
                        $('#yesOption').attr('href', 'UserServlet?sub=save&fullname=' + fullname + '&email=' + email + '&phone=' + phone);
                        $('#confirmationID').modal('show');
                    }
                </script>   

                <!-- Modal Notice -->
                <div class="modal fade" id="confirmationID" role="dialog" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">Confirmation</h5>
                            </div>

                            <div class="modal-body">
                                Do you want to Save Change?
                            </div>

                            <div class="modal-footer">
                                <a id="yesOption" type="button" class="btn btn-primary" >Yes</a>
                                <button type="button" class="btn btn-default" data-dismiss="modal" >Close</button>
                            </div>

                        </div>
                    </div>
                </div>

            </div>
        </div>

        <style type="text/css">
            body{
                margin-top:20px;
                background-color:#f2f6fc;
                color:#69707a;
            }
            .img-account-profile {
                height: 10rem;
            }
            .rounded-circle {
                border-radius: 50% !important;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
            .card .card-header {
                font-weight: 500;
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }
            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                background: #f6f6f6;
                color: #444;
                font-family: 'Roboto', sans-serif;
                font-size: 16px;
                line-height: 1;
            }
            .container {
                max-width: 1100px;
                padding: 0 20px;
                margin:0 auto;
            }
            .panel {
                margin: 100px auto 40px;
                max-width: 500px;
                text-align: center;
            }
            .button_outer {
                background: #83ccd3;
                border-radius:30px;
                text-align: center;
                height: 50px;
                width: 200px;
                display: inline-block;
                transition: .2s;
                position: relative;
                overflow: hidden;
            }
            .btn_upload {
                padding: 17px 30px 12px;
                color: #fff;
                text-align: center;
                position: relative;
                display: inline-block;
                overflow: hidden;
                z-index: 3;
                white-space: nowrap;
            }
            .btn_upload input {
                position: absolute;
                width: 100%;
                left: 0;
                top: 0;
                width: 100%;
                height: 105%;
                cursor: pointer;
                opacity: 0;
            }
            .file_uploading {
                width: 100%;
                height: 10px;
                margin-top: 20px;
                background: #ccc;
            }
            .file_uploading .btn_upload {
                display: none;
            }
            .processing_bar {
                position: absolute;
                left: 0;
                top: 0;
                width: 0;
                height: 100%;
                border-radius: 30px;
                background:#83ccd3;
                transition: 3s;
            }
            .file_uploading .processing_bar {
                width: 100%;
            }
            .success_box {
                display: none;
                width: 50px;
                height: 50px;
                position: relative;
            }
            .success_box:before {
                content: '';
                display: block;
                width: 9px;
                height: 18px;
                border-bottom: 6px solid #fff;
                border-right: 6px solid #fff;
                -webkit-transform:rotate(45deg);
                -moz-transform:rotate(45deg);
                -ms-transform:rotate(45deg);
                transform:rotate(45deg);
                position: absolute;
                left: 17px;
                top: 10px;
            }
            .file_uploaded .success_box {
                display: inline-block;
            }
            .file_uploaded {
                margin-top: 0;
                width: 50px;
                background:#83ccd3;
                height: 50px;
            }
            .uploaded_file_view {
                max-width: 300px;
                margin: 40px auto;
                text-align: center;
                position: relative;
                transition: .2s;
                opacity: 0;
                border: 2px solid #ddd;
                padding: 15px;
            }
            .file_remove{
                width: 30px;
                height: 30px;
                border-radius: 50%;
                display: block;
                position: absolute;
                background: #aaa;
                line-height: 30px;
                color: #fff;
                font-size: 12px;
                cursor: pointer;
                right: -15px;
                top: -15px;
            }
            .file_remove:hover {
                background: #222;
                transition: .2s;
            }
            .uploaded_file_view img {
                max-width: 100%;
            }
            .uploaded_file_view.show {
                opacity: 1;
            }
            .error_msg {
                text-align: center;
                color: #f00
            }
        </style>

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
    </body>
</html>