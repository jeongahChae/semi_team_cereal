package kr.or.iei.event.controller;

import java.io.File;
import java.io.IOException;

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
import kr.or.iei.event.service.EventService;
import kr.or.iei.event.vo.Event;
import kr.or.iei.event.vo.WinnerListData;

@Controller
@RequestMapping(value="/event")
public class EventController {

	@Value("${file.root}")
	private String root;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private EventService eventService;
	
	@GetMapping(value="/list")
	public String eventList() {
		return "event/eventList";
	}
	
	@ResponseBody
	@GetMapping(value="/winnerBoard")
	public WinnerListData winnerBoardList(int reqPage) {
		WinnerListData wld = eventService.selectWinnerBoardList(reqPage);
		return wld;
	}
	
	@ResponseBody
	@PostMapping(value="/editor", produces="plain/text;charset=utf-8")//produces : 파일명 한글로 받기용
	public String editorUpload(MultipartFile file) {//file은 넘겨준 form.append의 첫번째 "file"과 맞춘 것
		String savepath = root+"editor/";
		String filepath = fileUtil.getFilepath(savepath, file.getOriginalFilename());
		File image = new File(savepath+filepath);
		System.out.println(image);
		try {
			file.transferTo(image);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/editor/"+filepath;
	}
	
	@PostMapping(value = "/write")
	public String noticeWrite(Event e, Model model) {// 업로드되는 파일을 여러개일수도 있으니까 배열로 받음
		int result = eventService.insertEvent(e); // fileList가 있는 경우와 없는 경우(null)를 나누어서 고려할 것
		if(result>0) {
			model.addAttribute("title", "이벤트 등록 성공");
			model.addAttribute("msg", "이벤트 페이지로 이동합니다.");
			model.addAttribute("icon", "success");
		} else {
			model.addAttribute("title", "이벤트 작성 실패");
			model.addAttribute("msg", "관리자에게 문의하세요.");
			model.addAttribute("icon", "error");
		}
		 model.addAttribute("loc", "/event/list");
		 
		 return "common/msg";
	}
}
