<%@page import="Model.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.House"%>
<%@page import="java.util.List"%>
<%@page import="Model.Account"%>
<jsp:useBean id="calculateStar" class="configs.CalculateFeedback"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>House Details</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <style>
            body {
                margin-top: 100px;
            }
            .star {
                color: gold;
            }
            .feedback-rating .star.gray {
                color: gray;
            }
            .heading2{
                color: black;
                font-size: 15px;
            }
        </style>
    </head>

    <%@ include file="header.jsp" %>
    <body>

        <div class="container" style="margin-top: 100px">
            <div class="row">
                <div class="col-md-8">
                    <!-- House details section -->
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

                <div class="col-md-4 mt-5">
                    <!-- Booking section -->
                    <div class="hotel-services mt-4 p-3 border rounded">
                        <h2>Book house</h2>
                        <form action="booking" method="get">
                            <input type="hidden" value="${house.houseid}" name="houseId"/>
                            <button type="submit" class="btn btn-success btn-block">Reserve or Book Now</button>
                        </form>
                    </div>

                    <!-- Feedback section -->
                    <div class="hotel-services mt-4 p-3 border rounded">
                        <c:set var="totalStar" value="${calculateStar.totalStar(feedbacks)}" />
                        <div class="product-rating" style="font-size: 20px;">
                            <h4>Feedback average</h4>
                            <c:choose>
                                <c:when test="${totalStar > 0}">
                                    <c:forEach var="i" begin="1" end="${calculateStar.floor(totalStar)}">
                                        <span class="star" style="color: gold">★</span>
                                    </c:forEach>
                                    <c:if test="${totalStar - calculateStar.floor(totalStar) >= 0.5}">
                                        <i class="fa fa-star-half-o" style="color: gold"></i>
                                    </c:if>
                                    <c:forEach var="i" begin="${calculateStar.floor(totalStar) + (totalStar - calculateStar.floor(totalStar) >= 0.5 ? 1 : 0)}" end="4">
                                        <span class="star">☆</span>
                                    </c:forEach>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="i" begin="0" end="4">
                                        <span class="star">☆</span>
                                    </c:forEach>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <a class="review-link" href="#">(${feedbacks.size()} Reviews)</a>
                        <hr>
                        <h4>Feedbacks</h4>
                        <c:forEach var="feedback" items="${feedbacks}">
                            <div class="feedback-item">
                                <p><strong>${feedback.fullname}</strong> (${feedback.date})</p>
                                <div class="feedback-rating" style="font-size: 20px;">
                                    <c:forEach var="i" begin="1" end="5">
                                        <span class="star" style="color: ${i <= feedback.star ? 'gold' : 'gray'}">★</span>
                                    </c:forEach>
                                </div>
                                <p>${feedback.content}</p>
                            </div>
                            <hr>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <!-- Comment section -->
            <c:choose>
                <c:when test="${empty sessionScope.username}">
                    <div class="alert alert-warning mt-4" role="alert">
                        Please <a href="LoginServlet" class="alert-link">login</a> to comment.
                    </div>
                </c:when>
                <c:otherwise>
                    <section class="comments-container mt-5">
                        <h1 class="heading2"></h1>
                        <form action="AddCommentServlet" method="POST" class="form-group">
                            <input type="hidden" name="houseid" value="${house.houseid}">
                            <div class="form-group">
                                <label>Name: ${sessionScope.username}</label><br>
                                
                                <input type="hidden" name="userid" value="${sessionScope.account.userid}">
                            </div>
                            <div class="form-group">
                                <label>Comment:</label>
                                <textarea name="comment" class="form-control" required></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">Add Comment</button>
                        </form>
                    </section>

                    <h1 class="heading2 mt-5">Comments</h1>
                    <c:forEach var="comment" items="${commentt}">
                        <section class="comment mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">From: ${comment.fullname}</h5>
                                    <h6 class="card-subtitle mb-2 text-muted">By: ${comment.fullname}</h6>
                                    <p class="card-text">${comment.comment}</p>
                                    <p class="card-text"><small class="text-muted">${comment.date}</small></p>
                                        <c:if test="${comment.reply != null}">
                                        <div class="reply">
                                            <p><strong>Reply:</strong> ${comment.reply}</p>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                        </section>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>


        <%@ include file="footer.jsp" %>
        <script>
            window.onload = function () {
                var today = new Date().toISOString().split('T')[0];
                var drink = document.querySelector('#drink');
                var beefsteak = document.querySelector('#beefsteak');
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
                    var beefsteakValue = parseInt(beefsteak.value);
                    var drinkValue = parseInt(drink.value);
                    if (drinkValue <= 0) {
                        event.preventDefault();

                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Number of drink must be greater than 0!',
                        });
                    }
                    if (beefsteakValue <= 0) {
                        event.preventDefault();

                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Number of beefsteak must be greater than 0!',
                        });
                    }
                });
            }
        </script>
        <script>
            var mess = '${mess}';
            if (mess) {
                alert(mess);
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="Housepage.js" type="text/javascript"></script>
    </body>
</html>

