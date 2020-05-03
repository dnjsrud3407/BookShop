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
	
	</style>
	
	<style type="text/css">
	
	
	
	#neamam{border-top: none; 
	border-left: none;
	 border-right: none;}
	
	
	#neamam2{
	border-top:solid;
	}
	
	.neamam3{
 	background-color: #f6f7f8;
	}
	
	
	
	</style>
	
	
	
	
	
	<script type="text/javascript">
	
	
	
	
	
	
	
	
	  window.onload = function () {
		  
		  
		  var ea = document.getElementById('bookEA');
		  var price = document.getElementById('bookPrice');
		  
// 		  ea = Number(ea);
// 		  Number(price);
		  
// 		  alert(ea.value*price.value); //갯수*가격
		  
		  
		  
	  }
	
	
	
	
	</script>
  </head>
<body>
<%

int point=Integer.parseInt(request.getParameter("point"));
%>

<!-- header -->
<jsp:include page="top.jsp"></jsp:include>
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
		<li class="active">주문 내역</li>
    </ul>
	<h3>주문 내역</h3>	
	<br>
 <c:set value="1" var="point"></c:set>
<c:set value="1" var="volume"></c:set>
<c:set value="1" var="eaPrice"></c:set>
<c:set value="-1" var="orderNum"></c:set>
	<c:if test="${orderDetailList!=null}">
	<c:forEach var="list" items="${orderDetailList}" varStatus="status">
 <c:if test="${orderNum != list.orderNum}">

			
			<form>
	<table class="table table-bordered" style="border-top: none; border-left: none;">
              <thead style="border-top: none;">
                <tr style="border-top: none;">
                  <th  id="neamam" colspan="2">배송회원정보</th>
				</tr>
              </thead>
              <tbody>
                <tr>
                </tr>
                <tr>
                  <td class="neamam3">주문번호</td>
                  <td>${list.orderNum}</td>
                </tr> 
                <tr>
                  <td id="neamam2" class="neamam3">주문자</td>
                  <td id="neamam2">${list.u_name}</td>
                </tr>
                <tr>
                  <td class="neamam3">주문자휴대폰</td>
                  <td>${list.phone_num}</td>
                </tr>
                <tr>
                  <td class="neamam3">주문자 ID</td>
                  <td>${list.order_id}</td>
                </tr>
                <tr>
                  <td class="neamam3">주문자이메일</td>
                  <td>${list.email}</td>
                </tr>
                <tr>
                  <td class="neamam3">받으시는분</td>
                  <td>${list.u_name2}</td>
                </tr>
                <tr>
                  <td class="neamam3">배송지</td>
                  <td>${list.address2_post}<br>${list.address2_1} ${list.address2_2}</td>
                </tr>
<!--                 <tr> -->
<!--                   <td class="neamam3">요청사항</td> -->
<!--                   <td>요청사항</td> -->
<!--                 </tr> -->
   
				</tbody>
            </table>
		</form>
		  <c:set value="${list.orderNum}" var="orderNum"></c:set>
			</c:if>
			
			
	<table class="table table-bordered" style="border-top: none; table-layout: fixed;">
              <thead style="border-right: none;">
                <tr style="border-right: none;">
<!--                   <th id="neamam" colspan="3">주문상품목록</th> -->
				</tr>
              </thead>
              <tbody>
              <tr>
              <td id="neamam2">상품명      ${list.bookTitle}</td><td id="neamam2">가격</td><td id="neamam2">합계</td>
              </tr>
                <tr>
                  <td style="vertical-align: middle; text-align: center;"> <img width="120px" height="160px" src="boardFile/${list.bookOriginImage}" alt=""/><br> 
                  상품옵션:${list.bookTitle}  <b style="color: red;">${list.bookEA}개</b><br>
                                           출판사: ${list.bookPublisher}<br>
<!--                  가격 -->
                               <c:set value="<%=point%>" var="point2"></c:set> 
                 	<c:set value="${list.volume}" var="volume"></c:set> 
                    <c:set value="${list.pointValue}" var="point"></c:set> 
                  </td>
                  
                  <td style="text-align: center; vertical-align:middle;">
          <span class="label label-important">금액</span><b style="font-size: 1.5em;">  ${list.bookPrice} 원</b><br>        
          <span class="label label-success">쿠폰</span>    ${couponInfo.coupon_name} <br>
<%--           <span class="label">포인트</span>    ${list.pointValue} <br> --%>
<!--           <span class="label">배송비</span>    2500원 <br> -->
               
                   </td>
                  <td style="text-align: center; vertical-align:middle;"> <b style="font-size: 1.5em;">${list.bookEA * list.bookPrice} 원</b>
<%--                    ${list.pointValue}  --%>
					<br>
                     <span class="label">결제수단</span> ${list.paymentType}결제
                  </td>
                </tr>
                  <tr>
<!--                   <td colspan="3" style="text-align: right;">총 주문금액 : <b>원</b> -->
                  </td>
                </tr>
				</tbody>
            </table>
		</c:forEach>
       </c:if>  
		
		
			<table class="table" style="border-top: none; table-layout: fixed; height: 200px;">
              <thead style="border-right: none;">
                 <tr style="border-top: none;">
                  <th  id="neamam" colspan="2">결제 정보</th>
				</tr>
              </thead>
              	<tbody>
              	  <tr>
                     <td id="neamam2"></td><td id="neamam2"></td> <td id="neamam2"></td> <td id="neamam2"></td> <td id="neamam2"></td>
                 </tr>
                 
                 <tr>
                     <td colspan="" id="" style="vertical-align: middle; ">
				       <c:forEach var="list2" items="${orderDetailList2}" varStatus="status">
                     		 <c:if test="${list2.orderNum eq orderNum}">
                     		  <c:if test="${list2.pointAction==0}">
				          <span class="label  label-info">포인트</span>  
                     	   		${list2.pointValue}<br>
                     	   	   </c:if>
                     	   	   </c:if>
                     </c:forEach>
                      <span class="label label-success">쿠폰할인</span> <b style="font-size: 1.5em;"><c:if test="${couponInfo.volume eq null}">0</c:if>${couponInfo.volume}</b>원 <br>
				      <span class="label">배송비</span><b style="font-size: 1.5em;">${shipPrice}</b>원 <br>
                   </td>
                 
                    <td>
                   </td>
                   
                    <td colspan="3" style="text-align: center; vertical-align: middle; background-color:#f6f7f8; font: 1.5em" >  <span class="label label-important">총 주문 금액</span>  : <b style="color: red; font-size: 1.5em;">  ${total} </b>원
                  </td>
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
				<!-- <a href="login.html">YOUR ACCOUNT</a>
				<a href="login.html">PERSONAL INFORMATION</a> 
				<a href="login.html">ADDRESSES</a> 
				<a href="login.html">DISCOUNT</a>  
				<a href="login.html">ORDER HISTORY</a> -->
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
	<!-- <div id="hideme" class="themeTitle">Style Selector</div>
	<div class="themeName">Oregional Skin</div>
	<div class="images style">
	<a href="themes/css/#" name="bootshop"><img src="themes/switch/images/clr/bootshop.png" alt="bootstrap business templates" class="active"></a>
	<a href="themes/css/#" name="businessltd"><img src="themes/switch/images/clr/businessltd.png" alt="bootstrap business templates" class="active"></a>
	</div>
	<div class="themeName">Bootswatch Skins (11)</div>
	<div class="images style">
		<a href="themes/css/#" name="amelia" title="Amelia"><img src="themes/switch/images/clr/amelia.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="spruce" title="Spruce"><img src="themes/switch/images/clr/spruce.png" alt="bootstrap business templates" ></a>
		<a href="themes/css/#" name="superhero" title="Superhero"><img src="themes/switch/images/clr/superhero.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="cyborg"><img src="themes/switch/images/clr/cyborg.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="cerulean"><img src="themes/switch/images/clr/cerulean.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="journal"><img src="themes/switch/images/clr/journal.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="readable"><img src="themes/switch/images/clr/readable.png" alt="bootstrap business templates"></a>	
		<a href="themes/css/#" name="simplex"><img src="themes/switch/images/clr/simplex.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="slate"><img src="themes/switch/images/clr/slate.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="spacelab"><img src="themes/switch/images/clr/spacelab.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="united"><img src="themes/switch/images/clr/united.png" alt="bootstrap business templates"></a>
		<p style="margin:0;line-height:normal;margin-left:-10px;display:none;"><small>These are just examples and you can build your own color scheme in the backend.</small></p>
	</div>
	<div class="themeName">Background Patterns </div>
	<div class="images patterns">
		<a href="themes/css/#" name="pattern1"><img src="themes/switch/images/pattern/pattern1.png" alt="bootstrap business templates" class="active"></a>
		<a href="themes/css/#" name="pattern2"><img src="themes/switch/images/pattern/pattern2.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern3"><img src="themes/switch/images/pattern/pattern3.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern4"><img src="themes/switch/images/pattern/pattern4.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern5"><img src="themes/switch/images/pattern/pattern5.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern6"><img src="themes/switch/images/pattern/pattern6.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern7"><img src="themes/switch/images/pattern/pattern7.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern8"><img src="themes/switch/images/pattern/pattern8.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern9"><img src="themes/switch/images/pattern/pattern9.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern10"><img src="themes/switch/images/pattern/pattern10.png" alt="bootstrap business templates"></a>
		
		<a href="themes/css/#" name="pattern11"><img src="themes/switch/images/pattern/pattern11.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern12"><img src="themes/switch/images/pattern/pattern12.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern13"><img src="themes/switch/images/pattern/pattern13.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern14"><img src="themes/switch/images/pattern/pattern14.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern15"><img src="themes/switch/images/pattern/pattern15.png" alt="bootstrap business templates"></a>
		
		<a href="themes/css/#" name="pattern16"><img src="themes/switch/images/pattern/pattern16.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern17"><img src="themes/switch/images/pattern/pattern17.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern18"><img src="themes/switch/images/pattern/pattern18.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern19"><img src="themes/switch/images/pattern/pattern19.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern20"><img src="themes/switch/images/pattern/pattern20.png" alt="bootstrap business templates"></a>
		 
	</div>
	</div>
</div> -->
<span id="themesBtn"></span>
</body>
</html>