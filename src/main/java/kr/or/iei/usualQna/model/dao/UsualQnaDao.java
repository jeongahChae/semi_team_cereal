package kr.or.iei.usualQna.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.usualQna.model.vo.UsualQnaRowMapper;

@Repository
public class UsualQnaDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private UsualQnaRowMapper usualQnaRowMapper;
	
	public List selectUsualQnaList(int start, int end) {
		String query = "select * from (select rownum as rnum, u.* from (select * from usual_qna order by 1 desc)u) where rnum between ? and ?";
		List list = jdbc.query(query, usualQnaRowMapper, start, end);
		return list;
	}

	public int selectUsualQnaTotalCount() {
		String query = "select count(*) as cnt from usual_qna";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
}
