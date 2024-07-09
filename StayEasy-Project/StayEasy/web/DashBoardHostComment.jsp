<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="Model.Account"%>
<%@page import="Model.House"%>
<!DOCTYPE html>
<html dir="ltr" lang="en-gb">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Host Comment · StayEasy</title>
        <link rel="icon" href="Images/logo1.png"/>
        <link rel="stylesheet" data-href="static/css/client.91cb4144.css" href="https://r-xx.bstatic.com/psb/capla/static/css/client.91cb4144.css">
        <link rel="stylesheet" data-href="static/css/f7728259.a591419f.chunk.css" href="https://r-xx.bstatic.com/psb/capla/static/css/f7728259.a591419f.chunk.css">
        <link rel="stylesheet" data-href="static/css/304ddb56.e5569b2b.chunk.css" href="https://r-xx.bstatic.com/psb/capla/static/css/304ddb56.e5569b2b.chunk.css">
        <link rel="stylesheet" data-href="static/css/108baf03.3c87a2bd.chunk.css" href="https://r-xx.bstatic.com/psb/capla/static/css/108baf03.3c87a2bd.chunk.css">
        <link rel="stylesheet" data-href="static/css/3d3058d2.1cf4c9d1.chunk.css" href="https://r-xx.bstatic.com/psb/capla/static/css/3d3058d2.1cf4c9d1.chunk.css">
        <link rel="stylesheet" data-href="static/css/32199d33.3cc3cf7c.chunk.css" href="https://r-xx.bstatic.com/psb/capla/static/css/32199d33.3cc3cf7c.chunk.css">    
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-nj18pzhn0PymqU4cwI3JG43ugNLTq6aGd7pJ3FfW3nJAs4NKzZsvJ1CgTOnB5k2gC0AxQrm2K5VKf4vK5f4E3g==" crossorigin="anonymous" referrerpolicy="no-referrer" />


        <link rel="stylesheet" data-href="c1_1.css" href="css/c1_1.css">
        <link rel="stylesheet" data-href="c2_1.css" href="css/c2_1.css">
        <link rel="stylesheet" data-href="c3_1.css" href="css/c3_1.css">
        <link rel="stylesheet" data-href="c4.css" href="css/c4.css">
        <link rel="stylesheet" data-href="c5.css" href="css/c5.css"> 
        <link rel="stylesheet" data-href="c6.css" href="css/c6.css"> 
        <style>
            header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 10px 20px;
                background-color: #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            header .logo {
                font-size: 1.5em;
                font-weight: bold;
                color: #333;
            }

            header .navbar a {
                margin: 0 10px;
                color: #333;
                text-decoration: none;
                font-size: 2em;
            }

            header .navbar a:hover {
                color: #007bff;
            }

            header .icons {
                display: flex;
                align-items: center;
            }

            header .icons .fas {
                margin-left: 15px;
                cursor: pointer;
            }

            header .user-fullname {
                font-weight: bold;
                color: #fd7e14;
                margin-left: 20px;
                font-size: 1.6em;
            }

            .icon-link {
                color: #333;
            }

            .icon-link:hover {
                color: #007bff;
            }
            body {
                font-family: Arial, sans-serif;
                background-color: #f5f5f5;
                margin: 0;
                padding: 0;
            }

            .extranet-page-content {
                padding: 20px;
            }

            .title {
                text-align: center;
                font-size: 36px;
                color: #333;
                margin-bottom: 20px;
                padding-bottom: 10px;
                border-bottom: 2px solid #ddd;
            }

            .ext-navigation-top-item__list {
                list-style-type: none;
                padding: 0;
                display: flex;
                justify-content: center;
                margin-bottom: 20px;
            }

            .ext-navigation-top-item {
                margin: 0 10px;
            }

            .ext-navigation-top-item__link {
                text-decoration: none;
                color: #0073e6;
                font-weight: bold;
                padding: 10px 15px;
                display: block;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .ext-navigation-top-item__link:hover {
                background-color: #f1f1f1;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
                background-color: #fff;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            table th, table td {
                border: 1px solid #ddd;
                padding: 10px;
                text-align: left;
            }

            table th {
                background-color: #f2f2f2;
            }

            .description {
                overflow: hidden;
                text-overflow: ellipsis;
                white-space: nowrap;
                max-width: 200px;
                display: inline-block;
            }

            .read-more-btn {
                background-color: #008CBA;
                color: white;
                border: none;
                padding: 5px 10px;
                cursor: pointer;
                margin-top: 5px;
            }

            .read-more-btn:hover {
                background-color: #005f73;
            }

            .full-description {
                white-space: normal;
            }
            .housepage-buttons {
                text-align: center;
                margin-bottom: 20px;
            }

            .create-house-btn,
            .search-house-btn {
                padding: 10px 20px;
                margin: 0 10px;
                font-size: 16px;
                cursor: pointer;
                background-color: #007bff;
                color: white;
                border: none;
                border-radius: 5px;
                transition: background-color 0.3s ease;
            }

            .create-house-btn:hover,
            .search-house-btn:hover {
                background-color: #0056b3;
            }

            .status-icon {
                display: inline-block;
                width: 24px;
                text-align: center;
                margin-right: 5px;
            }

            .status-icon.ready {
                color: white;
            }

            .status-icon.booked {
                color: red;
            }
            .icons i {
                font-size: 2.5rem;
                color: white;
                cursor: pointer;
                margin-right: 2rem;
            }
            body{
                margin-top:20px;
                background-color:#f2f6fc;
                color:#69707a;
            }
            .img-account-profile {
                height: 10rem;
            }
            .rounded-circle {
                border-radius: 50% !important;
            }
            .card {
                box-shadow: 0 0.15rem 1.75rem 0 rgb(33 40 50 / 15%);
            }
            .card .card-header {
                font-weight: 500;
            }
            .card-header:first-child {
                border-radius: 0.35rem 0.35rem 0 0;
            }
            .card-header {
                padding: 1rem 1.35rem;
                margin-bottom: 0;
                background-color: rgba(33, 40, 50, 0.03);
                border-bottom: 1px solid rgba(33, 40, 50, 0.125);
            }
            .form-control, .dataTable-input {
                display: block;
                width: 100%;
                padding: 0.875rem 1.125rem;
                font-size: 0.875rem;
                font-weight: 400;
                line-height: 1;
                color: #69707a;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #c5ccd6;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 0.35rem;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
            }

            .nav-borders .nav-link.active {
                color: #0061f2;
                border-bottom-color: #0061f2;
            }
            .nav-borders .nav-link {
                color: #69707a;
                border-bottom-width: 0.125rem;
                border-bottom-style: solid;
                border-bottom-color: transparent;
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                padding-left: 0;
                padding-right: 0;
                margin-left: 1rem;
                margin-right: 1rem;
            }
            .fa-2x {
                font-size: 2em;
            }

            .table-billing-history th, .table-billing-history td {
                padding-top: 0.75rem;
                padding-bottom: 0.75rem;
                padding-left: 1.375rem;
                padding-right: 1.375rem;
            }
            .table > :not(caption) > * > *, .dataTable-table > :not(caption) > * > * {
                padding: 0.75rem 0.75rem;
                background-color: var(--bs-table-bg);
                border-bottom-width: 1px;
                box-shadow: inset 0 0 0 9999px var(--bs-table-accent-bg);
            }

            .border-start-primary {
                border-left-color: #0061f2 !important;
            }
            .border-start-secondary {
                border-left-color: #6900c7 !important;
            }
            .border-start-success {
                border-left-color: #00ac69 !important;
            }
            .border-start-lg {
                border-left-width: 0.25rem !important;
            }
            .h-100 {
                height: 100% !important;
                font-family: Arial;
            }

            @keyframes buttonClickAnimation {
                0% {
                    transform: scale(1);
                }
                50% {
                    transform: scale(1.1);
                }
                100% {
                    transform: scale(1);
                }
            }


        </style>
    </head
    <!-- --------------------------------------------------- -->
    <body>
        <div data-capla-component="b-mpp-group-extranet-mfe" data-capla-namespace="b-mpp-group-extranet-mfeNdIBScZE">
            <div data-server-rendered="true" class="full-page">
                <div class="extranet-common-header">
                    <header class="ext-header ext-header--animated">
                        <div class="ext-header__container">
                            <section class="ext-header__logo-container">
                                <div>                                     
                                </div>
                                <div data-test-id="property-details" class="ext-header__property-details bui-u-hidden bui-u-show">                                
                                    <!---->
                                </div>
                            </section>
                            <div class="ext-header__side-items">
                                <div class="ext-search__bar" data-v-ed37d1ee>
                                    <button class="ext-search-trigger ext-search-trigger--md-hidden" data-v-ed37d1ee>
                                        <span aria-hidden="true" role="presentation" class="bui-icon bui-icon--medium" data-v-ed37d1ee data-v-ed37d1ee>
                                            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" role="img" aria-hidden="true" class="ext-search-trigger__icon" data-v-ed37d1ee data-v-ed37d1ee>
                                            <path d="M17.464 6.56a8.313 8.313 0 1 1-15.302 6.504A8.313 8.313 0 0 1 17.464 6.56m1.38-.586C16.724.986 10.963-1.339 5.974.781.988 2.9-1.337 8.662.783 13.65s7.881 7.312 12.87 5.192c4.987-2.12 7.312-7.881 5.192-12.87zM15.691 16.75l7.029 7.03a.75.75 0 0 0 1.06-1.06l-7.029-7.03a.75.75 0 0 0-1.06 1.06" data-v-ed37d1ee data-v-ed37d1ee></path>
                                            </svg>
                                        </span>
                                    </button>
                                    <div class="ext-search__wrap" data-v-ed37d1ee>
                                        <div class="ext-search__input-container" data-v-ed37d1ee>
                                            <div class="ext-search-input__wrap" data-v-ed37d1ee>
                                                <input type="text" name="query" aria-label="Search pages and reservations" placeholder="Search pages and reservations" autocomplete="off" data-test-id="search-input" value="" class="ext-search-input">
                                                <button type="button" title="Cancel" aria-label="Cancel" data-test-id="search-cancel-btn" class="ext-search-input__icon-wrap" style="display:none;">
                                                    <span aria-hidden="true" role="presentation" class="bui-icon bui-icon--small bui-icon--color-neutral_alt">
                                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" role="img" aria-hidden="true" class="ext-search-input__icon--transparent ext-search-input__icon ext-search-input__icon--animated">
                                                        <path d="m13.31 12 6.89-6.89a.93.93 0 1 0-1.31-1.31L12 10.69 5.11 3.8A.93.93 0 0 0 3.8 5.11L10.69 12 3.8 18.89a.93.93 0 0 0 1.31 1.31L12 13.31l6.89 6.89a.93.93 0 1 0 1.31-1.31z"></path>
                                                        </svg>
                                                    </span>
                                                </button>
                                                <button type="button" data-test-id="search-icon" title="Search pages and reservations" aria-label="Search pages and reservations" class="ext-search-input__icon-wrap" style="display:;">
                                                    <span aria-hidden="true" role="presentation" class="bui-icon bui-icon--small bui-icon--color-action">
                                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" role="img" aria-hidden="true" class="ext-search-input__icon ext-search-input__icon-search">
                                                        <path d="M17.464 6.56a8.313 8.313 0 1 1-15.302 6.504A8.313 8.313 0 0 1 17.464 6.56m1.38-.586C16.724.986 10.963-1.339 5.974.781.988 2.9-1.337 8.662.783 13.65s7.881 7.312 12.87 5.192c4.987-2.12 7.312-7.881 5.192-12.87zM15.691 16.75l7.029 7.03a.75.75 0 0 0 1.06-1.06l-7.029-7.03a.75.75 0 0 0-1.06 1.06"></path>
                                                        </svg>
                                                    </span>
                                                </button>
                                            </div>
                                        </div>
                                        <!---->
                                    </div>
                                </div>

                                <div class="icons">
                                    <i class="fas fa-search" id="search-btn"></i>
                                    <c:if test="${sessionScope.acc == null}">
                                        <a href="LoginServlet" class="icon-link">
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
                            </div>
                        </div>

                        <div class="ext-header__navigation">
                            <nav class="ext-navigation ext-navigation--groups">
                                <div data-test-id="mobile-trigger" role="button" tabindex="0" class="ext-navigation__mobile-trigger">
                                    <span aria-hidden="true" role="presentation" class="bui-icon bui-icon--medium">
                                        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" role="img" aria-hidden="true" class="ext-navigation__mobile-trigger-icon">
                                        <path d="M2.25 18.753h19.5a.75.75 0 0 0 0-1.5H2.25a.75.75 0 0 0 0 1.5m0-6h19.5a.75.75 0 0 0 0-1.5H2.25a.75.75 0 0 0 0 1.5m0-6h19.5a.75.75 0 0 0 0-1.5H2.25a.75.75 0 0 0 0 1.5"></path>
                                        </svg>
                                    </span>
                                </div>
                                <div class="ext-navigation__inner">
                                    <div class="ext-navigation__inner-container">
                                        <div data-test-id="partner-details" class="ext-navigation__partner-details-container bui-u-hidden@medium">
                                            <div data-test-id="property-details" class="ext-header__property-details bui-u-hidden bui-u-show@small">
                                                <div class="ext-header__property-details-spinner bui-spinner bui-spinner--light bui-spinner--size-small">
                                                    <div class="bui-spinner__inner"></div>
                                                </div>
                                                <!---->
                                            </div>
                                        </div>
                                        <ul class="ext-navigation-top-item__list">
                                            <li data-nav-tag="group_overview" class="ext-navigation-top-item ext-navigation-top-item--active">
                                                <a href="DashboardHostServlet" data-test-id="item-link" class="ext-navigation-top-item__link">
                                                    <span class="ext-navigation-top-item__icon">
                                                        <!---->
                                                    </span>
                                                    <span class="ext-navigation-top-item__title">
                                                        <span class="ext-navigation-top-item__title-text">Group homepage</span>
                                                        <!---->
                                                        <!---->
                                                    </span>
                                                    <!---->
                                                    <!---->
                                                </a>
                                                <div class="ext-navigation-top-item__submenu">
                                                    <!---->
                                                </div>
                                            </li>
                                            <li data-nav-tag="group_reviews" class="ext-navigation-top-item">
                                                <a href="HostComment" data-test-id="item-link" class="ext-navigation-top-item__link">
                                                    <span class="ext-navigation-top-item__icon">
                                                        <!---->
                                                    </span>
                                                    <span class="ext-navigation-top-item__title">
                                                        <span class="ext-navigation-top-item__title-text">Reviews</span>

                                                    </span>

                                                </a>
                                                <div class="ext-navigation-top-item__submenu">
                                                    <!---->
                                                </div>
                                            </li>
                                            <li data-nav-tag="group_bill" class="ext-navigation-top-item">
                                                <a href="BillHostServlet" data-test-id="item-link" class="ext-navigation-top-item__link">
                                                    <span class="ext-navigation-top-item__icon">
                                                        <!---->
                                                    </span>
                                                    <span class="ext-navigation-top-item__title">
                                                        <span class="ext-navigation-top-item__title-text">Bill</span>
                                                        <!---->
                                                        <!---->
                                                    </span>
                                                    <!---->
                                                    <!---->
                                                </a>
                                                <div class="ext-navigation-top-item__submenu">
                                                    <!---->
                                                </div>
                                            </li>                                            
                                        </ul>
                                    </div>
                                </div>
                                <div role="presentation" class="ext-navigation__mobile-overlay"></div>
                            </nav>
                        </div>
                    </header>
                </div>



                <!-- --------------------------------------------------- -->

                <div class="extranet-page-content">
                    <div data-test-element="groups-home">
                        <main id="main-content">
                            <div class="bui-container bui-container--center">
                                <div class="ext-page-header peg-page-header bui-u-hidden-print">
                                    <h1 class="ext-page-header__title bui-text bui-text--variant-headline_2 title">
                                        <span>List Comment</span>
                                    </h1>
                                </div>

                                <div class="house-list bui-spacer--large">
                                    <c:if test="${not empty commentList}">
                                        <table>
                                            <thead>
                                                <tr>
                                                    <th>Comment ID</th>
                                                    <th>Comment</th>
                                                    <th>Date</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach var="comment" items="${commentList}">
                                                    <tr class="comment-item">
                                                        <td>${comment.cid}</td>
                                                        <td>${comment.comment}</td>
                                                        <td>${comment.date}</td>
                                                        <td></td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </c:if>
                                    <c:if test="${empty commentList}">
                                        <p class="no-comments">No comments found.</p>
                                    </c:if>
                                </div>
                            </div>
                        </main>
                    </div>
                </div>



                <!-- ----------------------------------------------------->

                <footer class="extranet-common-footer ext-footer bui-u-hidden-print">
                    <div class="ext-footer__top-content">
                        <div class="bui-container bui-container--center">
                            <div class="ext-footer__top-content__inner">
                                <div class="ext-footer__top-content__first">
                                    <div class="ext-footer__links">
                                        <a href="aboutus" target="_blank" class="ext-footer__link bui-link bui-link--primary">
                                            <span class="bui-link__text">
                                                <span>About Us</span>
                                            </span>
                                        </a>
                                        <a href="" target="_blank" class="ext-footer__link bui-link bui-link--primary">
                                            <span class="bui-link__text">
                                                <span>Privacy and Cookie Statements</span>
                                            </span>
                                        </a>
                                        <a href="" target="_blank" class="ext-footer__link bui-link bui-link--primary">
                                            <span class="bui-link__text">
                                                <span>FAQs</span>
                                            </span>
                                        </a>
                                    </div>
                                </div>
                                <div class="ext-footer__top-content__second">
                                    <div class="ext-footer__ctas">
                                        <!---->
                                        <div class="ext-feedback-cta ext-footer__cta">
                                            <!---->
                                            <div class="ext-feedback__trigger-container">
                                                <button type="button" data-test-id="cta-button" class="footer-feedback-trigger bui-button bui-button--primary">
                                                    <span class="bui-button__text">
                                                        <span>Share your feedback</span>
                                                    </span>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!---->
                        </div>
                    </div>
                    <!-- --------------------------------------------------- -->
                    <div class="ext-footer__bottom-content">
                        <div class="bui-container bui-container--center">
                            <div class="ext-footer__bottom-content__inner">
                                <div class="ext-footer__bottom-content__first">
                                    <span>
                                        © Copyright 
                                        <a href="home" target="_blank" class="bui-link bui-link--primary">
                                            <span class="bui-link__text">StayEasy</span>
                                        </a>
                                        2024
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', () => {
                const readMoreBtns = document.querySelectorAll('.read-more-btn');

                readMoreBtns.forEach(btn => {
                    btn.addEventListener('click', () => {
                        const description = btn.previousElementSibling;
                        description.classList.toggle('full-description');
                        if (description.classList.contains('full-description')) {
                            btn.textContent = 'Read less';
                        } else {
                            btn.textContent = 'Read more';
                        }
                    });
                });
            });
            function buttonHoverEffect(button) {
                button.classList.toggle('hovered');
            }

            function buttonClickAnimation(button) {
                button.style.animation = 'buttonClickAnimation 0.5s';
                setTimeout(() => {
                    button.style.animation = '';
                }, 500);
            }


            function searchByHouseName() {
                alert('Implementing search functionality...');

            }
            function confirmRemove(houseName, form) {
                if (confirm(`Are you sure you want to remove ${houseName}?`)) {
                    form.submit();
                } else {

                }
            }
        </script>

    </body>
</html>
