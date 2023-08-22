package kr.or.iei.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="myPage")
public class MyPageController {
	
	@GetMapping(value="/cssTest")
	public String cssTest() {
//      return "myPage/Donghyo_orderHistory-deliveryStatus_1";
//      return "myPage/Donghyo_orderHistory-deliveryStatus_2";
//      return "myPage/Donghyo_orderCancel-change-return_1";
//      return "myPage/Donghyo_orderCancel-change-return_2";
//      return "myPage/Donghyo_orderCancel-change-return_3";
//      return "myPage/Donghyo_productReview_1";
 	    return "myPage/Donghyo_productReview(modal)_2";
//      return "myPage/Donghyo_productReview_3";
//      return "myPage/Donghyo_productReview_4";
//      return "myPage/Donghyo_productInquiry_1";
//      return "myPage/Donghyo_productInquiry_2";
//      return "myPage/Donghyo_likeItList";
//	    return "myPage/Donghyo_myPageList";
//		return "myPage/Donghyo_accmulated_money";
	}//orderHistoryDeliveryStatus1()
	
}//MyPageController
