package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailFileRowMapper implements RowMapper<ProductDetailFile> {

	@Override
	public ProductDetailFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductDetailFile dfile = new ProductDetailFile();
		dfile.setFilename(rs.getString("filename"));
		dfile.setFileNo(rs.getInt("file_no"));
		dfile.setFilepath(rs.getString("filepath"));
		dfile.setProductNo(rs.getInt("product_no"));
		return dfile;
	}

}
