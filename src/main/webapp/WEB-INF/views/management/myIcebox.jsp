<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head/main-head.jsp" %>
<link href="../../../resources/css/include/head/menu_head.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>myIcebox</title>
<style type="text/css">
/* nav,div,ul,li,a,span {
	border: solid thin lightgray;
	display: flex;
}    */

.hidden {
	display: none;
}

#bg {
	position: fixed;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	background-color: rgb(0, 0, 0, 0.5);
	z-index: 1;
}

html, body {
    width: 100%;
    height: 100%;
    min-width: 1130px;
    display: flex;
    justify-content: center;
}

.wrap {
    width: 1130px;
    height: 100%;
}

.main{
	display: flex;
}

.btn{
	display: flex;
	flex-direction: column;
	margin-top: 85px;
	flex-shrink: 0;
	position: absolute;
}

.icebox_btn{
	border: none;
	width: 180px;
	height: 90px;
	background-color: rgba(52, 73, 94, 0.33);
	border-top-right-radius: 20px;
	border-bottom-right-radius: 20px;
	font-size: 23px;
	color: white;
	font-weight: bold;
	box-shadow: 3px 3px 3px #333;
	text-shadow: 2px 2px 2px #333;
	cursor: pointer;
}

.icebox_btn:hover{
	color: #fff;
  	background-color: #cca552;
  	border-color: #bf9b4d;
}

.house_btn{
	border: none;
	width: 150px;
	height: 80px;
	background-color: rgba(52, 73, 94, 0.98);
	border-top-right-radius: 20px;
	border-bottom-right-radius: 20px;
	font-size: 20px;
	color: white;
	font-weight: bold;
	margin-top: 30px;
	box-shadow: 3px 3px 3px #333;
	text-shadow: 2px 2px 2px #333;
	cursor: pointer;
}

.house_btn:hover{
  color: #fff;
  background-color: #cca552;
  border-color: #bf9b4d;
  
  
}

.icebox{
	margin-left: 10px;
}

.postIt{
	width: 140px;
	height: 140px;
	position: absolute;
	background-color: rgb(217, 232, 103);
	margin-top: 320px;
	margin-left: 360px;
	text-align: center;
	font-size: 30px;
	font-weight: bold;
	box-shadow: 3px 3px 3px #333;
	cursor: pointer;
}

.click{
	margin-top: 55px;
}

.postIt:hover{
	color: white;
    background-color: rgb(87, 153, 167);
	border-color: rgb(87, 153, 167);
	box-shadow: 0 0 0 0.25rem rgba(211, 212, 213, 0.5);
}


.tape{
	width: 27px;
	height: 60px;
	position: absolute;
	background-color: rgb(211, 84, 0);
	margin-left: 425px;
	margin-top: 290px;
	transform:rotate(20deg);
}

.bell{
	position: absolute;
	font-size: 60px;
	color: #e6bc14;
	margin-left: 1000px;
	margin-top: 30px;
	z-index: 2;
	cursor: pointer;
}

.bell_cnt{
	width: 30px;
	height: 30px;
	position: absolute;
	color: white;
	background-color: red;
	border-radius: 100%;
	font-size: 20px;
	text-align: center;
	margin-top: 10px;
	right: 26px;
	line-height: 1.4;
}

.bell_cnt:hover{
	color: white;
    background-color: rgb(87, 153, 167);
	border-color: rgb(87, 153, 167);
	box-shadow: 0 0 0 0.25rem rgba(211, 212, 213, 0.5);
}


#popup{
	width: 440px;
	height: 540px;
	position: absolute;
	background-color: rgb(250, 250, 210);
	margin-top: 100px;
	margin-left: 560px;
	text-align: center;
	font-size: 30px;
	font-weight: bold;
	box-shadow: 3px 3px 3px #333;
	overflow: auto;
	z-index: 2;
}

.bell_title{
	display: flex;
	margin-left: 30px;
	margin-top: 25px;
	font-size: 22px;
	padding-bottom: 10px;
}
.no_read{
	margin-left: 7px;
	font-size: 18px;
	margin-top: 3px;
}

.cnt{
	margin-left: 10px;
	font-size: 18px;
	margin-top: 3px;
	color: rgb(41, 128, 185);
}

.total_cnt{
	font-size: 18px;
	margin-top: 3px;
}

.bell_txt_wrap{
	display: flex;
	flex-direction: column;
	border-bottom: solid thin darkgray;
	width:380px;
	margin: auto;
	margin-bottom: 20px;
	margin-top: 20px;
}

.bell_txt{
	font-weight: bold;
	font-size: 18px;
	margin-left: 10px;
	margin-bottom: 7px;
	display: flex;
}

.bell_title_wrap{
	display: flex;
}

.bell_cancel{
	margin-top: 20px;
	margin-left: 150px;
	font-size: 30px;
	cursor: pointer;
}

.bell_cancel:hover{
	color: red;
}

.cart{
	position: absolute;
	font-size: 60px;
	margin-left: 990px;
	margin-top: 120px;
	cursor: pointer;
}

.cart_cnt{
	width: 30px;
	height: 30px;
	position: absolute;
	color: white;
	background-color: green;
	border-radius: 100%;
	font-size: 20px;
	text-align: center;
	margin-top: 10px;
	margin-right: 10px;
	line-height: 1.4;
}

.cart_cnt:hover{
	color: white;
    background-color: rgb(87, 153, 167);
	border-color: rgb(87, 153, 167);
	box-shadow: 0 0 0 0.25rem rgba(211, 212, 213, 0.5);
}

</style>
</head>
<body>
<div id="bg" class="hidden"></div>
	<div class="wrap">
	<%@ include file="/WEB-INF/views/include/head/menu-head.jsp" %>
		<div class="main">
			<div class="btn">
				<button class="icebox_btn" onclick="javascript:location.href='myIcebox.html'">
					나의 냉장고
				</button>
				<button class="house_btn" onclick="javascript:location.href='myIcebox5.html'">
					나의 가계부
				</button>
			</div>
			
			<div class="icebox">
				<img src="../../../resources/image/icebox.png">
			</div>
				
			<div class="postIt" onclick="javascript:location.href='myIcebox2.html'">
				<div class="click">CLICK!</div>
			</div>
			<div class="tape"></div>
			<div class="bell" id="go">
				<div class="bell_cnt">3</div>
				<i class="fas fa-bell"></i>
			</div>
			
			<div class="cart" onclick="javascript:location.href='myIcebox4.html'">
				<div class="cart_cnt">3</div>
			<i class="fas fa-shopping-cart"></i>
			</div>
			
			<div class="hidden" id="popup">
				<div class="bell_title_wrap">
					<div class="bell_title">
						<div>알림</div>
						<div class="no_read">(읽지않음</div>
						<div class="cnt">9</div>
						<div class="total_cnt">/15)</div>
					</div>
					<div class="bell_cancel" id="exit"><i class="fas fa-times-circle"></i></div>
				</div>
				
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
				<div class="bell_txt_wrap">
					<div class="bell_txt">- 삼겹살의 유통기한이 <div style="color:rgb(192, 57, 43); ">&nbsp3일</div>&nbsp남았습니다.</div>
				</div>
			</div>
		</div>
	</div>



</body>
<script>
        const go = document.querySelector("#go");
        const bg = document.querySelector("#bg");
        const popup = document.querySelector("#popup");
        const exit = document.querySelector("#exit");

        go.addEventListener("click",function(){
            bg.classList.remove("hidden");
            popup.classList.remove("hidden");
        });
        exit.addEventListener("click",function(){
            bg.classList.add("hidden");
            popup.classList.add("hidden");
        });
    </script>
</html>
