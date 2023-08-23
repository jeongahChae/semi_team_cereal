package kr.or.iei.event.dao;

import java.util.List;

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
	
	public List selectWinnerBoardList(int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from winner_board order by 1 desc)n)where rnum between ? and ?";
		List list = jdbc.query(query, winnerBoardRowMapper, start, end);
		return list;
	}
	
	public int selectWinnerBoardListTotalCount() {
		String query = "select count(*) from winner_board";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
}
