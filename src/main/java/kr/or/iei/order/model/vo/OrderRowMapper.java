package kr.or.iei.order.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderRowMapper implements RowMapper<Order>{

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order o = new Order();
		o.setOrderNo(rs.getInt("order_no"));
		o.setMemberNo(rs.getInt("member_no"));
		o.setProductName(rs.getString("product_name2"));
		o.setOrderAmount(rs.getInt("order_amount"));
		o.setMemberAddr(rs.getString("member_addr"));
		o.setOrderStatus(rs.getString("order_status"));
		o.setOrderDate(rs.getString("order_date"));
		o.setTotalPrice(rs.getInt("total_price"));
		o.setTotalPoint(rs.getInt("total_point"));
		return o;
	}

}//OrderRowMapper
