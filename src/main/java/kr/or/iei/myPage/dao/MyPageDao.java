package kr.or.iei.myPage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.myPage.vo.LikeRowMapper;
import kr.or.iei.myPage.vo.Order;
import kr.or.iei.myPage.vo.OrderCancelRowMapper;
import kr.or.iei.myPage.vo.OrderCancelRowMapper2;
import kr.or.iei.myPage.vo.OrderRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;

@Repository
public class MyPageDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private OrderRowMapper orderRowMapper;
	@Autowired
	private LikeRowMapper likeRowMapper;
	@Autowired
	private ProductRowMapper productRowMapper;
	@Autowired
	private OrderCancelRowMapper orderCancelRowMapper;
	@Autowired
	private OrderCancelRowMapper2 orderCancelRowMapper2;
	
	//전체 주문 내역
	public List selectAllOrderList(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from order_tbl order by 1 desc)n) where rnum between ? and ?";
		List orderList = jdbc.query(query, orderRowMapper, start, end);
		return orderList;
	}//selectAllOrderList()

	//전체 주문 내역 페이지 네비에 사용
	public int totalCount() {
		String query = "select count(*) from order_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//totalCount()

	
	//기간내 주문 내역
	public List selectDateOrderList(int start, int end, String startDate, String endDate) {
		/*
		System.out.println(start);
		System.out.println(end);
		System.out.println(startDate);
		System.out.println(endDate);
		*/
		String query = "select * from (select rownum as rnum, n. * from (select * from order_tbl where order_date between ? and ? order by 1 desc)n) where rnum between ? and ?";
		List orderList = jdbc.query(query, orderRowMapper, startDate, endDate, start, end);
		return orderList;
	}//selectDateOrderList(int start, int end, String startDate, String endDate)
	
	//기간내 주문 내역 페이지 네비에 사용
	public int selectOrderTotalCount(String startDate, String endDate) {
		String query = "select count(*) from order_tbl where order_date between ? and ?";
		int totalCount = jdbc.queryForObject(query, Integer.class, startDate, endDate);
		return totalCount;
	}//selectOrderTotalCount(String startDate, String endDate)
	
	
		
	//주문 내역 상세
	public List selectOrderHistory(int orderNo) {
		String query = "select * from order_tbl where order_no=?";
		List orderDetail = jdbc.query(query, orderRowMapper, orderNo);
		return orderDetail;
	}//selectOrderHistory(int orderNo)



	//전체 주문 수
	public int selectOrderTotalCount() {
		String query = "select count(*) as cnt from order_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//selectOrderTotalCount()

	
	//주문 내역 - 주문 번호로 조회
	public Order selectNoOrderList(int orderNO) {
		String query = "select * from order_tbl where order_no=?";
		List orderDetail = jdbc.query(query, orderRowMapper, orderNO);
		return (Order)orderDetail.get(0);
	}//selectNoOrderList(int orderNO)

	//찜목록 - 전체 조회
	public List selectAllLikeList(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from like_list order by 1 desc)n) where rnum between ? and ?";
		List likeList = jdbc.query(query, likeRowMapper, start, end); //문제
		System.out.println("dao");
		System.out.println(likeList.size());
		for(int i=0;i<likeList.size();i++) {
			System.out.println("dao: "+likeList.get(i));
		}
		return likeList;
	}
	
	//전체 찜목록 수 
	public int selectLikeListTotalCount() {
		String query = "select count(*) from like_list";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	//상품 후기 목록
	public List selectAllreview(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from product_review order by 1 desc)n) where rnum between ? and ?";
		List reviewList = jdbc.query(query, productRowMapper, start, end);
		return reviewList;
	}

	//상품 후기 전체 수
	public int selectReviewTotalCount() {
		String query = "select count(*) as cnt from product_review";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	//주문취소/교환/반품 등록
	public int insertOrderCancelList(String selectTap, String reasonDetail, int orderNo, String productName, String orderDate) {
		String query = "insert into order_cancel values(ORDER_CANCEL_NO.NEXTVAL, ?, ?, ?, ?, ?)";
		Object[] params = {orderNo, productName, orderDate, selectTap, reasonDetail}; 
		int result = jdbc.update(query, params);
		return result;
	}
	//주문취소/교환/반품 내역 출력
	public List selectAllOrderCancel(int start, int end) {
		String query = "select order_no, order_tbl.product_name2, ORDER_AMOUNT, select_tap from (select rownum as rnum, n. * from (select * from order_cancel order by 1 desc)n) join order_tbl using(order_no) where rnum between ? and ?";
		List cancelList = jdbc.query(query, orderCancelRowMapper2, start, end);
		return cancelList;
	}
	//주문취소/교환/반품 전체 수
	public int selectCancelTotalCount() {
		String query = "select count(*) as cnt from order_cancel";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

//	//주문내역에서 삭제
//	public int deleteOrderHistory(int orderNO) {
//		String query = "delete from order_tbl where order_no=?";
//		Object[] params = {orderNO};
//		int result = jdbc.update(query, params);
//		return result;
//	}




	





}//myPageDao
