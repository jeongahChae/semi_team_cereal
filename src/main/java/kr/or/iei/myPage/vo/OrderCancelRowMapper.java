package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

public class OrderCancelRowMapper implements RowMapper<OrderCancel>{

	@Override
	@Nullable
	public OrderCancel mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderCancel oc = new OrderCancel();
		oc.setCancelNo(rs.getInt("cancel_no"));
		oc.setOrderDate(rs.getString("order_date"));
		oc.setOrderNo(rs.getInt("order_no"));
		oc.setProductName(rs.getString("product_name"));
		oc.setProductNo(rs.getInt("product_no"));
		oc.setReason(rs.getString("reason"));
		oc.setSelectTap(rs.getString("select_tap"));
		return oc;
	}

}
