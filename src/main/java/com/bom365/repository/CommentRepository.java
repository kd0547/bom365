package com.bom365.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bom365.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	public List<Comment> findByBoardId(Long id);
}
