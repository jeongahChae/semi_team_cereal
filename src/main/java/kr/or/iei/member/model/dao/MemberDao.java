package kr.or.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.MemberRowMapper;

@Repository
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MemberRowMapper memberRowMapper;
	
	public Member selectOneMember(String signId, String signPw) {
		String query ="select * from member_tbl where member_id=? and member_pw=?";
		List list = jdbc.query(query, memberRowMapper, signId, signPw);
		if(list.isEmpty()) {
			return null;
		}
		return(Member)list.get(0);
		
	}

	public int insertMember(Member member) {
		
		String query = "insert into member_tbl values(member_tbl_seq.nextval,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),2)";
		Object[] params = {member.getMemberId(),member.getMemberPw(),member.getMemberName(),member.getMemberPhone(),member.getMemberEmail(),member.getMemberAddr(),member.getMemberGender(),member.getBirthDate()};
		int result = jdbc.update(query,params);		
		return result;
	}

	public Member selectOneMember(String checkId) {
		String query = "select * from member_tbl where member_id = ?";
		List list = jdbc.query(query, memberRowMapper,checkId);
		if(list.isEmpty()) {
			return null;
		}
		return (Member)list.get(0);
		
	}

}
