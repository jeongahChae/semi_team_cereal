package kr.or.iei.event.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.event.service.EventService;
import kr.or.iei.event.vo.Event;
import kr.or.iei.event.vo.EventFile;
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
	public String noticeWrite(Event e, MultipartFile[] upfile2, Model model) {// 업로드되는 파일을 여러개일수도 있으니까 배열로 받음
		ArrayList<EventFile> fileList = null; // 첨부파일 목록을 저장할 리스트
		if (!upfile2[0].isEmpty()) {// 첫 번째 파일이 비어있지 않으면
			fileList = new ArrayList<EventFile>();// 파일이 없으면 리스트필요없으니까 있을 때 만들어줌!
			String savepath = root + "event/";
			for (MultipartFile file : upfile2) {
				String filename = file.getOriginalFilename();// 사용자가 실제로 올린 파일 이름
				System.out.println("filename : " + filename);
				// 중복 파일명 체크
				String filepath = fileUtil.getFilepath(savepath, filename);
				System.out.println("filepath : " + filepath);
				// 실제 폴더에 파일을 업로드
				File uploadFile = new File(savepath + filepath);
				try {
					file.transferTo(uploadFile);
				} catch (IllegalStateException | IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				} // 파일 업로드(물리적인 처리) 끝
				EventFile nf = new EventFile();
				nf.setFilename(filename);
				nf.setFilepath(filepath);
				fileList.add(nf);
			}
		}
		int result = eventService.insertEvent(e, fileList); // fileList가 있는 경우와 없는 경우(null)를 나누어서 고려할 것
		// 성공케이스
		// 1. 파일이 없는 경우 result = 1
		// 2. 파일이 있는 경우 result = 파일갯수+1

		if ((fileList == null && result == 1) || (fileList != null && result == (fileList.size() + 1))) {
			model.addAttribute("title", "이벤트 등록 성공");
			model.addAttribute("msg", "이벤트 페이지로 이동합니다.");
			model.addAttribute("icon", "success");
		} else {
			model.addAttribute("title", "이벤트 작성 실패");
			model.addAttribute("msg", "관리자에게 문의하세요.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/event/list?reqPage=1");
		return "common/msg";
	}
}
