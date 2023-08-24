package kr.or.iei.myPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.myPage.dao.MyPageDao;
import kr.or.iei.order.model.vo.OrderListData;

@Service
public class MyPageService {
	@Autowired
	private MyPageDao myPageDao;

	public OrderListData selectAllOrderList(int reqPage) {
		//게시판 페이지는 가장 마지막에 insert된 요소가 제일 상단에 위치하는 내림차순 정렬로 표기
		int numPerPage = 10; //한 페이지에 표시되는 게시물 수를 10개로 설정
		int end = reqPage * numPerPage; //끝나는 개수 숫자 /한 페이지에 표시되는 마지막 숫자 /reqPage가 1일 경우: 10
		int start = end - numPerPage +1; //시작하는 개수 숫자 /한 페이지에 표시되는 시작 숫자/reqPage가 1일 경우: 1
		List orderList = myPageDao.selectAllOrderList(start, end);
		
		//pageNavi 제작준비
		int totalCount = myPageDao.selectOrderTotalCount();
		int totalPage = (int)Math.ceil(totalCount/(double)numPerPage);
		//pageNavi 사이즈(넘버 갯수 지정)
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		//pageNavi 제작
		String pageNavi = "<ul class='page-design circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/myPage/Donghyo_orderHistory-deliveryStatus_1?btn=1&reqPage="+1+"'>";//1번페이지로 
			pageNavi += "<span class='material-icons'>first_page</span>";//(|<)이렇게 생김
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/myPage/Donghyo_orderHistory-deliveryStatus_1?btn=1&reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		//pageNavi 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/myPage/Donghyo_orderHistory-deliveryStatus_1?btn=1&reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/myPage/Donghyo_orderHistory-deliveryStatus_1?btn=1&reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				//페이지가 마지막 페이지에 도달했을 경우
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/myPage/Donghyo_orderHistory-deliveryStatus_1?btn=1&reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/myPage/Donghyo_orderHistory-deliveryStatus_1?btn=1&reqPage="+totalPage+"'>";//마지막 페이지로 가기
			pageNavi += "<span class='material-icons'>last_page</span>";//마지막 페이지로 가기 icon
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		OrderListData old = new OrderListData(orderList, pageNavi);
		return old;
	}//selectAllOrderList()

	
	public int totalCount() {
		int totalCount = myPageDao.totalCount();
		return totalCount;
	}//totalCount()
	
	
	public List selectOrderList(int start, int end) {
		List orderList = myPageDao.selectOneList(start, end);
		return orderList;
	}//selectOrderList(int start, int end)



}//MyPageService