package kr.or.iei.order.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.order.Dao.OrderDao;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	
	public List selectCartInfoList(int memberNo) {
		List cartInfoList = orderDao.selectCartInfoList(memberNo);
		return cartInfoList;
	}
}
