<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Bootshop online Shopping cart - book_list.jsp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<!--Less styles -->
   <!-- Other Less css file //different less files has different color scheam
	<link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
	-->
	<!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
	<script src="themes/js/less.js" type="text/javascript"></script> -->
	
<!-- Bootstrap style --> 
    <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css?ver=1" media="screen"/>
    <link href="themes/css/base.css?ver=1" rel="stylesheet" media="screen"/>
<!-- Bootstrap style responsive -->	
	<link href="themes/css/bootstrap-responsive.min.css?ver=1" rel="stylesheet"/>
	<link href="themes/css/font-awesome.css?ver=1" rel="stylesheet" type="text/css">
<!-- Google-code-prettify -->	
	<link href="themes/js/google-code-prettify/prettify.css?ver=1" rel="stylesheet"/>
<!-- fav and touch icons -->
    <link rel="shortcut icon" href="themes/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="themes/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="themes/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
	<link href="css/nav.css?ver=1" rel="stylesheet" type="text/css">
	<style type="text/css" id="enject"></style>
	<link href="css/nav.css?ver=1" rel="stylesheet" type="text/css">
</head>
<script src="./js/jquery-3.4.1.js"></script>
<body>
<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- Header End====================================================================== -->
<div id="mainBody">
	<div class="container">
	<div class="row" style="margin-left: 10%;">
<!-- Sidebar ================================================== -->

<!-- Sidebar end=============================================== -->

<div class="span9">
    <ul class="breadcrumb">
		<li><a href="Main.me">메인으로</a><span class="divider">/</span></li>
		<li class="active">이벤트</li>
    </ul>
	<div id="myTab" class="pull-right">
	</div>
<br class="clr"/>
<div class="tab-content">
	
	<div class="tab-pane  active" id="blockView">
		<ul class="thumbnails">
<c:forEach var="articleList"  items="${articleList}" varStatus="status">
			<li class="span3">
			  <div class="thumbnail">
				<a href="EventUserDetail.bo?boardNum=${articleList.boardNum}"><img src="EventUserDetail.bo?boardNum=${articleList.boardNum}" alt=""/></a>
				<div class="caption">
				  <c:set var="boardTitle" value="${articleList.boardTitle }"/>
				  <c:set var="length" value="${fn:length(boardTitle) }"/>
				  <h5><a href="EventUserDetail.bo?boardNum=${articleList.boardNum}">${fn:substring(boardTitle,0,length- 8)}</a></h5>
				  <p onclick="location.href='EventUserDetail.bo?boardNum=${articleList.boardNum}'"> 
						<c:forEach var="file" items="${articleList.fileList }" varStatus="varStatus"><img src="boardFile/${file.storedFileName }" onclick="location.href='EventUserDetail.bo?boardNum=${articleList.boardNum}'"></c:forEach>
				  </p>
				</div>
			  </div>
			</li>
</c:forEach>
		  </ul>
	<hr class="soft"/>
	</div>
	
	
		   <div class="pagination pagination-centered"> 
        <ul>
			    <c:choose>
			    <c:when test="${pageInfo.page <= 1}">
			   			 <li><a>이전</a></li>
			    </c:when>
			    <c:otherwise>
			          <li><a href="QList.bo?page=${pageInfo.page -1}">이전</a></li>
			    </c:otherwise>
			   </c:choose>  
        	<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1">
          				<li><a href="QList.bo?page=${i}">${i}</a></li>
       		 </c:forEach>  
				   <c:choose>
				    <c:when test="${pageInfo.page >= pageInfo.maxPage}">
				   			 <li><a>다음</a></li>
				    </c:when>
				    <c:otherwise>
				          <li><a href="QList.bo?page=${pageInfo.page +1}">다음</a></li>
				    </c:otherwise>
				   </c:choose>      
        </ul>
      </div>
<!-- </div> -->
<!-- 	<div class="pagination"> -->
<!-- 		<ul> -->
<!-- 		<li><a href="#">&lsaquo;</a></li> -->
<!-- 		<li><a href="#">1</a></li> -->
<!-- 		<li><a href="#">2</a></li> -->
<!-- 		<li><a href="#">3</a></li> -->
<!-- 		<li><a href="#">4</a></li> -->
<!-- 		<li><a href="#">...</a></li> -->
<!-- 		<li><a href="#">&rsaquo;</a></li> -->
<!-- 		</ul> -->
<!-- 	</div> -->
<br class="clr"/>
</div>
</div></div>
</div>
</div>
<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->
    <div  id="footerSection">
    <div class="container">
        <div class="row">
            <div class="span3">
                <h5><a href="NoticeList.bo">공지사항/이벤트</a></h5>
             </div>
            <div class="span3">
                <h5><a href="FAQList.bo">고객센터</a></h5>
             </div>
            <div class="span3">
                <h5>회사이름</h5>
                FAX: 02-0000-0000 <br>
                E-mail: xxx@xxx.xxx<br>  
                부산시 OO구 OO로 00 <a href="#">약도</a>  
             </div>
            <div id="socialMedia" class="span3 pull-right">
                <h5>SOCIAL MEDIA </h5>
                <a href="#"><img width="60" height="60" src="themes/images/facebook.png" title="facebook" alt="facebook"/></a>
                <a href="#"><img width="60" height="60" src="themes/images/twitter.png" title="twitter" alt="twitter"/></a>
                <a href="#"><img width="60" height="60" src="themes/images/youtube.png" title="youtube" alt="youtube"/></a>
             </div> 
         </div>
        <p class="pull-right">&copy; BookShop</p>
    </div><!-- Container End -->
    </div>
<!-- Placed at the end of the document so the pages load faster ============================================= -->
    <script src="themes/js/jquery.js" type="text/javascript"></script>
    <script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="themes/js/google-code-prettify/prettify.js"></script>
    
    <script src="themes/js/bootshop.js"></script>
    <script src="themes/js/jquery.lightbox-0.5.js"></script>
    

</body>
</html>