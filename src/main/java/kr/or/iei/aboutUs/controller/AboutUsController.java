package kr.or.iei.aboutUs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
