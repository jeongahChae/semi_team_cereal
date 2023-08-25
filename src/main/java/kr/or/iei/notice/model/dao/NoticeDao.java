package kr.or.iei.notice.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.notice.model.vo.NoticeFile;
import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticeFileRowMapper;
import kr.or.iei.notice.model.vo.NoticeRowMapper;

@Repository
public class NoticeDao {
	@Autowired 
	private JdbcTemplate jdbc;
	@Autowired
	private NoticeRowMapper noticeRowMapper;
	@Autowired
	private NoticeFileRowMapper noticeFileRowMapper;
	

	public List selectNoticeList(int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from notice order by 1 desc)n) where rnum between ? and ?";
		List list = jdbc.query(query, noticeRowMapper, start, end);
		return list;
	}


	public int selectNoticeTotalCount() {
		String query = "select count(*) as cnt from notice";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}


	public Notice selectOneNotice(int noticeNo) {
		String query = "select * from notice where notice_no=?";
		List list = jdbc.query(query, noticeRowMapper, noticeNo);
		return (Notice)list.get(0);
	}


	public int insertNotice(Notice n) {
		String query = "insert into notice values(notice_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),?)";
		Object[] params = {n.getNoticeTitle(),n.getNoticeContent(),n.getNoticeWriter()};
		int result = jdbc.update(query, params);
		return result;
	}


	public int getNoticeNo() {
		String query = "select max(notice_no) from notice";
		int noticeNo = jdbc.queryForObject(query, Integer.class);
		return noticeNo;
	}


	public int insertNoticeFile(NoticeFile file) {
		String query = "insert into notice_file values(notice_file_seq.nextval,?,?,?)";
		Object[] params = {file.getNoticeNo(),file.getFilename(),file.getFilepath()};
		int result = jdbc.update(query, params);
		return result;
	}


	public List selectNoticeFile(int noticeNo) {
		String query = "select * from notice_file where notice_no=?";
		List list = jdbc.query(query, noticeFileRowMapper,noticeNo);
		return list;
	}


	public int deleteNotice(int noticeNo) {
		String query = "delete from notice where notice_no=?";
		Object[] params = {noticeNo};
		int result = jdbc.update(query,params);
		return result;
	}


	public int updateNotice(Notice n) {
		String query = "update notice set notice_title=?, notice_content=? where notice_no=?";
		Object[] params = {n.getNoticeTitle(), n.getNoticeContent(), n.getNoticeNo()};
		int result = jdbc.update(query,params);
		return result;
	}


	public NoticeFile selectOneFile(int fileNo) {
		String query = "select * from notice_file where file_no=?";
		List list = jdbc.query(query, noticeFileRowMapper, fileNo);
		return (NoticeFile)list.get(0);
	}


	public int deleteFile(int fileNo) {
		String query = "delete from notice_file where file_no=?";
		Object[] params = {fileNo};
		int result = jdbc.update(query,params);
		return result;
	}

}


















