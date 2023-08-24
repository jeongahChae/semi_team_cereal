package kr.or.iei.adminPage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="adminPage") //관리자 페이지
public class AdminPageController {
    
	//회원목록
    @GetMapping(value="/allMemberList")
    public String allMemberListt(int btn, Model model) {
        model.addAttribute("btn", btn);
    	return "adminPage/allMemberList";
    }//allMemberListt(int btn, Model model)
    
    
    //등록 상품 현황
    @GetMapping(value="/allProductList")
    public String allProductList(int btn, Model model) {
    	model.addAttribute("btn", btn);
        return "adminPage/allProductList";
    }//allProductList(int btn, Model model)
    
    
    //주문 현황 관리
    @GetMapping(value="/orderStatusManagement")
    public String orderStatusManagement(int btn, Model model) {
    	model.addAttribute("btn", btn);
    	return "adminPage/orderStatusManagement";
    }//orderStatusManagement(int btn, Model model)
    
    
    //상품 등록
    @GetMapping(value="/insertProduct")
    public String insertProduct(int btn, Model model) {
    	model.addAttribute("btn", btn);
    	return "product/writeFrm_copy";
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
}//AdminPageController
