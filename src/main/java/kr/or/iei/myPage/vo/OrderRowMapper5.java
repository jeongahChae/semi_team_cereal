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
		o.setMemberNo(rs.getInt("member_no"));
		o.setOptionNo(rs.getInt("option_no"));
		o.setProductName(rs.getString("product_name"));
		return o;
	}

}
