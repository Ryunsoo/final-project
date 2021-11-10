<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head/main-head.jsp" %>
<link href="../../../resources/css/mypage/mypage_company_css.css" type="text/css" rel="stylesheet">
<link href="../../../resources/css/mypage/bootstrap.css" type="text/css" rel="stylesheet">
<link href="../../../resources/css/mypage/modal.css" type="text/css" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/4.12.0/d3.js" integrity="sha512-SuXpPdajLF/GkLBHndpO/A05M1yY4UXJjeeYSbuXRat6E2AUmnG5CVQ0xPtI7IxfXjRmAHoOuOsCqd8yoPup+g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.js" integrity="sha512-11Z4MD9csmC3vH8Vd0eIPJBQu3uEHEqeznWEt3sLBCdQx3zm9mJbBcJH8WTcyGY9EXDE81BNpjE2vLosPK8cFQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<link href="../../../resources/css/reset.css" type="text/css" rel="stylesheet">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id='modal'></div>
<div id="bg" class="hidden"></div>
<div class="wrap">
	<%@ include file="/WEB-INF/views/include/head/menu-head.jsp" %>
	<div class="main">
		<div class="box1">
			<img src="../../../resources/image/돼지저금통.png" class="pig">
			<div id="infoicon"><i class="fas fa-clipboard-user"></i></div>
			<div id="company_user">업체 회원</div>
			<div class="myinfo">
				<div id="name">
					<div id="nameicon"><i class="far fa-user"></i></div>
					<div id="user_name">권구현님&nbsp(GuHyun)</div>
					<div class="btn-info_wrap">
						<button type="button" class="btn btn-info open" id="myInfo_btn">내정보&nbsp<i class="fas fa-chevron-down"></i></button>
						<button type="button" class="btn btn-info hidden" id="modifyInfo_btn" style="background-color: rgb(246, 199, 124); ">정보수정</button>
						<button type="button" class="btn btn-info hidden" id="delete_user_btn" style="background-color: rgb(244, 166, 159); ">회원탈퇴</button>
					
					</div>
					<div><button type="button" class="btn btn-info" id="namebtn2" onclick="changeNomal()">일반전환</button></div>
				</div>
				<div id="grade">
					<div id="gold">GOLD</div>
					<div id="gradetext">회원</div>
					<div id="gradeicon" type="button">
						<i class="fas fa-question-circle"></i>
						<div class="balloon">
							등급선정 기준
							<br>
							<br>
							1. 해협매칭 완료 후 거래완료 +3
							<br>
							2. 서비스 후 평점에 따라 추가 점수 획득
							<br>
							<br>
							※ BRONZE:50점      SILVER:100점      GOLD:200점      DIA:300점
						</div>
					</div>
				</div>
				<div id="infobox">
					<div id="infobox1">
						<div id="infobox1-1">해협</div>
						<div id="infobox1-2">
							<div id="orange">견적</div>
							<div id="basic">전달</div>
						</div>
						<div id="infobox1-3">2건</div>
					</div>
					<div id="infobox2">
						<div id="infobox2-1">해협</div>
						<div id="infobox2-2">
							<div id="sky">완료</div>
							<div id="basic">성공</div>
						</div>
						<div id="infobox2-3">5건</div>
					</div>
				</div>
			</div>
			
			
			<div id="line"></div>
			
			
			<div class="wallet">
				<div id="mywallet">
					<div id="walleticon"><i class="fas fa-wallet"></i></div>
					<div id="wallettext">my월렛</div>
				</div>
				<div id="cash">
					<div id="cash_con">
						<div id="cashicon"><i class="fas fa-dollar-sign"></i></div>
						<div>
							<div id="sky">현재 보유 캐시</div>
							<div id="basic">25,000</div>
						</div>
					</div>
					<div id="lock_con">
						<div id="lockicon"><i class="far fa-lock-alt"></i></div>
						<div>
							<div id="lock_con-1">
								<div id="orange">거래 중 캐시</div>
								<div id="questionicon" type="button">
									<i class="fas fa-question-circle"></i>
									<div class="balloon2">
										거래 중 캐시는 진행 중인 서비스가 완료되면
										<br>
										현재 보유 캐쉬로 이전됩니다.
									</div>
								</div>
							</div>
							<div id="basic">13,000</div>
						</div>
					</div>
				</div>
				<div id="account">
					<div id="myaccount" style="display:flex">
						<div id="accounttext">my계좌</div>
						<input id="accountinput" placeholder="&nbsp&nbsp계좌 인증/등록이 필요합니다"></input>
					</div>
					<div id="accountbtn">
						<div id="charge">
							<div id="plusicon"><i class="far fa-plus-square"></i></div>
							<div class="accountbtntext" id="empty">충전하기</div>
						</div>
						<div id="send">
							<div id="minusicon" type="button" onclick="beforeSendModal()"><i class="far fa-minus-square"></i></div>
							<div class="accountbtntext" id="basic">송금하기</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		
		
		<div class="box2">
			<div class="box2_head">
				<div class="townhead">
					<div id="box2icon_town"><i class="fas fa-map-marker-alt"></i></div>
					<div id="title1">my동네</div>
				</div>
				<div class="checklisthead">
					<div id="box2icon_check"><i class="fas fa-clipboard-check"></i></div>
					<div id="title2" type="button">체크 리스트</div>
				</div>
			</div>
			<div class="box2_body">
				<div class="town">
					<div id="towninfo">
						<div id="towninfotext">탐색할 동네를 선택하세요<br>(최대 3개)</div>
						<div id="addtown_wrap">
							<div id="basic">동네 추가하기</div>
							<div id="addtown" type="button" onclick="createAddressFirstModal()"><i class="fal fa-plus-circle"></i></div>
						</div>
					</div>
					<div id="townlist">
						<div id="townlist_con">
							<div id="list_wrap">
								<div id="listidx">1</div>
								<div id="listbody"><div id="address">성남시 중원구 성남동</div></div>
								<div id="empty" type="button"><i class="fas fa-times-circle"></i></div>
							</div>
						</div>	
					</div>
				</div>
				<div class="checklist">
					<div id="checklist_wrap">
						<div id="score">
							<div id="score_head">
								<div id="tagicon"><i class="fas fa-tags"></i></div>
			<!-- 나중에 버튼 다 빼기 -->
								<div class="temp_btn" type="button" id="basic">my평점</div>
							</div>
							<div id="score_star">
								<div id="score_num">0.5</div>
								<div class="wrap-star">
								    <div class='star-rating'>
								        <span id="contorl_star" style ="width:10%"></span>
								    </div>
								</div>
							</div>
							<div id="score_body">
								<div class="score_chart" id="chart"></div>
							</div>
						</div>
						<div id="specialty">
							<div id="specialty_head">
								<div id="tagicon"><i class="fas fa-tags"></i></div>
								<div id="basic">my전문분야</div>
							</div>
							<div id="specialty_body">
								<div id="item_wrap">
									<button type="button" id="specialty_item" class="btn btn-info">도배</button>
								</div>
								<div id="item_wrap">
									<button type="button" id="specialty_item" class="btn btn-info">타일</button>
								</div>
								<div id="item_wrap">
									<button type="button" id="specialty_item" class="btn btn-info">페인트</button>
								</div>
								<div id="item_wrap">
									<button type="button" id="specialty_item" class="btn btn-info">욕실</button>
								</div>
								<div id="item_wrap">
									<button type="button" id="specialty_item" class="btn btn-info">욕실</button>
								</div>
								<div id="item_wrap">
									<button type="button" id="specialty_item" class="btn btn-info">욕실</button>
								</div>
								<div id="item_wrap">
									<button type="button" id="specialty_item" class="btn btn-info">욕실</button>
								</div>
								<div id="item_wrap">
									<button type="button" id="specialty_item" class="btn btn-info">욕실</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>	
		</div>
		
		<div class="delete_user hidden">
			<div class="delete_user_title">회원탈퇴</div>
			<div class="alert_wrap">
				<div class="alert_title" style="font-size: 19px;">※ 회원 탈퇴 시 주의 사항</div>
				<div class="alert_title">1. 회원 탈퇴 후 재 가입 시, <div>&nbsp회원 등급이 초기화&nbsp</div> 됩니다.</div>
				<div class="alert_title">2. 회원 탈퇴 시, <div>&nbsp작성한 모든 게시 글 및 댓글이 유지&nbsp</div>됩니다.</div>
				<div class="alert_title">3. 회원 탈퇴 시, <div>&nbsp현재 보유하고 있는 캐시가 소멸&nbsp</div>됩니다.</div>
			</div>
			<div class="check">
				<label><a>※ 1번 항목에 동의 하십니까?</a><input type="checkbox"></label>
				<label><a>※ 2번 항목에 동의 하십니까?</a><input type="checkbox"></label>
				<label><a>※ 3번 항목에 동의 하십니까?</a><input type="checkbox"></label>
			</div>
			<div class="delete_user_button">
				<button class="cancel" >취소</button>
				<button class="confirm" onclick="real_delete_confirm()">회원탈퇴</button>
			</div>
		</div>
	</div>


<div class='chatting'></div>
</div>

<script type="text/javascript" src="../../../resources/js/include/head/menu_head.js"></script>
<script type="text/javascript">
/* document.querySelector("#addtown").addEventListener('click', e => {
	let idx = document.querySelectorAll('#list_wrap').length + 1;
	if(idx > 3) {
		alert("동네는 최대 3개까지만 추가할 수 있습니다.");
	}else {
		let inputAddress = prompt("주소를 입력하세요");
		if(inputAddress == "") {
			alert("주소를 입력해주세요.");
		}else if(inputAddress != "") {
			let listWrap = document.createElement("div");
			listWrap.setAttribute("id", 'list_wrap');
			document.querySelector("#townlist_con").appendChild(listWrap);
			let listIdx = document.createElement("div");
			listIdx.setAttribute("id", 'listidx');
			listIdx.innerHTML = `${idx}`;
			listWrap.appendChild(listIdx);
			let listBody = document.createElement("div");
			if(idx == 2) {
				listBody.setAttribute("id", 'listbody2');
			}else if(idx == 3) {
				listBody.setAttribute("id", 'listbody3');
			}
			listBody.innerHTML = `<div id="address">${inputAddress}</div>`;
			listWrap.appendChild(listBody);
			let listDelete = document.createElement("div");
			listDelete.setAttribute("id", 'listdelete');
			listDelete.setAttribute("type", 'button');
			listDelete.innerHTML = '<i class="fas fa-times-circle"></i>';
			listWrap.appendChild(listDelete);
			listDelete.addEventListener('click', () => {
				listWrap.remove();
			})
		}
	}
}) */

/* 재미로 만든 별점 올리기 */
document.querySelector(".temp_btn").addEventListener('click', e => {
	let scoreNum = parseFloat(document.querySelector("#score_num").innerHTML);
	if(scoreNum < 5) {
		document.querySelector("#score_num").innerHTML = scoreNum + 0.5;
		let widthValue = parseInt(document.querySelector("#contorl_star").style.width.substring(0,2));
		document.querySelector("#contorl_star").style.width = (widthValue + 10) + "%";
	}
});


var chart = c3.generate({
    data: {
        columns: [
            ['신속', 30],
            ['친절', 80],
            ['비용', 60],
        ],
        type : 'pie',
        colors: {
        	'신속' : 'rgb(255, 167, 167)',

        	'친절' : '#FFCE67',

        	'비용' : '#6cc3d5',
    	},
        onclick: function (d, i) { console.log("onclick", d, i); },
    }
});

/* 송금하기-인증 전 모달 */
let beforeSendModal = () => {
	let modal = initModal('modal', 1);
	appendTitle(modal, '송금하기');
	setButton(modal, '그만두기', '인증하기');
	setContent(modal, true, true);
	addPiggyBackground(modal);
	
	let modalBody = $('<br><div class="sendBefore">"첫 송금이신가요?<br>계좌 인증을 진행해주세요!"<div>').height('10px')
					.addClass('send_modal_content');
	$('.modal_content').append(modalBody);
	
	modalBlock();
	
	$('.modal_left_btn').click(function() {
		modalNone();
	})
}

/* 송금하기-인증 후 모달 */
let afterSendModal = () => {
	let modal = initModal('modal', 2);
	appendTitle(modal, '송금하기');
	setButton(modal, '그만두기', '인증하기');
	setContent(modal, true, true);
	addPiggyBackground(modal);
	
	let modalBody = $('<br><div class="send">현재 보유 캐시<br>25,000 <i class="fas fa-coins"></i><div><br><div class="chargeMoney">인증 계좌 <input placeholder="&nbsp&nbsp 123-456789-5463"> <br><br>송금할 금액<input placeholder="&nbsp&nbsp금액을 입력해주세요. (최소 금액 : 천원)"><div>').height('10px')
					.addClass('send_modal_content');
	$('.modal_content').append(modalBody);
	
	
	
	modalBlock();
	
	$('.modal_left_btn').click(function() {
		modalNone();
	})
}

/* 일반전환 */
let changeNomal = () => {
	let modal = initModal('modal', 2);
	appendTitle(modal, '일반계정 등록');
	setButton(modal, '그만두기', '연결하기');
	setContent(modal, true, true);
	
	let modalBody = $('<div class="registration"><br>아이디 <input  placeholder="&nbsp&nbsp연결할 일반회원 계정의 아이디를 입력하세요."><br> 비밀번호<input placeholder="&nbsp&nbsp연결할 일반회원 계정의 비밀번호를 입력하세요."> <br><br> 현재 계정 비밀번호 확인<input placeholder="&nbsp&nbsp현재 로그인된 업체 비밀번호를 입력하세요."><div>').height('100px')
					.addClass('send_modal_content');
	$('.modal_content').append(modalBody);
	
	
	
	modalBlock();
	
	$('.modal_left_btn').click(function() {
		modalNone();
	})
}

/* 내정보 하위 버튼 */
     	const myInfo_btn = document.querySelector("#myInfo_btn");
        const modifyInfo_btn = document.querySelector("#modifyInfo_btn");
        const delete_user_btn = document.querySelector("#delete_user_btn");
        const bg = document.querySelector("#bg");
        const delete_user = document.querySelector(".delete_user");
		const cancel = document.querySelector(".cancel");
        const confirm = document.querySelector(".confirm");
        const wrap = document.querySelector(".wrap");
        
        /* 내정보 하위 버튼 */
        myInfo_btn.addEventListener("mouseover",function(){
        	modifyInfo_btn.classList.remove("hidden");
        	delete_user_btn.classList.remove("hidden");
        });
        
        /* 정보 수정 버튼 */
        modifyInfo_btn.addEventListener("click",function(){
        	modifyInfo_btn.classList.add("hidden");
        	delete_user_btn.classList.add("hidden");
        });
        
        /* div class wrap부분 누르면 하위 버튼들 사라짐 */
        wrap.addEventListener("click",function(){
        	modifyInfo_btn.classList.add("hidden");
        	delete_user_btn.classList.add("hidden");
        });
        
        /* 탈퇴 버튼 */
        delete_user_btn.addEventListener("click",function(){
        	bg.classList.remove("hidden");
        	modifyInfo_btn.classList.add("hidden");
        	delete_user_btn.classList.add("hidden");
        	delete_user.classList.remove("hidden");
        });	
        
        /* 탈퇴취소 */
        cancel.addEventListener("click",function(){
        	bg.classList.add("hidden");
        	delete_user.classList.add("hidden");
        });	
        
        /* 탈퇴 가즈아 */
        confirm.addEventListener("click",function(){
        	bg.classList.add("hidden");
        	delete_user.classList.add("hidden");
        });	
        
        /* 탈퇴 완료 확인 모달 */
        let real_delete_confirm = () => {
    		let modal = initModal('modal', 3);
    		appendTitle(modal, '회원탈퇴');
    		setButton(modal, '확인');
    		setContent(modal,true,true);
    		
    		
    		let modalBody = $('<div class="bye">탈퇴되었습니다.<div><br>')
    						.addClass('send_modal_content');
    		$('.modal_content').append(modalBody);
    		
    		
    		modalBlock();
    		
    		$('.modal_left_btn').click(function() {
    			modalNone();
    		})
    	}
</script>
<script type="text/javascript" src="../../../resources/js/mypage/address_modal.js"></script>
</body>
</html>