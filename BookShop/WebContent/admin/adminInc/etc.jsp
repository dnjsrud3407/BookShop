<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

 <div class="row">

          <!-- Content Row -->
          <div class="row">

            <!-- Content Column -->
            <div class="col-lg-6 mb-4">

              <!-- Project Card Example -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">배송진행리스트</h6>
                </div>
                <div class="card-body">
  <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
<!--           <h1 class="h3 mb-2 text-gray-800">배송진행리스트</h1> -->
          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
<!--             <div class="card-header py-3"> -->
<!--               <h6 class="m-0 font-weight-bold text-primary">배송진행리스트</h6> -->
<!--          <a href="MemberModifyForm.adm"><input type="button" value="멤버수정"></a> -->
<!-- 		  <a href="MemberDeletePro.adm"><input type="button" value="멤버삭제"></a> -->
<!--             </div> -->
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Name</th>
                      <th>level</th>
                      <th>address</th>
                      <th>Age</th>
                      <th>Join date</th>
                      <th>Profit</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Name</th>
                      <th>level</th>
                      <th>address</th>
                      <th>Age</th>
                      <th>Join date</th>
                      <th>Profit</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <tr onclick="location.href='OrderDeliveryList.adm'">
                      <td>Tiger Nixon</td>
                      <td>System Architect</td>
                      <td>Edinburgh</td>
                      <td>61</td>
                      <td>2011/04/25</td>
                      <td>$320,800</td>
                    </tr>
                    <tr onclick="location.href='OrderDeliveryList.adm'">
                      <td>Garrett Winters</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>63</td>
                      <td>2011/07/25</td>
                      <td>$170,750</td>
                    </tr>
                    <tr>
                      <td>Ashton Cox</td>
                      <td>Junior Technical Author</td>
                      <td>San Francisco</td>
                      <td>66</td>
                      <td>2009/01/12</td>
                      <td>$86,000</td>
                    </tr>
                    <tr>
                      <td>Cedric Kelly</td>
                      <td>Senior Javascript Developer</td>
                      <td>Edinburgh</td>
                      <td>22</td>
                      <td>2012/03/29</td>
                      <td>$433,060</td>
                    </tr>
                    <tr onclick="location.href='OrderDeliveryList.adm'">
                      <td>Airi Satou</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>33</td>
                      <td>2008/11/28</td>
                      <td>$162,700</td>
                    </tr>
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->                
      </div>
      
              </div>


            <div class="col-lg-6 mb-4">

              <!-- Illustrations -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">주문취소 진행리스트</h6>
                </div>
                <div class="card-body">
  <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
<!--           <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
<!--             <div class="card-header py-3"> -->
<!--               <h6 class="m-0 font-weight-bold text-primary">주문취소 진행리스트</h6> -->
<!--               <a href="MemberModifyForm.adm"><input type="button" value="멤버수정"></a> -->
<!-- 	          <a href="MemberDeletePro.adm"><input type="button" value="멤버삭제"></a> -->
<!--             </div> -->
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Name</th>
                      <th>level</th>
                      <th>address</th>
                      <th>Age</th>
                      <th>Join date</th>
                      <th>Profit</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Name</th>
                      <th>level</th>
                      <th>address</th>
                      <th>Age</th>
                      <th>Join date</th>
                      <th>Profit</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <tr>
                      <td>Tiger Nixon</td>
                      <td>System Architect</td>
                      <td>Edinburgh</td>
                      <td>61</td>
                      <td>2011/04/25</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Garrett Winters</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>63</td>
                      <td>2011/07/25</td>
                      <td>$170,750</td>
                    </tr>
                    <tr>
                      <td>Ashton Cox</td>
                      <td>Junior Technical Author</td>
                      <td>San Francisco</td>
                      <td>66</td>
                      <td>2009/01/12</td>
                      <td>$86,000</td>
                    </tr>
                    <tr>
                      <td>Cedric Kelly</td>
                      <td>Senior Javascript Developer</td>
                      <td>Edinburgh</td>
                      <td>22</td>
                      <td>2012/03/29</td>
                      <td>$433,060</td>
                    </tr>
                    <tr onclick="location.href='OrderCencleList.adm'">
                      <td>Airi Satou</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>33</td>
                      <td>2008/11/28</td>
                      <td>$162,700</td>
                    </tr>
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->                
      </div>
              </div>

            <div class="col-lg-6 mb-4">

              <!-- Illustrations -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">제품반품 진행리스트</h6>
                </div>
                <div class="card-body">
  <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
<!--           <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
<!--             <div class="card-header py-3"> -->
<!--               <h6 class="m-0 font-weight-bold text-primary">제품반품 진행리스트</h6> -->
<!--               		<a href="MemberModifyForm.adm"><input type="button" value="멤버수정"></a> -->
<!-- 		<a href="MemberDeletePro.adm"><input type="button" value="멤버삭제"></a> -->
<!--             </div> -->
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Name</th>
                      <th>level</th>
                      <th>address</th>
                      <th>Age</th>
                      <th>Join date</th>
                      <th>Profit</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Name</th>
                      <th>level</th>
                      <th>address</th>
                      <th>Age</th>
                      <th>Join date</th>
                      <th>Profit</th>
                    </tr> 
                  </tfoot>
                  <tbody>
                    <tr> 
                      <td>Tiger Nixon</td>
                      <td>System Architect</td>
                      <td>Edinburgh</td>
                      <td>61</td>
                      <td>2011/04/25</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Garrett Winters</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>63</td>
                      <td>2011/07/25</td>
                      <td>$170,750</td>
                    </tr>
                    <tr>
                      <td>Ashton Cox</td>
                      <td>Junior Technical Author</td>
                      <td>San Francisco</td>
                      <td>66</td>
                      <td>2009/01/12</td>
                      <td>$86,000</td>
                    </tr>
                    <tr>
                      <td>Cedric Kelly</td>
                      <td>Senior Javascript Developer</td>
                      <td>Edinburgh</td>
                      <td>22</td>
                      <td>2012/03/29</td>
                      <td>$433,060</td>
                    </tr>
                    <tr onclick="location.href='OrderRefundList.adm'">
                      <td>Airi Satou</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>33</td>
                      <td>2008/11/28</td>
                      <td>$162,700</td>
                    </tr>
                    
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->                
      </div>
              </div>

            <div class="col-lg-6 mb-4">

              <!-- Illustrations -->
              <div class="card shadow mb-4">
                <div class="card-header py-3">
                  <h6 class="m-0 font-weight-bold text-primary">제품교환 진행리스트</h6>
                </div>
                <div class="card-body">
  <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
<!--           <h1 class="h3 mb-2 text-gray-800">Tables</h1> -->
          <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below. For more information about DataTables, please visit the <a target="_blank" href="https://datatables.net">official DataTables documentation</a>.</p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">
<!--             <div class="card-header py-3"> -->
<!--               <h6 class="m-0 font-weight-bold text-primary">제품교환 진행리스트</h6> -->
<!--               	<a href="MemberModifyForm.adm"><input type="button" value="멤버수정"></a> -->
<!-- 				<a href="MemberDeletePro.adm"><input type="button" value="멤버삭제"></a> -->
<!--             </div> -->
            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>Name</th>
                      <th>level</th>
                      <th>address</th>
                      <th>Age</th>
                      <th>Join date</th>
                      <th>Profit</th>
                    </tr>
                  </thead>
                  <tfoot>
                    <tr>
                      <th>Name</th>
                      <th>level</th>
                      <th>address</th>
                      <th>Age</th>
                      <th>Join date</th>
                      <th>Profit</th>
                    </tr>
                  </tfoot>
                  <tbody>
                    <tr>
                      <td>Tiger Nixon</td>
                      <td>System Architect</td>
                      <td>Edinburgh</td>
                      <td>61</td>
                      <td>2011/04/25</td>
                      <td>$320,800</td>
                    </tr>
                    <tr>
                      <td>Garrett Winters</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>63</td>
                      <td>2011/07/25</td>
                      <td>$170,750</td>
                    </tr>
                    <tr>
                      <td>Ashton Cox</td>
                      <td>Junior Technical Author</td>
                      <td>San Francisco</td>
                      <td>66</td>
                      <td>2009/01/12</td>
                      <td>$86,000</td>
                    </tr>
                    <tr>
                      <td>Cedric Kelly</td>
                      <td>Senior Javascript Developer</td>
                      <td>Edinburgh</td>
                      <td>22</td>
                      <td>2012/03/29</td>
                      <td>$433,060</td>
                    </tr>
                    <tr onclick="location.href='OrderExchangeList.adm'">
                      <td>Airi Satou</td>
                      <td>Accountant</td>
                      <td>Tokyo</td>
                      <td>33</td>
                      <td>2008/11/28</td>
                      <td>$162,700</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
</div>
</div>
</div>
</div>
</div>
</div>


</body>
</html>