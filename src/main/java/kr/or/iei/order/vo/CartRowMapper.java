package kr.or.iei.order.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class CartRowMapper implements RowMapper{

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cart c = new Cart();
		//c.setCartNo(rs.getInt("cart_no"));
		c.setCount(rs.getInt("count"));
		//c.setMemberNo(rs.getInt("member_no"));
		c.setOptionNo(rs.getInt("option_no"));
		c.setPoint(rs.getInt("product_point"));
		c.setProductFinalPrice(rs.getInt("product_final_price"));
		c.setProductCategory(rs.getInt("product_category"));
		c.setProductDelfree(rs.getString("product_delfree"));
		c.setProductName(rs.getString("product_name"));
		c.setProductPercent(rs.getInt("product_percent"));
		c.setProductPoint(rs.getInt("product_point"));
		c.setProductPrice(rs.getInt("product_price"));
		c.setProductNo(rs.getInt("product_no"));
		c.setOptionAmount(rs.getInt("option_amount"));
		c.setOptionName(rs.getString("option_name"));
		return c;
	}

}
