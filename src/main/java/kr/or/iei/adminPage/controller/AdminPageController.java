package kr.or.iei.adminPage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.adminPage.service.AdminPageService;
import kr.or.iei.adminPage.vo.Dashboard;
import kr.or.iei.member.model.vo.MemberListData;
import kr.or.iei.myPage.service.MyPageService;
import kr.or.iei.myPage.vo.Order;
import kr.or.iei.myPage.vo.OrderListData;
import kr.or.iei.product.model.vo.ProductListData;

@Controller
@RequestMapping(value="adminPage") //관리자 페이지
public class AdminPageController {
    @Autowired
    private AdminPageService adminPageService;
	@Autowired
	private MyPageService myPageService;
    
	//회원목록
    @GetMapping(value="/allMemberList")
    public String allMemberListt(int btn, Model model, int reqPage) {
    	MemberListData mld = adminPageService.selectAllMember(reqPage);
    	model.addAttribute("memberList", mld.getMemberList());
    	model.addAttribute("pageNavi", mld.getPageNavi());
        model.addAttribute("btn", btn);
    	return "adminPage/allMemberList";
    }//allMemberListt(int btn, Model model)
    
    
    //등록 상품 현황
    @GetMapping(value="/allProductList")
    public String allProductList(int btn, Model model, int reqPage) {
    	ProductListData pld = adminPageService.selectAllProduct(reqPage);
    	System.out.println("pld.getProductList().size(): "+pld.getProductList().size());
    	model.addAttribute("productList", pld.getProductList());
    	model.addAttribute("pageNavi", pld.getPageNavi());
    	model.addAttribute("btn", btn);
        return "adminPage/allProductList";
    }//allProductList(int btn, Model model)

    
    //주문 현황 관리
    @GetMapping(value="/orderStatusManagement")
    public String orderStatusManagement(int btn, Model model, int reqPage) {
    	OrderListData old = adminPageService.selectAllOrderListAdmin(reqPage);
		model.addAttribute("orderList", old.getOrderList()); //주문내역 전체 조회
		model.addAttribute("pageNavi", old.getPageNavi()); //페이지 네비게이션
		model.addAttribute("btn", btn);
    	return "adminPage/orderStatusManagement";
    }//orderStatusManagement(int btn, Model model)
    //주문 현황 관리 - 업데이트
    @GetMapping(value="orderUpdate")
    public String orderUpdate(int btn, int reqPage, Model model, String selectOrderStatus, String orderNo) {
    	long orderNO = Long.parseLong(orderNo);
    	int orderStatus = Integer.parseInt(selectOrderStatus);
    	//조회 후 업데이트
    	List order = adminPageService.selectOrderAdmin(orderNO);
    	
    	for(int i=0;i<order.size();i++) {
    		System.out.println("order.get(i): "+order.get(i));
    	}
    	
    	Order orderList = (Order)order.get(0);
    	
    	System.out.println("order.get(0): "+order.get(0));
    	
    	int result = adminPageService.orderUpdate(orderStatus, orderList.getOrderNo());
    	if(result > 0) {
    		System.out.println("업데이트 완료");
    		
    		if(orderStatus==3 || orderStatus==7 || orderStatus==8) {
    			String reasonDetail = "관리자 관한으로 수행";
    			
				//int result2 = myPageService.insertOrderCancelList(orderStatus, reasonDetail, orderList.getOrderNo(), orderList.getProductName(), orderList.getOrderDate(), orderList.getOrderAmount(), orderList.getMemberName(), orderList.getMemberAddr());
    			System.out.println("orderList.getMemberNo(): "+orderList.getMemberNo());
    			System.out.println("orderList.getProductNo(): "+orderList.getProductNo());
    			int result2 = myPageService.insertOrderCancelList(orderStatus, reasonDetail, orderList.getOrderNo(), orderList.getOptionName(), orderList.getCount(), orderList.getMemberNo(), orderList.getProductNo());
    			if(result>0) {
					System.out.println("insert 성공: "+result);			
					
					//주문내역에서 삭제
					int resultDelete = myPageService.deleteOrderHistory(orderList.getOrderNo());
					if(result > 0) {
						System.out.println("주문내역에서 삭제 성공");
					}else {
						System.out.println("주문내역에서 삭제 실패");
					}	
					
				}else {
					System.out.println("insert 실패");
				}	
    		}
    		
    	}
    	
    
    	OrderListData old = adminPageService.selectAllOrderListAdmin(reqPage);
    	model.addAttribute("orderList", old.getOrderList());
    	model.addAttribute("pageNavi", old.getPageNavi());
    	model.addAttribute("btn", btn);
    	return "adminPage/orderStatusManagement";
    }
    
    //상품 등록
    @GetMapping(value="/insertProduct")
    public String insertProduct(int btn, Model model) {
    	model.addAttribute("btn", btn);
    	return "product/writeFrm";
    }//insertProduct(int btn, Model model)
    
    
    //기사등록
    @GetMapping(value="/insertNews")
    public String insertNews(int btn, Model model) {
    	model.addAttribute("btn", btn);
    	return "aboutUs/newsWriteFrm";
    }
    
    //이벤트등록
    @GetMapping(value="/insertEvent")
    public String insertEvent(int btn, Model model) {
    	model.addAttribute("btn", btn);
    	return "event/eventWriteFrm";
    }
    
    //당첨자 발표 등록
    @GetMapping(value="/insertWinner")
    public String insertWinner(int btn, Model model) {
    	model.addAttribute("btn", btn);
    	return "event/winnerWriteFrm";
    }
    
    //대시보드 이동
    @GetMapping(value="/dashboard")
    public String dashboard(int btn, Model model) {
    	model.addAttribute("btn", btn);
    	Dashboard d = adminPageService.selectDashboardData();
    	model.addAttribute("d", d);
    	return "adminPage/dashboard";
    }
    
    
    //카테고리별 매출
    @ResponseBody
    @GetMapping(value="/findCategorySales")
    public List findCategorySales(int year, int month, int category) {
        String strMonth = String.format("%02d", month);
    	List categorySalesList = adminPageService.selectCategorySales(year, strMonth, category);
    	return categorySalesList;
    }
    
}//AdminPageController
