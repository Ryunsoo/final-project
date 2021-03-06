package com.kh.hehyeop.common.interceptor;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.hehyeop.common.code.ErrorCode;
import com.kh.hehyeop.common.exception.HandlableException;
import com.kh.hehyeop.member.model.dto.Admin;
import com.kh.hehyeop.member.model.dto.CMember;
import com.kh.hehyeop.member.model.dto.Member;
import com.kh.hehyeop.member.model.dto.User;

public class AuthInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Object handler) {
		String[] uriArr = httpRequest.getRequestURI().split("/");
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("authentication");
		
		if(uriArr.length == 0 && user instanceof CMember) {
			throw new HandlableException(ErrorCode.COMPANY_LOGIN_ERROR);
		}
		
		if(uriArr.length != 0) {
			switch (uriArr[1]) {
			case "chat":
				chatAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "member":
				//memberAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "admin":
				adminAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "community":
				communityAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "mypage":
				mypageAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "help":
				helpAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "purchase":
				purchaseAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "company":
				companyAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "management":
				managementAuthorize(httpRequest, httpResponse, uriArr);
				break;
			default:
				break;
			}
		}
		
		//다음 인터셉터 또는 컨트롤러에게 요청을 전달
		return true;
	}
	

	private void communityAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("authentication");
		
		if(user == null) {
			throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
		}
		
		if(user instanceof CMember) {
			throw new HandlableException(ErrorCode.COMPANY_LOGIN_ERROR);
		}
		
	}


	private void managementAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			String[] uriArr) {
		
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("authentication");
		
		if(user == null) {
			throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
		}
		
		if(user instanceof CMember) {
			throw new HandlableException(ErrorCode.COMPANY_LOGIN_ERROR);
		}
		
	}


	private void adminAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("authentication");
		
		if (user == null) {
			throw new HandlableException(ErrorCode.ADMIN_LOGIN_ERROR);
		}
		
		if(!(user instanceof Admin)) {
			throw new HandlableException(ErrorCode.ADMIN_LOGIN_ERROR);
		}
		
	}


	private void companyAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("authentication");
		
		if(user == null) {
			throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
		}
		
		if(user instanceof Member) {
			throw new HandlableException(ErrorCode.NORMAL_LOGIN_ERROR);
		}
	}


	private void purchaseAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("authentication");
		
		if(user == null) {
			throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
		}
		
		if(user instanceof CMember) {
			throw new HandlableException(ErrorCode.COMPANY_LOGIN_ERROR);
		}
		
	}


	private void helpAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("authentication");
		
		if(user instanceof CMember) {
			throw new HandlableException(ErrorCode.COMPANY_LOGIN_ERROR);
		}
		
		//if(uriArr[2] != "main" && user == null) throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
		
		switch (uriArr[2]) {
		case "request":
			if(user == null) {
				throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
			}
			break;
		case "review":
			if(user == null) {
				throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
			}
			break;
		case "my-hehyeop":
			if(user == null) {
				throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
			}
			break;
		default:
			break;
		}
		
	}

	private void mypageAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("authentication");
		
		switch (uriArr[2]) {
		case "mypage-common":
			if(user == null) {
				throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
			}else if(user instanceof CMember) {
				throw new HandlableException(ErrorCode.COMPANY_LOGIN_ERROR);
			}
			break;
			
		case "mypage-company":
			if(user == null) {
				throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
			}else if(user instanceof Member) {
				throw new HandlableException(ErrorCode.NORMAL_LOGIN_ERROR);
			}
			break;
			
		case "change":
			if(user == null) {
				throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
			}else if(user instanceof Member) {
				throw new HandlableException(ErrorCode.NORMAL_LOGIN_ERROR);
			}
			break;
		case "company-modifyInfo":
			CMember cmember = (CMember) session.getAttribute("authentication");
			
			if(user == null) {
				throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR);
			}else if(user instanceof Member) {
				throw new HandlableException(ErrorCode.NORMAL_LOGIN_ERROR);
			}else if(cmember.getIsPermit() != 1) {
				throw new HandlableException(ErrorCode.MYPAGE_ACCESS_ERROR);
			}
			break;
		default:
			break;
		}
	}

	private void chatAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		HttpSession session = httpRequest.getSession();
		User user = (User) session.getAttribute("authentication");
		
		switch (uriArr[2]) {
		case "chat-room":
			if(user == null) {
				throw new HandlableException(ErrorCode.BEFORE_LOGIN_ERROR.setURL(""));
			}
			break;

		default:
			break;
		}
		
		
	}

	private void boardAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		HttpSession session = httpRequest.getSession();
		Member member = (Member) session.getAttribute("authentication");
		
		switch (uriArr[2]) {
		case "board-form":
			if(member == null) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR);
			}
			break;
		case "upload":
			if(member == null) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR);
			}
			break;
		default:
			break;
		}
	}

	/*
	 * private void adminAuthorize(HttpServletRequest httpRequest,
	 * HttpServletResponse httpResponse, String[] uriArr) {
	 * 
	 * HttpSession session = httpRequest.getSession();
	 * Member member = (Member) session.getAttribute("authentication");
	 * 
	 * //비회원과, 사용자 회원인지를 판단
	 * if(member == null ||
	 * MemberGrade.valueOf(member.getGrade()).ROLE.equals("user")) {
	 * throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR);
	 * }
	 * //슈퍼관리자라면 모든 admin 페이지에 접근할 수 있다.
	 * if(MemberGrade.valueOf(member.getGrade()).DESC.equals("super")) {
	 * return;
	 * }
	 * 
	 * switch (uriArr[2]) {
	 * case "member":
	 * if(!MemberGrade.valueOf(member.getGrade()).DESC.equals("member")) {
	 * throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR);
	 * }
	 * break;
	 * case "board":
	 * if(!MemberGrade.valueOf(member.getGrade()).DESC.equals("board")) {
	 * throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR);
	 * }
	 * break;
	 * default:
	 * break;
	 * }
	 * }
	 */

	private void memberAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		HttpSession session = httpRequest.getSession();
		
		switch (uriArr[2]) {
		case "mypage":
			if(session.getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR);
			}
			break;
		default:
			break;
		}
		
	}
	
}
