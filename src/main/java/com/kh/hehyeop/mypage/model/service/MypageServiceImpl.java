package com.kh.hehyeop.mypage.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kh.hehyeop.mypage.model.repository.MypageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService{

	private final MypageRepository mypageRepository;
	
	@Override
	public List<String> getValidTokens(String userId) {
		List<String> validTokens = new ArrayList<String>();
		Map<String, String> allTokens = mypageRepository.selectPushTokensById(userId);
		
		
		
		
		return null;
	}

	@Override
	public void insertToken(String token, String device, String id) {
		mypageRepository.insertToken(Map.of("token",token,"device",device,"id",id));
	}

	@Override
	public void deleteToken(String device, String id) {
		mypageRepository.deleteToken(Map.of("device",device,"id",id));
		
	}




}
