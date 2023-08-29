package kr.or.iei.adminPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OrderAdminRowMapper implements RowMapper<OrderAdmin>{

	@Override
	@Nullable
	public OrderAdmin mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderAdmin oa = new OrderAdmin();
		oa.setOrderNo(rs.getInt("order_no"));
		oa.setMemberNo(rs.getInt("member_no"));
		oa.setProductName(rs.getString("product_name2"));
		oa.setOrderAmount(rs.getInt("order_amount"));
		oa.setMemberAddr(rs.getString("member_addr"));
		oa.setOrderStatus(rs.getInt("order_status"));
		oa.setOrderDate(rs.getString("order_date"));
		oa.setTotalPrice(rs.getInt("total_price"));
		oa.setTotalPoint(rs.getInt("total_point"));
		oa.setMemberName(rs.getString("member_name"));
		return oa;
	}

}
