package com.bom365.entity;

import com.bom365.dto.CommentDto;

import javax.persistence.*;
import lombok.*;


@Entity
@Table(name="comment")
@Getter
@Setter
@ToString
public class Comment extends BaseEntity{
	
	@Id
	@Column(name="commentId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name ="boardId")
	private Board boardId;
	
	private String writer;
	
	private String Content;
	
	public void updateComment(CommentDto commnetDto) {
		this.Content = commnetDto.getContent();
	}
}
