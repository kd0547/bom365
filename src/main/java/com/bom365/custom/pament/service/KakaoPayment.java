package com.bom365.custom.pament.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.bom365.custom.pament.dto.ReadyRequestKakaopayDto;
import com.bom365.custom.pament.dto.ReadyResponseKakaoPayDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;





public class KakaoPayment extends PaymentURI implements Payment{
	
	private RestTemplate restTemplate;
	
	public HttpHeaders header;
	
	//리랙토링하기 
	public KakaoPayment() {
		header = new HttpHeaders();
		restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}
	
	
	
	public <T> T  send(URI url,Object body,Class<T> dto) throws RestClientException{
		HttpEntity<Object>  request = new HttpEntity<Object>(body,header);

		return restTemplate.postForObject(url,request,dto);
	}
	
	
	public  MultiValueMap<String, String> convert(ObjectMapper objectMapper, Object dto) {
		//https://jojoldu.tistory.com/478
		try {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			Map<String, String> map = objectMapper.convertValue(dto, new TypeReference<Map<String, String>>(){});
			ArrayList<String> removeKeList = removeParam(map);
			
			Iterator<String> key = removeKeList.iterator();
			
			while (key.hasNext()) {
				String remove = key.next();
				map.remove(remove);
			}
	
			params.setAll(map);
			return params;
			
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("변화 오류 발생");
		}
		
	}
	private ArrayList<String> removeParam(Map<String, String> map) {
		ArrayList<String> removeList = new ArrayList<String>();
		
		for(Entry<String, String> entry : map.entrySet()) {
			
			String key = entry.getKey();
			String value = entry.getValue();
			
			if (value == null || value == "null") {
				
				removeList.add((String) key);
			}

		}
		
		
		return removeList;
	}


	
	
	
}
