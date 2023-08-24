package kr.or.iei.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.aboutUs.vo.NewsListData;
import kr.or.iei.event.dao.EventDao;
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
}
