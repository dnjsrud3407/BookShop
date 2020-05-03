<%@page import="vo.MemberBean"%>
<%@page import="dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%

String user = null;
if(session.getAttribute("uID") == null){
// 	out.println("<script>");
//     out.println("location.href='Login.me'");
//     out.println("</script>");
	response.sendRedirect("index.jsp");
// } else { // 로그인 된 상태일 경우 세션 ID 가져오기
// 	user = (String)session.getAttribute("uID");
}


MemberBean member = (MemberBean)request.getAttribute("member");

%>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - member_detail.jsp</title>

<!--   <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen">  -->
  <!-- Custom fonts for this template -->
  <link href="admin/vendor/fontawesome-free/css/all.min.css?ver=1" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="admin/css/sb-admin-2.min.css?ver=1" rel="stylesheet">
  <link href="admin/css/sb-admin-2.css?ver=1" rel="stylesheet" type="text/css">

  <!-- Custom styles for this page -->
  <link href="admin/vendor/datatables/dataTables.bootstrap4.min.css?ver=1" rel="stylesheet">


<!-- 회원수정관련 --> 

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
.control-label {
    width: 150px;
    text-align: right;
    }
input.size {
	width: 350px;
}
</style>
<script type="text/javascript">	
	function checkPasswd(passwd) {
		// 8 ~ 16자리 패스워드 영문,숫자,특수문자 조합 유효성 검사
		// 1. 정규표현식 지정
		// 1) 길이 체크 : 8 ~ 16자리. 영문 대문자&소문자&숫자&특수문자(!@#$%^_)
		var lengthRegex = /^[A-Za-z0-9!@#$%^_]{8,16}$/;
		// 2) 대문자 체크
		var upperCaseRegex = /[A-Z]/;
		// 3) 소문자 체크
		var lowerCaseRegex = /[a-z]/;
		// 4) 숫자 체크
		var digitRegex = /[0-9]/;
		// 5) 특수문자 체크
		var specCharRegex = /[!@#$%^_]/;
		
// 		// 2. 체크 후 메세지 표시할 공간의 태그 id 값 가져오기
		var element = document.getElementById('checkPasswdResult'); // checkPasswdResult 값을 ID 로 갖는 태그 찾기
		
// 		// 3. 정규표현식을 통한 유효성 검사 수행(정규표현식 저장 변수명.exec() 를 사용)
// 		// 함수 호출 시 전달받은 파라미터(id) 의 값을 정규표현식으로 검사
		// 길이, 대문자, 소문자, 숫자, 특수문자 체크를 모두 통과했을 경우
		if(lengthRegex.exec(passwd.value) && upperCaseRegex.exec(passwd.value) &&
				lowerCaseRegex.exec(passwd.value) && digitRegex.exec(passwd.value) &&
					specCharRegex.exec(passwd.value)) {
// 			alert('유효성 검사 통과');	
			// 지정된 태그 내에 메세지 표시
			element.innerHTML = "적합한 패스워드";
		} else { // 유효성 검사를 통과하지 못했을 경우
// 			alert('유효성 검사 탈락');
			element.innerHTML = "적합하지 않은 패스워드";
		}
	}
	function removeCheck() {
		if(confirm("정말 삭제하시겠습니까 ??") == true) {
			document.removefrm.remove();
		}else{
			return false;
		}
				
	}
</script>	
</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
	<jsp:include page="../adminInc/adminMenu.jsp" />
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
<!-- top 로그인 관리 부분---------------------------------------------------------------------------------------------------------------------- -->
				<jsp:include page="../adminInc/topbar.jsp" />
<!-- ---------------------------------------------------------------------------------------------------------------------------------------- -->
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">회원상세보기</h1>
          <p class="mb-4">회원 상세정보입니다.</p>

          <!-- DataTales Example -->
                     <!-- DataTales Example -->
     
			  <div class="card shadow mb-4">
			<div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">회원상세정보</h6>              
            </div>
            <div class="card-body">
              <div class="table-responsive">

<!-- 			<div class="span9"> -->
			
<!-- 	<div class="well"> -->
	<!--
	<div class="alert alert-info fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	<div class="alert fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>
	 <div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div> -->
	<form action="MemberModifyPro.adm" method="post" name="" id="searchForm">
			<h4>회원 개인정보</h4>
	<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	 
	 <tr>
                 	<th style="width: 15%;">이름 *</th>
                 	<td>
				  <span class="checkbox_padding" style="width: 15%;">
                 			<%=member.getU_name() %>
             			</span>
                 	</td>
                 	
                 	<th style="width: 15%;">가입날짜</th>
                 	<td>
                 	    <span class="checkbox_padding" style="width: 15%;">
                 			<%=member.getJoinDate() %>
             			</span>
                 	</td>
                 </tr>
                 
                 
                 <tr>
                 	<th style="width: 15%;">아이디 *</th>
                 	<td>
				  <span class="checkbox_padding" style="width: 15%;">
                 			<input type="hidden" name="uID" value="<%=member.getuID() %>">
                 			<%=member.getuID() %>
             			</span>
                 	</td>
                 	
                 	<th style="width: 15%;">비밀번호 *</th>
                 	<td>
                 	    <span class="checkbox_padding" style="width: 15%;">
                 			***********
             			</span>
                 	</td>
                 </tr>
                 
                 
                 <tr>
                 	<th style="width: 15%;">주소 *</th>
                 	<td colspan="3">
                 	    <span class="checkbox_padding" style="width: 15%;">
						<%=member.getAddress() %>
             			</span>
                 	
				</td>
                 </tr>
                 
                 <tr>
                 	<th style="width: 15%;">상세주소</th>
                 	<td colspan="3">
                 	    <span class="checkbox_padding" style="width: 15%;">
						<%=member.getAddress2() %>
             			</span>
                 	
				</td>
                 </tr>
                 
                 <tr>
                 	<th style="width: 15%;">모바일 *</th>
                 	<td>
                 		<span class="checkbox_padding">
                 			<%=member.getPhone_num() %>
                 		</span>
                 	</td>
                 	<th style="width: 15%;">전화번호</th>
                 	<td>
                 		<span class="checkbox_padding">
                 			<%=member.getTell_num() %>
                 		</span>
                 	</td>
                 </tr>
                 
                 <tr>
                 	<th style="width: 15%;">이메일</th>
                 	<td colspan="3">
                 	    <span class="checkbox_padding" style="width: 15%;">
						<%=member.getEmail() %>
             			</span>
                 	
				</td>
                 </tr>
                  
	 </table>
		<br>
	<p class="control-group"><sup>*</sup>필수 입력사항</p>

	<div class="alert alert-block alert-error fade in">
		<button type="button" class="close" data-dismiss="alert">×</button>
		<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
	 </div>	

		<h4>포인트와 등급</h4>
		<div class="control-group">

		<div class="">
		
		<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		 <tr>
	       	<th style="width: 15%;">포인트</th>
	           	<td>
				<span class="checkbox_padding" style="width: 15%;">
			  <input type="text" id="point" width="129" name="point" value="<%=member.getPoint() %>"/> Points
	   			</span>
	       	</td>
	       	
	       	<th style="width: 15%;">등급</th>
	       	<td>
	       	    <span class="checkbox_padding" style="width: 15%;">
<%-- 			  <input type="text" id="grade" name="grade" value="<%=member.getGrade() %>"/>  --%>
			  <select name="grade" class="size" id="grade">
				<option value="0" class="size" <c:if test="${member.grade == 1 }">selected</c:if>>선택해주세요.</option>
				<option value="">--관리자용--</option>
								
				<option value="1" <c:if test="${member.grade == 1 }">selected</c:if>>ADMIN</option>
				<option value="2" <c:if test="${member.grade == 2 }">selected</c:if>>ADMIN1</option>
				<option value="3" <c:if test="${member.grade == 3 }">selected</c:if>>ADMIN2</option>
				<option value="4" <c:if test="${member.grade == 4 }">selected</c:if>>ADMIN3</option>
				<option value="5" <c:if test="${member.grade == 5 }">selected</c:if>>ADMIN4</option>
				<option value="">--회원용--</option>
				<option value="6" <c:if test="${member.grade == 8 }">selected</c:if>>BRONZE</option>
				<option value="7" <c:if test="${member.grade == 7 }">selected</c:if>>SILVER</option>
				<option value="8" <c:if test="${member.grade == 8 }">selected</c:if>>GOLD</option>
				<option value="9" <c:if test="${member.grade == 9 }">selected</c:if>>VIP</option>
				<option value="10" <c:if test="${member.grade == 10 }">selected</c:if>>VVIP</option>
			
			</select>	   			
			</span>
	       	</td>
	       </tr>
	     </table>
		

<!-- 		<div class="control-group"> -->
<!-- 			<div class=""> -->
<!-- 			<label class="control-label" for="point">포인트 &nbsp;&nbsp;</label> -->
<%-- 			  <input type="text" id="" width="129" name="point" value="<%=member.getPoint() %>"/>Points --%>
<!-- 			</div> -->
<!-- 		</div> -->
		
<!-- 		<div class="control-group"> -->
<!-- 			<div class=""> -->
<!-- 			<label class="control-label" for="grade">등급 &nbsp;&nbsp;</label> -->
<%-- 			  <input type="text" id="grade" name="grade" value="<%=member.getGrade() %>"/>  --%> 
<!-- 			  <select name="grade" classs="size" id="grade"> -->
<!-- 				<option value="" class="size">선택해주세요.</option>				 -->
<%-- 				<option value="1" <c:if test="${member.grade == 1 }">selected</c:if>>admin</option> --%>
<%-- 				<option value="2" <c:if test="${member.grade == 2 }">selected</c:if>>admin1</option> --%>
<%-- 				<option value="3" <c:if test="${member.grade == 3 }">selected</c:if>>admin2</option> --%>
<%-- 				<option value="4" <c:if test="${member.grade == 4 }">selected</c:if>>admin3</option> --%>
<%-- 				<option value="5" <c:if test="${member.grade == 5 }">selected</c:if>>admin4</option> --%>
<%-- 				<option value="6" <c:if test="${member.grade == 6 }">selected</c:if>>member</option> --%>
<%-- 				<option value="7" <c:if test="${member.grade == 7 }">selected</c:if>>BRONZE</option> --%>
<%-- 				<option value="8" <c:if test="${member.grade == 8 }">selected</c:if>>SILVER</option> --%>
<%-- 				<option value="9" <c:if test="${member.grade == 9 }">selected</c:if>>GOLD</option> --%>
<%-- 				<option value="10" <c:if test="${member.grade == 10 }">selected</c:if>>VIP</option> --%>
<%-- 				<option value="11" <c:if test="${member.grade == 11 }">selected</c:if>>VVIP</option> --%>
			
<!-- 			</select> -->
<!-- 			</div> -->
<!-- 		</div> -->
		
		</div>
		</div>	
<br>
	
		<div class="control-group">
			<div class="">
				<input type="hidden" name="email_create" value="1">
				<input type="hidden" name="is_new_customer" value="1">

<!--         <a class="" href="#" data-toggle="modal" data-target="#logoutModal"> -->
<!--           <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i> -->
<!--           Logout -->
<!--         </a> -->

			<input  class="custom_button" type="submit" value="멤버수정">
			<input  class="custom_button" type="button" value="멤버삭제" name="removeCheck" data-toggle="modal" data-target="#deleteModal">
			</div>
		</div>	
			
	</form>
<!-- </div> -->
<!-- </div> -->
</div>
</div>
</div>
</div>
</div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <jsp:include page="../adminInc/adminBottom.jsp" />

      <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->

  </div>
  <!-- End of Page Wrapper -->

  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>

  <!-- Logout Modal-->
  <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
          <a class="btn btn-primary" href="login.html">Logout</a>
        </div>
      </div>
    </div>
  </div>
  
    <!-- delete Modal-->
  <div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">정말 삭제하시겠습니까 ? </h5>
          <button class="close" type="button" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">×</span>
          </button>
        </div>
        <div class="modal-body">정말 삭제를 원하신다면 아래의 삭제를 선택하세요</div>
        <div class="modal-footer">
          <button class="btn btn-secondary" type="button" data-dismiss="modal">취소</button>
          <a class="btn btn-primary" href="MemberDeletePro.adm?uID=<%=member.getuID() %>">삭제</a>
        </div>
      </div>
    </div>
  </div>
  

  <!-- Bootstrap core JavaScript-->
  <script src="admin/vendor/jquery/jquery.min.js?ver=1"></script>
  <script src="admin/vendor/bootstrap/js/bootstrap.bundle.min.js?ver=1"></script>

  <!-- Core plugin JavaScript-->
  <script src="admin/vendor/jquery-easing/jquery.easing.min.js?ver=1"></script>

  <!-- Custom scripts for all pages-->
  <script src="admin/js/sb-admin-2.min.js?ver=1"></script>

  <!-- Page level plugins -->
  <script src="admin/vendor/datatables/jquery.dataTables.min.js?ver=1"></script>
  <script src="admin/vendor/datatables/dataTables.bootstrap4.min.js?ver=1"></script>

  <!-- Page level custom scripts -->
  <script src="admin/js/demo/datatables-demo.js?ver=1"></script>

</body>

</html>
    