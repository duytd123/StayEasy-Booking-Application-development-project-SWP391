<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
        <meta charset="UTF-8">
        <title>Host Bookings Timeline</title>
        <link rel="icon" href="Images/logo1.png"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet" type="text/css"/> 
        <link href="css/fullcalendar.min.css" rel="stylesheet" type="text/css"/> 
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb-pro.min.css">
        <link rel="stylesheet" href="https://mdbootstrap.com/previews/ecommerce-demo/css/mdb.ecommerce.min.css">
        <link rel="stylesheet" href="dashboardhost/newcss.css">
        <style>
            td img {
                width: 200px;
                height: 120px;
            }
            body {
                margin: 0;
                padding: 0;
            }

            .alert.alert-success {
                margin: 20px 10px;
            }
        </style>
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"><link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&amp;display=swap"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb5/3.8.1/compiled.min.css"><link rel="stylesheet" type="text/css" href="https://mdbootstrap.com/wp-content/themes/mdbootstrap4/css/mdb-plugins-gathered.min.css"><style>body {
                background-color: #fbfbfb;
            }
            @media (min-width: 991.98px) {
                main {
                    padding-left: 240px;
                }
            }

            /* Sidebar */
            .sidebar {
                position: fixed;
                top: 0;
                bottom: 0;
                left: 0;
                padding: 58px 0 0; /* Height of navbar */
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 5%), 0 2px 10px 0 rgb(0 0 0 / 5%);
                width: 240px;
                z-index: 600;
            }

            @media (max-width: 991.98px) {
                .sidebar {
                    width: 100%;
                }
            }
            .sidebar .active {
                border-radius: 5px;
                box-shadow: 0 2px 5px 0 rgb(0 0 0 / 16%), 0 2px 10px 0 rgb(0 0 0 / 12%);
            }
            .text_page_head{
                font-size: 18px;
                font-weight: 600;
            }
            .text_page {
                font-size: 14px;
                font-weight: 600;
            }
            .buttonadd{
                position: absolute;
                right: 100px;
                top: 30px;
            }
            .sidebar-sticky {
                position: relative;
                top: 0;
                height: calc(100vh - 48px);
                padding-top: 0.5rem;
                overflow-x: hidden;
                overflow-y: auto;
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
            .service-img {
                width: 100px;
                height: 100px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <header>
            <jsp:include page="leftadmin.jsp" />
        </header>
        <jsp:include page="header_right.jsp" />
        <main>
            <div class="container pt-4">
                <section class="mb-4">
                    <div class="card">
                        <div class="card-header">
                            <h2>Manage Additional Services</h2>
                        </div>
                        <div class="card-body">
                            <form action="additional1" method="get">
                                <div class="form-group">
                                    <label for="houseId">Select House</label>
                                    <select id="houseId" name="houseId" class="form-control" onchange="this.form.submit()">
                                        <c:forEach var="house" items="${houses}">
                                            <option value="${house.houseid}" <c:if test="${house.houseid == houseId}">selected</c:if>>
                                                ${house.housename}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </form>
                            <h2>Additional Services for Selected House</h2>
                            <form action="additional1" method="post">
                                <input type="hidden" name="houseId" value="${houseId}">
                                <div class="form-group">
                                    <label>Select Service to add</label>
                                    <select name="serviceId" class="form-control">
                                        <c:forEach var="service" items="${services}">
                                            <option value="${service.serviceid}">
                                                ${service.servicename} - ${service.servicedesc}
                                            </option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Add Service</button>
                            </form>
                            <h2>Current Services Assigned:</h2>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Image</th>
                                        <th>Service Name</th>
                                        <th>Description</th>
                                        <th>Price</th>
                                        <th>Status</th>
                                        <th>Set Price</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="houseService" items="${houseServices}">
                                        <c:set var="service" value="${services[houseService.serviceid]}" />
                                        <tr>
                                            <td>
                                                <img src="${service.imageUrl}" class="service-img" alt="${service.servicename}">
                                            </td>
                                            <td>${service.servicename}</td>
                                            <td>${service.servicedesc}</td>
                                            <td>${houseService.serviceprice}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${houseService.servicestatus == 1}">Active</c:when>
                                                    <c:otherwise>Inactive</c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <input type="number" class="form-control" id="price-${houseService.houseaddserviceid}" placeholder="Enter price">
                                                <button type="button" class="btn btn-secondary" onclick="setPrice(${houseService.houseaddserviceid})">Set Price</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </section>
            </div>
        </main>
        <script>
            function setPrice(houseAddServiceId) {
                var priceInput = document.getElementById("price-" + houseAddServiceId);
                var price = priceInput.value;

                if (price === "" || isNaN(price) || price < 0) {
                    alert("Please enter a valid price.");
                    return;
                }

                var xhr = new XMLHttpRequest();
                xhr.open("POST", "updatePrice", true);
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4) {
                        if (xhr.status === 200) {
                            alert("Price updated successfully.");
                            location.reload();
                        } else {
                            alert("Failed to update the price. Status: " + xhr.status);
                        }
                    }
                };
                xhr.send("houseAddServiceId=" + houseAddServiceId + "&price=" + price);
            }
        </script>
    </body>
</html>
