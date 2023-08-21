package kr.or.iei.notice.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import kr.or.iei.notice.model.vo.NoticeRowMapper;

public class NoticeDao {
	@Autowired 
	private JdbcTemplate jdbc;
	@Autowired
	private NoticeRowMapper noticeRowMapper;

}
