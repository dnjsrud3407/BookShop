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

<script src="admin/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script> 
	$(document).ready(function() {
	    
		// 책 카테고리 선택 수정
		// ================== 대분류 카테고리 지정//
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
		
		// 검색 초기화
		$("#btnReset").click(function () {
			$("#searchForm").each(function(){
			    this.reset();
			});
		});
		
		
	});
	
	// =====================검색 json=========================== 
	function searchList(page) {
		var bookID = $("#bookID").val();
		var bookTitle = $("#bookTitle").val();
		var bookPublisher = $("#bookPublisher").val();
		var BK1 = $("#BK1Category option:selected").val();
		var BK2 = $("#BK2Category option:selected").val();
		var BK3 = $("#BK3Category option:selected").val();
		var bookEA = null;
		var bookisView = $(":input:radio[name=bookisView]:checked").val();
		if($("input:checkbox[id=bookEA]").is(":checked")) {
			bookEA = $("#bookEA").val();
		}
		
		// 넘겨줄 파라미터 값
		var dataList = {"bookID":bookID,
				  "bookTitle":bookTitle,
				  "bookPublisher":bookPublisher,
				  "BK1":BK1,
				  "BK2":BK2,
				  "BK3":BK3,
				  "bookEA":bookEA,
				  "bookisView":bookisView,
				  "page":page};
		
		$.getJSON('Search.abook', dataList, function (json) {
			$('#dataSearchTable').html("");
	  		$('#dataSearchTable').html("<tr><th><input type='checkbox' onclick='checkAll(this)'></th>"+
	  				"<th>상품 번호</th><th>상품 이름</th><th>출판사</th><th>출판일</th>"+
	  				"<th>가격</th><th>재고 수량</th><th>판매량</th><th>전시상태</th>"+
	  				"<th>포인트 적립률</th><th>대분류</th><th>단계</th><th>소분류</th></tr>");
			$.each(json.bookList, function(index, book){
				var bookEA = parseInt(book.bookEA);
				// 책 수량 10보다 작으면 빨간 색
				var outBookEA = "";
				if(bookEA < 10) {
					outBookEA = "</td><td class='red'>"+ bookEA;
				} else {
					outBookEA = "</td><td>"+ bookEA;
				}
				// 책 출판일 없으면 공백 
				var bookPublishedDate = book.bookPublishedDate;
				/* alert(book.bookPublishedDate); */
				if(bookPublishedDate == null || bookPublishedDate == "" || bookPublishedDate == "null") {
					bookPublishedDate = " ";
				}
		  		$('#dataSearchTable').append(
		  				"<tr><td><input type='checkbox' class='checklist' name='bookIDList' value='"+ book.bookID +"'></td>"+
		  				"<td>" + book.bookID 
		  				+ "</td><td><a href='Detail.abook?bookID="+ book.bookID +"'>"+book.bookTitle+"</a></td>"
		  				+"<td>"+ book.bookPublisher
		  				+"</td><td>"+ bookPublishedDate 
		  				+"</td><td>"+ book.bookPrice 
	  					+ outBookEA
		  				+"</td><td>"+ book.salesVolume 
		  				+"</td><td>"+ book.bookisView
		  				+"</td><td>"+ book.saveRatio 
		  				+"</td><td>"+ book.BK1 
		  				+"</td><td>"+ book.BK2
		  				+"</td><td>"+ book.BK3 +"</td></tr>"
		  				);
							  		
			});

			$.each(json.pageInfo, function (index, pageInfo) {
				// [이전] 에 들어갈 값
				var pre=parseInt(pageInfo.startPage)-parseInt(pageInfo.pageBlock);
				// [다음] 에 들어갈 값
				var next=parseInt(pageInfo.startPage)+parseInt(pageInfo.pageBlock);
				$("#pageList").html("");
				if(parseInt(pageInfo.startPage) > parseInt(pageInfo.pageBlock)) {
					$("#pageList").append("<a href='#' onclick='searchList("+pre+")'>[이전]</a>&nbsp;");
				}
				for(var i = parseInt(pageInfo.startPage); i <= parseInt(pageInfo.endPage); i++) {
					if(i == parseInt(pageInfo.page)) {
						$("#pageList").append(i+"&nbsp;");						
					} else {
						$("#pageList").append("<a href='#' onclick='searchList("+ i +")'>"+i+"</a>&nbsp;");
					}
				}
				if(parseInt(pageInfo.endPage) < parseInt(pageInfo.maxPage )) {
					$("#pageList").append("<a href='#' onclick='searchList("+next+")'>[다음]</a>&nbsp;");
				}
			});
		});
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
            <div class="card-body">
              <div class="table-responsive">
              <form action="Search.abook" method="post" id="searchForm">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <tr>
                  	<th class="thWidth">검색어</th>
                  	<td>
                  		<div class="searchWidth">
                  			상품 번호 <input type="text" name="bookID" id="bookID" class="inputWidth">
                  		</div>
                  		<div class="searchWidth">
                  			상품 이름 <input type="text" name="bookTitle" id="bookTitle" class="inputWidth">
                  		</div>
                  		<div class="searchWidth">
                  			출판사 <input type="text" name="bookPublisher" id="bookPublisher" class="inputWidth">
                  		</div>
                  	</td>
                  </tr>
                  <tr>
                  	<th>카테고리</th>
                  	<td>
                  		<div class="searchWidth">
						대분류  <select name="BK1Category" id="BK1Category" class="inputWidth">
						     		<option value="선택하세요">선택하세요</option>
						      </select>
				  		</div>
				  		<div class="searchWidth">
						레벨  <select name="BK2Category" id="BK2Category" class="inputWidth">
						      		<option value="선택하세요">선택하세요</option>
						  	   </select>
				       </div>
				  	   <div class="searchWidth">
						소분류  <select name="BK3Category" id="BK3Category" class="inputWidth">
						     		<option value="선택하세요">선택하세요</option>
						  	  </select>
					   </div>
                  	</td>
                  </tr>
                  <tr>
                  	<th>기타 여부</th>
                  	<td>
                  		<span class="checkbox_padding">
                  			<input type="checkbox" name="bookEA" id="bookEA" value="shortage"/>&nbsp;재고 부족
                  		</span>
                  	</td>
                  </tr>
                  <tr>
                  	<th>전시 여부</th>
                  	<td>
                  		<div class="checkboxWidth">
                  			<input type="radio" name="bookisView" value="all"/>&nbsp;전체
                  		</div>
                  		<div class="checkboxWidth">
                  			<input type="radio" name="bookisView" value="true"/>&nbsp;전시중 
                  		</div>
                  		<div class="checkboxWidth">
                  			<input type="radio" name="bookisView" value="false"/>&nbsp;미전시 
                  		</div>
                  	</td>
                  </tr>
                </table>
                <div class="text-center">
                	<input type="button" class="custom_button" value="검색" id="search" onclick="searchList(1)">
                	<input type="button" class="custom_button" id="btnReset" value="초기화">
              	</div>
              </form>
              </div>
            </div>
          </div>

          <!-- DataTales Example// -->
          <div class="card shadow mb-4">
            <div class="card-header py-3">
              <h5 class="m-0 font-weight-bold text-primary">상품 목록</h5>
            </div>
            <div class="card-body">
              <div class="table-responsive">
<!--               게시판 목록의 리스트 form의 action은 다중 삭제로 이동-->
              <form action="DeleteForm.abook" id="searchBoard" method="post">
              <div>
		  		<input type="button" value="상품등록" class="custom_button custom_button_margin" onclick="location.href='WriteForm.abook'">
              	<input type="submit" value="상품삭제" class="custom_button custom_button_margin">
		  		&nbsp;&nbsp;&nbsp;* 상품 이름을 클릭하시면 상세보기, 상품수정, 상품삭제가 가능합니다.
		  	  </div>
              <c:if test="${bookList != null && pageInfo.listCount > 0}">
                <table class="table table-bordered" id="dataSearchTable" width="100%" cellspacing="0">
                    <tr>
                      <th><input type="checkbox" onclick="checkAll(this)"></th>
                      <th>상품 번호</th>
                      <th>상품 이름</th>
                      <th>출판사</th>
                      <th>출판일</th>
                      <th>가격</th>
                      <th>재고 수량</th>
                      <th>판매량</th>
                      <th>전시상태</th>
                      <th>포인트 적립률</th>
                      <th>대분류</th>
                      <th>단계</th>
                      <th>소분류</th>
                    </tr>
                    <c:forEach var="book" items="${bookList }" varStatus="status">
                    <tr>
                      <td><input type="checkbox" class="checklist" name="bookIDList" value="${book.bookID }"></td>
                      <td>${book.bookID }</td>
                      <td><a href="Detail.abook?bookID=${book.bookID }&page=${pageInfo.page}">${book.bookTitle }</a></td>
                      <td>${book.bookPublisher }</td>
                      <td>${book.bookPublishedDate }</td>
                      <td>${book.bookPrice }</td>
                      <c:if test="${book.bookEA < 10}">
                      	<td class="red">${book.bookEA }</td>
                      </c:if>
                      <c:if test="${book.bookEA >= 10}">
                      	<td>${book.bookEA }</td>
                      </c:if>
                      <td>${book.salesVolume }</td>
                      <td>${book.bookisView }</td>
                      <td>${book.saveRatio }</td>
                      <td>${book.BK1 }</td>
                      <td>${book.BK2 }</td>
                      <td>${book.BK3 }</td>
                    </tr>
                    </c:forEach>
                </table>
                <section id="pageList" class="list-center">
                	<c:if test="${pageInfo.startPage > pageInfo.pageBlock }">
                		<a href="List.abook?page=${pageInfo.startPage-pageInfo.pageBlock }">[이전]</a>&nbsp;
                	</c:if>
                	<c:forEach var="i" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" step="1">
                		<c:choose>
                			<c:when test="${i eq pageInfo.page }">${i }&nbsp;</c:when>
                			<c:otherwise><a href="List.abook?page=${i }">${i }</a>&nbsp;</c:otherwise>
                		</c:choose>
                	</c:forEach>
                	<c:if test="${pageInfo.endPage < pageInfo.maxPage }">
                		<a href="List.abook?page=${pageInfo.startPage+pageInfo.pageBlock }">[다음]</a>
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
  <script src="vendor/jquery/jquery.min.js"></script>
  <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
<!--   <script src="vendor/datatables/jquery.dataTables.min.js"></script> -->
  <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="js/demo/datatables-demo.js"></script>


</body>
</html>