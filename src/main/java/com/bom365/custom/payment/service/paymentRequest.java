package com.bom365.custom.payment.service;

import java.net.URI;

import org.springframework.web.client.RestClientException;

public interface paymentRequest {
	
	public Object payReady(URI url,Object formObject,Object toObject) throws RestClientException;
	
}
