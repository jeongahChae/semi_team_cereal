package kr.or.iei.personalQna.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.personalQna.model.service.PersonalQnaService;
import kr.or.iei.personalQna.model.vo.PersonalQna;
import kr.or.iei.personalQna.model.vo.PersonalQnaFile;
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
		PersonalQnaListData pld = personalQnaService.selectPersonalQnaList(reqPage);
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
	
	@PostMapping(value = "/write")
	public String personalQnaWrite(PersonalQna p, MultipartFile[] addfile, Model model) {
		ArrayList<PersonalQnaFile> fileList = null;
		if(!addfile[0].isEmpty()) {
			fileList = new ArrayList<PersonalQnaFile>();
			String savepath = root+"personalQna/";
			for(MultipartFile file : addfile) {
				String filename = file.getOriginalFilename();
				String filepath = fileUtil.getFilepath(savepath, filename);
				File uploadFile = new File(savepath+filepath);
				try {
					file.transferTo(uploadFile);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PersonalQnaFile pf = new PersonalQnaFile();
				pf.setFilename(filename);
				pf.setFilepath(filepath);
				fileList.add(pf);
			}
		}
		int result = personalQnaService.insertPersonalQna(p,fileList);
		model.addAttribute("btn", 3);
		return "redirect:/personalQna/list?reqPage=1";
	}
}
















