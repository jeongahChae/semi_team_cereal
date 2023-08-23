package kr.or.iei.event.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.event.dao.EventDao;

@Service
public class EventService {

	@Autowired
	private EventDao eventDao;
}
