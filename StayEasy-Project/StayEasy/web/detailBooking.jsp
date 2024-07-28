<%-- 
    Document   : detailBooking
    Created on : Jul 5, 2024, 6:24:28 PM
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail booking</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
                <h2>Detail booking</h2>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger">${param.error}</div>
                </c:if>
                <c:if test="${param.success != null}">
                    <div class="alert alert-success">${param.success}</div>
                </c:if>
                <button class="btn btn-warning">${bill.status == 0 ? "Pending" : bill.status == 1 ? "Completed" : "Canceled"}</button>
                <c:if test="${bill.status == 1}">
                    <button class="btn btn-info btn-feedback-now">${feedback != null ? "View your feedback" : "Feedback now"}</button>
                </c:if>
                <c:if test="${bill.status != 1}">
                    <button class="btn btn-danger btn-cancel-now" data-toggle="modal" data-target="#cancelModal">${bill.status == 2 ? "View reason" : "Cancel"}</button>
                </c:if>
                <form action="booking" method="post" id="bookingForm">
                    <div class="form-group">
                        <label for="fullname">Full Name:</label>
                        <input value="${bill.fullname}" readonly value="${account.fullname}" type="text" id="fullname" name="fullname" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone:</label>
                        <input value="${bill.phone}" readonly value="${account.phone}" type="tel" id="phone" name="phone" class="form-control" pattern="[0-9]{10}" required />
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input value="${bill.email}" readonly value="${account.email}" type="email" id="email" name="email" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="startdate">Booking Date:</label>
                        <input value="${bill.date}" readonly type="date" id="startDate" name="startdate" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="startdate">Start Date:</label>
                        <input value="${billDetail.startdate}" readonly type="date" id="startDate" name="startdate" class="form-control" required />
                    </div>
                    <div class="form-group">
                        <label for="enddate">End Date:</label>
                        <input value="${billDetail.enddate}" readonly type="date" id="endDate" name="enddate" class="form-control" required />
                    </div>
                   
                  
                    <div class="form-group">
                        <label for="total">Total:</label>
                        <input readonly type="number" id="total" name="total" class="form-control" value="${bill.total}" readonly />
                    </div>
                    <div class="form-group">
                        <label for="note">Note:</label>
                        <input readonly value="${billNote}" type="text" id="note" name="note" class="form-control" />
                    </div>
                    <a href="history-booking" class="btn btn-secondary btn-block">Back to history</a>
                </form>
            </div>
        </div>
        <style>
            .rating {
                unicode-bidi: bidi-override;
                direction: rtl;
                text-align: center;
            }
            .rating span {
                font-size: 30px;
                padding: 5px;
                cursor: pointer;
            }
            .star.active {
                color: black;
            }
            .star.active {
                color: gold;
            }
        </style>

        <div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" aria-labelledby="cancelModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cancelModalLabel">Reason for Cancellation</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <form id="cancelForm" action="history-booking" method="post">
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="cancelReason">Reason</label>
                                <textarea ${bill.status == 2 ? "readonly" : ""} class="form-control" id="cancelReason" name="cancelReason" required>${bill.reason}</textarea>
                            </div>
                            <input type="hidden" name="bill_id" value="${bill.billid}">
                            <input type="hidden" name="action" value="cancel">
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <c:if test="${bill.status != 2}">
                                <button type="submit" class="btn btn-danger">Submit</button>
                            </c:if>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="modal fade" id="feedbackModal" tabindex="-1" role="dialog" aria-labelledby="feedbackModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="feedbackModalLabel">Feedback Form</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="history-booking" method="post">
                            <input type="hidden" value="${bill.billid}" name="bill_id" />
                            <input type="hidden" value="${feedback != null ? "edit-feedback" : "feedback"}" name="action"/>
                            <c:if test="${feedback != null}">
                                <input type="hidden" value="${feedback.id}" name="feedbackId" />
                            </c:if>
                            <div class="form-group">
                                <label for="rating">Rating:</label>
                                <div class="rating">
                                    <span class="star" data-star="5">☆</span>
                                    <span class="star" data-star="4">☆</span>
                                    <span class="star" data-star="3">☆</span>
                                    <span class="star" data-star="2">☆</span>
                                    <span class="star" data-star="1">☆</span>
                                    <input type="hidden" id="rating" name="star" value="${feedback != null ? feedback.star : 0}" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="content">Feedback:</label>
                                <textarea  id="content" name="content" rows="3" class="form-control" required>${feedback != null ? feedback.content : "" }</textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">${feedback != null ? "Save feedback" : "Submit feedback"}</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
            const feedbackButton = document.querySelector('.btn.btn-feedback-now');

            feedbackButton.addEventListener('click', function (event) {
                event.preventDefault();
                $('#feedbackModal').modal('show');
            });
            const stars = document.querySelectorAll('.star');
            const ratingInput = document.getElementById('rating');
            for (let i = stars.length - 1; i >= (5 - ratingInput.value); i--) {
                stars[i].textContent = '★';
                stars[i].classList.add('active');
            }
            stars.forEach((star, index) => {
                star.addEventListener('click', function () {
                    const starValue = parseInt(this.getAttribute('data-star'));
                    ratingInput.value = (starValue);

                    stars.forEach(s => {
                        s.textContent = '☆';
                        s.classList.remove('active');
                    });

                    for (let i = stars.length - 1; i >= index; i--) {
                        stars[i].textContent = '★';
                        stars[i].classList.add('active');
                    }
                });
            });

        </script>

         <%@ include file="footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="Housepage.js" type="text/javascript"></script>

    </body>
</html>
