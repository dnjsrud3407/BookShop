<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Sidebar ================================================== -->
    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href='<c:url value="/AdminMain.adm"/>'>
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-book"></i>
        </div>
        <div class="sidebar-brand-text mx-3">ITBOOKS</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0"> 

      <!-- Nav Item - Tables -->
      <li class="nav-item">
<!--         <a class="nav-link" href="tables.html"> -->
        <a class="nav-link" href="MemberList.adm">
          <i class="fas fa-fw fa-table"></i>
<!--           <i class=material-icons style="font-size:20px">person_add</i> -->
          <span>회원관리</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        주문 / 매출 조회
      </div>
      
      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
<!--           <i class="material-icons" style="font-size:20px">library_books</i> -->
          <span>주문/매출조회</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">주문관리</h6>
            <a class="collapse-item" href="OrderList.adm">주문내역</a>    
            
            <h6 class="collapse-header">매출관리</h6> 
            <!-- orderStatus 이름 내가 마마대로지정가능 -->
<!--             <a class="collapse-item" href="OrderList.adm?orderStatus=결제요청">결제내역</a> -->
<!--             <a class="collapse-item" href="OrderList.adm?orderStatus=취소요청">취소내역</a> -->
<!--             <a class="collapse-item" href="OrderList.adm?orderStatus=반품요청">반품내역</a> -->
<!--             <a class="collapse-item" href="OrderList.adm?orderStatus=교환요청">교환내역</a> -->
             
            <a class="collapse-item" href="OrderCompList.adm?orderStatus=결제완료">결제내역</a>
            <a class="collapse-item" href="OrderCompList.adm?orderStatus=취소요청">취소내역</a>
            <a class="collapse-item" href="OrderCompList.adm?orderStatus=반품요청">반품내역</a>
            <a class="collapse-item" href="OrderCompList.adm?orderStatus=교환요청">교환내역</a>

            

          </div>
        </div>
      </li>

 <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        상품관리
      </div>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
<!--            <i class="material-icons" style="font-size:20px">laptop_chromebook</i> -->
          <span>상품관리</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <a class="collapse-item" href="List.abook">상품목록</a>
            <a class="collapse-item" href="WriteForm.abook">상품등록</a>
            <a class="collapse-item" href="ReviewList.abook">상품후기</a>
            <a class="collapse-item" href="QList.abook">상품문의</a>
          </div>
        </div>
      </li>
       <!-- Divider -->
      
       <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        	게시판관리
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
          <i class="fas fa-fw fa-folder"></i>
<!--            <i class="material-icons" style="font-size:20px">dashboard</i> -->
          <span>게시판 관리</span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
          
                      <h6 class="collapse-header">공지사항/이벤트</h6>
            <a class="collapse-item" href='<c:url value="/Notice.adb"/>'>공지사항</a>
            <a class="collapse-item" href='<c:url value="/Event.adb"/>'>이벤트</a>
            <a class="collapse-item" href='<c:url value="/Coupon.adb"/>'>쿠폰관리</a>
          
            <h6 class="collapse-header">FAQ/1:1문의 관리</h6>
            <a class="collapse-item" href='<c:url value="/FAQ.adb"/>'>FAQ 목록</a>
            <a class="collapse-item" href='<c:url value="/QListNA.adb"/>'>1:1문의 목록(미답변)</a>
            <a class="collapse-item" href='<c:url value="/QList.adb"/>'>1:1문의 목록(전체)</a>
          </div>
        </div>
      </li>
 <!-- Divider -->

 	 <hr class="sidebar-divider">
      
      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    <!-- End of Sidebar -->

<!-- Sidebar end=============================================== -->