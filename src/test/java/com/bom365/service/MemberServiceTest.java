package com.bom365.service;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import com.bom365.dto.MemberDto;
import com.bom365.entity.Member;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
class MemberServiceTest {
	@Autowired
	MemberService memberService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	public Member createMember(String id, String password) {
	 	MemberDto memberFormDto = new MemberDto();
        memberFormDto.setSupporterId(id);
        memberFormDto.setSupporterName("홍길동");
        memberFormDto.setPostCode("12345");
        memberFormDto.setPhoneNumber("010-1234-5678");
        memberFormDto.setDetailedAddress("서울시 마포구 합정동");
        memberFormDto.setSupporterPassword(password);;
        
        //Member member = Member.createMember(memberFormDto);
        //System.out.println(member.toString());
        
        return Member.createMember(memberFormDto,passwordEncoder);
}
	@Test
	@DisplayName("회원가입 테스트")
	void testSaveMember() {
		String id = "test";
        String password= "testPassword";
		 Member member = createMember(id,password);
	     Member savedMember = memberService.saveMember(member);
	     
	     assertEquals(member.getSupporterId(), savedMember.getSupporterId());
	}

}
