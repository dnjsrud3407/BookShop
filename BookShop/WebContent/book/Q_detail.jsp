<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 	String sid = null;
// // 로그인이 되지 않은 상태일 경우 로그인 페이지로 강제 이동 처리
// 	if(session.getAttribute("sid") == null) {
// 		out.println("<script>");
// 		out.println("alert('This service requires a login')");
// 		out.println("location.href='LoginForm.me'");
// 		out.println("</script>");
// 	} else {// 로그인 된 상태일 경우 세션 ID 가져오기
// 		sid = (String)session.getAttribute("sid");
// 	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC Board</title>
<style type="text/css">
	#registForm {
		width: 500px;
		height: 610px;
		border: 1px solid red;
		margin: auto;
	}
	
	h2 {
/* 		text-align: center; */
	}
	
	table {
		margin: auto;
		width: 450px;
		border: 1px solid darkgray;
	}
	
	.td_left {
		width: 150px;
		background: orange;
	}
	
	.td_right {
		width: 300px;
		background: skyblue;
	}
	
	#commandCell {
		text-align: center;
	}
	header {
	text-align: right;
}
</style>
</head>
<body>
		<h2>Q_detail.jsp</h2>
		<header>
<%-- 			<%if(sid == null) {%> --%>
<!-- 				<a href="LoginForm.me">로그인</a> | <a href="MemberJoinForm.me">회원가입</a> -->
				
<%-- 			<%} else { %> --%>
<%-- 				<%=sid %>님 | <a href="Logout.me">로그아웃</a>  --%>
<%-- 			<%} %> --%>
		</header>
	<section id="articleForm">
		<h2>문의 내용 상세보기</h2>
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
		<a href="QModifyForm.book"><input type="button" value="문의수정"></a>
		<a href="QDeleteForm.book"><input type="button" value="문의삭제"></a>
	</section>
		
	
</body>
</html>