package kr.or.iei.aboutUs.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class NewsRowMapper implements RowMapper<News>{

	@Override
	public News mapRow(ResultSet rs, int rowNum) throws SQLException {
		News n = new News();
		n.setNewsContent(rs.getString("news_content"));
		n.setNewsNo(rs.getInt("news_no"));
		n.setNewsTitle(rs.getString("news_title"));
		n.setPress(rs.getString("press"));
		n.setRegDate(rs.getString("reg_date"));
		return n;
	}

}
