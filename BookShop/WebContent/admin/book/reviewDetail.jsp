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

  <title>SB Admin 2 - member_list.jsp</title>
  
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

</head>

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
	              <h5 class="m-0 font-weight-bold text-primary"><a href="ReviewList.abook?page=${page }">&lt; 상품 후기 목록</a></h5>
	            </div>
        	</div>
			<div class="row">

            <!-- 고객후기 내용 보기 -->
            <div class="col-lg-6 heightM">

              <div class="card position-relative">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-gray">고객후기 내용</h6>
                </div>
                <div class="card-body">
	              <div class="table-responsive">
	              <form>
	                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                    <tr>
	                      <th class="thWidth">접수번호</th>
	                      <td class="tdWidth">${board_review.boardNum }</td>
	                      <th class="thWidth">작성일</th>
	                      <td class="tdWidth">${board_review.boardRegTime }</td>
	                    </tr>
	                    <tr>
	                      <th>고객ID</th>
	                      <td>${board_review.boardWriter }</td>
	                      <th>처리상태</th>
	                      <td>답변완료</td>
	                    </tr>
	                    <tr>
	                      <th>상품명</th>
	                      <td colspan="3">${board_review.bookTitle }</td>
	                    </tr>
	                    <tr>
	                      <th>후기 제목</th>
	                      <td colspan="3">${board_review.boardTitle }</td>
	                    </tr>
	                    <tr>
	                      <th>후기 내용</th>
	                      <td colspan="3"><textarea rows="10" cols="70" readonly="readonly">${board_review.boardContent }</textarea></td>
	                    </tr>
	                </table>
	              </form>
	              </div>
	            </div>
              </div>

            </div>

            <!-- 판매자 답변 처리 -->
            <div class="col-lg-6 heightM">

              <div class="card position-relative">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">판매자 답변</h6>
                </div>
                <div class="card-body">
	              <div class="table-responsive">
	                <form action="ReviewModifyPro.abook" method="post">
                    <input type="hidden" name="page" value="${page }">
                    <input type="hidden" name="boardReRef" value="${board_answer.boardReRef }">
                    <input type="hidden" name="boardNum" value="${board_answer.boardNum }">
	                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                    <tr>
	                      <th class="thWidth">답변제목</th>
	                      <td colspan="3"><input type="text" name="boardTitle" size="70" value="${board_answer.boardTitle }"></td>
	                    </tr>
	                    <tr>
	                      <th>답변 수정</th>
	                      <td colspan="3"><textarea name="boardContent" rows="15" cols="70" required="required">${board_answer.boardContent }</textarea></td>
	                    </tr>
	                </table>
	                <input type="submit" class="custom_button" value="답변 수정" />
	                <input type="button" class="custom_button" value="답변 삭제" onclick="location.href='ReviewDeleteForm.abook?&boardRe_refList=${board_answer.boardReRef }'"/>
	                </form>
	              </div>
	            </div>
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