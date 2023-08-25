package kr.or.iei.usualQna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.usualQna.model.service.UsualQnaService;
import kr.or.iei.usualQna.model.vo.UsualQnaListData;

@Controller
@RequestMapping(value = "/usualQna")
public class UsualQnaController {
	@Autowired
	private UsualQnaService usualQnaService;
	
	@GetMapping(value = "/list")
	public String usualQnaList(Model model, int reqPage) {
		UsualQnaListData uld = usualQnaService.selectUsualQnaList(reqPage);
		model.addAttribute("usualQnaList",uld.getUsualQnaList());
		model.addAttribute("pageNavi", uld.getPageNavi());
		model.addAttribute("btn",1);
		return "notice/usualQnaList";
	}
}
