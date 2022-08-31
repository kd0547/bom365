package com.bom365.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

import com.bom365.dto.BoardFormDto;
import com.bom365.service.BoardService;



@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	
	private final BoardService boardService;
	
	
	@GetMapping("/list")
	public String boardMain(Model Model) {
		List<BoardFormDto> boardFormDto = boardService.getBoardList();
		
		if(!boardFormDto.isEmpty()) {
			Model.addAttribute("boardFormDto", boardFormDto);
			
		}
		boardFormDto = null;
		
		
		return"board/boardList";
	}
	
	@GetMapping("/content/{boardId}")
	public String detailContent(@PathVariable("boardId") Long boardId, Model model) {
		
		
		
		
		return "/board/detailContent";
	}
	
	@GetMapping("/writing")
	public String boardWriting() {
		
		return "board/boardRequest";
	}
	
}
