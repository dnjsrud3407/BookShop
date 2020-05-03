<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img{
width: 400px;
height: 300px;
}
</style>
</head>
<body>
<h2>order_page.jsp</h2>

<section id = "writeButton">	
	
	<!-- 		<a href="ReviewDetail.book">제품교한 디테일입니다</a><br> -->
<!-- 		<br> -->
		<a href="OrderDelivery.adm"><input type="button" value="배송하기"></a><br>
		<br>
<!-- 		<a href="ReviewDetail.book">주문취소 디테일입니다</a><br> -->
<!-- 		<br> -->
		<a href="OrderCencleList.adm"><input type="button" value="주문 취소"></a><br>
<!-- 		<a href="ReviewWriteForm.book"><input type="button" value="리뷰작성"></a> -->

		<br>
<!-- 		<a href="QDetail.book">제품반품 디테일입니다</a><br> -->
<!-- 		<br> -->
		<a href="OrderRefundList.adm"><input type="button" value="제품 반품"></a><br>
		<br>
<!-- 		<a href="ReviewDetail.book">제품교한 디테일입니다</a><br> -->
<!-- 		<br> -->
		<a href="OrderExchangeList.adm"><input type="button" value="제품 교환"></a><br>
		
	</section>
</body>
</html>