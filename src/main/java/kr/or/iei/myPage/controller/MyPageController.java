package kr.or.iei.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="myPage") //마이페이지
public class MyPageController {
	
	//개인정보수정
	@GetMapping(value="/updateMemberInfo")
	public String updateMemberInfo(int btn, Model model) {
		model.addAttribute("btn", btn);
//	    return "myPage/Donghyo_myPageList";
		return "myPage/updateMemberInfo";
	}//orderHistoryDeliveryStatus1()
	
	
	//주문내역 / 배송현황
	@GetMapping(value="/Donghyo_orderHistory-deliveryStatus_1")
	public String orderHistoryDeliveryStatus(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_orderHistory-deliveryStatus_1";
//      return "myPage/Donghyo_orderHistory-deliveryStatus_2";
	}
	
	
	//주문취소/교환/반품
	@GetMapping(value="Donghyo_orderCancel-change-return_1")
	public String orderCancelChangeReturn(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_orderCancel-change-return_1";
//      return "myPage/Donghyo_orderCancel-change-return_2";
//      return "myPage/Donghyo_orderCancel-change-return_3";
	}
	
	
	//찜한 상품
	@GetMapping(value="Donghyo_likeItList")
	public String likeItList(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_likeItList";
	}
	
	
	//상품후기
	@GetMapping(value="Donghyo_productReview_1")
	public String productReview(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_productReview_1";
// 	    return "myPage/Donghyo_productReview(modal)_2";
//      return "myPage/Donghyo_productReview_3";
//      return "myPage/Donghyo_productReview_4";
	}
	
	
	//상품문의
	@GetMapping(value="Donghyo_productInquiry_1")
	public String productInquiry(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_productInquiry_1";
//      return "myPage/Donghyo_productInquiry_2";
	}
	
	
	//적립금
	@GetMapping(value="Donghyo_accumulated_money")
	public String accumulatedMoney(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/Donghyo_accmulated_money";
	}
}//MyPageController
