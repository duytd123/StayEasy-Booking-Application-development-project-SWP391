<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Account"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>StayEaseBooking</title>
        <link rel="icon" href="Images/logo1.png"/>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="assets/css/style.min.css">
        <link rel="stylesheet" href="StyleSheet.css">
        <link rel="stylesheet" href="css/housepage.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <style>
        .user-fullname {
            font-weight: bold;
            color: #fd7e14;
            margin-left: 20px;
            font-size: 1.6em;
        }
        .sales {
            animation: slideInUp 1s ease forwards;
        }
        element.style {
            padding-bottom: 4px;
            padding-top: 4px;
        }

        .row_1 {
            display: flex;
            justify-content: space-between;
        }

        .product {
            width: 48%;
            margin-bottom: 20px;
        }

        .newsletter_content{
            margin-bottom: 20px;
        }

        @keyframes slideInUp {
            from {
                transform: translateY(100%);
                opacity: 0;
            }
            to {
                transform: translateY(0);
                opacity: 1;
            }
        }
        .product_tab_btn1 ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        .product_tab_btn1 ul li {
            display: inline-block;
            margin-right: 10px;
        }

        .product_tab_btn1 ul li a {
            text-decoration: none;
            color: #333;
            padding: 5px 10px;
            border-radius: 5px;
        }

        .product_tab_btn1 ul li a.active {
            background-color: #ff6600;
            color: #fff;
        }
        .image_container {
            overflow: hidden;
            white-space: nowrap;
            position: relative;
        }

        .image_wrapper {
            position: relative;
            display: inline-block;
            transition: transform 0.3s ease;
        }

        .image_wrapper img {
            display: inline-block;
            width: 500px;
            height: 500px;
            margin-right: 10px;
            cursor: pointer;
        }

        #prevBtn,
        #nextBtn {
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            background-color: #ccc;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
        }

        #prevBtn {
            left: 0;
        }

        #nextBtn {
            right: 0;
        }
        .success-message {
            color: green;
            font-weight: bold;
        }

        .error-message {
            color: red;
            font-weight: bold;
        }
         section {
                margin-top: 40px;
            }
    </style>

    <body>
        <%@ include file="header.jsp" %>

        <section class="home" id="home">
            <div class="content">
                <h3>StayEasy</h3>
                <p>Discover new places with us, adventure awaits</p>
                <a href="aboutus" class="btn">Discover more</a>
            </div>

            <div class="video-container">
                <video width="350" height="250" id="video-slider" loop autoplay muted>
                    <source src="Images/vid-1.mp4" type="video/mp4">
                </video>
            </div>
        </section>


        <!-- home section ends -->
        <%@ include file="bookNowSection.jsp" %>
        <!-- book section starts  -->

        <section class="sales" id="sales">

            <!-- Newsletter section starts -->

            <div class="newsletter_style2" id="newsletter">
                <div class="newsletter_container">
                    <div class="row">
                        <div class="col-12">
                            <div class="section_title">
                                <h2 class="text-shadow" style="font-size: 40px;"><span class="ani-fire">BEAUTIFUL HOUSE</span></h2>
                                <div class="image_container">
                                    <div class="image_wrapper">
                                        <c:forEach items="${houseImages}" var="houseImg">
                                            <img src="${houseImg.imglink}" alt="House Image">
                                        </c:forEach>
                                    </div>
                                    <button id="prevBtn" onclick="prevImages()">Previous</button>
                                    <button id="nextBtn" onclick="nextImages()">Next</button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="subscribe_form">
                        <form action="SignUpServlet" method="get">
                            <input id="emailDiscount" type="email" autocomplete="off" placeholder="Go to sigup">
                            <button type="submit"> 
                                <i class="fa fa-envelope-o"></i>
                            </button>
                        </form>
                    </div>

                    <div class="newsletter_content">
                        <p>Go to sign up we get news and get 20% discount and many offer instantly.</p>
                    </div>
                </div>
            </div>
            <!-- Newsletter section ends -->

        </section>

        <!-- book section ends -->

        <!-- gallery section starts  -->

        <section class="gallery" id="gallery">
            <h1 class="heading">
                <span>H</span>
                <span>O</span>
                <span>T</span
            </h1>

            <div class="box-container">
                <div class="box">
                    <img src="Images/p-2.jpg" alt="">
                    <div class="content">
                        <h3>Hạ Long</h3>
                        <p>...</p>
                        <a href="#" class="btn">See more</a>
                    </div>
                </div>
                <div class="box">
                    <img src="Images/danang.jpg" alt="">
                    <div class="content">
                        <h3>Đà Nẵng</h3>
                        <p>...</p>
                        <a href="#" class="btn">See more</a>
                    </div>
                </div>
                <!-- Add more gallery boxes as needed -->
                <div class="box">
                    <img src="Images/condao.png" alt="">
                    <div class="content">
                        <h3>Côn Đảo</h3>
                        <p>...</p>
                        <a href="#" class="btn">See more</a>
                    </div>
                </div>
            </div>
        </section>


        <section class="gallery" id="gallery">
            <div class="box-container">
                <div class="box">
                    <img src="Images/dalat.png" alt="">
                    <div class="content">
                        <h3>Đà Lạt</h3>
                        <p>...</p>
                        <a href="#" class="btn">See more</a>
                    </div>
                </div>
                <div class="box">
                    <img src="Images/nhatrang.png" alt="">
                    <<h2></h2>L
                    <div class="content">
                        <h3>Nha Trang</h3>
                        <p>...</p>
                        <a href="#" class="btn">See more</a>
                    </div>
                </div>
                <!-- Add more gallery boxes as needed -->
                <div class="box">
                    <img src="Images/sapa.png" alt="">
                    <div class="content">
                        <h3>Sa Pa</h3>
                        <p>...</p>
                        <a href="#" class="btn">See more</a>
                    </div>
                </div>
            </div>
        </section>


        <!-- gallery section ends -->

        <!-- contact section starts  -->

        <section class="contact" id="contact">
            <h1 class="heading">
                <span>C</span>
                <span>O</span>
                <span>N</span>
                <span>T</span>
                <span>A</span>
                <span>C</span>
                <span>T</span>
            </h1>

            <div class="row">
                <div class="image">
                    <img src="Images/travel.png" alt=""/>
                </div>
                <form action="SendMessageServlet" method="POST">
                    <div class="inputBox">
                        <% String username = (String) session.getAttribute("username"); %>
                        <input type="text" name="Name" placeholder="Name" value="<%= username != null ? username : "" %>">
                        <% String email = (String) session.getAttribute("email"); %>
                        <input type="email" name="Email" placeholder="Email" value="<%= email != null ? email : "" %>">
                    </div>
                    <textarea name="Message" placeholder="Message" cols="30" rows="10"></textarea>
                    <input type="submit" class="btn" value="Send message">
                    <% if (request.getAttribute("successMessage") != null) { %>
                    <p style="color: green;"><%= request.getAttribute("successMessage") %></p>
                    <% } %>
                    <% if (request.getAttribute("errorMessage") != null) { %>
                    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
                    <% } %>
                </form>
            </div>

        </section>


        <!-- Include footer -->
        <%@ include file="footer.jsp" %>
        <script>
            const scriptURL = "https://script.google.com/macros/s/AKfycbzmx3GEyLiss69xqkLRzbnbatnyo3kYTUCatb3PQUEGjxJbL3HubmFjoPFAYxoWXylIZg/exec";
            const form = document.forms["submit-to-google-sheet"];
            form.addEventListener("submit", (e) => {
                e.preventDefault();
                fetch(scriptURL, {method: "POST", body: new FormData(form)})
                        .then((response) => console.log("Success!", response))
                        .catch((error) => console.error("Error!", error.message));
            });
        </script>
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
            let currentIndex = 0;
            const images = document.querySelectorAll('.image_wrapper img');
            let container = document.querySelector('.image_wrapper');
            const prevBtn = document.getElementById('prevBtn');
            const nextBtn = document.getElementById('nextBtn');
            const imageWidth = images[0].offsetWidth + 10;
            console.log(container)

            function nextImages() {
                if (currentIndex < images.length - 1) {
                    currentIndex++;

                } else {
                    currentIndex = 0;
                }
                container.style.transform = "translateX(-" + (currentIndex * imageWidth) + "px)";
            }

            function prevImages() {
                if (currentIndex > 0) {
                    currentIndex--;
                } else {
                    currentIndex = images.length - 1;
                }
                container.style.transform = "translateX(-" + (currentIndex * imageWidth) + "px)";
            }
        </script>
        <!-- jquery cdn link  -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
        <script src="JavaScript.js"></script>
        <script src="js/clickevents.js"></script>
    </body>
</html>



