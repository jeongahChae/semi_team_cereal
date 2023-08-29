package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OrderRowMapper2 implements RowMapper<Order>{

	@Override
	@Nullable
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order o = new Order();
		o.setOrderNo(rs.getInt("order_no"));
		o.setProductName(rs.getString("product_name2"));
		o.setMemberName(rs.getString("member_name"));
		o.setMemberAddr(rs.getString("member_addr"));
		o.setOrderStatus(rs.getInt("order_status"));
		return o;
	}

}
