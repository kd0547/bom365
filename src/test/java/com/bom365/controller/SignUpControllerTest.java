package com.bom365.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


@SpringBootTest
@AutoConfigureMockMvc
class SignUpControllerTest {

	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void signUpForm() throws Exception{
		mockMvc.perform(get("/signup"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) content().string(containsString("_csrf")));
	}
	
	@Test
	public void processSignUp() throws Exception {
		mockMvc.perform(post("/signup")
				.param("username", "keesun")
				.param("password", "123")
				.with(csrf()))
				.andDo(print())
				.andExpect(status().is3xxRedirection());
	}

}
