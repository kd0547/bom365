package com.bom365.service;



import javax.transaction.Transactional;

import org.springframework.stereotype.Service;


import com.bom365.repository.VolunteerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class VolunteerService {
	
	private final VolunteerRepository volunteerRepository;
	
	
}
