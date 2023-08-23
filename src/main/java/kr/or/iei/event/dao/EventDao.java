package kr.or.iei.event.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.event.vo.EventRowMapper;
import kr.or.iei.event.vo.WinnerBoardRowMapper;


@Repository
public class EventDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private EventRowMapper eventRowMapper;
	@Autowired
	private WinnerBoardRowMapper winnerBoardRowMapper;
}
