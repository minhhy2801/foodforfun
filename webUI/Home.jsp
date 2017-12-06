<%-- 
    Document   : index
    Created on : Nov 9, 2017, 2:31:26 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="minhdn" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <title>Home Page</title>
        <link href='http://fonts.googleapis.com/css?family=Fira+Sans:300,400,500,700&amp;subset=latin,cyrillic-ext,cyrillic' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
        <!--        <link rel="icon" type="image/png" href="favicon.png" sizes="32x32">-->

        <link rel="stylesheet" href="css/style.min.css">
        <script src="js/lib/modernizr.js"></script>
    </head>
    <body data-color="pink" data-currency="$">
        <div class="preloader-block js-preloader-block">
            <div class="preloader-container">
                <div class="preloader-icon"></div>
                <div class="mist-block">
                    <div class="mist"></div>
                </div>
            </div>
        </div>
        <div class="preloader-fog">
            <span class="fog-item fog-left-top"><img src="img/fog.png" alt=""></span>
            <span class="fog-item fog-left-bottom"><img src="img/fog.png" alt=""></span>
            <span class="fog-item fog-right-bottom"><img src="img/fog.png" alt=""></span>
            <span class="fog-item fog-right-top"><img src="img/fog.png" alt=""></span>
        </div>
        <div class="page-container">
            <header class="slider-header">
                <div class="header-wrapper">
                    <div class="header-content">
                        <div class="custom-row fullWidth">
                            <div class="medium-3 small-12 custom-column">
                                <div class="logo-wrapper">
                                    <div class="logo-block">
                                        <img src="img/b.svg" class="logo-image l-dis-ib" alt="" style="width: 15%;">
                                    </div>
                                </div>
                            </div>

                            <div class="medium-6 small-12 custom-column">
                                <div class="nav-container">
                                    <nav>
                                        <ul class="navs-list l-tal-c">
                                            <li class="nav-item js-nav-item">
                                                <a href="#speciality">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">Home</p>
                                                </a>
                                            </li>
                                            <li class="nav-item js-nav-item">
                                                <a href="#about">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">About</p>
                                                </a>
                                            </li>

                                            <li class="nav-item">
                                                <minhdn:url var="article" value="MainController">
                                                    <minhdn:param name="flagArticle" value="TitleClick"/>
                                                    <minhdn:param name="currentPage" value="0"/>
                                                    <minhdn:param name="action" value="Article"/> 
                                                </minhdn:url>
                                                <a href="${article}">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">Article</p>
                                                </a>
                                            </li>

                                            <li class="nav-item js-nav-item">
                                                <a href="#contact">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">Contact</p>
                                                </a>
                                            </li>

                                        </ul>
                                    </nav>
                                </div>
                            </div>

                            <div class="medium-3 small-12 custom-column">
                                <ul class="header-social-list l-tal-r">
                                    <minhdn:if test="${empty ID}">
                                        <li class="nav-item">
                                            <a href="Login.jsp">
                                                <p class="nav-text t-fw-l" style="font-size: large;">Login</p>
                                            </a></li>
                                        <li class="nav-item">
                                            <a href="SignUp.jsp">
                                                <p class="nav-text t-fw-l" style="font-size: large;">Sign Up</p>
                                            </a>
                                        </li>
                                    </minhdn:if>
                                    <minhdn:if test="${not empty ID}">
                                        <minhdn:if test="${ROLE eq 'Moderator'}">
                                            <li class="nav-item">
                                                <a href="DashboardController">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">Hi ${ROLE} ${ID}</p>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="LogoutController?logout=${REMEMBER}">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">Log out</p>
                                                </a>
                                            </li>
                                        </minhdn:if>
                                        <minhdn:if test="${ROLE eq 'Editor'}">
                                            <li class="nav-item">
                                                <a href="DashboardController">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">Hi ${ROLE} ${ID}</p>
                                                </a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="LogoutController?logout=${REMEMBER}">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">Log out</p>
                                                </a>
                                            </li>
                                        </minhdn:if>
                                        <minhdn:if test="${ROLE eq 'Member'}">
                                            <li class="nav-item">
                                                <a href="DashboardController">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">Hi ${ROLE} ${ID}</p></a>
                                            </li>
                                            <li class="nav-item">
                                                <a href="LogoutController?logout=${REMEMBER}">
                                                    <p class="nav-text t-fw-l" style="font-size: large;">Log out</p>
                                                </a>
                                            </li>
                                        </minhdn:if>

                                    </minhdn:if>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="header-variation">

                    <div class="header-slider">
                        <div class="l-p-0 custom-row fullWidth">
                            <div class="column large-12 l-p-0">
                                <div class="slider-container js-header-slider">

                                    <div class="slider-item">
                                        <figure class="item-container">
                                            <img src="img/slider/item1.jpg" class="item-image" alt="">
                                            <figcaption class="variation-text l-tal-c">
                                                <div class="text-container">
                                                    <div class="main-content">
                                                        <h1 class="title">Food For Fun</h1>
                                                        <h3 class="subtitle">Taste Of The East</h3>
                                                    </div>
                                                </div>
                                            </figcaption>
                                        </figure>
                                    </div>
                                    <div class="slider-item">
                                        <figure class="item-container">
                                            <img src="img/slider/item2.jpg" class="item-image" alt="">
                                            <figcaption class="variation-text l-tal-c">
                                                <div class="text-container">
                                                    <div class="main-content">
                                                        <h1 class="title">We're in love with food</h1>
                                                        <h3 class="subtitle">There are many variations of passages of Lorem Ipsum available, but the majority have suffered</h3>
                                                    </div>
                                                </div>
                                            </figcaption>
                                        </figure>
                                    </div>
                                    <div class="slider-item">
                                        <figure class="item-container">
                                            <img src="img/slider/item3.jpg" class="item-image" alt="">
                                            <figcaption class="variation-text l-tal-c">
                                                <div class="text-container">
                                                    <div class="main-content">
                                                        <h1 class="title">Food unites us all</h1>
                                                        <h3 class="subtitle">There are many variations of passages of Lorem Ipsum available, but the majority have suffered</h3>
                                                    </div>
                                                </div>
                                            </figcaption>
                                        </figure>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <section role="main">
                <section class="speciality-section">
                    <div class="speciality-wrapper" id="speciality">
                        <div class="row">
                            <div class="large-12 column">
                                <div class="section-title-block">
                                    <h3 class="section-title">Our speciality</h3>

                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="column large-4 medium-6 small-12">
                                <div class="speciality-block l-tal-c">
                                    <img src="img/speciality/special1.jpg" alt="">
                                    <div class="speciality-description l-dis-ib l-tal-l">
                                        <h5 class="speciality-title">PAVEMENT</h5>
                                        <p class="simple-text t-fw-l">Pellentesque et nisl scelerisque, viverra neque et, consectetur ex. Ut maximus ut tortor eget laoreet. Aliquam ullamcorper tellus tortor, vitae ultricies massa auctor id. Donec vel mauris turpis.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="column large-4 medium-6 small-12">
                                <div class="speciality-block l-tal-c">
                                    <img src="img/speciality/special2.jpg" alt="">
                                    <div class="speciality-description l-dis-ib l-tal-l">
                                        <h5 class="speciality-title">STORE</h5>
                                        <p class="simple-text t-fw-l">Pellentesque et nisl scelerisque, viverra neque et, consectetur ex. Ut maximus ut tortor eget laoreet. Aliquam ullamcorper tellus tortor, vitae ultricies massa auctor id. Donec vel mauris turpis.</p>
                                    </div>
                                </div>
                            </div>
                            <div class="column large-4 medium-12 small-12">
                                <div class="speciality-block l-tal-c">
                                    <img src="img/speciality/special3.jpg" alt="">
                                    <div class="speciality-description l-dis-ib l-tal-l">
                                        <h5 class="speciality-title">RESTAURANT</h5>

                                        <p class="simple-text t-fw-l">Pellentesque et nisl scelerisque, viverra neque et, consectetur ex. Ut maximus ut tortor eget laoreet. Aliquam ullamcorper tellus tortor, vitae ultricies massa auctor id. Donec vel mauris turpis.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section class="payment-section">
                    <div class="payment-wrapper " id="about">
                        <div class="row">
                            <div class="column large-12">
                                <div class="section-title-block">
                                    <h3 class="section-title">About Us</h3>
                                    <h5 class="section-subtitle t-fw-l">Our Story Begins In The East</h5>
                                </div>
                                <div class="payment-list-block">
                                    <div class="row">
                                        <h3 class="section-subtitle t-fw-l" style="text-align: center;">
                                            "When you drink the water, remember the spring"
                                        </h3>
                                        <h5 class="section-subtitle t-fw-l" style="text-align: right;">Yin Shui Si Yuan</h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

                <section class="find-section">
                    <div class="find-wrapper bg-fx" id="contact">
                        <div class="fixed-background"></div>
                        <div class="row">
                            <div class="column large-12">
                                <div class="section-title-block t-light-title">
                                    <h3 class="section-title">Find us</h3>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="column large-7 medium-7 find-us-column">
                                <div class="contact-block">
                                    <div class="find-us-dashed">
                                        <div class="row">
                                            <div class="column large-5 medium-5">

                                                <div class="item-container">
                                                    <h4 class="findus-title">
                                                        <i class="fa fa-map-marker"></i>
                                                        <span class="js-show-map">Find us</span>
                                                    </h4>
                                                    <ul class="contacts-list">
                                                        <li class="contact-item">
                                                            76a/5/9 Trần Hữu Trang<br>
                                                            P.10 Quận Phú Nhuận
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div class="item-container">
                                                    <h4 class="findus-title">
                                                        <i class="fa fa-envelope"></i>
                                                        Emails
                                                    </h4>
                                                    <ul class="contacts-list">
                                                        <li class="contact-item">foodforfun@gmail.com</li>

                                                    </ul>
                                                </div>
                                                <div class="item-container last-child">
                                                    <h4 class="findus-title phone-title">
                                                        <i class="fa fa-phone"></i>
                                                        Phones
                                                    </h4>
                                                    <ul class="contacts-list">
                                                        <li class="contact-item">0938 700 452</li>

                                                    </ul>
                                                </div>
                                            </div>

                                        </div>
                                    </div>
                                    <div class="map-container">
                                        <div class="main-map">
                                            <div class="hide-map-btn js-hide-map">
                                                <i class="fa fa-angle-left"></i>
                                                back
                                            </div>
                                            <div id="js-map-container"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="column large-5 medium-5 find-us-column">
                                <div class="working-ours">
                                    <div class="find-us-dashed">
                                        <h4 class="findus-title">Working days & hours</h4>
                                        <ul class="working-ours-list">
                                            <li>Monday: 08.00 am to 11.00 pm</li>
                                            <li>Tuesday: 08.00 am to 11.00 pm</li>
                                            <li>Wednesday: 08.00 am to 11.00 pm</li>
                                            <li>Thursday: 08.00 am to 11.00 pm</li>
                                            <li>Friday: 08.00 am to 11.00 pm</li>
                                            <li>Satrurday: 10.00 am to 11.00 pm</li>
                                            <li>Sunday: 10.00 am to 11.00 pm</li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </section>
            <footer>
                <div class="footer-wrapper">
                    <div class="row">
                        <div class="column large-12">
                            <div class="copyright-block">
                                <p class="copyright">All rights reserved © 2017</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
        <script src="js/mapapi.js"></script>
        <script data-main="js/script.min.js" src="js/lib/require.js"></script>
    </body>
</html>
