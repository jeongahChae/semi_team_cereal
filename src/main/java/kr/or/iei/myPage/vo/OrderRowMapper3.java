package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OrderRowMapper3 implements RowMapper<Order>{

	@Override
	@Nullable
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order o = new Order();
		//product_name, total_price, count, order_status
		o.setProductName(rs.getString("product_name"));
		o.setTotalPrice(rs.getInt("total_price"));
		o.setCount(rs.getInt("count"));
		o.setOrderStatus(rs.getInt("order_status"));
		return o;
	}

}
