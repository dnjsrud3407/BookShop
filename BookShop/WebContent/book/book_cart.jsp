<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootshop online Shopping cart</title>
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
    
    <!-- Cutomizing CSS -->
    <link href="css/order.css?ver=1" rel="stylesheet">
    <link href="css/nav.css?ver=1" rel="stylesheet" type="text/css">
    <style type="text/css" id="enject"></style>
    
    <script>
    function inNumber(e){
    	
		if(event.keyCode >=48 && event.keyCode <= 57){
			return true;
		} else {
			event.returnValue=false;
		}
		
	}

	
	function qtyChange(qty, sort, cartNum){
		var changeUrl='CartQtyChange.book?cartNum=' + cartNum + "&qty=";
		var removeUrl='CartRemove.book?cartNum=' + cartNum;
		if(sort === "minus") {
			qty = qty-1;
			alert(qty);		
		} else if(sort === "plus"){
			qty = qty+1;
			alert(qty);
		} else {
			qty = sort.value;
		}
		
		if(qty == 0){
			console.log(qty);
			if(confirm('삭제하시겠습니까?')){
				location.href=removeUrl;				
			}
		} else if(qty < 0){
			console.log(qty);
			alert("수량을 1 ~ 99 사이의 값으로 입력하세요.");
			sort.value = 1;
			console.log(sort);
		} else if(qty > 0){
			console.log(qty);
			location.href=changeUrl+qty;
		}
	}
    
	function qtyRemove(cartNum){
		var removeUrl='CartRemove.book?cartNum=' + cartNum;
			if(confirm('삭제하시겠습니까?')){
				location.href=removeUrl;	
			}
	}
	
	// 체크박스 전체선택/전체해제
	function checkAll(checkBox) {
		if(checkBox.checked == true) {
			$(".checklist").prop("checked", true);
		} else {
			$(".checklist").prop("checked", false);
		}
	}
    
    </script>
  </head>
<body>
<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- Header End====================================================================== -->

<div id="mainBody">
	<div class="container">
	<div class="row" style="margin-left: 10%;">

<!-- Sidebar ================================================== -->
<!-- Sidebar end=============================================== -->
	<div class="span9">
<!--     <ul class="breadcrumb"> -->
<!-- 		<li><a href="Main.me">메인으로</a> <span class="divider">/</span></li> -->
<!-- 		<li class="active">장바구니</li> -->
<!--     </ul> -->
	<h3>장바구니 [ <small>${cartList.size()} Item(s) </small>]</h3>	
<!-- 	<hr class="soft"/> -->
<br>
	
    <form class="form-horizontal" action="BookBuy.book" method="post">
    	<!-- 상품목록 보여주는 테이블 -->
		<table class="table table-bordered">
             <thead>
               <tr>
                 <td style="width: 75px;"><h5 class="h5Custom"><input type="checkbox" onclick="checkAll(this)">&nbsp;전체선택</h5></td>
                 <td><h5 class="h5Custom">상품</h5></td>
                 <td><h5 class="h5Custom">상품명</h5></td>
                 <td><h5 class="h5Custom">수량변경</h5></td>
                 <td><h5 class="h5Custom">가격</h5></td>
                 <td><h5 class="h5Custom">총가격</h5></td>
			</tr>
             </thead>
             <tbody>
             <c:forEach var="cart" items="${cartList }" varStatus="status">         	      	
               <tr>
                 <td><input type="checkbox" class="checklist" name="cartNumList" value="${cart.cartNum }" checked="checked"></td>
                 <td> <img width="60" src="./upload/${cart.bookImage }" alt="상품이미지"/></td>
                 <td>${cart.bookTitle}</td>
			  <td>
				<div class="input-append">
					<input class="span1" style="max-width:34px;" value="${cart.bookEA }" size="16" type="text" required="required" id="qty<c:out value="${status.index}"/>"
					onkeypress="inNumber(this)" onkeyup="javascript:qtyChange(this.value,this,${cart.cartNum})">
					<button class="btn" type="button" onclick="qtyChange(${cart.bookEA },'minus', ${cart.cartNum})"><i class="icon-minus"></i></button>
					<button class="btn" type="button" onclick="qtyChange(${cart.bookEA },'plus', ${cart.cartNum})"><i class="icon-plus"></i></button>
					<button class="btn btn-danger" type="button" onclick="qtyRemove(${cart.cartNum })"><i class="icon-remove icon-white"></i></button>				
				</div>
			  </td>
                 <td>${cart.bookPrice}</td>
                 <td>${cart.bookPrice * cart.bookEA }</td>
               </tr>
             </c:forEach>  
			</tbody>
         </table>
		
		<br><br><br>
		<!-- 결제 정보 테이블 -->	
		<table class="table table-bordered">
		 <tr><td><h5 class="h5Custom">결제예정금액</h5></td></tr>
		 <tr> 
		 <td>
		   <div class="control-group">
			 <label class="control-label">총 상품금액 </label>
			 <div class="controls">
			   <input type="text" name="totalPrice" value="${totalPrice }" readonly="readonly">
			 </div>
		   </div>
		   <div class="control-group">
			 <label class="control-label">배송비 </label>
			 <div class="controls">
			   <c:choose>
			  	 <c:when test="${totalPrice >= 50000}">
					  	   <input type="text" name="shipPrice" value="0" readonly="readonly">
					   	  <c:set var="finalPrice" value="${totalPrice }"></c:set>
			  	 </c:when>
			  	 <c:otherwise>
			  	   <input type="text" name="shipPrice" value="2500" readonly="readonly">
			  	   <c:set var="finalPrice" value="${totalPrice + 2500 }"></c:set>
			  	 </c:otherwise>
			   </c:choose>
			 </div>
		   </div>
		   <div class="control-group">
			 <label class="control-label">최종 결제금액 </label>
			 <div class="controls">
			   <input type="text" name="finalPrice" value="${finalPrice }" readonly="readonly">
			 </div>
		   </div>
		 </td>
		 </tr>
          </table>		
             <div class="centerBtn">
               <input type="button" class="btn btn-large" onclick="location.href='Main.me'" value="쇼핑계속하기"> 
	     <input type="submit" class="btn btn-large" value="주문하기">
	   </div> 
   </form>				  
	
</div>
</div></div>
</div>
<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->
	<div  id="footerSection">
	<div class="container">
		<div class="row">
			<div class="span3">
				<h5>ACCOUNT</h5>
				<a href="login.html">YOUR ACCOUNT</a>
				<a href="login.html">PERSONAL INFORMATION</a> 
				<a href="login.html">ADDRESSES</a> 
				<a href="login.html">DISCOUNT</a>  
				<a href="login.html">ORDER HISTORY</a>
			 </div>
			<div class="span3">
				<h5>INFORMATION</h5>
				<a href="contact.html">CONTACT</a>  
				<a href="register.html">REGISTRATION</a>  
				<a href="legal_notice.html">LEGAL NOTICE</a>  
				<a href="tac.html">TERMS AND CONDITIONS</a> 
				<a href="faq.html">FAQ</a>
			 </div>
			<div class="span3">
				<h5>OUR OFFERS</h5>
				<a href="#">NEW PRODUCTS</a> 
				<a href="#">TOP SELLERS</a>  
				<a href="special_offer.html">SPECIAL OFFERS</a>  
				<a href="#">MANUFACTURERS</a> 
				<a href="#">SUPPLIERS</a> 
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
<span id="themesBtn"></span>
</body>
</html>