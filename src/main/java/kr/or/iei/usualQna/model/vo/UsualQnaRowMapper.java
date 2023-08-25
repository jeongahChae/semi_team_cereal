package kr.or.iei.usualQna.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class UsualQnaRowMapper implements RowMapper<UsualQna> {

	@Override
	public UsualQna mapRow(ResultSet rs, int rowNum) throws SQLException {
		UsualQna u = new UsualQna();
		u.setQnaCategory(rs.getString("qna_category"));
		u.setQnaContent(rs.getString("qna_content"));
		u.setQnaNo(rs.getInt("qna_no"));
		u.setQnaTitle(rs.getString("qna_title"));
		u.setQnaWriter(rs.getString("qna_writer"));
		return u;
	}
	
}
