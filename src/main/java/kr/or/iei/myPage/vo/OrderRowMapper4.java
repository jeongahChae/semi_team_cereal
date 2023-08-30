package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OrderRowMapper4 implements RowMapper<Order>{

	@Override
	@Nullable
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order o = new Order();
		//order_no, product_name, count
		o.setOrderNo(rs.getInt("order_no"));
		o.setProductName(rs.getString("product_name"));
		o.setCount(rs.getInt("count"));
		return o;
	}

}