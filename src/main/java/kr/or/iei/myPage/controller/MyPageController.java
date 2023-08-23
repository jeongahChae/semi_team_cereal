package kr.or.iei.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="myPage", method = {RequestMethod.GET, RequestMethod.POST}) //마이페이지
public class MyPageController {
	
	//개인정보수정
	@GetMapping(value="updateMemberInfo")
	public String updateMemberInfo(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/updateMemberInfo";
	}//orderHistoryDeliveryStatus1()
	
	
	//주문내역 / 배송현황
	@GetMapping(value="Donghyo_orderHistory-deliveryStatus_1")
	public String orderHistoryDeliveryStatus(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_orderHistory-deliveryStatus_1";
	}//orderHistoryDeliveryStatus(int btn, Model model)
	//주문내역 / 배송현황 2
	@GetMapping(value="Donghyo_orderHistory-deliveryStatus_2")
	public String orderHistoryDeliveryStatus2(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_orderHistory-deliveryStatus_2";
	}//orderHistoryDeliveryStatus2(int btn, Model model)
	
	
	//주문취소/교환/반품
	@GetMapping(value="Donghyo_orderCancel-change-return_1")
	public String orderCancelChangeReturn(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_orderCancel-change-return_1";
	}//orderCancelChangeReturn(int btn, Model model)
	//주문취소/교환/반품 2 - 접수
	@PostMapping("Donghyo_orderCancel-change-return_2")
	public String registerOrderCancelChangeReturn(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_orderCancel-change-return_2";
	}//registerOrderCancelChangeReturn(int btn, Model model)
	//주문취소/교환/반품 3 - 교환/반품 내역
	@GetMapping(value="Donghyo_orderCancel-change-return_3")
	public String changeReturnHistory(int btn, Model model) {
		model.addAttribute("btn", btn);
      return "myPage/Donghyo_orderCancel-change-return_3";
	}//changeReturnHistory(int btn, Model model)
	
	
	//찜한 상품
	@GetMapping(value="Donghyo_likeItList")
	public String likeItList(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_likeItList";
	}//likeItList(int btn, Model model)
	
	
	//상품 후기
	@GetMapping(value="Donghyo_productReview_1")
	public String productReview(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_productReview_1";
// 	    return "myPage/Donghyo_productReview(modal)_2";
	}//productReview(int btn, Model model)
	//상품 후기 3 - 작성한 후기
	@GetMapping(value="Donghyo_productReview_3")
	public String writtenReview(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_productReview_3";
	}//writtenReview(int btn, Model model)
	//상품 후기 4 - 작성한 후기 상세 보기
	@GetMapping(value="Donghyo_productReview_4")
	public String readReview(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_productReview_4";
	}//readReview(int btn, Model model)
	
	
	//상품 문의
	@GetMapping(value="Donghyo_productInquiry_1")
	public String productInquiry(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_productInquiry_1";
	}//productInquiry(int btn, Model model)
	//상품 문의 2 - 상세보기 및 답변
	@GetMapping(value="Donghyo_productInquiry_2")
	public String answerInquiry(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_productInquiry_2";
	}//answerInquiry(int btn, Model model)
	
	
	//적립금
	@GetMapping(value="Donghyo_accumulated_money")
	public String accumulatedMoney(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_accmulated_money";
	}//accumulatedMoney(int btn, Model model)
	
}//MyPageController
