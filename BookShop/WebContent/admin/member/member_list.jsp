<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
    pageEncoding="UTF-8"%>
<%
if(session.getAttribute("uID") == null){
	response.sendRedirect("index.jsp");
}
 List<MemberBean> memberList = (List<MemberBean>) request.getAttribute("memberList");
 String gradeName = "선택요망";
 %>


<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - member_list.jsp</title>

  <!-- Custom fonts for this template -->
  <link href="admin/vendor/fontawesome-free/css/all.min.css?ver=1" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="admin/css/sb-admin-2.min.css?ver=1" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="admin/vendor/datatables/dataTables.bootstrap4.min.css?ver=1" rel="stylesheet">
	
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
          <h1 class="h3 mb-2 text-gray-800">회원관리</h1>
          <p class="mb-4"> 회원관리 페이지입니다. 
          <!-- <a target="_blank" href="https://datatables.net">official DataTables documentation</a>. --></p>
          

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h6 class="m-0 font-weight-bold text-primary">회원목록</h6>              
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>아이디</th>
                      <th>이름</th>
                      <th>휴대폰</th>
                      <th>이메일</th>
                      <th>포인트</th>
                      <th>등급</th>
                    </tr>
                  </thead>
                  <tbody>
						<%for(int i = 0; i < memberList.size(); i++){
							
						switch(memberList.get(i).getGrade()){
						case 0 :
							gradeName = "선택해주세요";
							break;
						case 1 :
							gradeName = "ADMIN";
							break;
						case 2 :
							gradeName = "ADMIN1";
							break;
						case 3 :
							gradeName = "ADMIN2";
							break;
						case 4 :
							gradeName = "ADMIN3";
							break;
						case 5 :
							gradeName = "ADMIN4";
							break;
						case 6 :
							gradeName = "BRONZE";
							break;
						case 7 :
							gradeName = "SILVER";
							break;
						case 8 :
							gradeName = "GOLD";
							break;
						case 9 :
							gradeName = "VIP";
							break;
						case 10 :
							gradeName = "VVIP";
							break;
						}
						
						//MemberBean mb = (MemberBean)memberList.get(i); //e다운캐스팅 %>
<%--                     <%if(memberList != null & memListCount > 0) {%> --%>
<%--                     	<% for(int i = 0; i < memberList.size(); i++) {%> --%>
<!--uID서버저장공간리퀘스트 화면딴에서 클릭하면 서버로가는데 서버에서 클릭한 정보들고가려면 서버에전달이되고 저장시켜야하는데 그게  -->
                    <tr onclick="location.href='MemberDetail.adm?uID=<%=memberList.get(i).getuID()%>'">
<%--     <a href="BoardDetail.bo?board_num=<%=articleList.get(i).getBoard_num()%>&page=<%=nowPage%>">
 --%>                   
                      <td><%=memberList.get(i).getuID() %></td>
                      <td><%=memberList.get(i).getU_name() %></td>
                      <td><%=memberList.get(i).getPhone_num() %></td>
                      <td><%=memberList.get(i).getEmail() %></td>
                      <td><%=memberList.get(i).getPoint() %></td>
                      <td><%=gradeName%> </td>
                    </tr>
                   		<%}%>
<%--                    <% }%> --%>
                   
                   </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->
        
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