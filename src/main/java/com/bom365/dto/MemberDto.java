package com.bom365.dto;

import com.bom365.constant.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
	
	private String supporterId;
	private String supporterPassword;
	private String email;
	private String supporterName;
	private String phoneNumber;
	private String postCode;
	private String detailedAddress;
	private Role role;
}
