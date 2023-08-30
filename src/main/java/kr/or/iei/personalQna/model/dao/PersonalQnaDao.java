package kr.or.iei.personalQna.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.vo.Member;
import kr.or.iei.personalQna.model.vo.PersonalQna;
import kr.or.iei.personalQna.model.vo.PersonalQnaCommentRowMapper;
import kr.or.iei.personalQna.model.vo.PersonalQnaFile;
import kr.or.iei.personalQna.model.vo.PersonalQnaFileRowMapper;
import kr.or.iei.personalQna.model.vo.PersonalQnaRowMapper;

@Repository
public class PersonalQnaDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private PersonalQnaRowMapper personalQnaRowMapper;
	@Autowired
	private PersonalQnaFileRowMapper personalQnaFileRowMapper;
	@Autowired
	private PersonalQnaCommentRowMapper personalQnaCommentRowMapper;
	
	public List selectPersonalQnaList(int start, int end) {
		String query = "select * from (select rownum as rnum, p.* from (select * from personal_qna order by 1 desc)p) where rnum between ? and ?";
		List list = jdbc.query(query, personalQnaRowMapper, start, end);
		return list;
	}

	public int selectPersonalQnaTotalCount() {
		String query = "select count(*) as cnt from personal_qna";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public int insertPersonalQna(PersonalQna p) {
		String query = "insert into personal_qna values(personal_qna_seq.nextval,?,?,to_char(sysdate,'yyyy-mm-dd'),'답변대기',?)";
		Object[] params = {p.getQnaTitle(),p.getQnaContent(),p.getQnaWriter()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int getPersonalQnaNo() {
		String query = "select max(qna_no) from personal_qna";
		int personalQnaNo = jdbc.queryForObject(query, Integer.class);
		return personalQnaNo;
	}

	public int insertPersonalQnaFile(PersonalQnaFile file) {
		String query = "insert into personal_qna_file values(personal_qna_file_seq.nextval,?,?,?)";
		Object[] params = {file.getQnaNo(),file.getFilename(),file.getFilepath()};
		int result = jdbc.update(query, params);
		return result;
	}

	public PersonalQna selectOnePersonalQna(int qnaNo) {
		String query = "select * from personal_qna where qna_no=?";
		List list = jdbc.query(query, personalQnaRowMapper, qnaNo);
		return (PersonalQna)list.get(0);
	}

	public List selectPersonalQnaMemberList(Member m, int start, int end) {
		String query = "select * from (select rownum as rnum, p.* from (select * from personal_qna where personal_qna.qna_writer=? order by 1 desc)p) where rnum between ? and ? ";
		List list = jdbc.query(query, personalQnaRowMapper,m.getMemberId(), start, end);
		return list;
	}

	public int selectPersonalQnaMemberTotalCount(Member m) {
		String query = "select count(*) as cnt from personal_qna where qna_writer=?";
		int totalCount = jdbc.queryForObject(query, Integer.class, m.getMemberId());
		return totalCount;
	}

	public int updatePersonalQna(PersonalQna p) {
		String query = "update personal_qna set qna_title=?, qna_content=? where qna_no=?";
		Object[] params = {p.getQnaTitle(),p.getQnaContent(),p.getQnaNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int deletePersonalQna(int qnaNo) {
		String query = "delete from personal_qna where qna_no=?";
		Object[] params = {qnaNo};
		int result = jdbc.update(query,params);
		return result;
	}

	
	
	
	
}
