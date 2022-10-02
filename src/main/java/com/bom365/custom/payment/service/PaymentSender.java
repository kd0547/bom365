package com.bom365.custom.payment.service;

import java.net.URI;

import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface PaymentSender {
	<T> T  send(URI url,Object body,Class<T> ResponseType);
	
	MultiValueMap<String, String> convert(ObjectMapper objectMapper, Object dto);
	
	
}
