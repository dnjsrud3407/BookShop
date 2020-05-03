<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<div id="header">
<div class="container">
<div id="welcomeLine" class="row">
    <!-- <div class="span6"></div> -->
    <div class="">
	    <span style="padding-left: 30px;font-size: 15px;">
	    	<c:if test="${sessionScope.uID ne null}"> 
		        welcome ${sessionScope.uID}님
		    </c:if></span>
	    <div class="pull-right">

			<c:if test="${sessionScope.isAdmin eq true }">
				<a href="AdminMain.adm">관리자</a> |
			</c:if>	

<!-- 		로그인 안했을 때 -->
	        <c:if test="${sessionScope.uID eq null}">
	        	<a href="JoinForm.me">회원가입</a> |
	        	<a href="helpCenter.jsp">고객센터</a> |
		        <a href="#login" role="button" data-toggle="modal" style="padding-right:0"><span class="btn btn-mini btn-success" style="font-size: 14px;">Login</span></a>
	        </c:if>
	        
<!-- 	        로그인 했을 때 -->
	         <c:if test="${sessionScope.uID ne null}"> 
		        <a href="LogoutPro.me">로그아웃</a> |
		        <a href="OrderList.mo">마이페이지</a> |
		        <a href="FAQList.bo">고객센터</a> |
		        <a href="CartList.book"><span class="btn btn-mini btn-primary"><i class="icon-shopping-cart icon-white"></i>장바구니 </span> </a>
		    </c:if>
		<div id="login" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="false" >
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
			<h3>로그인<button class="btn" data-dismiss="modal" aria-hidden="true" style="float: right;">X</button></h3>
		  </div>
		  <div class="modal-body">
			<form action="./LoginPro.me" class="form-horizontal loginFrm" method="post">
			  <div class="control-group">								
				<input type="text" id="inputId" name="uID" placeholder="Id">
			  </div>
			  <div class="control-group" style="margin-bottom: 20px;">
				<input type="password" id="inputPassword" name="pw" placeholder="Password">
			  </div>
			 
			<button type="submit" class="btn btn-success">로그인</button>
			<a href="FindId.me" class="btn">아이디 찾기</a>
			<a href="FindPass.me" class="btn">비밀번호 찾기</a>
			</form>		
		  </div>
	</div> 
	    </div>
    </div>
</div>
<!-- Navbar =================================================== -->
<div>
    <a class="brand" href="Main.me"><img src="themes/images/logo.png" alt="Bootsshop"/></a>
    <form class="form-inline navbar-search pull-right" method="get" action="BookSearchList.book?page=1" onsubmit="return checkSearch()">
        <input id="srchFld" name="bookTitle" class="srchTxt" type="text" placeholder="책 검색"/>
        <button type="submit" id="submitButton" class="btn btn-primary">검색</button>
    </form>
</div>



</div>

<div id="menu" style="position: relative; left:10px; z-index: 1;">
    <jsp:include page="./nav.jsp"></jsp:include>
</div>
</div>