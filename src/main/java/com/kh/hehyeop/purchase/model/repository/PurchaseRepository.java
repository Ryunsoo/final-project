package com.kh.hehyeop.purchase.model.repository;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kh.hehyeop.purchase.model.dto.MyPurchaseInfo;
import com.kh.hehyeop.common.util.file.FileDTO;
import com.kh.hehyeop.purchase.model.dto.RegisterInfo;
import com.kh.hehyeop.purchase.validator.RegisterForm;

@Mapper
public interface PurchaseRepository {

	@Select("select join_idx, reg_idx, match_idx, PR.id, item_name, deal_loc, deal_time, ongoing, join_buy_num, PJ.id AS buyer_id, nickname, name, tell from purchase_register PR left join purchase_match PM"
			+ " using(reg_idx) left join purchase_join PJ using(join_idx) left join Member M on(M.id = PJ.id) where PR.id = #{id} OR PJ.id = #{id}")
	List<MyPurchaseInfo> selectMyPurchaseInfo(String id);

	@Insert("insert into purchase_register(reg_idx, id, item_name, item_link, deal_loc, end_time, deal_time, price, total_num, buy_num, content) "
			+ "values (sc_reg_idx.nextval, #{id}, #{itemName}, #{itemLink}, #{dealLoc}, #{endTime}, #{dealTime}, #{price}, #{totalNum}, #{buyNum}, #{content})")
	int registerInfo(RegisterForm form);

	@Select("select reg_idx from purchase_register p where rownum = 1 order by reg_idx desc")
	String selectRegIdx();

	@Insert("insert into file_info(file_idx, file_category, origin_name, re_name, save_path, type_idx) "
			+ "values(sc_file_idx.nextval, 'PURCHASE', #{originName}, #{reName}, #{savePath}, #{typeIdx})")
	int uploadFile(FileDTO fileUpload);

	@Select("select * from purchase_register where reg_idx = #{regIdx}")
	MyPurchaseInfo selectPurchaseInfoByIdx(@Param("regIdx") String regIdx);

	@Select("select * from purchase_register where done = 'N' order by reg_idx desc")
	List<RegisterInfo> selectRegisterList();

	@Select("select * from file_info where file_category = 'PURCHASE' order by type_idx desc")
	List<FileDTO> selectRegisterFileList();

}