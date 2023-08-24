package kr.or.iei.myPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.myPage.dao.MyPageDao;

@Service
public class MyPageService {
	@Autowired
	private MyPageDao myPageDao;

	public List selectAllOrderList(int reqPage) {
		//게시판 페이지는 가장 마지막에 insert된 요소가 제일 상단에 위치하는 내림차순 정렬로 표기
		int numPerPage = 10; //한 페이지에 표시되는 게시물 수를 10개로 설정
		int end = reqPage * numPerPage; //끝나는 개수 숫자 /한 페이지에 표시되는 마지막 숫자 /reqPage가 1일 경우: 10
		int start = end - numPerPage +1; //시작하는 개수 숫자 /한 페이지에 표시되는 시작 숫자/reqPage가 1일 경우: 1
		List orderList = myPageDao.selectAllOrderList(start, end);
		
		
		return orderList;
	}//selectAllOrderList()


}
