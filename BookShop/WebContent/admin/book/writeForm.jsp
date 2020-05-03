<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

<!-- 책 카테고리 시 사용하는 js -->
<!-- <script src="admin/js/jquery-3.4.1.js"></script> -->
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script> -->
<script> 
	// 책 카테고리 선택 수정
	$(document).ready(function() {
	    
		// ================== 대분류 카테고리 지정
		$.ajax({
			type:"POST",
			url:"BK1.abook",
			success: function(msg1){	// 대분류 innerHTML
				$("select[name='BK1Category']").html(msg1);
			}
		});
		
		// ================== 대분류 카테고리 바꼈을 때 소분류 변경함수
		$("#BK1Category").on("change", function () {
			// 대분류 값 가져오기
			var BK1 = $("#BK1Category option:selected").val();
			// 소분류 데이터 가져오기
			$.ajax({
				type:"POST",
				url:"BK2.abook",
				data:"BK1="+BK1,
				success: function(msg2){	// 소분류 innerHTML
					$("select[name='BK2Category']").html(msg2);
				}
			});

			// 레벨 셀렉트 박스 지우기
			$("select[name='BK3Category']").html("<option value='선택하세요'>선택하세요</option>");
		});
		
		
		// ================== 소분류 카테고리 바꼈을 때 레벨 변경함수
		$("#BK2Category").on("change", function () {
	 		// 대분류, 소분류 값 가져오기
			var BK1 = $("#BK1Category option:selected").val();
			var BK2 = $("#BK2Category option:selected").val();
	 		// 소분류 데이터 가져오기
			$.ajax({
				type:"POST",
				url:"BK3.abook",
				data:"BK1="+BK1+"&BK2="+BK2,
				success: function (msg3) {	// 레벨 innerHTML
					$("select[name='BK3Category']").html(msg3);
				}
			});
		});
	});
	
	
	
  // 달력 api	
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
          ,minDate: "-40Y" //최소 선택일자(-1D:하루전, -1M:한달전, -1Y:일년전) 0 으로 설정했을때는 오늘 날짜 이후로 만 선택 가능함.
          ,maxDate: "0" //최대 선택일자(+1D:하루후, -1M:한달후, -1Y:일년후)
      });
      $("#datepicker").datepicker(); 
  } ); 
  
  // submit 전 카테고리 체크
  function KateCheck() {
	var BK1 = $("#BK1Category option:selected").val();
	var BK2 = $("#BK2Category option:selected").val();
	var BK3 = $("#BK3Category option:selected").val();
	// 카테고리 지정안 할 시 오류 띄우기
	if(BK1 == '선택하세요' || BK2 == '선택하세요' || BK3 == '선택하세요') {
		alert('카테고리를 지정하세요');
		return false;
	}
  }
  </script>
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
		<div class="container-fluid">

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h5 class="m-0 font-weight-bold text-primary">상품등록</h5>
            </div>
            <div class="card-body">
              <div class="table-responsive">
              <form action="WritePro.abook" method="post" enctype="multipart/form-data" onsubmit="return KateCheck()">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <tr>
                      <th class="thWidth">* 책 카테고리</th>
                      <td>
	                      <div class="searchWidth">
							대분류 : <select name="BK1Category" id="BK1Category" class="inputWidth">
							     		<option value="선택하세요">선택하세요</option>
							  		</select>
						  </div>
						  <div class="searchWidth">
							단계 : <select name="BK2Category" id="BK2Category" class="inputWidth">
							      		<option value="선택하세요">선택하세요</option>
							  	   </select>
						  </div>
						  <div class="searchWidth">
							소분류 : <select name="BK3Category" id="BK3Category" class="inputWidth">
							     		<option value="선택하세요">선택하세요</option>
							  	  </select>
						  </div>
                      </td>
                    </tr>
                    <tr>
                      <th>* 책 제목</th>
                      <td><input type="text" name="bookTitle" required="required" size="120"></td>
                    </tr>
                    <tr>
                      <th>상품 이미지</th>
                      <td><input type="file" name="bookImage"></td>
                    </tr>
                    <tr>
                      <th>출판사</th>
                      <td><input type="text" name="bookPublisher" size="120"></td>
                    </tr>
                    <tr>
                    <!-- 달력창 띄우기 -->
                      <th>출판일</th>
                      <td><input type="text" id="datepicker" name="bookPublishedDate" placeholder="" readonly="readonly" size="100"></td>
                    </tr>
                    <tr>
                      <th>* 가격</th>
                      <td><input type="text" name="bookPrice" required="required" size="120"></td>
                    </tr>
                    <tr>
                      <th>* 상품 재고</th>
                      <td><input type="text" name="bookEA" required="required" size="120"></td>
                    </tr>
                    <tr>
                      <th>* 상품 소개</th>
                      <td><textarea rows="10" cols="120" name="bookIntroduce" required="required"></textarea></td>
                    </tr>
                    <tr>
                      <th>* 상품의 공개여부</th>
                      <td>
                        <select name="bookisView">
                            <option value="true">공개</option>
                            <option value="false">비공개</option>
                        </select>
                      </td>
                    </tr>
                    <tr>
                      <th>상품별 포인트 적립률</th>
                      <td><input type="text" name="saveRatio" size="120"></td>
                    </tr>
                </table>
                <input type="submit" class="custom_button" value="작성하기">
                </form>
              </div>
            </div>
          </div>

        </div>
        <!-- Begin Page Content -->
        
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
  <script src="vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/chart-area-demo.js"></script>
  <script src="js/demo/chart-pie-demo.js"></script>

</body>
</html>