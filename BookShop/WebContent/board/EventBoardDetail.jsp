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
	
</head>
<body>
<c:if test="${result == 4}">
<script type="text/javascript">
alert("이미 쿠폰이 있습니다.");
</script>
</c:if>
<c:if test="${result == 1}">
<script type="text/javascript">
alert("쿠폰이 발급 되었습니다!");
// 책 검색하는 작업
function checkSearch() {
	if($("#srchFld").val() == "") {
		alert('검색어를 입력하세요');
		return false;
	} else {
		return true;
	}
}
</script>
</c:if>


<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- Header End====================================================================== -->

<!-- Sidebar ================================================== -->

<!-- Sidebar end=============================================== -->
<div id="mainBody">
	<div class="container">
	<div class="row" style="margin-left: 10%;">
<div class="span9" id="mainCol">
    <ul class="breadcrumb">
		<li><a href="index.html">이벤트</a> <span class="divider">/</span></li>
		<li class="active">이벤트 상세 페이지</li>
    </ul>
	<h3> ${article.boardTitle}&nbsp;&nbsp;&nbsp;<input type="button" value="쿠폰발급받기"></h3>	
	<hr class="soft"/>
	<p>
	<c:forEach var="file" items="${article.fileList }" varStatus="varStatus"><img src="boardFile/${file.storedFileName }"></c:forEach>
	 <br><br>${article.boardContent}<br><br>
<!-- <img id="Image-Maps-Com-image-maps-2020-04-20-041929" src="img/evt_NewMember2018.jpg" border="0" width="940" height="850" orgWidth="940" orgHeight="850" usemap="#image-maps-2020-04-20-041929" alt="" /> -->
<!-- <map name="image-maps-2020-04-20-041929" id="ImageMapsCom-image-maps-2020-04-20-041929"> -->
<%-- <area  alt="" title="" href="GetCoupon.adb?uID=${sessionScope.uID}&cID=13" shape="rect" coords="258,593,683,698" style="outline:none;" target="_self"     /> --%>
<%-- <area shape="rect" coords="938,848,940,850" alt="Image Map" style="outline:none;" title="Image Map" href="GetCoupon.adb?uID=${sessionScope.uID}&cID=13" /> --%>
<!-- </map> -->
	
	</p>
</div>
</div></div>
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