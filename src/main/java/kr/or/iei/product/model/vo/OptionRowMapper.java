package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OptionRowMapper implements RowMapper<Option> {

	@Override
	public Option mapRow(ResultSet rs, int rowNum) throws SQLException {
		Option o = new Option();
		
		o.setOptionAmount(rs.getInt("option_amount"));
		o.setOptionName(rs.getString("option_name"));
		o.setOptionNo(rs.getInt("option_no"));
		o.setProductNo(rs.getInt("product_no"));
		return o;
	}

}
