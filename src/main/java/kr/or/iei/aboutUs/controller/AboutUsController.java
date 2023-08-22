package kr.or.iei.aboutUs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.FileUtil;
import kr.or.iei.aboutUs.service.AboutUsService;

@Controller
public class AboutUsController {

	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private AboutUsService aboutUsService;
	
	
	@GetMapping(value="/aboutUs")
	public String aboutUs() {
		return "aboutUs/aboutUs";
	}
	
	@GetMapping(value="/offlineStore")
	public String offlineStore(Model model) {
		return "aboutUs/offStore";
	}
	
	@ResponseBody
	@GetMapping(value="/store/detail")
	public List storeList() {
		List list = aboutUsService.selectAllStore();
		return list;
	}
	
	
}
