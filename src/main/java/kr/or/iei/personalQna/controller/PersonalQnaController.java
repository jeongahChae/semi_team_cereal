package kr.or.iei.personalQna.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.FileUtil;
import kr.or.iei.personalQna.model.service.PersonalQnaService;
import kr.or.iei.personalQna.model.vo.PersonalQnaListData;

@Controller
@RequestMapping(value = "/personalQna")
public class PersonalQnaController {
	@Autowired
	private PersonalQnaService personalQnaService;
	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping(value = "/list")
	public String personalQnaList(Model model, int reqPage) {
		PersonalQnaListData pld = personalQnaService.seletPersonalQnaList(reqPage);
		model.addAttribute("personalQnaList", pld.getPersonalQnaList());
		model.addAttribute("pageNavi", pld.getPageNavi());
		model.addAttribute("btn", 2);
		return "personalQna/personalQnaList";
	}
	
	@GetMapping(value = "/writeFrm")
	public String personalQnaWriteFrm(Model model) {
		model.addAttribute("btn", 2);
		return "personalQna/personalQnaWriteFrm";
	}
	
}
