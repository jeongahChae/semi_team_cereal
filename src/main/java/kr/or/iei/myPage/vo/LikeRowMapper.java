package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class LikeRowMapper implements RowMapper<LikeList>{

	@Override
	public LikeList mapRow(ResultSet rs, int rowNum) throws SQLException {
		LikeList likeList = new LikeList();
		likeList.setLikeNo(rs.getInt("like_no"));
		likeList.setProductName(rs.getString("product_name"));
		likeList.setOptionNo(rs.getInt("option_no"));
		likeList.setProductPrice(rs.getInt("product_price"));
		return likeList;
	}

}
