package com.bom365.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	
	public void saveComment() {
		
	}
	public void updateComment() {
		
	}
	
	public void deleteComment() {
		
	}
	
}
