async function clickChatting() {
	let viewChat = document.getElementById("chatting_main");
	let viewChatRoom = document.querySelector("#chattingRoom");
	
	if(viewChat.style.display == "none") {
		let success = await getChattingList();
		if(!success) return;
		
		viewChat.style.display = "flex";
	}else {
		viewChat.style.display = "none";
	}
	
	if(document.querySelector('body').classList.contains('frogue-opened')) {
		return;
	}
	if (viewChatRoom.style.display == "flex"){
		return;
	}
	
}

let getChattingList = async () => {
	let success = true;
	try{
		let response = await fetch('/chat/chat-room');
		if(!response.ok) throw new Error();
		let datas = await response.json();
		
		await createList(datas);
		
		console.log(JSON.stringify(datas));
	} catch(e) {
		success = false;
		window.alert('로그인 전 채팅 이용이 불가능합니다.');
	}
	return success;
}

let createList = async (data) => {
	document.getElementById('myList').innerHTML = '';
	
	for (var i = 0; i < data.length; i++) {
		let chatItem = document.createElement("div");
		chatItem.setAttribute('id','chatItem');
		let chatItemTitle = document.createElement("div");
		chatItemTitle.setAttribute('id','chatItemTitle');
		chatItem.appendChild(chatItemTitle);
		let chatItemBtn = document.createElement("div");
		chatItemBtn.setAttribute('id','chatItemBtn');
		chatItemBtn.innerHTML = '입장';
		chatItem.appendChild(chatItemBtn);
		
		chatItemBtn.setAttribute('data-room-no', data[i].roomNo);
		chatItemTitle.innerHTML = data[i].roomName;
		
		chatItemBtn.addEventListener('click', function(e) {
			document.getElementById("chatting_main").style.display = "none";
			document.querySelector("#chattingRoom").style.display = "flex";
			document.getElementById('chattingRoom').contentWindow.openSocket(e.target.dataset.roomNo);
		}) 
		
		let myList = document.getElementById('myList');
		myList.appendChild(chatItem);
	}
}

function openChattingRoom() {
	document.querySelector("#chattingRoom").style.display = "flex";
	document.getElementById("chatting_main").style.display = "none";
}

function closeIframe() {
	document.querySelector("#chattingRoom").style.display = "none";
}