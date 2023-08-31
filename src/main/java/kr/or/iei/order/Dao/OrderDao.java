package kr.or.iei.order.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.order.vo.Cart;
import kr.or.iei.order.vo.CartRowMapper;

@Repository
public class OrderDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private CartRowMapper cartRowMapper;

	public List selectCartInfoList(int memberNo) {
		String query = "select * from(select* from(select * from cart) where member_no = ? ) left join option_tbl using (option_no)" + 
				"left join product using(product_no)";
		List cartInfoList = jdbc.query(query, cartRowMapper,memberNo);
		return cartInfoList;
	}

	public int updateCartCount(int cartNo, int count2) {
		String query = "update cart set count=count+? where cart_no = ?";
		int result = jdbc.update(query, count2, cartNo);
		return result;
	}

	public int deleteCart(int cartNo2) {
		String query = "delete from cart where cart_no = ?";
		int result = jdbc.update(query, cartNo2);
		return result;
	}

	public int updateOption(int cartNo, int newOptionNo, int newCount) {
		String query = "update cart set option_no = ?, count = ? where cart_no=?";
		Object[] params = {newOptionNo, newCount, cartNo};
		int result= jdbc.update(query, params);
		return result;
	}

	public int deleteCart(int memberNo, int cartNo) {
		String query = "delete from cart where cart_no = ? and member_no = ?";
		int result = jdbc.update(query, cartNo, memberNo);
		return result;
	}

	public Cart selectCartToOrder(int cartNo) {
		String query = "select * from(select* from(select * from cart where cart_no = ?))"+
				"left join option_tbl using (option_no) left join product using(product_no)";
		Object[] params = {cartNo};
		List list = jdbc.query(query, cartRowMapper, params);
		return (Cart)list.get(0);
	}

	public int createOrder(int memberNo, int price) {
		String query = "insert into order_tbl values(to_char(sysdate,'yyyymmddhhmi')+order_seq.nextval, ?, sysdate, ?, null, 2)";
		Object[] params = {memberNo, price};
		int result = jdbc.update(query, params);
		return result;
	}

	public Cart selectCartInfo(int cartNo) {
		String query = "select* from (select * from(select * from cart where cart_no = ?) left join option_tbl using(option_no))\r\n" + 
				"left join product using(product_no)";
		List cartInfoList = jdbc.query(query, cartRowMapper, cartNo);
		return (Cart)cartInfoList.get(0);
	}

	public long selectOrderNo(int memberNo) {
		String query = "select max(order_no) from order_tbl where member_no=?";
		long orderNo = jdbc.queryForObject(query, Long.class, memberNo);
		return orderNo;
	}

	public int createOrderedProduct(Cart c, long orderNo) {
		String query = "insert into ORDERED_PRODUCTS_TBL values (ORDERED_PRODUCTS_TBL_SEQ.NEXTVAL,?,?,?,?)";
		Object[] params = {c.getOptionNo(),orderNo,c.getProductFinalPrice(),c.getCount()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int insertPoint(Cart c) {
		String query = "insert into point_tbl values(p_seq.nextval, ?,?,0)";
		int result = jdbc.update(query, c.getMemberNo(), c.getPoint());
		return result;
	}

	public int usePoint(int memberNo, int usePoint) {
		String query = "insert into point_tbl values(p_seq.nextval, ?,0,?)";
		int result = jdbc.update(query, memberNo, usePoint);
		return result;
	}

	public int selectPointNo(int memberNo) {
		String query = "select max(point_no) from point_tbl where member_no=?";
		int pointNo = jdbc.queryForObject(query, Integer.class, memberNo);
		return pointNo;
	}

	public int createPointForOrder(int pointNo, long orderNo) {
		String query = "insert into changep_reason values(chgP_seq.nextval, ?, ?, null)";
		int result = jdbc.update(query, pointNo,orderNo);
		return result;
	}



}
