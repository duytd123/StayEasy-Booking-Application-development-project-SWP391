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
                            <h2>Edit <b>House</b></h2>
                        </div>
                        <div class="col-sm-6">
                        </div>
                    </div>
                </div>
            </div>
            <div id="editHouseModal">
                <div class="modal-dialog" style="width: 100%">
                    <div class="modal-content">
                        <form action="edithouse" method="post">
                            <div class="modal-body">
                                <div class="form-group">
                                    <label>ID</label>
                                    <input value="${detail.houseid}" name="id" type="text" class="form-control" readonly required>
                                </div>
                                <div class="form-group">
                                    <label>Name</label>
                                    <input value="${detail.housename}" name="name" type="text" class="form-control" required>
                                </div>

                                <div class="form-group" style="display: flex; align-items: center; justify-content: space-between">
                                    <label style="margin-right: 20px">Image</label>
                                    <div>
                                        <c:forEach var="img" items="${detail.images}">
                                            <img style="width: 200px; height: auto; margin-right: 10px;" src="${img}">
                                        </c:forEach>
                                    </div>
                                    <div></div>
                                </div>

                                <div class="form-group">
                                    <label>Price</label>
                                    <input value="${detail.houseprice}" name="price" type="number" step="0.01" min="0" class="form-control">
                                </div>
                                <div class="form-group">
                                    <label>Description</label>
                                    <textarea style="height: 200px" name="description" class="form-control" required>${detail.description}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>Address</label>
                                    <textarea name="address" class="form-control" required>${detail.address}</textarea>
                                </div>
                                <div class="form-group">
                                    <label>Release Date</label>
                                    <input name="date" type="text" value="${detail.postdate}" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Discount</label>
                                    <input name="discount" type="number" step="0.01" min="0" value="${detail.discount}" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Location</label>
                                    <select name="location" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listLoca}" var="o">
                                            <option value="${o.id}" ${o.id == detail.location.id ? 'selected' : ''}>${o.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Menu</label>
                                    <select name="menu" class="form-select" aria-label="Default select example">
                                        <c:forEach items="${listMenu}" var="l">
                                            <option value="${l.id}" ${l.id == detail.menu.id ? 'selected' : ''}>${l.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Number of Guests</label>
                                    <input name="numberOfGuest" type="number" min="1" value="${detail.number_of_guest}" class="form-control" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" onclick="redirectToManager()">Close</button>
                                <button type="submit" class="btn btn-primary">Save changes</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <script src="js/main.js"></script>
        <script src="js/clickevents.js"></script>
        <script src="js/calender.js"></script>
        <script  type="text/javascript">

                                      function redirectToManager() {
                                          window.location.href = 'manager';
                                      }

                                      function addOption(selectElement, value, text) {
                                          var option = document.createElement("option");
                                          option.value = value;
                                          option.text = text;
                                          selectElement.add(option);
                                      }

                                      var defaultReleaseDate = document.getElementById("stringdateolb").value;
                                      var defaultDateArray = defaultReleaseDate.split('-');
                                      var defaultDay = parseInt(defaultDateArray[2]);
                                      var defaultMonth = parseInt(defaultDateArray[1]);
                                      var defaultYear = parseInt(defaultDateArray[0]);

                                      var daysSelect = document.getElementById('dobDay');
                                      var monthsSelect = document.getElementById('dobMonth');
                                      var yearsSelect = document.getElementById('dobYear');

                                      for (var day = 1; day <= 31; day++) {
                                          addOption(daysSelect, day, day);
                                      }

                                      for (var month = 1; month <= 12; month++) {
                                          addOption(monthsSelect, month, month);
                                      }

                                      var currentYear = new Date().getFullYear();
                                      for (var year = currentYear; year >= 1900; year--) {
                                          addOption(yearsSelect, year, year);
                                      }

                                      daysSelect.value = defaultDay;
                                      monthsSelect.value = defaultMonth;
                                      yearsSelect.value = defaultYear;

        </script>
    </body>
</html>
