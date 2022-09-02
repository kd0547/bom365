package com.bom365.entity;


import javax.persistence.*;

import org.hibernate.annotations.Formula;

import lombok.*;

import com.bom365.dto.BoardFormDto;



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
	//@Basic(fetch = FetchType.LAZY)
	//@Formula("(select m.supporter_name from member m where m.supporter_name = writer_Id)")
	private String writer;
	
	
	@Column(nullable = false)
	private String writerId;
	
	@Column(nullable = false)
	private String title;
	
	
	
	@Column(nullable = false)
	private String content;
	
	//https://www.popit.kr/jpa-%EC%97%94%ED%84%B0%ED%8B%B0-%EC%B9%B4%EC%9A%B4%ED%8A%B8-%EC%84%B1%EB%8A%A5-%EA%B0%9C%EC%84%A0%ED%95%98%EA%B8%B0/
	@Basic(fetch = FetchType.LAZY)
	@Formula("(select count(*) from comment c where c.board_id = board_id)")
	private int commentCount;
	
	
	
	public void updateBoard(BoardFormDto boardDto) {
		this.title = boardDto.getTitle();
	}
	
	
	
}
