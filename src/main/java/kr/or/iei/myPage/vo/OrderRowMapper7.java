package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderRowMapper7 implements RowMapper<Order>{

	//member_tbl, option_tbl, order_tbl, ordered_products_tbl
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order o = new Order();
		o.setOrderNo(rs.getLong("order_no"));
		o.setOrderDate(rs.getString("order_date"));
		o.setTotalPrice(rs.getInt("total_price"));
		o.setTotalPoint(rs.getInt("total_point"));
		o.setCategoryNo(rs.getInt("category_no"));
		o.setOrderStatus(rs.getInt("order_status"));
		return o;
	}
}				