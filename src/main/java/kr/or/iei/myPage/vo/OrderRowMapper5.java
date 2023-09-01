package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OrderRowMapper5 implements RowMapper<Order>{

	@Override
	@Nullable
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order o = new Order();
		o.setOrderNo(rs.getLong("order_no"));
		o.setProductNo(rs.getInt("product_no"));
		o.setCount(rs.getInt("count"));
		o.setTotalPrice(rs.getInt("total_price"));
		o.setOrderStatus(rs.getInt("order_status"));
		o.setMemberNo(rs.getInt("member_no"));
		return o;
	}

}
