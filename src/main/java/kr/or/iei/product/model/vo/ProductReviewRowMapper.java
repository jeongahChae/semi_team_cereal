package kr.or.iei.product.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewRowMapper implements RowMapper<ProductReview>{

	@Override
	@Nullable
	public ProductReview mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProductReview pr = new ProductReview();
		pr.setMemberId(rs.getString("member_id"));
		pr.setOrderedPno(rs.getInt("ordered_pno"));
		pr.setReviewContent(rs.getString("review_content"));
		pr.setReviewDate(rs.getString("review_date"));
		pr.setReviewImg(rs.getString("review_img"));
		pr.setReviewNo(rs.getInt("review_no"));
		pr.setReviewStatus(rs.getInt("review_status"));
		return pr;
	}

}
