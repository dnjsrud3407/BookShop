<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>1:1문의 내용 보기</title>
  
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- Custom fonts for this template -->
  <link href="admin/vendor/fontawesome-free/css/all.min.css?ver=1" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="admin/css/sb-admin-2.css?ver=1" rel="stylesheet">
  <link href="admin/css/sb-admin-2.min.css?ver=1" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="admin/vendor/datatables/dataTables.bootstrap4.min.css?ver=1" rel="stylesheet">
<!-- <script src="admin/js/jquery-3.4.1.js"></script> -->
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script> -->

<style type="text/css">
.heightM {
	margin-top: 3%;
}
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


<script type="text/javascript">
	
	function checkOK(boardNum,boardReRef) {
		r = confirm("정말로 삭제하시겠습니까?");
		if(r){
			location.href="./QDelete.adb?boardNum="+boardNum+"&boardReRef="+boardReRef;
		}
	}
		
	</script>


</head>
<body id="page-top">
<!-- 관리자 id 아닌 경우 로그인창으로 이동 -->
<c:if test="${sessionScope.uID ne 'admin' and sessionScope.uID ne 'admin1' and sessionScope.uID ne 'admin2' and sessionScope.uID ne 'admin3' and sessionScope.uID ne 'admin4'}">
	<c:redirect url="Main.me"/>
</c:if>

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
        <jsp:include page="../adminInc/topbar.jsp"></jsp:include>
        <!-- End of Topbar -->

		
        <!-- Begin Page Content -->
        <div class="container-fluid">
        	<div class="card shadow mb-4">
	            <div class="card-header py-3">
	              <h5 class="m-0 font-weight-bold text-primary"><a href="QList.abook">&lt; 1:1 문의 목록</a></h5>
	            </div>
        	</div>
			<div class="row">

            <!-- 고객문의 내용 보기 -->
            <div class="col-lg-6 heightM">

              <div class="card position-relative">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-gray">고객문의 내용</h6>
                </div>
                <div class="card-body">
	              <div class="table-responsive">
	                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                    <tr>
	                      <th style="width:60%">접수번호</th>
	                      <td>${question.boardNum }</td>
	                      <th style="width:60%">접수일</th>
	                      <td>${question.boardRegTime }</td>
	                    </tr>
	                    <tr>
	                      <th>고객ID</th>
	                      <td>${question.boardWriter }</td>
	                      <th style="width:60%">처리상태</th>
	                      <c:if test="${question.boardReLev eq 1 }"><td>답변완료</td></c:if>
	                      <c:if test="${question.boardReLev eq 0 }"><td>답변대기</td></c:if>
	                      
	                    </tr>
	                    <tr>
	                      <th>카테고리</th>
	                      <td colspan="3">${question.k2 }</td>
	                    </tr>
	                    <tr>
	                      <th>문의 제목</th>
	                      <td colspan="3">${question.boardTitle }</td>
	                    </tr>
	                    <tr>
	                      <th>문의 내용</th>
	                      <td colspan="3"><textarea rows="20" cols="40" readonly="readonly">${question.boardContent }</textarea></td>
	                    </tr>
	                </table>
	              </div>
	            </div>
              </div>

            </div>

            <!-- 판매자 답변 처리 -->
            <!-- 답변이 있을 떄! -->
            <c:if test="${question.boardReLev > 0 }">
            <div class="col-lg-6 heightM">

              <div class="card position-relative">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">판매자 답변</h6>
                </div>
                <div class="card-body">
	              <div class="table-responsive">
	                <form action="QModify.adb" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="boardReRef" value="${answer.boardReRef }">
                    <input type="hidden" name="boardNum" value="${answer.boardNum }">
                    <input type="hidden" name="k2" value="${answer.k2 }">
	                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                    <tr>
	                      <th style="width:60%">답변제목</th>
	                      <td colspan="3"><input type="text" name="boardTitle" size="60" value="${answer.boardTitle }"></td>
	                    </tr>
	                    <tr>
	                      <th style="width:60%">답변 수정</th>
	                      <td colspan="3"><textarea name="boardContent" rows="23" cols="60" required="required">${answer.boardContent }</textarea></td>
	                    </tr>
	                </table>
	                <input type="submit" class="custom_button" value="답변 수정" />
	                <input type="button" class="custom_button" value="답변 삭제" onclick="checkOK(${answer.boardNum}, ${question.boardReRef})"  />
	                </form>
	              </div>
	            </div>
              </div>

            </div>
            </c:if>
             <!-- 답변이 없을 떄! -->
            <c:if test="${question.boardReLev eq 0 }">
            <div class="col-lg-6 heightM">

              <div class="card position-relative">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">판매자 답변</h6>
                </div>
                <div class="card-body">
	              <div class="table-responsive">
	                <form action="QWrite.adb" method="post" enctype="multipart/form-data">
	                <input type="hidden" name="boardWriter" value="${sessionScope.uID }">
                    <input type="hidden" name="boardReRef" value="${question.boardReRef }">
	                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                	<tr>
	                	  <th>카테고리</th>
	                	  <td>
	                	  	<input type="hidden" name="k2" value="${question.k2  }">
	                	  	${question.k2 }
	                	  <td>
	                	</tr>
	                    <tr>
	                      <th style="width:60%">답변제목</th>
	                      <td colspan="3"><input type="text" name="boardTitle" size="40"></td>
	                    </tr>
	                    <tr>
	                      <th style="width:60%">답변 작성</th>
	                      <td colspan="3"><textarea name="boardContent" rows="23" cols="60" required="required"></textarea></td>
	                    </tr>
	                </table>
	                <input type="submit" class="custom_button" value="답변 작성" />
	                </form>
	              </div>
	            </div>
              </div>

            </div>
            </c:if>
		<!-- 판매자 답변란 끝 -->
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
          <a class="btn btn-primary" href="LogoutPro.me">Logout</a>
        </div>
      </div>
    </div>
  </div>

  <!-- Bootstrap core JavaScript-->
<!--   <script src="vendor/jquery/jquery.min.js"></script> -->
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/datatables-demo.js"></script>


</body>
</html>