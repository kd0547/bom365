package com.bom365.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.bom365.constant.Role;
import com.bom365.dto.MemberDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(unique = true)
	private String supporterId;
	
	@Column(nullable = false)
	private String email;
	
	
	@Column(nullable = false)
	private String supporterPassword;
	
	@Column(nullable = false)
	private String supporterName;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Column(nullable = false)
	private String postCode;
	
	@Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
	
	@Column(nullable = false)
	private String detailedAddress;
	
	
	public static Member createMember(MemberDto memberFormDto,PasswordEncoder passwordEncoder) {
		String password = passwordEncoder.encode(memberFormDto.getSupporterPassword());
		
		Member member = new Member();
		member.setSupporterId(memberFormDto.getSupporterId());
		member.setSupporterPassword(password);
		
		
		member.setSupporterName(memberFormDto.getSupporterName());
		member.setPhoneNumber(memberFormDto.getPhoneNumber());
		member.setRole(Role.USER);
		member.setDetailedAddress(memberFormDto.getDetailedAddress());
		member.setPostCode(memberFormDto.getPostCode());
		
		return member;
	}
	
}
