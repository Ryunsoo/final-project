package com.kh.hehyeop.mypage.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kh.hehyeop.member.model.repository.MemberRepository;

@Component
public class MypageValidator implements Validator{
	
	private MemberRepository memberRepository;
	
	public MypageValidator(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return JoinForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		JoinForm form = (JoinForm) target;
		boolean valid = true;
		
		if(form.getPassword() != null) {
			// 2. 비밀번호가 8글자 이상, 숫자 영문자 특수문자 조합인지 확인
			valid = Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}", form.getPassword());
			
			if(!valid) {
				errors.rejectValue("password", "error-password", "다시 입력해주세요");
			}
		}
		
		if(form.getName() != null) {
			// 2. 비밀번호가 8글자 이상, 숫자 영문자 특수문자 조합인지 확인
			valid = Pattern.matches("^[가-힣]{2,4}$", form.getName());
			
			if(!valid) {
				errors.rejectValue("name", "error-name", "이름은 2~4글자의 한글만 가능합니다.");
			}
		}
		
		
		if(form.getTell() != null) {
			// 3. 휴대폰 존재 유무
			valid = Pattern.matches("^\\d{9,11}$", form.getTell());
			
			if(!valid) {
				errors.rejectValue("tell", "error-tell", "전화번호는 9~11자리의 숫자입니다.");
			}
		}
	
		
		if(form.getEmail() != null) {
			valid = Pattern.matches("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}", form.getEmail());
			
			if (!valid) {
				errors.rejectValue("email", "error-email", "이메일 형식을 확인하세요!");
			}
		}
		
		if(form.getNickname() != null) {
			valid = Pattern.matches("^[\\w\\Wㄱ-ㅎㅏ-ㅣ가-힣]{2,10}$", form.getNickname());
			
			if (!valid) {
				errors.rejectValue("nickname", "error-nickname", "닉네임은 2~10자리만 가능합니다.");
			}
		}
	}

}
