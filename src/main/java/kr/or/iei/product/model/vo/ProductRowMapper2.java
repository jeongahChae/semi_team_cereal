package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ProductRowMapper2 implements RowMapper<Product>{

	@Override
	@Nullable
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product p = new Product();
		p.setProductNo(rs.getInt("product_no"));
		p.setProductName(rs.getString("product_name"));
		p.setOptionName(rs.getNString("option_name"));
		p.setOptionAmount(rs.getInt("option_amount"));
		return p;
	}

}
