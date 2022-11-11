package com.bom365.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.bom365.dto.AdoptQuestionDto;

@Entity
@Table(name = "adopt")
public class AdoptionApplication extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "animal_id")
	private Animal animal;
	
	private String subject;
	
	private String question1;
	
	private String question2;
	
	private String question3;
	
	private String question4;
	
	private String question5;
	
	private String question6;
	
	private String question7;
	
	private String question8;
	
	private String question9;
	
	private String question10;
	
	private String question11;
	
	private String question12;
	
	private String question13;
	
	private String question14;
	
	private String question15;
	
	private String question16;

	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}

	public Member getMember() {return member;}
	public void setMember(Member member) {this.member = member;}
	
	public Animal getAnimal() {return animal;}
	public void setAnimal(Animal animal) {this.animal = animal;}
	
	public String getSubject() {return subject;}
	public void setSubject(String subject) {this.subject = subject;}
	
	public String getQuestion1() {return question1;}
	public void setQuestion1(String question1) {this.question1 = question1;}

	public String getQuestion2() {return question2;}
	public void setQuestion2(String question2) {this.question2 = question2;}

	public String getQuestion3() {return question3;}
	public void setQuestion3(String question3) {this.question3 = question3;}

	public String getQuestion4() {return question4;}
	public void setQuestion4(String question4) {this.question4 = question4;}

	public String getQuestion5() {return question5;}
	public void setQuestion5(String question5) {this.question5 = question5;}

	public String getQuestion6() {return question6;}
	public void setQuestion6(String question6) {this.question6 = question6;}

	public String getQuestion7() {return question7;}
	public void setQuestion7(String question7) {this.question7 = question7;}

	public String getQuestion8() {return question8;}
	public void setQuestion8(String question8) {this.question8 = question8;}

	public String getQuestion9() {return question9;}
	public void setQuestion9(String question9) {this.question9 = question9;}

	public String getQuestion10() {return question10;}
	public void setQuestion10(String question10) {this.question10 = question10;}

	public String getQuestion11() {return question11;}
	public void setQuestion11(String question11) {this.question11 = question11;}

	public String getQuestion12() {return question12;}
	public void setQuestion12(String question12) {this.question12 = question12;}

	public String getQuestion13() {return question13;}
	public void setQuestion13(String question13) {this.question13 = question13;}

	public String getQuestion14() {return question14;}
	public void setQuestion14(String question14) {this.question14 = question14;}

	public String getQuestion15() {return question15;}
	public void setQuestion15(String question15) {this.question15 = question15;}

	public String getQuestion16() {return question16;}
	public void setQuestion16(String question16) {this.question16 = question16;}
	
	@Override
	public String toString() {
		return "AdoptionApplication [id=" + id + ", member=" + member + ", animal=" + animal + ", subject=" + subject
				+ ", question1=" + question1 + ", question2=" + question2 + ", question3=" + question3 + ", question4="
				+ question4 + ", question5=" + question5 + ", question6=" + question6 + ", question7=" + question7
				+ ", question8=" + question8 + ", question9=" + question9 + ", question10=" + question10
				+ ", question11=" + question11 + ", question12=" + question12 + ", question13=" + question13
				+ ", question14=" + question14 + ", question15=" + question15 + ", question16=" + question16 + "]";
	}
	
	public static AdoptionApplication create(Member findMember,Animal animal ,AdoptQuestionDto adoptQuestionDto) {
		AdoptionApplication adoptionApplication = new AdoptionApplication();
		adoptionApplication.setMember(findMember);
		adoptionApplication.setSubject(adoptQuestionDto.getSubject());
		
		
		adoptionApplication.setMember(findMember);
		adoptionApplication.setAnimal(animal);
		
		adoptionApplication.setQuestion1(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion2(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion3(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion4(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion5(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion6(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion7(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion8(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion9(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion10(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion11(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion12(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion13(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion14(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion15(adoptQuestionDto.getQuestion1());
		adoptionApplication.setQuestion16(adoptQuestionDto.getQuestion1());
		
		return adoptionApplication;
	}
	
}
