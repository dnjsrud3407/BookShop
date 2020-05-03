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

  <title>SB Admin 2 - order_delivery_list.jsp</title>

  <!-- Custom fonts for this template -->
  <link href='<c:url value="/admin/vendor/fontawesome-free/css/all.min.css"/>' rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template -->
  <link href='<c:url value="/admin/css/sb-admin-2.min.css"/>' rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href='<c:url value="/admin/vendor/datatables/dataTables.bootstrap4.min.css"/>' rel="stylesheet">
    <link href="admin/css/sb-admin-2.css?ver=1" rel="stylesheet" type="text/css">
  
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
	
	<script type="text/javascript">
	
	function checkOK(boardNum) {
		r = confirm("정말로 삭제하시겠습니까?");
		if(r){
			location.href="./EventDelete.adb?boardNum="+boardNum;
		}
	}
		
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
                <div class="card shadow mb-4">
		            <div class="card-header py-3">
		              <h5 class="m-0 font-weight-bold text-primary"><a href='<c:url value="/Event.adb"/>'>&lt; 이벤트 목록</a></h5>
		            </div>
        		</div>
			<div class="row">

           <!-- 이벤트 보기 -->

			<div style="margin-left: auto; margin-right: auto; width: 60%;">
              <div class="card position-relative">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">이벤트 보기</h6>
                </div>
                <div class="card-body">
	              <div class="table-responsive">
		                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                    <tr>
		                      <th>제목</th>
		                      <td colspan="3">${article.boardTitle }</td>
		                    </tr>
		                    <tr>
		                    	<th>쿠폰</th>
		                    	<td>쿠폰 ID : ${coupon.cID } / 쿠폰명 : ${coupon.coupon_name } / 적용일 : ${coupon.couponReg_date } / 만료일 : ${coupon.couponEnd_date } / 할인액 : ${coupon.volume }</td>
		                    </tr>
		                    <tr>
		                      <th style="width:15%">내용</th>
		                      <td colspan="3">
		                      
		                      <c:if test="${article.fileList.size() > 0 }">
		                      	<c:forEach var="image" items="${article.fileList}" varStatus="index">
		                      		<img src='<c:url value="/boardFile/${image.storedFileName }"/>' height="300px"/><br>
		                      	</c:forEach>
		                      </c:if>
		                      ${article.boardContent }
		                      </td>
		                    </tr>
		                </table>
		                <div style="text-align: right;">
		                <a href='<c:url value="/EventModify.adb?boardNum=${article.boardNum }"/>'><input type="button" class="custom_button" value="이벤트  수정"></a>
		                <a href="#"><input type="button" value="삭제" onclick="checkOK(${article.boardNum})" class="custom_button"></a>
		                </div>
	                
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
