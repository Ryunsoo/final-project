package com.kh.hehyeop.management.model.repository;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.kh.hehyeop.management.model.dto.Expense;
import com.kh.hehyeop.management.model.dto.FExpense;
import com.kh.hehyeop.management.model.dto.Icebox;
import org.apache.ibatis.annotations.Update;
import com.kh.hehyeop.management.model.dto.ShoppingList;
import com.kh.hehyeop.management.validator.FixedForm;
import com.kh.hehyeop.management.validator.PersonalForm;

@Mapper
public interface ManagementRepository {

	@Select("select v.*, floor(EX_DATE - sysdate) res_day from icebox v where id = #{id} and category=#{category} order by ex_date")
	List<Icebox> selectIceboxList(@Param("id")String id, @Param("category") int category);

	@Select("select * from shopping_list where id = #{id} and status = 1")
	List<ShoppingList> selectPurchaseListById(@Param("id") String id);
	
	@Select("select * from shopping_list where id = #{id} and status = 0")
	List<ShoppingList> selectExhaustListById(@Param("id") String id);

	@Delete("delete from shopping_list where shopping_idx = #{shoppingIdx}")
	int deleteItemByShoppingIdx(@Param("shoppingIdx") String shoppingIdx);

	@Insert("insert into shopping_list(shopping_idx, id, item, status) values(sc_shopping_idx.nextval, #{id}, #{item}, 1)")
	int insertInputItem(@Param("id") String id, @Param("item") String item);

	@Update("update shopping_list set status = 1 where id = #{id} and shopping_idx = #{shoppingIdx}")
	int updateItemStatus(@Param("id") String id, @Param("shoppingIdx") String shoppingIdx);

	@Insert("insert into expense values(sc_expense_idx.nextval, #{form.category}, #{id}, #{form.content}, #{form.price}, #{form.expDate})")
	void insertPersonalSpend(@Param("id")String id, @Param("form") PersonalForm form);

	void insertFixedSpend(@Param("id")String id, @Param("form")FixedForm form);

	@Select("select * from f_expense where id = #{id} and to_date(end_date) > current_date order by fixed_date asc")
	List<FExpense> selectFExpenseList(@Param("id") String id);

	@Insert("insert into icebox values(sc_ice_idx.nextval, #{id}, #{item}, #{category}, #{date}, current_date)")
	int insertIceboxItem(@Param("id")String id, @Param("item")String item, @Param("date")String date, @Param("category")int category);

	@Insert("insert into shopping_list values(SC_SHOPPING_IDX.nextval, #{id}, #{item}, 0)")
	void insertShoppingItem(@Param("id")String id, @Param("item")String item);

	@Delete("delete from icebox where ice_idx = #{iceIdx}")
	void deletedeleteIceboxItem(@Param("iceIdx")String iceIdx);

	@Select("select v.*, floor(EX_DATE - sysdate) res_day from icebox v where id = #{id} and floor(EX_DATE - sysdate) <= 3 order by ex_date")
	List<Icebox> selectIceboxBellList(@Param("id")String id);

	@Select("select count(*) from icebox v where id = #{id} and floor(EX_DATE - sysdate) <= 3")
	int selectBellCnt(@Param("id")String id);

	List<Expense> selectExpenseList(@Param("id") String id, @Param("cate") String cate, @Param("period") String period);

	@Select("select * from expense where id = #{id} and exp_date like #{date}||'%'")
	List<Expense> selectPersonalExpense(@Param("id") String id, @Param("date") String date);
	
	@Select("select f_exp_idx, 'FIXED' as category, id, content, fixed_date, start_date, end_date, concat(concat(#{date}, '-'), fixed_date) as exp_date, price "
			+ "from f_expense where id = #{id} and to_date(concat(concat(#{date}, '-'), fixed_date)) >= to_date(start_date) "
			+ "and to_date(concat(concat(#{date}, '-'), fixed_date)) <= to_date(end_date)")
	List<FExpense> selectFixedExpense(@Param("id") String id, @Param("date") String date);

	@Update("update expense set category = #{form.category}, content = #{form.content}, price = #{form.price}, exp_date = #{form.expDate} where exp_idx = #{form.expIdx}")
	void updatePersonalExpense(@Param("form") PersonalForm form);

	@Update("update f_expense set content = #{form.content}, fixed_date = #{form.fixedDate}, "
			+ "start_date = #{form.startDate}, end_date = #{form.endDate}, price = #{form.price} where f_exp_idx = #{form.fExpIdx}")
	void updateFixedExpense(@Param("form") FixedForm form);

	@Delete("delete from expense where exp_idx = #{expIdx}")
	void deletePersonalExpense(@Param("expIdx") String expIdx);

	@Delete("delete from f_expense where f_expense_idx = #{expIdx}")
	void deleteFixedExpense(@Param("expIdx") String expIdx);

	@Select("select content from f_expense where id = #{id} and concat(#{monthStr}, fixed_date) = to_char(current_date, 'YYYY-MM-DD') "
			+ "and to_date(concat(#{monthStr}, fixed_date)) >= to_date(start_date) "
			+ "and to_date(concat(#{monthStr}, fixed_date)) <= to_date(end_date)")
	List<String> selectTodayFixedExpense(@Param("id") String id, @Param("monthStr") String monthStr);
	
	
	
	
	
}
