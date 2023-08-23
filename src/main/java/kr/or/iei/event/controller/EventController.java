package kr.or.iei.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.aboutUs.vo.NewsListData;
import kr.or.iei.event.service.EventService;
import kr.or.iei.event.vo.WinnerListData;

@Controller
@RequestMapping(value="/event")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping(value="/list")
	public String eventList() {
		return "event/eventList";
	}
	@GetMapping(value="/winnerBoard")
	public String winnerBoardList(int reqPage, Model model) {
		WinnerListData wld = eventService.selectWinnerBoardList(reqPage);
		model.addAttribute("winnerList", wld.getWinnerBoardList());
		model.addAttribute("pageNavi", wld.getPageNavi());
		return "event/eventList";
	}
}
