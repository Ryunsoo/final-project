//해협삭제(취소) 버튼
let cancelHelp = (reqIdx) => {
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
	$('.modal_rigft_btn').click(function() {
		modalNone();
	    fetch('/help/cancelHelp?reqIdx='+reqIdx);
	})
}
//최신화 버튼
let refreshHelp = (reqIdx) => {
	modalNone();
	let modal = initModal('modal', 1);
	appendTitle(modal,'해협끌올');
	setButton(modal,'그만두기','확 인');
	setContent(modal,true,true);
	let modalBody = $('<div>해협신청을 갱신하시겠습니까?<div>').height('10px').css("margin",'0 20px 0 20px');
	$('.modal_content').append(modalBody);
	modalBlock();
	$('.modal_left_btn').click(function() {
		modalNone();
	})
	$('.modal_rigft_btn').click(function() {
		modalNone();
	    fetch('/help/refreshHelp?reqIdx='+reqIdx);
	})
}
//완료버튼
