package com.bom365.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.dto.BoardFormDto;
import com.bom365.dto.PageDto;
import com.bom365.entity.Board;
import com.bom365.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;
	
	
	public List<BoardFormDto> getBoardList(PageDto pageDto) {
		List<Board> boardList = boardRepository.findByIdBetween(pageDto.getStart(),pageDto.getEnd());
		
		List<BoardFormDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardList) {
			BoardFormDto boardDto = new BoardFormDto();
			
			boardDto.setId(board.getId());
			boardDto.setTitle(board.getTitle());
			boardDto.setWriter(board.getWriter());
			boardDto.setRegTime(board.getRegTime());
			boardDto.setCommentCount(board.getCommentCount());
			
			boardDtoList.add(boardDto);
		}

		return boardDtoList;
	}
	
	public Board saveBoard(Board board) {
		return boardRepository.save(board);
	}
	
	public void updateBoard() {
		
	}
	
	public void deleteBoard() {
		
	}
	
}
