package kr.or.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		
		String query = "insert into member_tbl values(member_tbl_seq.nextval,?,?,?,?,?,?,?,?,to_char(sysdate,'yyyy-mm-dd'),2,?)";
		Object[] params = {member.getMemberId(),member.getMemberPw(),member.getMemberName(),member.getMemberPhone(),member.getMemberEmail(),member.getMemberAddr(),member.getMemberGender(),member.getBirthDate(),member.getDetail()};
		int result = jdbc.update(query,params);		
		return result;
	}

	public Member selectOneMember(String checkId) {
		String query = "select * from member_tbl where member_id = ?";
		List list = jdbc.query(query, memberRowMapper,checkId);
		System.out.println(list.isEmpty());
		if(list.isEmpty()) {
			return null;
		}
		return (Member)list.get(0);
		
	}

	public Member selectOneMemberEmail(String checkEmail) {
		String query = "select * from member_tbl where member_email = ?";
		List list = jdbc.query(query, memberRowMapper,checkEmail);
		System.out.println(list.isEmpty());
		if(list.isEmpty()) {
			return null;
		}
		return (Member)list.get(0);
	}

	public int updateMember(Member member) {
		String query = "update member_tbl set member_pw = ?, member_phone = ?, member_addr=?, birth_date=? where member_id = ?";
		Object[] params = {member.getMemberPwnew(),member.getMemberPhone(),member.getMemberAddr(),member.getBirthDate(),member.getMemberId()};
		int result = jdbc.update(query, params);
		return result;
	}

	@Transactional
	public int deleteMember(String memberId) {
		String query = "delete from member_tbl where member_id = ?";
		Object[] params = {memberId};
		int result = jdbc.update(query, params);
		return result;
		
	}

}
