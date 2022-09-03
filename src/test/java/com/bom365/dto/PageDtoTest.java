package com.bom365.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.custom.dto.PageDto;
import com.bom365.entity.Board;
import com.bom365.repository.BoardRepository;

@SpringBootTest
@Transactional
class PageDtoTest {

	@Autowired
	BoardRepository boardRepository;
	/*
	@BeforeEach
	void setUp() {
		for(int i=0; i<956; i++) {
			Board board = new Board();
			board.setTitle("title"+i);
			board.setContent("content"+i);
			board.setWriter("writer"+i);
			board.setWriterId("홍길동"+i);
			
			boardRepository.save(board);
		}
	}
	*/
	@Test
	void test() {
		PageDto pageDto = new PageDto();
		
		Long test = 1L;
		Long test2 = 10L;
		

		
		pageDto.setStartPage(test);
		
		//System.out.println(" 기본 ");
		//System.out.println(pageDto.toString());
		System.out.println(" ");
		
		//changeStartUp() TEST
		pageDto.setCountPage(test2);
		//pageDto.setSize(test2);
		for(Long i = 0L;i< 50L ;i++) {
			
		
			
			
			
			System.out.println(" changeStartUp() TEST ");
			System.out.println(pageDto.toString());
			
			
			
			
			System.out.println(" ");
		}
		
		for(Long i = 0L;i< 50L ;i++) {
			
			System.out.println(" changeStartUp() TEST ");
			System.out.println(pageDto.toString());
			System.out.println(" ");
		}
		
		
	}
	
	
	/*
	@Test
	void test() {
		
		
		
		for(int i=1;i<100;i++) {
			PageDto pageDto = new PageDto();
			pageDto.setPage((long) i);
			pageDto.setStartEnd((long) 10);
			//System.out.println(pageDto.toString()); 
			
			List<Board> boardList = boardRepository.findByIdBetween(pageDto.getStart(), pageDto.getEnd());
			
			for(Board board : boardList) {
				System.out.println(board.toString());
			}
			
		}
		
		
		
	}
	*/

}
