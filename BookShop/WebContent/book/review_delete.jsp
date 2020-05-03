<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- <form name="deleteForm" action="ReviewDeletePro.book?board_num=?&page=?" method="post"> -->
		<h2>review_delete.jsp</h2>
		<form name="deleteForm" action="ReviewDeletePro.book" method="post">
<%-- 			<input type="hidden" name="board_num" value="<%=board_num %>" /> --%>
<%-- 			<input type="hidden" name="page" value="<%=nowPage %>" /> --%>
			<table>
				<tr>
					<td>
						<label>Post password</label><br>
<!-- 						<input type="password" name="board_pass" required="required" /> -->
					</td>
				</tr>
				<tr>
					<td>
						<input type="submit" value="Delete" />
						&nbsp;&nbsp;<input type="reset" value="Back" onclick="history.back()" />
					</td>
				</tr>
			</table>
		</form>

<!-- BookReviewWriteProAction -->
<!-- BookReviewWriteProService -->

<!-- BookReviewDetailProAction -->
<!-- BookReviewDetailProService -->

<!-- BookReviewModifyFormAction -->
<!-- BookReviewModifyProAction -->
<!-- BookReviewModifyProService -->

<!-- BookReviewDeleteProAction -->
<!-- BookReviewDeleteProService -->

</body>
</html>