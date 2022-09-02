package com.bom365.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bom365.entity.Board;

@SpringBootTest
@Transactional
class BoardRepositoryTest {

	@Autowired
	BoardRepository boardRepository;
	
	@BeforeEach
	void setUp() {
		Set<Board> boards= new HashSet<Board>();
		
		for(int i=0; i<1000000; i++) {
			Board board = new Board();
			board.setTitle("title"+i);
			board.setContent("content"+i);
			board.setWriter("writer"+i);
			board.setWriterId("홍길동"+i);
			
			boards.add(board);
		
	
			if(boards.size() == 1000) {
				System.out.println("TEST");
				boardRepository.saveAll(boards);
				
				boards = null;
				boards = new HashSet<Board>();
			}
			
			
		}
	}
	
	@Test
	void test() {
		Long count =  boardRepository.countFindAllBy();
		
		System.out.println(count);
	}

}
