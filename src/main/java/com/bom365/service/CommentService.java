package com.bom365.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.entity.Comment;
import com.bom365.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
	
	private final CommentRepository commentRepository;
	
	
	public List<Comment> findCommentBoardId(Long boardId) {
		return commentRepository.findByBoardId(boardId);
	}
	
	
	public void saveComment() {
		
	}
	public void updateComment() {
		
	}
	
	public void deleteComment() {
		
	}
	
}
