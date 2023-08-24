package kr.or.iei.myPage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.order.model.vo.OrderRowMapper;

@Repository
public class MyPageDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private OrderRowMapper orderRowMapper;
	
	//전체 주문 내역
	public List selectAllOrderList(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from order_tbl order by 1 desc)n) where rnum between ? and ?";
		List orderList = jdbc.query(query, orderRowMapper, start, end);
		return orderList;
	}//selectAllOrderList()

	//페이지 네비에 사용
	public int totalCount() {
		String query = "select count(*) from order_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//totalCount()
	
	//주문 내역 상세
	public List selectOrderHistory(int orderNo) {
		String query = "selct * from order_tbl where order_no=?";
		List orderDetail = jdbc.query(query, orderRowMapper, orderNo);
		return orderDetail;
	}
	
	public List selectOneList(int start, int end) {
		String query = "select * from (select rownum as rnum, p.* from (select * from order_tbl order by 1 desc)p) where rnum between ? and ?"; 
		List orderList = jdbc.query(query, orderRowMapper, start, end);
		return orderList;
	}//selectOneList(int start, int end)


	public int selectOrderTotalCount() {
		String query = "select count(*) as cnt from order_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//selectOrderTotalCount()




}//myPageDao
