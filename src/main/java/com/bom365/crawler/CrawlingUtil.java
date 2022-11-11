package com.bom365.crawler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class CrawlingUtil {
	private final String defaultencodeType = "UTF-8";
	
	private String encodeType;
	
	
	public String encodeURI(String source) throws UnsupportedEncodingException {
		return URLEncoder.encode(source,setType());
	}
	
	public String getURL(int page) {
		return "https://ekara.org/kams/adopt?page="+page;
	}
	
	private String setType() {
		return (encodeType == null) ? defaultencodeType : encodeType;
	}
	
	
	/* getter & setter */
	public String getEncodeType() {return encodeType;}
	public void setEncodeType(String encodeType) {this.encodeType = encodeType;}
	
	public String getDefaultencodeType() {return defaultencodeType;}
	
	
}
