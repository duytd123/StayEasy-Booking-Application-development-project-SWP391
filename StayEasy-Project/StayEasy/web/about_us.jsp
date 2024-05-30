<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>
            About us
        </title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/assets/owl.carousel.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/css/nice-select.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"/>       
        <link rel="stylesheet" href="https://cdn.tailwindcss.com/3.3.2"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js"/>
        <link rel="stylesheet" href="style.css">
        <link rel="icon" href="Images/logo1.png"/>
        <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"> 
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
            .a {
                color: black;
            }
            .icon {
                font-family: 'Font Awesome 5 Free';
                font-weight: 900;
                font-size: 24px;
                color: #f39c12;
            }
            .icon:before {
                content: '\f015';
                margin-right: 8px;
            }
            section {
                margin-top: 50px;
            }
        </style>
    </head>

    <%@ include file="header.jsp" %>
    
    <body>           
        <!-- Content
============================================= -->
        <section id="content">
            <div class="content-wrap notoppadding nobottompadding">
                <a href="home" class="logo">
                </span><span class="icon">StayEasy</span><span>

        </a>

        <!-- group 1 -->

        <section id="section_about" class="corner clearfix">
            <div class="container">
                <h2 style="font-family: Roboto;font-size: 48px; margin-top: 50px">Booking
                    <span style="font-family: Roboto;color: #ff5722;">
                        STAY EASY
                    </span>
                </h2>
                <h3 style="font-weight: 400;">
                    StayEasy trang Web uy tín tại Việt Nam
                </h3>
                <div class="section_about_content col-md-12" style="margin-top: 30px;">
                    <div class="col-md-4" style="text-align: center;">
                    </div>
                    <div class="col-md-8">
                        <div class="section_about_content_text">
                            <p><i class="fa fa-check" aria-hidden="true"></i><span>Cam kết</span>
                                uy tín.</p>
                            <p><i class="fa fa-check" aria-hidden="true"></i>Chính xách bảo hành 
                                <span>10 năm.</span></p>
                            <p><i class="fa fa-check" aria-hidden="true"></i>Đặt phòng
                                <span>Nhanh chóng</span> Uy tín .</p>
                        </div>
                        <a class="button-click" href="search-house-main?whereTo=&guests=&arrivals=&leaving=" data-href="#section_detail">
                            <button>BOOK NOW </button>
                        </a>
                    </div>
                </div>
            </div>
        </section>
        <!-- end group 1 -->


        <!-- group 2 -->
        <section id="section_inspiration" class="marbtm10 clearfix">
            <div class="row">
                <div class="col-sm-6">
                    <div class="col-sm-12 col-md-9 col-md-offset-3 pad0">
                        <div class="section_content">
                            <h2 class="section_inspiration_title">Cam Kết </h2>
                            <div class="section_inspiration_content">
                                <p>
                                    Chúng tôi hợp tác chặt chẽ với các thương hiêu khách sạn nổi tiếng trên thị
                                    trường để đảm bảo rằng mọi sản phẩm mà quý khách chọn lựa đều chất lượng.
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 pad0" style="text-align: center;">
                    <a href="">
                        <picture>
                            <img src="Images/hl.png"
                                 alt="section_inspiration_small" itemprop="image"
                                 title="section_inspiration_small">
                        </picture>
                    </a>
                </div>
            </div>
        </section>
        <!-- end group 2 -->


        <!-- group 3 -->
        <section id="section_material" class="clearfix">
            <div class="row marbtm10">
                <div class="col-sm-6 col-sm-push-6 pad0">
                    <div class="col-sm-12 col-md-9">
                        <div class="section_content">
                            <h3 class="section_material_1_title"> Đa Dạng</h3>

                            <blockquote class="section_material_1_quote"><i>Chúng tôi hiểu rằng mỗi người đều có cái 
                                    tôi riêng biệt</i></blockquote>

                            <div class="section_material_1_content">                                       
                                Chúng tôi liên tục cập nhật để đáp ứng mọi sở thích và phong cách cá nhân của quý khách.
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 col-sm-pull-6 pad0" style="text-align: center;">
                    <picture>
                        <img src="Images/p1.jpg"
                             alt="section_material_1_image" itemprop="image" title="section_material_1_image">
                    </picture>
                </div>
            </div>
            <div class="row marbtm10">
                <div class="col-sm-6 pad0">
                    <div class="col-sm-12 col-md-9 col-md-offset-3">
                        <div class="section_content">
                            <h3 class="section_material_2_title">Hỗ Trợ Khách Hàng Nhanh Chóng</h3>

                            <div class="section_material_2_content">
                                Bạn có thể liên hệ với chúng tôi thông 
                                qua điện thoại, email để nhận được sự tư vấn chuyên nghiệp 
                                và nhanh chóng. Chúng tôi tin rằng sự hài lòng của khách hàng là chìa khóa mở cửa cho sự 
                                thành công của chúng tôi.
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6 pad0" style="text-align: center;">
                    <picture>
                        <img src="Images/g-6.jpg"
                             alt="section_material_2_image" itemprop="image" title="section_material_2_image">
                    </picture>
                </div>
            </div>
        </section>
        <!-- end group 3 -->


        <!-- group 5 -->
        <div class="clear"></div>

        <div id="section_contact" class="page-section nobottompadding notoppadding"  style="padding: 0">
            <div class="row noleftmargin norightmargin common-height">
                <div class='index_map col-md-6 col-sm-6 hidden-xs nopadding'>
                    <iframe
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.506216904016!2d105.52271427471398!3d21.012421688340616!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2sFPT%20University!5e0!3m2!1sen!2s!4v1710051481955!5m2!1sen!2shttps://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3724.506216904016!2d105.52271427471398!3d21.012421688340616!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3135abc60e7d3f19%3A0x2be9d7d0b5abcbf4!2sFPT%20University!5e0!3m2!1sen!2s!4v1710051481955!5m2!1sen!2s"
                        width="100%" height="400" frameborder="0" style="border:0" allowfullscreen></iframe>

                </div>

                <!-- Google Map End -->

                <div class="col-md-6 col-sm-6" style="background-color: #383c44">
                    <div class="max-height" style='padding: 60px 30px'>
                        <h3 class="" style='color: #ffffff; font-size: 24px; '>Văn phòng chính</h3>

                        <div style="line-height: 1.7;">
                            <address style="line-height: 1.7; font-size: 16px; color: #dbdbdb ;">
                                <strong style='color: #fff'>HÀ NỘI - VIỆT NAM</strong><br>
                            </address>
                            <address style="line-height: 1.7; font-size: 16px; color: #dbdbdb ;">
                                <strong style='color: #fff'></strong><br>
                                DH FPT HA NOI<br>
                                Hotline: 0964173603<br />
                                Email:stayeasyfpt@gmail.com
                            </address>
                            <address style="line-height: 1.7; font-size: px; color: #ffffff ;">

                            </address>
                            <address style="line-height: 1.7; font-size: px; color: #ffffff ;">

                            </address>
                            <address style="line-height: 1.7; font-size: px; color: #ffffff ;">

                            </address>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- #wrapper end -->
<link href="//bizweb.dktcdn.net/100/048/087/themes/776353/assets/bootstrap.min.scss.css?1671122359380"
      rel="stylesheet" type="text/css" media="all" />
<link href="//bizweb.dktcdn.net/100/048/087/themes/776353/assets/style.css?1671122359380" rel="stylesheet"
      type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="//hstatic.net/0/global/design/member/fonts/svn-gotham-book,sans-serif.css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//bizweb.dktcdn.net/100/048/087/themes/776353/assets/scripts.js?1671122359380"type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>   
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.2.1/owl.carousel.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-nice-select/1.1.0/js/jquery.nice-select.min.js"></script>
<script src="js/countdown.js"></script>
<script src="js/clickevents.js"></script>
<script src="js/main.js"></script>
</body>
</html>
