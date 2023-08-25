package kr.or.iei.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.event.dao.EventDao;
import kr.or.iei.event.vo.Event;
import kr.or.iei.event.vo.EventFile;
import kr.or.iei.event.vo.WinnerListData;

@Service
public class EventService {

	@Autowired
	private EventDao eventDao;

	public WinnerListData selectWinnerBoardList(int reqPage) {
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end-numPerPage +1;
		List winnerBoardList = eventDao.selectWinnerBoardList(start,end);
		int totalCount = eventDao.selectWinnerBoardListTotalCount();

		//총 페이지 수 계산
		int totalPage = totalCount%numPerPage == 0 ? totalCount/numPerPage : totalCount/numPerPage + 1;
		
		WinnerListData wld = new WinnerListData(winnerBoardList,totalPage); 
		return wld;
	}

	@Transactional
	public int insertEvent(Event e) {
		int result = eventDao.insertEvent(e);
		return result;
	}

	public List selectEventList() {
		List eventList = eventDao.selectEventList();
		return eventList;
	}

	public Event selectOneEvent(int eventNo) {
		Event e = eventDao.selectOneEvent(eventNo);
		return e;
	}

	@Transactional
	public int updateEvent(Event e) {
		int result = eventDao.updateEvent(e);
		return result;
	}
	
	@Transactional
	public int deleteEvent(int eventNo) {
		int result = eventDao.deleteEvent(eventNo);
		return result;
	}
}
