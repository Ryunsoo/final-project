package com.kh.hehyeop.company.model.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.kh.hehyeop.common.util.paging.Paging;
import com.kh.hehyeop.common.util.file.FileDTO;
import com.kh.hehyeop.company.model.dto.CompanyField;
import com.kh.hehyeop.company.model.dto.MyRequest;
import com.kh.hehyeop.company.model.dto.RequestDetail;
import com.kh.hehyeop.company.validator.ResponseForm;
import com.kh.hehyeop.help.model.dto.HelpRequest;
import com.kh.hehyeop.help.model.dto.HelpResponse;
import com.kh.hehyeop.mypage.model.dto.MyAddress;

@Mapper
public interface CompanyRepository {
	
	@Select("select address1, address2, address3 from my_area where id = #{id}")
	MyAddress selectMyAreaList(String id);

	@Select("select field from company_field where id = #{id} and is_permit = 1")
	List<CompanyField> selectCompanyFieldListById(String id);
	
	List<HelpRequest> selectRequestList(@Param("paging")Paging paging,@Param("addressList")List<String> addressList
										, @Param("companyFieldList") List<CompanyField> companyFieldList
										, @Param("area")String area, @Param("id") String id);


	@Select("select * from help_response where c_id = #{id}")
	List<HelpResponse> selectResponseList(@Param("id") String id);

	List<MyRequest> selectRequestListById(@Param("paging")Paging paging, @Param("id") String id, @Param("state") String state);


	@Select("select * from v_request_detail where req_idx = #{reqIdx}")
	RequestDetail selectRequestDetailByReqIdx(@Param("reqIdx") String reqIdx);

	int countRequest(@Param("addressList")List<String> addressList
					, @Param("companyFieldList")List<CompanyField> companyFieldList, @Param("area") String area, @Param("id") String id);

	@Update("update help_request set ongoing = 1 where req_idx = #{reqIdx}")
	void updateRequestOngoing(@Param("reqIdx") String reqIdx);
	
	@Update("update help_response set ongoing = 1 where req_idx = #{reqIdx} and id = #{id}")
	void updateResponseOngoing(@Param("reqIdx") String reqIdx, @Param("id")String id);

	int selectRequestListCntById(@Param("id") String id, @Param("state") String state);

	@Update("update help_response set ongoing = #{state} where id = #{id} and req_idx = #{reqIdx}")
	void updateOngoing(@Param("id") String id, @Param("reqIdx") String reqIdx, @Param("state") int state);
	
	@Select("select id, ongoing from help_request where req_idx = #{reqIdx}")
	HelpRequest selectOngoingByReqIdx(@Param("reqIdx") String reqIdx);
	
	@Select("select id, ongoing from help_request where req_idx = #{reqIdx}")
	HelpRequest selectIdAndOngoingByReqIdx(@Param("reqIdx") String reqIdx);
	
	@Select("select res_pay from help_response where req_idx = #{reqIdx} and ongoing=2")
	int selectResPayByReqIdxComplete(@Param("reqIdx") String reqIdx);
	
	@Select("select res_pay from help_response where req_idx = #{reqIdx} and ongoing=3")
	int selectResPayByReqIdxCancel(@Param("reqIdx") String reqIdx);
	
	@Update("update wallet set cash= cash + #{resPay} where id = #{id}")
	void completeCashByReqIdx(@Param("id") String id, @Param("resPay") int resPay);

	@Insert("insert into help_response(res_idx, id, req_idx, res_time, res_pay, res_content) "
			+ "values(sc_c_idx.nextval, #{id}, #{reqIdx}, #{form.resTime}, #{form.resPrice}, #{form.resContent})")
	void insertHelpResponse(@Param("form") ResponseForm form, @Param("reqIdx") String reqIdx, @Param("id") String id);

	@Select("select res_idx from help_response where req_idx = #{reqIdx} and id = #{id}")
	String selectResIdxByReqIdxAndId(@Param("reqIdx") String reqIdx, @Param("id") String id);

	@Insert("insert into file_info(file_idx, file_category, origin_name, re_name, save_path, type_idx) "
			+ "values(sc_file_idx.nextval, 'HELP_RESPONSE', #{originName}, #{reName}, #{savePath}, sc_c_idx.currval)")
	void insertFileInfo(FileDTO fileUpload);

	@Select("select id from help_request where req_idx = #{reqIdx}")
	String selectReqIdByReqIdx(@Param("reqIdx") String reqIdx);

	@Update("update wallet set cash= cash + #{resPay}, cash_lock = cash_lock - #{resPay} where id = #{reqId}")
	void cancelCashByReqIdx(@Param("reqId") String reqId, @Param("resPay") int resPay);
	
	@Select("select * from help_request where req_idx in (select req_idx from help_response where id = #{id} and ongoing = #{state})")
	List<MyRequest> selectDisMatchRequestListById(@Param("id") String id, @Param("state") int state);

	@Select("select pay_status from help_match where req_idx = #{reqIdx}")
	int selectPayStatus(@Param("reqIdx")String reqIdx);

}
