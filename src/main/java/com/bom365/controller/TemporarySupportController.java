package com.bom365.controller;



import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bom365.custom.payment.KakopayDto.ApproveRequestKakaoPayDto;
import com.bom365.custom.payment.KakopayDto.CancelRequestKakaopayDto;
import com.bom365.custom.payment.KakopayDto.ReadyRequestKakaopayDto;
import com.bom365.custom.payment.KakopayDto.ReadyResponseKakaoPayDto;
import com.bom365.dto.KakaoPayFormDto;

import com.bom365.service.TemporarySupportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/temporary")
@RequiredArgsConstructor
@SessionAttributes({"approveRequestKakaoPayDto"}) 
public class TemporarySupportController {
	
	private final TemporarySupportService temporarySupportService;
	//private final KakaoPayService kakaoPayService;
	
	
	@GetMapping(value="/payment")
	public String temporaryMain() {
		
		
		//return "error/404";
		return "support/temporarySupport";
	}
	
	
	//리팩토링하기
	@PostMapping(value="/kakaopay")
	public @ResponseBody ReadyResponseKakaoPayDto  readyKakaopay(KakaoPayFormDto kakaopayFormDto, Model model) {
		ReadyRequestKakaopayDto readyRequestKakaopayDto = new ReadyRequestKakaopayDto();
		ReadyResponseKakaoPayDto readyResponseKakaoPayDto = null;
		
		readyRequestKakaopayDto.setCid("TC0ONETIME");
		readyRequestKakaopayDto.setItem_name("봄365 후원");
		readyRequestKakaopayDto.setPartner_order_id("500"+ "12345");
		readyRequestKakaopayDto.setPartner_user_id(kakaopayFormDto.getId());
		readyRequestKakaopayDto.setQuantity(1);
		readyRequestKakaopayDto.setTotal_amount(kakaopayFormDto.getAmount());
		readyRequestKakaopayDto.setTax_free_amount(0);
		
		//url을 config에서 설정할 수 있게 변경하기 
		readyRequestKakaopayDto.setApproval_url("http://localhost:8080/temporary/kakaopayApproval");
		readyRequestKakaopayDto.setCancel_url("http://localhost:8080/temporary/kakaopayCancel");
		readyRequestKakaopayDto.setFail_url("http://localhost:8080/temporary/kakaopayFail");
		
		//url을 config에서 설정할 수 있게 변경하기 
		//readyRequestKakaopayDto.setApproval_url("http://www.bom365.org/temporary/kakaopayApproval");
		//readyRequestKakaopayDto.setCancel_url("http://www.bom365.org/temporary/kakaopayCancel");
		//readyRequestKakaopayDto.setFail_url("http://www.bom365.org/temporary/kakaopayFail");
		
		  
		//readyResponseKakaoPayDto = kakaoPayService.payReady(readyRequestKakaopayDto);
		
		ApproveRequestKakaoPayDto approveRequestKakaoPayDto = new ApproveRequestKakaoPayDto();
		
		
		approveRequestKakaoPayDto.setCid(readyRequestKakaopayDto.getCid());
		approveRequestKakaoPayDto.setTid(readyResponseKakaoPayDto.getTid());
		approveRequestKakaoPayDto.setPartner_order_id(readyRequestKakaopayDto.getPartner_order_id());
		approveRequestKakaoPayDto.setPartner_user_id(readyRequestKakaopayDto.getPartner_user_id());
		
		
		
		
		model.addAttribute("approveRequestKakaoPayDto", approveRequestKakaoPayDto);
		return readyResponseKakaoPayDto;
	}
	
	//여기도 로그로 저장하면 좋겠음
	//결제 완료부터 DB 저장까지 트랜잭션
	//만약 DB 저장 실패면 환불를 진행해야한다.
	//아닌가? 그냥 에러로그에 저장하고 수동입력해도 되나?
	@Transactional
	@GetMapping(value="/kakaopayApproval")
	public String approvalKakaopay(@RequestParam("pg_token")String pgToken,@ModelAttribute("approveRequestKakaoPayDto")ApproveRequestKakaoPayDto approveRequestKakaoPayDto,Model model) {
		approveRequestKakaoPayDto.setPg_token(pgToken);

		//ApproveResponseKakaoPayDto readyResponseKakaoPayDto = kakaoPayService.payApprove(approveRequestKakaoPayDto);
		
		try {
			//temporarySupportService.save(TemporarySupport.createTemporarySupport(readyResponseKakaoPayDto));
			
		} catch (IllegalArgumentException e) {
			CancelRequestKakaopayDto cancelRequestKakaopayDto = new CancelRequestKakaopayDto();
			//cancelRequestKakaopayDto.setCid(readyResponseKakaoPayDto.getCid());
			//cancelRequestKakaopayDto.setTid(readyResponseKakaoPayDto.getTid());
			//cancelRequestKakaopayDto.setCancel_amount(readyResponseKakaoPayDto.getAmount().getTotal());
			//cancelRequestKakaopayDto.setCancel_tax_free_amount(readyResponseKakaoPayDto.getAmount().getTax_free());
			
			
			//kakaoPayService.payCancel(cancelRequestKakaopayDto);
			
			e.getStackTrace();
		}
			
		
		model.addAttribute("message","후원 감사합니다.");

		return "message";
	}
	
	@GetMapping(value="/kakaopayCancel")
	public String cancelKakaopay() {
		return "redirect:/main";
	}
	
	@GetMapping(value="/kakaopayFail")
	public String failkakaopay() {
		return "redirect:/main";
	}
	
}
