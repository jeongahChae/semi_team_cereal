package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product p = new Product();
		
		p.setProductBrand(rs.getString("product_brand"));
		p.setProductCategory(rs.getInt("product_category"));
		p.setProductContent(rs.getString("product_content"));
		p.setProductDelfree(rs.getString("product_delfree"));
		p.setProductFinalPrice(rs.getInt("product_final_price"));
		p.setProductName(rs.getString("product_name"));
		p.setProductNo(rs.getInt("product_no"));
		p.setProductPercent(rs.getInt("product_percent"));
		p.setProductPoint(rs.getInt("product_point"));
		p.setProductPrice(rs.getInt("product_price"));
		return p;
	} 
}
