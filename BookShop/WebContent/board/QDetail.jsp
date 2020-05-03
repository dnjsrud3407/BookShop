<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>문의내역</title>
    
    
    <script src="js/jquery-3.4.1.js"></script>
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
     <link href="themes/css/mycus.css" rel="stylesheet"/>
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
	<style type="text/css" id="enject"></style>
	
	<style type="text/css">
	
	 .btn2{border: 1px solid #D9D4D4; text-align: center; margin: 2px; padding: 3px;}
	 
	 #qa:not(#b) {color: gray; height: 100px; text-align: center; vertical-align: middle; border-right: none; 

	 }
	 #qaback {background: #f5f5f5;}
	</style>
	
	
	
		<script type="text/javascript">
			
			
			function deleteConFirm() {
				
				alert("삭제하시겠습니까?");
					return ture;
				}
				
			
			
			
			
			
	</script>
	
	

  </head>
<body>

<%String status=request.getParameter("status"); %>
<!-- header -->
<jsp:include page="/member/top.jsp"></jsp:include>
<!-- header -->

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
		<li class="active">1:1문의내용</li>
    </ul>
	<h3>1:1문의내용</h3>	
	<br>
	
<!-- 	enctype="multipart/form-data" -->
<table class="table table-bordered" style="border-top: none; border-left: none;">
              <thead style="border-top: none;">
                <tr style="border-top: none;">
                  <td>제목</td>
                  <th colspan="2"  style="font-size: 1.5em;">[${boardBean.k2}] &nbsp;&nbsp;&nbsp; ${boardBean.boardTitle}</th>
				</tr>
              </thead>
              <tbody>
              
                <tr>
                  <td id="neamam2">작성자 : ${boardBean.boardWriter}</td>
                  <td id="neamam2">상담상태 : <%=status %></td>
                  <td id="neamam2">등록일자 :${boardBean.boardRegTime}</td>
                </tr> 
              
                <tr>
                <td id="qa"><b style="font-size: 3em;">Q</b>
                <p id="b">질문하신 내용</p>
                </td>
                  <td colspan="2" style="border-left: none;">${boardBean.boardContent}<br></td>
                  
                </tr>
				<tr>
				 <td colspan="3" style="text-align: right;"> <a class=btn2 href="QModifyForm.bo?boardNum=${boardBean.boardNum}&kID=${boardBean.kID}">수정</a><a class=btn2 onclick="javascript:deleteConFirm()" href="QDeletePro.bo?boardNum=${boardBean.boardNum}&kID=${boardBean.kID}">삭제</a></td>
				</tr>                
                
                 <tr id="qaback">
                <td id="qa"><b style="font-size: 3em;">A</b>
                <p id="b">답변 내용</p>
                
                </td>
                <c:if test="${boardBean2.boardContent eq null}">
                  <td colspan="2" style="border-left: none;"><b>답변대기</b><br> 안녕하십니까 고객님!<br>
                 	고객님의 문의를 확인중에 있습니다.<br>
                 	담당자가 확인 후 신속히 처리해드리겠습니다.
                   </td>
                </c:if>
                  <td colspan="2" style="border-left: none;">${boardBean2.boardContent}</td>
                </tr>
                
                <tr>
                  <td colspan="3">${boardBean.storedFileName}</td>
                </tr>
            
   
				</tbody>
            </table>
	
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
<!-- 				<a href="login.html">YOUR ACCOUNT</a> -->
<!--  				<a href="login.html">PERSONAL INFORMATION</a>  -->
<!--  				<a href="login.html">ADDRESSES</a>   -->
<!-- 				<a href="login.html">DISCOUNT</a>    -->
<!-- 				<a href="login.html">ORDER HISTORY</a> -->
			 </div>
			<div class="span3">
				<h5><a href="FAQList.bo">고객센터</a></h5>
		<!-- 		<a href="contact.html">CONTACT</a>  
				<a href="register.html">REGISTRATION</a>  
				<a href="legal_notice.html">LEGAL NOTICE</a>  
				<a href="tac.html">TERMS AND CONDITIONS</a> 
				<a href="faq.html">FAQ</a> -->
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
		<p class="pull-right">&copy; Bootshop</p>
	</div><!-- Container End -->
	</div>
<!-- Placed at the end of the document so the pages load faster ============================================= -->
	<script src="themes/js/jquery.js" type="text/javascript"></script>
	<script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="themes/js/google-code-prettify/prettify.js"></script>
	
	<script src="themes/js/bootshop.js"></script>
    <script src="themes/js/jquery.lightbox-0.5.js"></script>
	
	<!-- Themes switcher section ============================================================================================= -->
<div id="secectionBox">
<link rel="stylesheet" href="themes/switchthemeswitch.css" type="text/css" media="screen" />
<script src="themes/switch/theamswitcher.js" type="text/javascript" charset="utf-8"></script>
	<div id="themeContainer">

<span id="themesBtn"></span>
</body>
</html>