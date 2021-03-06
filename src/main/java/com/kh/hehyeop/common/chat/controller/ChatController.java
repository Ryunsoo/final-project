package com.kh.hehyeop.common.chat.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.hehyeop.common.chat.model.dto.ChatLog;
import com.kh.hehyeop.common.chat.model.dto.ChatRoom;
import com.kh.hehyeop.common.chat.model.service.ChatService;
import com.kh.hehyeop.member.model.dto.CMember;
import com.kh.hehyeop.member.model.dto.Member;
import com.kh.hehyeop.member.model.dto.User;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("chat")
@RequiredArgsConstructor
public class ChatController {
	
	private final ObjectMapper objectMapper;
	private final ChatService chatService;
	
	//세션에 저장되어 있는 로그인 정보를 가지고, 해당 아이디가 보유하고 있는 채팅 목록을 가져와 view 로 넘기는 메서드
	@GetMapping("chat-room")
	@ResponseBody
	public Map<String, List<ChatLog>> chatList(HttpSession session){		
		User user = (User) session.getAttribute("authentication");
		
		if(user == null) {
			return null;
		}
		
		Map<String, List<ChatLog>> chatListMap = new HashMap<String, List<ChatLog>>();
		chatListMap.put("unread", chatService.selectUnReadChatListById(user.getId()));
		chatListMap.put("read", chatService.selectReadChatListById(user.getId()));
		//session.setAttribute("chatLog", chatListMap);
		return chatListMap;
	}
	
	@GetMapping("chatting")
	public String chatting(HttpSession session) {
		return "include/chat/chat-room";
	}
	
	@PostMapping("chat-save")
	@ResponseBody
	public void chatSave(@RequestBody String chatLog, HttpSession session) throws JsonMappingException, JsonProcessingException {
		System.out.println("roomNo : " + chatLog);
		Map<String, String> mapper = objectMapper.readValue(chatLog, Map.class);
		System.out.println("map으로 변환한 chatlog json : " + mapper);
		chatService.updateChatLog(mapper.get("room"), mapper.get("chatLog"));
	}
	
	@GetMapping("update-exit")
	@ResponseBody
	public void updateExit(HttpSession session, String roomNo) {
		User user = (User) session.getAttribute("authentication");
		chatService.updateExitDate(user.getId(), roomNo);
	}
	
	@GetMapping("chat-log")
	@ResponseBody
	public ResponseEntity<String> selectChatLog(String roomNo) {
		String logData = chatService.selectChatLog(roomNo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		
		return (new ResponseEntity<String>(logData, headers, HttpStatus.OK));
	}
	
	@GetMapping("chat-room-member")
	@ResponseBody
	public List<ChatRoom> chatMemberList(HttpSession session, String roomNo){		
		User user = (User) session.getAttribute("authentication");
		
		if(user == null) {
			return null;
		}
		return chatService.selectChatMemberListById(user.getId(), roomNo);
	}

	@GetMapping("exit-room")
	@ResponseBody
	public String exitRoom(HttpSession session, String roomNo) {
		User user = (User) session.getAttribute("authentication");
		
		int res = chatService.deleteIdByRoomNo(roomNo, user.getId());
		if(res == 1) return "success";
		return null;
	}
	
	@PostMapping("rename-room")
	@ResponseBody
	public String rename(HttpSession session, @RequestBody String roomName, String roomNo) throws JsonMappingException, JsonProcessingException {
		User user = (User) session.getAttribute("authentication");
		
		ChatRoom chatRoom = new ChatRoom();
		chatRoom.setId(user.getId());
		chatRoom.setRoomName(roomName);
		chatRoom.setRoomNo(roomNo);
		int res = chatService.updateRoomName(chatRoom);
		
		if(res == 1) return "success";
		return null;
	}

	@GetMapping("chat-room-addFriend")
	@ResponseBody
	public String chatAddFriend(HttpSession session, String nickname, String friendId){	
		User user = (User) session.getAttribute("authentication");
		if(user instanceof CMember) return "company";
		
		String friend = chatService.selectMemberByFriendId(friendId);
		if(friend == null) return "coFriend";
		
		//친구추가가 이미 되어있는지 확인
		String hasId = chatService.selectFriendIdByNickname(user.getId(), nickname);
		if(hasId != null) return "exist";
			
		int res = chatService.insertFriendByNickname(user.getId(), nickname);
		if(res == 1) return "success";
		
		return "fail";
	}
	
	
	//1:1 생성 패치로 여기 오기 / 친구 아이디 파람파람
	@GetMapping("create-chatRoom")
	@ResponseBody
	public String createChatRoom(HttpSession session, String friendId) {
		System.out.println(friendId);
		User user = (User) session.getAttribute("authentication");
		chatService.createChatRoom(List.of(user.getId(), friendId));
		return "success";
	}
}
