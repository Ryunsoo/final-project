package com.kh.hehyeop.management.model.service;

import java.util.List;

import com.kh.hehyeop.management.model.dto.Expense;
import com.kh.hehyeop.management.model.dto.FExpense;
import com.kh.hehyeop.management.model.dto.Icebox;
import java.util.Map;

import com.kh.hehyeop.management.model.dto.ShoppingList;
import com.kh.hehyeop.management.validator.FixedForm;
import com.kh.hehyeop.management.validator.PersonalForm;

public interface ManagementService {

	List<Icebox> selectIceboxList(String id, int category);

	Map<String, List<ShoppingList>> selectShoppingListById(String id);

	int deleteItem(String shoppingIdx);

	int insertInputItem(String id, String item);

	int moveCheckedItem(String id, String[] idxArr);

	void insertPersonalSpend(String id, PersonalForm form);

	void insertFixedSpend(String id, FixedForm form);
	
	List<FExpense> selectFExpenseList(String id);

	int insertIceboxItem(String id, String item, String date, int category);

	String deleteIceboxItem(String iceIdx, int status, String id, String item);

	List<Icebox> selectIceboxBellList(String id);

	int selectBellCnt(String id);

	List<Expense> selectExpenseList(String id, String cate, String period);

	List<Map<String, Object>> selectEvents(String id, String date);

	void updatePersonalSpend(PersonalForm form);

	void updateFixedSpend(FixedForm form);

	void deletePersonalSpend(String expIdx);

	void deleteFixedSpend(String expIdx);

	List<String> selectTodayFixedSpend(String id);

}
