package kr.or.iei.personalQna.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.member.model.vo.Member;
import kr.or.iei.personalQna.model.dao.PersonalQnaDao;
import kr.or.iei.personalQna.model.vo.PersonalQna;
import kr.or.iei.personalQna.model.vo.PersonalQnaFile;
import kr.or.iei.personalQna.model.vo.PersonalQnaListData;

@Service
public class PersonalQnaService {
	@Autowired
	private PersonalQnaDao personalQnaDao;

	public PersonalQnaListData selectPersonalQnaList(int qnaNo,int reqPage, Member m) {
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage +1;
		//commentList 삭제
		List personalQnaList = null; 
		PersonalQna p = personalQnaDao.selectOnePersonalQna(qnaNo);
		List commentList = personalQnaDao.selectCommentList(qnaNo);
		int totalCount = 0;
		if(m.getMemberLevel() == 1) {
			//관리자인경우 이전과 동일.
			personalQnaList = personalQnaDao.selectPersonalQnaList(start,end);
			//pageNavi 제작준비
			totalCount = personalQnaDao.selectPersonalQnaTotalCount();
		}else {
			//관리자가 아닌 경우 member의 id값을 필요로한다.(query문 변경)
			personalQnaList = personalQnaDao.selectPersonalQnaMemberList(m,start,end);
			//pageNavi 제작준비
			totalCount = personalQnaDao.selectPersonalQnaMemberTotalCount(m);
		}
		
		
		int totalPage = (int)Math.ceil(totalCount/(double)numPerPage);
		//pageNavi 사이즈(넘버 갯수 지정)
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		//pageNavi 제작
		String pageNavi = "<ul class='page-design circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/personalQna/list?reqPage="+1+"'>";//1번페이지로 
			pageNavi += "<span class='material-icons'>first_page</span>";//(|<)이렇게 생김
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/personalQna/list?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		//pageNavi 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/personalQna/list?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/personalQna/list?reqPage="+pageNo+"'>";
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
			pageNavi += "<a class='page-item' href='/personalQna/list?reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/personalQna/list?reqPage="+totalPage+"'>";//마지막 페이지로 가기
			pageNavi += "<span class='material-icons'>last_page</span>";//마지막 페이지로 가기 icon
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		PersonalQnaListData nld = new PersonalQnaListData(personalQnaList,pageNavi);
		return nld;
	}

	@Transactional
	public int insertPersonalQna(PersonalQna p, ArrayList<PersonalQnaFile> fileList) {
		int result = personalQnaDao.insertPersonalQna(p);
		if(fileList != null) {
			int personalQnaNo = personalQnaDao.getPersonalQnaNo();
			for(PersonalQnaFile file : fileList) {
				file.setQnaNo(personalQnaNo);
				result += personalQnaDao.insertPersonalQnaFile(file);
			}
		}
		return result;
	}

	public PersonalQna getPersonalQna(int qnaNo) {
		PersonalQna p = personalQnaDao.selectOnePersonalQna(qnaNo);
		return p;
	}

	@Transactional
	public int updatePersonalQna(PersonalQna p) {
		int result = personalQnaDao.updatePersonalQna(p);
		return result;
	}

	public int deletePersonalQna(int qnaNo) {
		int result = personalQnaDao.deletePersonalQna(qnaNo);
		return result;
	}
}
