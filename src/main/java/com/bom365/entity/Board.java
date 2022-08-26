package com.bom365.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.bom365.dto.BoardDto;

import lombok.*;


@Entity
@Table(name="board")
@Getter
@Setter
@ToString
public class Board extends BaseEntity{
	@Id
	@Column(name="boardId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String writer;
	
	@Column(nullable = false)
	private String title;
	
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = true)
	@OneToMany
	@JoinColumn(name="boardId")
	private List<Comment> comments = new ArrayList<Comment>();
	
	
	public void updateBoard(BoardDto boardDto) {
		this.title = boardDto.getTitle();
		this.content = boardDto.getContent();
	}
	
	
	
}
