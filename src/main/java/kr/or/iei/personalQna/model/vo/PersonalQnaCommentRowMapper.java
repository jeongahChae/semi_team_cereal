package kr.or.iei.personalQna.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonalQnaCommentRowMapper implements RowMapper<PersonalQnaComment>{

	@Override
	public PersonalQnaComment mapRow(ResultSet rs, int rowNum) throws SQLException {
		PersonalQnaComment pc = new PersonalQnaComment();
		pc.setPersonalCommentContent(rs.getString("personal_comment_content"));
		pc.setPersonalCommentDate(rs.getString("personal_comment_date"));
		pc.setPersonalCommentNo(rs.getInt("personal_comment_no"));
		pc.setPersonalCommentWriter(rs.getString("personal_comment_writer"));
		return pc;
	}

}
