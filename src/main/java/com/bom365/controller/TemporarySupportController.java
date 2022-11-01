package com.bom365.controller;



import java.net.URI;

import java.net.URISyntaxException;
import java.security.Principal;

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
import org.springframework.web.client.RestClientException;

import com.bom365.custom.payment.KakopayDto.ApproveRequestKakaoPay;
import com.bom365.custom.payment.KakopayDto.ApproveResponseKakaoPay;
import com.bom365.custom.payment.KakopayDto.CancelRequestKakaopay;
import com.bom365.custom.payment.KakopayDto.ReadyRequestKakaopay;
import com.bom365.custom.payment.KakopayDto.ReadyResponseKakaoPay;
import com.bom365.custom.payment.service.KakaoPayRequest;
import com.bom365.custom.payment.service.PaymentURI;
import com.bom365.dto.KakaoPayFormDto;
import com.bom365.entity.TemporarySupport;
import com.bom365.service.TemporarySupportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value="/temporary")
@RequiredArgsConstructor
@SessionAttributes({"approveRequestKakaoPay"}) 
public class TemporarySupportController {
	
	private final TemporarySupportService temporarySupportService;
	private final KakaoPayRequest kakaoPayRequest;
	
	
	@GetMapping(value="/payment")
	public String temporaryMain() {
		
		
		//return "error/404";
		return "support/temporarySupport";
	}
	
	
	//리팩토링하기
	@PostMapping(value="/kakaopay")
	public @ResponseBody ReadyResponseKakaoPay  readyKakaopay(Principal principal,KakaoPayFormDto  kakaoPayFormDto , Model model) {
		
		ReadyResponseKakaoPay readyResponseKakaoPay = null;
		
		ReadyRequestKakaopay readyRequestKakaopay = 
							new ReadyRequestKakaopay
								.Builder()
									.cid("TC0ONETIME").itemName("봄365 후원")
									.partnerOrderId("500"+ "12345").partnerUserId(principal.getName())
									.quantity(1).totalAmount(kakaoPayFormDto.getAmount()).taxFreeAmount(0)
									//url을 config에서 설정할 수 있게 변경하기 
									.approvalUrl("http://localhost:8080/temporary/kakaopayApproval")
									.cancelUrl("http://localhost:8080/temporary/kakaopayCancel")
									.failUrl("http://localhost:8080/temporary/kakaopayFail")
									//url을 config에서 설정할 수 있게 변경하기 
									//.approvalUrl("http://www.bom365.org/temporary/kakaopayApproval")
									//.cancelUrl("http://www.bom365.org/temporary/kakaopayCancel")
									//.failUrl("http://www.bom365.org/temporary/kakaopayFail")
									.build();
	
		
		try {
			
			readyResponseKakaoPay = (ReadyResponseKakaoPay) kakaoPayRequest.payReady(new URI(PaymentURI.PaymentReady()) ,readyRequestKakaopay,new ReadyResponseKakaoPay());
			
			
			ApproveRequestKakaoPay approveRequestKakaoPay = 
					new ApproveRequestKakaoPay
						.Builder()
							.cid(readyRequestKakaopay.getCid())
							.tid(readyResponseKakaoPay.getTid())
							.partnerOrderId(readyRequestKakaopay.getPartner_order_id())
							.partnerUserId(readyRequestKakaopay.getPartner_user_id())
							.build();
	
			
			model.addAttribute("approveRequestKakaoPay", approveRequestKakaoPay);
			
			return readyResponseKakaoPay;
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		
		
		
		return readyResponseKakaoPay;
	}
	
	@Transactional
	@GetMapping(value="/kakaopayApproval")
	public String approvalKakaopay(@RequestParam("pg_token")String pgToken,@ModelAttribute("approveRequestKakaoPay")ApproveRequestKakaoPay approveRequestKakaoPay,Model model) {
		ApproveResponseKakaoPay approveResponseKakaoPay = null;
		approveRequestKakaoPay.setPg_token(pgToken);
		
		System.out.println(approveRequestKakaoPay.toString());
		
		try {
			approveResponseKakaoPay = (ApproveResponseKakaoPay) kakaoPayRequest.payApprove(new URI(PaymentURI.PaymentApprove()),approveRequestKakaoPay,new ApproveResponseKakaoPay());
			
			
			
		
		} catch (RestClientException e1) {
			
			e1.printStackTrace();
		} catch (URISyntaxException e1) {
			
			e1.printStackTrace();
		}
		//이 코드를 paymentService 클래스로 전환하고 Controller에는 메서드만으로 이욯할 수 있게 만들기 
		// 책임 연쇄 패턴도 됀찮을 것 같음 
		if(approveResponseKakaoPay != null) {
			try {
				temporarySupportService.save(TemporarySupport.createTemporarySupport(approveResponseKakaoPay));
				
				
			} catch (Exception e) {
				CancelRequestKakaopay cancelRequestKakaopay = 
						new CancelRequestKakaopay.Builder()
							.cid(approveResponseKakaoPay.getCid())
							.tid(approveResponseKakaoPay.getTid())
							.cancelAmount(approveResponseKakaoPay.getAmount().getTotal())
							.cancelTaxFreeAmount(approveResponseKakaoPay.getAmount().getTax_free())
							.build();
		
				try {
					kakaoPayRequest.payCancel(new URI(PaymentURI.PaymentCancel()),cancelRequestKakaopay);
				} catch (RestClientException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				e.getStackTrace();
			}
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
