package kr.or.iei.order.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
			if(list!=null) {
				model.addAttribute("list", list);				
			} else {
				model.addAttribute("list", null);
			}
		}
		return "order/cart";
	}
}