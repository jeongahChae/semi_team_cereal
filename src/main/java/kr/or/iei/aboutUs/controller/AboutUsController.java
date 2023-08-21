package kr.or.iei.aboutUs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.or.iei.FileUtil;

@Controller
public class AboutUsController {

	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping(value="/aboutUs")
	public String aboutUs() {
		return "aboutUs/aboutUs";
	}
	
}
