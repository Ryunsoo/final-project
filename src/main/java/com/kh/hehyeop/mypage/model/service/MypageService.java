package com.kh.hehyeop.mypage.model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.hehyeop.member.model.dto.CMember;
import com.kh.hehyeop.member.model.dto.Member;
import com.kh.hehyeop.member.validator.FieldForm;
import com.kh.hehyeop.mypage.model.dto.Location;
import com.kh.hehyeop.mypage.model.dto.MyAddress;
import com.kh.hehyeop.mypage.model.dto.Friend;
import com.kh.hehyeop.mypage.model.dto.LinkMember;
import com.kh.hehyeop.mypage.model.dto.Wallet;
import com.kh.hehyeop.mypage.validator.JoinForm;
import com.kh.hehyeop.mypage.validator.ModifyCompany;

public interface MypageService {

	List<String> getValidTokens(String userId);
	List<String> getValidTokens(List<String> userIdList);
	void insertToken(String token,String device, String id);
	void deleteToken(String device,String id);
	void deleterUser(Member member);
	void updateWalletInfo(Wallet wallet);
	Wallet selectWallet(String id);
	Member selectMemberByEmail(String email);
	void updateInfo(JoinForm form);
	void insertCash(Wallet chargeWallet);
	Member authenticateUser(Member member);
	List<Location> selectLocationList(Location location);
	void updateAddress(Location location, String id);
	MyAddress getMypageAddressList(String id);
	void removeAddress(String id, String targetAddress);
	void updateCash(Wallet wallet);
	List<Friend> selectFriend(String id);
	void updateMemo(String id, String friendId, String memo);
	List<String> selectField(String id);
	void deleteFriend(String id, String friendId);
	void updateCompanyInfo(JoinForm form);
	CMember authenticateCUser(CMember member);
	LinkMember selectLink(String id);
	void updateCompanyField(String id, List<String> fieldList);
	void uploadFile(List<MultipartFile> files, String cIdx);
	List<Integer> selectResponseCnt(String id);
	List<Double> getScore(String id);
	List<Map<String, Object>> getReview(String id);
	Map<String, String> getPurchaseCount(String id);
	int selectStatus(String id);
	ArrayList<FieldForm> selectFieldExceptPermit(String id);
	
}
