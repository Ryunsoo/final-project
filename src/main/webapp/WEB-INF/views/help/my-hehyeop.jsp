<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../../../resources/css/help/my.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
</head>
<body>
	<div id='modal'></div>
	<div class="wrap">
		<%@ include file="/WEB-INF/views/include/head/menu-head.jsp" %>
		<div class="page_name">MY해협</div>
		<div class="my_info">
			<div class="my_name">이호준 <a>님</a>(DIA)</div>
			<div class="left_info">
				<div class="tell">
					<div class="info_title">연락처</div><div class="info_body">01099991111</div>
					<div class="info_btn"><button>수정</button></div>
				</div>
				<div class="info_line"></div>
				<div class="address">
					<div class="info_title">주소</div><div class="info_body">서울시 강남구 KH정보교육원</div>
					<div class="info_btn"><button>수정</button></div>
				</div>
			</div>
			<div class="right_info">
				<div class="email">
					<div class="info_title">이메일</div><div class="info_body">pclass@kh.com</div>
					<div class="info_btn"><button>수정</button></div>
				</div>
				<div class="info_line"></div>
				<div class="region">
					<div class="info_title">지역</div><div class="info_body">서울 강남</div>
					<div class="info_btn"><button>수정</button></div>
				</div>
			</div>		
		</div>
		
		<div class="req_list">
			<table>
				<tr>
					<th>말머리</th>
					<th>지역</th>
					<th>신청일</th>
					<th>받은 견적</th>
					<th>서비스업체</th>
					<th>결제방식</th>
					<th></th>
				</tr>
				<tr>
					<td>[수리 | 보수] 가전</td>
					<td>서울 강남</td>
					<td>2021/10/16</td>
					<td>2</td>
					<td></td>
					<td>캐시</td>
					<td>
					<button class="list_btn">취소</button>
					<button class="list_btn">완료</button>
					</td>
				</tr>
				<tr>
					<td>[수리 | 보수] 싱크대</td>
					<td>서울 강남</td>
					<td>2021/10/14</td>
					<td>5</td>
					<td>싱크내게맡겨</td>
					<td>캐시</td>
					<td><button class="list_btn" onclick="createReviewModal()">후기</button></td>
				</tr>
				<tr>
					<td>[인테리어] 가구조립</td>
					<td>서울 강남</td>
					<td>2021/10/09</td>
					<td>4</td>
					<td></td>
					<td>만나서</td>
					<td><button class="list_btn" onclick="createReviewModal()">후기</button></td>
				</tr>
			</table>	
		</div>

		<div class="breakdown">
			<button class="bk_btn" onclick="location.href='./myHeyeopDetail.html'">상세내역</button>
			<button class="bk_btn" onclick="location.href='./myHeyeopEstimate.html'">견적내역</button>
			<div class="bk_body"></div>
		
		</div>


	</div>
	
<script type="text/javascript">

let createReviewModal = () => {
	let sendModal = initModal('modal', 2);
	appendTitle(sendModal, '서비스 후기', true);
	setButton(sendModal, '확인');
	setContent(sendModal, true, true);
	
	let rating = $('<div>').height('80px')
					.addClass('rating');
	
	//별이 그려지는 div
	for(let i = 1; i < 6; i++) {
		let backDiv = $('<div>').addClass('back_star').width('64px').height('100%').attr('data-star', i * 1);
		let star;
		
		if(i <= 2) {
			star = $('<i class="fas fa-star"></i>');
		}else if(i == 3) {
			star = $('<i class="fas fa-star-half-alt"></i>');
		}
		backDiv.append(star);
		rating.append(backDiv);
	}
	
	//미리 보이는 별 테두리
	let middleRating = $('<div>').height('80px')
						.addClass('middle_rating');
	
	for(let i = 1; i < 6; i++) {
		let middleDiv = $('<div>').addClass('front_star').width('64px').height('100%');
		let star = $('<i class="far fa-star"></i>');
		middleDiv.append(star);
		middleRating.append(middleDiv);
	}
	rating.append(middleRating);
	
	let frontRating = $('<div>').height('80px')
						.addClass('front_rating');
	
	for(let i = 1; i < 11; i++) {
		let frontDiv = $('<div>').addClass('click_div').width('32px').height('100%').attr('data-score', i * 0.5);
		frontRating.append(frontDiv);
	}
	
	rating.append(frontRating);
	
	$('.modal_content').append(rating);
	
	let selectWrap = $('<div>').height('240px').addClass('modal_select_wrapper');
	
	selectWrap.append($('<div><i class="fas fa-check"></i> 친절해요</div>').addClass('select_text'))
				.append($('<div><i class="fas fa-check"></i> 신속해요</div>').addClass('select_text'))
				.append($('<div><i class="fas fa-check"></i> 안전해요</div>').addClass('select_text'))
				.append($('<div><i class="fas fa-check"></i> 전문적이에요</div>').addClass('select_text'))
				.append($('<div><i class="fas fa-check"></i> 시간을 잘 지켜요</div>').addClass('select_text'))
				.append($('<div><i class="fas fa-check"></i> 비용이 합리적이에요</div>').addClass('select_text'))
	
	$('.modal_content').append(selectWrap);
	
	
	modalBlock();
	
	$('.click_div').click(function(e) {
		document.querySelectorAll('.back_star').forEach(el => {
			el.innerHTML = '';
		})
		
		let score = e.target.dataset.score;
		
		document.querySelectorAll('.back_star').forEach(el => {
			let star = el.dataset.star;
			console.dir('score : ' + score);
			console.dir('star : ' + star);
			
			if(star < score) {
				el.innerHTML = '<i class="fas fa-star"></i>';
			}
			
			if(star == (Number(score) + 0.5)) {
				el.innerHTML = '<i class="fas fa-star-half-alt"></i>';
			}
			
			if(star == score) {
				el.innerHTML = '<i class="fas fa-star"></i>';
			}
		})
	})
	
	$('.select_text').click(function(e) {
		if(!$(this).hasClass('selected_review')) {
			$(this).addClass('selected_review');
		}else if($(this).hasClass('selected_review')) {
			$(this).removeClass('selected_review');
		}
	})
	
	$('.modal_left_btn').click(function() {
		modalNone();
	})
}



</script>


</body>
</html>