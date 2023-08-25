package kr.or.iei.event.controller;

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
import kr.or.iei.event.service.EventService;
import kr.or.iei.event.vo.Event;
import kr.or.iei.event.vo.WinnerListData;

@Controller
@RequestMapping(value="/event")
public class EventController {

	@Value("${file.root}")
	private String root;
	@Value("${file.root2}")
	private String root2;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private EventService eventService;
	
	@GetMapping(value="/list")
	public String eventList(Model model) {
		List eventList = eventService.selectEventList();
		model.addAttribute("eventList", eventList);
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
	
	@PostMapping(value = "/write")
	public String eventWrite(Event e, MultipartFile upfile2, Model model) {// 업로드되는 파일을 여러개일수도 있으니까 배열로 받음
		String savepath = root2+"event/";	//저장경로 지정
		String filepath = fileUtil.getFilepath(savepath, upfile2.getOriginalFilename());	//중복파일명체크
		e.setThumbnail(filepath);
		File upFile = new File(savepath+filepath);
		try {
			upfile2.transferTo(upFile);	//파일업로드
		} catch (IllegalStateException | IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		int result = eventService.insertEvent(e); // fileList가 있는 경우와 없는 경우(null)를 나누어서 고려할 것
		if (result>0) {
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
	
	@GetMapping(value = "/view")
	public String eventView(int eventNo, Model model) {
		Event e = eventService.selectOneEvent(eventNo);// 삭제됐으면 null 아니면 조회결과

		if (e != null) {
			model.addAttribute("e", e);
			return "event/eventView";
		} else {
			model.addAttribute("title", "이벤트 조회 실패");
			model.addAttribute("msg", "이미 삭제된 게시물입니다.");
			model.addAttribute("icon", "info");
			model.addAttribute("loc", "/event/list");
			return "common/msg";
		}
	}
	
	@GetMapping(value = "/updateFrm")
	public String eventUpdate(int eventNo, Model model) {
		Event e = eventService.selectOneEvent(eventNo);
		model.addAttribute("e", e);
		return "event/eventUpdateFrm";
	}
	
	@PostMapping(value = "/update")
	public String update(Event e, MultipartFile upfile2, String delFileName, Model model) {
		String savepath = root2+"event/";	//저장경로 지정
		if(delFileName != e.getThumbnail()) {
			String filepath = fileUtil.getFilepath(savepath, upfile2.getOriginalFilename());	//중복파일명체크
			File upFile = new File(savepath+filepath);
			try {
				upfile2.transferTo(upFile);	//파일업로드
			} catch (IllegalStateException | IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			e.setThumbnail(filepath);	
		}
		int result = eventService.updateEvent(e); // 여기까지는 제목,내용,새 파일 추가 + 삭제까지 하는 것&폴더에 있는 파일까지
																		// 지우기 위해서 list로 받음
		if (result>0) {
			File delFile = new File(savepath + delFileName);
			delFile.delete();

			model.addAttribute("title", "수정 완료");
			model.addAttribute("msg", "게시글이 수정되었습니다.");
			model.addAttribute("icon", "success");
		} else {
			model.addAttribute("title", "수정 실패");
			model.addAttribute("msg", "관리자에게 문의하세요.");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/event/view?eventNo=" + e.getEventNo());
		return "common/msg";
	}
	
	@GetMapping(value = "/delete")
	public String deleteEvent(int eventNo, Model model) {
		Event e = eventService.selectOneEvent(eventNo);
		String filepath = e.getThumbnail();
		int result = eventService.deleteEvent(eventNo);
		if (result>0) {
			String savepath = root2 + "event/";
				File delFile = new File(savepath + e.getThumbnail());
				delFile.delete();
				model.addAttribute("title", "삭제 완료");
				model.addAttribute("msg", "게시글이 삭제되었습니다.");
				model.addAttribute("icon", "success");
				model.addAttribute("loc", "/event/list?reqPage=1");
		} else {
			model.addAttribute("title", "삭제 실패");
			model.addAttribute("msg", "관리자에게 문의하세요");
			model.addAttribute("icon", "error");
			model.addAttribute("loc", "/event/view?eventNo=" + eventNo);
		}
		return "common/msg";
	}
}
