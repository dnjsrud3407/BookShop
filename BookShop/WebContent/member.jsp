<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>member</title>

</head>

<body>

/member/account


	<ul>

		<li><a href="OrderList.mo">주문내역(배송조회/취소/교환/반품/상세보기)</a></li>
		<li><a href="OffInfo.me">할인쿠폰/포인트조회</a></li>
		<li><a href="OrderQList.mo">상품문의내역</a></li>
		<li><a href="QWriteList.bo">1:1문의내역(문의하기/수정/삭제)</a></li>
		<li><a href="Modify.me">정보수정(정보수정/탈퇴-비밀번호확인필수)</a></li>
		<li><a href="DeleteForm.me">회원탈퇴(정보수정/탈퇴-비밀번호확인필수)</a></li>
		
	</ul>
	
<form action="LoginPro.me" method="post">
<input type="submit" value="가입하기">
</form>
</body>

</html>