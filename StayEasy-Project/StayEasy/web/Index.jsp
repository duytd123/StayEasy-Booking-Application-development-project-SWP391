<%-- 
    Document   : Index
    Created on : Oct 4, 2022, 8:31:55 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Account"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

    <!-- font awesome cdn link  -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <!-- custom css file link  -->
    <link rel="stylesheet" href="StyleSheet.css">
    <link rel="stylesheet" href="room/room.html">
    </head>
    <body>

        <!-- header section starts  -->

    <header>

        <div id="menu-bar" class="fas fa-bars"></div>

        <a href="#" class="logo"><span>H</span>ouse<span>B</span>ooking</a>

        <nav class="navbar">
            <a href="#home">home</a>
            <a href="#book">book</a>
            <a href="#packages">room</a>
            <a href="#contact">contact</a>
        </nav>
        <div class="icons">
            <i class="fas fa-search" id="search-btn"></i>
                <c:if  test="${sessionScope.acc == null}">
                    <a href="login.jsp">
                        <i class="fas fa-user" id="user-btn"></i>
                    </a>
                </c:if>
                <c:if test="${sessionScope.acc != null}">
                    <i>username : <span>${acc.fullname}</span></i>
                    <a href="LogoutServlet">
                        <i class="delete-btn">Logout</i>
                    </a>
                    <a  class="" href="user.jsp" >
                        
                        <i>Profile</i>
                    </a>
                </c:if>
        </div>


    </header>

    <!-- header section ends -->

    <!-- home section starts  -->

    <section class="home" id="home">
        <div class="content">
            <h3>adventure is worthwhile</h3>
            <p>dicover new places with us, adventure awaits</p>
            <a href="#" class="btn">discover more</a>
        </div>

        <div class="controls">
            <span class="vid-btn active" data-src="Images/vi-1.mp4"></span>
            <span class="vid-btn" data-src="Images/vi-2.mp4"></span>
            <span class="vid-btn" data-src="Images/vid-3.mp4"></span>
            <span class="vid-btn" data-src="Images/vid-4.mp4"></span>
            <span class="vid-btn" data-src="Images/vid-5.mp4"></span>
        </div>
        <div class="video-container">
            <video width="320" height="240" id="video-slider" loop autoplay muted>
                <source src="Images/vi-1.mp4" type="video/mp4">
            </video>
        </div>

    </section>

    <!-- home section ends -->
    <!-- book section starts  -->

    <section class="book" id="book">

        <h1 class="heading">
            <span>b</span>
            <span>o</span>
            <span>o</span>
            <span>k</span>
            <span class="space"></span>
            <span>n</span>
            <span>o</span>
            <span>w</span>
        </h1>

        <div class="row">

            <div class="image">
                <img src="Images/vn.jpg" alt=""/>
            </div>

            <form action="search-house-main" method="get" >
                <div class="inputBox">
                    <h3>where to</h3>
                    <input type="text" name="whereTo" placeholder="place name">
                </div>
                <div class="inputBox">
                    <h3>how many</h3>
                    <input type="number" placeholder="number of guests">
                </div>
                <div class="inputBox">
                    <h3>arrivals</h3>
                    <input name="arrivals" type="date">
                </div>
                <div class="inputBox">
                    <h3>leaving</h3>
                    <input type="date">
                </div>
                <!-- <input type="submit" class="btn" value="book now" href="#packages" >  -->
                <input type="submit" value="Book now"  class="btn">

            </form>

        </div>

    </section>

    <!-- book section ends -->
    <!-- packages section starts  -->

    

    <!-- packages section ends -->
    <!-- services section starts  -->


    <!-- services section ends -->
    <!-- gallery section starts  -->

    <!-- <section class="gallery" id="gallery">

        <h1 class="heading">
            <span>g</span>
            <span>a</span>
            <span>l</span>
            <span>l</span>
            <span>e</span>
            <span>r</span>
            <span>y</span>
        </h1>

        <div class="box-container">

            <div class="box">
                <img src="images/g-1.jpg" alt="">
                <div class="content">
                    <h3>amazing places</h3>
                    <p>...</p>
                    <a href="#" class="btn">see more</a>
                </div>
            </div>
            <div class="box">
                <img src="images/g-2.jpg" alt="">
                <div class="content">
                    <h3>amazing places</h3>
                    <p>...</p>
                    <a href="#" class="btn">see more</a>
                </div>
            </div>
            <div class="box">
                <img src="images/g-3.jpg" alt="">
                <div class="content">
                    <h3>amazing places</h3>
                    <p>...</p>
                    <a href="#" class="btn">see more</a>
                </div>
            </div>
            <div class="box">
                <img src="images/g-4.jpg" alt="">
                <div class="content">
                    <h3>amazing places</h3>
                    <p>...</p>
                    <a href="#" class="btn">see more</a>
                </div>
            </div>
            <div class="box">
                <img src="images/g-5.jpg" alt="">
                <div class="content">
                    <h3>amazing places</h3>
                    <p>...</p>
                    <a href="#" class="btn">see more</a>
                </div>
            </div>
            <div class="box">
                <img src="images/g-6.jpg" alt="">
                <div class="content">
                    <h3>amazing places</h3>
                    <p>...</p>
                    <a href="#" class="btn">see more</a>
                </div>
            </div>
            <div class="box">
                <img src="images/g-7.jpg" alt="">
                <div class="content">
                    <h3>amazing places</h3>
                    <p>...</p>
                    <a href="#" class="btn">see more</a>
                </div>
            </div>
            <div class="box">
                <img src="images/g-8.jpg" alt="">
                <div class="content">
                    <h3>amazing places</h3>
                    <p>...</p>
                    <a href="#" class="btn">see more</a>
                </div>
            </div>
            <div class="box">
                <img src="images/g-9.jpg" alt="">
                <div class="content">
                    <h3>amazing places</h3>
                    <p>...</p>
                    <a href="#" class="btn">see more</a>
                </div>
            </div>

        </div>

    </section> -->

    <!-- gallery section ends -->
    <!-- review section starts  -->


    <!-- review section ends -->
    <!-- contact section starts  -->

    <section class="contact" id="contact">

        <h1 class="heading">
            <span>c</span>
            <span>o</span>
            <span>n</span>
            <span>t</span>
            <span>a</span>
            <span>c</span>
            <span>t</span>
        </h1>

        <div class="row">
            <div class="image">
                <img src="Images/travel.jpg" alt=""/>
            </div>
            <form name="submit-to-google-sheet">
                <div class="inputBox">
                    <%
                        String username = (String)session.getAttribute("username");
                        if(username == null){
                    %>
                    <input type="text" name="Name" placeholder="name">
                    <%
                    }else{
                %>
                <input type="text" name="Name" placeholder="name" value="<%=username%>">
                <%
                    }
                %>
                <%
                String email = (String)session.getAttribute("email");
                if(email == null){
                %>
                <input type="email" name="Email" value="" placeholder="email">
                <%
                    }else{
                %>
                <input type="email" name="Email" value="<%=email%>" placeholder="email">
                <%
                    }
                %>
                </div>
                <div class="inputBox">
                    <input type="number" name="Number" placeholder="number">
                    <input type="text" name="Subject" placeholder="subject">
                </div>
                <textarea placeholder="message" name="Message" id="" cols="30" rows="10"></textarea>
                <input type="submit" class="btn" value="send message">
            </form>
        </div>

    </section>

    <!-- contact section ends -->
    <!-- brand section  -->
    <!--<section class="brand-container">

        <div class="swiper-container brand-slider">
            <div class="swiper-wrapper">
                <div class="swiper-slide"><img src="images/brand.png" alt=""></div>
                <div class="swiper-slide"><img src="images/2.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/3.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/4.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/5.jpg" alt=""></div>
                <div class="swiper-slide"><img src="images/6.jpg" alt=""></div>
            </div>
        </div>

    </section>-->

    <!-- footer section  -->

    <section class="footer">

        <div class="box-container">

            <div class="box">
                <h3>about us</h3>
                <p>We are team ... This is my our project for SWP391</p>
            </div>
            <div class="box">
                <h3>locations</h3>
                <a href="#">Ha Long</a>
                <a href="#">Da Nang</a>
                <a href="#">Con Dao</a>
                <a href="#">Da Lat</a>
                <a href="#">Nha Trang</a>
                <a href="#">SaPa</a>
            </div>
            <div class="box">
                <h3>quick links</h3>
                <a href="#home">home</a>
                <a href="#book">book</a>
                <a href="#packages">packages</a>
                <a href="#services">services</a>
                <a href="#gallery">gallery</a>
                <a href="#review">review</a>
                <a href="#contact">contact</a>
            </div>
            <div class="box">
                <h3>follow us</h3>
                <a href="https://www.facebook.com/huuduc.devil">facebook</a>
                <a href="#">instagram</a>
                <a href="#">twitter</a>
                <a href="#">linkedin</a>
            </div>

        </div>

        <h1 class="credit"> created by <span> Trung, Đức, Hy, Nam, Kiệt </span> | SWP391 </h1>

    </section>

<script>
      const scriptURL =
        "https://script.google.com/macros/s/AKfycbzmx3GEyLiss69xqkLRzbnbatnyo3kYTUCatb3PQUEGjxJbL3HubmFjoPFAYxoWXylIZg/exec";
      const form = document.forms["submit-to-google-sheet"];

      form.addEventListener("submit", (e) => {
        e.preventDefault();
        fetch(scriptURL, { method: "POST", body: new FormData(form) })
          .then((response) => console.log("Success!", response))
          .catch((error) => console.error("Error!", error.message));
      });
    </script>











<!-- jquery cdn link  -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js%22%3E</script>

    <script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

    <!-- custom js file link  -->
    <script src="JavaScript.js"></script>

    </body>
</html>
