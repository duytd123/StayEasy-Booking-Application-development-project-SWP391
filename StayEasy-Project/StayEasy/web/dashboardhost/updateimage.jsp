<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Edit</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="../css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            img{
                width: 200px;
                height: 120px;
            }

            select {
                width: 32.3%;
                margin: 0;
                font-size: 100%;
                padding: 5px 10px 5px 10px;
                font-family: Segoe UI, Helvetica, sans-serif;
                border: 1px solid #D0D0D0;
                -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;
                box-sizing: border-box;
                border-radius: 20px;
                outline: none;
            }
        </style>
    <body>
        <div class="container">
            <div class="table-wrapper">
                <div class="table-title">
                    <div class="row">
                        <div class="col-sm-6">
                            <h2>Edit <b>Image House</b></h2>
                        </div>
                        <div class="col-sm-6">
                          
                        </div>
                    </div>
                </div>
            </div>
            <div id="editHouseModal">
                <div class="modal-dialog" style="width: 100%">
                    <div class="modal-content">
                        <form action="editimage" method="post" enctype="multipart/form-data">
                            <div class="modal-body">
                                <div class="form-group">
                                    <div class="form-group" style="display: flex; align-items: center; justify-content: space-between">
                                        <label style="margin-right: 20px">Images</label>
                                        <div>
                                            <c:forEach var="img" items="${detail}">
                                                <div style="display: inline-block; position: relative;">
                                                    <img style="width: 200px; height: auto; margin-right: 10px;" src="${img.imglink}" alt="House Image">
                                                    <button type="button" class="btn btn-danger btn-sm" style="position: absolute; top: 0; right: 0;" onclick="deleteImage(${img.imgid})">Delete</button>
                                                </div>
                                            </c:forEach>
                                        </div>
                                        <input id="imageInput" name="image" type="file" multiple>
                                    </div>
                                    <div class="form-group">
                                        <label for="imageInput">Add new image:</label>
                                        <input id="imageInput" name="image" type="file" multiple>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="redirectToManager()">Close</button>
                                        <button type="submit" class="btn btn-primary">Save changes</button>
                                    </div>
                                </div>
                            </div>
                        </form>                          
                    </div>
                </div>
            </div>
        </div>

        <script>
            function deleteImage(imgId) {
                if (confirm('Are you sure you want to delete this image?')) {
                    window.location.href = 'deleteimage?imgId=' + imgId;
                }
            }

            function redirectToManager() {
                window.location.href = 'manager';
            }
        </script>
        <script src="js/main.js"></script>
        <script src="js/clickevents.js"></script>
        <script src="js/calender.js"></script>

    </body>
</html>
