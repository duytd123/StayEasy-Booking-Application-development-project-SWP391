
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <!-- custom css file link  -->

    </head>
    <%@ include file="header.jsp" %>

    <body>     
        <div class="listContainer">
            <div class="listWrapper">
                <div class="listSearch">
                    <form action="search-house-main" method="get">
                        <h1 class="lsTitle">Search</h1>
                        <div class="lsItem">
                            <label for="">Destination</label>
                            <input name="whereTo" type="text" />
                        </div>
                        <div class="lsItem">
                            <label for="">Check-in Date</label>
                            <input name="arrivals" type="date" />
                        </div>
                        <div>
                            <button>Search</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="listResult">
            <c:if test="${list.size() > 0 }" >
                <c:forEach items="${list}" var="house" >
                    <div class="searchItem">
                        <img src="Images/ht.png" alt="" class="siImg" />
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
    </body>
</html>