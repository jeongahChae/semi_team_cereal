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
@RequestMapping(value="/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/cart")
	public String cartList(@SessionAttribute(required = false) Member m, Model model) {
		int memberNo = (m == null) ? 0 : m.getMemberNo();
		if(memberNo == 0) {
			model.addAttribute("noMember", memberNo);
		}else {
			List cartInfoList = orderService.selectCartInfoList(memberNo);
				model.addAttribute("cil", cartInfoList);
			
		}
		return "/order/cart";
	}
}
