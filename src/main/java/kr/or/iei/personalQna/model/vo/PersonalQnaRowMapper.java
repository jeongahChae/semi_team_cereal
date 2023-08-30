package kr.or.iei.personalQna.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonalQnaRowMapper implements RowMapper<PersonalQna>{

	@Override
	public PersonalQna mapRow(ResultSet rs, int rowNum) throws SQLException {
		PersonalQna p = new PersonalQna();
		p.setQnaContent(rs.getString("qna_content"));
		p.setQnaNo(rs.getInt("qna_no"));
		p.setQnaStatus(rs.getString("qna_status"));
		p.setQnaTitle(rs.getString("qna_title"));
		p.setQnaWriter(rs.getString("qna_writer"));
		p.setRegDate(rs.getString("reg_date"));
		return p;
	}
		
	
}
