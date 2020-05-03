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

  <!-- Custom fonts for this template -->
  <link href="admin/vendor/fontawesome-free/css/all.min.css?ver=1" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href="admin/css/sb-admin-2.css?ver=1" rel="stylesheet">
  <link href="admin/css/sb-admin-2.min.css?ver=1" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="admin/vendor/datatables/dataTables.bootstrap4.min.css?ver=1" rel="stylesheet"> 
</head>
<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
	<jsp:include page="../adminInc/adminMenu.jsp"></jsp:include>
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

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h5 class="m-0 font-weight-bold text-primary"><a href="List.abook?page=${page }">&lt; 상품 목록</a></h5>
            </div>
            <div class="card-body">
              <div class="table-responsive">
                <input type="button" class="custom_button custom_button_margin" value="수정하기" onclick="location.href='ModifyForm.abook?bookID=${book.bookID }&page=${page}'">
                <input type="button" class="custom_button custom_button_margin" value="삭제하기" onclick="location.href='DeleteForm.abook?bookIDList=${book.bookID }&page=${page}'">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <tr>
                      <th class="thWidth">책 카테고리</th>
                      <td>
                      		${book.BK1 } > ${book.BK2 } > ${book.BK3 }
                      </td>
                    </tr>
                    <tr>
                      <th>책 제목</th>
                      <td>${book.bookTitle }</td>
                    </tr>
                    <tr>
                      <th>상품 이미지</th>
                      <td>
                        <img class="detail_img" src="./upload/${book.bookImage }"><br>
                        ${book.bookOriginImage }
                      </td>
                    </tr>
                    <tr>
                      <th>저자 및 출판사</th>
                      <td>${book.bookPublisher }</td>
                    </tr>
                    <tr>
                      <th>출판일</th>
                      <td>${book.bookPublishedDate }</td>
                    </tr>
                    <tr>
                      <th>가격</th>
                      <td>${book.bookPrice }</td>
                    </tr>
                    <tr>
                      <th>상품 재고</th>
                      <td>${book.bookEA }</td>
                    </tr>
                    <tr>
                      <th>판매량</th>
                      <td>${book.salesVolume }</td>
                    </tr>
                    <tr>
                      <th>상품 소개</th>
                      <td>${book.bookIntroduce }</td>
                    </tr>
                    <tr>
                      <th>상품의 공개여부</th>
                      <td>${book.bookisView }</td>
                    </tr>
                    <tr>
                      <th>상품별 포인트 적립률</th>
                      <td>${book.saveRatio }</td>
                    </tr>
                </table>
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
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>

</body>
</html>