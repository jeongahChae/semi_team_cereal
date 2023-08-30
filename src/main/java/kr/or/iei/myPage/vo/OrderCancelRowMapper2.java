package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OrderCancelRowMapper2 implements RowMapper<OrderCancel>{

	@Override
	@Nullable
	public OrderCancel mapRow(ResultSet rs, int rowNum) throws SQLException {
		OrderCancel oc = new OrderCancel();
		oc.setOrderNo(rs.getInt("order_no"));
		oc.setCount(rs.getInt("count"));
		oc.setOrderStatus(rs.getInt("order_Status_c"));
		oc.setOptionName(rs.getString("option_name"));
		oc.setProductName(rs.getString("product_name"));
		return oc;
	}

}