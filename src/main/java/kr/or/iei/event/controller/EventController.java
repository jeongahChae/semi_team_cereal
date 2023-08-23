package kr.or.iei.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@ResponseBody
	@GetMapping(value="/winnerBoard")
	public WinnerListData winnerBoardList(int reqPage) {
		WinnerListData wld = eventService.selectWinnerBoardList(reqPage);
		return wld;
	}
}
