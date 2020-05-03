<%@page import="vo.CartBean"%>
<%@page import="java.util.ArrayList"%>
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
    <link href="css/order.css?ver=2" rel="stylesheet">
	<link href="css/nav.css?ver=1" rel="stylesheet" type="text/css">
  
    <!-- jQuery -->
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<!-- iamport.payment.js -->
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" charset="utf-8">
function check() {
	var name = $("#u_name").val();
	var post = $("#address_post").val();
	var address_1 = $("#address_1").val();
	var address_2 = $("#address_2").val();
	var phone_num = $("#phone_num").val();
	if(name == '' || post == '' || address_1 == '' || address_2 == '' || phone_num == ''){
 		alert('배송지를 입력해주세요');
 		return false;
 	} 	
	requestPay();
}
function requestPay() {
    var IMP = window.IMP; // 생략가능
    IMP.init('imp39182007');
    var totalPrice = $("#finalPrice").val();
    var bookTitle = $("#payTitle").val();
    
    IMP.request_pay({
        pg : 'inicis', // version 1.1.0부터 지원.
        pay_method : 'card',
        merchant_uid : 'merchant_' + new Date().getTime(),
        name : bookTitle,
        amount : 100,
        buyer_email : 'iamport@siot.do',
        buyer_name : '구매자이름',
        buyer_tel : '010-1234-5678',
        buyer_addr : '서울특별시 강남구 삼성동',
        buyer_postcode : '123-456',
        m_redirect_url : 'https://www.yourdomain.com/payments/complete'
    }, function(rsp) {
        if ( rsp.success ) {
            document.fr.submit();
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;
        }
        alert(msg);
    });
}
function qtyRemove(cartNum){
	var removeUrl='CartRemove.book?cartNum=' + cartNum;
		if(confirm('삭제하시겠습니까?')){
			location.href=removeUrl;	
		}
}
// 쿠폰 적용
function couponDis(finalPrice){
	var beforePrice = parseInt(finalPrice);
	
	// 셀렉트에 선택된 num(value로) 구하기, volume(num를 id로 가지는)구하기 
	var num = "#" + $("#couponList option:selected").val();
	var volume = parseInt($(num).val());
	
	// 쿠폰이 없는 경우
	if(num == "#-1") {
		alert('사용가능한 쿠폰이 없습니다');
		volume = 0;		// 쿠폰 초기화
	} 
	// 쿠폰 선택하지 않고 적용 누른 경우
	else if(num == "#0") {
		alert('쿠폰을 선택해주세요');
		volume = 0;		// 쿠폰 초기화
	}
	
	$("#disCoupon").val(volume);
	// 사용한 포인트 가져오기
	var point = parseInt($("#disPoint").val());
	// 최종 결제금액 적용
	$("#finalPrice").val(beforePrice-point-volume);
}
// 포인트 적용
function pointDis(finalPrice, memberPoint){
	// 포인트 입력한 값, 사용자 포인트 값, 총 결제금액
	var point = parseInt($("#pointInput").val());
	var memberPoint = parseInt(memberPoint);
	var beforePrice = parseInt(finalPrice);
	
	// 입력한 포인트가 보유한 포인트 보다 클 때
	if(point > memberPoint) {
		alert('사용가능한 포인트는 ' + memberPoint + 'P 입니다.');
		$("#pointInput").val(0);	// 포인트 Input 박스 초기화
		point = 0;	// 포인트 초기화
	}
	$("#disPoint").val(point);
	// 사용한 쿠폰값 가져오기
	var coupon = parseInt($("#disCoupon").val());
	// 최종 결제금액 적용
	$("#finalPrice").val(beforePrice-point-coupon);
}
// 배송지 라디오 버튼 세팅
function address(type,u_name,post,add1,add2,phone) {
	$("#u_name").val(u_name);
	$("#address_post").val(post);
	$("#address_1").val(add1);
	$("#address_2").val(add2);
	$("#phone_num").val(phone);
	// 최근 배송지, 회원정보와 동일
	if(type == "1" || type == "2") {
		$("#u_name").prop("readonly",true);
		$("#address_post").prop("readonly",true);
		$("#address_1").prop("readonly",true);
		$("#address_2").prop("readonly",true);
		$("#phone_num").prop("readonly",true);	
		$("#addSearch").html("");
	} else if(type == "3") {	// 새로 입력
		$("#u_name").prop("readonly",false);
		$("#address_2").prop("readonly",false);
		$("#phone_num").prop("readonly",false);
		$("#addSearch").html("<input type='button' class='btn' name='address_btn' value='주소검색' onclick='address_search()'>");
	}
}
</script>
<!-- 주소 찾기 -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function address_search() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('address_post').value = data.zonecode;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
	                document.getElementById("address_1").value = roadAddr + ' ' + extraRoadAddr;
                } else {
	                document.getElementById("address_1").value = roadAddr;
                }
            }
        }).open();
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
<%--     <jsp:include page="../inc/menu.jsp"></jsp:include> --%>
<!-- Sidebar end=============================================== -->
	<!-- 결제하는 Form -->		
	<form class="form-horizontal" action="BookBuyPro.book" name="fr" method="post">
	
	<div class="span9">
<!--     <ul class="breadcrumb"> -->
<!-- 		<li><a href="Main.me">메인으로</a> <span class="divider">/</span></li> -->
<!-- 		<li class="active">주문/결제</li> -->
<!--     </ul> -->
	<h3>주문/결제 [ <small>${cartList.size()} Item(s) </small>]</h3>	
<!-- 	<hr class="soft"/> -->
<br>	
	<!-- 상품목록 보여주는 테이블 -->
		<table class="table table-bordered">
             <thead>
               <tr>
                 <td><h5 class="h5Custom">상품</h5></td>
                 <td><h5 class="h5Custom">상품명</h5></td>
                 <td><h5 class="h5Custom">수량</h5></td>
                 <td><h5 class="h5Custom">총가격</h5></td>
			</tr>
             </thead>
             <tbody>
             <c:forEach var="cart" items="${cartList }" varStatus="status">
             	<input type="hidden" name="cartNum${status.index }" value="${cart.cartNum }">
             	<input type="hidden" name="bookID${status.index }" value="${cart.bookID }">
             	<input type="hidden" name="bookTitle${status.index }" value="${cart.bookTitle }">
             	<input type="hidden" name="bookPrice${status.index }" value="${cart.bookPrice }">
             	<input type="hidden" name="bookEA${status.index }" value="${cart.bookEA }">
             	<c:choose>
             		<c:when test="${fn:length(cartList) > 1}">
             			<input type="hidden" id="payTitle" value="${cart.bookTitle } 외 ${fn:length(cartList)}건">
             		</c:when>
            		<c:otherwise>
             			<input type="hidden" id="payTitle" value="${cart.bookTitle }">
             		</c:otherwise>   
             	</c:choose>           	      	
               <tr>
                 <td><img width="60" src="./upload/${cart.bookImage }" alt="상품이미지"/></td>
                 <td>${cart.bookTitle}</td>
			     <td>${cart.bookEA }</td>
                 <td>${cart.bookPrice * cart.bookEA}</td>
               </tr>
             </c:forEach>  
			</tbody>
         </table>
<!-- 	<hr class="soft"/> -->
	<br><br><br>
<%-- 	<input type="hidden" name="recName" value="${memberBean.u_name }"> --%>
<%-- 	<input type="hidden" name="recPhone" value="${memberBean.tell_num }"> --%>
<%-- 	<input type="hidden" name="orderAddress" value="${memberBean.address2 }"> --%>
	<!-- 배송지 Table -->	
    <table class="table">
	  <tr><td colspan="2"><h5 class="h5Custom">배송지</h5></td></tr>
	  <tr>
	  	<td colspan="2">
	  	 <span class="smargin">
	  	   <input type="radio" name="shipAddress" class="rmargin" onclick="address(1,'${memberBean.u_name2 }','${memberBean.address2_post }','${memberBean.address2_1 }','${memberBean.address2_2 }','${memberBean.phone_num2 }')" <c:if test="${memberBean.u_name2 ne null}">checked="checked"</c:if>>최근배송지</span>	  	
	  	 <span class="smargin">
	  	   <input type="radio" name="shipAddress" class="rmargin" onclick="address(1,'${memberBean.u_name }','${memberBean.address_post }','${memberBean.address_1 }','${memberBean.address_2 }','${memberBean.phone_num }')" <c:if test="${memberBean.u_name2 eq null}">checked="checked"</c:if>>회원정보동일</span>
	  	 <span><input type="radio" name="shipAddress" class="rmargin" onclick="address(3,'','','','','')">새로입력</span><br>
		</td>
	   </tr>
       <!-- 최근 배송지가 있는 경우 -->
       <c:choose>
        <c:when test="${memberBean.u_name2 ne null}">
		   <tr class="bhidden">
		     <td>이름</td><td><input type="text" id="u_name" name="recName" readonly="readonly" value="${memberBean.u_name2 }"></td>
		   </tr>
		   <tr class="bhidden">
		     <td>주소</td>
		     <td><p><input type="text" id="address_post" name="orderAddress_post" value="${memberBean.address2_post }" readonly="readonly" style="width: 200px;"/><span id="addSearch"></span></p>
				<p><input type="text" id="address_1" name="orderAddress_1" value="${memberBean.address2_1 }" readonly="readonly" style="width: 400px;"/></p>
				<p><input type="text" id="address_2" name="orderAddress_2" value="${memberBean.address2_2 }" readonly="readonly" style="width: 400px;"/></p>
			</td>
		   </tr>
		   <tr class="bhidden">
		     <td>휴대전화</td><td><input type="text" id="phone_num" name="recPhone" readonly="readonly" value="${memberBean.phone_num2 }"/></td>
		   </tr>
        </c:when>
        <c:otherwise>
		   <tr class="bhidden">
		     <td>이름</td><td><input type="text" id="u_name" name="recName" readonly="readonly" value="${memberBean.u_name }"></td>
		   </tr>
		   <tr class="bhidden">
		     <td>주소</td>
		     <td><p><input type="text" id="address_post" name="orderAddress_post" value="${memberBean.address_post }" readonly="readonly" style="width: 200px;"/><span id="addSearch"></span></p>
				<p><input type="text" id="address_1" name="orderAddress_1" value="${memberBean.address_1 }" readonly="readonly" style="width: 400px;"/></p>
				<p><input type="text" id="address_2" name="orderAddress_2" value="${memberBean.address_2 }" readonly="readonly" style="width: 400px;"/></p>
			</td>
		   </tr>
		   <tr class="bhidden">
		     <td>휴대전화</td><td><input type="text" id="phone_num" name="recPhone" readonly="readonly" value="${memberBean.phone_num }"/></td>
		   </tr>        
        </c:otherwise>
       </c:choose>
    </table>
    <br><br><br>
    <c:set var="finalPrice" value="${totalPrice + shipPrice }"></c:set>
    <!-- 할인 Table -->	
    <table class="table" border="0">
	  <tr><td><h5 class="h5Custom">할인적용</h5></td></tr>
	  <tr> 
		<td>할인 쿠폰&nbsp;&nbsp;
		  <select name="couponList" id="couponList">
		  	<c:choose>
		  		<c:when test="${fn:length(couponList) < 1}">
		  			<option value="-1">사용가능한 쿠폰이 없습니다</option>
		  		</c:when>
		  		<c:otherwise>
				  	<option value="0">선택하세요</option>
				  	<c:forEach var="coupon" items="${couponList }" varStatus="status">
				  		<option value="${coupon.num }">${coupon.coupon_name }</option>
				  	</c:forEach>
		  		</c:otherwise>
		  	</c:choose>
		  </select>
		  <c:forEach var="coupon" items="${couponList }" varStatus="status">
	  		<input type="hidden" id="${coupon.num }" value="${coupon.volume }">
		  </c:forEach>
		  <input type="button" class="btn" value="쿠폰적용" onclick="couponDis(${finalPrice})"><br><br>
	   	     포인트 사용 (${memberBean.point }P 보유)&nbsp;&nbsp;<input type="text" name="point" id="pointInput" placeholder="포인트를 입력하세요"/>
	   	  <input type="button" class="btn" value="포인트적용" onclick="pointDis(${finalPrice}, ${memberBean.point })">
		</td>
	   </tr>
    </table>
    <br><br><br>

	
		<table class="table table-bordered">
		 <tr><td><h5 class="h5Custom">결제예정금액</h5></td></tr>
		 <tr> 
		 <td>
		   <div class="control-group">
			 <label class="control-label">총 상품금액 </label>
			 <div class="controls">
			   <input type="text" name="totalPrice" id="totalPrice" value="${totalPrice }" readonly="readonly">
			 </div>
		   </div>
		   <div class="control-group">
			 <label class="control-label">배송비 </label>
			 <div class="controls">
			  	   <input type="text" name="shipPrice" value="${shipPrice }" readonly="readonly">
			 </div>
		   </div>
		   <div class="control-group">
			 <label class="control-label">할인쿠폰 </label>
			 <div class="controls">
			  	   <input type="text" name="disCoupon" id="disCoupon" value="0" readonly="readonly">
			 </div>
		   </div>
		    <div class="control-group">
			 <label class="control-label">포인트사용 </label>
			 <div class="controls">
			  	   <input type="text" name="disPoint" id="disPoint" value="0" readonly="readonly">
			 </div>
		   </div>
		   <div class="control-group">
			 <label class="control-label">최종 결제금액 </label>
			 <div class="controls">
			   <input type="text" name="finalPrice" id="finalPrice" value="${finalPrice }" readonly="readonly">
			 </div>
		   </div>
		 </td>
		 </tr>
       </table>		
           <div class="centerBtn">
             <input type="button" class="btn btn-large" onclick="location.href='Main.me'" value="쇼핑계속하기"> 
    	   <input type="button" class="btn btn-large" value="주문하기" onclick="check()">
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