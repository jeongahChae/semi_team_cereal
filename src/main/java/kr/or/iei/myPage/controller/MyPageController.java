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
import kr.or.iei.myPage.vo.LikeListData;
import kr.or.iei.myPage.vo.Order;
import kr.or.iei.myPage.vo.OrderCancelListData;
import kr.or.iei.myPage.vo.OrderListData;
import kr.or.iei.product.model.vo.ProductListData;

@Controller
@RequestMapping(value="myPage", method = {RequestMethod.GET, RequestMethod.POST}) //마이페이지
public class MyPageController {
	@Autowired
	private MyPageService myPageService;
	
	@GetMapping(value="myPageList")
	public String myPageList(int btn, Model model) {
		model.addAttribute("btn", btn);
		return "myPage/myPageList";
	}
	
	//개인정보수정
	@GetMapping(value="updateMemberInfo")
	public String updateMemberInfo(int btn, Model model) {
		System.out.println("update");
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
	
	/*
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
	@PostMapping(value="orderCancel-change-return_2")
	public String registerOrderCancelChangeReturn(int btn, Model model, String orderNo) {
		List orderList = myPageService.selectNoOrderList(orderNo);
//		System.out.println(orderNo);
//		System.out.println(orderList);
		model.addAttribute("orderList", orderList);
		model.addAttribute("btn", btn);
		return "myPage/orderCancel-change-return_2";		
	}//registerOrderCancelChangeReturn(int btn, Model model)
	//주문취소/교환/반품 - 접수 양식
	@GetMapping(value="insertCancel")
	public String insertCancel(int btn, int reqPage, Model model, String orderStatus, String reasonDetail, int[] orderNo) {
		
		for(int i=0;i<orderNo.length;i++) {
			
			System.out.println("orderNo["+i+"]: "+orderNo[i]);			
			//조회 과정 후, 인서트
			List list = myPageService.selectOrderHistory(orderNo[i]);
			System.out.println("list.size(): "+list.size());
			for(int j=0;j<list.size();j++){
				Order order = (Order)list.get(j); //형변환 필요
				
				System.out.println("orderStatus: "+orderStatus); //3: 주문취소, 7: 교환, 8: 반품
				if(orderStatus.equals("3")) {
					//주문취소
					int result = myPageService.insertOrderCancelList(Integer.parseInt(orderStatus), reasonDetail, order.getOrderNo(), order.getProductName(), order.getOrderDate(), order.getOrderAmount(), order.getMemberName(), order.getMemberAddr());
					if(result>0) {
						System.out.println("insert 성공: "+result);			
						System.out.println("order.getOrderStatus(): "+order.getOrderStatus());
						//주문내역에서 삭제
						int resultDelete = myPageService.deleteOrderHistory(order.getOrderNo());
						if(result > 0) {
							System.out.println("주문내역에서 삭제 성공");
						}else {
							System.out.println("주문내역에서 삭제 실패");
						}	
						
					}else {
						System.out.println("insert 실패");
					}					
				}else if(orderStatus.equals("7")) {
					//교환
					int result = myPageService.insertOrderCancelList(Integer.parseInt(orderStatus), reasonDetail, order.getOrderNo(), order.getProductName(), order.getOrderDate(), order.getOrderAmount(), order.getMemberName(), order.getMemberAddr());
					if(result>0) {
						System.out.println("insert 성공: "+result);	
						
						//주문내역에서 삭제
						int resultDelete = myPageService.deleteOrderHistory(order.getOrderNo());
						if(result > 0) {
							System.out.println("주문내역에서 삭제 성공");
						}else {
							System.out.println("주문내역에서 삭제 실패");
						}	
					}else {
						System.out.println("insert 실패");
					}	
				}else if(orderStatus.equals("8")) {
					//반품
					int result = myPageService.insertOrderCancelList(Integer.parseInt(orderStatus), reasonDetail, order.getOrderNo(), order.getProductName(), order.getOrderDate(), order.getOrderAmount(), order.getMemberName(), order.getMemberAddr());
					if(result>0) {
						System.out.println("insert 성공: "+result);		
						
						//주문내역에서 삭제
						int resultDelete = myPageService.deleteOrderHistory(order.getOrderNo());
						if(result > 0) {
							System.out.println("주문내역에서 삭제 성공");
						}else {
							System.out.println("주문내역에서 삭제 실패");
						}	
					}else {
						System.out.println("insert 실패");
					}	
				}

			}//for(int j=0;j<list.size();j++)
		}//for(int i=0;i<orderNo.length;i++)
		
		
	
		OrderCancelListData ocld = myPageService.selectAllOrderCancel(reqPage);
		model.addAttribute("cancelList", ocld.getOrderCancelList());
		model.addAttribute("pageNavi", ocld.getPageNavi());
		model.addAttribute("btn", btn);
		
		return "myPage/orderCancel-change-return_3";
	}
	//주문취소/교환/반품 내역 출력
	@GetMapping(value="orderCancel-change-return_3")
	public String orderCancelChangeReturnHistory(int btn, int reqPage, Model model) {
		OrderCancelListData ocld = myPageService.selectAllOrderCancel(reqPage);
		model.addAttribute("cancelList", ocld.getOrderCancelList());
		model.addAttribute("pageNavi", ocld.getPageNavi());
		model.addAttribute("btn", btn);
		return "myPage/orderCancel-change-return_3";
	}
	*/
	
	
	//찜한 상품
	@GetMapping(value="likeItList")
	public String likeItList(int btn, Model model, int reqPage) {
		LikeListData lld = myPageService.selectAllListList(reqPage);
		System.out.println(lld.getLikeList());
		model.addAttribute("likeList", lld.getLikeList());
		model.addAttribute("pageNavi", lld.getPageNavi());
		model.addAttribute("btn", btn);
		return "myPage/likeItList";
	}//likeItList(int btn, Model model)
	
	
	//상품 후기
	@GetMapping(value="productReview_1")
	public String productReview(int btn, Model model, int reqPage) {
		ProductListData pld = myPageService.selectAllreview(reqPage);
		model.addAttribute("reviewList", pld.getProductList());
		model.addAttribute("pageNavi", pld.getPageNavi());
		model.addAttribute("btn", btn);
		return "myPage/productReview_1";
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
