<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../../../resources/css/include/head/menu_head.css" type="text/css" rel="stylesheet">
<link href="../../../resources/css/help/my.css" type="text/css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<meta charset="UTF-8">
<style>
.alertMessage {
	text-align: center;
	font-size: 20px;
	font-weight: bold;
}
</style>
<script type="text/javascript">
function alertMessage(msg){
	let modal = initModal('modal', 3);
	appendTitle(modal, '');
	setButton(modal, '닫기');
	setContent(modal, true, true);
	modalBlock();
	
	let modalBody = $('<div class="alertMessage">'+msg+'</div><br>')
	.addClass('send_modal_content');
	
	$('.modal_content').append(modalBody);
	
	$('.modal_left_btn').click(function() {
		modalNone();
	})
}
</script>
</head>
<body>
	<div id='modal'></div>
	<div class="wrap">
		<%@ include file="/WEB-INF/views/include/head/menu-head.jsp" %>
		<div class="page_name">MY해협</div>
		<div class="my_info">
			<div class="my_name">${authentication.name} <a>님</a></div>
			<div class="left_info">
				<div class="tell">
					<div class="info_title">연락처</div><div class="info_body">${authentication.tell}</div>
				</div>
				<div class="info_line"></div>
				<div class="address">
					<div class="info_title">주소</div><div class="info_body">${authentication.address}</div>
				</div>
			</div>
			<div class="right_info">
				<div class="email">
					<div class="info_title">이메일</div><div class="info_body">${authentication.email}</div>
				</div>
				<div class="info_line"></div>
				<div class="region">
					<div class="info_title">지역</div><div class="info_body">${authentication.oldAddress}</div>
				</div>
			</div>		
		</div>
		
		<div class="req_list">
			<select class='state_filter' onchange="filtering()">
				<option selected disabled>진행 여부별 검색</option>
				<option value="대기중">대기중</option>
				<option value="진행중">진행중</option>
				<option value="완료">완료</option>
				<option value="취소">취소</option>
				<option value="all">전체</option>
			</select>
			<table>
				<tr>
					<th style="width: 250px">말머리</th>
					<th style="width: 110px">지역</th>
					<th style="width: 132px">신청일</th>
					<th style="width: 110px">받은 견적</th>
					<th style="width: 160px">서비스업체</th>
					<th style="width: 102px">결제방식</th>
					<th style="width: 170px"></th>
				</tr>
				<tbody class='help_list'>
				<c:forEach items='${helpList}' var='help'>
					<tr>

						<td onclick="showDetail(${help.reqIdx},${help.state})">${help.field}</td>
						<td onclick="showDetail(${help.reqIdx},${help.state})">${help.area}</td>
						<td onclick="showDetail(${help.reqIdx},${help.state})">${help.regDate}</td>
						<td onclick="showDetail(${help.reqIdx},${help.state})">${help.estimateCnt}</td>
						<td onclick="showDetail(${help.reqIdx},${help.state})">
							<c:if test="${not empty help.company}">
									<c:choose>
										<c:when test="${help.grade eq 'BRONZE'}">
											<span style="color: #cc9900"><i class="fas fa-medal"></i></span>
										</c:when>
										<c:when test="${help.grade eq 'SILVER'}">
											<span style="color: silver"><i class="fas fa-medal"></i></span>
										</c:when>
										<c:when test="${help.grade eq 'GOLD'}">
											<span style="color: gold"><i class="fas fa-medal"></i></span>
										</c:when>
										<c:when test="${help.grade eq 'DIA'}">
											<span style="color: silver"><i class="fas fa-gem"></i></span>
										</c:when>
									</c:choose>
							</c:if>
							 ${help.company}
						</td>
						<td onclick="showDetail(${help.reqIdx},${help.state})">${help.payMeans}</td> 
						<td>
							<c:choose>
								<c:when test="${help.state == 1}">
									<button class="list_btn_red" id="delete" onclick="deleteHelp(${help.reqIdx})">삭제</button>
                           			<button class="list_btn_green" id="refresh" onclick="refreshHelp(${help.reqIdx})">끌올</button>
								</c:when>
								<c:when test="${help.state == 2}">
									<button class="list_btn_green" id="complete" onclick="completeHelp(${help.reqIdx})">완료</button>
									<button class="list_btn_red" id="cancel" onclick="cancelHelp(${help.reqIdx})">취소</button>
								</c:when>
								<c:when test="${help.state == 3}">
									완료 대기 중
								</c:when>
								<c:when test="${help.state == 4}">
									<button class="list_btn_green" onclick="createReviewModal(${help.reqIdx})">후기</button>
								</c:when>
								<c:when test="${help.state == 5}">
									★ ${help.score}
								</c:when>
								<c:when test="${help.state == 6}">
									취소 대기 중
								</c:when>
								<c:otherwise>
									진행취소 완료
								</c:otherwise>
							</c:choose>
						</td>
						<input type="hidden" class="reqIdx" value="${help.reqIdx}">
					</tr>
				</c:forEach>
				</tbody>
			</table>
			<div class='page'>
				<i class="fas fa-caret-left" onclick="getList('${paging.url}?page=${paging.prev}')"></i>
				<div>
					<c:forEach var="i" begin="${paging.blockStart}" step="1" end="${paging.blockEnd}">
						<span onclick="getList('${paging.url}?page=${i}')"
							<c:if test="${i == 1}">class='selected'</c:if>
						>${i}</span>
					</c:forEach>
				</div>
				<i class="fas fa-caret-right" onclick="getList('${paging.url}?page=${paging.next}')"></i>
			</div>	
		</div>
	  <input type="hidden" class="saveReqIdx">
	  <input type="hidden" class="saveHelpState">	
      <div class="breakdown">
         <button class="bk_btn" id="detailBtn" onclick="detail()">상세내역</button>
         <button class="bk_btn" id="estimateBtn" onclick="estimate()">견적내역</button>
         <div class="bk_body">
         <%@ include file="/WEB-INF/views/help/my-hehyeop-detail.jsp" %>
         <%@ include file="/WEB-INF/views/help/my-hehyeop-estimate.jsp" %>
         </div>
      </div>
	<c:if test="${not empty message}">
		<script>alertMessage('${message}')</script>
	</c:if>
   </div>

<%@ include file="/WEB-INF/views/include/chat/chat.jsp" %>
</body>
<link rel='stylesheet' href="../../../resources/css/include/chat/chat.css">
<script type="text/javascript" src="../../../resources/js/help/help.js"></script>
<script type="text/javascript" src="../../../resources/js/urlEncoder.js"></script>
<script type="text/javascript" src="../../../resources/js/help/star_modal.js"></script>
<script type="text/javascript" src="../../../resources/js/include/chat/chat.js"></script>
</html>