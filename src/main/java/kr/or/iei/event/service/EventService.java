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
	public int insertEvent(Event e,  ArrayList<EventFile> fileList) {
		int result = eventDao.insertEvent(e);	//이미 만들어진 메소드 사용
		if(fileList != null) {
			int eventNo = eventDao.getEventNo();
			for(EventFile file : fileList) {
				//바로 insert하면 file 객체 내부의  noticeNo가 0
				// → 0인상태로 insert : 외래키 제약조건 위배(parent key not found)에러 
				file.setEventNo(eventNo);
				result += eventDao.insertEventFile(file);
			}
		}
		return result;
	}
}
