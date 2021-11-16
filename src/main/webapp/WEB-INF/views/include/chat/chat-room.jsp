<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel='stylesheet' href="../../../resources/css/include/chat/chat-room.css">
<link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous"/>
</head>
<body>

<div class="room_con">
	<input type="text" id="sender" value="${sessionScope.id}" style="display: none;">
	<div id="back" onclick="closeSocket()"><i class="fas fa-times-circle"></i></div>
	<div class="room_wrap">
		<div class="room_head">
			<img id="room_img" src="../../../resources/image/logo-icon.png">
			<div id="room_title">김현수, 황륜수, 이호준</div>
		</div>
		
		<div class="room_main">
			<div class="chatting_wrap"></div>
			<div class="input_wrap">
				<input type="text" id="input_box" placeholder="내용을 입력하세요" autocomplete="off">
				<div type="button" id="send_btn" onclick="send()"><div><i class="fas fa-paper-plane"></i></div></div>
			</div>
		</div>
	</div>
</div>	   
</body>
<script type="text/javascript" src="../../../resources/js/include/chat/chat-room.js"></script>
</html>