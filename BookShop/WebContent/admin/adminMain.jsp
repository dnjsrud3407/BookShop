<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<%
if(session.getAttribute("uID") == null){
	response.sendRedirect("index.jsp");
}
 %>
<html lang="en">
<head>

  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>SB Admin 2 - Dashboard</title>

  <!-- Custom fonts for this template-->
  <link href="admin/vendor/fontawesome-free/css/all.min.css?ver=1" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i?ver=1" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="admin/css/sb-admin-2.css?ver=1" rel="stylesheet">
  <link href="admin/css/sb-admin-2.min.css?ver=1" rel="stylesheet">

</head>
<script src="./js/jquery-3.4.1.js"></script>
<script type="text/javascript">
function getBoard(type) {
	  // type = 102 -> 상품문의
	  // type = 103 -> 상품후기
	  // type = 109 -> 1:1문의
	  $.ajax({
		  type:"POST",
		  url:"MainBoard.adm?type=" + type,
		  success: function(msg2){	// 베스트셀러
		  	  $(".ajaxBoard").html(msg2);
		  }
	  });
}


// =============================  
// 매출 통계
$(document).ready(function() {
	// 달 정보 가져오기
	var monthArr = new Array();
	var id = ""; var index = 0;
	for(var i = 11; i >= 0; i--) {
		id = "#month" + i;
		monthArr[index] = $(id).val();
		index = index + 1;
	}
	// 달 별 매출 
	var monthCasheArr = new Array();
	var id = ""; var index = 0;
	for(var i = 11; i >= 0; i--) {
		id = "#monthCashe" + i;
		monthCasheArr[index] = parseInt($(id).val());
		index = index + 1;
	}
	
// 	alert(monthArr[0]);
	var ctx = document.getElementById("myAreaChart");
	var myLineChart = new Chart(ctx, {
	  type: 'line',
	  data: {
	    labels: [monthArr[0], monthArr[1], monthArr[2], monthArr[3], monthArr[4], monthArr[5], monthArr[6], monthArr[7], monthArr[8], monthArr[9], monthArr[10], monthArr[11]],
	    datasets: [{
	      label: "총 매출",
	      lineTension: 0.3,
	      backgroundColor: "rgba(78, 115, 223, 0.05)",
	      borderColor: "rgba(78, 115, 223, 1)",
	      pointRadius: 3,
	      pointBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointBorderColor: "rgba(78, 115, 223, 1)",
	      pointHoverRadius: 3,
	      pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
	      pointHoverBorderColor: "rgba(78, 115, 223, 1)",
	      pointHitRadius: 10,
	      pointBorderWidth: 2,
	      data: [monthCasheArr[0], monthCasheArr[1], monthCasheArr[2], monthCasheArr[3], monthCasheArr[4], monthCasheArr[5], monthCasheArr[6], monthCasheArr[7], monthCasheArr[8], monthCasheArr[9], monthCasheArr[10], monthCasheArr[11]],
	    }],
	  },
	  options: {
	    maintainAspectRatio: false,
	    layout: {
	      padding: {
	        left: 10,
	        right: 25,
	        top: 25,
	        bottom: 0
	      }
	    },
	    scales: {
	      xAxes: [{
	        time: {
	          unit: 'date'
	        },
	        gridLines: {
	          display: false,
	          drawBorder: false
	        },
	        ticks: {
	          maxTicksLimit: 7
	        }
	      }],
	      yAxes: [{
	        ticks: {
	          maxTicksLimit: 5,
	          padding: 10,
	          // Include a dollar sign in the ticks
	          callback: function(value, index, values) {
	            return number_format(value) + '원';
	          }
	        },
	        gridLines: {
	          color: "rgb(234, 236, 244)",
	          zeroLineColor: "rgb(234, 236, 244)",
	          drawBorder: false,
	          borderDash: [2],
	          zeroLineBorderDash: [2]
	        }
	      }],
	    },
	    legend: {
	      display: false
	    },
	    tooltips: {
	      backgroundColor: "rgb(255,255,255)",
	      bodyFontColor: "#858796",
	      titleMarginBottom: 10,
	      titleFontColor: '#6e707e',
	      titleFontSize: 14,
	      borderColor: '#dddfeb',
	      borderWidth: 1,
	      xPadding: 15,
	      yPadding: 15,
	      displayColors: false,
	      intersect: false,
	      mode: 'index',
	      caretPadding: 10,
	      callbacks: {
	    	title: function (tooltipItem, chart) {
				return ;
			},
	        label: function(tooltipItem, chart) {
	          return chart['labels'][tooltipItem['index']] + "월 : " + number_format(tooltipItem.yLabel) + '원';
	        }
	      }
	    }
	  }
	});
	
});
function number_format(number, decimals, dec_point, thousands_sep) {
	  // *     example: number_format(1234.56, 2, ',', ' ');
	  // *     return: '1 234,56'
	  number = (number + '').replace(',', '').replace(' ', '');
	  var n = !isFinite(+number) ? 0 : +number,
	    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
	    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
	    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
	    s = '',
	    toFixedFix = function(n, prec) {
	      var k = Math.pow(10, prec);
	      return '' + Math.round(n * k) / k;
	    };
	  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
	  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
	  if (s[0].length > 3) {
	    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
	  }
	  if ((s[1] || '').length < prec) {
	    s[1] = s[1] || '';
	    s[1] += new Array(prec - s[1].length + 1).join('0');
	  }
	  return s.join(dec);
	}

	
</script>
<body id="page-top">

  <!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
	<jsp:include page="adminInc/adminMenu.jsp" />
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Topbar -->
        <jsp:include page="./adminInc/topbar.jsp"></jsp:include>
        <!-- End of Topbar -->

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Content Row -->
          <div class="">
          <div class="row" >

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="font-weight-bold text-xl text-info text-uppercase mb-1">주문현황</div>
                    </div>
                    <div class="col-xl-6">
                      <div class="col mr-2">
                      <c:forEach var="order" items="${orderList }" varStatus="status">
                      	<c:choose>
                      	  <c:when test="${status.index == 0 }">
	                        <div class="font-weight text-dark mb-1 iconText">신규주문</div><div class="iconText2">${order }건</div><div class="clear"></div>
                      	  </c:when>
                      	  <c:when test="${status.index == 1 }">
	                        <div class="font-weight text-dark mb-1 iconText">배송중</div><div class="iconText2">${order }건</div><div class="clear"></div>
                      	  </c:when>
                      	  <c:when test="${status.index == 2 }">
	                        <div class="font-weight text-dark mb-1 iconText">배송완료</div><div class="iconText2">${order }건</div><div class="clear"></div>
                      	  </c:when>                      	  
                      	</c:choose>
                      </c:forEach>
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-clipboard-list fa-2x_custom text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="font-weight-bold text-xl text-success text-uppercase mb-1">매출현황</div>
                    </div>
                    <div class="col mr-2">
                      <c:forEach var="salesCashe" items="${salesCasheList }" varStatus="status">
                      	<c:choose>
                      	  <c:when test="${status.index == 0 }">
	                        <div class="font-weight text-dark mb-1 iconTextS">총 주문</div><div class="iconTextS2">${salesCashe }원</div><div class="clear"></div>
                      	  </c:when>
                      	  <c:when test="${status.index == 1 }">
	                        <div class="font-weight text-dark mb-1 iconTextS">취소/반품</div><div class="iconTextS2">${salesCashe }원</div><div class="clear"></div>
                      	  </c:when>
                      	  <c:when test="${status.index == 2 }">
	                        <div class="font-weight text-dark mb-1 iconTextS">매출</div><div class="iconTextS2">${salesCashe }원</div><div class="clear"></div>
                      	  </c:when>                      	  
                      	</c:choose>
                      </c:forEach>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-dollar-sign fa-2x_custom text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="font-weight-bold text-xl text-primary text-uppercase mb-1">상품</div>
                    </div>
                    <div class="col mr-2">
                      <c:forEach var="ea" items="${bookEAList }" varStatus="status">
                        <c:choose>
                          <c:when test="${status.index == 1 }">
		                    <div class="font-weight text-dark mb-1 iconText">판매중 상품</div><div class="iconText2">${ea }건</div><div class="clear"></div>
                          </c:when>
                          <c:otherwise>
		                    <div class="font-weight text-dark mb-1 iconText">수정중 상품</div><div class="iconText2">${ea }건</div><div class="clear"></div>
                          </c:otherwise>
                        </c:choose>
                      </c:forEach>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-calendar fa-2x_custom text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>
          </div>
          <!-- Content Row -->

          <div class="row">

            <!-- Area Chart -->
            <div class="col-xl-12 col-lg-7">
              <div class="card shadow mb-4">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary text-xl">매출 통계</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body">
                  <div class="chart-area">
                    <canvas id="myAreaChart"></canvas>
                    <c:forEach var="month" items="${monthList }" varStatus="status">
                    	<input type="hidden" id="month${status.index }" value="${month }">
                    </c:forEach>
                    <c:forEach var="monthCashe" items="${monthCasheList }" varStatus="status">
                    	<input type="hidden" id="monthCashe${status.index }" value="${monthCashe }">
                    </c:forEach>
                  </div>
                </div>
              </div>
            </div>

            
          </div>
          
          <div class="">
          <div class="row">
            <div class="col-xl-4 col-lg-5">
              <div class="card shadow mb-4 EventHeight">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">공지사항</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body text-center text-dark">
                	<table class="boardTr">
                		<jsp:useBean id="date" class="java.util.Date" />
                		<c:forEach var="board" items="${noticeList }" varStatus="status">
	                		<tr>
	                			<td colspan="2"><a href="NoticeDetail.adb?boardNum=${board.boardNum }&page=1">${board.boardTitle }</a></td>
	                			<td><fmt:formatDate value="${board.boardRegTime }" type="date"/></td>
	                		</tr>                		
                		</c:forEach>
                	</table>
                </div>
              </div>
            </div>
            
            <div class="col-xl-4 col-lg-5">
              <div class="card shadow mb-4 EventHeight">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">이벤트</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body text-center text-dark">
                	<table class="boardTr">
                		<c:forEach var="board" items="${eventList }" varStatus="status">
						  <c:set var="boardTitle" value="${board.boardTitle }"/>
						  <c:set var="length" value="${fn:length(boardTitle) }"/>
	                		<tr>
	                			<td colspan="2"><a href="EventDetail.adb?boardNum=${board.boardNum }&page=1">${fn:substring(boardTitle,0,length-8) }</a></td>
	                			<td><fmt:formatDate value="${board.boardRegTime }" type="date"/></td>
	                		</tr>                		
                		</c:forEach>
                	</table>
                </div>
              </div>
            </div>
            
            <!-- 미답변 게시글 -->
            <div class="col-xl-4 col-lg-5">
              <div class="card shadow mb-4 EventHeight">
                <!-- Card Header - Dropdown -->
                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                  <h6 class="m-0 font-weight-bold text-primary">미답변 게시글</h6>
                </div>
                <!-- Card Body -->
                <div class="card-body text-center text-dark" style="padding-top: 0px;">
                	<table class="boardTr ajaxBoard">
                		<tr>
                			<td class="selectColor" onclick="getBoard(102)">상품문의</td>
                			<td class="boardTitle" onclick="getBoard(103)">상품후기</td>
                			<td class="boardTitle" onclick="getBoard(109)">1:1문의</td>
                		</tr>
                		<tr></tr>
                		<c:forEach var="board" items="${qList }" varStatus="status">
	                		<tr>
	                			<td colspan="2"><a href="QWriteForm.abook?boardNum=${board.boardNum }&page=1">${board.boardTitle }</a></td>
	                			<td><fmt:formatDate value="${board.boardRegTime }" type="date"/></td>
	                		</tr>                		
                		</c:forEach>
                	</table>
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
  <script src="./vendor/jquery/jquery.min.js"></script>
  <script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="./vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="./js/sb-admin-2.min.js"></script>

  <!-- Page level plugins -->
  <script src="./vendor/chart.js/Chart.min.js"></script>

  <!-- Page level custom scripts -->
<!--   <script src="./js/demo/chart-area-demo.js"></script> -->
<!--   <script src="./js/demo/chart-pie-demo.js"></script> -->
</body>

</html>
