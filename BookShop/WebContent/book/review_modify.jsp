<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>마이페이지</title>
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
    <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen"/>
    <link href="themes/css/base.css" rel="stylesheet" media="screen"/>

<!-- Bootstrap style responsive -->	
	<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
	<link href="themes/css/font-awesome.css" rel="stylesheet" type="text/css">
<!-- Google-code-prettify -->	
	<link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
<!-- fav and touch icons -->
    <link rel="shortcut icon" href="themes/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="themes/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="themes/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
    <script type="text/javascript" src="./js/jquery-3.4.1.js"></script>
	<style type="text/css" id="enject"></style>
	
	<style type="text/css">
	
	 .btn2 {border: 1px solid #D9D4D4; text-align: center; margin: 2px; padding: 3px;}
	 
	#orderTable {text-align: center; border-bottom: 2px solid #D9D4D4; border-top: hidden; height: 3.5em; background-color:#f5f5f5 }


	#ReviewForm {
		
		
		margin: auto;
	}
	

	table {
		margin: auto;
		
		
	}
	
	
	.form-actions{
		text-align: center;
	}
	
	
	#commandCell {
		text-align: center;
	}
	
	#review_ti{
	width: 540px;
	height: 30px;
	line-height: 20px;
	
	}
	
	#review_ta{
	width: 540px;
	height: 320px;
	resize: none;
	}


</style>



<script type="text/javascript">

	$(document).ready(function () {
		$("#selectScore").val('${boardBean.score}').prop("selected", true);
	});
	
	
	
	


function scoreCheck(){
	var f = document.reviewForm;
	
	if(f.score.value == ''){
		alert('평점을 선택하여 주세요');
		f.score.focus();
		return false;
	} else {
		return true;
	}
}



</script>	
	
	



	
	
  </head>
<body>
<!-- header -->

<!-- header -->

<!-- Header End====================================================================== -->

<!-- Sidebar ================================================== -->






<!-- Sidebar end=============================================== -->

   
	<h3>${param.bookID }상품 사용후기</h3>	
	<hr class="soft"/>
	<section id="reviewForm">
		<form action="ReviewModifyPro.book" method="post" enctype="multipart/form-data" name="reviewForm" onsubmit="return scoreCheck()">
			<input type="hidden" name="k1" value="상품후기">
			<input type="hidden" name="boardWriter" value="${sessionScope.uID }">
			<input type="hidden" name="boardNum" value="${boardBean.boardNum }">
			<input type="hidden" name="bookID" value="${param.bookID }">
			<table>
				<tr>
					<td class="td_left"><label for="boardTitle">제목</label></td>
					<td class="td_right">
					<div class="controls">
						<input type="text" class="input-xxlarge" name="boardTitle" id="review_ti" required="required"
						 value="${boardBean.boardTitle }"></div>
          			</td>
				</tr>
				
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td class="td_right">
						<div class="controls"> 
							<textarea class="input-xxlarge" name="boardContent" id="review_ta" rows="3" required="required"
							>${boardBean.boardContent }</textarea>
						</div>
						<!-- <textarea rows="13" cols="40" name="content" id="content"></textarea> -->
					</td>
				</tr>
				
				<tr>				
					<td class="td_left"><label for="image">사진</label></td>
					<td class="td_right"><input type="file" name="image" id="image" title="첨부파일"/>
						<c:forEach var="file" items="${boardBean.fileList }">
						<input type="hidden" name="old_file" value="${file.originFilename }">${file.originFilename }
						</c:forEach>
					</td>
				</tr>
				
				<tr>
					<td class="td_left"><label class="control-label" for="reviewScore">평점</label></td>
					<td class="td_right"><select name="score" id="selectScore" required="required">
								<option value="">평점을 선택하세요</option>
								<option value="1">1</option>
								<option value="2">2</option>
								<option value="3">3</option>
								<option value="4">4</option>
								<option value="5">5</option>
							</select></td>
				</tr>
				<tr>
					<td colspan="2"><div class="form-actions">
	            		<button type="submit" class="btn btn-primary">Save</button>
	            		<button type="reset" class="btn">Reset</button>
	            		<button class="btn">Cancel</button>
	          			</div>
	          		</td>
          		</tr>	
          		<!-- <td>	
					<td colspan="2" id="commandCell">
						<input type="submit" value="등록"/>
						<input type="reset" value="다시작성"/>
						<input type="button" value="목록보기" onclick="location.href='DogList.dog'"/>
					</td>
				</tr>	 -->
			</table>
		</form>
	</section>

		
		   
		   
		   
		   
     
	
	

<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->
	
	
<!-- Placed at the end of the document so the pages load faster ============================================= -->

	
	<!-- Themes switcher section ============================================================================================= -->



</body>
</html>