<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
  <head> 
    <title>Bootshop online Shopping cart</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
<!--Less styles -->
   <!-- Other Less css file //different less files has different color scheam
	<link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
	<link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
	-->
	<!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
	<script src="themes/js/less.js" type="text/javascript"></script> -->
	
<!-- Bootstrap style --> 
    <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen"/>
    <link href="themes/css/base.css" rel="stylesheet" media="screen"/>
<!-- Bootstrap style responsive -->	
	<link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
	<link href="themes/css/font-awesome.css" rel="stylesheet" type="text/css">
<!-- Google-code-prettify -->	
	<link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
<!-- fav and touch icons -->
    <link rel="shortcut icon" href="themes/images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="themes/images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="themes/images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
	<style type="text/css" id="enject"></style>
	

<script type="text/javascript">

function javascript(){
    	
        self.close();  
	}


</script>


	
	
  </head>
<body>
<h3 style="background-color: green;"><a style="color: white;">배송 조회</a></h3>



	<table class="table">
        <thead>
          <tr>
            <th>주문 번호</th>
            <th>상품명</th>
            <th>주문수량</th>
            <th>진행상태</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>${orderBean.orderNum}</td>
            <td>${orderBean.bookTitle}</td>
            <td>${orderBean.bookEA}</td>
            <td>${orderBean.orderStatus}</td>
          </tr>
        </tbody>
      </table>
      
      
      	<table class="table" style="table-layout: fixed;">
        <thead>
          <tr>
            <th>주문상세</th>
            <td></td>
          </tr>
          <tr>
           <th style="background-color: #f5f5f5;">받는사람</th>
           <td>${orderBean.u_name2}</td>
          </tr>
          <tr>
           <th style="background-color: #f5f5f5;">전화번호</th>
           <td>${orderBean.phone_num2}</td>
          </tr>
            <tr>
           <th style="background-color: #f5f5f5;">주소</th>
           <td>${orderBean.address2_post}<br>${orderBean.address2_1} ${orderBean.address2_2}</td>
          </tr>
          <tr>
          <td></td>
          <td></td>
          </tr>
          
        </thead>
        <tbody>
          <tr>
          </tr>
        </tbody>
      </table>
      
        
      	<table class="table" style="table-layout: fixed;">
        <thead>
          <tr>
          <td colspan="3"><img src="img/delivery.PNG" style="text-align: center; width: 350px; height: 90px"></td>
          </tr>
          <tr>
            <th class="control-label" style="background-color: #f5f5f5;">처리 일시</th>
             <th class="control-label" style="background-color: #f5f5f5;">현재 위치</th>
              <th class="control-label" style="background-color: #f5f5f5;">상태</th>
          </tr>
          <tr>
           <td>2020-03-31 15:54:3</td>
           <td>경기부천선도	</td>
           <td>집하</td>
          </tr>
          
          <tr>
          <td></td>
          <td></td>
          <td></td>
          </tr>
          
        </thead>
        <tbody>
          <tr>
          </tr>
        </tbody>
      </table>
<!--       	<a class="btn" onclick="javascript()" id="exchange">확인</a> -->
      	<a class="btn" onClick="javascript:javascript()">확인</a>
      
</body>
</html>