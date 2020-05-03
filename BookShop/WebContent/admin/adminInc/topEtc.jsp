<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">배송 진행 리스트</div>
                     <div class="h5 mb-0 font-weight-bold text-gray-800"><a href="OrderDeliveryList.adm">[]건</a></div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-box fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
			
            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1">주문취소 진행리스트</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800"><a href="OrderCencleList.adm">[]건</a></div>
                    </div>
                    <div class="col-auto">
                      <i class="material-icons fa-3x text-gray-300" style="font-size:48px;">cancel</i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1">제품 반품 진행리스트</div>
                      <div class="row no-gutters align-items-center">
                        <div class="col-auto">
                          <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800"><a href="OrderRefundList.adm">[]건</a></div>
                        </div>
                        <div class="col">
                          <div class="progress progress-sm mr-2">
                            <div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="col-auto">
                      <i class="material-icons fa-2x text-gray-300" style="font-size:48px;">assignment_return</i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Pending Requests Card Example -->
            <div class="col-xl-3 col-md-6 mb-4">
              <div class="card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">제품교환 진행 리스트</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800"><a href="OrderExchangeList.adm">[]건</a></div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-exchange-alt fa-2x text-gray-300" style="font-size:48px;"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
</body>
</html>