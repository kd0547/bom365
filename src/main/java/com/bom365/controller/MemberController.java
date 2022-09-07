package com.bom365.controller;

import java.security.Principal;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bom365.constant.Duplicate;
import com.bom365.dto.CheckIdDto;
import com.bom365.dto.DuplicateDto;
import com.bom365.dto.MemberDto;
import com.bom365.dto.MemberFormDto;
import com.bom365.dto.UpdateMemberFormDto;
import com.bom365.entity.Member;
import com.bom365.repository.MemberRepository;
import com.bom365.service.EmailService;
import com.bom365.service.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
	private final MemberService memberService;
	private final MemberRepository memberRepository;
	private final EmailService emailService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	
	@GetMapping(value="/login")
	public String loginForm() {
		
		return "member/loginForm";
	}
	
	@GetMapping("/findId")
	public String findIdEmail() {
		
		
		return "member/searchId";
	}
	
	
	@PostMapping("/findId")
	public String findIdEmail(@RequestParam String email ,Model model) {
		Member member = new Member();
		
		try {
			member = memberService.findEmail(email);
		} catch (IllegalStateException e) {
			model.addAttribute("findErrorMessage",e.getMessage());
			
			return "member/searchId";
		}
		
		
		emailService.sendIdEmail(member.getEmail(), member.getSupporterId());
		model.addAttribute("findSuccessMessage","이메일을 전송했습니다.");
		
		
		return "member/searchId";
	}
	
	
	
	
	
	//리팩토링 하기
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
	
	
	
	
	
	//리팩토링 
	@PostMapping(value="/signup")
	public String signup(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,Model model) {
		
		
		if(bindingResult.hasErrors() || memberFormDto.isDuplicateCheck() ) {
			return "member/signupForm";
		}
		
		new Member();
		MemberDto memberDto= new MemberDto();
		memberDto.setSupporterId(memberFormDto.getSupporterId());
		memberDto.setSupporterName(memberFormDto.getSupporterName());
		memberDto.setSupporterPassword(memberFormDto.getSupporterPassword());
		memberDto.setPostCode(memberFormDto.getPostCode());
		memberDto.setPhoneNumber(memberFormDto.getPhoneNumber());
		memberDto.setDetailedAddress(memberFormDto.getDetailedAddress());
		
		Member member = Member.createMember(memberDto, passwordEncoder);
		
		
	
		memberService.saveMember(member);
		
		return "member/signupDone";
	}
	
	
	
	
	
	
	@GetMapping(value="/signup")
	public String signup(Model model) {
		model.addAttribute("memberFormDto", new MemberFormDto());
		
		
		
		return "member/signupForm";
	}
	
	
	
	@GetMapping( value="/update")
	public String updateMember(Principal principal, Model model) {
		Member member = memberRepository.findBySupporterId(principal.getName());
		UpdateMemberFormDto updateMemberFormDto = new UpdateMemberFormDto().createMemberFormDto(member);
		
		model.addAttribute("updateMemberFormDto", updateMemberFormDto);
		
		return "/member/mypageForm";
	}
	
	@PostMapping( value="/update")
	public String updateMember(@Valid UpdateMemberFormDto updateMemberFormDto,BindingResult bindingResult, Principal principal, Model model) {
		Member member = memberRepository.findBySupporterId(principal.getName());
		/* 아이디 변경 검사 */
		if(!updateMemberFormDto.getSupporterId().equals(principal.getName())) {
			FieldError fieldError = new FieldError("updateMemberFormDto", "supporterId", "아이디는 변경할 수 없습니다.");
			bindingResult.addError(fieldError);
			
		}
		
		/* 비밀번호 자리 검사 */
		if(!updateMemberFormDto.getSupporterPassword().equals("")) {
		
			String password = updateMemberFormDto.getSupporterPassword();
			if(password.length() <= 4 || password.length() >= 20) {
				FieldError fieldError = new FieldError("updateMemberFormDto", "supporterPassword", "비밀번호는 4자 이상, 20자 이하로 입력해주세요.");
				bindingResult.addError(fieldError);
			}
		}
		
		if(bindingResult.hasErrors()) {
			return "/member/mypageForm";
		}
		
		try {

			member.setSupporterName(updateMemberFormDto.getSupporterName());
			member.setPhoneNumber(updateMemberFormDto.getPhoneNumber());
			member.setPostCode(updateMemberFormDto.getPostCode());
			member.setDetailedAddress(updateMemberFormDto.getDetailedAddress());
			
			if(!updateMemberFormDto.getSupporterPassword().equals("")) {
				
				member.setSupporterPassword(passwordEncoder.encode(updateMemberFormDto.getSupporterPassword()));
			}
			
			memberRepository.save(member);
		} catch(IllegalStateException e) {
			
			model.addAttribute("errorMessage", e.getMessage());
			
			return "/member/mypageForm";
		}
		
	
		
		return "redirect:/";
	}
	
	
	@GetMapping(value="/login/error")
	public String loginError(Model model) {
		
		model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
		return "/member/loginForm";
	}
	
	
}
