package com.bom365.controller;

import java.security.Principal;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

import com.bom365.custom.dto.PageDto;
import com.bom365.dto.BoardFormDto;
import com.bom365.entity.Board;
import com.bom365.entity.Member;
import com.bom365.service.BoardService;
import com.bom365.service.MemberService;



@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final MemberService memberService;
	private final BoardService boardService;
	
	//https://congsong.tistory.com/26
	@GetMapping("/list")
	public String boardMain(Model model,PageDto pageDto,
			@RequestParam(value="page",required=false) Long page,
			@RequestParam(value="size",required=false) Long size) {
		// 코드 리팩토링하기 
		// pageDto 로직을 클래스 내부에서 동작하게 구현하기 
		
		
		Long total = boardService.getBoardCount();
		pageDto = new PageDto(page,size,total);

		
		List<BoardFormDto> list = boardService.getBoardList(pageDto);
		
		if(list.isEmpty()) {
			list = null;
		}
		
		
		model.addAttribute("boardFormDto", list);
		model.addAttribute("pageDto", pageDto);
		
		return"board/boardList";
	}

	
	@GetMapping("/content/{boardId}")
	public String detailContent(@PathVariable("boardId") Long boardId, Model model) {
		
		
		
		
		return "/board/detailContent";
	}
	
	@GetMapping("/writing")
	public String boardWriting(Model model) {
		
		
		model.addAttribute("boardFormDto", new BoardFormDto());
		
		return "board/boardRequest";
	}
	
	@PostMapping("/writing")
	public String boardWriting(@Valid BoardFormDto boardFormDto,Principal principal,BindingResult bindingResult ,Model model) {
		Member member = new Member();
		Board board = new Board();
		
		if(bindingResult.hasErrors()) {
			
			return "board/boardRequest";
		}
		
		member = memberService.findMember(principal.getName());
		
		board.setTitle(boardFormDto.getTitle());
		board.setContent(boardFormDto.getContent());
		board.setWriter(member.getSupporterName());
		board.setWriterId(member.getSupporterId());
		
		try {
			boardService.saveBoard(board);
			
			
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", "게시글 작성 중 에러가 발생하였습니다.");
			
			return "board/boardRequest";
		}
		
		
		
		return "redirect:board/boardList";
	}
}
