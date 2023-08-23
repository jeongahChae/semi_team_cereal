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
		
			//페이지 네비게이션 사이즈 지정
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination'>";
			//이전 버튼 제작
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='javascript:void(0);'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
			//페이지 숫자 만들기
		for(int i=0 ; i<pageNaviSize ; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='javascript:void(0);'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='javascript:void(0);'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				break;	//for문을 멈추고 나감
			}
		}
			//다음 버튼 제작
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='javascript:void(0);'>";//페이지 숫자를 만들 때 pageNo++한 상태에서 나오기 때문에
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		WinnerListData wld = new WinnerListData(winnerBoardList,pageNavi); 
		return wld;
	}
}
