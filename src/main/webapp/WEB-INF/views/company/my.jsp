<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head/company-head.jsp" %>
<link rel="stylesheet" href="${contextPath}/resources/css/company/all-help.css">
<link rel='stylesheet' href="../../../resources/css/include/chat/chat.css">
</head>
<style>
.alertMessage {
	text-align: center;
	font-size: 20px;
	font-weight: bold;
}
</style>
<script type="text/javascript">
function showResult(msg){
	let modal = initModal('modal', 3);
	appendTitle(modal, '');
	setButton(modal, '닫기');
	setContent(modal, true, true);
	//addPiggyBackround(sendModal);
	modalBlock();
	
	let modalBody = $('<div class="alertMessage">'+msg+'</div><br>')
	.addClass('send_modal_content');
	
	$('.modal_content').append(modalBody);
	
	$('.modal_left_btn').click(function() {
		modalNone();
	})
}	
</script>
<body>
<div id='modal'></div>
<%@ include file="/WEB-INF/views/include/chat/chat2.jsp" %>
	<div class='main'>
	
		<!-- 사이트 메뉴(조회해협 & MY 해협) -->
		<!-- <aside> -->
		<div class='aside'>
			<button class="help_list" onclick="location.href='/company/main'">조회 해협</button>
			<button class="my_help" onclick="location.href='/company/my'">MY 해협</button>
		</div>
		<!-- </aside> -->
		<div class='section'>
			<div class='wrapper'>
				<div class='select_wrapper'>
					<div class='sub_menu'>
						<!-- 현재 선택된 메뉴에 selected class를 추가해주면 자동으로 css 바뀝니다!! -->
						
						<c:if test="${empty param.state || param.state eq 0}">
							<div><button id="await" class="selected" onclick="moveTab(0)">대기내역</button></div>
							<div><button id="going" onclick="moveTab(1)">진행내역</button></div>
							<div><button id="cancel" onclick="moveTab(3)">취소내역</button></div>
							<div><button id="complete" onclick="moveTab(2)">완료내역</button></div>
						</c:if>
						<c:if test="${param.state eq 1}">
							<div><button id="await" onclick="moveTab(0)">대기내역</button></div>
							<div><button id="going" class="selected" onclick="moveTab(1)">진행내역</button></div>
							<div><button id="cancel" onclick="moveTab(3)">취소내역</button></div>
							<div><button id="complete" onclick="moveTab(2)">완료내역</button></div>
						</c:if>
						<c:if test="${param.state eq 2}">
							<div><button id="await" onclick="moveTab(0)">대기내역</button></div>
							<div><button id="going" onclick="moveTab(1)">진행내역</button></div>
							<div><button id="cancel" onclick="moveTab(3)">취소내역</button></div>
							<div><button id="complete" class="selected" onclick="moveTab(2)">완료내역</button></div>
						</c:if>
						<c:if test="${param.state eq 3}">
							<div><button id="await" onclick="moveTab(0)">대기내역</button></div>
							<div><button id="going" onclick="moveTab(1)">진행내역</button></div>
							<div><button id="cancel" class="selected" onclick="moveTab(3)">취소내역</button></div>
							<div><button id="complete" onclick="moveTab(2)">완료내역</button></div>
						</c:if>
						
					</div>
				</div>
				
				<div class='wrap_list'>
					<div class='list'>
						<table>
							<thead>
								<tr>
									<th>신청 분야</th>
									<th>지역</th>
									<th>방문날짜</th>
									<th>처리구분</th>
									<th>신청자명</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="requestList" items="${requestList}">
									<tr>
										<td onclick="viewDetail(${requestList.reqIdx})">${requestList.field}</td>
										<td onclick="viewDetail(${requestList.reqIdx})">${requestList.oldAddress}</td>
										<td onclick="viewDetail(${requestList.reqIdx})">${requestList.reqTime}</td>
										<td>
										<c:choose>
											<c:when test="${requestList.status == 0}">
												요청 대기 중
											</c:when>										
											<c:when test="${requestList.status == 1}">
												<button class="list_btn_green" id="completeBtn" onclick="completeService(${requestList.reqIdx})">완료</button>
                           						<button class="list_btn_red" id="cancelBtn" onclick="cancelService(${requestList.reqIdx})">취소</button>
											</c:when>
											<c:when test="${requestList.status == 2}">
												완료 대기 중
											</c:when>
											<c:when test="${requestList.status == 3}">
												완료됨
											</c:when>
											<c:when test="${requestList.status == 4}">
												취소 대기 중
											</c:when>
											<c:otherwise>
												취소됨
											</c:otherwise>
										</c:choose>
										</td>
										<td onclick="viewDetail(${requestList.reqIdx})">${requestList.reqName}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="page">
						<div class="pg_wrap" style="display: block; text-align: center;">		
							<c:if test="${paging.startPage != 1 }">
								<a class="pg_start" href="/company/my?nowPage=1&cntPerPage=${paging.cntPerPage}&state=${state}">&lt;&lt;</a>
								<a class="pg_prev" href="/company/my?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}&state=${state}">&lt;</a>
							</c:if>
							<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
								<c:choose>
									<c:when test="${p == paging.nowPage }">
										<a class="pg_current">${p }</a>
									</c:when>
									<c:when test="${p != paging.nowPage }">
										<a class="pg_page" href="/company/my?nowPage=${p }&cntPerPage=${paging.cntPerPage}&state=${state}">${p }</a>
									</c:when>
								</c:choose>
							</c:forEach>
							<c:if test="${paging.endPage != paging.lastPage}">
								<a class="pg_next" href="/company/my?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}&state=${state}">&gt;</a>
								<a class="pg_end" href="/company/my?nowPage=${paging.lastPage}&cntPerPage=${paging.cntPerPage}&state=${state}">&gt;&gt;</a>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${not empty message}">
		<script>showResult('${message}')</script>
	</c:if>
	<div class='back'></div>
	<!-- 자취해협 정보 footer -->
	<%@ include file="/WEB-INF/views/include/footer/footer.jsp" %>
	
	<script type="text/javascript" src="../../../resources/js/include/chat/chat2.js"></script>
	<script type="text/javascript" src="../../../resources/js/company/my.js"></script>
</body>
</html>