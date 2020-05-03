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

  <title>팀프로젝트 - 관리자 Coupon목록</title>
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  


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
	
	$(document).ready(function() {
		$('.title').click(function() {
			$(this).parent().parent().next().toggle();
			});
		
		
// 		$('.btnMod').click(function() {
// 			alert($(this).parent().parent().child(".mod_End_date"));
// 		});
		
		
		});
	
		
		function checkOK(cID) {
			r = confirm("정말로 삭제하시겠습니까?");
			if(r){
				location.href="./CouponDelete.adb?cID="+cID;
			}
		}
		
// 		function modCoupon(listStatus) {
// 			var x = document.getElementsByClassName("mod_End_date");
// 			var mod_End_date = x[0].val();
// // 			var mod_volume = document.getElementsByClassName("mod_volume")[listStatus];
// 			alert(listStatus);
// 		}
		
		$( function() { 
		    $.datepicker.setDefaults({
		        dateFormat: 'yy-mm-dd' //Input Display Format 변경
		        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
		        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
		        ,changeYear: true //콤보박스에서 년 선택 가능
		        ,changeMonth: true //콤보박스에서 월 선택 가능                
		        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시
		        ,buttonImage: "./img/calendar.png" //버튼 이미지 경로
		        ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
		        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
		        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
		        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
		        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
		        ,minDate: "0" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전) 0 으로 설정했을때는 오늘 날짜 이후로 만 선택 가능함.
		        ,maxDate: "+3Y" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
		    });
 
            //input을 datepicker로 선언
            $("#datepicker").datepicker();                    
            $("#datepicker2").datepicker();
            $(".mod_End_date").datepicker();
            
            //From의 초기값을 오늘 날짜로 설정
            $('#datepicker').datepicker('setDate', 'today'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
            //To의 초기값을 내일로 설정
            $('#datepicker2').datepicker('setDate', '+1M'); //(-1D:하루전, -1M:한달전, -1Y:일년전), (+1D:하루후, -1M:한달후, -1Y:일년후)
        });
		
		
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
          <h1 class="h3 mb-2 text-gray-800">쿠폰</h1>
          	<div>
            <form action='<c:url value="/CounponInsert.adb"/>' method="POST">
          	<table class="table table-bordered" id="dataTable" style="width: 50%; margin-left: auto; margin-right: auto;" cellspacing="0">		
              	<tbody>
              	<tr>
              		<th style="width: 10%">쿠폰명</th><td style="width: 10%"><input type="text" name="coupon_name" required="required"></td>
              		<th style="width: 10%">할인액</th><td><input type="text" name="volume" required="required"></td>
				</tr>
				<tr>
					<th style="width: 10%">유효기간</th><td colspan="3"><input type="text" id="datepicker" name="couponReg_date" readonly="readonly">
					 ~ <input type="text" id="datepicker2" name="couponEnd_date" readonly="readonly"></td>
				</tr>
				<tr>
					<td colspan="4"><div style="float: right;"><input type="submit" value="쿠폰 등록" class="custom_button"></div></td>
				</tr>
				</tbody>
			</table>
			</form>
            </div>

          <!-- DataTales Example -->
          <div class="card shadow mb-4" style="width: 80%; margin-left: auto; margin-right: auto;">
            
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataSearchTable" width="100%" cellspacing="0">
                  <tbody>
                  <c:choose>
	                  <c:when test="${pageInfo.listCount > 0}">
		                  <c:forEach var="coupon" items="${couponList }" varStatus="listStatus">
		                    <tr>
		                      <th style="width: 8%">쿠폰명</th>
		                      <th><a href="#" class="title">${coupon.coupon_name }</a></th>
		                      <th style="width: 10%">유효기간</th>
		                      <td>${coupon.couponReg_date } ~ ${coupon.couponEnd_date }</td>
		                      <th style="width: 8%">할인액</th>
		                      <td style="width: 10%; text-align: right;">${coupon.volume } 원</td>
		                    </tr>
		                    <tr class="contents" style="display:none;">
		                    	<th style="width: 20%">쿠폰 수정사항</th>
		                    	<td colspan="5">
								<form action='<c:url value="/CounponModify.adb"/>' method="POST">
		                    	만료일 : <input type="text" class="mod_End_date" name="mod_End_date">&nbsp;&nbsp;
		                    	할인액 : <input type="text" class="mod_volume" name="mod_volume" value="${coupon.volume }">
		                    	<input type="hidden" name="cID" value="${coupon.cID }">
			                    		<a href='#' style="margin-left: 20%;"><input type="submit" value="수정" class="custom_button"></a>
			                    		<a href="#"><input type="button" value="삭제" onclick="checkOK(${coupon.cID})" class="custom_button"></a>
			                    </form>
		                    	</td>
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
                		<a href="Coupon.adb?page=${pageInfo.startPage-pageInfo.pageBlock }">[이전]</a>&nbsp;
                	</c:if>
                	<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                	<c:choose>
                	<c:when test="${pageInfo.page eq i }">${i }&nbsp;</c:when>
                	<c:otherwise><a href="Coupon.adb?page=${i }">${i }</a>&nbsp;</c:otherwise>
                	</c:choose>
                		
                	</c:forEach>
                	<c:if test="${pageInfo.endPage < pageInfo.maxPage }">
                		<a href="Coupon.adb?page=${pageInfo.startPage+pageInfo.pageBlock }">[다음]</a>
                	</c:if>
                	</section>
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
<%--   <script src='<c:url value="/admin/vendor/jquery/jquery.min.js"/>'></script> --%>
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
