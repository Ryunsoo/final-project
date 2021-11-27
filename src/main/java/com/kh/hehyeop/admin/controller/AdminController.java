package com.kh.hehyeop.admin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.hehyeop.admin.model.dto.CMember;
import com.kh.hehyeop.admin.model.service.AdminService;
import com.kh.hehyeop.common.sms.Coolsms;
import com.kh.hehyeop.common.util.paging.Paging;
import com.kh.hehyeop.member.model.service.MemberService;
import com.kh.hehyeop.member.validator.FieldForm;
import com.kh.hehyeop.mypage.model.service.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final AdminService adminService;
	private final MemberService memberService;
	private final MypageService mypageService;
	
	@GetMapping("join-request")
	public void joinRequestForm(Model model, Paging paging, 
			  @RequestParam(value="nowPage", required = false) String nowPage,
			  @RequestParam(value="cntPerPage", required = false) String cntPerPage) {
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "11";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "11";
		}
		
		int total = adminService.selectJoinCount();
		paging = new Paging(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		List<CMember> joinRequestList = adminService.selectJoinRequest(paging);
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
	
		for (CMember cMember : joinRequestList) {
			cMember.setParseDate(format.format(cMember.getRegDate()));
		}
		
		model.addAttribute("paging", paging);
		model.addAttribute("joinRequestList", joinRequestList);
		
	}
	
	@GetMapping("modify-request")
	public void modifyRequestForm(Model model, Paging paging, 
								  @RequestParam(value="nowPage", required = false) String nowPage,
								  @RequestParam(value="cntPerPage", required = false) String cntPerPage) {
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "11";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "11";
		}
		
		int total = adminService.selectModifyCount();
		paging = new Paging(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		List<CMember> modifyList = adminService.selectModifyRequest(paging);
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		
		for (CMember cMember : modifyList) {
			cMember.setParseDate(format.format(cMember.getPermitDate()));
		}
		
		model.addAttribute("paging", paging);
		model.addAttribute("modifyList", modifyList);
		
	}
	
	@GetMapping("join-finish-list")
	public void finishListForm(Model model, Paging paging,
			  @RequestParam(value="nowPage", required = false) String nowPage,
			  @RequestParam(value="cntPerPage", required = false) String cntPerPage) {
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "11";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "11";
		}
		
		int total = adminService.selectJoinFinishListCount();
		paging = new Paging(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		List<CMember> joinFinishList = adminService.selectJoinFinishList(paging);
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
	
		for (CMember cMember : joinFinishList) {
			cMember.setParseDate(format.format(cMember.getPermitDate()));
			cMember.setModifyParseDate(format.format(cMember.getModifyDate()));
		}
		
		
		model.addAttribute("paging", paging);
		model.addAttribute("joinFinishList", joinFinishList);
		
		
	}
	
	@GetMapping("modify-finish-list")
	public void modifyFinishListForm(Model model, Paging paging,
			@RequestParam(value="nowPage", required = false) String nowPage,
			@RequestParam(value="cntPerPage", required = false) String cntPerPage) {
		
		if(nowPage == null && cntPerPage == null) {
			nowPage = "1";
			cntPerPage = "11";
		} else if (nowPage == null) {
			nowPage = "1";
		} else if (cntPerPage == null) { 
			cntPerPage = "11";
		}
		
		int modifyTotal = adminService.selectModifyFinishListCount();
		paging = new Paging(modifyTotal, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
		
		List<CMember> modifyFinishList = adminService.selectModifyFinishList(paging);
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		
		for (CMember cMember : modifyFinishList) {
			cMember.setParseDate(format.format(cMember.getPermitDate()));
			cMember.setModifyParseDate(format.format(cMember.getModifyDate()));
		}
		
		
		model.addAttribute("paging", paging);
		model.addAttribute("modifyFinishList", modifyFinishList);
		
		
	}
	
	@GetMapping("approval-first")
	public void approvalFirstForm(@RequestParam(value="id") String id, 
								  @RequestParam(value="cate") String category,
								  HttpSession session) {
		
		Map<String, Object> memberInfo = adminService.selectMemberById(id, category);
		session.setAttribute("memberInfo", memberInfo);
		
	}
	
	@GetMapping("approval-second")
	public void approvalSecondForm(@RequestParam(value="id") String id, Model model) {
		
		List<String> myField = adminService.selectFieldListById(id);
		ArrayList<FieldForm> fieldList = memberService.selectField();
		ArrayList<String> categoryList = memberService.selectCategory();
		
		model.addAttribute("myField", myField);
		model.addAttribute("fieldList", fieldList);
		model.addAttribute("categoryList", categoryList);
		
	}
	
	@PostMapping("permit")
	public String permitInfo(@RequestParam(value="field") List<String> fields, 
							 @RequestParam(value="cate") String category,
							 HttpSession session) {
		
		Map<String, Object> infoMap = (Map<String, Object>) session.getAttribute("memberInfo");
		String id = ((CMember) infoMap.get("member")).getId();
		mypageService.updateCompanyField(id, fields);
		
		if (category.equals("modify")) {
			adminService.updateModify(id);
			return "redirect:/admin/modify-request";
		} else if (category.equals("join")) {
			adminService.updateJoin(id);
			return "redirect:/admin/join-request";
		}
		
		return null;
	}
	
	@PostMapping("cancel-approval")
	public String cancelApproval(HttpSession session) {
		return "redirect:/admin/join-finish-list";
	}
	
	@GetMapping("reject")
	public String rejectInfo(@RequestParam(value = "id") String id, @RequestParam(value="cate") String category, HttpSession session) {
		
		String api_key = "NCSCLG5MVOMXL3QG"; String api_secret = "Z3FL84I3T3HPYEEXARZJC5CXHWKKJ2OJ"; 
		Coolsms coolsms = new Coolsms(api_key, api_secret);
		Map<String, Object> infoMap = (Map<String, Object>) session.getAttribute("memberInfo");
		String tell = ((CMember) infoMap.get("member")).getTell();
		String text = "";
		
		if (category.equals("modify")) {
			adminService.rejectModify(id);
			text = "[자취해협] 수정이 거절되었습니다. 다시 수정해주세요";
			
			HashMap<String, String> set = new HashMap<String, String>(); 
			set.put("to",  "01028422007"); // 수신번호
			set.put("from", "01041147406"); // 발신번호 
			set.put("text", text); // 문자내용
			set.put("type", "sms"); // 문자 타입 
			
			JSONObject result = coolsms.send(set); // 보내기&전송결과받기
			return "redirect:/admin/modify-request";
		} else if (category.equals("join")) {
			adminService.rejectJoin(id);
			text = "[자취해협] 가입이 거절되었습니다. 다시 가입해주세요";
			
			HashMap<String, String> set = new HashMap<String, String>(); 
			set.put("to",  "01028422007"); // 수신번호
			set.put("from", "01041147406"); // 발신번호 
			set.put("text", text); // 문자내용
			set.put("type", "sms"); // 문자 타입 
			
			JSONObject result = coolsms.send(set); // 보내기&전송결과받기
			return "redirect:/admin/join-request";
		}
		
		return null;
	}
}
