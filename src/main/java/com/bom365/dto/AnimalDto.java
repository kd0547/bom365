package com.bom365.dto;



import com.bom365.constant.AdoptState;
import com.bom365.constant.Gender;

public class AnimalDto {

	private Long id;
	
	//이름 
	private String animalName;
	
	//종
	private String animalSpecies;
	
	//고양이 or 개 
	private String animalType;
	
	//중성화 여부 
	private String Neutering;
	
	//성별
	private Gender animalGender;
	
	//나이 
	private double animalAge;
	//나이 문자열
	private String animalAgeString;
	
	//몸무게 
	private double animalWeight;
	
	//이미지 
	private String animalImage;
	
	private AdoptState adoptState;

	
	
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	public String getAnimalName() {return animalName;}
	public void setAnimalName(String animalName) {this.animalName = animalName;}

	public String getAnimalSpecies() {return animalSpecies;}
	public void setAnimalSpecies(String animalSpecies) {this.animalSpecies = animalSpecies;}

	public String getAnimalType() {return animalType;}
	public void setAnimalType(String animalType) {this.animalType = animalType;}

	public String getNeutering() {return Neutering;}
	public void setNeutering(String neutering) {Neutering = neutering;}

	public Gender getAnimalGender() {return animalGender;}
	public void setAnimalGender(Gender animalGender) {this.animalGender = animalGender;}

	public double getAnimalAge() {return animalAge;}
	public void setAnimalAge(double animalAge) {this.animalAge = animalAge;}

	public double getAnimalWeight() {return animalWeight;}
	public void setAnimalWeight(double animalWeight) {this.animalWeight = animalWeight;}

	public String getAnimalImage() {return animalImage;}
	public void setAnimalImage(String animalImage) {this.animalImage = animalImage;}
	
	public String getAnimalAgeString() {return animalAgeString;}
	public void setAnimalAgeString(String animalAgeString) {this.animalAgeString = animalAgeString;}
	
	public AdoptState getAdoptState() {return adoptState;}
	public void setAdoptState(AdoptState adoptState) {this.adoptState = adoptState;}
	
	@Override
	public String toString() {
		return "AnimalDto [id=" + id + ", animalName=" + animalName + ", animalSpecies=" + animalSpecies
				+ ", animalType=" + animalType + ", Neutering=" + Neutering + ", animalGender=" + animalGender
				+ ", animalAge=" + animalAge + ", animalAgeString=" + animalAgeString + ", animalWeight=" + animalWeight
				+ ", animalImage=" + animalImage + ", adoptState=" + adoptState + "]";
	}
	
	
	
	
	
	
	
	
	
}
