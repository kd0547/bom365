package com.bom365.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.custom.dto.PageDto;
import com.bom365.dto.BoardFormDto;
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
	public Board selectBoardOne(Long id) {
	 	Optional<Board> board = boardRepository.findById(id);
		
	 	if(!board.isPresent()) {
	 		throw new NoSuchElementException();
	 	}
	 	
		return board.get();
	}
	
	public Board saveBoard(Board board) {
		return boardRepository.save(board);
	}
	
	public Long getBoardCount() {
		return boardRepository.countFindAllBy();
	}
	
	public void updateBoard() {
		
	}
	
	public void deleteBoard() {
		
	}
	
}
