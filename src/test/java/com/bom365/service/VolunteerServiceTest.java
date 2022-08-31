package com.bom365.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bom365.entity.Volunteer;
import com.bom365.repository.VolunteerRepository;

@SpringBootTest
@Transactional
class VolunteerServiceTest {
	
	@Autowired
	private VolunteerRepository volunteerRepository;
	
	@BeforeEach
	void setUp() {
		Volunteer volunteer1 = new Volunteer();
		volunteer1.setSupporterId("test");
		volunteer1.setSupporterName("홍길동");
		volunteer1.setPhoneNumber("01023456789");
		
		Volunteer volunteer2 = new Volunteer();
		volunteer2.setSupporterId("test");
		volunteer2.setSupporterName("홍길동");
		volunteer2.setPhoneNumber("01034567891");
		
		Volunteer volunteer3 = new Volunteer();
		volunteer3.setSupporterId("test");
		volunteer3.setSupporterName("홍길동");
		volunteer3.setPhoneNumber("01012345678");
		
		
		
		volunteerRepository.save(volunteer1);
		volunteerRepository.save(volunteer2);
		volunteerRepository.save(volunteer3);
	}

	@Test
	@DisplayName("조회 테스트")
	void testVolunteerService() {
		List<Volunteer> list = volunteerRepository.findVolunteerListBySupporterId("test");
		
		System.out.println(list.toString());
	}

}
