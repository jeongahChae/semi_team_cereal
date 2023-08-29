package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelRowMapper implements RowMapper<OrderCancel>{

	@Override
	@Nullable
	public OrderCancel mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderCancel oc = new OrderCancel();
		oc.setCancelNo(rs.getInt("cancel_no"));
		oc.setOrderNo(rs.getInt("order_no"));
		oc.setProductName(rs.getString("product_name2"));
		oc.setOrderDate(rs.getString("order_date"));
		oc.setReason(rs.getString("reason"));
		oc.setOrderAmount(rs.getInt("order_amount"));
		oc.setMemberName(rs.getString("member_name"));
		oc.setMemberAddr(rs.getString("member_addr"));
		oc.setOrderStatus(rs.getInt("order_status"));
		return oc;
	}

}
