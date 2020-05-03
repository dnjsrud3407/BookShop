<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bookshop online Shopping cart</title>
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
    <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen"/>
    <link href="themes/css/base.css" rel="stylesheet" media="screen"/>
<!-- Bootstrap style responsive -->	

	<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
	<link href="themes/css/font-awesome.css" rel="stylesheet" type="text/css">
<!-- Google-code-prettify -->	
	<link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
<!-- fav and touch icons -->
    <link rel="shortcut icon" href="themes/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="themes/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="themes/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
	<link href="css/nav.css?ver=1" rel="stylesheet" type="text/css">
	<style type="text/css" id="enject"></style>
	
	<style type="text/css">
	
	
	
	</style>

  </head>
<body>
<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- Header End====================================================================== -->
<div id="mainBody">
	<div class="container">
	<div class="row">
<!-- Sidebar ================================================== -->
	 <jsp:include page="/member/left.jsp"></jsp:include>
<!-- Sidebar end=============================================== -->
	<div class="span9">
    <ul class="breadcrumb">
		<li><a href="OrderList.mo">마이페이지</a> <span class="divider">/</span></li>
		<li class="active">회원확인</li>
    </ul>
	<h3>회원확인</h3>	
	<div class="well">
	<!--
	<div class="alert alert-info fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	<div class="alert fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	 <div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div> -->
	<form class="form-horizontal" action="ModifyFormAction.me" method="post">
		<h4 style="border-bottom: 1px solid gray; text-align: center; line-height: 200%">고객님의 개인정보 보호를 위해 본인확인을 진행합니다.<br>
비밀번호를 입력해주세요.

		</h4>
		<div class="control-group">
<!-- 		<label class="control-label">Title <sup>*</sup></label> -->
		<div class="controls">
<!-- 		<select class="span1" name="days"> -->
<!-- 			<option value="">-</option> -->
<!-- 			<option value="1">Mr.</option> -->
<!-- 			<option value="2">Mrs</option> -->
<!-- 			<option value="3">Miss</option> -->
<!-- 		</select> -->
		</div>
		</div>
	
		 <div class="control-group">
			<label class="control-label" for="inputPassword" style="position: relative; top: 2px; left: 90px">비밀번호 <sup></sup></label>
			<div class="controls">
			  <input type="password" id="inputPassword" name="pw" required="required" style="position: relative; top: -18px; left: 90px"><button type="submit" id="submitButton" class="btn btn-primary" style="margin:20px; position: relative; top: -18px; left: 90px">확인</button>
			  <span id="checkPasswdResult"></span>
			</div>
		 </div>
	
	</form>
	
</div>

</div>
</div>
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

