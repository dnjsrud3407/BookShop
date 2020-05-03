<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h2>order_delivery_detail.jsp</h2>
		<header>
<%-- 			<%if(sid == null) {%> --%>
<!-- 				<a href="LoginForm.me">로그인</a> | <a href="MemberJoinForm.me">회원가입</a> -->
				
<%-- 			<%} else { %> --%>
<%-- 				<%=sid %>님 | <a href="Logout.me">로그아웃</a>  --%>
<%-- 			<%} %> --%>
		</header>
	<section id="articleForm">
		<h2>배송 내용 상세보기</h2>
		<section id="basicInfoArea">
<%-- 			제목 : <%=article.getBoard_subject() %><br> --%>
<!-- 			첨부파일 :  -->
<!-- 			<!-- 프론트컨트롤러 동작하려면 .bo필요 새창뛰울것임  --> -->
<%-- 			<%if(article.getBoard_file() != null) { %> --%>
<!-- 			<!-- 파일클릭시 새창에서 다운로드 작업수행 --> -->
<%-- 					<a href="BoardFileDown.bo?file_name=<%=article.getBoard_file()%>" target="blank"><%=article.getBoard_file() %></a> --%>
<%-- 			<%}%> --%>
		</section>
		<section id="articleContentArea">
<%-- 			<%=article.getBoard_content() %>	 --%>
		</section>
	</section>
	<section id="commandList">
		<a href=""><input type="button" value="배송완료"></a>
<!-- 		<a href="MemberDeletePro.adm"><input type="button" value="멤버삭제"></a> -->

	</section>
		
	

</body>
</html>