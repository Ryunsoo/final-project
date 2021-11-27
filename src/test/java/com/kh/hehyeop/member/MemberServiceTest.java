package com.kh.hehyeop.member;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
public class MemberServiceTest {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Test
	public void passwordEncoderTest() {
		String password = "xptmxm123!";
		String encodedPassword = passwordEncoder.encode(password);
		System.out.println(encodedPassword);
		encodedPassword = passwordEncoder.encode(password);
		System.out.println(encodedPassword);
		
		System.out.println("matches 결과 : " + passwordEncoder.matches(password, encodedPassword));
	}
	
	
}
