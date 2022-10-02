package com.bom365.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.bom365.dto.BoardFormDto;
import com.bom365.dto.PageDto;


@SpringBootTest
@Transactional
class BoardServiceTest {

	@Autowired
	BoardService boardService;
	
	@Test
	void test() {
		PageDto pageDto =  new PageDto();
		pageDto.setEnd(10L);
		pageDto.setStart(1L);
		
		List<BoardFormDto> list = boardService.getBoardList(pageDto);
		
		System.out.println(list.isEmpty());
		
	}

}
