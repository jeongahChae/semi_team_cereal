package kr.or.iei.myPage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.myPage.service.MyPageService;
import kr.or.iei.order.model.vo.OrderListData;

@Controller
@RequestMapping(value="myPage", method = {RequestMethod.GET, RequestMethod.POST}) //마이페이지
public class MyPageController {
	@Autowired
	private MyPageService myPageService;
	
	//개인정보수정
	@GetMapping(value="updateMemberInfo")
	public String updateMemberInfo(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/updateMemberInfo";
	}//orderHistoryDeliveryStatus1()
	
	
	//주문내역 / 배송현황
	@GetMapping(value="orderHistory-deliveryStatus_1")
	public String orderHistoryDeliveryStatus(int btn, int reqPage, Model model, String startDate, String endDate) {
		if(startDate!=null && endDate!=null) {
			OrderListData old = myPageService.selectDateOrderList(reqPage, startDate, endDate);
			model.addAttribute("orderList", old.getOrderList()); //주문내역 전체 조회
			model.addAttribute("pageNavi", old.getPageNavi()); //페이지 네비게이션
			model.addAttribute("btn", btn);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}else {
			OrderListData old = myPageService.selectAllOrderList(reqPage); //주문내역 전체 조회			
			model.addAttribute("orderList", old.getOrderList()); //주문내역 전체 조회
			model.addAttribute("pageNavi", old.getPageNavi()); //페이지 네비게이션
			model.addAttribute("btn", btn);
		}
		return "myPage/orderHistory-deliveryStatus_1";
	}//orderHistoryDeliveryStatus(int btn, Model model)
	//주문내역 / 배송현황 2
	@GetMapping(value="orderHistory-deliveryStatus_2")
	public String orderHistoryDeliveryStatus2(int btn, int orderNo, Model model) {
//		System.out.println(orderNo);
		List orderDetail = myPageService.selectOrderHistory(orderNo);
		model.addAttribute("orderNo", orderNo);
		model.addAttribute("orderDetail", orderDetail);
		model.addAttribute("btn", btn);
		return "myPage/orderHistory-deliveryStatus_2";
	}//orderHistoryDeliveryStatus2(int btn, Model model)
	
	
	//주문취소/교환/반품
	@GetMapping(value="orderCancel-change-return_1")
	public String orderCancelChangeReturn(int btn, int reqPage, Model model) {
		OrderListData old = myPageService.selectAllOrderList2(reqPage); //주문내역 전체 조회			
		model.addAttribute("orderList", old.getOrderList()); //주문내역 전체 조회
		model.addAttribute("pageNavi", old.getPageNavi()); //페이지 네비게이션
		model.addAttribute("totalCount", old.getTotalCount()); //전체게시물 수
		model.addAttribute("btn", btn);
		return "myPage/orderCancel-change-return_1";
	}//orderCancelChangeReturn(int btn, Model model)
	//주문취소/교환/반품 2 - 접수
	@PostMapping("orderCancel-change-return_2")
	public String registerOrderCancelChangeReturn(int btn, Model model, String orderNo) {
		List orderList = myPageService.selectNoOrderList(orderNo);
		model.addAttribute("orderList", orderList);
		model.addAttribute("btn", btn);
		return "myPage/orderCancel-change-return_2";		
	}//registerOrderCancelChangeReturn(int btn, Model model)
	//주문취소/교환/반품 3 - 교환/반품 내역
	@GetMapping(value="orderCancel-change-return_3")
	public String changeReturnHistory(int btn, Model model) {
		model.addAttribute("btn", btn);
      return "myPage/orderCancel-change-return_3";
	}//changeReturnHistory(int btn, Model model)
	
	
	//찜한 상품
	@GetMapping(value="likeItList")
	public String likeItList(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/likeItList";
	}//likeItList(int btn, Model model)
	
	
	//상품 후기
	@GetMapping(value="productReview_1")
	public String productReview(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/productReview_1";
// 	    return "myPage/productReview(modal)_2";
	}//productReview(int btn, Model model)
	//상품 후기 3 - 작성한 후기
	@GetMapping(value="productReview_3")
	public String writtenReview(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/productReview_3";
	}//writtenReview(int btn, Model model)
	//상품 후기 4 - 작성한 후기 상세 보기
	@GetMapping(value="productReview_4")
	public String readReview(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/productReview_4";
	}//readReview(int btn, Model model)
	
	
	//상품 문의
	@GetMapping(value="productInquiry_1")
	public String productInquiry(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/productInquiry_1";
	}//productInquiry(int btn, Model model)
	//상품 문의 2 - 상세보기 및 답변
	@GetMapping(value="productInquiry_2")
	public String answerInquiry(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/productInquiry_2";
	}//answerInquiry(int btn, Model model)
	
	
	//적립금
	@GetMapping(value="accumulated_money")
	public String accumulatedMoney(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/accmulated_money";
	}//accumulatedMoney(int btn, Model model)
	
	
}//MyPageController
