<%@page import="vo.BoardBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.BookBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%

String nowPage = request.getParameter("page");


boolean isLogin = false; 
	if(session.getAttribute("uID") == null) {
		isLogin = false;
     	String uID	= (String)session.getAttribute("uID");
     	
	} else { 
		isLogin = true;
		String uID	= (String)session.getAttribute("uID");
     
		
	}

%> 
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootshop online Shopping cart - book_list.jsp</title>
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
    <link href=themes/css/base.css?ver=1 rel="stylesheet" media="screen"/>
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
  	<link rel="css/bookqna.css" rel="stylesheet">
  	<link href="css/nav.css?ver=1" rel="stylesheet" type="text/css">
  	<script type="text/javascript" src="./js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
  	
    $(document).ready(function(){
    	
    	$(".qna_tr_s").hide();
    	$('.qna_tr_p').click(function(){
    		
        	$(this).next().slideToggle();	
        	});

    });
    
    $(document).on("click",".open-AddReview", function (){
    	var Title = $(".rv_img").data('id');
    	$(".rv_main ")
    	var reviewImg = $(".rv_img").attr("src");
    	console.log(reviewImg);
    })
   
   
	var isLogin = <%=isLogin%>;
	var pop_title = document.bookQnaForm;
	
	function loginChk() {
		var loginUrl = "http://localhost:8080/TeamProject" + "/Login.me?url=" + location.href;
		if(!isLogin){
			window.location.href = loginUrl;
			return false;
		}
		return true;
	}
	
	function openQna(p, number) {
		var qnaUrl = "";
		if(p === 'w'){
			qnaUrl = "./QWriteForm.book";
			
		} else if(p === 'm'){
			qnaUrl = "./QModifyForm.book";
			
		} else if(p === 'd'){
			if(loginChk()){
				if(confirm('삭제하시겠습니까?')){
					location.href = "./QDeletePro.book?bookID=" + ${book.bookID} + "&boardNum=" + number;
					return;
				}
			}
		}else {
			alert('알 수 없는 오류입니다');
		}
	
		if(loginChk()){
 			window.open('',"bookQnaForm",'toolbar=no,scrollbars=no,resizable=no,top=100,left=0,width=700,height=600');
 			bookQnaForm.action = qnaUrl;
 			bookQnaForm.method="post"
 			bookQnaForm.target="bookQnaForm";
 			bookQnaForm.bookID="${book.bookID}";
 			bookQnaForm.bookTitle="${book.bookTitle}";
 			bookQnaForm.uID="${sessionScope.uID}";
 			if(p === 'm'){
 				 document.bookQnaForm.boardNum.value=number;
 			}
 			bookQnaForm.submit();
		}
	}
	
	function openReview(p, number) {
		alert(isLogin);
		console.log(p);
		console.log(number);
		
		var reviewUrl = "";
		
		if(p === 'm'){
			reviewUrl = "./ReviewModifyForm.book?boardNum=" + number + "&bookID=" + ${book.bookID};
			
		} else if(p === 'd'){
			if(loginChk()){
				if(confirm('삭제하시겠습니까?')){
					location.href = "./ReviewDeletePro.book?boardNum=" + number + "&bookID=" + ${book.bookID};
					return;	
				}
			}
		}else {
			alert('알 수 없는 오류입니다');
		}
		
	
		
	
		if(loginChk()){
 			window.open(reviewUrl,"reviewForm",'toolbar=no,scrollbars=no,resizable=no,top=100,left=0,width=700,height=700');

		}
	}
    
	function kindSubmit(index, qty){
		if(loginChk()){
			if(index == 1) {
				document.kindFrm.action='CartAdd2.book';
			} else if(index == 2) { 
				document.kindFrm.action='CartAdd.book';
			} else if (index == 3) {
				document.kindFrm.action='BookLike.book';
			}
			document.kindFrm.submit();
		}
		return false;
	}
	

	
</script>
	<style type="text/css" id="enject">
	.qBtn {
    display: inline-block;
    float: right;
}

.td_rgt {/* border: 1px solid #00f; */
/*  vertical-align:bottom !important; */}

/* 
.btn_rgt {/* border: 1px solid red; */
 float: right;
  
 }	 */
.rv_wrap .rv_main{
padding: 20px;
} 

.rv_content .info_area{
	position: relative;
	margin-bottom: 15px;
	border-bottom: 1px solid; color: #777;
}

.rv_content .info_area .rv_comment{
position: absolute;
top: 0;
left: 0;
vertical-align: middle;
}

.rv_content .info_area .rv_comment .title {
color: #444;
font-size: 14px;
font-weight: 14px;
font-weight: 700;
line-height: 20px;
text-align: left;
}
.rv_content .info_area {
padding: 5px 0;
text-align: right;
display: block;
line-height: 18px;
}

.rv_content{
vertical-align: top;
padding: 15px;}

.info_area{
vertical-align: top;
display: block;
}

.info_text .info_text span{
display: block;
line-height: 18px;
}


.scroll_area{
overflow-y: auto;
overflow-x: hidden;
}
/*Review modal */
/*  .modal { */
/*  	text-align: center; */
/*  } */
 
/*  @@media screen and (min-width: 768px){ */
/*  	.modal:before { */
/*  		display: inline-block; */
/*  		vertical-align: middle; */
/*  		content: " "; */
/*  		height: 100%; */
/*  	} */
/*  } */
/*  .modal-dialog { */
/*  		display: inline-block; */
/*  		text-align: left; */
/*  		vertical-align: middle; */
/*  	} */
	</style>
  </head>
<body>
<jsp:include page="../inc/top.jsp"></jsp:include>
<!-- Header End====================================================================== -->
<div id="mainBody">
	<div class="container">
	<div class="row" style="margin-left: 10%;">
<!-- Sidebar ================================================== -->
<!-- Sidebar end=============================================== -->
	<div class="span9">
    <ul class="breadcrumb">
	    <li><a href="Main.me">메인으로</a><span class="divider">/</span></li>
	    <li><a href="BookList.book?type=L&bk2=${fn:substring(bk2,0,1)}">${book.BK2 }</a><span class="divider">/</span></li>
	    <li class="active"><a href="BookList.book?type=L&bk2=1&BKID=${book.bookKategorie_BKID }">${book.BK1 } - ${book.BK3 }</a></li>
    </ul>
    <c:set var="book" value="${requestScope.book}"/>
	<div class="row">	  
			<div id="gallery" class="span3">
            <a href="upload/${book.bookImage }" title="${book.bookTitle }">
				<img src="upload/${book.bookImage }" style="width:100%" alt="상품 이미지"/>
            </a>
			</div>
			<div class="span6">
				<h3>${book.bookTitle } </h3>
				<hr class="soft"/>
				<form class="form-horizontal qtyFrm" method="post" name="kindFrm">
				  <div class="control-group">
					<label class="control-label"><span>${book.bookPrice }원</span></label>
					<div class="controls">
					<input type="number" class="span1" placeholder="Qty." name="qty" value="1"/>
					<input type="hidden" name="bookID" value="${book.bookID }"/>
				  	  <button type="button" class="btn btn-large btn-primary pull-right" onclick='kindSubmit(1)'> 구매하기</button>
					  <button type="button" class="btn btn-large btn-primary pull-right" onclick='kindSubmit(2,qty.value)'> 장바구니 <i class=" icon-shopping-cart"></i></button>
					  <button type="button" class="btn btn-large btn-primary pull-right" onclick='kindSubmit(3)'> ♡ </button>	
					</div>
				  </div>
				</form>

				<br class="clr"/>
			</div>
			
			<div class="span9">
            <ul id="productDetail" class="nav nav-tabs">
              <li ><a href="#bookqna" data-toggle="tab">상품문의</a></li>
              <li><a href="#review" data-toggle="tab">상품후기</a></li>
              <li class="active"><a href="#home" data-toggle="tab">상세설명</a></li>
            </ul>
            <div id="myTabContent" class="tab-content">
              <div class="tab-pane active" id="home">
				${book.bookIntroduce }
				<br>
				<c:set var="bk2" value="${book.BK2 }"/>
				 <a href="BookList.book?bk2=${fn:substring(bk2,0,1)}" class="btn btn-large pull-right">다른 상품 보기</a>
              </div>
		<div class="tab-pane fade" id="profile">
		<div id="myTab" class="pull-right">
		 <a href="#listView" data-toggle="tab"><span class="btn btn-large"><i class="icon-list"></i></span></a>
		 <a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
		</div>
		<br class="clr"/>
		<hr class="soft"/>
		<div class="tab-content">
			<div class="tab-pane" id="listView">
				<div class="row">	  
					<div class="span2">
						<img src="themes/images/products/4.jpg" alt=""/>
					</div>
					<div class="span4">
						<h3>New | Available</h3>				
						<hr class="soft"/>
						<h5>Product Name </h5>
						<p>
						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
						that is why our goods are so popular..
						</p>
						<a class="btn btn-small pull-right" href="product_details.html">View Details</a>
						<br class="clr"/>
					</div>
					<div class="span3 alignR">
					<form class="form-horizontal qtyFrm">
					<h3> $222.00</h3>
					<label class="checkbox">
						<input type="checkbox">  Adds product to compair
					</label><br/>
					<div class="btn-group">
					  <a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
					  <a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
					 </div>
						</form>
					</div>
			</div>
			<hr class="soft"/>
			<div class="row">	  
					<div class="span2">
						<img src="themes/images/products/5.jpg" alt=""/>
					</div>
					<div class="span4">
						<h3>New | Available</h3>				
						<hr class="soft"/>
						<h5>Product Name </h5>
						<p>
						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
						that is why our goods are so popular..
						</p>
						<a class="btn btn-small pull-right" href="product_details.html">View Details</a>
						<br class="clr"/>
					</div>
					<div class="span3 alignR">
					<form class="form-horizontal qtyFrm">
						<h3> $222.00</h3>
						<label class="checkbox">
						<input type="checkbox">  Adds product to compair
						</label><br/>
						<div class="btn-group">
						<a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
						<a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
						</div>
					</form>
					</div>
			</div>
			<hr class="soft"/>
			<div class="row">	  
					<div class="span2">
					<img src="themes/images/products/6.jpg" alt=""/>
					</div>
					<div class="span4">
						<h3>New | Available</h3>				
						<hr class="soft"/>
						<h5>Product Name </h5>
						<p>
						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
						that is why our goods are so popular..
						</p>
						<a class="btn btn-small pull-right" href="product_details.html">View Details</a>
						<br class="clr"/>
					</div>
					<div class="span3 alignR">
					<form class="form-horizontal qtyFrm">
					<h3> $222.00</h3>
					<label class="checkbox">
						<input type="checkbox">  Adds product to compair
					</label><br/>
				<div class="btn-group">
				  <a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
				  <a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
				 </div>
						</form>
					</div>
			</div>
			<hr class="soft"/>
			<div class="row">	  
					<div class="span2">
					<img src="themes/images/products/7.jpg" alt=""/>
					</div>
					<div class="span4">
						<h3>New | Available</h3>				
						<hr class="soft"/>
						<h5>Product Name </h5>
						<p>
						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
						that is why our goods are so popular..
						</p>
						<a class="btn btn-small pull-right" href="product_details.html">View Details</a>
						<br class="clr"/>
					</div>
					<div class="span3 alignR">
						<form class="form-horizontal qtyFrm">
						<h3> $222.00</h3>
						<label class="checkbox">
						<input type="checkbox">  Adds product to compair
						</label><br/>
						<div class="btn-group">
						<a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
						<a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
						</div>
						</form>
					</div>
			</div>
			
			<hr class="soft"/>
			<div class="row">	  
					<div class="span2">
					<img src="themes/images/products/8.jpg" alt=""/>
					</div>
					<div class="span4">
						<h3>New | Available</h3>				
						<hr class="soft"/>
						<h5>Product Name </h5>
						<p>
						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
						that is why our goods are so popular..
						</p>
						<a class="btn btn-small pull-right" href="product_details.html">View Details</a>
						<br class="clr"/>
					</div>
					<div class="span3 alignR">
						<form class="form-horizontal qtyFrm">
						<h3> $222.00</h3>
						<label class="checkbox">
						<input type="checkbox">  Adds product to compair
						</label><br/>
						<div class="btn-group">
						<a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
						<a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
						</div>
						</form>
					</div>
			</div>
			<hr class="soft"/>
				<div class="row">	  
					<div class="span2">
					<img src="themes/images/products/9.jpg" alt=""/>
					</div>
					<div class="span4">
						<h3>New | Available</h3>				
						<hr class="soft"/>
						<h5>Product Name </h5>
						<p>
						Nowadays the lingerie industry is one of the most successful business spheres.We always stay in touch with the latest fashion tendencies - 
						that is why our goods are so popular..
						</p>
						<a class="btn btn-small pull-right" href="product_details.html">View Details</a>
						<br class="clr"/>
					</div>
					<div class="span3 alignR">
						<form class="form-horizontal qtyFrm">
						<h3> $222.00</h3>
						<label class="checkbox">
						<input type="checkbox">  Adds product to compair
						</label><br/>
						<div class="btn-group">
						<a href="product_details.html" class="btn btn-large btn-primary"> Add to <i class=" icon-shopping-cart"></i></a>
						<a href="product_details.html" class="btn btn-large"><i class="icon-zoom-in"></i></a>
						</div>
						</form>
					</div>
			</div>
			<hr class="soft"/>
		</div>
			<div class="tab-pane active" id="blockView">
				<ul class="thumbnails">
					<li class="span3">
					  <div class="thumbnail">
						<a href="product_details.html"><img src="themes/images/products/10.jpg" alt=""/></a>
						<div class="caption">
						  <h5>Manicure &amp; Pedicure</h5>
						  <p> 
							Lorem Ipsum is simply dummy text. 
						  </p>
						  <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
						</div>
					  </div>
					</li>
					<li class="span3">
					  <div class="thumbnail">
						<a href="product_details.html"><img src="themes/images/products/11.jpg" alt=""/></a>
						<div class="caption">
						  <h5>Manicure &amp; Pedicure</h5>
						  <p> 
							Lorem Ipsum is simply dummy text. 
						  </p>
						  <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
						</div>
					  </div>
					</li>
					<li class="span3">
					  <div class="thumbnail">
						<a href="product_details.html"><img src="themes/images/products/12.jpg" alt=""/></a>
						<div class="caption">
						  <h5>Manicure &amp; Pedicure</h5>
						  <p> 
							Lorem Ipsum is simply dummy text. 
						  </p>
						   <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
						</div>
					  </div>
					</li>
					<li class="span3">
					  <div class="thumbnail">
						<a href="product_details.html"><img src="themes/images/products/13.jpg" alt=""/></a>
						<div class="caption">
						  <h5>Manicure &amp; Pedicure</h5>
						  <p> 
							Lorem Ipsum is simply dummy text. 
						  </p>
						   <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
						</div>
					  </div>
					</li>
					<li class="span3">
					  <div class="thumbnail">
						<a href="product_details.html"><img src="themes/images/products/1.jpg" alt=""/></a>
						<div class="caption">
						  <h5>Manicure &amp; Pedicure</h5>
						  <p> 
							Lorem Ipsum is simply dummy text. 
						  </p>
						   <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
						</div>
					  </div>
					</li>
					<li class="span3">
					  <div class="thumbnail">
						<a href="product_details.html"><img src="themes/images/products/2.jpg" alt=""/></a>
						<div class="caption">
						  <h5>Manicure &amp; Pedicure</h5>
						  <p> 
							Lorem Ipsum is simply dummy text. 
						  </p>
						   <h4 style="text-align:center"><a class="btn" href="product_details.html"> <i class="icon-zoom-in"></i></a> <a class="btn" href="#">Add to <i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">&euro;222.00</a></h4>
						</div>
					  </div>
					</li>
				  </ul>
			<hr class="soft"/>
			</div>
		</div>
		
								
				<br class="clr">
					 </div>
					 <div class="tab-pane fade" id="review">
					 <c:if test="${empty articleReviewList }">
						<h3>상품후기</h3>
						<hr class="soft">
						<table>
						<tr style="height: 200px;">
							<td style="width: 870px;">
							   	<h4 style="text-align: center;">등록된 상품평이 없습니다.</h4>
							</td>
						</tr>
						
						</table>
							
								<hr class="soft">
					 </c:if>
					 
					 <c:forEach var="review" items="${articleReviewList }" varStatus="status">
					 	<c:forEach var="file" items="${review.fileList }" varStatus="status"> 
						<div class="row">	  
							<div class="span2">
								<img id="rv_img${status.index }" class="rv_img" src="boardfile/${file.originFilename}" alt="상품후기이미지">
							</div>
							<div class="span4">
								
								<a href="#viewDetail" role="button" data-toggle="modal" class="open-AddReview" onclick="readcountUp('<c:out value="${review.boardNum}"/>')"><h3>New | Available</h3>				
									
									<h5 id="rv_title" class="rv_title">${review.boardTitle } </h5>
									<p id="rv_content" class="rv_content">
										${review.boardContent }
									</p>
								</a>
								
								<br class="clr">
							</div>
							<div class="span3" style="">
							
							<div>
							<h4>작성자 : ${review.boardWriter}</h4>
							<h4 id="rv_regtime">작성일 : <fmt:formatDate value="${review.boardRegTime }" pattern="yyyy-MM-dd" /></h4>
							<h4 id="rv_count">조회수 : ${review.boardReadcount }</h4>
							<h4 style="margin-bottom: 100px;">평점 :
							<c:forEach  var="i" begin="1" end="${review.score}">
								<img src="./img/rateStar.png" style="width: 20px; height: 20px;">
							</c:forEach>
							<c:if test="${review.score <5 }">
								<c:forEach  var="i" begin="1" end="${5 - review.score}">
									<img src="./img/writingStar.png" style="width: 20px; height: 20px;">
								</c:forEach>
								
							</c:if>	 </h4>
							
							
							<br>
							</div>
								<div>
								 <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" onclick="openReview('m','<c:out value="${review.boardNum}"/>')">글 수정하기 <span class=""></span></button>
							     <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" onclick="openReview('d','<c:out value="${review.boardNum}"/>')">글 삭제하기 <span class=""></span></button>
								</div>
							</div>
						</div>
								<hr class="soft">
							</c:forEach>
						</c:forEach>
								<div style="text-align: right;"> 상품평은 구매완료 후 <a href="OrderList.mo">수취확인</a>에서 작성하실 수 있습니다.</div>
						<c:if test="${!empty articleReviewList}">
						<div class="pagination">
							<ul>
							<li><c:if test="${pageInfoReview.startPage > pageBlock }">
							<a href='<c:url value="/Book.book?bookID=${book.bookID }&page=${pageInfoReview.startPage-pageInfoReview.pageBlock }"/>'>&lsaquo;</a></c:if></li>
							<li>
							<c:forEach var="i" begin="${pageInfoReview.startPage }" end="${pageInfoReview.endPage }" step="1">
							<a href='<c:url value="/Book.book?bookID=${book.bookID}&page=${i}"/>'>${i}</a>
							</c:forEach></li>
<!-- 							<li><a href="#bookqna&page=2">2</a></li> -->
<!-- 							<li><a href="#bookqna#&page=3">3</a></li> -->
<!-- 							<li><a href="#bookqna#&page=4">4</a></li> -->
							<li><a href="#">...</a></li>
							<li><c:if test="${pageInfoReview.endPage < pageInfoReview.maxPage }">
							<a href='<c:url value="/Book.book?bookID=${book.bookID }&page=${pageInfoReview.startPage + pageInfoReview.pageBlock}"/>'>&rsaquo;</a></c:if></li>
							</ul>
							</div>
							<br class="clr"/>
						</c:if>
					 </div>
					 <div class="tab-pane fade" id="bookqna">
					 	<div>
					 		
						    <h3><a href='<c:url value ="/Book.book?bookID=${book.bookID}"/>'>상품 문의하기</a></h3>    
						    <form class="well form-search">
						        <select id="select11" style="width:100px">
						                <option>제목</option>
						                <option>답변상태</option>
						                <option>문의자</option>
						              </select>
								<input type="text" class="input-medium search-query" style="">
								  <button type="submit" class="btn">Search</button>
						        <div class="qBtn">
									<button class="btn btn-inverse dropdown-toggle" data-toggle="dropdown" onclick="openQna('w','0')">문의하기 </button>
								</div>
							</form>
								<form name="bookQnaForm">
									<input type="hidden" name="bookID" value="${book.bookID }"/>
									<input type="hidden" name="bookTitle" value="${book.bookTitle }"/>
									<input type="hidden" name="uID" value="${sessionScope.uID }"/>
									<input type="hidden" name="boardNum" value=""/>
								</form>
						 </div>
					 	
					 	
					  
					 	<table id="qna_tb" class="table table-striped" style="width:870px"> 
					       <thead>
					         <tr>
					           <th style="width:100px">번호</th>
					           <th style="width:100px">답변상태</th>
					           <th style="text-align: center">제목</th>
					           <th style="width:100px">문의자</th>
					           <th style="width:100px">등록일</th>  
					         </tr>
					       </thead>
					       <tbody>
					       
							<c:forEach var="qna" items="${articleQnaList}" varStatus="status">
					       		<tr class="qna_tr_p">
									<!-- 전체 레코드 수 - ( (현재 페이지 번호 - 1) * 한 페이지당 보여지는 레코드 수 + 현재 게시물 출력 순서 ) -->
									<td>${pageInfoQna.listCount -((pageInfoQna.page-1)* pageInfoQna.pageBlock + status.index)}</td>
									<td><c:if test="${0 == qna.boardReSeq}"> 
	           		 					답변전
	           		 					</c:if>
	           		 					<c:if test="${1 == qna.boardReSeq}"> 
	           		 					답변완료 
	           		 					</c:if>  
					           		<td>${qna.boardTitle }</td>
									<td>${qna.boardWriter }</td>
									<td>${qna.boardRegTime }</td>
								</tr>
								<tr class="qna_tr_s" style="display: table-row;">
								<td colspan="4"> 		Q ${qna.boardContent}<br>
								 <c:if test="${qna.boardReSeq == 1}">
							       	<c:set var="doneLoop" value="false"/>
							         	<c:forEach  var="ans" items="${qna.AnswserList}" varStatus="status">
							         	  <c:if test="${not doneLoop }">
							         		A 판매자의 답변 : ${ans.boardContent}<br>
                                      	  	판매자의 답변 등록일 : ${ans.boardRegTime}
							         		<c:if test="${status.index+1 eq 1}">
							         	
							         		<c:set var="doneLoop" value="true"/>	
							         		</c:if>
							         	</c:if>
							         	</c:forEach>
							       </c:if>
							  	</td>
							    					    
							        
							          		
                                        		
                                          	 
                                        
									<td colspan="2" style="vertical-align: bottom;">
									<c:if test="${sessionScope.uID eq qna.boardWriter}">
							    	 <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" onclick="openQna('m','<c:out value="${qna.boardNum}"/>')">글 수정하기 <span class=""></span></button>
							         <button class="btn btn-primary dropdown-toggle" data-toggle="dropdown" onclick="openQna('d','<c:out value="${qna.boardNum}"/>')">글 삭제하기 <span class=""></span></button>
							    	</c:if>
									</td>
								</tr>
											
  							</c:forEach>
							</tbody>
					    </table>
					   <%--  <a href="BookList.book?bk2=${book.BK2 }" class="btn btn-large pull-right">Compair Product</a> --%>
					    <c:if test="${!empty articleQnaList }">
						<div class="pagination">
							<ul>
							<li><c:if test="${pageInfoQna.startPage > pageBlock }">
							<a href='<c:url value="/Book.book?bookID=${book.bookID }&page=${pageInfoQna.startPage-pageInfoQna.pageBlock }"/>'>&lsaquo;</a></c:if></li>
							<li>
							<c:forEach var="i" begin="${pageInfoQna.startPage }" end="${pageInfoQna.endPage }" step="1">
							<a href='<c:url value="/Book.book?bookID=${book.bookID}&page=${i}"/>'>${i}</a>
							</c:forEach></li>
<!-- 							<li><a href="#bookqna&page=2">2</a></li> -->
<!-- 							<li><a href="#bookqna#&page=3">3</a></li> -->
<!-- 							<li><a href="#bookqna#&page=4">4</a></li> -->
							<li><a href="#">...</a></li>
							<li><c:if test="${pageInfoQna.endPage < pageInfoQna.maxPage }">
							<a href='<c:url value="/Book.book?bookID=${book.bookID }&page=${pageInfoQna.startPage + pageInfoQna.pageBlock}"/>'>&rsaquo;</a></c:if></li>
							</ul>
							</div>
							<br class="clr"/>
						</c:if>	
					 </div>
<!-- 					 <a href="compair.html" class="btn btn-large pull-right">Compair Product</a> -->
<!-- 						<div class="pagination"> -->
<!-- 							<ul> -->
<%-- 							<li><c:if test="${pageInfo.startPage > pageBlock }"> --%>
<%-- 							<a href='<c:url value="/Book.book?bookID=${book.bookID }&page=${pageInfo.startPage-pageInfo.pageBlock }"/>'>&lsaquo;</a></c:if></li> --%>
<!-- 							<li> -->
<%-- 							<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1"> --%>
<%-- 							<a href='<c:url value="/Book.book?bookID=${book.bookID}&page=${i}"/>'>${i}</a> --%>
<%-- 							</c:forEach></li> --%>
<!-- <!-- 							<li><a href="#bookqna&page=2">2</a></li> --> 
<!-- <!-- 							<li><a href="#bookqna#&page=3">3</a></li> --> 
<!-- <!-- 							<li><a href="#bookqna#&page=4">4</a></li> --> 
<!-- 							<li><a href="#">...</a></li> -->
<%-- 							<li><c:if test="${pageInfo.endPage < pageInfo.maxPage }"> --%>
<%-- 							<a href='<c:url value="/Book.book?bookID=${book.bookID }&page=${pageInfo.startPage + pageInfo.pageBlock}"/>'>&rsaquo;</a></c:if></li> --%>
<!-- 							</ul> -->
<!-- 							</div> -->
<!-- 							<br class="clr"/> -->
						</div>
          			</div>

	</div>
</div>
</div> </div>
</div>
<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->
    <div  id="footerSection">
    <div class="container">
        <div class="row">
            <div class="span3">
                <h5><a href="NoticeList.bo">공지사항/이벤트</a></h5>
             </div>
            <div class="span3">
                <h5><a href="FAQList.bo">고객센터</a></h5>
             </div>
            <div class="span3">
                <h5>회사이름</h5>
                FAX: 02-0000-0000 <br>
                E-mail: xxx@xxx.xxx<br>  
                부산시 OO구 OO로 00 <a href="#">약도</a>  
             </div>
            <div id="socialMedia" class="span3 pull-right">
                <h5>SOCIAL MEDIA </h5>
                <a href="#"><img width="60" height="60" src="themes/images/facebook.png" title="facebook" alt="facebook"/></a>
                <a href="#"><img width="60" height="60" src="themes/images/twitter.png" title="twitter" alt="twitter"/></a>
                <a href="#"><img width="60" height="60" src="themes/images/youtube.png" title="youtube" alt="youtube"/></a>
             </div> 
         </div>
        <p class="pull-right">&copy; BookShop</p>
    </div><!-- Container End -->
    </div>
    <!-- <div class="modal"></div> -->
   <!--  <div class="modal-backdrop fade in"></div> -->
   
    <div id="viewDetail" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="false"
    style="width: 820px; height: 620px; left:42%; top:45%; border: 1px solid #fff;">
		<div class="rv_wrap" style="color: #777; letter-spacing: -1px; text-align: left;">
			<div class="rv_top" style="padding: 20px 20px 20px; border-bottom: 1px solid #c6c6c6; height: 10px; line-height: 18px;" >
				<strong class="rev_title" style="width: 122px; font-size: 20px; height: 19px;">상품평</strong>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">close</button>
			</div>
		  <div class="rv_main">
			<div class="rv_content">
				<div class="info_area">
					<div class="rv_comment">
						<p class="title" id="detail_title">
							
						</p>
					</div>
					<div class="info_text" style="text-align: right; width: 774px; height: 46px; padding: 5px;">
						<div style="display: inline-block; margin-right: 40px;">
						<span id="rv_readcount"></span>
						<span id="review_regtime"></span>
						</div>
					</div>
				</div>
				<div class="scroll_area" style="max-height: 415px;">
					<div class="cont_box">
					<p class="cont_p" style="text-align: center;"><img alt="상품후기이미지" id="cont_img" class="modal_img"></p>
					<p class="cont_text" style="margin: 10px;" id="detail_content"></p>
					<hr>
					</div>
				</div>
				<div class="cont_bottom">
					<div class="">
						
					</div>
				</div>
				 <div></div>	
			
			</div>
		  </div>
		  
		</div>  
	</div>
<!-- Placed at the end of the document so the pages load faster ============================================= -->
    <script src="themes/js/jquery.js" type="text/javascript"></script>
    <script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="themes/js/google-code-prettify/prettify.js"></script>
    <script src="themes/js/bootshop.js"></script>
    <script src="themes/js/jquery.lightbox-0.5.js"></script>
    

</body>
</html>