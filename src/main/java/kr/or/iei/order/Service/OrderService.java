package kr.or.iei.order.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.order.Dao.OrderDao;
import kr.or.iei.order.vo.Cart;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	public List selectCartInfoList(int memberNo) {
		List list = orderDao.selectCartInfoList(memberNo);
		return list;
	}

	@Transactional
	public int updateCartCount(int cartNo, int count) {		//장바구니에 들은 상품개수 업데이트
		int result = orderDao.updateCartCount(cartNo, count);
		return result;
	}

	@Transactional
	public int deleteCart(int cartNo) {
		int result = orderDao.deleteCart(cartNo);
		return result;
	}
}
