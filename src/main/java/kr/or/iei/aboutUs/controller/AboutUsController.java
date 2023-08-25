package kr.or.iei.aboutUs.controller;

import java.io.File;
import java.io.IOException;
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
	@Value("${file.root2}")
	private String root2;
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
			model.addAttribute("title", "목록 불러오기 실패");
			model.addAttribute("msg", "관리자에게 문의하세요.");
			model.addAttribute("icon", "error");
			 model.addAttribute("loc", "/");
			return "common/msg";
		}
	}
	
	@ResponseBody
	@PostMapping(value="/news/editor", produces="plain/text;charset=utf-8")//produces : 파일명 한글로 받기용
	public String editorUpload(MultipartFile file) {//file은 넘겨준 form.append의 첫번째 "file"과 맞춘 것
		String savepath = root2+"editor/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		System.out.println(image);
		try {
			file.transferTo(image);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/img/editor/"+filepath;
	}
	
	@PostMapping(value = "/news/write")
	public String noticeWrite(News n, Model model) {// 업로드되는 파일을 여러개일수도 있으니까 배열로 받음
		System.out.println(n.getNewsTitle());
		int result = aboutUsService.insertNews(n); // fileList가 있는 경우와 없는 경우(null)를 나누어서 고려할 것
		if(result>0) {
			model.addAttribute("title", "기사 작성 성공");
			model.addAttribute("msg", "미디어 센터 페이지로 이동합니다.");
			model.addAttribute("icon", "success");
		} else {
			model.addAttribute("title", "공지사항 작성 실패");
			model.addAttribute("msg", "관리자에게 문의하세요.");
			model.addAttribute("icon", "error");
		}
		 model.addAttribute("loc", "/news/list?reqPage=1");
		 
		 return "common/msg";
	}
}
