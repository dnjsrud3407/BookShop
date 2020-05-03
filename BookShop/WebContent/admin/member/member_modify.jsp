<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 	<header> -->
<!-- 		<!-- 세션ID(sId) 가 없을 경우 로그인(LoginForm.me), 회원가입(JoinForm.me) 링크 표시 --> -->
<!-- 		<!-- 세션ID(sId) 가 있을 경우 회원ID, 로그아웃(Logout.me)링크 표시 --> -->
<%-- 		<%if(sid == null) {%> --%>
<!-- 			<a href="LoginForm.me">Login</a> | <a href="JoinForm.me">Join</a> -->
<%-- 		<%} else { %> --%>
<%-- 			Welcome <%=sid %> :D | <a href="Logout.me">Logout</a> --%>
<%-- 		<%} %> --%>
<!-- 	</header> -->
	<!-- 게시판 글 수정 -->
	<section id="writeForm">
		<h2>member_modify.jsp</h2>	
		<form action="MemberModifyPro.adm" method="post" name="BookForm">
<%-- 			<input type="hidden" name="board_num" value="<%=article.getBoard_num()%>" /> --%>
<%-- 			<input type="hidden" name="page" value="<%=nowPage%>" /> --%>
<!-- 			<table> -->
<!-- 				<tr> -->
<!-- 					label 태그를 사용하여 해당 레이블 클릭 시 for 속성에 지정된 이름과 같은 id 속성을 갖는 텍스트필드로 커서 요청 -->
<!-- 					<td class="td_left"><label for="board_name">Writer</label></td> -->
<!-- 					<td class="td_right"> -->
<!-- 						글쓴이는 편집 불가능하도록 설정 -->
<%-- 						<input type="text" name="board_name" id="board_name" value="<%=article.getBoard_name() %>" required="required" readonly="readonly" /> --%>
<!-- 					</td>	 -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td class="td_left"><label>Password</label></td> -->
<!-- 					<td class="td_right"> -->
<!-- 						<input type="password" name="board_pass" id="board_pass" required="required" /> -->
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td class="td_left"><label>Subject</label></td> -->
<!-- 					<td class="td_right"> -->
<%-- 						<input type="text" name="board_subject" id="board_subject" value="<%=article.getBoard_subject() %>" required="required" /> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td class="td_left"><label for="board_content">Content</label></td> -->
<%-- 					<td class="td_right"><textarea name="board_content" id="board_content" cols="40" rows="15" required="required" ><%=article.getBoard_content() %></textarea></td> --%>
<!-- 				</tr> -->
<!-- 			</table> -->
			<section id="commandCell">
				<input type="submit" value="Register" />&nbsp;&nbsp;
<!-- 				<input type="button" value="Back" onclick="history.back()"/> -->
			</section>
		</form>
	</section>
</body>
</html>