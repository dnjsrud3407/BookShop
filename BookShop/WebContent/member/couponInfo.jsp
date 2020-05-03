<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>마이페이지</title>
     	<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css"/>
        <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
        <script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
        <!-- datepicker 한국어로 -->
        <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/i18n/datepicker-ko.js"></script>
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
	<style type="text/css">
	
	 .btn2{border: 1px solid #D9D4D4; text-align: center; margin: 2px; padding: 3px;}
	 #coupontable{table-layout: fixed; width: 800px;}
	
	</style>
	
	
	
	   <style>    
        
            /* Default */
            input[type=text],input[type=password]{font-family:"Malgun Gothic","맑은 고딕",Dotum,"돋움",Arial,sans-serif}
            *{margin:0;padding:0;font-family:"Malgun Gothic","맑은 고딕",Dotum,"돋움",Arial,sans-serif}
            body{font-size:12px;color:#555;background:transparent;-webkit-user-select:none;-moz-user-select:none;-webkit-text-size-adjust:none;-moz-text-size-adjust:none;-ms-text-size-adjust:none}
            ol,ul{list-style:none} 
            table{table-layout:fixed;width:100%;border-collapse:collapse;border-spacing:0}
            caption{overflow:hidden;width:0;height:0;font-size:0;line-height:0;text-indent:-999em}
            img,fieldset{border:0}
            legend{height:0;visibility:hidden}
            em,address{font-style:normal}
            img{border:0 none;vertical-align:middle}
            a{color:#555;text-decoration:none}
            input,select{margin:0;padding:0;vertical-align:middle}
            button{margin:0;padding:0;font-family:inherit;border:0 none;background:transparent;cursor:pointer}
            button::-moz-focus-inner{border:0;padding:0}
            header,footer,aside,nav,section,article{display:block}

            .clearfix{*zoom:1}
            .clearfix:after{content:"";display:block;clear:both;overflow:hidden}

            /* Search */
            .searchBox{border:none}
            .searchBox tbody th{padding:20px 10px 20px 35px;font-size:14px;font-weight:bold;text-align:left;vertical-align:top;border:none;background:#e6e6e6 }
            .searchBox tbody td{padding:12px 10px 12px 25px;border:none;background-color:#efefef}
        
            .searchDate{overflow:hidden;margin-bottom:10px;*zoom:1}
            .searchDate:after{display:block;clear:both;content:''}
            .searchDate li{position:relative;float:left;margin:0 7px 0 0}
            .searchDate li .chkbox2{display:block;text-align:center}
            .searchDate li .chkbox2 input{position:absolute;z-index:-1}
            .searchDate li .chkbox2 label{display:block;width:72px;height:26px;font-size:14px;font-weight:bold;color:#fff;text-align:center;line-height:25px;text-decoration:none;cursor:pointer;background:#a5b0b6}
            .searchDate li .chkbox2.on label{background:#ec6a6a}
        
            .demi{display:inline-block;margin:0 1px;vertical-align:middle}
            .inpType{padding-left:6px;height:24px;line-height:24px;border:1px solid #dbdbdb}
            .btncalendar{display:inline-block;width:22px;height:22px;background:url(images/btn_calendar.gif) center center no-repeat;text-indent:-999em}


        </style>
	
  <script>      
        var jb = jQuery.noConflict();

        jb(document).ready(function() {

            //datepicker 한국어로 사용하기 위한 언어설정
//              jb.datepicker.setDefaults($.datepicker.regional['ko']);     
        
            // Datepicker            
             jb(".datepicker").datepicker({
                showButtonPanel: true,
                dateFormat: "yy-mm-dd",
                onClose : function ( selectedDate ) {
                
                    var eleId = $(this).attr("id");
                    var optionName = "";

                    if(eleId.indexOf("StartDate") > 0) {
                        eleId = eleId.replace("StartDate", "EndDate");
                        optionName = "minDate";
                    } else {
                        eleId = eleId.replace("EndDate", "StartDate");
                        optionName = "maxDate";
                    }

                    jb("#"+eleId).datepicker( "option", optionName, selectedDate );        
                    jb(".searchDate").find(".chkbox2").removeClass("on"); 
                }
            }); 

            //시작일.
            /*$('#searchStartDate').datepicker("option","onClose", function( selectedDate ) {    
                // 시작일 datepicker가 닫힐때
                // 종료일의 선택할수있는 최소 날짜(minDate)를 선택한 시작일로 지정
                $("#searchEndDate").datepicker( "option", "minDate", selectedDate );
                $(".searchDate").find(".chkbox2").removeClass("on");
            });
            */

            //종료일.
            /*$('#searchEndDate').datepicker("option","onClose", function( selectedDate ) {    
                // 종료일 datepicker가 닫힐때
                // 시작일의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
                $("#searchStartDate").datepicker( "option", "maxDate", selectedDate );
                $(".searchDate").find(".chkbox2").removeClass("on");
            });
            */

            jb(".dateclick").dateclick();    // DateClick
            jb(".searchDate").schDate();        // searchDate
            
        });

        // Search Date
        jQuery.fn.schDate = function(){
            var jbobj = jb(this);
            var jbchk = jbobj.find("input[type=radio]");
            jbchk.click(function(){                
            	 jb('input:not(:checked)').parent(".chkbox2").removeClass("on");
            	 jb('input:checked').parent(".chkbox2").addClass("on");                    
            });
        };

        // DateClick
        jQuery.fn.dateclick = function(){
            var $obj = $(this);
            $obj.click(function(){
            	 jb(this).parent().find("input").focus();
            });
        }    

        
        function setSearchDate(start){

            var num = start.substring(0,1);
            var str = start.substring(1,2);

            var today = new Date();

            //var year = today.getFullYear();
            //var month = today.getMonth() + 1;
            //var day = today.getDate();
            
            var endDate =  jb.datepicker.formatDate('yy-mm-dd', today);
            jb('#searchEndDate').val(endDate);
            
            if(str == 'd'){
                today.setDate(today.getDate() - num);
            }else if (str == 'w'){
                today.setDate(today.getDate() - (num*7));
            }else if (str == 'm'){
                today.setMonth(today.getMonth() - num);
                today.setDate(today.getDate() + 1);
            }

            var startDate =  jb.datepicker.formatDate('yy-mm-dd', today);
            jb('#searchStartDate').val(startDate);
                    
            // 종료일은 시작일 이전 날짜 선택하지 못하도록 비활성화
             jb("#searchEndDate").datepicker( "option", "minDate", startDate );
            
            // 시작일은 종료일 이후 날짜 선택하지 못하도록 비활성화
             jb("#searchStartDate").datepicker( "option", "maxDate", endDate );
            
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
		<li><a href="OrderList.mo">마이페이지</a> <span class="divider">/</span></li>
		<li class="active">쿠폰정보</li>
    </ul>
	<h3>쿠폰 정보</h3>	
	<br>
	<!-- <table class="table table-bordered"> -->
		<!-- <tr><th> I AM ALREADY REGISTERED  </th></tr>
		 <tr> 
		 <td>
			<form class="form-horizontal">
				<div class="control-group">
				  <label class="control-label" for="inputUsername">Username</label>
				  <div class="controls">
					<input type="text" id="inputUsername" placeholder="Username">
				  </div>
				</div>
				<div class="control-group">
				  <label class="control-label" for="inputPassword1">Password</label>
				  <div class="controls">
					<input type="password" id="inputPassword1" placeholder="Password">
				  </div>
				</div>
				<div class="control-group">
				  <div class="controls">
					<button type="submit" class="btn">Sign in</button> OR <a href="register.html" class="btn">Register Now!</a>
				  </div>
				</div>
				<div class="control-group">
					<div class="controls">
					  <a href="forgetpass.html" style="text-decoration:underline">Forgot password ?</a>
					</div>
				</div>
			</form>
		  </td>
		  </tr>
	</table>		 -->
			
			<form id="searchDate" method="get" action="SearchCouponProAction.me">
            
        <!-- search -->
        <table class="searchBox">
            <caption>조회</caption>
            <colgroup>
                <col width="123px">
                <col width="*">
            </colgroup>
            <tbody>
                <tr>
                    <th>조회기간</th>
                    <td>
                        <ul class="searchDate">
                            <li>
                                <span class="chkbox2">
                                    <input type="radio" name="dateType" id="dateType1" onclick="setSearchDate('0d')"/>
                                    <label for="dateType1">당일</label>
                                </span>
                            </li>
                            <li>
                                <span class="chkbox2">
                                    <input type="radio" name="dateType" id="dateType2" onclick="setSearchDate('3d')"/>
                                    <label for="dateType2">3일</label>
                                </span>
                            </li>
                            <li>
                                <span class="chkbox2">
                                    <input type="radio" name="dateType" id="dateType3" onclick="setSearchDate('1w')"/>
                                    <label for="dateType3">1주</label>
                                </span>
                            </li>
                            <li>
                                <span class="chkbox2">
                                    <input type="radio" name="dateType" id="dateType4" onclick="setSearchDate('2w')"/>
                                    <label for="dateType4">2주</label>
                                </span>
                            </li>
                            <li>
                                <span class="chkbox2">
                                    <input type="radio" name="dateType" id="dateType5" onclick="setSearchDate('1m')"/>
                                    <label for="dateType5">1개월</label>
                                </span>
                            </li>
                            <li>
                                <span class="chkbox2">
                                    <input type="radio" name="dateType" id="dateType6" onclick="setSearchDate('3m')"/>
                                    <label for="dateType6">3개월</label>
                                </span>
                            </li>
                            <li>
                                <span class="chkbox2">
                                    <input type="radio" name="dateType" id="dateType7" onclick="setSearchDate('6m')"/>
                                    <label for="dateType7">6개월</label>
                                </span>
                            </li>
                        </ul>
                        
                        <div class="clearfix">
                            <!-- 시작일 -->
                            <span class="dset">
                                <input type="text" class="datepicker inpType" name="searchStartDate" id="searchStartDate" >
                                <a href="#none" class="btncalendar dateclick">달력</a>
                            </span>
                            <span class="demi">~</span>
                            <!-- 종료일 -->
                            <span class="dset">
                                <input type="text" class="datepicker inpType" name="searchEndDate" id="searchEndDate" >
                                <a href="#none" class="btncalendar dateclick">달력</a>
                                <input type="submit" value="검색">
                            </span>
                        </div>    
                    </td>
                </tr>

            <tbody>
        </table>
        </form>
			
			
		    <c:if test="${empty couponInfo}">
			      <h4>검색 결과가 없습니다</h4>
			</c:if>


	
	  	<c:if test="${!empty couponInfo}">
<div id="grids">
<ul class="nav nav-tabs" id="myTab">
  <li><a href="#one" data-toggle="tab">사용가능</a></li>
  <li class="active"><a href="#two" data-toggle="tab">사용완료 / 기간만료</a></li>
</ul>
<div class="tab-content">
  <div class="tab-pane" id="one">
  <div class="row-fluid">
	 <div class="span12" style="position: relative;">
	  <p>
	  
	  

			<table class="table table-bordered" id="coupontable">
<!-- 		 	 and couponInfo.couponReg_date-couponInfo.couponEnd_date=0 -->
              <thead>
                <tr>
                  <th colspan="2">쿠폰종류</th>
                  <th>쿠폰정보</th>
                  <th>유효기간</th>
				</tr>
              </thead> 
              <tbody>
		 <c:forEach var="couponInfo" items="${couponInfo}" varStatus="status">  
		 	 <c:if test="${couponInfo.couponStatus eq '사용안함'}">
                <tr>
                  <td> <img width="80px" height="100px" src="img/20-207112_coupon-by-user-role-for-woocommerce-coupon-icon.png" alt=""/></td>
                  <td style="border-left: none;"><span class="label">쿠폰</span>${couponInfo.coupon_name}<br>쿠폰상태:${couponInfo.couponStatus}<br>
                  <td><span class="label">쿠폰내용</span> ${couponInfo.couponContent} <br></td>
				 <!--  <td> -->
				<!-- 	<div class="input-append"><input class="span1" style="max-width:34px" placeholder="1" id="appendedInputButtons" size="16" type="text"><button class="btn" type="button"><i class="icon-minus"></i></button><button class="btn" type="button"><i class="icon-plus"></i></button><button class="btn btn-danger" type="button"><i class="icon-remove icon-white"></i></button>				</div> -->
				 <!--  </td> -->
                  <td>
               		${couponInfo.couponReg_date} ~ ${couponInfo.couponEnd_date}
                 </td>
				
                </tr>
		   </c:if>
		   </c:forEach>
	
				</tbody>
	  
            </table>
	  
	  </p>
	  </div>
		   </c:if>
	  
	  
	  
	  
	   <div class="pagination pagination-centered"> 
        <ul>
<%-- 			    <c:choose> --%>
<%-- 			    <c:when test="${pageInfo.page <= 1}"> --%>
			   			 <li><a>이전</a></li>
<%-- 			    </c:when> --%>
<%-- 			    <c:otherwise> --%>
<%-- 			          <li><a href="CouponInfoAction.me?page=${pageInfo.page -1}">이전</a></li> --%>
<%-- 			    </c:otherwise> --%>
<%-- 			   </c:choose>   --%>
<%--         	<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1"> --%>
<%--           				<li><a href="CouponInfoAction.me?page=${i}">${i}</a></li> --%>
<%--        		 </c:forEach>   --%>
<%-- 				   <c:choose> --%>
<%-- 				    <c:when test="${pageInfo.page >= pageInfo.maxPage}"> --%>
				   			 <li><a>다음</a></li>
<%-- 				    </c:when> --%>
<%-- 				    <c:otherwise> --%>
<%-- 				          <li><a href="CouponInfoAction.me?page=${pageInfo.page +1}">다음</a></li> --%>
<%-- 				    </c:otherwise> --%>
<%-- 				   </c:choose>       --%>
        </ul>
      </div>
	  
	  
	  
  </div>
  </div>
  
  
  
	<c:if test="${!empty couponInfo}">
  <div class="tab-pane active" id="two">
  <div class="row-fluid">
	  <div class="span12">
		<p>
			<table class="table table-bordered">
              <thead>
                <tr>
                  <th colspan="2">쿠폰종류</th>
                  <th>쿠폰정보</th>
                  <th>유효기간</th>
				</tr>
              </thead> 
              <tbody>
		 <c:forEach var="couponInfo" items="${couponInfo}" varStatus="status">  
		     <c:if test="${couponInfo.couponStatus eq '사용' or couponInfo.couponStatus eq '만료'}">
                <tr>
                  <td> <img width="80px" height="100px" src="img/20-207112_coupon-by-user-role-for-woocommerce-coupon-icon.png" alt=""/></td>
                  <td style="border-left: none;"><span class="label">쿠폰</span>${couponInfo.coupon_name}<br>쿠폰상태:${couponInfo.couponStatus}<br>
                  <td><span class="label">쿠폰내용</span> ${couponInfo.couponContent}</td>
				 <!--  <td> -->
				<!-- 	<div class="input-append"><input class="span1" style="max-width:34px" placeholder="1" id="appendedInputButtons" size="16" type="text"><button class="btn" type="button"><i class="icon-minus"></i></button><button class="btn" type="button"><i class="icon-plus"></i></button><button class="btn btn-danger" type="button"><i class="icon-remove icon-white"></i></button>				</div> -->
				 <!--  </td> -->
                  <td>
               				${couponInfo.couponReg_date} ~ ${couponInfo.couponEnd_date}
                  </td>
                </tr>
            
		   </c:if>
		   </c:forEach>
	
				</tbody>
            </table>
		 
		
		</p>
		
<!-- 	     <div class="span3"> -->
      <div class="pagination pagination-centered"> 
        <ul>
<%-- 			    <c:choose> --%>
<%-- 			    <c:when test="${pageInfo.page <= 1}"> --%>
			   			 <li><a>이전</a></li>
<%-- 			    </c:when> --%>
<%-- 			    <c:otherwise> --%>
<%-- 			          <li><a href="CouponInfoAction.me?page=${pageInfo.page -1}">이전</a></li> --%>
<%-- 			    </c:otherwise> --%>
<%-- 			   </c:choose>   --%>
<%--         	<c:forEach var="i" begin="${pageInfo.startPage}" end="${pageInfo.endPage}" step="1"> --%>
<%--           				<li><a href="CouponInfoAction.me?page=${i}">${i}</a></li> --%>
<%--        		 </c:forEach>   --%>
<%-- 				   <c:choose> --%>
<%-- 				    <c:when test="${pageInfo.page >= pageInfo.maxPage}"> --%>
				   			 <li><a>다음</a></li>
<%-- 				    </c:when> --%>
<%-- 				    <c:otherwise> --%>
<%-- 				          <li><a href="CouponInfoAction.me?page=${pageInfo.page +1}">다음</a></li> --%>
<%-- 				    </c:otherwise> --%>
<%-- 				   </c:choose>       --%>
        </ul>
      </div>
	  </div>
	
	  </div>
		   </c:if>
	  

      
  </div>
  </div>
  
  
  
  
  
  
  
  
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
<!-- 	<a href="login.html" class="btn btn-large pull-right">Next <i class="icon-arrow-right"></i></a> -->
	
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
