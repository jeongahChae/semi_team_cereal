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
		oc.setOrderNo(rs.getLong("order_no"));
		oc.setOrderDate(rs.getString("order_date"));
		oc.setOrderStatus(rs.getInt("order_status_c"));
		oc.setReason(rs.getString("reason"));
		oc.setOptionName(rs.getString("option_name"));
		oc.setOptionAmount(rs.getInt("option_amount"));
		oc.setMemberName(rs.getString("member_name"));
		oc.setMemberAddr(rs.getString("member_addr"));
		oc.setProductName(rs.getString("product_name"));
		oc.setCount(rs.getInt("count"));
		oc.setOrderedPno(rs.getInt("ordered_pno"));
		return oc;
	}

}
