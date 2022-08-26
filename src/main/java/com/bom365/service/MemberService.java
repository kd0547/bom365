package com.bom365.service;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bom365.entity.Member;
import com.bom365.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	
	
	public Member saveMember(Member member) {
		 return  memberRepository.save(member);
	}
	
	public void validateDuplicateMember(Member member) {
		Member findMember = memberRepository.findBySupporterId(member.getSupporterId());
		
		if(findMember != null) {
			throw new IllegalStateException("이미 가입된 아이디입니다.");
		} 
		
	}
	

	@Override
	public UserDetails loadUserByUsername(String supporter_id) throws UsernameNotFoundException {
		
		Member member = memberRepository.findBySupporterId(supporter_id);
		
		if(member == null) {
			throw new UsernameNotFoundException(supporter_id);
		}
		System.out.println(member.toString());
		
		return User.builder()
				.username(member.getSupporterId())
				.password(member.getSupporterPassword())
				.roles(member.getRole().toString())
				.build();
	}

}
