<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bootshop online Shopping cart</title>
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
    <link href="themes/css/base.css?ver=2" rel="stylesheet" media="screen"/>
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
    <!--Constom styles -->
    <link href="css/main.css?ver=2" rel="stylesheet" type="text/css">
    <link href="css/nav.css?ver=1" rel="stylesheet" type="text/css">
    <style type="text/css" id="enject"></style>
  
  
  </head>
  
  
  
<script src="./js/jquery-3.4.1.js"></script>


        
<script type="text/javascript">
  // 책 검색하는 작업
  function checkSearch() {
	if($("#srchFld").val() == "") {
		alert('검색어를 입력하세요');
		return false;
	} else {
		return true;
	}
  }
  function getMiddle(type) {
	  // type = 0 -> 베스트 셀러
	  // type = 1 -> 1단계 책
	  // type = 2 -> 2단계 책
	  // type = 3 -> 3단계 책
	  $.ajax({
		  type:"POST",
		  url:"MiddleBook.book?type=" + type,
		  success: function(msg2){	// 베스트셀러
		  	  $(".middle_banner").html(msg2);
		  }
	  });
  }
</script>




<body>
<jsp:include page="./inc/top.jsp"></jsp:include>
<!-- Header End====================================================================== -->


<div id="carouselBlk">
    <div id="myCarousel" class="carousel slide">
        <div class="carousel-inner">
          <c:forEach var="board" items="${eventList }" varStatus="status">
            <c:choose>
              <c:when test="${status.index == 0 }">
		         <div class="item active">
		          <div class="container">
                  <c:if test="${board.fileList.size() > 0 }">
                  	<c:forEach var="image" items="${board.fileList}" varStatus="index">
		              <a href="EventUserDetail.bo?boardNum=${board.boardNum }"><img style="width:100%" src="<c:url value='/boardFile/${image.storedFileName }'/>"/></a>
                  	</c:forEach>
                  </c:if>
		          </div>
		         </div>
               </c:when>
		       <c:otherwise>
		         <div class="item">
		          <div class="container">
                  <c:if test="${board.fileList.size() > 0 }">
                  	<c:forEach var="image" items="${board.fileList}" varStatus="index">		          
		            <a href="EventUserDetail.bo?boardNum=${board.boardNum }"><img style="width:100%" src="<c:url value='/boardFile/${image.storedFileName }'/>" alt=""/></a>
                  	</c:forEach>
                  </c:if>		          
		          </div>
		         </div>
		       </c:otherwise>  
            </c:choose>
          </c:forEach>
          <div class="item">
          <div class="container">
            <a href="EventUserDetail.bo?boardNum=3"><img src="themes/images/carousel/banner3.png" alt=""/></a>
          </div>
          </div>
           <div class="item">
           <div class="container">
            <a href="EventUserDetail.bo?boardNum=4"><img src="themes/images/carousel/banner4.png" alt=""/></a>
          </div>
          </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
      </div> 
</div>
<div id="mainBody">
    <div class="container">
    <div class="row">
<!-- Sidebar ================================================== -->

<!-- Sidebar end=============================================== -->
        <div class="mainContent">     
            <div class="well2 well-small">
            <div class="row-fluid">
            <div id="featured" class="carousel slide">
              <div class="carousel-inner">
              	<!-- 여기 이후에 새로 고침 -->
	              <div class="carousel-inner middle_banner">
	              <div class="item active">
	              <ul class="thumbnails">
	              	<c:forEach var="book" items="${bookList }" begin="0" end="3" varStatus="status">
		                <li class="span3">
		                  <div class="thumbnail" style="height: 280px">
		                  <i class="tag"></i>
		                    <a href="Book.book?bookID=${book.bookID }"><img src="upload/${book.bookImage}" alt="${book.bookTitle }"></a>
		                    <div class="caption">
		                      <h5>${book.bookTitle }</h5>
		                      ${book.bookPublisher } | ${book.bookPrice }
		                    </div>
		                  </div>
		                </li>
	              	</c:forEach>
	              </ul>
	              </div>
	              <div class="item" >
	              <ul class="thumbnails">
	                <c:forEach var="book" items="${bookList }" begin="4" end="7" varStatus="status">
		                <li class="span3">
		                  <div class="thumbnail" style="height: 280px">
		                  <i class="tag"></i>
		                    <a href="Book.book?bookID=${book.bookID }"><img src="upload/${book.bookImage}" alt="${book.bookTitle }"></a>
		                    <div class="caption">
		                      <h5>${book.bookTitle }</h5>
		                      ${book.bookPublisher } | ${book.bookPrice }
		                    </div>
		                  </div>
		                </li>
	              	</c:forEach>
	              </ul>
	              </div>
	              </div>
              </div>
              <a class="left carousel-control" href="#featured" data-slide="prev">‹</a>
              <a class="right carousel-control" href="#featured" data-slide="next">›</a>
              </div>
              <div class="Middle_center">
              	  <h4 style="float: left; display: inline-block; margin: 15px;"><a href="#" onclick="getMiddle(0)">새로나온책</a></h4>
                  <h4 style="float: left; display: inline-block; margin: 15px;"><a href="#" onclick="getMiddle(1)">1단계</a></h4>
                  <h4 style="float: left; display: inline-block; margin: 15px;"><a href="#" onclick="getMiddle(2)">2단계</a></h4>
                  <h4 style="float: left; display: inline-block; margin: 15px;"><a href="#" onclick="getMiddle(3)">3단계</a></h4>
              </div>
              </div>
        </div>
        <!-- 로드맵 배너 -->
        <div class="roadMap">
          <img src="themes/images/roadMap.png" alt=""/>
        </div>
        
        <!-- 베스트 셀러 시작 -->
        <div class="best_seller">
          <div class="best_title">베스트셀러 Top 5</div>
             <table class="best_table">
             	<c:forEach var="book" items="${bestList }" varStatus="status">
             	<c:choose>
             		<c:when test="${status.index == 0 }">
		             	<tr>
		             	  <td class="best_num Bestbtn">${status.index+1 }</td>
		             	  <td>
		             	  	<a href="Book.book?bookID=${book.bookID }"><img src="upload/${book.bookImage }" alt="${book.bookTitle }" class="best_img"/></a>
		             	  </td>
		             	  <td class="best_booktitle">
		             	  	<a href="Book.book?bookID=${book.bookID }">${book.bookTitle }</a>
		             	  </td>
		             	</tr>             		
             		</c:when>	
             		<c:otherwise>
		             	<tr>
		             	  <td class="best_num Bestbtn">${status.index+1 }</td>
		             	  <td colspan="2" class="best_booktitle"><a href="Book.book?bookID=${book.bookID }">${book.bookTitle }</a></td>
		             	</tr>              		
             		</c:otherwise>
             	</c:choose>
             	</c:forEach>
             </table>
		 </div>
		 <!-- 베스트 셀러 끝 -->
        </div>
        </div>
    </div>
</div>
<br><br><br>
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
</html>