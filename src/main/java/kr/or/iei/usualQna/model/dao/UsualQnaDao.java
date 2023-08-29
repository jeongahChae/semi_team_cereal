package kr.or.iei.usualQna.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.usualQna.model.vo.UsualQna;
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

	public int insertUsualQna(UsualQna u) {
		String query = "insert into usual_qna values(usual_qna_seq.nextval,?,?,?,?)";
		Object[] params = {u.getQnaCategory(),u.getQnaTitle(),u.getQnaContent(),u.getQnaWriter()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int deleteUsualQna(int qnaNo) {
		String query = "delete from usual_qna where qna_no=?";
		Object[] params = {qnaNo};
		int result = jdbc.update(query,params);
		return result;
	}

	public UsualQna selectOneUsualQna(int qnaNo) {
		String query = "select * from usual_qna where qna_no=?";
		List list = jdbc.query(query, usualQnaRowMapper, qnaNo);
		return (UsualQna)list.get(0);
	}

	public int updateUsualQna(UsualQna u) {
		String query = "update usual_qna set qna_category=?, qna_title=?, qna_content=? where qna_no=?";
		Object[] params = {u.getQnaCategory(),u.getQnaTitle(),u.getQnaContent(),u.getQnaNo()};
		int result = jdbc.update(query,params);
		return result;
	}
}
