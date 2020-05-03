<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<h2>review_list.jsp</h2>
		<table>
<%-- 		<%if(articleList != null & listCount > 0) {%> --%>
			<tr id="tr_top">
<!-- 				<td width="100">Number</td> -->
<!-- 				<td width="400">Subject</td> -->
<!-- 				<td width="150">Name</td> -->
<!-- 				<td width="150">Date</td> -->
<!-- 				<td width="100">Read Count</td> -->
			</tr>
<%-- 		<%for(int i = 0; i < articleList.size(); i++) {%> --%>
				<tr>
<%-- 					<td align="center"><%=articleList.get(i).getBoard_num() %></td> --%>
					<td>
<%-- 					<%if(articleList.get(i).getBoard_re_lev() != 0) {%> --%>
<%-- 							<%for(int j = 0; j <= articleList.get(i).getBoard_re_lev() * 2; j++) {%> --%>
<!-- 									&nbsp; -->
<%-- 							<%} %>▶ --%>
<%-- 					<%} else {%> --%>
<!-- 							▶ -->
<%-- 					<%} %> --%>
						<a href="ReviewDetail.book">
<%-- 												<a href="BoardDetail.bo?board_num=<%=articleList.get(i).getBoard_num()%>&page=<%=nowPage%>"> --%>
						
<%-- 							<%=articleList.get(i).getBoard_subject() %> --%>
						</a>
					</td>
<%-- 					<td align="center"><%=articleList.get(i).getBoard_name() %></td> --%>
<%-- 					<td align="center"><%=articleList.get(i).getBoard_date() %></td> --%>
<%-- 					<td align="center"><%=articleList.get(i).getBoard_readcount() %></td> --%>
				</tr>
<%-- 			<%} %> --%>
		</table>
	</section>
	<section id = "writeButton">
			<a href="ReviewDetail.book">리뷰디테일입니다</a><br>
		<br>
		<a href="ReviewWriteForm.book"><input type="button" value="리뷰작성"></a>
		<a href="ReviewModifyPro.book"><input type="button" value="리뷰수정"></a>
		<a href="ReviewDeletePro.book"><input type="button" value="리뷰삭제"></a>
		
	</section>
	
	<!-- 페이지 목록 버튼 표시 -->
	<!-- 이전 페이지 또는 다음 페이지가 존재할 경우에만 해당 하이퍼링크 활성화 -->
	<!-- 존재하는 페이지 번호만 표시(현재 페이지는 번호만 표시하고, 나머지 페이지는 하이퍼링크 활성화) -->
	<section id="pageList">
<%-- 	<%if(nowPage <= 1) {%> --%>
<!-- 			[BEFORE]&nbsp; -->
<%-- 	<%} else {%> --%>
<%-- 			<a href="BoardList.bo?page=<%=nowPage - 1%>">[BEFORE]</a>&nbsp; --%>
<!-- 						<a href="BoardList.book?page=?">[BEFORE]</a>&nbsp; -->
												<a href="ReviewList.book">[BEFORE]</a>&nbsp;
						
			
<%-- 	<%} %> --%>
	
<%-- 	<%for(int i = startPage; i <= endPage; i++) { --%>
<%-- 			if(i == nowPage) {%> --%>
<%-- 					[<%=i %>] --%>
<%-- 		<%} else { %> --%>
<%-- 				<a href="BoardList.bo?page=<%=i %>">[<%=i %>]</a>&nbsp; --%>
<!-- 								<a href="BoardList.book?page=?"></a>&nbsp; -->
<!-- 																<a href="ReviewList.book"></a>&nbsp; -->
								
				
<%-- 		<%} %> --%>
<%-- 	<%} %> --%>
	
<%-- 	<%if(nowPage >= maxPage) {%> --%>
<!-- 			&nbsp;[NEXT] -->
<%-- 	<%} else {%> --%>
<%-- 			<a href="BoardList.bo?page=<%=nowPage + 1%>">&nbsp;[NEXT]</a> --%>
<!-- 						<a href="BoardList.book?page=?">&nbsp;[NEXT]</a> -->
												<a href="ReviewList.book">&nbsp;[NEXT]</a>
						
			
<%-- 	<%} %> --%>
	</section>
<%-- <%} else {%> --%>
	<section id="emptyArea">There's no registered post yet.</section>
<%-- <%} %> --%>


</body>
</html>