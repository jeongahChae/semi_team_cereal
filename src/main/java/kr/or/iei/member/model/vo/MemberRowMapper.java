package kr.or.iei.member.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class MemberRowMapper implements RowMapper<Member>{

	@Override
	public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
		Member m = new Member();
		m.setMemberNo(rs.getInt("member_no"));
		m.setMemberId(rs.getNString("member_id"));
		m.setMemberPw(rs.getString("member_pw"));
		m.setMemberName(rs.getNString("member_name"));
		m.setMemberPhone(rs.getString("member_phone"));
		m.setMemberEmail(rs.getString("member_email"));
		m.setMemberAddr(rs.getString("member_addr"));
		m.setMemberGender(rs.getString("member_gender"));
		m.setBirthDate(rs.getString("birth_date"));
		m.setEnrollDate(rs.getString("enroll_date"));
		m.setMemberLevel(rs.getInt("member_level"));		
		return m;
	}
	
}
