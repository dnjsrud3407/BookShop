<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">

<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>팀프로젝트 - 관리자 QListNA목록</title>

  <!-- Custom fonts for this template -->
  <link href='<c:url value="/admin/vendor/fontawesome-free/css/all.min.css"/>' rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href='<c:url value="/admin/css/sb-admin-2.min.css"/>' rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href='<c:url value="/admin/vendor/datatables/dataTables.bootstrap4.min.css"/>' rel="stylesheet">
  
  <style type="text/css">
	#pageList {
		margin: auto;
		width: 500px;
		text-align: center;
		font-size: 1.2em;
		}
	.red {
		color: #ff0000;
		}
	.checkbox_padding {
		margin-right: 2.5%;
		width:200px
		}
	</style>
	
	
	<script src="http://code.jquery.com/jquery-1.12.0.min.js"></script>
		
	<script>
		
	</script>
	
	
</head>

<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    
    <!-- Sidebar -->
	<jsp:include page="../adminInc/adminMenu.jsp"/>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <jsp:include page="../adminInc/topbar.jsp"/>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">QListNA</h1>
          	<div>
          	<table class="table table-bordered" id="dataTable" width="50%" cellspacing="0">		
              	<tbody>
              	<tr>
              		<td><a href='<c:url value="/QListNA.adb"/>'>전체</a></td>
	              	<c:forEach var="k2" items="${k2List }" varStatus="k2Status">
	              	
		              	<td>
		              		<a href='<c:url value="/QListNA.adb?k2=${k2}"/>'>${k2}</a>
		              	</td>
					</c:forEach>

				</tr>
				</tbody>
			</table>
            </div>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataSearchTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th style= "width: 150px;">문의번호</th>
                      <th style="width: 150px;">답변 여부</th>
                      <th>제목</th>
                      <th>문의날짜</th>
                    </tr>
                  </thead>
                  
                  <tbody>
                  <c:choose>
	                  <c:when test="${pageInfo.listCount > 0}">
		                  <c:forEach var="articleList" items="${articleList }" varStatus="listStatus">
		                    <tr>
		                      <th>${articleList.boardNum }</th>
		                      <c:choose>
		                      <c:when test="${articleList.boardReSeq eq 0 }"><th>답변 대기</th></c:when>
		                      <c:when test="${articleList.boardReSeq > 0 }"><th>답변 완료</th></c:when>
		                      </c:choose>
		                      <th><a href="QDetail.adb?boardNum=${articleList.boardNum }" class="title">${articleList.boardTitle }</a></th>
		                      <th>${articleList.boardRegTime}</th>
		                    </tr>
		                  </c:forEach>
	                  </c:when>
                  </c:choose>
                  	
                  </tbody>
                </table>
              </div>
              	<div class="card-header py-3">
              		<section id="pageList">
                	<c:if test="${pageInfo.startPage > pageInfo.pageBlock }">
                		<a href="QListNA.adb?page=${pageInfo.startPage-pageInfo.pageBlock }">[이전]</a>&nbsp;
                	</c:if>
                	<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                	<c:choose>
                	<c:when test="${pageInfo.page eq i }">${i }&nbsp;</c:when>
                	<c:when test="${pageInfo.k2 eq null }"> <a href="QListNA.adb?page=${i }">${i }</a>&nbsp; </c:when>
                	<c:when test="${pageInfo.k2 != null }"> <a href="QListNA.adb?page=${i }&k2=${pageInfo.k2}">${i }</a>&nbsp; </c:when>
                	<c:otherwise><a href="QListNA.adb?page=${i }">${i }</a>&nbsp;</c:otherwise>
                	</c:choose>
                		
                	</c:forEach>
                	<c:if test="${pageInfo.endPage < pageInfo.maxPage }">
                		<a href="QListNA.adb?page=${pageInfo.startPage+pageInfo.pageBlock }">[다음]</a>
                	</c:if>
                	</section>
<!--                 	<div style="text-align:right"> -->
<%-- 	            	<a href='<c:url value="/QListNAWrite.adb?"/>'> <input type="button" value="1:1 문의 작성하기"></a> --%>
<!-- 	            	</div> -->
	            </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2019</span>
          </div>
        </div>
      </footer>
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

  <!-- Bootstrap core JavaScript-->
  <script src='<c:url value="/admin/vendor/jquery/jquery.min.js"/>'></script>
  <script src='<c:url value="/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"/>'></script>

  <!-- Core plugin JavaScript-->
  <script src='<c:url value="/admin/vendor/jquery-easing/jquery.easing.min.js"/>'></script>

  <!-- Custom scripts for all pages-->
  <script src='<c:url value="/admin/js/sb-admin-2.min.js"/>'></script>

  <!-- Page level plugins -->
  <script src='<c:url value="/admin/vendor/datatables/jquery.dataTables.min.js"/>'></script>
  <script src='<c:url value="/admin/vendor/datatables/dataTables.bootstrap4.min.js"/>'></script>

  <!-- Page level custom scripts -->
  <script src='<c:url value="/admin/js/demo/datatables-demo.js"/>'></script>

</body>

</html>
