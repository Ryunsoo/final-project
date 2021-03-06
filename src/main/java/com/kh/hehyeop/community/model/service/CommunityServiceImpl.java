package com.kh.hehyeop.community.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.hehyeop.common.util.paging.Paging;
import com.kh.hehyeop.community.model.dto.Community;
import com.kh.hehyeop.community.model.dto.Reply;
import com.kh.hehyeop.community.model.dto.Rereply;
import com.kh.hehyeop.community.model.repository.CommunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

	private final CommunityRepository communityRepository;

	@Override
	public void insertBoard(Community community) {
		communityRepository.insertBoard(community);
	}

	@Override
	public String selectNickname(String id) {
		return communityRepository.selectNickname(id);
	}

	@Override
	public List<Community> selectBoardList(Paging paging) {
		return communityRepository.selectBoardList(paging);
	}

	@Override
	public int countBoard() {
		return communityRepository.countBoard();
	}

	@Override
	public Community selectBoardByIdx(String boardIdx) {
		return communityRepository.selectBoardByIdx(boardIdx);
	}

	@Override
	public void updateViewCnt(String boardIdx) {
		communityRepository.updateViewCnt(boardIdx);
	}

	@Override
	public void insertReply(Reply reply) {
		communityRepository.insertReply(reply);
	}

	@Override
	public List<Reply> selectReplyList(String boardIdx) {
		return communityRepository.selectReplyList(boardIdx);
	}

	@Override
	public void modifyReply(Reply reply) {
		communityRepository.modifyReply(reply);
	}

	@Override
	public void deleteReply(String replyIdx) {
		communityRepository.deleteReply(replyIdx);
	}

	@Override
	public void modifyBoard(Community community) {
		communityRepository.modifyBoard(community);
	}

	public void insertReReply(Rereply reReply) {
		communityRepository.insertReReply(reReply);
	}

	@Override
	public List<Rereply> selectReReplyList() {
		return communityRepository.selectReReplyList();
	}

	@Override
	public void deleteReReply(String reReplyIdx) {
		communityRepository.deleteReReply(reReplyIdx);
	}

	@Override
	public Integer countBoardSearchList(String boardCategory, String searchKeyword) {
		return communityRepository.countBoardSearchList(boardCategory, searchKeyword);
	}
	
	public void deleteBoard(String boardIdx) {
		communityRepository.deleteBoard(boardIdx);
	}

	@Override
	public List<Community> selectSearchList(String boardCategory, String searchKeyword, Paging paging) {
		return communityRepository.selectSearchList(boardCategory, searchKeyword, paging);
	}

	@Override
	public void modifyReReply(Rereply reReply) {
		communityRepository.updateReReply(reReply);
	}

}
