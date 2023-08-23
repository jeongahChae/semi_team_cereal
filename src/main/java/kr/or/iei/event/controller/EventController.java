package kr.or.iei.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.event.service.EventService;

@Controller
@RequestMapping(value="/event")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@GetMapping(value="/list")
	public String eventList() {
		return "event/eventList";
	}
}
