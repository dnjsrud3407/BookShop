<%@page import="vo.MemberBean"%>
<%@page import="vo.OrderBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
if(session.getAttribute("uID") == null){
	response.sendRedirect("index.jsp");
}
	List<OrderBean> order = (List<OrderBean>)request.getAttribute("order");
	OrderBean orderDetaile = (OrderBean)request.getAttribute("orderDetaile");
	MemberBean memberInfo = (MemberBean)request.getAttribute("member");
 %>
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
  <link href="admin/css/sb-admin-2.min.css?ver=1" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="admin/vendor/datatables/dataTables.bootstrap4.min.css?ver=1" rel="stylesheet">
<script src="admin/js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>

<style type="text/css">
img{
	width: 400px;
	height: 300px;
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
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

<!-- top 로그인 관리 부분---------------------------------------------------------------------------------------------------------------------- -->
				<jsp:include page="../adminInc/adminTop.jsp" />
<!-- ---------------------------------------------------------------------------------------------------------------------------------------- -->


        </nav>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

		  <!-- 상세 설정 -->
          <div class="card shadow mb-4">
           <div class="card-header py-3">
				<h6 class="m-0 font-weight-bold text-primary">-교환 내용 상세보기-</h6>
			</div>
            <div class="card-body">
              <div class="table-responsive">
              <form action="OrderModifyPro.adm" method="post" id="searchForm">
               <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
              	 <tr>
       				<th style="width: 15%;">상품코드</th>
<!--      				<th style="width: 15%;">사진</th> -->
     				<th style="width: 15%;">상품명</th>
     				<th style="width: 15%;">상품가격</th>
					<th style="width: 15%;">수량</th>
					<th style="width: 15%;">적립금</th>     				
       			</tr>
       			<%for(int i = 0; i < order.size(); i++){
												 %>
       			<tr>
       			    <td><%=order.get(i).getBookID() %></td>
<!-- 					<td><img src="#" alt="-사진-" height="30px"> </td> -->
       				<td><%=order.get(i).getBookTitle() %></td>
  				    <td><%=order.get(i).getBookPrice() %></td>
  				    <td><%=order.get(i).getBookEA() %></td>
  				    <td>3,000원 </td>
       			</tr>
  				    <%} %>
              	</table>
              	<%-- 상품합계(<%=orderDetaile.getTotalPrice() %>) - 회원할인(<%=orderDetaile.get() %>) + 배송비(3,000) =  --%>총 결제금액 : <%=orderDetaile.getTotalPrice() %>원 <br>
              
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
					
                  <tr>
                  	<th style="width: 15%;">주문번호</th>
                  	<td>
                  		<span class="checkbox_padding" style="width: 15%;">

              			</span>
                  	</td>
                  	
                  	<th style="width: 15%;">주문상태</th>
                  	<td>
                  	    <span class="checkbox_padding" style="width: 15%;">
                  			<span class="checkbox_padding" style="width: 15%;">
	    			    <select name="orderStatus" class="size" id="orderStatus">
<!-- 	    			    	<option value="선택해주세요" class="size">선택해주세요.</option>				 -->
<!-- 		    			    <option value="주문접수" class="size">주문접수</option>	 -->
<!-- 		    			    <option value="결제완료" class="size">결제완료</option> -->
<!-- 		    			    <option value="배송중" class="size">배송중</option> -->    
<!-- 		    			    <option value="환불요청" class="size">환불요청</option> -->
<!-- 		    			    <option value="교환요청" class="size">교환요청</option> -->
<!-- 		    			    <option value="주문취소" class="size">주문취소</option> -->
		    			    <option value="null" class="size" <c:if test="${orderDetaile.orderStatus eq '선택해주세요' }">selected</c:if>>선택해주세요.</option>				
<%-- 		    			    <option value="주문접수" <c:if test="${orderDetaile.orderStatus eq '주문접수' }">selected</c:if>>주문접수</option>				 --%>
							<option value="결제완료" <c:if test="${orderDetaile.orderStatus eq '결제완료' }">selected</c:if>>결제완료 </option>
							<option value="배송중" <c:if test="${orderDetaile.orderStatus eq '배송중' }">selected</c:if>>배송중</option>		
							<option value="취소요청" <c:if test="${orderDetaile.orderStatus eq '취소요청' }">selected</c:if>>취소요청</option>
							<option value="반품요청" <c:if test="${orderDetaile.orderStatus eq '반품요청' }">selected</c:if>>반품요청</option>
							<option value="교환요청" <c:if test="${orderDetaile.orderStatus eq '교환요청' }">selected</c:if>>교환요청</option>
							<option value="" class="size">---------</option>				
							<option value="배송내역" <c:if test="${orderDetaile.orderStatus eq '배송내역' }">selected</c:if>>배송내역</option>
<%-- 							<option value="교환내역" <c:if test="${orderDetaile.orderStatus eq '교환내역' }">selected</c:if>>교환내역</option> --%>
							<option value="취소내역" <c:if test="${orderDetaile.orderStatus eq '취소내역' }">selected</c:if>>취소내역</option>
							<option value="반품내역" <c:if test="${orderDetaile.orderStatus eq '반품내역' }">selected</c:if>>반품내역</option>
	 					</select>
	 					</span>
              			</span>
                  	</td>
                  </tr>
                  
                  <tr>
                  	<th style="width: 15%;">주문일자</th>
                  	<td>
                  	    <span class="checkbox_padding" style="width: 15%;">
							<%=orderDetaile.getOrderTime() %>
              			</span>
					</td>
					
					<th style="width: 15%;">결제 방법</th>
                  	<td>
                  	    <span class="checkbox_padding" style="width: 15%;">
                  			<%=orderDetaile.getPaymentType() %>
              			</span>
                  	</td>
                  </tr>
                  
<!--                   <tr>
                  	<th style="width: 15%;">결제 정보 - X</th>
                  	<td>
                  		<span class="checkbox_padding">
                  			000-000-0000
                  		</span>
                  	</td>
                  	<th style="width: 15%;">입금인 - X</th>
                  	<td>
                  		<span class="checkbox_padding">
                  			김북킹
                  		</span>
                  	</td>
                  </tr>
                  
                   <tr>
                  	<th style="width: 15%;">운송장 번호 - X</th>
                  	<td>
                  		<span class="checkbox_padding" style="width: 15%;">
                  			012-456789-10
                  		</span>
                  	</td>
                  	<th style="width: 15%;">발송일자 - X</th>
                  	<td>
                  		<span class="checkbox_padding" style="width: 15%;">
                  			2020-12-12 12:12
                  		</span>
                  	</td>
                  </tr>
                  
                  <tr>
                  	<th style="width: 15%;">처리 상태 - X</th>
                  	<td colspan="3">
                  		<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  			<tr>
			                  	<th style="width: 15%;">주문접수</th>
			                  	<th style="width: 15%;">결제완료</th>
			                  	<th style="width: 15%;">배송완료</th>
			                  	<th style="width: 15%;">주문취소</th>

                  			</tr>
                  			<tr>
                  			    <td>2020-20-20 20:20:20 </td>
                  				<td>- </td>
             				    <td>- </td>
             				    <td>- </td>
                  			</tr>
                  		</table>
                  	</td>
                  </tr> -->
                  </table>
                  
                  - 주문자 정보
                  <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
	                  <tr>
	                  	<th style="width: 15%;">주문자명</th>
	                  	<td>
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<%=memberInfo.getU_name() %>
                  			</span>
	                  	</td>
	                  	
	                  	<th style="width: 15%;">이메일</th>
	                  	<td>
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<%=memberInfo.getEmail() %>
                  			</span>
	                  	</td>
	                  </tr>
	                  
	                   <tr>
	                  	<th style="width: 15%;">전화번호</th>
	                  	<td>
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<%=memberInfo.getTell_num() %>
                  			</span>
	                  	</td>
	                  	<th style="width: 15%;">휴대폰</th>
	                  	<td>
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<%=memberInfo.getPhone_num() %>
                  			</span>
	                  	</td>
	                  </tr>
	                  
<!-- 	                   <tr> -->
<!-- 	                  	<th style="width: 15%;">우편번호</th> -->
<!-- 	                  	<td colspan="3"> -->
<!-- 	                  		<span class="checkbox_padding" style="width: 15%;"> -->
<!--                   				12345 -->
<!--                   			</span> -->
<!-- 	                  	</td> -->
<!-- 	                  </tr> -->
	                  
	                    <tr>
	                  	<th style="width: 15%;">주소</th>
	                  	<td>
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<%=memberInfo.getAddress() %>
                  			</span>
	                  	</td>
	                  	<th style="width: 15%;">상세주소</th>
	                  	<td>
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<%=memberInfo.getAddress2() %>
                  			</span>
	                  	</td>
	                  </tr>
                  </table>
                  
                  - 수취인 정보
                  <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <tr>
                  	<th style="width: 15%;">수취인명</th>
                  	<td colspan="3">
                  		<span class="checkbox_padding" style="width: 15%;">
               				<%=orderDetaile.getOrderRec() %>
               			</span>
                  	</td>
      		       </tr>
          		   
<!--           		    <tr> -->
<!-- 	                  	<th style="width: 15%;">전화번호</th> -->
<!-- 	                  	<td> -->
<!-- 	                  		<span class="checkbox_padding" style="width: 15%;"> -->
<!--                   				051-1234-5678 -->
<!--                   			</span> -->
<!-- 	                  	</td> -->
<!-- 	                  	<th style="width: 15%;">휴대폰</th> -->
<!-- 	                  	<td> -->
<!-- 	                  		<span class="checkbox_padding" style="width: 15%;"> -->
<!--                   				010-1234-5678 -->
<!--                   			</span> -->
<!-- 	                  	</td> -->
<!-- 	                  </tr> -->
          		       
<!--           		       <tr> -->
<!-- 	                  	<th style="width: 15%;">우편번호</th> -->
<!-- 	                  	<td colspan="3"> -->
<!-- 	                  		<span class="checkbox_padding" style="width: 15%;"> -->
<!--                   				78945 -->
<!--                   			</span> -->
<!-- 	                  	</td> -->
<!-- 	                  </tr> -->
	                  
	                   <tr>
	                  	<th style="width: 15%;">주소</th>
	                  	<td colspan="3">
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<%=orderDetaile.getOrderAddress() %>
                  			</span>
	                  	</td>
          		       </tr>
          		       
          		        <!-- <tr>
	                  	<th style="width: 15%;">요청사항 - X </th>
	                  	<td colspan="3">
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<textarea rows="5" cols="100" readonly="readonly">해당사항 없음</textarea>
                  			</span>
	                  	</td>
          		       </tr>
          		       
          		       <tr>
	                  	<th style="width: 15%;">주문 취소 사유</th>
	                  	<td colspan="3">
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<textarea rows="5" cols="100" readonly="readonly">해당사항 없음</textarea>
                  			</span>
	                  	</td>
          		       </tr>
          		       
          		       <tr>
	                  	<th style="width: 15%;">관리자 메모</th>
	                  	<td colspan="3">
	                  		<span class="checkbox_padding" style="width: 15%;">
                  				<textarea rows="5" cols="100" readonly="readonly">해당사항 없음</textarea>
                  			</span>
	                  	</td>
          		       </tr> -->
                  </table>
                  
<!--                   - 증빙서류 정보 -->
<!--                   <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0"> -->
<!--                   	<tr> -->
<!-- 	                  	<th style="width: 15%;">발급 여부</th> -->
<!-- 	                  	<td colspan="3"> -->
<!-- 	                  		<span class="checkbox_padding" style="width: 15%;"> -->
<!-- 								<input type="radio" value="">발행안함 -->
<!--                   			</span> -->
<!--                   			<span class="checkbox_padding" style="width: 15%;"> -->
<!-- 								<input type="radio" value="">세금계산서 신청 -->
<!--                   			</span> -->
<!--                   			<span class="checkbox_padding" style="width: 15%;"> -->
<!-- 								<input type="radio" value="">현금영수증 신청 -->
<!--                   			</span> -->
<!-- 	                  	</td> -->
<!--           		       </tr> -->
<!--                   </table> -->
                <input type="submit" value="수정" class="btn btn-large btn-success">
<!--                 <input type="button" id="" value="목록" onclick="location.href='OrderCompList.adm'"> -->
              </form>
              </div>
            </div>
          </div>

          <!-- DataTales Example// -->
<!--           <div class="card shadow mb-4"> -->
<!--             <div class="card-header py-3"> -->
<!--               <h5 class="m-0 font-weight-bold text-primary">주문 내용 상세보기</h5> -->
<!--             </div> -->
<!--             <div class="card-body"> -->
<!--               <div class="table-responsive"> -->
              
<!--               <form action="DeleteForm.abook" id="searchBoard" method="post"> -->
<!--               <input type="button" value="배송하기" onclick="location.href='OrderDeliveryDetail.adm'"> -->
<!-- 			  <input type="button" value="주문 취소" onclick="location.href='OrderCencleDetail.adm'"> -->
<!--               <input type="button" value="제품 반품" onclick="location.href='OrderRefundDetail.adm'"> -->
<!--               <input type="button" value="제품 교환" onclick="location.href='OrderExchangeDetail.adm'"> -->

<!--                 </form> -->
<!--               </div> -->
<!--             </div> -->
<!--           </div> -->

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