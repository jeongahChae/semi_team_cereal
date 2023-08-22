package kr.or.iei.aboutUs.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class StoreRowMapper implements RowMapper<Store> {

	@Override
	public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
		Store s = new Store();
		s.setStoreAddr(rs.getString("store_addr"));
		s.setStoreImage(rs.getString("store_image"));
		s.setStoreName(rs.getString("store_name"));
		s.setStoreNo(rs.getInt("store_no"));
		s.setStorePhone(rs.getString("store_phone"));
		return s;
	}

}
