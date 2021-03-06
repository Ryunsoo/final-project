package com.kh.hehyeop.common.push;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.hehyeop.member.model.dto.Member;
import com.kh.hehyeop.member.model.dto.User;
import com.kh.hehyeop.mypage.model.service.MypageService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("push")
@RequiredArgsConstructor
public class PushHandler {

	private final MypageService mypageService;
	
	@GetMapping("save-token")
	@ResponseBody
	public String saveToken(String token, HttpSession session, String device){		
		User user = (User) session.getAttribute("authentication");
			mypageService.insertToken(token,device,user.getId());

		return "success";
	}
	
	@GetMapping("delete-token")
	@ResponseBody
	public String deleteToken(HttpSession session, String device) {
		User user = (User) session.getAttribute("authentication");
		mypageService.deleteToken(device,user.getId());

		return "success";
	}
	
	
}
