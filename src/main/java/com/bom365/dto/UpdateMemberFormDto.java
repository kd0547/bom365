package com.bom365.dto;

import com.bom365.entity.Member;

import lombok.*;

@Getter
@Setter
@ToString
public class UpdateMemberFormDto {
	
	private String supporterId;
	private String supporterPassword;
	private String supporterName;
	private String phoneNumber;
	private String postCode;
	private String detailedAddress;
	
	
	
	
	public UpdateMemberFormDto createMemberFormDto(Member member){
		UpdateMemberFormDto updateMemberFormDto = new  UpdateMemberFormDto();
		updateMemberFormDto.setSupporterId(member.getSupporterId());
		updateMemberFormDto.setSupporterPassword(member.getSupporterPassword());
		updateMemberFormDto.setSupporterName(member.getSupporterName());
		updateMemberFormDto.setPhoneNumber(member.getPhoneNumber());
		updateMemberFormDto.setPostCode(member.getPostCode());
		updateMemberFormDto.setDetailedAddress(member.getDetailedAddress());
		
		
		return updateMemberFormDto;
	}
}
