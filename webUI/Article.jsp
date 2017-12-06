<%-- 
    Document   : Article
    Created on : Nov 9, 2017, 2:57:57 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="minhnh" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Article Page</title>
        <!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Bootstrap  -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!-- icon fonts font Awesome -->
        <link href="assets/css/font-awesome.min.css" rel="stylesheet">
        <!-- icon fonts linecons -->
        <link href="assets/css/linecons-font-style.css" rel="stylesheet">
        <!-- Import Custom Styles -->
        <link href="assets/css/style.css" rel="stylesheet">
        <!-- Import Responsive Styles -->
        <link href="assets/css/responsive.css" rel="stylesheet">
        <!-- Important owl stylesheet -->
        <link rel="stylesheet" href="assets/css/owl.carousel.css">
    </head>
    <body>
        <!-- Page Top	 -->
        <div id="page-top"></div>
        <!-- Page Top End -->
        <!-- Menu -->
        <%@include file="menuArticle.jsp" %>
        <!-- Menu End -->
        <!-- Page Name Section-->
        <div id="page-name-sec">
            <div class="parallax-style page-name-sec">
                <div class="pattern">
                    <div class="container">
                        <p class="page-name">
                            Our Articles
                        </p><!-- /.page-name -->
                        <p class="page-location">
                            <a href="Home.jsp">Home</a> Article
                        </p><!-- /.page-location -->
                    </div><!-- /.container -->
                </div><!-- /.pattern -->
            </div><!-- /.parallax-style  /.page-name-sec-->
        </div><!-- #page-name-sec -->
        <!-- Page Name Section End-->
        <div id="blog-page" class="page blog-page">
            <div class="container">
                <div class="row">
                    <!-- Post Content -->
                    <div id="post-container" class="col-md-8" role="main">
                        <div class="post-container">
                            <minhnh:if test="${not empty ARTICLE}">
                                <minhnh:forEach var="art" items="${ARTICLE}">
                                    <article class="post">
                                        <minhnh:url var="Detail" value="MainController">
                                            <minhnh:param name="action" value="ArticleDetail"/>
                                            <minhnh:param name="PostID" value="${art.id}"/>
                                        </minhnh:url>
                                        <div class="post-thumbnail">
                                            <img src="images/${art.imgMain}" alt="Post Thumbnail Image" style="height: 500px">
                                        </div><!-- /.post-thumbnail -->
                                        <div class="post-content">
                                            <h2 class="post-title">
                                                <a href="${Detail}">${art.title}</a>
                                            </h2>
                                            <div class="entry">
                                                <div>
                                                    ${art.detail}
                                                </div>
                                            </div><!-- /.entry -->
                                            <footer class="post-meta">
                                                <div class="continue-reading pull-left">
                                                    <a href="${Detail}">Continue reading ...</a>
                                                </div><!-- /.continue-reading -->
                                                <div class="entry-meta pull-right">
                                                    <fmt:formatDate value="${art.dateAccepted}" var="dateAccepted" type="date" pattern="MMM dd, yyyy" />
                                                    <span class="entry-date"><i class="fa fa-clock-o"></i>
                                                        ${dateAccepted}
                                                    </span>
                                                    <span class="author"><i class="fa fa-user"></i>
                                                        ${art.createrName}
                                                    </span>
                                                    <span class="comments-link"><i class="fa fa-comment-o"></i>
                                                        ${art.commentNum}

                                                    </span>

                                                </div><!-- /.entry-meta -->
                                            </footer><!-- /.post-meta -->
                                        </div><!-- /.post-content --> 
                                    </article><!-- /.post -->
                                </minhnh:forEach>
                            </minhnh:if>

                            <nav class="paging-navigation" role="navigation">
                                <minhnh:url var="prePageClick" value="MainController">
                                    <minhnh:param name="flagArticle" value="ChangePage"/>
                                    <minhnh:param name="currentPage" value="-1"/>
                                    <minhnh:param name="action" value="Article"/>
                                </minhnh:url>
                                <a class="prev page-numbers pull-left" href="${prePageClick}" title="Previous"
                                   <minhnh:if test="${PREPAGE eq 1}"> style="visibility: hidden;"</minhnh:if>><i class="fa fa-chevron-left"></i></a>
                                    <minhnh:url var="nextPageClick" value="MainController">
                                        <minhnh:param name="flagArticle" value="ChangePage"/>
                                        <minhnh:param name="currentPage" value="1"/>
                                        <minhnh:param name="action" value="Article"/>
                                    </minhnh:url>
                                <a class="next page-numbers pull-right" href="${nextPageClick}" title="Next"
                                   <minhnh:if test="${(NEXTPAGE eq 'finalPage') or (NEXTPAGEVIEW eq 'emptyPage')}"> style="visibility: hidden;"</minhnh:if>><i class="fa fa-chevron-right"></i></a>
                                </nav><!-- /.paging-navigation -->

                            </div><!-- /.post-container -->
                        </div><!-- /.col-md-8 -->
                        <!-- Post Content End -->
                        <!-- Side Bar -->
                        <div id="blog-sidebar" class="col-md-4">
                            <div class="blog-sidebar">
                                <aside class="widget widget_categories">
                                    <h3 class="widget-title">
                                        Categories
                                    </h3><!-- /.widget-title -->
                                    <ul>
                                    <minhnh:forEach var="topCate" items="${TOPCATE}">
                                        <minhnh:url var="article" value="MainController">
                                            <minhnh:param name="cateID" value="${topCate.id}"/>
                                            <minhnh:param name="flagArticle" value="CateClick"/>
                                            <minhnh:param name="currentPage" value="0"/>
                                            <minhnh:param name="action" value="Article"/> 
                                        </minhnh:url> 
                                        <li><a href="${article}" >${topCate.name}</a></li>
                                        </minhnh:forEach>
                                </ul>
                            </aside><!-- /.widget -->

                            <aside class="widget widget_recent_entries">
                                <h3 class="widget-title">
                                    Popular Posts
                                </h3><!-- /.widget-title -->
                                <ul class="latest-post">
                                    <minhnh:forEach var="topPost" items="${TOPPOST}">
                                        <li>
                                            <div class="recent-post-details">
                                                <minhnh:url var="topDetail" value="MainController">
                                                    <minhnh:param name="action" value="ArticleDetail"/>
                                                    <minhnh:param name="PostID" value="${topPost.id}"/>
                                                </minhnh:url>
                                                <a class="post-title" href="${topDetail}">${topPost.title}</a><br>
                                                <fmt:formatDate value="${topPost.dateAccepted}" var="topDateAccepted" type="date" pattern="dd MMM" />
                                                <fmt:formatDate value="${topPost.dateAccepted}" var="timeTag" type="date" pattern="yyyy-MM-dd" />
                                                <time datetime="${timeTag}"><i class="fa fa-clock-o"></i>${topDateAccepted}</time> 
                                                <i class="fa fa-user"></i> ${topPost.createrName}
                                            </div><!-- /.recent-post-details -->
                                        </li>
                                    </minhnh:forEach>

                                </ul><!-- /.latest-post -->
                            </aside><!-- /.widget -->



                            <aside class="widget widget_tagcloud clearfix">
                                <h3 class="widget-title">
                                    Tag Clouds
                                </h3><!-- /.widget-title -->
                                <div class="tag-cloud-wrapper">
                                    <minhnh:forEach var="topTag" items="${TOPTAG}">
                                        <minhnh:url var="article" value="MainController">
                                            <minhnh:param name="tagID" value="${topTag.id}"/>
                                            <minhnh:param name="flagArticle" value="TagClick"/>
                                            <minhnh:param name="currentPage" value="0"/>
                                            <minhnh:param name="action" value="Article"/> 
                                        </minhnh:url> 
                                        <a href="${article}">${topTag.name}</a>
                                    </minhnh:forEach>
                                </div><!-- /.tag-cloud-wrapper -->
                            </aside><!-- /.widget -->					
                        </div><!-- /.blog-sidebar -->

                    </div><!-- /#blog-sidebar /.col-md-4 -->
                    <!-- Side Bar End -->
                </div><!-- /.row -->	
            </div><!-- /.container -->
        </div><!-- /#blog-page -->

        <div id="scroll-to-top">
            <span>
                <i class="fa fa-chevron-up"></i>    
            </span>
        </div>


        <!-- Include modernizr-2.8.0.min.js -->
        <script src="assets/js/modernizr-2.8.0.min.js"></script>
        <!-- Include jquery.min.js plugin -->
        <script src="assets/js/jquery-2.1.0.min.js"></script>
        <!-- Include JavaScript Plugins -->
        <script src="assets/js/plugins.js"></script>
        <!-- Include JavaScript Functions -->
        <script src="assets/js/functions.js"></script>
        <!-- Include jquery.parallax.js Plugins -->
        <script src="assets/js/jquery.parallax.js"></script>
        <!-- Include jquery.fitvids.js Plugins -->
        <script src="assets/js/jquery.fitvids.js"></script>

        <!--	<script>
                        jQuery(document).ready(function(){
                                 // Target your .container, .wrapper, .post, etc.
                                 jQuery(".video-container").fitVids();
                                });
                </script>-->
    </body>
</html>
