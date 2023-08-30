package kr.or.iei.personalQna.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonalQnaFileRowMapper implements RowMapper<PersonalQnaFile>{

	@Override
	public PersonalQnaFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		PersonalQnaFile file = new PersonalQnaFile();
		file.setFilename(rs.getString("filename"));
		file.setFileNo(rs.getInt("file_no"));
		file.setFilepath(rs.getString("filepath"));
		file.setQnaNo(rs.getInt("qna_no"));
		return file;
	}
	
}
