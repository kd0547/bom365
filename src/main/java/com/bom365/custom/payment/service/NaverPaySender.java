package com.bom365.custom.payment.service;

import java.net.URI;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class NaverPaySender implements PaymentSender{
	private RestTemplate restTemplate;
	private HttpHeaders headers = new HttpHeaders();
	
	public NaverPaySender() {
		headers.add("X-Naver-Client-Id", "X-Naver-Client-Id");
		headers.add("X-Naver-Client-Secret", "PPlydWdZPD");
		headers.add("Content-Type", "application/json");
		
		this.restTemplate = new RestTemplate();
		this.restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}
	
	@Override
	public <T> T  send(URI url,Object body,Class<T> dto) throws RestClientException{
		HttpEntity<Object>  request = new HttpEntity<Object>(body,headers);
		
		
		try {
			
			System.out.println(restTemplate.postForObject(url,request,String.class));
			
		} catch (RestClientException e) {
			System.out.println("Error");
			System.out.println(e.getMessage());
			
			
		}
		
		
		
		return null;
	}

	@Override
	public MultiValueMap<String, String> convert(ObjectMapper objectMapper, Object dto) {
		// TODO Auto-generated method stub
		return null;
	}
 
	
	
}
