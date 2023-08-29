package kr.or.iei.usualQna.controller;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticeFile;
import kr.or.iei.usualQna.model.service.UsualQnaService;
import kr.or.iei.usualQna.model.vo.UsualQna;
import kr.or.iei.usualQna.model.vo.UsualQnaListData;

@Controller
@RequestMapping(value = "/usualQna")
public class UsualQnaController {
	@Autowired
	private UsualQnaService usualQnaService;
	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtil fileUtil;
	
	@GetMapping(value = "/list")
	public String usualQnaList(Model model, int reqPage) {
		UsualQnaListData uld = usualQnaService.selectUsualQnaList(reqPage);
		model.addAttribute("usualQnaList",uld.getUsualQnaList());
		model.addAttribute("pageNavi", uld.getPageNavi());
		model.addAttribute("btn",1);
		return "usualQna/usualQnaList";
	}
	@GetMapping(value = "/writeFrm")
	public String usualQnaWruteFrm(Model model) {
		model.addAttribute("btn", 1);
		return "usualQna/usualQnaWriteFrm";
	}
	@PostMapping(value = "/write")
	public String usualQnaWrite(UsualQna u, Model model) {
		System.out.println(u.getQnaCategory());
		int result = usualQnaService.insertUsualQna(u);
		if(result == 0) {
			return "usualQna/usualQnaWriteFrm";
		}
		model.addAttribute("btn", 1);
		return "redirect:/usualQna/list?reqPage=1";
	}
	
	@GetMapping(value = "/delete")
	public String deleteUsualQna(int qnaNo, Model model) {
		int result= usualQnaService.deleteUsualQna(qnaNo);
		model.addAttribute("btn", 1);
		return "redirect:/usualQna/list?reqPage=1";
	}
	
	@GetMapping(value = "/updateFrm")
	public String updateFrm(int qnaNo, Model model) {
		UsualQna q = usualQnaService.getUsualQna(qnaNo);
		model.addAttribute("q", q);
		model.addAttribute("btn", 1);
		return "usualQna/usualQnaUpdateFrm";
	}
	@PostMapping(value = "/update")
	public String update(UsualQna u, Model model) {
		int result = usualQnaService.updateUsualQna(u);
		model.addAttribute("u", u);
		model.addAttribute("btn",1);
		return "redirect:/usualQna/list?reqPage=1";
		
	}
	
	
	@ResponseBody
	@PostMapping(value = "/noticeEditor", produces = "plain/text;charset=utf-8")
	public String editorUpload(MultipartFile file) {
		String savepath = root+"noticeEditor/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		try {
			file.transferTo(image);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/noticeEditor/"+filepath;
	}
}
