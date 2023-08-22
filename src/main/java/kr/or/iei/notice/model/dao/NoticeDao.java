package kr.or.iei.notice.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticeRowMapper;

@Repository
public class NoticeDao {
	@Autowired 
	private JdbcTemplate jdbc;
	@Autowired
	private NoticeRowMapper noticeRowMapper;
	

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

}
