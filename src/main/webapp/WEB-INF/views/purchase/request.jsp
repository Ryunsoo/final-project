<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head/menu-head.jsp" %>
<link href="${contextPath}/resources/css/include/head/menu_head.css" type="text/css" rel="stylesheet">
<link rel='stylesheet' href="${contextPath}/resources/css/purchase/purchase-request.css">
<link rel='stylesheet' href="${contextPath}/resources/css/include/chat/chat.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">

</head>






<body>
<div id='modal'></div>
	<!-- 섹션 시작 -->
	<div class="section">
		
		<!-- 작성해협 제목 -->
		<div class="title">
			<div id="this-page-name">&nbsp&nbsp신청해협</div>
			
			<!-- 구분선 -->
			<div id="line-wrapper">
				<hr id="seprate-line">
			</div>
			
		</div>
		
		
		
		<!-- 이미지 & 어필해협 -->
		<form class="form-wrapper" action="/purchase/purchase-request" method="get">
			<div class="image-and-appeal-wrapper">
				<div id="uploaded-image">
					<img id="product-image" src="/file/${purchaseInfo.savePath}${purchaseInfo.reName}">
				</div>
				<div class="submit-input-title">*어필해협</div>
				<input id="appeal-help-content" value="${purchaseInfo.content}" readonly/>
			</div>
			
			
			
			<!-- 제품명 ~금액~ 제출까지 -->
			<div class="product-info-submit">
			<br><br>
				<div class="input-content-wrapper">
					<div class="submit-input-title">*제품명</div>
					<input class="text-output" value="${purchaseInfo.itemName}" readonly/>
				</div>
				<br>
				
				<div class="input-content-wrapper">
					<div class="submit-input-title">*거래위치</div>
						<input class="text-output" value="${purchaseInfo.dealLoc}" readonly/>
				</div>
				<br>
				
				<div class="half-size-content-wrapper">
					<div class="input-content-wrapper">
						<div class="submit-input-title">*거래시간</div>
						<input class="text-output" value="${purchaseInfo.dealTime}" style="width:173px;" readonly/>
					</div>
					
					<div class="input-content-wrapper">
						<div class="submit-input-title">*마감시간</div>
						<input class="text-output" value="${purchaseInfo.endTime}" style="width:173px;" readonly/>
					</div>
				</div>
				<br>
				
				
				<div class="input-content-wrapper">
					<div class="submit-input-title">*금액</div>
					<input class="text-output" id="price" value="${purchaseInfo.price}원" readonly/>
				</div>
				<br>
				
				<div class="half-size-content-wrapper">
					<div class="input-content-wrapper">
						<div class="submit-input-title">*남은 개수</div>
						<input id="restCnt" class="text-output" value="${purchaseInfo.restNum}개" readonly/>
					</div>
					
					<div class="input-content-wrapper">
						<div class="submit-input-title">*내가 구매할 개수</div>
						<input id="myBuyCnt" class="text-input myBuyCnt" name="buyNum" type="number" min="0" placeholder="수량을 입력하세요"/>
					</div>
				</div>
				<br>
				
				<div class="half-size-content-wrapper">
					<div class="input-content-wrapper">
						<div class="submit-input-title">*내 포인트</div>
						<input class="text-output" id="myPoint" value="${purchaseInfo.cash}P" readonly/>
					</div>
				</div>
				<br>
				
				<input id="regIdx" name="regIdx" value="${purchaseInfo.regIdx}"style="display: none"/>
				<button type="submit" class="submit-button" id="submit-button">
					SUBMIT!
				</button>
				
				
			</div>
		</form>
		

		
	
	</div>
	<!-- 섹션 끝 -->
	
	
	<!-- 후터 -->
	<div class="hooter">
	</div>




	<%@ include file="/WEB-INF/views/include/chat/chat.jsp" %>
</body>
<script type="text/javascript" src="${contextPath}/resources/js/include/chat/chat.js"></script>
<script type="text/javascript" src="../../../resources/js/purchase/request.js"></script>
</html>