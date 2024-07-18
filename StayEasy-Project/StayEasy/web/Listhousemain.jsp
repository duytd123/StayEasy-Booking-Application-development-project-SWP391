
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="getHouse" class="Dao.HouseImgDAO" />
<jsp:useBean id="getHouseIs" class="Dao.HouseDAO" />
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Book Your Stay</title>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="housepage.css">
        <link href="stylesheet" rel="stylesheet">
        <link rel="stylesheet" href="list.css">
        <link rel="stylesheet" href="housepage.css">
        <link rel="stylesheet" href="css/list_house_main.css">
        <link rel="stylesheet" href="assets/css/style.min.css">
        <link rel="stylesheet" href="assets/css/dist/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/dist/css/bootstrap_1.css">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" href="user.jsp">
        <link rel="stylesheet" href="list_house_main.css">
        <link rel="stylesheet" href="StyleSheet.css">
        <link rel="stylesheet" href="css/housepage.css">

        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <style>
            .searchItem {
                display: flex;
                border: 1px solid #ddd;
                border-radius: 8px;
                margin-bottom: 20px;
                overflow: hidden;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }

            .siImg {
                width: 250px;
                height: 150px;
                object-fit: cover;
            }

            .siDesc {
                padding: 15px;
                flex: 1;
            }

            .siTitle {
                font-size: 1.5em;
                margin: 0 0 10px 0;
            }

            .siDistance, .siTaxi0p, .siCancel0p, .siCancel0pSubtitle {
                display: block;
                margin-bottom: 10px;
            }

            .siDetailTexts {
                padding: 15px;
                text-align: center;
            }

            .siPrice {
                font-size: 1.25em;
                font-weight: bold;
            }

            .siRating {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
            }

            .siRating button {
                background-color: #ffd700;
                border: none;
                padding: 5px 10px;
                color: #fff;
                font-size: 1em;
                border-radius: 5px;
            }

            .siCheckButton {
                background-color: #007bff;
                color: white;
                border: none;
                padding: 10px 20px;
                text-decoration: none;
                border-radius: 5px;
                font-size: 1em;
                cursor: pointer;
                display: inline-block;
            }

            .siCheckButton:hover {
                background-color: #0056b3;
            }

            .outOfStock {
                background-color: #ffdddd;
                border: 1px solid #ff6666;
                color: #d8000c;
                padding: 5px;
                border-radius: 5px;
                font-weight: bold;
                text-align: center;
            }

            .col-2 {
                background-color: #f9f9f9;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            }

            .filter-group {
                margin-bottom: 20px;
            }

            .filter-group h3 {
                font-size: 1.2em;
                color: #333;
                margin-bottom: 10px;
            }

            .location-filters, ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .location-filters li, ul li {
                margin-bottom: 10px;
            }

            .location-filters label, ul label {
                margin-left: 5px;
            }

            .location-filters input[type="radio"], ul input[type="radio"] {
                display: none;
            }

            .location-filters label, ul label {
                cursor: pointer;
                padding: 8px 12px;
                border-radius: 5px;
                border: 1px solid #ccc;
                transition: background-color 0.3s, color 0.3s, border-color 0.3s;
            }

            .location-filters label:hover, ul label:hover {
                background-color: #007bff;
                color: #fff;
                border-color: #007bff;
            }

            .location-filters input[type="radio"]:checked + label, ul input[type="radio"]:checked + label {
                background-color: #007bff;
                color: #fff;
                border-color: #007bff;
            }

            .type-filters {
                margin-top: 20px;
            }

            .type-filters div {
                font-size: 1.2em;
                color: #333;
                margin-bottom: 10px;
            }

            .type-filters ul {
                list-style: none;
                padding: 0;
                margin: 0;
            }

            .type-filters li {
                margin-bottom: 10px;
            }

            .type-filters label {
                cursor: pointer;
                padding: 8px 12px;
                border-radius: 5px;
                border: 1px solid #ccc;
                transition: background-color 0.3s, color 0.3s, border-color 0.3s;
            }

            .type-filters label:hover {
                background-color: #007bff;
                color: #fff;
                border-color: #007bff;
            }

            .type-filters input[type="radio"]:checked + label {
                background-color: #007bff;
                color: #fff;
                border-color: #007bff;
            }
        </style>
    </head>
    <header>
        <%@ include file="header.jsp" %>
    </header>
    <body style="padding-top:100px">
        <div> <%@ include file="bookNowSection.jsp" %></div>
        <div class="d-flex">
            <div class="col-2">
                <form acion="ListHouse"method="Post" >
                    <div class="filter-group">
                        <h3>Location</h3>
                        <ul class="location-filters">
                            <li>
                                <input ${currentLocation == null || currentLocation == -1 ? "checked" : "" } onchange="submitForm(event)" type="radio" name="selectedLocations" value="-1" id="location-1">
                                <label for="location-1">All</label>
                            </li>
                            <c:forEach items="${locations}" var="loca">
                                <li>
                                    <input ${currentLocation == loca.id ? "checked" : "" } onchange="submitForm(event)" type="radio" name="selectedLocations" value="${loca.id}" id="location-${loca.id}">
                                    <label for="location-${loca.id}">${loca.name}</label>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div>
                        <div>Type</div>
                        <ul>
                            <li>
                                <input ${currentMenu == null || currentMenu == -1 ? "checked" : "" } onchange="submitForm(event)" type="radio" name="selectType" value="-1" id="type-1">
                                <label for="type-${type.id}">All</label>
                            <li>
                                <c:forEach var="type" items="${menus}">
                                <li>
                                    <input ${currentMenu == type.id ? "checked" : "" } onchange="submitForm(event)" type="radio" name="selectType" value="${type.id}" id="${type.id}">
                                    <label for="type-${type.id}">${type.name}</label>
                                <li>
                            </c:forEach></ul>
                    </div>
                    <div></div>
                </form>
            </div>
            <div class="listResult col-8">
                <c:if test="${list.size() > 0 }" >
                    <c:forEach items="${list}" var="house" >
                        <c:set value="${getHouse.getHouseImgbyID(house.houseid)}" var="houseImg"/>
                        <div class="searchItem">
                            <img src="${houseImg != null && houseImg.size() > 0 ? houseImg.get(0).imglink  : ""}" alt="" class="siImg" />
                            <div class="siDesc">
                                <h1 class="siTitle">${house.housename}</h1>
                                <span class="siDistance"> </span>
                                <span class="siTaxi0p"></span>
                                <span class="siSubtitle"> ${house.houseprice} </span>
                                <span class="siFeatures">

                                </span>
                                <span class="siCancel0p"> ${house.description}</span>
                                <span class="siCancel0p"> ${house.address}</span>
                                <span class="siCancel0p">  Check-in Date : ${house.postdate}</span>

                                <span class="siCancel0pSubtitle">
                                    ${house.description}
                                </span>
                                <c:if test="${house.rentHouse  == true}">
                                    <div class="outOfStock">Out of Stock</div>
                                </c:if>
                            </div>
                            <div class="siDetails">
                                <div class="siRating">
                                    <span></span>
                                    <button>8.9</button>
                                </div>
                                <div class="siDetailTexts">
                                    <span class="siPrice"></span>
                                    <span class="siTax0p">Includes taxes and fees</span>
                                    <a href="housepage?houseId=${house.houseid}" >  <button class="siCheckButton">See availability</button></a>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${list.size() == 0 }" >
                    No Data
                </c:if>

            </div>

            <div class="col-2"></div>
        </div>
        <script>
            window.onload = function () {
                var today = new Date().toISOString().split('T')[0];
                var arrivals = document.querySelector('input[name="arrivals"]');
                var leaving = document.querySelector('input[name="leaving"]');
                var guests = document.querySelector('input[name="guests"]');
                var form = document.getElementById('bookingForm');

                arrivals.setAttribute('min', today);
                leaving.setAttribute('min', today);

                arrivals.addEventListener('change', function () {
                    var arrivalDate = this.value;
                    leaving.setAttribute('min', arrivalDate);
                });

                form.addEventListener('submit', function (event) {
                    var guestsValue = parseInt(guests.value);
                    if (guestsValue <= 0) {
                        event.preventDefault();

                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Number of guests must be greater than 0!',
                        });
                    }
                });
            }
        </script>
        <script>
            function submitForm(event) {
                event.preventDefault();

                var formData = {
                    'whereTo': encodeURIComponent(document.querySelector('input[name=whereTo]').value),
                    'guests': document.querySelector('input[name=guests]').value,
                    'arrivals': document.querySelector('input[name=arrivals]').value,
                    'leaving': document.querySelector('input[name=leaving]').value,
                    'location': [],
                    'menu': []
                };

                document.querySelectorAll('input[name="selectedLocations"]:checked').forEach(function (el) {
                    formData.location.push(el.value);
                });

                document.querySelectorAll('input[name="selectType"]:checked').forEach(function (el) {
                    formData.menu.push(el.value);
                });

                var params = new URLSearchParams(formData).toString();
                let SearchRoomServlet = "SearchRoomServlet" + "?" + params;

                var xhr = new XMLHttpRequest();
                xhr.open('GET', SearchRoomServlet, true);
                xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xhr.onreadystatechange = function () {
                    if (xhr.readyState == 4 && xhr.status == 200) {
                        var response = xhr.responseText;
                        window.location.href = 'search-house-main?' + response;
                    }
                };
                xhr.send();
            }

            document.querySelectorAll('input[type="checkbox"]').forEach(function (checkbox) {
                checkbox.addEventListener('change', submitForm);
            });
        </script>

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    </body>
</html>



