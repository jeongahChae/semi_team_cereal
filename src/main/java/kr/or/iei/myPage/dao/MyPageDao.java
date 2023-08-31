package kr.or.iei.myPage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.myPage.vo.LikeRowMapper;
import kr.or.iei.myPage.vo.OdHistoryDelStatusRowMapper;
import kr.or.iei.myPage.vo.Order;
import kr.or.iei.myPage.vo.OrderCancelRowMapper;
import kr.or.iei.myPage.vo.OrderCancelRowMapper2;
import kr.or.iei.myPage.vo.OrderRowMapper;
import kr.or.iei.myPage.vo.OrderRowMapper2;
import kr.or.iei.myPage.vo.OrderRowMapper3;
import kr.or.iei.myPage.vo.OrderRowMapper4;
import kr.or.iei.myPage.vo.OrderRowMapper5;
import kr.or.iei.product.model.vo.ProductReviewRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;

@Repository
public class MyPageDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private OrderRowMapper orderRowMapper;
	@Autowired
	private OrderRowMapper2 orderRowMapper2;
	@Autowired
	private OrderRowMapper3 orderRowMapper3;	
	@Autowired
	private OrderRowMapper4 orderRowMapper4;
	@Autowired
	private OrderRowMapper5 orderRowMapper5;
	@Autowired
	private OdHistoryDelStatusRowMapper odHistoryDelStatusRowMapper;
	@Autowired
	private LikeRowMapper likeRowMapper;
	@Autowired
	private ProductRowMapper productRowMapper;
	@Autowired
	private OrderCancelRowMapper orderCancelRowMapper;
	@Autowired
	private OrderCancelRowMapper2 orderCancelRowMapper2;
	@Autowired
	private ProductReviewRowMapper productReviewRowMapper;
	
	//전체 주문 내역
	public List selectAllOrderList(int start, int end) {
		//String query = "select * from (select rownum as rnum, n. * from (select * from order_tbl order by 1 desc)n) where rnum between ? and ?";
		
		String query = "select * from (select rownum as rnum, n. * from\r\n" + 
				"(select order_no, product_name, count, total_price, order_status\r\n" + 
				"from (select *\r\n" + 
				"from order_tbl\r\n" + 
				"left join ordered_products_tbl using(order_no))\r\n" + 
				"left join option_tbl using(option_no)\r\n" + 
				"left join member_tbl using(member_no)\r\n" + 
				"left join product using(product_no)\r\n" + 
				"order by 1 desc) \r\n" + 
				"n) where rnum between ? and ?";
		
		List orderList = jdbc.query(query, orderRowMapper2, start, end);
		
		System.out.println("orderList.size(): "+orderList.size());
		for(int i=0;i<orderList.size();i++) {
			System.out.println("orderList.get(i): "+orderList.get(i));
		}
		
		return orderList;
	}//selectAllOrderList()

	//전체 주문 내역 페이지 네비에 사용
	public int totalCount() {
		String query = "select count(*)\r\n" + 
				"from (\r\n" + 
				"select *\r\n" + 
				"from (select *\r\n" + 
				"from order_tbl\r\n" + 
				"left join ordered_products_tbl using(order_no))\r\n" + 
				"left join option_tbl using(option_no)\r\n" + 
				"left join member_tbl using(member_no)\r\n" + 
				"left join product using(product_no)\r\n" + 
				")";
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
		String query = "select * from (select rownum as rnum, n. * from \r\n" + 
				"(select order_no, product_name, count, total_price, order_status\r\n" + 
				"from (select *\r\n" + 
				"from order_tbl\r\n" + 
				"left join ordered_products_tbl using(order_no))\r\n" + 
				"left join option_tbl using(option_no)\r\n" + 
				"left join member_tbl using(member_no)\r\n" + 
				"left join product using(product_no)\r\n" + 
				"where order_date between ? and ?\r\n" + 
				"order by 1 desc)\r\n" + 
				"n) where rnum between ? and ?";
		List orderList = jdbc.query(query, orderRowMapper2, startDate, endDate, start, end);
		return orderList;
	}//selectDateOrderList(int start, int end, String startDate, String endDate)
	
	//기간내 주문 내역 페이지 네비에 사용
	public int selectOrderTotalCount(String startDate, String endDate) {
		String query = "select count(*)\r\n" + 
				"from (\r\n" + 
				"select *\r\n" + 
				"from (select *\r\n" + 
				"from order_tbl\r\n" + 
				"left join ordered_products_tbl using(order_no))\r\n" + 
				"left join option_tbl using(option_no)\r\n" + 
				"left join member_tbl using(member_no)\r\n" + 
				")\r\n" + 
				"where order_date between ? and ?";
		int totalCount = jdbc.queryForObject(query, Integer.class, startDate, endDate);
		return totalCount;
	}//selectOrderTotalCount(String startDate, String endDate)
	
	
		
	//주문 내역 상세
	public List selectOrderHistory(Long orderNo) {
		/*
		String query = "select product_name, total_price, count, order_status\r\n" + 
				"from (select *\r\n" + 
				"from order_tbl\r\n" + 
				"left join ordered_products_tbl using(order_no))\r\n" + 
				"left join option_tbl using(option_no)\r\n" + 
				"left join member_tbl using(member_no)\r\n" + 
				"left join product using(product_no)\r\n" + 
				"where order_no=?";
		*/
		String query = "select order_no, member_no, option_no, product_name\r\n" + 
				"from order_tbl\r\n" + 
				"left join ordered_products_tbl using(order_no)\r\n" + 
				"left join option_tbl using(option_no)\r\n" + 
				"left join product using(product_no)\r\n" + 
				"where order_no=?";
		List orderDetail = jdbc.query(query, orderRowMapper5, orderNo);
		
		System.out.println("orderDetail.size(): "+orderDetail.size());
		for(int i=0;i<orderDetail.size();i++) {
			System.out.println("orderDetail.get(i): "+orderDetail.get(i));
		}
		
		return orderDetail;
	}//selectOrderHistory(int orderNo)



	//현재 미사용
	//전체 주문 수
	public int selectOrderTotalCount() {
		String query = "select count(*) as cnt from order_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//selectOrderTotalCount()

	
	//주문 내역 - 주문 번호로 조회
	public Order selectNoOrderList(int orderNO) {
//		String query = "select * from order_tbl where order_no=?";
		String query = "select order_no, product_name, count\r\n" + 
				"from order_tbl\r\n" + 
				"left join ordered_products_tbl using(order_no)\r\n" + 
				"left join option_tbl using(option_no)\r\n" + 
				"left join product using(product_no)\r\n" + 
				"where order_no=?";
		List orderDetail = jdbc.query(query, orderRowMapper4, orderNO);
		return (Order)orderDetail.get(0);
	}//selectNoOrderList(int orderNO)

	

	//주문취소/교환/반품 등록
	public int insertOrderCancelList(int orderStatus, String reasonDetail, Long orderNo, String productName, String optionName, int orderCount, int memberNo) {
	  //insert into order_cancel values(order_cancel_no.nextval, order_no, product_name, option_name, order_count, order_status, reason, member_no);
		String query = "insert into order_cancel values(order_cancel_no.nextval, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {orderNo, productName, optionName, orderCount,orderStatus, reasonDetail, memberNo}; 
		int result = jdbc.update(query, params);
		return result;
	}
	//주문취소/교환/반품 내역 출력
	public List selectAllOrderCancel(int start, int end) {
//		String query = "select * from (select rownum as rnum, n. * from (select * from order_cancel order by 1 desc)n) where rnum between ? and ?";
		/*
		회원번호: 27로 로그인해서 해당하는 정보를 출력할 경우
		String query = "select order_no,count,order_status,option_name,product_name\r\n" + 
				"from ordered_products_tbl\r\n" + 
				"join order_tbl using(order_no)\r\n" + 
				"join option_tbl using(option_no)\r\n" + 
				"join product using(product_no)\r\n" + 
				"where \r\n" + 
				"member_no = 27\r\n" + 
				"and\r\n" + 
				"ordered_pno in (select min(ordered_pno) from ordered_products_tbl group by order_no);";
		*/
		/*
		String query = "select * from (select rownum as rnum, n. * from \r\n" + 
				"(\r\n" + 
				"select order_no,count,order_status,option_name,product_name\r\n" + 
				"from ordered_products_tbl\r\n" + 
				"join order_tbl using(order_no)\r\n" + 
				"join option_tbl using(option_no)\r\n" + 
				"join product using(product_no)\r\n" + 
				"where\r\n" + 
				"ordered_pno in (select min(ordered_pno) from ordered_products_tbl group by order_no)\r\n" + 
				")\r\n" + 
				"n) where rnum between ? and ?";
		*/
		String query = "select * from (select rownum as rnum, n. * from(select * from order_cancel order by 1 desc)n) where rnum between ? and ?";
		List cancelList = jdbc.query(query, orderCancelRowMapper, start, end);
		return cancelList;
	}
	//주문취소/교환/반품 전체 수
	public int selectCancelTotalCount() {
//		String query = "select count(*) as cnt from order_cancel";
		/*
		회원번호: 27로 로그인해서 해당하는 정보를 출력할 경우
		String query = "select count(*)\r\n" + 
				"from ordered_products_tbl\r\n" + 
				"join order_tbl using(order_no)\r\n" + 
				"join option_tbl using(option_no)\r\n" + 
				"join product using(product_no)\r\n" + 
				"where \r\n" + 
				"member_no = 27\r\n" + 
				"and\r\n" + 
				"ordered_pno in (select min(ordered_pno) from ordered_products_tbl group by order_no);";
		*/
		/*
		String query = "select count(*)\r\n" + 
				"from ordered_products_tbl\r\n" + 
				"join order_tbl using(order_no)\r\n" + 
				"join option_tbl using(option_no)\r\n" + 
				"join product using(product_no)\r\n" + 
				"where \r\n" + 
				"ordered_pno in (select min(ordered_pno) from ordered_products_tbl group by order_no)";
		*/
		String query = "select count(*) from order_cancel";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	//주문내역에서 삭제
	public int deleteOrderHistory(Long orderNo) {
		String query = "delete from order_tbl where order_no=?";
		Object[] params = {orderNo};
		int result = jdbc.update(query, params);
		return result;
	}




	//찜목록 - 전체 조회
	public List selectAllLikeList(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from like_list order by 1 desc)n) where rnum between ? and ?";
		List likeList = jdbc.query(query, likeRowMapper, start, end);  
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
		List reviewList = jdbc.query(query, productReviewRowMapper, start, end); 
		return reviewList;
	}

	//상품 후기 전체 수
	public int selectReviewTotalCount() {
		String query = "select count(*) as cnt from product_review";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}





}//myPageDao
