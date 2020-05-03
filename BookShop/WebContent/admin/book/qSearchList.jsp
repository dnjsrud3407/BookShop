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
<script type="text/javascript">
	//달력 api	
	$(function() { 
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
	    $("#datepicker_Before").datepicker(); 
	    $("#datepicker_After").datepicker(); 
	    
	});
	
	
	// 날짜 구하기
	function getFormatDate(date, type){
	    var year = date.getFullYear();   //yyyy
	    var month;
	    var day;
	    if(type == "Today") {
	    	month = date.getMonth()+1;
	    	day = date.getDate(); 
	    } else if(type == "-7Day") {
	    	month = date.getMonth()+1;
	    	day = date.getDate()-7; 
	    	if(day <= 0) {	// 현재가 2,4,6,8,9,11,1 월이라면
	    		if(month == 2 || month == 4 || month == 6 || month == 8 || month == 9 || month == 11 || month == 1) {
		    		month = date.getMonth();
		    		day = 31 + day;	// 음수값 만큼 31에서 뺌(1,3,5,7,8,10,12월은 31일 까지)
	    		} else if(month == 3) {	// 현재가 3 월이라면
	    			month = date.getMonth();
	    			// 윤달이라면
	    			if(year % 4 == 0) {
	    				day = 29 + day;
	    			} else {
		    			day = 28 + day;
	    			}
	    		} else {	// 그 밖의 달
	    			month = date.getMonth();
	    			day = 30 + day;
	    		}
	    	}
	    } else if(type == "-1Month") {
	    	month = date.getMonth();
	    	day = date.getDate(); 
	    } else if(type == "-3Month") {
	    	month = date.getMonth()-2;
	    	day = date.getDate(); 
	    }
	    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
	    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
	    return  year + '-' + month + '-' + day;
	}
	
	// 날짜 바꾸기(type = 'Today', '-7Day', '-1Month', '-3Month')
	function changeDate(type) {
		var dateBefore = new Date();
	    var dateAfter = new Date();
	    
		dateBefore = getFormatDate(dateBefore, type);
		$("#datepicker_Before").val(dateBefore);
		
		// 현재 날짜도 다시
		dateAfter = getFormatDate(dateAfter, "Today");
		$("#datepicker_After").val(dateAfter);
	}
	
	// 체크박스 전체선택/전체해제
	function checkAll(checkBox) {
		if(checkBox.checked == true) {
			$(".checklist").prop("checked", true);
		} else {
			$(".checklist").prop("checked", false);
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
        <jsp:include page="../adminInc/topbar.jsp"></jsp:include>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

		  <!-- 상세 설정 -->
          <div class="card shadow mb-4">
          	<div class="card-header py-3">
              <h5 class="m-0 font-weight-bold text-primary">상품 문의</h5>
            </div>
            <div class="card-body">
              <div class="table-responsive">
              <form action="QSearchPro.abook" method="post">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <tr>
                  	<th class="thWidth">문의 접수일</th>
                  	<td>
                  		<input type="button" class="custom_dateBtn" value="오늘" onclick="changeDate('Today')">
                  		<input type="button" class="custom_dateBtn" value="1주일전" onclick="changeDate('-7Day')">
                  		<input type="button" class="custom_dateBtn" value="1개월전" onclick="changeDate('-1Month')">
                  		<input type="button" class="custom_dateBtn" value="3개월전" onclick="changeDate('-3Month')"> | 
                  		<input type="text" id="datepicker_Before" name="boardRegTime_Before" value="${boardRegTime_Before }" readonly="readonly">
                  		<input type="text" id="datepicker_After" name="boardRegTime_After" value="${boardRegTime_After }" readonly="readonly">
                  	</td>
                  </tr>
                  <tr>
                  	<th>처리상태</th>
                  	<td>
						<select name="answer" id="answer" class="inputWidth">
							<option value="all" <c:if test="${answer eq 'all' }"> selected="selected"</c:if>>전체</option>
							<option value="false" <c:if test="${answer eq 'false' }"> selected="selected"</c:if>>답변대기</option>
							<option value="true" <c:if test="${answer eq 'true' }"> selected="selected"</c:if>>답변완료</option>
				  		</select>
                  	</td>
                  </tr>
                </table>
                <div class="text-center">
                	<input type="submit" class="custom_button" value="검색" id="search">
                	<input type="reset" class="custom_button" id="btnReset" value="초기화">
              	</div>
              </form>
              </div>
            </div>
          </div>

		  
          <!-- DataTales Example// -->
          <div class="card shadow mb-4">
            <div class="card-body">
              <div class="table-responsive">
<!-- 		  	  게시판 목록의 리스트 form의 action은 다중 삭제로 이동 -->		  	  
              <form action="QDeleteForm.abook" id="searchBoard" method="post">
              <div>
                <input type="submit" class="custom_button custom_button_margin" value="답변삭제">
		  		&nbsp;&nbsp;&nbsp;* 문의제목을 클릭하시면 문의 상세보기, 답변작성, 답변수정, 답변삭제가 가능합니다.
		  	  </div> 
                <c:if test="${!empty qSearchList && pageInfo.listCount > 0}">
                <table class="table table-bordered" id="dataSearchTable" width="100%" cellspacing="0">
                    <tr>
                      <th><input type="checkbox" onclick="checkAll(this)"></th>
                      <th>접수번호</th>
                      <th>접수일</th>
                      <th>문의제목</th>
                      <th>처리상태</th>
                      <th>처리일</th>
                      <th>상품번호</th>
                      <th>상품명</th>
                      <th>고객ID</th>
                    </tr>
                    <c:forEach var="board" items="${qSearchList }" varStatus="status">
                    <tr>
                      <td><input type="checkbox" class="checklist" name="boardRe_refList" value="${board.boardReRef }"></td>
                      <td>${board.boardReRef }</td>
                      	<c:if test="${board.boardReSeq == 0 }">
                      		<td>${board.boardRegTime }</td>
	                      	<td><a href="QWriteForm.abook?boardNum=${board.boardNum }&page=${pageInfo.page}">${board.boardTitle }</a></td>
                      		<td class="red">답변대기</td>
                      		<td>-</td>
                      	</c:if>
                      	<c:if test="${board.boardReSeq > 0 }">
                      		<td>${board.boardRegTime }</td>
                      		<td><a href="QDetail.abook?boardReRef=${board.boardReRef }&page=${pageInfo.page}">${board.boardTitle }</a></td>
                      		<td>답변완료</td>
                      		<td>${board.boardAnswerRegTime }</td>
                      	</c:if>
                      <td>${board.bookID }</td>	
                      <td>${board.bookTitle }</td>
                      <td>${board.boardWriter }</td>
                    </tr>
                    </c:forEach>
                </table>
                <section id="pageList" class="list-center">
                	<c:if test="${pageInfo.startPage > pageInfo.pageBlock }">
                		<a href="QSearchPro.abook?page=${pageInfo.startPage-pageInfo.pageBlock }&boardRegTime_Before=${boardRegTime_Before}&boardRegTime_After=${boardRegTime_After}&answer=${answer}">[이전]</a>&nbsp;
                	</c:if>
                	<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                		<c:choose>
                			<c:when test="${i eq pageInfo.page }">${i }&nbsp;</c:when>
                			<c:otherwise><a href="QSearchPro.abook?page=${i }&boardRegTime_Before=${boardRegTime_Before}&boardRegTime_After=${boardRegTime_After}&answer=${answer}">${i }</a>&nbsp;</c:otherwise>
                		</c:choose>
                	</c:forEach>
                	<c:if test="${pageInfo.endPage < pageInfo.maxPage }">
                		<a href="QSearchPro.abook?page=${pageInfo.startPage+pageInfo.pageBlock }&boardRegTime_Before=${boardRegTime_Before}&boardRegTime_After=${boardRegTime_After}&answer=${answer}">[다음]</a>
                	</c:if>
                </section>
                </c:if>
                </form>
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