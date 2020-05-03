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
     <link href="themes/css/mycus.css" rel="stylesheet"/>
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
	<style type="text/css" id="enject"></style>
	<script type="text/javascript" src="./js/jquery-3.4.1.js"></script>
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

document.ready(function () {
	var fileValue = $('')
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
<jsp:include page="/member/top.jsp"></jsp:include>
<!-- header -->

<!-- Header End====================================================================== -->
<div id="mainBody">
	<div class="container">
	<div class="row">
<!-- Sidebar ================================================== -->


<jsp:include page="/member/left.jsp"></jsp:include>



<!-- Sidebar end=============================================== -->
	<div class="span9">
    <ul class="breadcrumb">
    </ul>
	<h3>상품 사용후기<a href="products.html" class="btn btn-large pull-right"><i class="icon-arrow-left"></i> Continue Shopping </a></h3>	
	<hr class="soft"/>
	<section id="reviewForm">
		<form action="ReviewWritePro.book" method="post" enctype="multipart/form-data" name="reviewForm" onsubmit="return scoreCheck()">
			<input type="hidden" name="kID" value="103">
			<input type="hidden" name="bookID" value="${param.bookID }">
			<input type="hidden" name="boardWriter" value="${sessionScope.uID }">
			<table>
				<tr>
					<td class="td_left"><label for="boardTitle">제목</label></td>
					<td class="td_right">
					<div class="controls"><input type="text" class="input-xxlarge" name="boardTitle" id="review_ti" required="required"></div>
          			</td>
				</tr>
				
				<tr>
					<td class="td_left"><label for="content">내용</label></td>
					<td class="td_right">
						<div class="controls"> <textarea class="input-xxlarge" name="boardContent" id="review_ta" rows="3" required="required"></textarea></div>
						<!-- <textarea rows="13" cols="40" name="content" id="content"></textarea> -->
					</td>
				</tr>
				<tr>
					<td class="td_left"><label for="image">사진</label></td>
					<td class="td_right"><input type="file" name="image" id="image" title="첨부파일"/></td>
				</tr>
				
				<tr>
					<td class="td_left"><label class="control-label" for="select01">평점</label></td>
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

								<c:set value="-1" var="orderNum"></c:set>
<c:if test="${orderList!=null}">
	<c:forEach var="list" items="${orderList}" varStatus="status">
<%-- 			  <c:if test="${list.orderNum eq list.order_ID}"></c:if> --%>
			<table class="table table-bordered" style="vertical-align: middle;">
			 <c:if test="${orderNum != list.orderNum}">
              <thead>
			  
                <tr>
                  <th colspan="4" id="orderTable">주문번호  ${list.orderNum}</th>
                  <th id="orderTable">상태</th>
				</tr>
              </thead> 
              <tbody>
            			  <c:set value="${list.orderNum}" var="orderNum"></c:set>
			</c:if>
                <tr>
                  <td> <img width="80px" height="100px" src="themes/images/products/4.jpg" alt=""/></td>
                  <td style="border-left: none;">상품명:${list.bookTitle}<br>상품옵션:어쩌구<br>주문번호:${list.orderNum}<br>주문일시:${list.orderTime}</td>
                  <td><span class="label">쿠폰</span> ${list.coupon_name} <br> <span class="label">포인트</span>  ${list.pointValue} <br><span class="label">총금액</span>${total}</td>
				 <!--  <td> -->
				<!-- 	<div class="input-append"><input class="span1" style="max-width:34px" placeholder="1" id="appendedInputButtons" size="16" type="text"><button class="btn" type="button"><i class="icon-minus"></i></button><button class="btn" type="button"><i class="icon-plus"></i></button><button class="btn btn-danger" type="button"><i class="icon-remove icon-white"></i></button>				</div> -->
				 <!--  </td> -->
                  <td>
                   <div class="btn2"><a href="OrderDetail.mo?orderNum=${list.orderNum}">주문내역</a></div>
                   <div class="btn2"><a href="">구매완료</a></div>
                   <div class="btn2"><a href="">배송조회(api로할지 모양만띄울지 고민)</a></div>
                   <div class="btn2"><a href="OrderExchange.mo">반품신청</a></div> <!-- order_tb테이블의 orderStatus의 값에 따라 보여지는게 다를것 -->
                   <div class="btn2"><a href="">교환신청</a></div> <!-- order_tb테이블의 orderStatus의 값에 따라 보여지는게 다를것 -->
                   <div class="btn2"><a href="">상품평</a></div>
                  </td>
                 <td>
                   <div class="btn2"><a href="">구매취소</a></div> <!-- order_tb테이블의 orderStatus의 값에 따라 보여지는게 다를것 -->
                 </td>
                </tr>
            
				</tbody>
            </table>
		
		</c:forEach>
</c:if>
		   
		   
		   
		   
		   
            <table class="table table-bordered">
			<tbody>
				<!--  <tr>
                  <td>  -->
			<!-- 	<form class="form-horizontal">
				<div class="control-group">
				<label class="control-label"><strong> VOUCHERS CODE: </strong> </label>
				<div class="controls">
				<input type="text" class="input-medium" placeholder="CODE">
				<button type="submit" class="btn"> ADD </button>
				</div>
				</div>
				</form> -->
			<!-- 	</td>
                </tr> -->
				
			</tbody>
			</table>
			
		<!-- 	<table class="table table-bordered">
			 <tr><th>ESTIMATE YOUR SHIPPING </th></tr>
			 <tr> 
			 <td>
				<form class="form-horizontal">
				  <div class="control-group">
					<label class="control-label" for="inputCountry">Country </label>
					<div class="controls">
					  <input type="text" id="inputCountry" placeholder="Country">
					</div>
				  </div>
				  <div class="control-group">
					<label class="control-label" for="inputPost">Post Code/ Zipcode </label>
					<div class="controls">
					  <input type="text" id="inputPost" placeholder="Postcode">
					</div>
				  </div>
				  <div class="control-group">
					<div class="controls">
					  <button type="submit" class="btn">ESTIMATE </button>
					</div>
				  </div>
				</form>				  
			  </td>
			  </tr>
            </table>		 -->
<!-- 	<a href="products.html" class="btn btn-large"><i class="icon-arrow-left"></i> Continue Shopping </a> -->
	<a href="login.html" class="btn btn-large pull-right">Next <i class="icon-arrow-right"></i></a>
	
</div>
</div></div>
</div>
<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->
	<div  id="footerSection">
	<div class="container">
		<div class="row">
			<div class="span3">
				<h5><a href="NoticeList.bo">공지사항/이벤트</a></h5>
				<!-- <a href="login.html">YOUR ACCOUNT</a>
				<a href="login.html">PERSONAL INFORMATION</a> 
				<a href="login.html">ADDRESSES</a> 
				<a href="login.html">DISCOUNT</a>  
				<a href="login.html">ORDER HISTORY</a> -->
			 </div>
			<div class="span3">
				<h5><a href="FAQList.bo">고객센터</a></h5>
		<!-- 		<a href="contact.html">CONTACT</a>  
				<a href="register.html">REGISTRATION</a>  
				<a href="legal_notice.html">LEGAL NOTICE</a>  
				<a href="tac.html">TERMS AND CONDITIONS</a> 
				<a href="faq.html">FAQ</a> -->
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
		<p class="pull-right">&copy; Bootshop</p>
	</div><!-- Container End -->
	</div>
<!-- Placed at the end of the document so the pages load faster ============================================= -->
	<script src="themes/js/jquery.js" type="text/javascript"></script>
	<script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="themes/js/google-code-prettify/prettify.js"></script>
	
	<script src="themes/js/bootshop.js"></script>
    <script src="themes/js/jquery.lightbox-0.5.js"></script>
	
	<!-- Themes switcher section ============================================================================================= -->
<div id="secectionBox">
<link rel="stylesheet" href="themes/switchthemeswitch.css" type="text/css" media="screen" />
<script src="themes/switch/theamswitcher.js" type="text/javascript" charset="utf-8"></script>
	<div id="themeContainer">
	<!-- <div id="hideme" class="themeTitle">Style Selector</div>
	<div class="themeName">Oregional Skin</div>
	<div class="images style">
	<a href="themes/css/#" name="bootshop"><img src="themes/switch/images/clr/bootshop.png" alt="bootstrap business templates" class="active"></a>
	<a href="themes/css/#" name="businessltd"><img src="themes/switch/images/clr/businessltd.png" alt="bootstrap business templates" class="active"></a>
	</div>
	<div class="themeName">Bootswatch Skins (11)</div>
	<div class="images style">
		<a href="themes/css/#" name="amelia" title="Amelia"><img src="themes/switch/images/clr/amelia.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="spruce" title="Spruce"><img src="themes/switch/images/clr/spruce.png" alt="bootstrap business templates" ></a>
		<a href="themes/css/#" name="superhero" title="Superhero"><img src="themes/switch/images/clr/superhero.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="cyborg"><img src="themes/switch/images/clr/cyborg.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="cerulean"><img src="themes/switch/images/clr/cerulean.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="journal"><img src="themes/switch/images/clr/journal.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="readable"><img src="themes/switch/images/clr/readable.png" alt="bootstrap business templates"></a>	
		<a href="themes/css/#" name="simplex"><img src="themes/switch/images/clr/simplex.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="slate"><img src="themes/switch/images/clr/slate.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="spacelab"><img src="themes/switch/images/clr/spacelab.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="united"><img src="themes/switch/images/clr/united.png" alt="bootstrap business templates"></a>
		<p style="margin:0;line-height:normal;margin-left:-10px;display:none;"><small>These are just examples and you can build your own color scheme in the backend.</small></p>
	</div>
	<div class="themeName">Background Patterns </div>
	<div class="images patterns">
		<a href="themes/css/#" name="pattern1"><img src="themes/switch/images/pattern/pattern1.png" alt="bootstrap business templates" class="active"></a>
		<a href="themes/css/#" name="pattern2"><img src="themes/switch/images/pattern/pattern2.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern3"><img src="themes/switch/images/pattern/pattern3.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern4"><img src="themes/switch/images/pattern/pattern4.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern5"><img src="themes/switch/images/pattern/pattern5.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern6"><img src="themes/switch/images/pattern/pattern6.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern7"><img src="themes/switch/images/pattern/pattern7.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern8"><img src="themes/switch/images/pattern/pattern8.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern9"><img src="themes/switch/images/pattern/pattern9.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern10"><img src="themes/switch/images/pattern/pattern10.png" alt="bootstrap business templates"></a>
		
		<a href="themes/css/#" name="pattern11"><img src="themes/switch/images/pattern/pattern11.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern12"><img src="themes/switch/images/pattern/pattern12.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern13"><img src="themes/switch/images/pattern/pattern13.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern14"><img src="themes/switch/images/pattern/pattern14.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern15"><img src="themes/switch/images/pattern/pattern15.png" alt="bootstrap business templates"></a>
		
		<a href="themes/css/#" name="pattern16"><img src="themes/switch/images/pattern/pattern16.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern17"><img src="themes/switch/images/pattern/pattern17.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern18"><img src="themes/switch/images/pattern/pattern18.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern19"><img src="themes/switch/images/pattern/pattern19.png" alt="bootstrap business templates"></a>
		<a href="themes/css/#" name="pattern20"><img src="themes/switch/images/pattern/pattern20.png" alt="bootstrap business templates"></a>
		 
	</div>
	</div>
</div> -->
<span id="themesBtn"></span>
</body>
</html>