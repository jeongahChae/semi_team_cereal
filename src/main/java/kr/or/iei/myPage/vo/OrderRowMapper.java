package kr.or.iei.myPage.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderRowMapper implements RowMapper<Order>{

	//member_tbl, option_tbl, order_tbl, ordered_products_tbl
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order o = new Order();
		o.setOrderNo(rs.getInt("order_no"));
		o.setOrderDate(rs.getString("order_date"));
		o.setTotalPrice(rs.getInt("total_price"));
		o.setTotalPoint(rs.getInt("total_point"));
		o.setCategoryNo(rs.getInt("category_no"));
		o.setOrderStatus(rs.getInt("order_status"));
		o.setOrderedPno(rs.getInt("ordered_pno"));
		o.setCount(rs.getInt("count"));
		o.setOptionNo(rs.getInt("option_no"));
		o.setOptionName(rs.getString("option_name"));
		o.setOptionAmount(rs.getInt("option_amount"));
		
		
		o.setMemberNo(rs.getInt("member_no"));
		o.setMemberId(rs.getNString("member_id"));
		o.setMemberPw(rs.getString("member_pw"));
		o.setMemberName(rs.getNString("member_name"));
		o.setMemberPhone(rs.getString("member_phone"));
		o.setMemberEmail(rs.getString("member_email"));
		o.setMemberAddr(rs.getString("member_addr"));
		o.setMemberGender(rs.getString("member_gender"));
		o.setBirthDate(rs.getString("birth_date"));
		o.setEnrollDate(rs.getString("enroll_date"));
		o.setMemberLevel(rs.getInt("member_level"));	
		o.setDetail(rs.getString("detail"));
		
		o.setProductBrand(rs.getString("product_brand"));
		o.setProductCategory(rs.getInt("product_category"));
		o.setProductContent(rs.getString("product_content"));
		o.setProductDelfree(rs.getString("product_delfree"));
		o.setProductFinalPrice(rs.getInt("product_final_price"));
		o.setProductName(rs.getString("product_name"));
		o.setProductNo(rs.getInt("product_no"));
		o.setProductPercent(rs.getInt("product_percent"));
		o.setProductPoint(rs.getInt("product_point"));
		o.setProductPrice(rs.getInt("product_price"));
//		o.setProductFinalPrice1(rs.getInt("product_final_price_1"));
		return o;
	}

}//OrderRowMapper
