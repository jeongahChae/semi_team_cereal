package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OdHistoryDelStatusRowMapper implements RowMapper<Order>{

	//member_tbl, option_tbl, order_tbl, ordered_products_tbl
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order o = new Order();
		o.setOrderNo(rs.getInt("order_no"));
		o.setOrderDate(rs.getString("order_date"));
		o.setTotalPrice(rs.getInt("total_price"));
		o.setTotalPoint(rs.getInt("total_point"));
		o.setCategoryNo(rs.getInt("category_no"));
		o.setOrderStatus(rs.getInt("order_status"));
		o.setMemberNo(rs.getInt("member_no"));
		o.setOrderedPno(rs.getInt("ordered_pno"));
		o.setProductFinalPrice(rs.getInt("product_final_price"));
		o.setCount(rs.getInt("count"));
		o.setOptionNo(rs.getInt("option_no"));
		o.setOptionName(rs.getString("option_name"));
		return o;
	}

}//OrderRowMapper
