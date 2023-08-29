package kr.or.iei.adminPage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.adminPage.vo.OrderAdminRowMapper;
import kr.or.iei.member.model.vo.MemberRowMapper;
import kr.or.iei.myPage.vo.OrderRowMapper;
import kr.or.iei.myPage.vo.OrderRowMapper2;
import kr.or.iei.product.model.vo.ProductRowMapper;

@Repository
public class AdminPageDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MemberRowMapper memberRowMapper;
	@Autowired
	private ProductRowMapper productRowMapper;
	@Autowired
	private OrderRowMapper orderRowMapper;
	@Autowired
	private OrderRowMapper2 orderRowMapper2;
	
	//회원 목록
	public List selectAllMember(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from member_tbl order by 1 desc)n) where rnum between ? and ?";
		List memberList = jdbc.query(query, memberRowMapper, start, end);
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
	
	
	//주문 현황 관리 - 전체 주문 조회
	public List selectAllOrderListAdmin(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from(select order_no, product_name2, member_name, member_addr, order_status from order_tbl union select order_no, product_name2, member_name, member_addr, order_status from ORDER_CANCEL) order by 1 desc)n) where rnum between ? and ?";
		List orderList = jdbc.query(query, orderRowMapper2, start, end);
		return orderList;
	}
	//주문 현황 관리 - 단일 조회
	public List selectOrderAdmin(int orderNO) {
		String query = "select * from order_tbl where order_no=?";
		List order = jdbc.query(query, orderRowMapper, orderNO);
		return order;
	}
	//주문 현황 관리 - 업데이트
	public int orderUpdate(int orderStatus, int orderNo) {
		String query = "update order_tbl set order_status=? where order_no=?";
		Object[] params = {orderStatus, orderNo};
		int result = jdbc.update(query, params);
		return result;
	}
	
	
	//전체 주문 수 
	public int selectOrderTotalCount() {
		String query = "select count(*) from (select order_no, product_name2, member_name, member_addr, order_status from order_tbl union select order_no, product_name2, member_name, member_addr, order_status from ORDER_CANCEL)";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
	

	//총 매출액(원)
	public String selectTotalSales() {
		String query = "select to_char(sum(total_price),'999,999,999') from order_tbl where order_status ='결제 완료'";
		String totalSales = jdbc.queryForObject(query, String.class);
		return totalSales;
	}

	public String selectSalesCount() {
		String query = "select to_char(count(total_price),'999,999,999') from order_tbl where order_status ='결제 완료'";
		String salesCount = jdbc.queryForObject(query, String.class);
		return salesCount;
	}

	public String selectAvgSales() {
		String query = "select to_char(avg(total_price),'999,999,999') from order_tbl where order_status ='결제 완료'";
		String avgSales = jdbc.queryForObject(query, String.class);
		return avgSales;
	}



	
}
