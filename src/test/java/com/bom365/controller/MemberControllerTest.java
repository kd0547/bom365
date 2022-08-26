package com.bom365.controller;



import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.bom365.dto.MemberDto;
import com.bom365.entity.Member;
import com.bom365.service.MemberService;


@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
class MemberControllerTest {
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	private MockMvc mockMvc;
	
	public Member createMember(String id, String password) {
		 	MemberDto memberFormDto = new MemberDto();
	        memberFormDto.setSupporterId(id);
	        memberFormDto.setSupporterName("홍길동");
	        memberFormDto.setPostCode("12345");
	        memberFormDto.setPhoneNumber("010-1234-5678");
	        memberFormDto.setDetailedAddress("서울시 마포구 합정동");
	        memberFormDto.setSupporterPassword(password);;
	        
	        Member member = Member.createMember(memberFormDto,passwordEncoder);
	        System.out.println(member.toString());
	        
	        return memberService.saveMember(member);
	}
 
   

	@Test
	@DisplayName("로그인 성공 테스트")
	void testSignup() throws Exception{
		String id = "test";
        String password= "testPassword";
        
        this.createMember(id, password);
        
        
        mockMvc.perform(
        		formLogin()
        		.loginProcessingUrl("/member/login")
        		.userParameter("supporterId")
        		.passwordParam("supporterPassword")  
        		.user(id).password(password))
        		.andExpect(SecurityMockMvcResultMatchers.authenticated())
        		;
	}

	 @Test
	 @DisplayName("로그인 실패 테스트")
	 public void loginFailTest() throws Exception {
		 String email = "test";
	     String password = "testPassword";

	     this.createMember(email, password);

	     mockMvc.perform(
	    		 formLogin()
	    		 .loginProcessingUrl("/members/login")
	    		 .userParameter("supporterId")
	    		 .passwordParam("supporterPassword") 
	             .user(email).password("12345"))
	             .andExpect(SecurityMockMvcResultMatchers.unauthenticated());
	    }

}
