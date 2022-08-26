package com.bom365.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bom365.constant.Duplicate;
import com.bom365.dto.CheckIdDto;
import com.bom365.dto.DuplicateDto;
import com.bom365.dto.MemberDto;
import com.bom365.dto.MemberFormDto;
import com.bom365.entity.Member;
import com.bom365.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping(value="/login")
	public String loginForm() {
		
		return "member/loginForm";
	}
	//@ResponseBody ResponseEntity
	@PostMapping(value="/duplicate")
	public @ResponseBody DuplicateDto validateDuplicateId(@Valid CheckIdDto id, BindingResult bindingResult ) {
		/*
		 * 유효성 검사
		 */
		if(bindingResult.hasErrors()) {
			DuplicateDto duplicateDto = 
					new DuplicateDto(HttpStatus.OK,200,bindingResult.getFieldError("id").getDefaultMessage(),null);
			
			
			return duplicateDto;
		}
		
		
		try {
			Member member = new Member();
			member.setSupporterId(id.getId());
			
			memberService.validateDuplicateMember(member);
			
			
		} catch (IllegalStateException e) {
			DuplicateDto duplicateDto = 
					new DuplicateDto(HttpStatus.OK,200,e.getMessage(),Duplicate.Duplicate);
			
			return duplicateDto;
		}
		
		DuplicateDto duplicateDto = 
				new DuplicateDto(HttpStatus.OK,200,"가입 가능한 아이디입니다.",Duplicate.NotDuplicate);
		
		
		
		return duplicateDto;
	}
	
	
	
	
	
	
	@PostMapping(value="/signup")
	public String signup(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,Model model) {
		System.out.println(memberFormDto.getDuplicate());
		if(bindingResult.hasErrors()) {
			return "member/signupForm";
		}
		
		new Member();
		MemberDto memberDto= new MemberDto();
		memberDto.setSupporterId(memberFormDto.getSupporterId());
		memberDto.setSupporterName(memberFormDto.getSupporterId());
		memberDto.setSupporterPassword(memberFormDto.getSupporterPassword());
		memberDto.setPostCode(memberFormDto.getPostCode());
		memberDto.setPhoneNumber(memberFormDto.getPhoneNumber());
		memberDto.setDetailedAddress(memberFormDto.getDetailedAddress());
		
		Member member = Member.createMember(memberDto, passwordEncoder);
		
		System.out.println(member.toString());
	
		//memberService.saveMember(member);
		
		return "member/signupDone";
	}
	
	
	
	
	
	
	@GetMapping(value="/signup")
	public String signup(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		
		
		
		return "member/signupForm";
	}
	
	
	
	@GetMapping(value="/mypage")
	public String updateMember() {
		
		return "/member/mypageForm";
	}
	
	
	@GetMapping(value="/login/error")
	public String loginError(Model model) {
		
		model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
		return "/member/loginForm";
	}
	
	
}
