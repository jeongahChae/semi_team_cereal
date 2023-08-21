package kr.or.iei.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.iei.notice.model.service.NoticeService;

@Controller
@RequestMapping(value = "/notice")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	
}
