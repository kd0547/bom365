package com.bom365.service;




import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.dto.AdoptStateDto;
import com.bom365.entity.AdoptionApplication;
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
	
	public List<AdoptStateDto> findAdoptState(String supporterId) {
		Member findMember = memberRepository.findBySupporterId(supporterId);
		
		List<AdoptionApplication> adoptionApplications = findMember.getAdopt();
		List<AdoptStateDto> adoptStateDtos = new ArrayList<AdoptStateDto>();
		
		for(AdoptionApplication ad : adoptionApplications) {
			AdoptStateDto adoptStateDto = new AdoptStateDto();
			adoptStateDto.setAdoptId(ad.getId());
			adoptStateDto.setAnimalName(ad.getAnimal().getAnimalName());
			adoptStateDto.setAdoptState(ad.getAnimal().getAdoptState());
			adoptStateDto.setDateTime(ad.getRegTime());
			
			adoptStateDtos.add(adoptStateDto);
		}
		return adoptStateDtos;
	}
	
	
	public Member findMember(String supporter_id) {
		return memberRepository.findBySupporterId(supporter_id);
	}
	
	public Member findEmail(String email) {
		Member findEmail = memberRepository.findByEmail(email);
		
		if(findEmail == null) {
			throw new IllegalStateException("존재하지 않는 이메일입니다.");
		}
		
		return findEmail;
	}

	@Override
	public UserDetails loadUserByUsername(String supporter_id) throws UsernameNotFoundException {
		
		Member member = memberRepository.findBySupporterId(supporter_id);
		
		
		
		if(member == null) {
			throw new UsernameNotFoundException(supporter_id);
		}
		
		
		return User.builder()
				.username(member.getSupporterId())
				.password(member.getSupporterPassword())
				.roles(member.getRole().toString())
				.build();
	}

	public boolean checkEmailAndSupportId(String email, String supportId) {
		Member member = memberRepository.findBySupporterId(supportId);
		
		System.out.println(member);
		
		if(member == null || !member.getEmail().equals(email)) {
			
			throw new IllegalStateException("이메일 또는 아이디가 일치하지 않습니다.");
		}
		
		return true;
	}

}
