<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Bookshop online Shopping cart</title>
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

function checkPasswd(passwd) {
	// 8 ~ 16자리 패스워드 영문,숫자,특수문자 조합 유효성 검사
	// 1. 정규표현식 지정
	// 1) 길이 체크 : 8 ~ 16자리. 영문 대문자&소문자&숫자&특수문자(!@#$%^_)
	var lengthRegex = /^[A-Za-z0-9!@#$%^_]{8,16}$/;
	// 2) 대문자 체크
	var upperCaseRegex = /[A-Z]/;
	// 3) 소문자 체크
	var lowerCaseRegex = /[a-z]/;
	// 4) 숫자 체크
	var digitRegex = /[0-9]/;
	// 5) 특수문자 체크
	var specCharRegex = /[!@#$%^_]/;
	
//		// 2. 체크 후 메세지 표시할 공간의 태그 id 값 가져오기
	var element = document.getElementById('checkPasswdResult'); // checkPasswdResult 값을 ID 로 갖는 태그 찾기
	
//		// 3. 정규표현식을 통한 유효성 검사 수행(정규표현식 저장 변수명.exec() 를 사용)
//		// 함수 호출 시 전달받은 파라미터(id) 의 값을 정규표현식으로 검사
	// 길이, 영문 대/소문자, 숫자, 특수문자 체크를 모두 통과했을 경우
	if(lengthRegex.exec(passwd.value) && 
		(upperCaseRegex.exec(passwd.value) || lowerCaseRegex.exec(passwd.value))
		&& digitRegex.exec(passwd.value) && specCharRegex.exec(passwd.value)) {
		element.innerHTML = "<b style='font-size:13px;color:green'>사용 가능한 패스워드입니다</b>";
		$("#pwcheck").val(1);
	} else { // 유효성 검사를 통과하지 못했을 경우
		element.innerHTML = "<b style='font-size:13px;color:red'>8 ~ 16자의 영문, 숫자, 특수문자를 사용하세요</b>";
		$("#pwcheck").val(0);
	}
}
// 회원가입 submit() 전 체크 - 필수사항 체크
function checkSubmit() {
	
	var pw = parseInt($("#pwcheck").val());
	var pw1 = $("#pw").val();
	var pw2 = $("#pw2").val();
	var name = $("#u_name").val();
	var post = $("#address_post").val();
	var address_1 = $("#address_1").val();
	var address_2 = $("#address_2").val();
	var phone_num = $("#phone_num").val();
	var email = $("#email").val();

	// pw가 사용가능하지 않을 경우
	if(pw != 1){
		document.fr.pw.focus();
		return false;
	}
 	// pw재확인이 다를 경우
	if(pw1 != pw2){
		document.fr.pw2.focus();
		$('#checkPasswdResult2').html('<b style="font-size:13px;color:red">비밀번호가 일치하지 않습니다</b>');
		return false;
	}
 	if(name == ''){
 		document.fr.u_name.focus();
 		$('#checkNameResult').html('<b style="font-size:13px;color:red">필수정보입니다</b>');
		return false;
 	}
 	if(post == '' || address_1 == '' || address_2 == ''){
 		$('#checkAddressResult').html('<b style="font-size:13px;color:red">필수정보입니다</b>');
 		return false;
 	} 
 	if(phone_num == ''){
 		document.fr.phone_num.focus();
 		$('#checkPhoneResult').html('<b style="font-size:13px;color:red">필수정보입니다</b>');
		return false;
 	} 
 	if(email == ''){
 		document.fr.email.focus();
 		$('#checkEmailResult').html('<b style="font-size:13px;color:red">필수정보입니다</b>');
		return false;
 	}
 	document.fr.submit();
}

function check() {
	var pw1 = $("#pw").val();
	var pw2 = $("#pw2").val();
	if(pw1 == pw2){
		$('#checkPasswdResult2').html('<b style="font-size:13px;color:green">비밀번호가 일치합니다</b>');
	} else {
		$('#checkPasswdResult2').html('<b style="font-size:13px;color:red">비밀번호가 일치하지 않습니다</b>');
	}
	$('#checkNameResult').html('');
	$('#checkAddressResult').html('');
	$('#checkPhoneResult').html('');
	$('#checkEmailResult').html('');
}
</script>
<!-- 주소 찾기 -->
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function address_search() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var roadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 참고 항목 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('address_post').value = data.zonecode;
                
                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                if(roadAddr !== ''){
	                document.getElementById("address_1").value = roadAddr + ' ' + extraRoadAddr;
                } else {
	                document.getElementById("address_1").value = roadAddr;
                }
            }
        }).open();
    }
</script>
  </head>
<body>

<jsp:include page="/member/top.jsp"></jsp:include>

<!-- Header End====================================================================== -->
<div id="mainBody">
	<div class="container">
	<div class="row">
<!-- Sidebar ================================================== -->
	<jsp:include page="/member/left.jsp"></jsp:include>
<!-- Sidebar end=============================================== -->
	<div class="span9">
    <ul class="breadcrumb">
		<li><a href="OrderList.mo">마이페이지</a> <span class="divider">/</span></li>
		<li class="active">내정보수정</li>
    </ul>
	<h3>내정보수정</h3>	
	<div class="well">
	<form class="form-horizontal" action="ModifyProAction.me?uID=${memberBean.uID}" method="post" name="fr">
		<div class="control-group">
		<div class="controls">
		</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="inputId">아이디</label>
			<div class="controls">
			  <input type="text" id="inputId" value="${memberBean.uID}" name="uID" required="required" readonly="readonly">
			  <span id="checkIdResult"></span>
			</div>
		 </div>
		 <div class="control-group">
			<label class="control-label" for="inputPassword">비밀번호 변경 <sup>*</sup></label>
			<div class="controls">
			  <input type="password" id="pw" name="pw" onkeyup="checkPasswd(this)"><br>
			  <input type="hidden" id="pwcheck" value="0">
			  <span id="checkPasswdResult"></span>
			</div>
		 </div>
		 <div class="control-group">
			<label class="control-label" for="inputPassword">비밀번호 재확인 <sup>*</sup></label>
			<div class="controls">
			  <input type="password" id="pw2" name="pw2" onkeyup="check()"><br>
			  <span id="checkPasswdResult2"></span>
			</div>
		 </div>
		 <div class="control-group">
			<label class="control-label" for="inputName">이름 </label>
			<div class="controls">
			  <input type="text" id="inputName" value="${memberBean.u_name}" name="u_name" readonly="readonly">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="address">주소 <sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="address_post" name="address_post" value="${memberBean.address_post }" readonly="readonly" style="width: 200px;"/>
			  <input type="button" class="btn" name="address_btn" value="주소검색" onclick="address_search()"><br><br>
			  <input type="text" id="address_1" name="address_1" value="${memberBean.address_1 }" readonly="readonly" style="width: 400px;"/><br><br>
			  <input type="text" id="address_2" name="address_2" value="${memberBean.address_2 }" placeholder="상세주소를 입력해주세요" onkeyup="check()" style="width: 400px;"/><br>
			  <span id="checkAddressResult"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="mobile">휴대전화 <sup>*</sup></label>
			<div class="controls">
			  <input type="text" id="phone_num" name="phone_num" value="${memberBean.phone_num }" onkeyup="check()"/><br>
			  <span id="checkPhoneResult"></span>
			</div>
		</div>		
<%! public int getRandom(){
	int random = 0;
	random = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
	return random;} %>
		<div class="control-group">
		<label class="control-label" for="input_Email">Email <sup>*</sup></label>
		<div class="controls">
		  <input type="email" id="email" placeholder="Email" name="email" value="${memberBean.email }" onkeyup="check()">
		  <input id = "emailCheck" type="button" name="emailCheck" class="btn"  value="인증번호발송">
		  <input type="hidden" readonly="readonly" name="code_check" id="code_check" value="<%=getRandom()%>" />
		  <span id="checkEmailResult"></span>
		</div>
	  </div>	  	
	
	<div class="control-group">
			<div class="controls">
				<input type="hidden" name="email_create" value="1">
				<input type="hidden" name="is_new_customer" value="1">
				<input id="btnJoin" class="btn btn-large btn-success" type="button" value="정보수정" onclick="checkSubmit()"/>
			</div>
		</div>		
	</form>
</div>

</div>
</div>
</div>
</div>
<!-- MainBody End ============================= -->
<!-- Footer ================================================================== -->
	 <div  id="footerSection">
    <div class="container">
        <div class="row">
            <div class="span3">
                <h5><a href="NoticeList.bo">공지사항/이벤트</a></h5>
             </div>
            <div class="span3">
                <h5><a href="FAQList.bo">고객센터</a></h5>
             </div>
            <div class="span3">
                <h5>회사이름</h5>
                FAX: 02-0000-0000 <br>
                E-mail: xxx@xxx.xxx<br>  
                부산시 OO구 OO로 00 <a href="#">약도</a>  
             </div>
            <div id="socialMedia" class="span3 pull-right">
                <h5>SOCIAL MEDIA </h5>
                <a href="#"><img width="60" height="60" src="themes/images/facebook.png" title="facebook" alt="facebook"/></a>
                <a href="#"><img width="60" height="60" src="themes/images/twitter.png" title="twitter" alt="twitter"/></a>
                <a href="#"><img width="60" height="60" src="themes/images/youtube.png" title="youtube" alt="youtube"/></a>
             </div> 
         </div>
        <p class="pull-right">&copy; BookShop</p>
    </div><!-- Container End -->
    </div>
<!-- Placed at the end of the document so the pages load faster ============================================= -->
    <script src="themes/js/jquery.js" type="text/javascript"></script>
    <script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="themes/js/google-code-prettify/prettify.js"></script>
    
    <script src="themes/js/bootshop.js"></script>
    <script src="themes/js/jquery.lightbox-0.5.js"></script>
</body>
</html>



