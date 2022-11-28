package com.bom365.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {
	
	private final JavaMailSender javaMailSender;
	
	
	private void sendEmail(String to, String subject, String text) {
		this.sendEmail(to, subject, text, false);
	}
	
	
	
	private void sendEmail(String to, String subject, String text,boolean html){
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		
		
		try {
			MimeMessageHelper mineMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
			// setFrom 사용 안하고 사용방법 찾기 
			mineMessageHelper.setTo(to);
			mineMessageHelper.setFrom("");
			mineMessageHelper.setSubject(subject);
			mineMessageHelper.setText(text, html);
			
			
			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void sendIdEmail(String email,String id) {
		String subject = "[bom 365] 회원 아이디입니다.";
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h1> 아이디는");
		sb.append(id);
		sb.append("입니다.</h1>");
			
		this.sendEmail(email, subject, sb.toString(),true);
	}

	
	//인증토큰이 필요함 
	public void sendPasswordEmail(String email) {
		  String subject = "[bom365] 비밀번호를 변경해주세요";

	      StringBuffer sb = new StringBuffer();
	      sb.append("<a href='http://bom365/member/updatePassword'");
	      sb.append("&email=");
	      sb.append(email);
	      sb.append("'>비밀번호 변경페이지</a>");

	      this.sendEmail(email, subject, sb.toString(), true);
		
	}
 }
