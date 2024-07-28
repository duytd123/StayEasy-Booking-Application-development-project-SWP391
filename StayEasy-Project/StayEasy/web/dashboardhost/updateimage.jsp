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
            img {
                width: 200px;
                height: 120px;
            }

            select {
                width: 32.3%;
                margin: 0;
                font-size: 100%;
                padding: 5px 10px;
                font-family: Segoe UI, Helvetica, sans-serif;
                border: 1px solid #D0D0D0;
                box-sizing: border-box;
                border-radius: 20px;
                outline: none;
            }
        </style>
    </head>
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
                            <input type="hidden" name="hid" value="${houseId}">
                            <div class="form-group">
                                <label>Update Images</label>
                                <div>
                                    <c:forEach var="img" items="${detail}">
                                        <div style="display: inline-block; position: relative;">
                                            <img style="width: 200px; height: 180px; margin-right: 10px;" src="${img.imglink}" alt="House Image">
                                            <button type="button" class="btn btn-danger btn-sm" style="position: absolute; top: 0; right: 0;" onclick="deleteImage(${img.imgid})">Delete</button>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="imageInput">Add new image:</label>
                                <input id="imageInput" name="image" type="file" required>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" onclick="redirectToManager()">Close</button>
                                <button type="submit" class="btn btn-primary">Save changes</button>
                            </div>
                        </form>


                    </div>
                </div>
            </div>
        </div>

        <script>
            function deleteImage(imgid) {
                if (confirm('Are you sure you want to delete this image?')) {
                    $.ajax({
                        url: 'editimage',
                        type: 'DELETE',
                        data: JSON.stringify({imgId: imgid}),
                        contentType: 'application/json; charset=utf-8',
                        success: function () {
                            location.reload();
                        },
                        error: function (xhr, status, error) {
                            alert('An error occurred while deleting the image: ' + xhr.responseText);
                        }
                    });
                }
            }

            function redirectToManager() {
                window.location.href = "manager";
            }
        </script>
    </body>
</html>
