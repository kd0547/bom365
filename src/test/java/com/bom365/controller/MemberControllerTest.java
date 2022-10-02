package com.bom365.controller;



import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.savedrequest.RequestCacheAwareFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
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
		
		CsrfFilter csrfFilter;
		LogoutFilter logoutFilter;
		UsernamePasswordAuthenticationFilter authenticationToken;
		RequestCacheAwareFilter requestCacheAwareFilter;
		SecurityContextHolderAwareRequestFilter securityContextHolderAwareRequestFilter;
		AnonymousAuthenticationFilter anonymousAuthenticationFilter;
		
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
