//삭제 버튼
let deleteHelp = (reqIdx) => {
	modalNone();
	let modal = initModal('modal', 1);
	appendTitle(modal,'해협취소');
	setButton(modal,'그만두기','확 인');
	setContent(modal,true,true);
	let modalBody = $('<div>정말 해협을 취소하시겠습니까?<div>').height('10px').css("margin",'0 20px 0 20px');
	$('.modal_content').append(modalBody);
	modalBlock();
	$('.modal_left_btn').click(function() {
		modalNone();
	})
	$('.modal_right_btn').click(function() {
	    location.href = '/help/deleteHelp?reqIdx='+reqIdx;
	    modalNone();
	})
}
//최신화 버튼
let refreshHelp = (reqIdx) => {
	modalNone();
	let modal = initModal('modal', 1);
	appendTitle(modal,'해협갱신');
	setButton(modal,'그만두기','확 인');
	setContent(modal,true,true);
	let modalBody = $('<div>해협신청을 갱신하시겠습니까?<div>').height('10px').css("margin",'0 20px 0 20px');
	$('.modal_content').append(modalBody);
	modalBlock();
	$('.modal_left_btn').click(function() {
		modalNone();
	})
	$('.modal_right_btn').click(function() {
	    location.href = '/help/refreshHelp?reqIdx='+reqIdx;
	    modalNone();
	})
}
//취소 버튼
let cancelHelp = (reqIdx) => {
	modalNone();
	let modal = initModal('modal', 1);
	appendTitle(modal,'해협취소');
	setButton(modal,'그만두기','확 인');
	setContent(modal,true,true);
	let modalBody = $('<div>취소요청을 보내시겠습니까?<div>').height('10px').css("margin",'0 20px 0 20px');
	$('.modal_content').append(modalBody);
	modalBlock();
	$('.modal_left_btn').click(function() {
		modalNone();
	})
	$('.modal_right_btn').click(function() {
	    location.href = '/help/cancelHelp?reqIdx='+reqIdx;
	    modalNone();
	})
}
//완료 버튼
let completeHelp = (reqIdx) => {
	modalNone();
	let modal = initModal('modal', 1);
	appendTitle(modal,'해협완료');
	setButton(modal,'그만두기','확 인');
	setContent(modal,true,true);
	let modalBody = $('<div>해협 완료를 진행하시겠습니까?<div>').height('10px').css("margin",'0 20px 0 20px');
	$('.modal_content').append(modalBody);
	modalBlock();
	$('.modal_left_btn').click(function() {
		modalNone();
	})
	$('.modal_right_btn').click(function() {
	    location.href = '/help/completeHelp?reqIdx='+reqIdx;
	    modalNone();
	})
}
//상세 페이지 클릭이벤트(견적 -> 상세)
let detail = () => {
	$('.left_page').css('display','block');
	$('.right_page').css('display','block');
	$('#estimateBtn').css('color','gray');
	$('#estimateBtn').css('background','rgb(239, 239, 239)');
	$('#detailBtn').css('background','rgb(190,190,190)');
	$('#detailBtn').css('color','black');
	$('.company_list').css('display','none');
	$('.company_detail').css('display','none');
}
//상세 페이지
let showDetail = (reqIdx) => {
	 fetch("/help/my-hehyeop-detail?reqIdx="+reqIdx)
	 .then(response => response.json())
	 .then(commandMap => {
		let file = commandMap.files[0];
	 	let helpRequest = commandMap.helpRequest;
	 	//reqTime ex)2021-11-20T21:05 변환해주기
	 	let reqTimeArr = helpRequest.reqTime.split("T");
	 	let reqTime = reqTimeArr[0]+" "+reqTimeArr[1];
	 	//값 세팅해주기
	 	$('#fileImg').attr('src','/file/'+file.savePath+file.reName);
	 	$('#dname').attr('value',helpRequest.reqName);
	 	$('#dtell').attr('value',helpRequest.reqTell);
	 	$('#daddress').attr('value',helpRequest.reqAddress);
	 	$('#dtime').attr('value',reqTime);
	 	$('#dpay').attr('value',helpRequest.reqPay);
	 	$('#dcontent').html(helpRequest.reqContent);
	 	//상세페이지 보이게하기
	 	$('.breakdown').css('display','block');
		$('#detailBtn').css('background','rgb(190,190,190)');
		$('#detailBtn').css('color','black');
		$('#estimateBtn').css('background','rgb(239, 239, 239)');
		$('#estimateBtn').css('color','gray');
		$('.left_page').css('display','block');
		$('.right_page').css('display','block');
		//히든태그에 reqIdx 저장
		$('.saveReqIdx').attr('value',reqIdx);
	 });
}

//견적 페이지
let estimate = () => {
	let reqIdx = $('.saveReqIdx').val();
	let table = $('#response_list');
	table.html("<tr><th>업체명</th><th>업체 주소</th><th>신청일</th><th>업체 선택</th></tr>");
	fetch("/help/my-hehyeop-estimate?reqIdx="+reqIdx)
	 .then(response => response.json())
	 .then(responseList => {
		console.dir(responseList);
		for(let i = 0; i < responseList.length; i++) {
			//주소 자르기
		 	let addressArr = responseList[i].address.split(" ");
		 	let address= addressArr[0]+" "+addressArr[1]; 
			//regDate 변환하기
			let regDate = new Date(responseList[i].regDate);
			regDate = regDate.getFullYear() + '-' + (regDate.getMonth()+1) + '-' + regDate.getDate();
			//
			let id = responseList[i].id;
			let resIdx = responseList[i].resIdx;
			let pay = responseList[i].resPay;
			let tell = responseList[i].tell;
			let timeArr = responseList[i].resTime.split("T");
	 		let time = timeArr[0]+" "+timeArr[1];
	 	
			let tr = $('<tr></tr>');
			table.append(tr);
			let resName = $('<td>'+responseList[i].company+'</td>');
			resName.attr('onclick',"estimateDetail('" + resIdx + "', '" + tell + "', '"+ time + "', '" + pay + "')");
			let resAddress = $('<td>'+address+'</td>');
			resAddress.attr('onclick',"estimateDetail('" + resIdx + "', '" + tell + "', '"+ time + "', '" + pay + "')");
			let resRegDate = $('<td>'+regDate+'</td>');
			resRegDate.attr('onclick',"estimateDetail('" + resIdx + "', '" + tell + "', '"+ time + "', '" + pay + "')");
			let btn = $('<td></td>');
			tr.append(resName);
			tr.append(resAddress);
			tr.append(resRegDate);
			tr.append(btn);
			let select = $('<button>선택하기</button>').css('margin-right','2px');
			select.addClass('list_btn_green');
			select.attr('onclick',"selectCompany('" + id + "', '" + resIdx + "', '"+ pay + "', '" + reqIdx + "')");
			let ask = $('<button>문의하기</button>').css('margin-left','2px');
			ask.addClass('list_btn');
			ask.attr('onclick',"chatCompany('" + id + "', '" + resIdx + "')");
			btn.append(select);
			btn.append(ask);
			console.dir('왜 안 만들어져??');
		}	
			$('.left_page').css('display','none');
			$('.right_page').css('display','none');
			$('#detailBtn').css('color','gray');
			$('#detailBtn').css('background','rgb(239, 239, 239)');
			$('#estimateBtn').css('background','rgb(190,190,190)');
			$('#estimateBtn').css('color','black');
			$('.company_list').css('display','flex');
			$('.company_detail').css('display','block');
	})
}
//견적서 목록 클릭시
let estimateDetail = (resIdx,tell,time,pay) => {
	fetch("/help/my-hehyeop-estimateFile?resIdx="+resIdx)
	 .then(response => response.json())
	 .then(fileDto => {
		$('#resTell').attr('value',tell);
		$('#resTime').attr('value',time);
		$('#resPay').attr('value',pay+'원');
		$('#resPhoto').attr('src','/file/'+fileDto[0].savePath+fileDto[0].reName);
	})
}

//견적서 목록에서 선택하기 클릭시
let selectCompany = (id,resIdx,resPay,reqIdx) => {
	
}
//견적서 목록에서 문의하기 클릭시
let chatCompany = (id,resIdx) => {
	
}

let filter = 'all';
let page = 1;

//상태 필터링
let filtering = async () => {
	$('.help_list').html('');
	filter = $('.state_filter').val();
	page = 1;
	let datas = await filterFetch();
	console.dir(datas);
	renewPage(datas.paging);
	
	if(datas.noList) {
		let tr = $('<tr>');
		tr.append($("<td colspan = '7'>조회된 해협 내역이 없습니다.</td>"));
		$('.help_list').append(tr);
		return;
	}
	renewHelpList(datas.helpList);
}

let filterFetch = async () => {
	let response = await fetch('/help/help-list?filter=' + filter)
	let datas = await response.json();
	return datas;
}

//페이징 번호나 화살표 눌렀을때
let getList = async (pageUrl) => {
	$('.help_list').html('');
	urlArr = pageUrl.split('=');
	page = Number(urlArr[1]);
	let url = pageUrl + '&filter=' + filter;
	let datas = await pageFetch(url);
	renewPage(datas.paging);
	
	if(datas.noList) {
		let tr = $('<tr>');
		tr.append($("<td colspan = '7'>조회된 해협 내역이 없습니다.</td>"));
		$('.help_list').append(tr);
		return;
	}
	renewHelpList(datas.helpList);
}

let pageFetch = async (url) => {
	let response = await fetch(url)
	let datas = await response.json();
	return datas;
}

let renewHelpList = (helpList) => {
	helpList.forEach(help => {
		let regDate = new Date(help.regDate);
		regDate = regDate.getFullYear() + '-' + (regDate.getMonth()+1) + '-' + regDate.getDate();
		let payMeans = help.payMeans == null ? '' : help.payMeans;
		let tr = $('<tr>');
		tr.append($('<td>' + help.field + '</td>').attr('onclick', 'showDetail(' + help.reqIdx + ')'))
			.append($('<td>' + help.area + '</td>').attr('onclick', 'showDetail(' + help.reqIdx + ')'))
			.append($('<td>' + regDate + '</td>').attr('onclick', 'showDetail(' + help.reqIdx + ')'))
			.append($('<td>' + help.estimateCnt + '</td>').attr('onclick', 'showDetail(' + help.reqIdx + ')'))
			.append(getCompanyTd(help).attr('onclick', 'showDetail(' + help.reqIdx + ')'))
			.append($('<td>' + payMeans + '</td>').attr('onclick', 'showDetail(' + help.reqIdx + ')'))
			.append(getBtnTd(help))
			.append($('<input>').addClass('reqIdx').attr('type', 'hidden').val(help.reqIdx));
		$('.help_list').append(tr);
	})
	
}

let getCompanyTd = (help) => {
	let company = help.company == null ? '' : help.company;
	if(!company) {
		return $('<td>');
	}
	let comDiv = $('<td>');
	let medalSpan;
	switch(help.grade) {
		case 'BRONZE':
			medalSpan = $("<span style='color: #cc9900'><i class='fas fa-medal'></i></span>");
			return comDiv.append(medalSpan).append(company);
		case 'SILVER':
			medalSpan = $("<span style='color: silver'><i class='fas fa-medal'></i></span>");
			return comDiv.append(medalSpan).append(company);
		case 'GOLD':
			medalSpan = $("<span style='color: gold'><i class='fas fa-medal'></i></span>");
			return comDiv.append(medalSpan).append(company);
		case 'DIA':
			medalSpan = $("<span style='color: silver'><i class='fas fa-gem'></i></span>");
			return comDiv.append(medalSpan).append(company);
		default:
			return $('<td>' + company + '</td>');
	}
}

let getBtnTd = (help) => {
	let td = $('<td>');
	switch(help.state) {
		case 1:
			let deleteBtn = $('<button>삭제</button>').addClass('list_btn_red').attr('id', 'delete').attr('onclick', 'deleteHelp(' + help.reqIdx + ')');
			let refreshBtn = $('<button>끌올</button>').addClass('list_btn_green').attr('id', 'refresh').attr('onclick', 'refreshHelp(' + help.reqIdx + ')');
			return td.append(deleteBtn).append(refreshBtn);
		case 2:
			let finishBtn = $('<button>완료</button>').addClass('list_btn_green').attr('id', 'complete').attr('onclick', 'completeHelp(' + help.reqIdx + ')');
			let cancelBtn = $('<button>취소</button>').addClass('list_btn_red').attr('id', 'cancel').attr('onclick', 'cancelHelp(' + help.reqIdx + ')');
			return td.append(finishBtn).append(cancelBtn);
		case 3:
			return td.html('완료 대기 중');
		case 4:
			let reviewBtn = $('<button>후기</button>').addClass('list_btn').attr('onclick', 'createReviewModal(' + help.reqIdx + ')');
			return td.append(reviewBtn);
		case 5:
			return td.html('★ ' + help.score);
		case 6:
			return td.html('취소 대기 중');
		default:
			return td.html('진행취소 완료');
	}
}

let renewPage = (paging) => {
	let pageDiv = $('.page');
	pageDiv.html('');
	pageDiv.append($('<i>').addClass('fas fa-caret-left').attr('onclick', "getList('" + paging.url + "?page=" + paging.prev + "')"));
	
	let numDiv = $('<div>');
	
	for(let i = paging.blockStart; i <= paging.blockEnd; i++) {
		let numSpan = $('<span>' + i + '</span>').attr('onclick', "getList('" + paging.url + "?page=" + i + "')");
		if(i == page) numSpan.addClass('selected');
		numDiv.append(numSpan);
	}
	
	pageDiv.append(numDiv)
			.append($('<i>').addClass('fas fa-caret-right').attr('onclick', "getList('" + paging.url + "?page=" + paging.next + "')"));

}