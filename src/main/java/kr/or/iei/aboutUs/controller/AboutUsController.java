package kr.or.iei.aboutUs.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.aboutUs.service.AboutUsService;
import kr.or.iei.aboutUs.vo.News;
import kr.or.iei.aboutUs.vo.NewsListData;

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
	public String offlineStore() {
		return "aboutUs/offStore";
	}
	
	@ResponseBody
	@GetMapping(value="/store/detail")
	public List storeList() {
		List list = aboutUsService.selectAllStore();
		return list;
	}
	
	@GetMapping(value = "/news/list")
	public String newsList(Model model, int reqPage) {
		NewsListData nld = aboutUsService.selectNewsList(reqPage);
		model.addAttribute("newsList", nld.getNewsList());
		model.addAttribute("pageNavi", nld.getPageNavi());
		return "aboutUs/news";
	}
	
	@GetMapping(value="/news/view")
	public String newsView(int newsNo, Model model) {
		News n = aboutUsService.selectOneNotice(newsNo);// 삭제됐으면 null 아니면 조회결과
		if (n != null) {
			model.addAttribute("n", n);
			return "aboutUs/newsView";
		}else {
			return "/";//스윗얼럿 만들고 수정할 것
		}
	}
	
	@ResponseBody
	@PostMapping(value="/editor", produces="plain/text;charset=utf-8")//produces : 파일명 한글로 받기용
	public String editorUpload(MultipartFile file) {//file은 넘겨준 form.append의 첫번째 "file"과 맞춘 것
		String savepath = root+"editor/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		try {
			file.transferTo(image);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/editor/"+filepath;
	}
	

}
