<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
//현재세션객체 sid세션이 존재할경우 string 타잆변수에 세션 아이디저장
//위에조건이 부합될때만 저장이될수있도록할것임 
	String uID = null;
	if(session.getAttribute("uID") != null){
		uID = (String)session.getAttribute("uID");
	}
%>
<style type="text/css">
.logoutSection{
 	text-align: right;
 }
 .nav-link {
 padding-top: 25px ;
 }
 </style>
 
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

<!--           Topbar Search -->
<!--           <form class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search"> -->
<!--             <div class="input-group"> -->
<!--               <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2"> -->
<!--               <div class="input-group-append"> -->
<!--                 <button class="btn btn-primary" type="button"> -->
<!--                   <i class="fas fa-search fa-sm"></i> -->
<!--                 </button> -->
<!--               </div> -->
<!--             </div> -->
<!--           </form> -->

          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - gotoMain.me -->
			<li class="nav-item no-arrow mx-1">
			 <a class="nav-link" href="Main.me"> 
			 <i class="fas fa-chalkboard">
			 </i><span class="badge badge-danger badge-counter"></span>
			</a> 
			</li>
            <div class="topbar-divider d-none d-sm-block"></div>
<%--             <%if(uID != null) {%> --%>

			<a class="nav-link"><%=uID %> 님 </a>
            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="logoutSection">
            
              <a class="nav-link" href="LogoutPro.me">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"> 로그아웃
                </span> 
<!--                 <img class="img-profile rounded-circle" src="https://source.unsplash.com/QAB-WJcbgJk/60x60"> -->
              </a>
           

            </li>
<%-- 	<%} %> --%>

          </ul>

        </nav>