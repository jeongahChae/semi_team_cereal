package kr.or.iei.aboutUs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.aboutUs.dao.AboutUsDao;
import kr.or.iei.aboutUs.vo.News;
import kr.or.iei.aboutUs.vo.NewsListData;

@Service
public class AboutUsService {

	@Autowired
	private AboutUsDao aboutUsDao;

	public List selectAllStore() {
		List list = aboutUsDao.selectAllStore();
		return list;
	}

	public NewsListData selectNewsList(int reqPage) {
		int numPerPage = 5;
		int end = reqPage * numPerPage;
		int start = end-numPerPage +1;
		List noticeList = aboutUsDao.selectNewsList(start,end);
		int totalCount = aboutUsDao.selectNewsTotalCount();

		//총 페이지 수 계산
		int totalPage = totalCount%numPerPage == 0 ? totalCount/numPerPage : totalCount/numPerPage + 1;
		
			//페이지 네비게이션 사이즈 지정
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize + 1;
		String pageNavi = "<ul class='pagination'>";
			//이전 버튼 제작
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/news/list?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
			//페이지 숫자 만들기
		for(int i=0 ; i<pageNaviSize ; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/news/list?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			} else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/news/list?reqPage="+(pageNo)+"'>";
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
			pageNavi += "<a class='page-item' href='/news/list?reqPage="+(pageNo)+"'>";//페이지 숫자를 만들 때 pageNo++한 상태에서 나오기 때문에
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		NewsListData nld = new NewsListData(noticeList,pageNavi); 
		return nld;
	}

	public News selectOneNotice(int newsNo) {
		News n = aboutUsDao.selectOneNotice(newsNo);
		return n;
	}

	@Transactional
	public int insertNews(News n) {
		int result = aboutUsDao.insertNews(n);
		return result;
	}
}
