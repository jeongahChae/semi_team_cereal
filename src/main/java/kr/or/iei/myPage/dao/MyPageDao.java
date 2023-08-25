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

	//전체 주문 내역 페이지 네비에 사용
	public int totalCount() {
		String query = "select count(*) from order_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//totalCount()

	
	//기간내 주문 내역
	public List selectDateOrderList(int start, int end, String startDate, String endDate) {
		System.out.println(start);
		System.out.println(end);
		System.out.println(startDate);
		System.out.println(endDate);
		String query = "select * from (select rownum as rnum, n. * from (select * from order_tbl where order_date between ? and ? order by 1 desc)n) where rnum between ? and ?";
		List orderList = jdbc.query(query, orderRowMapper, startDate, endDate, start, end);
		return orderList;
	}//selectDateOrderList(int start, int end, String startDate, String endDate)
	
	//기간내 주문 내역 페이지 네비에 사용
	public int selectOrderTotalCount(String startDate, String endDate) {
		String query = "select count(*) from order_tbl where order_date between ? and ?";
		int totalCount = jdbc.queryForObject(query, Integer.class, startDate, endDate);
		return totalCount;
	}//selectOrderTotalCount(String startDate, String endDate)
	
	
		
	//주문 내역 상세
	public List selectOrderHistory(int orderNo) {
		String query = "select * from order_tbl where order_no=?";
		List orderDetail = jdbc.query(query, orderRowMapper, orderNo);
		return orderDetail;
	}//selectOrderHistory(int orderNo)
	


	//전체 주문 수
	public int selectOrderTotalCount() {
		String query = "select count(*) as cnt from order_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//selectOrderTotalCount()

	
	//주문 내역 - 주문 번호로 조회
	public List selectNoOrderList(int orderNO) {
		String query = "select * from order_tbl where order_no=?";
		List orderDetail = jdbc.query(query, orderRowMapper, orderNO);
		return orderDetail;
	}//selectNoOrderList(int orderNO)







}//myPageDao
