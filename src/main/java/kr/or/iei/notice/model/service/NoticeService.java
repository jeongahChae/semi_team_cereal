package kr.or.iei.notice.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.notice.model.vo.NoticeFile;
import kr.or.iei.notice.model.dao.NoticeDao;
import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticeListData;


@Service
public class NoticeService {
	@Autowired
	private NoticeDao noticeDao;

	public NoticeListData selectNoticeList(int reqPage) {
		int numPerPage = 10;
		int end = reqPage * numPerPage;
		int start = end - numPerPage +1;
		List noticeList = noticeDao.selectNoticeList(start,end);
		//pageNavi 제작준비
		int totalCount = noticeDao.selectNoticeTotalCount();
		int totalPage = (int)Math.ceil(totalCount/(double)numPerPage);
		//pageNavi 사이즈(넘버 갯수 지정)
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		//pageNavi 제작
		String pageNavi = "<ul class='page-design circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/notice/list?reqPage="+1+"'>";//1번페이지로 
			pageNavi += "<span class='material-icons'>first_page</span>";//(|<)이렇게 생김
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/notice/list?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		//pageNavi 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/notice/list?reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/notice/list?reqPage="+pageNo+"'>";
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
			pageNavi += "<a class='page-item' href='/notice/list?reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/notice/list?reqPage="+totalPage+"'>";//마지막 페이지로 가기
			pageNavi += "<span class='material-icons'>last_page</span>";//마지막 페이지로 가기 icon
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		NoticeListData nld = new NoticeListData(noticeList,pageNavi);
		return nld;
	}

	public Notice selectOneNotice(int noticeNo) {
		Notice n = noticeDao.selectOneNotice(noticeNo);
		return n;
	}
	
	@Transactional
	public int insertNotice(Notice n, ArrayList<NoticeFile> fileList) {
		int result = noticeDao.insertNotice(n);
		if(fileList != null) {
			int noticeNo = noticeDao.getNoticeNo();
			for(NoticeFile file : fileList) {
				file.setNoticeNo(noticeNo);
				result += noticeDao.insertNoticeFile(file);
			}
		}
		return result;
	}
	@Transactional
	public List deleteNotice(int noticeNo) {
		List list = noticeDao.selectNoticeFile(noticeNo);
		int result = noticeDao.deleteNotice(noticeNo);
		if(result == 0) {
			return null;
		}
		return list;
	}

	public Notice getNotice(int noticeNo) {
		Notice n = noticeDao.selectOneNotice(noticeNo);
		List fileList = noticeDao.selectNoticeFile(noticeNo);
		n.setFileList(fileList);
		return n;
	}

	@Transactional
	public List updateNotice(Notice n, ArrayList<NoticeFile> fileList, int[] delFileNo) {
		int result = noticeDao.updateNotice(n);
		List delFileList = new ArrayList<NoticeFile>();
		if(result>0) {
			if(delFileNo != null) {
				for(int fileNo : delFileNo) {
					NoticeFile noticeFile = noticeDao.selectOneFile(fileNo);
					delFileList.add(noticeFile);
					result += noticeDao.deleteFile(fileNo);
				}
			}
			if(fileList != null) {
				for(NoticeFile file : fileList) {
					result += noticeDao.insertNoticeFile(file);
				}
			}
		}
		int updateTotal = 1;
		updateTotal += delFileNo==null?0:delFileNo.length;
		updateTotal += fileList==null?0:fileList.size();
		if(result == updateTotal) {
			return delFileList;
		}else {
			return null;
		}
	}
	
}


















