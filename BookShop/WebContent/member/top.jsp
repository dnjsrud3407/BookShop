<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>마이페이지</title>
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
     <link href="themes/css/mycus.css?ver=1" rel="stylesheet"/>
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
	<style type="text/css" id="enject"></style>
	<style type="text/css">
	
	 .btn2{border: 1px solid #D9D4D4; text-align: center; margin: 2px; padding: 3px;}
	
	</style>
	
	<style type="text/css">
	
	
	
	#neamam{border-top: none; 
	border-left: none;
	 border-right: none;}
	
	
	#neamam2{
	border-top:solid;
	}
	
  
/*   #menu { */

/*     height: 50px; */

/*     background: #333; */

/* } */

#menu {
	color: #4D84AB;
    height: 50px;
	font-size: 1.1em;
    background: #4D84AB;
    border: 1px solid #4D84AB;
    box-sizing: content-box;
}
.main1 {
    width: 600px;
    height: 100%;
    margin: 0 auto;
}
ul.main3 {
    position: absolute;
    margin-bottom: -20px;
    margin-left: -2px;
}
.main1>li {
    float: left;
    width: 20%;
    line-height: 50px;
    text-align: center;
    position: relative;
}
.main1>li:hover .main2 {
    left: 0;
}
.main1>li a {
    display: block;
}
.main1>li a:hover {
    background: #ffffff;
    text-decoration: none;
    color: #4D84AB;
    font-weight: bold;
}
li>a{ color: #000000; }
.main2 {
    position: absolute;
    top: 50px;
    left: -9999px;
	color: #4D84AB;
    background: #ffffff;
    border: 1px solid #4D84AB;
    width: 130%;
}
.main2>li {
    position: relative;
}
.main2>li:hover .main3 {
    left: 100%;
}
.main2>li a, .main3>li a {
	color: #4D84AB;
    margin: 10px;
}
.main2>li a:hover {
	color: #4D84AB;
	text-decoration: underline;
}
.main3 {
    position: absolute;
    top: 0;
	border: 1px solid #4D84AB;
    background: #ffffff;
    color: #4D84AB;
    left: -9999px;
    width: 130%;
    margin-right: 5px;
}
.main3>li a:hover {
    background: #ffffff;
    color: #4D84AB;
}
ul{
    list-style:none;
    list-style-type:none;
}
</style>
	
	  
<script src="./js/jquery-3.4.1.js"></script>


        
<script type="text/javascript">
  // 책 검색하는 작업
  function checkSearch() {
	if($("#srchFld").val() == "") {
		alert('검색어를 입력하세요');
		return false;
	} else {
		return true;
	}
  }
  function getMiddle(type) {
	  // type = 0 -> 베스트 셀러
	  // type = 1 -> 1단계 책
	  // type = 2 -> 2단계 책
	  // type = 3 -> 3단계 책
	  $.ajax({
		  type:"POST",
		  url:"MiddleBook.book?type=" + type,
		  success: function(msg2){	// 베스트셀러
		  	  $(".middle_banner").html(msg2);
		  }
	  });
  }
</script>
	
  </head>
<body>

<%
String uID=(String)session.getAttribute("uID");
if(uID==null){%>
	
	<script type="text/javascript">
	alert("로그인이 필요합니다");
	 location.href = "Login.me";
// 	history.back();
	
	</script>
<%}%>





<jsp:include page="../inc/top.jsp"></jsp:include>