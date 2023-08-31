package kr.or.iei.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	public Member selectOneMember(String signId, String signPw) {
		Member m = memberDao.selectOneMember(signId, signPw);
		return m;

	}

	@Transactional
	public int insertMember(Member member) {
		int result = memberDao.insertMember(member);
		return result;
	}

	public Member selectOneMember(String checkId) {

		Member m = memberDao.selectOneMember(checkId);
		return m;

	}

	public Member selectOneMemberEmail(String checkEmail) {
		Member m = memberDao.selectOneMemberEmail(checkEmail);
		return m;
	}

	@Transactional
	public int updateMember(Member member) {
		int result = memberDao.updateMember(member);
		return result;
	}

	@Transactional
	public int deleteMember(String memberId) {
		int result = memberDao.deleteMember(memberId);
		return result;
	}

	public int selectOneMemberPw(String checkPw) {
		int result = memberDao.selectOneMemberPw(checkPw);
		return result;
	}

	
	public Member searchId(String checkName, String checkEmail) {
		Member m = memberDao.searchId(checkName, checkEmail);
		return m;
	}
	
}
