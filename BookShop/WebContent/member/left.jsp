	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
	
		<div id="sidebar" class="span3">

		<ul id="sideManu" class="nav nav-tabs nav-stacked">
			<li class="subMenu open"><a style="text-align: center; height: 80px; text-align: center;height: 150px; "> MY PAGE<br>
			<img src="img/mypage-512.webp" style="width: 80px;"><br><br>[${sessionScope.uID}]님! 어서오세요<br>
			
			<c:if test="${memberinfo.grade == 0}"><i class="fas fa-crown"></i> 일반</c:if>
			<c:if test="${memberinfo.grade == 1}">vip</c:if>
			<c:if test="${memberinfo.grade == 2}">vvip</c:if>
			<c:if test="${memberinfo.grade == 3}">일반관리자</c:if>
			<c:if test="${memberinfo.grade == 4}">고급관리자</c:if>
			<c:if test="${memberinfo.grade == 5}">총관리자</c:if>
			</a>
			
			<li class="subMenu open"><a style="text-align: center; clear: both;"> 나의 쇼핑 </a>
	 		<ul>
				<li><a class="active" href="OrderList.mo"><i class="icon-chevron-right"></i>주문내역 </a></li>
<!-- 				<li><a href="products.html"><i class="icon-chevron-right"></i>배송현황</a></li> -->
<!-- 				<li><a href="OrderListCanCel.mo"><i class="icon-chevron-right"></i>취소 내역</a></li> -->
				<li><a href="OrderListCRE.mo"><i class="icon-chevron-right"></i>교환/반품/취소</a></li>
				<li><a href="OrderListDelivery.mo"><i class="icon-chevron-right"></i>배송중</a></li>
<!-- 				<li><a href="products.html"><i class="icon-chevron-right"></i>반품한 주문</a></li> -->
<!-- 				<li><a href="products.html"><i class="icon-chevron-right"></i>교환중인 상품</a></li> -->
				</ul>
			</li>
			<li class="subMenu"><a>내 정보 관리</a>
			<ul style="display:none">
				<li><a href="PwCheckBeforeModifyForm.me?uID=${sessionScope.uID}"><i class="icon-chevron-right"></i>내정보수정</a></li>
				<li><a href="CouponInfoAction.me?uID=${sessionScope.uID}"><i class="icon-chevron-right"></i>쿠폰조회</a></li>
				<li><a href="PointInfoAction.me?uID=${sessionScope.uID}"><i class="icon-chevron-right"></i>포인트조회</a></li>
				<li><a href="QList.bo?uID=${sessionScope.uID}"><i class="icon-chevron-right"></i>1:1문의내역</a></li>
				<li><a href="QWriteForm.bo?uID=${sessionScope.uID}"><i class="icon-chevron-right"></i>1:1문의 작성</a></li>
				<li><a href="CartList.book"><i class="icon-chevron-right"></i>장바구니</a></li>	 									
				<li><a href="DeleteForm.me?uID=${sessionScope.uID}"><i class="icon-chevron-right"></i>회원탈퇴</a></li>     <!--내꺼아님  -->	
			</ul>
			</li>
			</li>
	<!-- 		<li><a href="products.html">HEALTH & BEAUTY [18]</a></li>
			<li><a href="products.html">SPORTS & LEISURE [58]</a></li>
			<li><a href="products.html">BOOKS & ENTERTAINMENTS [14]</a></li> -->
		</ul>
		<br/>
	 	 <!--  <div class="thumbnail" style="background-color: #807D7D;">
		 	<img src="..themes/images/products/panasonic.jpg" alt="Bootshop panasonoc New camera"/>
			<div class="caption">
			  <h5 style="text-align: center; color: #FFFAFA">마이페이지</h5>
				<h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4>
			</div>
		  </div><br/> -->
<!-- 			<div class="thumbnail"> -->
<!-- 				<img src="themes/images/products/kindle.png" title="Bootshop New Kindel" alt="Bootshop Kindel"> -->
<!-- 				<div class="caption"> -->
<!-- 				  <h5>최근본 상품?</h5> -->
<!-- 				    <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">$222.00</a></h4> -->
<!-- 				</div> -->
<!-- 			  </div><br/>  -->
<!-- 			<div class="thumbnail">
				<img src="..themes/images/payment_methods.png" title="Bootshop Payment Methods" alt="Payments Methods">
				<div class="caption">
				  <h5>Payment Methods</h5>
				</div>
			  </div> -->
 </div>