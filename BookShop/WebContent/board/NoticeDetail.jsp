<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>공지사항</title>
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
   <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
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
	
	<style type="text/css">
	
	 .btn2 {border: 1px solid #D9D4D4; text-align: center; margin: 2px; padding: 3px;}
	 
	#orderTable {text-align: center; border-bottom: 2px solid #D9D4D4; border-top: hidden; height: 3.5em; background-color:#f5f5f5 }
	
	</style>
	
	
	<script type="text/javascript">

// 	alert("정말 회원탈퇴 하시겠습니까?");
// 	$('form').submit(function()	{
		
		
// 		alert("정말 회원탈퇴 하시겠습니까?");
		
// 		return false;
// 	}

function openWin(orderNum){  

    window.open("OrderExchange.mo?orderNum="+orderNum, "반품", "width=520, height=600, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
}  

function openWin2(orderNum){  

    window.open("OrderRefund.mo?orderNum="+orderNum, "교환", "width=520, height=600, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
}  

function openWin3(orderNum){  

    window.open("OrderDelivery.mo?orderNum="+orderNum, "배송", "width=389, height=550, toolbar=no, menubar=no, scrollbars=no, resizable=yes" );  
}  




var ja = jQuery.noConflict();

ja(document).ready(function () {
	
// 	var numb = ja('#hel').text();
		
// 		ja('#hel').val();
// 	alert(numb);
//  comma(numb);
	
	ja('#orderConFirm').click(function(){
		alert("구매확정 하시겠습니까?")
// 		ja("#orderConFirm").attr("href", "OrderConFirmPro.mo?orderNum="+);
	    });
})


// var hell = '100000';
		var hell = document.getElementById(hel2).value;
		
	function comma() {
// 		numb = String(hell);
	    alert(hell);
	    return hell.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
	    
	}
	 




// window.onload = function numberWithCommas() {
	
// 	alert(hell.value);
//     return hell.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
// }

	
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
 <div id="sidebar" class="span3" style="width: 120px;">
        <ul id="sideManu" class="nav nav-tabs nav-stacked">
            <li class="subMenu open"><a> 단계별</a>
                <ul>
                <li><a class="active" href="BookList.book?bk2=1"><i class="icon-chevron-right"></i>1단계 </a></li>
                <li><a href="BookList.book?bk2=2"><i class="icon-chevron-right"></i>2단계</a></li>
                <li><a href="BookList.book?bk2=3"><i class="icon-chevron-right"></i>3단계</a></li>
                </ul>
            </li>
            <li class="subMenu"><a> 분야별 </a>
            <ul style="display:none">
                <li><a href="BookList.book"><i class="icon-chevron-right"></i>JAVA</a></li>
                <li><a href="BookList.book"><i class="icon-chevron-right"></i>JSP</a></li>                                              
                <li><a href="BookList.book"><i class="icon-chevron-right"></i>DATABASE</a></li> 
                <li><a href="BookList.book"><i class="icon-chevron-right"></i>HTML / CSS</a></li>
            </ul>
            </li>
        </ul>
        <br/>
    </div>
<!-- Sidebar end=============================================== -->
	<div class="span9">
    <ul class="breadcrumb">
    </ul>
	<hr class="soft"/>
<!-- 	<a href="./admin/adminMain.jsp">관리자메인</a><br> -->
	
	<table class="table table-bordered">
		<tr><th> <h3>Notice 상세보기</h3>  </th></tr>
		 <tr> 
		 <td>
			<form class="form-horizontal">
<!-- <a href="NoticeList.bo">FAQ 목록</a><br> -->
Notice 내용 <br>


<!-- <input type="button" class="btn" value="수정" onclick="location.href='NoticeModifyForm.bo'"> -->
<!-- <input type="button" class="btn" value="삭제" onclick="location.href='NoticeDeleteForm.bo'">	 -->
				
				
<!-- 				<div class="control-group"> -->
<!-- 					<div class="controls"> -->
<!-- 					  <a href="forgetpass.html" style="text-decoration:underline">Forgot password ?</a> -->
<!-- 					</div> -->
<!-- 				</div> -->
			</form>

		  </td>
		  </tr>
	</table>	
<!-- 				<div class="control-group"> -->
<!-- 				  <div class="controls"> -->
<!-- 					<input type="button" class="btn" value="FAQ 목록" onclick="location.href='NoticeList.bo'">	 -->
<!-- 				  </div> -->
<!-- 				</div>	 -->

<%-- 	<jsp:include page="../search.jsp"></jsp:include>
	
								<c:set value="-1" var="orderNum"></c:set>
                   <c:if test="${empty orderList}">
						<h5>검색결과가 없습니다</h5>
				   </c:if>
<c:if test="${orderList!=null}">
	<c:forEach var="list" items="${orderList}" varStatus="status">
			  <c:if test="${list.orderNum eq list.order_ID}"></c:if>
			<table class="table table-bordered" style="vertical-align: middle;">
			 <c:if test="${orderNum != list.orderNum}">
              <thead>
			  
                <tr>
                  <th colspan="4" id="orderTable">주문번호  ${list.orderNum}${list.orderTime}</th>
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
                <c:if test="${list.orderStatus != '구매완료'}"><div class="btn2"><a href="" onClick="javascript:openWin3(${list.orderDetailCode})">배송조회</a></div></c:if>  
                <c:if test="${list.orderStatus eq '배송완료' or list.orderStatus eq '결제확정' or list.orderStatus eq '배송중'}"><div class="btn2"><a href="" onClick="javascript:openWin(${list.orderDetailCode})">반품신청</a></div></c:if>
                <c:if test="${list.orderStatus eq '배송완료' or list.orderStatus eq '결제확정' or list.orderStatus eq '배송중'}"><div class="btn2"><a href="" onClick="javascript:openWin2(${list.orderDetailCode})">교환신청</a></div></c:if>  
																					<!--                 연결할것 -->
                <c:if test="${list.orderStatus eq '확정'}"><div class="btn2"><a href="ReviewWriteForm.book?bookID=${list.bookID}" id="orderConFrim">상품후기쓰기</a></div></c:if>  
                  </td>
                  
                 <td>
                 <div class="btn2" style="background-color: #9988;">${list.orderStatus}</div>
															<!--                  결제취소 아직 구현안함 -->
                  <c:if test="${list.orderStatus eq '구매완료'}"><div class="btn2">결제취소</div> </c:if>  
                  <c:if test="${list.orderStatus eq '배송완료'}"><div class="btn2"><a href="OrderConFirmPro.mo?orderNum=${list.orderNum}" id="orderConFirm">구매확정완료</a></div> </c:if>
                  <c:if test="${list.orderStatus eq '반품'}"><div class="btn2">반품처리중</div> </c:if>   
                  <c:if test="${list.orderStatus eq '교환'}"><div class="btn2">교환처리중</div> </c:if>
                  <c:if test="${list.orderStatus eq '취소'}"><div class="btn2">결제취소처리중</div> </c:if>    
                   <c:if test="${list.orderStatus eq '교환처리완료'}"><div class="btn2">교환처리완료</div> </c:if>    
                   <c:if test="${list.orderStatus eq '반품처리완료'}"><div class="btn2">반품처리완료</div> </c:if>    
                   <c:if test="${list.orderStatus eq '취소처리완료'}"><div class="btn2">취소처리완료</div> </c:if>    
                 </td>
                </tr>
            
				</tbody>
            </table>
		
		</c:forEach>
</c:if>


 --%>

		   
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
	<a href="NoticeList.bo" class="btn btn-large pull-right">공지사항목록 <i class="icon-arrow-right"></i></a>
	
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