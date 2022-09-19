package com.bom365.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
public class EmailConfig {
	//https://cloud.google.com/appengine/docs/standard/java/mail/sending-mail-with-mail-api?hl=ko
	//AWS에서 이메일 서버를 구현할까 
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
		
		javaMailSenderImpl.setHost("smtp.naver.com");
		javaMailSenderImpl.setUsername("kda2935");
		javaMailSenderImpl.setPassword("Qpfvktmxm@9712");
		javaMailSenderImpl.setPort(465);
		
		
		
		javaMailSenderImpl.setJavaMailProperties(getMailProperties());
		
		return javaMailSenderImpl;
		
	}
	
	
	
	
	
	private Properties getMailProperties() {
        Properties properties = new Properties();
        
        
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.ssl.trust","smtp.naver.com");
        properties.setProperty("mail.smtp.ssl.enable","true");
        return properties;
    }
}
