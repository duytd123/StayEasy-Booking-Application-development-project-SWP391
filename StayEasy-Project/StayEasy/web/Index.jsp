
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
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link rel="stylesheet" href="StyleSheet.css">      
    </head>
    <style>
        .user-fullname {
            font-weight: bold;
            color: #fd7e14;
            margin-left: 20px;
            font-size: 1.6em;
        }
    </style>
    <body>

        <!-- header section starts  -->

        <header>
            <div id="menu-bar" class="fas fa-bars"></div>
            <a href="#" class="logo"><span>Welcome</span>Stay<span>Ease</span>Booking</a>
            <nav class="navbar">
                <a href="#home">Home</a>
                <a href="#book">Book</a>
                <a href="aboutus">About Us</a>
                <a href="#packages">Room</a>
                <a href="#contact">Contact</a>
                <c:if test="${sessionScope.acc != null}">
                    <c:choose>
                        <c:when test="${acc.role.id == 0}">
                            <a href="DashboardServlet">Dashboard</a>
                        </c:when>
                        <c:when test="${acc.role.id == 1}">
                            <a href="DashboardHostServlet">Dashboard</a>
                        </c:when>
                        <c:otherwise>

                        </c:otherwise>
                    </c:choose>
                </c:if>
            </nav>
            <div class="icons">
                <i class="fas fa-search" id="search-btn"></i>
                <c:if test="${sessionScope.acc == null}">
                    <a href="login.jsp" class="icon-link">
                        <i class="fas fa-user" id="user-btn"></i>
                    </a>
                </c:if>
                <c:if test="${sessionScope.acc != null}">

                    <span class="user-fullname">${acc.fullname}</span>
                    <a href="LogoutServlet" class="icon-link">
                        <i class="fas fa-sign-out-alt" id="logout-btn"></i> 
                    </a>
                    <a href="user.jsp" class="icon-link">
                        <i class="fas fa-user-circle" id="profile-btn"></i> 
                    </a>
                </c:if>
            </div>

        </header>

        <!-- header section ends -->

        <!-- home section starts  -->

        <section class="home" id="home">
            <div class="content">
                <h3>Adventure is worthwhile</h3>
                <p>Discover new places with us, adventure awaits</p>
                <a href="#" class="btn">Discover more</a>
            </div>

            <div class="controls">
                <span class="vid-btn active" data-src="Images/vi-1.mp4"></span>
                <span class="vid-btn" data-src="Images/vi-2.mp4"></span>
                <span class="vid-btn" data-src="Images/vid-3.mp4"></span>
                <span class="vid-btn" data-src="Images/vid-4.mp4"></span>
                <span class="vid-btn" data-src="Images/vid-5.mp4"></span>
            </div>
            <div class="video-container">
                <video width="350" height="250" id="video-slider" loop autoplay muted>
                    <source src="Images/vid-1.mp4" type="video/mp4">
                </video>
            </div>
        </section>

        <!-- home section ends -->

        <!-- book section starts  -->

        <section class="book" id="book">
            <h1 class="heading">
                <span>B</span>
                <span>O</span>
                <span>O</span>
                <span>K</span>
                <span class="space"></span>
                <span>N</span>
                <span>O</span>
                <span>W</span>
            </h1>

            <div class="row">
                <div class="image">
                    <img src="Images/vn.jpg" alt=""/>
                </div>
                <form action="search-house-main" method="get">
                    <div class="inputBox">
                        <input type="text" name="whereTo" placeholder="Place name">
                    </div>
                    <div class="inputBox">
                        <input type="number" name="guests" placeholder="Number of guests">
                    </div>
                    <div class="inputBox">
                        <h3>Arrivals</h3>
                        <input name="arrivals" type="date">
                    </div>
                    <div class="inputBox">
                        <h3>Leaving</h3>
                        <input name="leaving" type="date">
                    </div>
                    <input type="submit" class="btn" value="Book now">
                </form>
            </div>
        </section>

        <!-- book section ends -->

        <!-- gallery section starts  -->

        <section class="gallery" id="gallery">
            <h1 class="heading">
                <span>G</span>
                <span>A</span>
                <span>L</span>
                <span>L</span>
                <span>E</span>
                <span>R</span>
                <span>Y</span>
            </h1>

            <div class="box-container">
                <div class="box">
                    <img src="Images/g-1.jpg" alt="">
                    <div class="content">
                        <h3>Amazing places</h3>
                        <p>...</p>
                        <a href="#" class="btn">See more</a>
                    </div>
                </div>
                <div class="box">
                    <img src="Images/g-2.jpg" alt="">
                    <div class="content">
                        <h3>Amazing places</h3>
                        <p>...</p>
                        <a href="#" class="btn">See more</a>
                    </div>
                </div>
                <!-- Add more gallery boxes as needed -->
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
                    <img src="Images/travel.jpg" alt=""/>
                </div>
                <form name="submit-to-google-sheet">
                    <div class="inputBox">
                        <% String username = (String)session.getAttribute("username"); %>
                        <input type="text" name="Name" placeholder="Name" value="<%= username != null ? username : "" %>">
                        <% String email = (String)session.getAttribute("email"); %>
                        <input type="email" name="Email" placeholder="Email" value="<%= email != null ? email : "" %>">
                    </div>
                    <div class="inputBox">
                        <input type="number" name="Number" placeholder="Number">
                        <input type="text" name="Subject" placeholder="Subject">
                    </div>
                    <textarea name="Message" placeholder="Message" cols="30" rows="10"></textarea>
                    <input type="submit" class="btn" value="Send message">
                </form>
            </div>
        </section>

        <!-- contact section ends -->

        <!-- brand section starts  -->

        <section class="brand-container">
            <div class="swiper-container brand-slider">
                <div class="swiper-wrapper">
                    <div class="swiper-slide"><img src="Images/brand.png" alt=""></div>
                    <div class="swiper-slide"><img src="Images/2.jpg" alt=""></div>
                    <div class="swiper-slide"><img src="Images/3.jpg" alt=""></div>
                    <!-- Add more swiper slides as needed -->
                </div>
            </div>
        </section>

        <!-- brand section ends -->

        <!-- footer section starts  -->

        <section class="footer">
            <div class="box-container">
                <div class="box">
                    <h3>About Us</h3>
                    <p>We are team ... This is our project for SWP391</p>
                </div>
                <div class="box">
                    <h3>Locations</h3>
                    <a href="#">Ha Long</a>
                    <a href="#">Da Nang</a>
                    <a href="#">Con Dao</a>
                    <a href="#">Da Lat</a>
                    <a href="#">Nha Trang</a>
                    <a href="#">SaPa</a>
                </div>
                <div class="box">
                    <h3>Quick Links</h3>
                    <a href="#home">Home</a>
                    <a href="#book">Book</a>
                    <a href="#packages">Packages</a>
                    <a href="#services">Services</a>
                    <a href="#gallery">Gallery</a>
                    <a href="#review">Review</a>
                    <a href="#contact">Contact</a>
                </div>
                <div class="box">
                    <h3>Follow Us</h3>
                    <a href="#">Facebook</a>
                    <a href="#">Instagram</a>
                </div>
            </div>
            <h1 class="credit"> <span> @2024 </span> SWP391 </h1>
        </section>

        <!-- footer section ends -->

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

        <!-- jquery cdn link  -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
        <script src="JavaScript.js"></script>

    </body>
</html>
