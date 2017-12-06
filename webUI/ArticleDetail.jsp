<%-- 
    Document   : ArticleDetail
    Created on : Nov 9, 2017, 3:28:39 PM
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
        <!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->
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
        <div id="page-top"></div>
        <%@include file="menuArticle.jsp" %>

        <!-- Page Name Section-->
        <div id="page-name-sec">
            <div class="parallax-style page-name-sec">
                <div class="pattern">
                    <div class="container">
                        <p class="page-name">
                            Our Articles
                        </p><!-- /.page-name -->
                        <p class="page-location">
                            <minhdn:url var="article" value="MainController">
                                <minhdn:param name="flagArticle" value="TitleClick"/>
                                <minhdn:param name="currentPage" value="0"/>
                                <minhdn:param name="action" value="Article"/> 
                            </minhdn:url>
                            <a href="Home.jsp">Home</a><a href="${article}">Article</a>Single Post
                        </p><!-- /.page-location -->
                    </div><!-- /.container -->
                </div><!-- /.pattern -->
            </div><!-- /.parallax-style  /.page-name-sec-->
        </div><!-- #page-name-sec -->
        <!-- Page Name Section End-->
        <div id="blog-page" class="page single-page">
            <div class="container">
                <div class="row">
                    <!-- Post Content -->
                    <div  id="post-container" class="col-md-8" role="main">
                        <div class="post-container">
                            <article class="post type-post">
                                <div class="post-thumbnail">
                                    <img src="images/${DETAIL.imgMain}" alt="Post Thumbnail Image" style="height: 500px">
                                </div><!-- /.post-thumbnail -->
                                <div class="post-content">
                                    <h2 class="post-title">
                                        <strong>${DETAIL.title}</strong>
                                    </h2>
                                    <div class="entry">
                                        ${DETAIL.detail}                                         
                                    </div><!-- /.entry -->
                                    <footer class="post-meta">
                                        <div class="entry-meta pull-left">
                                            <span class="entry-date">
                                                <fmt:formatDate value="${DETAIL.dateAccepted}" var="dateAccepted" type="date" pattern="MMM dd, yyyy" />
                                                <i class="fa fa-clock-o"></i>
                                                ${dateAccepted}
                                            </span>
                                            <span class="tag-links">
                                                <i class="fa fa-tag"></i>
                                                <minhnh:forEach var="tagList" items="${LISTTAG}">

                                                    <minhnh:url var="article" value="MainController">
                                                        <minhnh:param name="cateID" value="${tagList.id}"/>
                                                        <minhnh:param name="flagArticle" value="TagClick"/>
                                                        <minhnh:param name="currentPage" value="0"/>
                                                        <minhnh:param name="action" value="Article"/> 
                                                    </minhnh:url> 



                                                    <a href="${article}" rel="tag">${tagList.name}</a>                                                
                                                </minhnh:forEach>
                                            </span>
                                        </div><!-- /.entry-meta -->
                                        <div class="entry-meta pull-right" style="font-size: large;">
                                            <minhnh:url var="likeNum" value="MainController">
                                                <minhnh:param name="isLike" value="${ISLIKE}"/>
                                                <minhnh:param name="postID" value="${POSTID}"/>
                                                <minhnh:param name="action" value="LikeNumber"/>
                                            </minhnh:url>
                                            <minhnh:if test="${not empty ID}">
                                                <a href="${likeNum}" style="color: black"><i class="fa fa-heart-o" 
                                                                                             <minhnh:if test="${ISLIKE eq true}">style="color: red"</minhnh:if>>${LIKEOFPOST}</i></a>
                                                </minhnh:if>
                                                <minhnh:if test="${empty ID}">
                                                <i class="fa fa-heart-o">${LIKEOFPOST}</i>
                                            </minhnh:if>
                                            <a href="#"><i class="fa fa-comment-o">${CMTNUM}</i></a>
                                        </div>
                                    </footer><!-- /.post-meta -->
                                    <div class="box-title">
                                        <div class="pull-right">
                                            <i class="fa fa-user "></i> ${DETAIL.createrName}
                                        </div>     
                                        <div class="entry-meta pull-left">
                                            <minhnh:if test="${(DETAIL.state eq 'Pending') or (DETAIL.state eq 'Ban')}">
                                                <minhnh:if test="${ROLE eq 'Editor'}">
                                                        <minhnh:url var="activePost" value="MainController">
                                                            <minhnh:param name="action" value="ActivePost"/>
                                                            <minhnh:param name="PostId" value="${POSTID}"/>
                                                        </minhnh:url>
                                                        <a href="${activePost}"><i class="fa fa-check-circle-o">Active</i></a>
                                                </minhnh:if>
                                            </minhnh:if>
                                            <minhnh:if test="${ID eq DETAIL.createrID}">
                                                <minhnh:url var="editPost" value="MainController">
                                                    <minhnh:param name="action" value="EditPost"/>
                                                    <minhnh:param name="PostId" value="${POSTID}"/>
                                                </minhnh:url>
                                                <a href="${editPost}"><i class="fa fa-pencil-square-o">Edit</i></a>
                                            </minhnh:if>
                                            <minhnh:if test="${(DETAIL.state eq 'Pending') or (DETAIL.state eq 'Active')}">
                                                <minhnh:if test="${(ID eq DETAIL.createrID) or (ROLE eq 'Editor')}">
                                                    <minhnh:url var="deletePost" value="MainController">
                                                        <minhnh:param name="action" value="DeletePost"/>
                                                        <minhnh:param name="PostId" value="${POSTID}"/>
                                                    </minhnh:url>
                                                    <a href="${deletePost}"><i class="fa fa-trash-o">Delete</i></a>
                                                </minhnh:if> 
                                            </minhnh:if>
                                        </div>                                        
                                    </div><!-- /.box-title -->
                                </div><!-- /.post-content --> 
                            </article><!-- /.post -->
                            <div id="comments" class="comments-area">

                                <h3 class="comments-title"> 
                                    ${CMTNUM} COMMENT	
                                </h3><!-- /.comments-title -->

                                <ol class="comment-list">
                                    <li class="comment parent" id="comments-id-1">
                                        <minhnh:forEach var="listCmt" items="${LISTCMT}">
                                            <article class="comment-body">
                                                <div class="comment-metadata">
                                                    <h5 class="comment-author">
                                                        ${listCmt.nameOfCreater}
                                                    </h5><!-- /.comment-author -->
                                                    <time datetime="2014-04-22T14:52:56+00:00">
                                                        <fmt:formatDate value="${listCmt.dateCreated}" var="dateCre" type="date" pattern="MMM dd,yy" />
                                                        <fmt:formatDate value="${listCmt.dateCreated}" var="timeCre" type="time" pattern="hh:mm:ss a" />
                                                        ${dateCre} at ${timeCre} 					
                                                    </time>
                                                    <span class="reply pull-right">
                                                        <minhnh:url var="updateComment" value="MainController">
                                                            <minhnh:param name="postId" value="${POSTID}"/>
                                                            <minhnh:param name="txtComment" value="${listCmt.detail}"/>
                                                            <minhnh:param name="commentID" value="${listCmt.commentID}"/>
                                                            <minhnh:param name="action" value="UpdateComment"/>
                                                        </minhnh:url>
                                                        <minhnh:if test="${ID eq listCmt.createrID}">
                                                            <a class="comment-reply-link" href="${updateComment}">Update</a> 
                                                        </minhnh:if>
                                                    </span><!-- /.reply -->
                                                    <!--  day la block comt-->
                                                    <span class="reply pull-right">
                                                        <minhnh:url var="blockComment" value="MainController">
                                                            <minhnh:param name="postId" value="${POSTID}"/>
                                                            <minhnh:param name="commentId" value="${listCmt.commentID}"/>
                                                            <minhnh:param name="action" value="BlockComment"/>
                                                        </minhnh:url>
                                                        <minhnh:if test="${(ID eq listCmt.createrID) or (ROLE eq 'Editor')}">
                                                            <a class="comment-reply-link" href="${blockComment}">Delete</a>
                                                        </minhnh:if>
                                                    </span>
                                                </div><!-- /.comment-metadata -->
                                                <div class="comment-content">
                                                    <p>${listCmt.detail}</p>
                                                </div><!-- .comment-content -->
                                            </article><!-- /.comment-body -->
                                        </minhnh:forEach>
                                    </li><!-- /.comment -->

                                </ol><!-- /.comment-list -->
                                <minhnh:if test="${(DETAIL.state eq 'Active')}">
                                    <div id="respond" class="comment-respond">
                                        <h4 id="reply-title" class="comment-reply-title">
                                            Leave a Reply
                                        </h4><!-- /#reply-title -->
                                        <font color="red">${ERROR}</font>
                                        <form action="MainController" method="post" id="commentform" class="comment-form">
                                            <input id="author" class="form-control" name="author" type="text" value="${sessionScope.ID}" size="30" aria-required="true" placeholder="Name" title="Name" readonly="true">
                                            <textarea name="txtComment" class="form-control" cols="45" rows="8" aria-required="true" placeholder="Comment" title="Comment" required >${CMTDTO.detail}</textarea>
                                            <input type="hidden" name="postID" value="${POSTID}"/>
                                            <input type="hidden" name="commentID" value="${CMTDTO.commentID}"/>
                                            <button name="action" value="Comment" class="btn btn-sm btn-default full-width btn-effect" type="submit" id="submit">Post Comment</button>
                                        </form><!-- /#commentform -->
                                    </div><!-- /#respond -->
                                </minhnh:if>
                            </div><!-- /#comments -->

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
                                    Tags
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
    </body>
</html>
