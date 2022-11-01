package com.bom365.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExceptionHandle implements ErrorController {
	
	@RequestMapping("/error")
	public String error(HttpServletRequest request, Model model) {
		Object status = request.getAttribute("javax.servlet.error.status_code");
		Object ExceptionStatus = request.getAttribute("javax.servlet.error.exception");
		
		
		if(status.equals(HttpStatus.NOT_FOUND.value())) {
			return "error/404";
		}
		/*
		if(ExceptionStatus != null) {
			return "error/500";
		}
		*/
		
		return "";
	}
}
