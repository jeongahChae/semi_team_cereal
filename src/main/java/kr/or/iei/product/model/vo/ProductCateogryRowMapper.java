package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductCateogryRowMapper implements RowMapper<ProductCategory> {

	@Override
	public ProductCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductCategory pc = new ProductCategory();
		
		pc.setCategoryName(rs.getString("category_name"));
		pc.setCategoryNo(rs.getInt("category_no"));
		pc.setCategoryRef(rs.getInt("category_ref"));
		return pc;
	}
	
}
