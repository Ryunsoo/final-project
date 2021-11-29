<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/resources/css/include/head/menu_head.css">
<link rel="stylesheet" href="${contextPath}/resources/css/management/myAccountList.css">
<link rel='stylesheet' href="${contextPath}/resources/css/include/chat/chat.css">
</head>
<body>
	<div id="bg" class="hidden"></div>
	<div class="wrap">
		<%@ include file="/WEB-INF/views/include/head/menu-head.jsp"%>
		<div class="main">
			<div class="btn">
				<button class="icebox_btn"
					onclick="javascript:location.href='myIcebox'">나의 냉장고
				</button>
				<button class="house_btn" onclick="javascript:location.href='myAccountBook'">나의 가계부</button>
				<div class="scheduler" onclick="javascript:location.href='myAccountBook'">스케쥴러</div>
				<div class="my_statistics" onclick="javascript:location.href='myAccountList'">My 통계</div>
			</div>
			<div class="personal_list_wrap">
				<div class="personal_list_title">개인 지출 내역</div>
				<div class="personal_search_wrap">
					<select class="personal_category">
						<option selected disabled>&nbsp&nbsp지출 구분</option>
						<option>&nbsp&nbsp식비</option>
						<option>&nbsp&nbsp생활비</option>
						<option>&nbsp&nbsp기타</option>
					</select>
					<div class="line"></div>
					<label class="year"><input type="radio" class="year_radio" name="choice" id="choice" value="1">연간 통계</label> 
					<label class="month"><input type="radio" class="month_radio" name="choice" id="choice" value="1">월간 통계</label> 
					<select
						class="personal_category">
						<option selected disabled>&nbsp&nbsp기간 (년/월) 구분</option>
						<option>&nbsp&nbsp일</option>
						<option>&nbsp&nbsp월</option>
						<option>&nbsp&nbsp년</option>
					</select>
					<button class="search_btn">조회</button>
				</div>
				<div class='personal_paper' id='list'>
					<div class='personal_txt'>
						<label id='idx'>No</label> 
						<label id='cate'>구분</label> 
						<label id='content'>내용</label> 
						<label id='money'>금액</label>
						<label id='date'>날짜</label>
						<label id='tatal_pay'>누적금액</label>
					</div>
					<c:forEach items="${ExpenseList}" var="el" varStatus="status">
						<div class='personal_content'>
							<label id='idx'>${status.count}</label> 
							<label id='cate'>${el.category}</label> 
							<label id='content'>${el.content}</label> 
							<label id='money'>${el.comPrice}</label>
							<label id='date'>${el.expDate}</label>
							<label id='tatal_pay'>18,000</label>
						</div>
					</c:forEach>
					
				</div>
				<div class="personal_total">
						조회하신 내역의 합계는<div style="color: orange;">&nbsp98,000원&nbsp</div>을 사용했습니다.
					</div>
			</div>
			<div class="list_line"></div>
			<div class="fixed_list_wrap">
				<div class="fixed_list_title">고정 지출 내역</div>
				<div class='fixed_paper' id='list'>
					<div class='fixed_txt'>
						<label id='idx'>No</label> 
						<label id='cate'>내역</label> 
						<label id='content'>금액</label> 
						<label id='money'>고정주기</label>
						<label id='date'>시작 일자</label>
						<label id='tatal_pay'>마감일자</label>
					</div>
					<c:forEach items="${FExpenseList}" var="fl" varStatus="status">
						<div class='fixed_content'>
							<label id='idx'>${status.count}</label> 
							<label id='cate'>${fl.content}</label> 
							<label id='content'>${fl.comPrice}</label> 
							<label id='content'>${fl.days}${fl.cycle}</label> 
							<label id='date'>${fl.startDate}</label>
							<label id='tatal_pay'>${fl.endDate}</label>
						</div>
					</c:forEach>
					<div class="fixed_total">
						고정지출의 합계는<div style="color: orange;">&nbsp${sumPrice}원&nbsp</div>입니다.
					</div>
				
				</div>
			</div>

		</div>
		
		<footer style="height: 200px;">
		
		</footer>
	</div>
	<%@ include file="/WEB-INF/views/include/chat/chat.jsp" %>
</body>
<script type="text/javascript" src="${contextPath}/resources/js/include/chat/chat.js"></script>
</html>
