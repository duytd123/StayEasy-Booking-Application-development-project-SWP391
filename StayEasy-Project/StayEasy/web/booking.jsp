<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House Booking</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
        <style>
            body {
                margin-top: 50px;
            }
            .container {
                display: flex;
                justify-content: center;
            }
            .house-details {
                margin: 10px;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                background-color: #fff;
                flex: 3;
            }
            .booking-form {
                margin: 10px;
                padding: 20px;
                border: 1px solid #ddd;
                border-radius: 8px;
                background-color: #fff;
                flex: 7;
            }
            .form-group {
                margin-bottom: 1rem;
            }
        </style>
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <style>
            .form-control {
                padding: 15px;
                font-size: 18px;
            }
        </style>
        <div class="container" style="margin-top: 100px;">
            <div class="house-details">
                <h1 class="display-4 text-dark">${house.housename}</h1>
                <p class="lead"><i class="fas fa-map-marker-alt"></i> ${house.address}</p>
                <p>Check-in Date: ${house.postdate}</p>
                <p class="text-primary font-weight-bold">${house.houseprice} USD</p>
                <div class="hotel-images mb-4">
                    <c:forEach items="${listImage}" var="image">
                        <img src="${image.imglink}" alt="House Image" class="img-fluid mb-2" style="width: 100%; height: auto;" />
                    </c:forEach>
                </div>
                <h2>Description</h2>
                <p>${house.description}</p>
                <h2>Location</h2>
                <p>${house.location.name}</p>
                <h2>Type</h2>
                <p>${house.menu.name}</p>
            </div>

            <div class="booking-form">
                <h2>Book This House</h2>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">${param.error}</div>
                </c:if>
                <form action="booking" method="post" id="bookingForm">
                    <input type="hidden" name="houseid" value="${house.houseid}" />
                    <input type="hidden" name="housePrice" value="${house.houseprice}" />
                    <div class="form-group">
                        <label for="fullname">Full Name:</label>
                        <input value="${account.fullname}" type="text" id="fullname" name="fullname" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input value="${account.phone}" type="tel" id="phone" name="phone" class="form-control" pattern="[0-9]{10}" required />
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input value="${account.email}" type="email" id="email" name="email" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="startdate">Start Date:</label>
                        <input type="date" id="startDate" name="startdate" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="enddate">End Date:</label>
                        <input type="date" id="endDate" name="enddate" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="total">Total:</label>
                        <input type="number" id="total" name="total" class="form-control" value="${house.houseprice}" readonly />
                    </div>
                    <div class="form-group">
                        <label for="note">Note:</label>
                        <input type="text" id="note" name="note" class="form-control" />
                    </div>
                    <div class="form-group">
                        <label for="method">Method payment</label></br>
                        <input checked type="radio" name="payment" value="0" class="form-control-radio" /> Cash </br>
                        <input type="radio" name="payment" value="1" class="form-control-radio" /> VNPAY
                    </div>
                    <button type="submit" class="btn btn-success btn-block">Reserve or Book Now</button>
                </form>
            </div>
        </div>

        <%@ include file="footer.jsp" %>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const startDateInput = document.getElementById('startDate');
                const endDateInput = document.getElementById('endDate');
                const totalInput = document.getElementById('total');
                const housePrice = parseFloat(${house.houseprice});

                function addDays(date, days) {
                    const result = new Date(date);
                    result.setDate(result.getDate() + days);
                    return result;
                }

                function formatDate(date) {
                    const d = new Date(date);
                    const month = '' + (d.getMonth() + 1);
                    const day = '' + d.getDate();
                    const year = d.getFullYear();

                    return [year, month.padStart(2, '0'), day.padStart(2, '0')].join('-');
                }

                const today = new Date();
                const startDate = addDays(today, 1);
                const endDate = addDays(startDate, 1);

                startDateInput.value = formatDate(startDate);
                startDateInput.min = formatDate(today);
                endDateInput.value = formatDate(endDate);
                endDateInput.min = formatDate(startDate);

                function calculateTotal() {
                    const startDateValue = new Date(startDateInput.value);
                    const endDateValue = new Date(endDateInput.value);

                    if (startDateValue && endDateValue && endDateValue > startDateValue) {
                        const timeDiff = endDateValue - startDateValue;
                        const daysDiff = Math.ceil(timeDiff / (1000 * 3600 * 24));
                        const total = daysDiff * housePrice;
                        totalInput.value = total.toFixed(2);
                    } else {
                        totalInput.value = housePrice.toFixed(2);
                    }
                }

                startDateInput.addEventListener('change', function () {
                    endDateInput.min = startDateInput.value;
                    const startDateValue = new Date(startDateInput.value);
                    const endDateValue = addDays(startDateValue, 1);
                    endDateInput.value = formatDate(endDateValue);
                    calculateTotal();
                });

                endDateInput.addEventListener('change', calculateTotal);

                calculateTotal();
            });
        </script>
        <script>
            window.onload = function () {
                var today = new Date().toISOString().split('T')[0];
                var start = document.querySelector('#startDate');
                var end = document.querySelector('#endDate');
                var form = document.getElementById('bookingForm');

                start.setAttribute('min', today);
                end.setAttribute('min', today);

                start.addEventListener('change', function () {
                    var arrivalDate = this.value;
                    end.setAttribute('min', arrivalDate);
                });

                form.addEventListener('submit', function (event) {
                    var totalValue = parseFloat(document.querySelector('#total').value);
                    if (totalValue <= 0) {
                        event.preventDefault();
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Total amount must be greater than 0!',
                        });
                    }
                });
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="Housepage.js" type="text/javascript"></script>
    </body>
</html>
