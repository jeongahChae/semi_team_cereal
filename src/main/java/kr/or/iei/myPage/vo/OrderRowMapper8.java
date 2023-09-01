package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class OrderRowMapper8 implements RowMapper<Order>{

	@Override
	@Nullable
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order oc = new Order();
		oc.setOrderNo(rs.getLong("order_no"));
		oc.setProductNo(rs.getInt("product_no"));
		oc.setMemberName(rs.getString("member_name"));
		oc.setMemberAddr(rs.getString("member_addr"));
		oc.setOrderStatus(rs.getInt("order_Status"));
		oc.setMemberNo(rs.getInt("member_no"));
		return oc;
	}

}