package com.bom365.dto;

import java.time.LocalDateTime;

import com.bom365.constant.AdoptState;

public class AdoptStateDto {
	private Long adoptId;
	private String animalName;
	private LocalDateTime dateTime;
	private AdoptState adoptState;
	
	
	
	
	public Long getAdoptId() {
		return adoptId;
	}
	public void setAdoptId(Long adoptId) {
		this.adoptId = adoptId;
	}
	public String getAnimalName() {
		return animalName;
	}
	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public AdoptState getAdoptState() {
		return adoptState;
	}
	public void setAdoptState(AdoptState adoptState) {
		this.adoptState = adoptState;
	}
	
	@Override
	public String toString() {
		return "AdoptStateDto [AdoptId=" + adoptId + ", animalName=" + animalName + ", dateTime=" + dateTime
				+ ", adoptState=" + adoptState + "]";
	}
}
