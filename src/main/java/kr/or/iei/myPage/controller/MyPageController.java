package kr.or.iei.myPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="myPage")
public class MyPageController {
	
	@GetMapping(value="/cssTest")
	public String orderHistoryDeliveryStatus1() {
		return "myPage/Donghyo_productReview(modal)_2";
	}//orderHistoryDeliveryStatus1()
	
}//MyPageController
