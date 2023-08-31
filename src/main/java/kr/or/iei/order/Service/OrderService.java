package kr.or.iei.order.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.order.Dao.OrderDao;
import kr.or.iei.order.vo.Cart;
import kr.or.iei.product.model.dao.ProductDao;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductDao productDao;

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

	public List selectProductOption(int productNo) {
		List list = productDao.selectAllOption(productNo);
		return list;
	}

	@Transactional
	public int updateOption(int cartNo, int newOptionNo, int newCount) {
		int result = orderDao.updateOption(cartNo, newOptionNo, newCount);
		return result;
	}

	public boolean deleteCart(String no, int memberNo) {
		StringTokenizer sT1 = new StringTokenizer(no, "/");
		boolean result = true;
		while(sT1.hasMoreTokens()) {
			int cartNo = Integer.parseInt(sT1.nextToken());
			int changeResult = orderDao.deleteCart(memberNo, cartNo);	//레벨변경 재활용
			if(changeResult == 0) {	//실패
				result = false;
				break;	//한 번이라도 실패가 일어나면 더 할 필요가 없으니까 내보냄
			}
		}
		return result;
	}

	public List selectCartToOrder(String no) {
		StringTokenizer sT1 = new StringTokenizer(no, "/");
		List<Cart> list = new ArrayList<Cart>();
		while(sT1.hasMoreTokens()) {
			int cartNo = Integer.parseInt(sT1.nextToken());
			Cart c = orderDao.selectCartToOrder(cartNo);
			list.add(c);
		}
		return list;
	}

	@Transactional
	public int createOrder(int memberNo, int price, String cart) {
		int result = 0;
		result += orderDao.createOrder(memberNo, price);
		int orderNo = orderDao.selectOrderNo();
		if(result>0) {
			StringTokenizer sT1 = new StringTokenizer(cart, "/");
			while(sT1.hasMoreTokens()) {
				int cartNo = Integer.parseInt(sT1.nextToken());
				Cart c = orderDao.selectCartInfo(cartNo);
				if(c != null) {
					result += orderDao.createOrderedProduct(c);
				}
		}
		return result;
	}
}
