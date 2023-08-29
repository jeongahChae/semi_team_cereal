package kr.or.iei.order.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.order.vo.CartRowMapper;

@Repository
public class OrderDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private CartRowMapper cartRowMapper;

	public List selectCartInfoList(int memberNo) {
		String query = "select * from" + 
				"(select * from (select option_no, sum(count)count from(select * from cart where member_no = ?)c group by option_no)" + 
				"left join option_tbl using (option_no))left join product using(product_no)";
		List cartInfoList = jdbc.query(query, cartRowMapper,memberNo);
		return cartInfoList;
	}
}
