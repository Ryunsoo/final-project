package com.kh.hehyeop.management.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.kh.hehyeop.management.model.dto.Icebox;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.hehyeop.common.sms.Coolsms;
import com.kh.hehyeop.management.model.dto.ShoppingList;
import com.kh.hehyeop.management.model.service.ManagementService;
import com.kh.hehyeop.management.validator.PersonalForm;
import com.kh.hehyeop.member.model.dto.Member;

import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

@Controller
@RequiredArgsConstructor
@RequestMapping("management")
public class managementController {

	private final ManagementService managementService;

	@GetMapping("myIcebox")
	public void test() {
	}

	@GetMapping("myIcebox_note")
	public void test2(HttpSession session, Model model, 
						@RequestParam(value = "category", required = false) String category) {
		
		if(category == null) category = "0";
		Member member = (Member) session.getAttribute("authentication");
		
		List<Icebox> iceboxUpList = managementService.selectIceboxUpList(member.getId(), category);
		List<Icebox> iceboxDownList = managementService.selectIceboxDownList(member.getId(), category);
		
		System.out.println("sdfsdfsdfsdfsd : " + iceboxUpList);
		System.out.println("sdfsdfsdfsdfsd : " + iceboxDownList);
		
		session.setAttribute("category",category);
		model.addAttribute("iceboxUpList", iceboxUpList);
		model.addAttribute("iceboxDownList", iceboxDownList);
		
	}

	@GetMapping("myIcebox_modify")
	public void test3() {
	}

	@GetMapping("myIcebox_cart")
	public void cart(HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("authentication");
		Map<String, List<ShoppingList>> shoppingList = managementService.selectShoppingListById(member.getId());
		model.addAllAttributes(shoppingList);
	}

	@GetMapping("deleteItem")
	public String deleteItem(String shoppingIdx, RedirectAttributes redirectAttr) {
		int res = managementService.deleteItem(shoppingIdx);
		if (res == 1)
			redirectAttr.addFlashAttribute("message", "해당 항목이 삭제되었습니다.");
		return "redirect:/management/myIcebox_cart";
	}

	@GetMapping("insertInputItem")
	public String insertInputItem(HttpSession session, String item, RedirectAttributes redirectAttr) {
		Member member = (Member) session.getAttribute("authentication");
		int res = managementService.insertInputItem(member.getId(), item);
		if (res == 1)
			redirectAttr.addFlashAttribute("message", "해당 항목이 추가되었습니다.");
		return "redirect:/management/myIcebox_cart";
	}

	@PostMapping("moveCheckedItem")
	public String moveCheckedItem(HttpSession session, String[] checkedItemIdx, RedirectAttributes redirectAttr) {
		Member member = (Member) session.getAttribute("authentication");
		// 체트된 항목들 shoppingIdx 배열
		String[] idxArr = checkedItemIdx;
		int res = managementService.moveCheckedItem(member.getId(), idxArr);
		if (res == 1)
			redirectAttr.addFlashAttribute("message", "해당 항목이 이동되었습니다.");
		return "redirect:/management/myIcebox_cart";
	}

	@PostMapping("sendSMS")
	public String sendSMS(HttpServletRequest request, @RequestParam(value = "item", required = false) String[] item, int tell, String date, RedirectAttributes redirectAttr) throws Exception {
		String tellStr = "0"+Integer.toString(tell);
		String text ="";
		for (String itemIdx : item) {
			text += itemIdx+" ";
		}
		date = date.replace("T", ""); 
		date = date.replace(":", ""); 
		date = date.replace("-", ""); 
		System.out.println("date : " + date);
		String api_key = "NCSCLG5MVOMXL3QG"; String api_secret = "Z3FL84I3T3HPYEEXARZJC5CXHWKKJ2OJ"; 
		Coolsms coolsms = new Coolsms(api_key, api_secret);
		HashMap<String, String> set = new HashMap<String, String>(); 
		set.put("to",tellStr); // 수신번호
		  
		set.put("from", "01050211937"); // 발신번호 
		set.put("text", text); // 문자내용
		set.put("type", "sms"); // 문자 타입 
		set.put("datetime", date); //예약전송시 날짜 설정 
		System.out.println(set);
		  
		JSONObject result = coolsms.send(set); // 보내기&전송결과받기
		redirectAttr.addFlashAttribute("message", "문자가 발송되었습니다.");
		return "redirect:/management/myIcebox_cart";
	}

	@GetMapping("myAccountBook")
	public void myAccountBookForm() {
		
	}
	
	@GetMapping("personal_spend")
	public String savePersonalSpend(PersonalForm form) {
		System.out.println(form);
		
		return "redirect:/management/myAccountBook";
	}
	
	@GetMapping("fixed_spend")
	public void saveFixedSpend() {
		
	}

	@GetMapping("myAccountList")
	public void test6() {}
	
	@GetMapping("plusItem")
	@ResponseBody
	public void plusItem(HttpSession session, String item, String date) throws ParseException {
		
		Member member = (Member) session.getAttribute("authentication");
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.parse(date);
	}	
}
