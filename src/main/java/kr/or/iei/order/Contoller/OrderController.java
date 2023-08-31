package kr.or.iei.order.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.iei.member.model.vo.Member;
import kr.or.iei.order.Service.OrderService;
import kr.or.iei.order.vo.Cart;
import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductDetailData;

@Controller
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;

	@GetMapping(value = "/cart")
	public String cartList(@SessionAttribute(required = false) Member m, Model model) {
		if (m != null) {
			int memberNo = m.getMemberNo();
			List list = orderService.selectCartInfoList(memberNo);
			
			/*
			for (int i = 0; i < list.size(); i++) {
				Cart c = (Cart) list.get(i);
				int cartNo = c.getCartNo();
				int optionNo = c.getOptionNo();
				int count = c.getCount(); // 장바구니에 담긴 옵션 선택갯수
				
				for (int j = 0; j < list.size(); j++) {
					Cart c2 = (Cart) list.get(j);
					int cartNo2 = c2.getCartNo();
					int optionNo2 = c2.getOptionNo();
					int count2 = c2.getCount();
					if (optionNo == optionNo2) {
						int countUpdateResult = orderService.updateCartCount(cartNo, count2);
						if (countUpdateResult > 0) {
							int c2DeleteResult = orderService.deleteCart(cartNo2);
							if (c2DeleteResult > 0) {
								List cartInfoList = orderService.selectCartInfoList(memberNo);
								model.addAttribute("list", cartInfoList);
								return "order/cart";
							} else {
								System.out.println("삭제 오류");
							}
						} else {
							System.out.println("개수 수정 오류");
						}
					}
				}
				
			}
			*/
			model.addAttribute("list", list);				
		}
		return "order/cart";
	}
	
	@ResponseBody
	@GetMapping(value="/optionList")
	public List optionList(int optionNo, int productNo) {
		List list = orderService.selectProductOption(productNo);
		return list;
	}
	
	@ResponseBody
	@GetMapping(value="/chgOpt")
	public int changeOption(int cartNo, int newOptionNo, int newCount) {
		int result = orderService.updateOption(cartNo, newOptionNo, newCount);
		return result;
	}
	
	@GetMapping(value="/orderChk")
	public String checkOrderPage(@SessionAttribute(required = false) Member m, String no, Model model) {
		List list = orderService.selectCartToOrder(no);
		model.addAttribute("list", list);
		return "order/orderChk";
	}
	
	@GetMapping(value="/delCart")
	public String deleteCart(@SessionAttribute(required = false) Member m, String no, Model model) {
		boolean result = orderService.deleteCart(no, m.getMemberNo());
		if(result) {
			return "redirect:/order/cart";//html이 아니라 컨트롤러로 리다이렉트시킴
		} else {
			model.addAttribute("title", "장바구니 삭제 실패");
			model.addAttribute("msg", "삭제를 실패했습니다. 시스템 관리자에게 문의하세요.");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/order/cart");
			return "common/msg";
		}
	}
	
	@ResponseBody
	@PostMapping(value="/createOrder")
	public void createOrder(@SessionAttribute(required=false) Member m, String cart, int price) {
		//order_tbl에 데이터 삽입
		int orderTblResult = orderService.createOrder()
	}
}