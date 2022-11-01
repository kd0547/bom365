package com.bom365.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;

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
import com.bom365.custom.payment.KakopayDto.ReadyRequestSubscription;
import com.bom365.custom.payment.KakopayDto.ReadyResponseKakaoPay;
import com.bom365.custom.payment.KakopayDto.ReadyResponseSubscription;
import com.bom365.custom.payment.service.KakaoPayRequest;
import com.bom365.custom.payment.service.PaymentURI;
import com.bom365.dto.KakaoPayFormDto;
import com.bom365.entity.RegularSupport;
import com.bom365.entity.TemporarySupport;
import com.bom365.service.RegularSupportHistoryService;
import com.bom365.service.RegularSupportService;

@Controller
@RequestMapping(value="/regular")
@SessionAttributes({"approveRequestKakaoPay"}) 
//세션으로 하면 가끔씩 결제가 안되는 버그 있음 
//아마 세션이 남아 있어서 그걸로 처리되는 것 같음 
public class RegularSupportController {
	
	@Autowired
	RegularSupportHistoryService regularSupportHistoryService;
	
	@Autowired
	RegularSupportService regularSupportService;
	
	@Autowired
	KakaoPayRequest kakaopayRequest;
	
	
	
	@GetMapping(value="/payment") 
	public String reqularMain(){
		
		
		//return "error/404";
		return "support/regularSupport";
	}
	/*
	 * 첫 1회는 단건결제 방식으로 진행 
	 */
	@PostMapping(value="/kakaopay")
	public @ResponseBody ReadyResponseKakaoPay  readyKakaopay(KakaoPayFormDto kakaopayFormDto, Model model) {
		
		RegularSupport regularSupport = regularSupportService.find(kakaopayFormDto.getId());
		
		if(regularSupport != null) {
			return null;
		}
		
		
		ReadyResponseKakaoPay readyResponseKakaoPay = new ReadyResponseKakaoPay();
		ReadyRequestKakaopay readyRequestKakaopay
				= new ReadyRequestKakaopay
					.Builder()
						.cid("TCSUBSCRIP")
						.itemName("봄365 후원")
						.partnerOrderId("500"+ "12345")
						.partnerUserId(kakaopayFormDto.getId())
						.quantity(1)
						.totalAmount(kakaopayFormDto.getAmount())
						.taxFreeAmount(0)
						.approvalUrl("http://localhost:8080/regular/kakaopayApproval")
						.cancelUrl("http://localhost:8080/regular/kakaopayCancel")
						.failUrl("http://localhost:8080/regular/kakaopayFail")
						.build();
		
		
		try {
			readyResponseKakaoPay  = (ReadyResponseKakaoPay) kakaopayRequest.payReady(new URI(PaymentURI.PaymentReady()), readyRequestKakaopay,new ReadyResponseKakaoPay());
			
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
		
		try {
			approveResponseKakaoPay = (ApproveResponseKakaoPay) kakaopayRequest.payApprove(new URI(PaymentURI.PaymentApprove()),approveRequestKakaoPay,new ApproveResponseKakaoPay());
		
		
		} catch (RestClientException | URISyntaxException e1) {
			e1.printStackTrace();
		} 
		
		//이 코드를 paymentService 클래스로 전환하고 Controller에는 메서드만으로 이욯할 수 있게 만들기 
		// 책임 연쇄 패턴도 됀찮을 것 같음 
		if(approveResponseKakaoPay != null) {
			try {
					
				regularSupportService.saveAll(approveResponseKakaoPay);
				regularSupportHistoryService.save(approveResponseKakaoPay);
				
				
			} catch (IllegalArgumentException e) {
				CancelRequestKakaopay cancelRequestKakaopay = 
						new CancelRequestKakaopay.Builder()
							.cid(approveResponseKakaoPay.getCid())
							.tid(approveResponseKakaoPay.getTid())
							.cancelAmount(approveResponseKakaoPay.getAmount().getTotal())
							.cancelTaxFreeAmount(approveResponseKakaoPay.getAmount().getTax_free())
							.build();
		
				try {
					kakaopayRequest.payCancel(new URI(PaymentURI.PaymentCancel()),cancelRequestKakaopay);
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
