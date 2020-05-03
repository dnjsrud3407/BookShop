<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootshop online Shopping cart - book_list.jsp</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<!-- Bootstrap style --> 
    <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css?ver=2" media="screen"/>
    <link href="themes/css/base.css?ver=1" rel="stylesheet" media="screen"/>
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
	<link href="css/nav.css?ver=1" rel="stylesheet" type="text/css">
	<style type="text/css" id="enject"></style>
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
    	<c:choose>
			<c:when test="${book.bookKategorie_BKID == 0 }">
				<li class="active">${book.BK2 }</li>
			</c:when>
			<c:otherwise>
				<li class="active"><a href="BookList.book?type=L&bk2=${bk2 }">${book.BK2 }</a><span class="divider">/</span></li>
				<li class="active">${book.BK1 } - ${book.BK3 }</li>
			</c:otherwise>
		</c:choose>
    </ul>
	<form class="form-horizontal span6">
		<div class="control-group">
		  <label class="control-label alignL">자세히 </label>
			<select onchange="if(this.value) location.href=(this.value);">
			  <c:forEach var="bookK" items="${bookKategorie }" varStatus="status">
                <option value="BookList.book?type=I&bk2=${bk2 }&BKID=${bookK.bookKategorie_BKID}" 
                	<c:if test="${book.bookKategorie_BKID == bookK.bookKategorie_BKID}"> selected="selected"</c:if>>${bookK.BK1 } - ${bookK.BK3 }
                </option>
			  </c:forEach>
            </select>
		</div>
	  </form>
<c:choose>
	<c:when test="${book.bookKategorie_BKID == 0 }">
		<div id="myTab" class="pull-right">
		 <a href="BookList.book?type=L&page=${pageInfo.page }&bk2=${bk2 }"><span class="btn btn-large"><i class="icon-list"></i></span></a>
		 <a href="BookList.book?type=I&page=${pageInfo.page }&bk2=${bk2 }"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
		</div>
	</c:when>
	<c:otherwise>
		<div id="myTab" class="pull-right">
		 <a href="BookList.book?type=L&page=${pageInfo.page }&bk2=${bk2 }&BKID=${book.bookKategorie_BKID}"><span class="btn btn-large"><i class="icon-list"></i></span></a>
		 <a href="BookList.book?type=I&page=${pageInfo.page }&bk2=${bk2 }&BKID=${book.bookKategorie_BKID}"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
		</div>
	</c:otherwise>
</c:choose>
<br class="clr"/>
<div class="tab-content">
	<div class="tab-pane active" id="blockView">
		<ul class="thumbnails">
		  <c:forEach var="book" items="${bookList }" varStatus="status">
				<li class="span3">
				  <div class="thumbnail">
					<a href="Book.book?bookID=${book.bookID }">
						<img src="boardFile/${book.bookImage}" alt="책 이미지" style="min-width:100px; max-width: 250px; min-height: 100px; max-height: 250px;"/>
					</a>
					<div class="caption">
					  <h5>${book.bookTitle }</h5>
					  <p style="height: 50px;text-overflow: ellipsis;white-space: nowrap; overflow: hidden;"> 
						${book.bookIntroduce }
					  </p>
					    <h4 style="text-align:center"><a class="btn" href="Book.book?bookID=${book.bookID }"> <i class="icon-zoom-in"></i></a> <a class="btn" href="CartAdd.book?bookID=${book.bookID }"><i class="icon-shopping-cart"></i></a> <a class="btn btn-primary" href="#">${book.bookPrice }원</a></h4>
					</div>
				  </div>
				</li>
			</c:forEach>
		</ul>
		<hr class="soft"/>
	</div>
</div>
<c:choose>
	<c:when test="${book.bookKategorie_BKID == 0 }">
		<div class="pagination txCenter">
		<ul>
			<c:if test="${pageInfo.startPage > pageInfo.pageBlock }">
				<li>
					<a href="BookList.book?type=I&bk2=${bk2 }&page=${pageInfo.startPage-pageInfo.pageBlock}">&lsaquo;</a>
				</li>
			</c:if>
			<li>
				<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
					<c:choose>
						<c:when test="${i eq pageInfo.page }"><a href="#">${i}</a></c:when>
	                	<c:otherwise><a href="BookList.book?type=I&bk2=${bk2 }&page=${i}" class="colorB" style="color: #2a84b6; border-color: #5e9bd1;">${i}</a></c:otherwise>
					</c:choose>
				</c:forEach>
			</li>
			<c:if test="${pageInfo.endPage < pageInfo.maxPage }">
				<li>
					<a href='<c:url value="BookList.book?type=I&bk2=${bk2 }&page=${pageInfo.startPage + pageInfo.pageBlock}"/>'>&rsaquo;</a>
				</li>
			</c:if>
		</ul>
		</div>
	</c:when>
	<c:otherwise>
		<div class="pagination txCenter">
		<ul>
			<c:if test="${pageInfo.startPage > pageInfo.pageBlock }">
				<li>
					<a href="BookList.book?type=I&bk2=${bk2 }&BKID=${book.bookKategorie_BKID}&page=${pageInfo.startPage-pageInfo.pageBlock}">&lsaquo;</a>
				</li>
			</c:if>
			<li>
				<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
					<c:choose>
						<c:when test="${i eq pageInfo.page }"><a href="#">${i}</a></c:when>
	                	<c:otherwise><a href="BookList.book?type=I&bk2=${bk2 }&BKID=${book.bookKategorie_BKID}&page=${i}" class="colorB" style="color: #2a84b6; border-color: #5e9bd1;">${i}</a></c:otherwise>
					</c:choose>
				</c:forEach>
			</li>
			<c:if test="${pageInfo.endPage < pageInfo.maxPage }">
				<li>
					<a href='<c:url value="BookList.book?type=I&bk2=${bk2 }&BKID=${book.bookKategorie_BKID}&page=${pageInfo.startPage + pageInfo.pageBlock}"/>'>&rsaquo;</a>
				</li>
			</c:if>
		</ul>
		</div>
	</c:otherwise>
</c:choose>	
<br class="clr"/>
</div>
</div>
</div>
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
<!-- Placed at the end of the document so the pages load faster ============================================= -->
    <script src="themes/js/jquery.js" type="text/javascript"></script>
    <script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="themes/js/google-code-prettify/prettify.js"></script>
    
    <script src="themes/js/bootshop.js"></script>
    <script src="themes/js/jquery.lightbox-0.5.js"></script>
    

</body>
