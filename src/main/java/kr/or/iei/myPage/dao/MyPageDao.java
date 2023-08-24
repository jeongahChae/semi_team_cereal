package kr.or.iei.myPage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.order.model.vo.OrderListData;
import kr.or.iei.order.model.vo.OrderRowMapper;

@Repository
public class MyPageDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private OrderRowMapper orderRowMapper;
	
	public List selectAllOrderList(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from order_tbl order by 1 desc)n) where rnum between ? and ?";
		List orderList = jdbc.query(query, orderRowMapper, start, end);
		return orderList;
	}//selectAllOrderList()

}//myPageDao
