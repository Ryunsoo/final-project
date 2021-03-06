package com.kh.hehyeop.mypage.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.hehyeop.common.util.file.FileDTO;
import com.kh.hehyeop.member.model.dto.CMember;
import com.kh.hehyeop.member.model.dto.Member;
import com.kh.hehyeop.member.validator.FieldForm;
import com.kh.hehyeop.mypage.model.dto.Location;
import com.kh.hehyeop.mypage.model.dto.Friend;
import com.kh.hehyeop.mypage.model.dto.LinkMember;
import com.kh.hehyeop.mypage.model.dto.Token;
import com.kh.hehyeop.mypage.model.dto.Wallet;
import com.kh.hehyeop.mypage.model.dto.MyAddress;
import com.kh.hehyeop.mypage.validator.JoinForm;

@Mapper
public interface MypageRepository {

	@Select("select * from push_token where id = #{userId}")
	Token selectPushTokensById(String userId);

	void insertToken(Map<String, Object> tokenMap);

	void deleteToken(Map<String, Object> tokenMap);

	List<Token> selectPushTokensByManyId(List<String> userIdList);

	@Update("update member set is_leave = 1 where id = #{id} and password = #{password}")
	void deleterUser(Member member);

	@Update("update wallet set bank = #{bank}, bank_num = #{bankNum}, auth_token = #{authToken}, user_seq = #{userSeq} where id = #{id}")
	void updateWalletInfo(Wallet wallet);

	@Select("select * from wallet where id = #{id}")
	Wallet selectWallet(String id);

	@Select("select * from member where email = #{email} and is_leave = 0")
	Member selectMemberByEmail(@Param("email") String email);

	@Update("update member set password = #{password}, name = #{name}, tell = #{tell}, email = #{email}, nickname = #{nickname}, address = #{address}, old_address = #{oldAddress} where id = #{id}")
	void updateInfo(JoinForm form);

	@Select("select * from member where id = #{id} and is_leave = 0")
	Member authenticateUser(Member member);

	@Select("select * from member_c where id = #{id} and is_leave = 0")
	CMember authenticateCUser(CMember member);

	@Update("update wallet set cash = cash + #{cash} where id = #{id}")
	void insertCash(Wallet chargeWallet);

	@Select("select * from friend f join member m on f.friend_id = m.id where f.id = #{id}")
	List<Friend> selectFriend(String id);

	@Update("update friend set memo = #{memo} where id = #{id} and friend_id = #{friendId}")
	void updateMemo(@Param("id") String id, @Param("friendId") String friendId, @Param("memo") String memo);

	@Update("update wallet set cash = #{cash} where id = #{id}")
	void updateCash(Wallet wallet);

	@Select("select distinct city from location where major_key= #{majorKey} order by city")
	List<Location> selectLocationCityList(Location location);

	@Select("select distinct town from location where city = #{city} order by town")
	List<Location> selectLocationTownList(Location location);

	@Select("select field from company_field where id = #{id} and is_permit = 1")
	List<String> selectField(String id);

	@Update("update my_area set address2 = #{address2} where id=#{id}")
	void updateAddress2(MyAddress myAddress);

	@Update("update my_area set address3 = #{address3} where id=#{id}")
	void updateAddress3(MyAddress myAddress);

	@Select("select * from my_area where id=#{id}")
	MyAddress checkMyAddress(String id);

	@Select("select address1, address2, address3 from my_area where id=#{id}")
	MyAddress getMyPageAddressList(String id);

	@Update("update my_area set address2 = (select address3 from my_area where id= #{id}),address3 = null where id = #{id}")
	void removeAddress2(String id);

	@Update("update my_area set address3 = null where id=#{id}")
	void removeAddress3(String id);

	@Delete("delete friend where id = #{id} and friend_id = #{friendId}")
	void deleteFriend(@Param("id") String id, @Param("friendId") String friendId);

	@Update("update member_c set password = #{password}, name = #{name}, tell = #{tell}, email = #{email}, company = #{company}, address = #{address}, old_address = #{oldAddress}, is_permit=2 , is_modify=1, modify_date=current_date, permit_date=current_date where id = #{id}")
	void updateCompanyInfo(JoinForm form);

	@Select("select * from link_member where c_id = #{id}")
	LinkMember selectLink(String id);

	void updateCompanyField(@Param("id") String id, @Param("fields") List<String> fields);

	@Insert("insert into file_info(file_idx, file_category, origin_name, re_name, save_path, type_idx) "
			+ "values(sc_file_idx.nextval, 'MEMBER_C', #{originName}, #{reName}, #{savePath}, #{typeIdx})")
	void uploadFile(FileDTO fileDTO);

	@Update("update wallet set cash = cash + #{cash} where id = #{id}")
	void addCashToWallet(@Param("id") String id, @Param("cash") int cash);

	@Update("update wallet set cash_lock = cash_lock - #{cash} where id = #{id}")
	void substractCashAndCashLock(@Param("id") String id, @Param("cash") int cash);

	@Update("update wallet set cash = cash + #{cash}, cash_lock = cash_lock - #{cash} where id = #{id}")
	void substractCashLock(@Param("id") String id, @Param("cash") int cash);

	@Select("select count(*) from help_response where id = #{id}")
	Integer selectAllResponse(@Param("id")String id);

	@Select("select count(*) from help_match where ongoing = 2 and res_idx in(select res_idx from help_response where id = #{id})")
	Integer selectCompleteResponse(@Param("id")String id);

	@Select("select score from help_match where score is not null and res_idx in (select res_idx from help_response where id = #{id})")
	List<Double> getScore(@Param("id") String id);

	@Select("select re_content, count(*) as count from help_review where help_idx in (select help_idx from help_match where res_idx in (select res_idx from help_response where id = #{id})) group by re_content")
	List<Map<String, Object>> getReview(@Param("id") String id);
	
	@Select("SELECT count(*) FROM PURCHASE_REGISTER A JOIN PURCHASE_MATCH B ON (A.REG_IDX = B.REG_IDX) JOIN PURCHASE_JOIN C USING(JOIN_IDX) WHERE C.ID= #{id} and ongoing = 2")
	String getParticipationCount(@Param("id") String id);
	
	@Select("select count(*) from (SELECT DISTINCT REG_IDX, done FROM PURCHASE_REGISTER JOIN PURCHASE_MATCH USING (REG_IDX) WHERE ID = #{id} and done = 'Y')")
	String getRecruitmentCount(@Param("id") String id);

	@Select("select is_permit from member_c where id = #{id}")
	int selectStatus(String id);

	@Select("select * from pro_field where field not in(select field from company_field where id = #{id})")
	ArrayList<FieldForm> selectFieldExceptPermit(@Param("id")String id);
	
	
	
}