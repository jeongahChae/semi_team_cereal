package kr.or.iei.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.product.model.service.ProductService;
import lombok.Value;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/productList")
	public String productList() {
		return "product/productList";
	}
	
	@GetMapping(value="/writeFrm")
	public String productWriteFrm() {
		return "product/writeFrm";
	}
}
