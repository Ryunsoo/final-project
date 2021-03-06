package com.kh.hehyeop.common.chat.model.service;

import java.util.List;

import com.kh.hehyeop.common.chat.model.dto.ChatLog;
import com.kh.hehyeop.common.chat.model.dto.ChatRoom;
import com.kh.hehyeop.member.model.dto.Member;

public interface ChatService {

	List<ChatLog> selectUnReadChatListById(String id);
	
	List<ChatLog> selectReadChatListById(String id);

	void updateChatLog(String roomNo, String chatData);

	void updateExitDate(String id, String no);

	String selectChatLog(String roomNo);

	List<ChatRoom> selectChatMemberListById(String id, String roomNo);

	int deleteIdByRoomNo(String roomNo, String id);
	
	int updateRoomName(ChatRoom chatRoom);

	int insertFriendByNickname(String id, String nickname);

	String selectFriendIdByNickname(String id, String nickname);

	String selectMemberByFriendId(String friendId);

	void createChatRoom(List<String> idList);


}
