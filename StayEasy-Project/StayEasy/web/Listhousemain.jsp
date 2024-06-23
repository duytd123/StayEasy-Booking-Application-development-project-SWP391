
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="getHouse" class="Dao.HouseImgDAO" />
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
                            <img src="${houseImg != null ? houseImg.get(0).imglink  : ""}" alt="" class="siImg" />
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

