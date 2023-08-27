package kr.or.iei.adminPage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.vo.MemberRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;

@Repository
public class AdminPageDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MemberRowMapper memberRowMapper;
	@Autowired
	private ProductRowMapper productRowMapper;
	
	//회원 목록
	public static List selectAllMember(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from member_tbl order by 1 desc)n) where rnum between ? and ?";
		List memberList = jdbc.query(query, memberRowMapper, start, end);s
		return memberList;
	}
	
	//전체 회원 수
	public int selectMemberTotalCount() {
		String query = "select count(*) as cnt from member_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//selectMemberTotalCount()

	//전체 등록 상품
	public List selectAllProduct(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from product order by 1 desc)n) where rnum between ? and ?";
		List productList = jdbc.query(query, productRowMapper, start, end);
		return productList;
	}

	//전체 등록 상품 수
	public int selectProductTotalCount() {
		String query = "select count(*) as cnt from product";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
}
