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
		String query = "insert into order_tbl values(to_char(sysdate,'yyyymmddhhmi')+order_seq.nextval, ?, sysdate, ?, null, default)";
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

}
