package com.bom365.dto;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.bom365.constant.Gender;

import lombok.*;


@Getter
@Setter
@ToString
public class AnimalSearchDto {
	
	
	private String animalSpecies;
	
	private Gender animalGender;
	
	private String animalWeight;
	
	private String animalAge;
	
	private String animalName;
	
	public boolean isAllEmpty() {
		
		if(this.animalSpecies == null && this.animalGender == null && this.animalWeight == null && this.animalAge == null && this.animalName == null) {
			return true;
		}
		
		if(this.animalSpecies == "" && this.animalGender == Gender.ALL && this.animalWeight =="" && this.animalAge == "" && this.animalName == "" ) {
			return true;
		}
		
		return false;
	}
	//만약 필드가 더 추가되면 어떻게 해야할까?
	//OCP을 지키면서if문 null값을 찾는 방법은 없나?
	public void changeEmptyFieldNull() {
		if(isEmpty(this.animalSpecies)) {
			this.animalSpecies = null;
		}
		
		if(this.animalGender == Gender.ALL) {
			this.animalGender = null;
		}
		
		if(isEmpty(this.animalWeight)) {
			this.animalWeight = null;
		}
		
		if(isEmpty(this.animalAge)) {
			this.animalAge = null;
		}
		
		
		if(isEmpty(this.animalName)) {
			this.animalName = null;
		}
		
	}
	
	public boolean isEmpty(String source) {
		return source == "" || source == "ALL" || source == null || source == "null";
	}
	
	/*
	public void SearchAnimalQuery(AnimalSearchDto object) {

		String searchQuery = "select * from animal a where ";
		
		Field[] fields= object.getClass().getDeclaredFields();
		int count = fields.length;
			
		
		try {
			for(int i= 0;i<count;i++) {
				String isEmptydata = (String) fields[i].get(object);
				
				System.out.println();
				
				//(String) field.get(object);
				if(!isEmpty(isEmptydata)) {
					searchQuery = searchQuery + fields[i].getName() + " = '"+ isEmptydata +"' ";
					
					if(!isEmpty(isEmptydata) && i+2 != count) {
						searchQuery += "and ";
					}
	
				}
				
				
				
			}
			
			System.out.println(searchQuery);
			
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//return null;
		
	}
	*/	
}
