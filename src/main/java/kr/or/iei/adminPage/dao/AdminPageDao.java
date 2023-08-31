package kr.or.iei.adminPage.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.adminPage.vo.OrderAdminRowMapper;
import kr.or.iei.member.model.vo.MemberRowMapper;
import kr.or.iei.myPage.vo.OrderCancelRowMapper2;
import kr.or.iei.myPage.vo.OrderRowMapper;
import kr.or.iei.myPage.vo.OrderRowMapper2;
import kr.or.iei.myPage.vo.OrderRowMapper6;
import kr.or.iei.myPage.vo.OrderRowMapper7;
import kr.or.iei.myPage.vo.OrderRowMapper8;
import kr.or.iei.product.model.vo.CategorySalesRowMapper;
import kr.or.iei.product.model.vo.ProductCateogryRowMapper;

import kr.or.iei.product.model.vo.ProductRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper2;

@Repository
public class AdminPageDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private MemberRowMapper memberRowMapper;
	@Autowired
	private ProductRowMapper productRowMapper;
	@Autowired
	private ProductRowMapper2 productRowMapper2;
	@Autowired
	private OrderRowMapper orderRowMapper;
	@Autowired
	private OrderRowMapper2 orderRowMapper2;
	@Autowired
	private OrderRowMapper6 orderRowMapper6;
	@Autowired
	private OrderRowMapper7 orderRowMapper7;
	@Autowired
	private OrderRowMapper8 orderRowMapper8;
	@Autowired
	private CategorySalesRowMapper categorySalesRowMapper;
	@Autowired
	private OrderCancelRowMapper2 orderCancelRowMapper2;
	
	//회원 목록
	public List selectAllMember(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select * from member_tbl order by 1 desc)n) where rnum between ? and ?";
		List memberList = jdbc.query(query, memberRowMapper, start, end);
		return memberList;
	}
	
	//전체 회원 수
	public int selectMemberTotalCount() {
		String query = "select count(*) as cnt from member_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}//selectMemberTotalCount()

	//전체 등록 상품
	public List selectAllProduct(int start, int end) {
		String query = "select * from (select rownum as rnum, n. * from (select product_no, product_name, option_name, option_amount from product join option_tbl using(product_no))n) where rnum between ? and ?";
		List productList = jdbc.query(query, productRowMapper2, start, end);
		return productList;
	}

	//전체 등록 상품 수
	public int selectProductTotalCount() {
		String query = "select count(*) from product join option_tbl using(product_no)";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
	
	
	//주문 현황 관리 - 전체 주문 조회
	public List selectAllOrderListAdmin(int start, int end) {
		//String query = "select * from (select rownum as rnum, n. * from (select * from(select order_no, product_name2, member_name, member_addr, order_status from order_tbl union select order_no, product_name2, member_name, member_addr, order_status from ORDER_CANCEL) order by 1 desc)n) where rnum between ? and ?";
		String query = "select * from (select rownum as rnum, n. * from (\r\n" + 
				"select order_no, product_name, member_name, member_addr, order_status\r\n" + 
				"from order_tbl\r\n" + 
				"left join member_tbl using(member_no)\r\n" + 
				"left join ordered_products_tbl using(order_no)\r\n" + 
				"left join product using(product_final_price)\r\n" + 
				"union\r\n" + 
				"select order_no, product_name, member_name, member_addr, order_status \r\n" + 
				"from order_cancel\r\n" + 
				"left join member_tbl using(member_no)\r\n" + 
				"order by 1 desc)n) where rnum between ? and ?";
		List orderList = jdbc.query(query, orderRowMapper6, start, end);
		return orderList;
	}
	//주문 현황 관리 - 단일 조회
	public List selectOrderAdmin(int orderNO) {
		//String query = "select * from order_tbl where order_no=?";
		String query = "select order_no, product_name, member_name, member_addr, order_status, member_no\r\n" + 
				"from order_tbl\r\n" + 
				"left join member_tbl using(member_no)\r\n" + 
				"left join ordered_products_tbl using(order_no)\r\n" + 
				"left join product using(product_final_price)\r\n" + 
				"where order_no=?";
		List order = jdbc.query(query, orderRowMapper8, orderNO);
		
		for(int i=0;i<order.size();i++) {
    		System.out.println("dao - order.get(i): "+order.get(i));
    	}
		
		return order;
	}
	//주문 현황 관리 - 업데이트
	public int orderUpdate(int orderStatus, Long orderNo) {
		String query = "update order_tbl set order_status=? where order_no=?";
		Object[] params = {orderStatus, orderNo};
		int result = jdbc.update(query, params);
		return result;
	}
	
	
	//전체 주문 수 
	public int selectOrderTotalCount() {
		//String query = "select count(*) from (select order_no, product_name2, member_name, member_addr, order_status from order_tbl union select order_no, product_name2, member_name, member_addr, order_status from ORDER_CANCEL)";
		String query = "select count(*)\r\n" + 
				"from \r\n" + 
				"(select order_no, product_name, member_name, member_addr, order_status\r\n" + 
				"from order_tbl\r\n" + 
				"left join member_tbl using(member_no)\r\n" + 
				"left join ordered_products_tbl using(order_no)\r\n" + 
				"left join product using(product_final_price)\r\n" + 
				"union\r\n" + 
				"select order_no, product_name, member_name, member_addr, order_status \r\n" + 
				"from order_cancel\r\n" + 
				"left join member_tbl using(member_no))";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}
	

	//총 매출액(원)
	public String selectTotalSales() {
		String query = "select to_char(sum(total_price),'999,999,999') from order_tbl where order_status ='1'";
		String totalSales = jdbc.queryForObject(query, String.class);
		return totalSales;
	}

	public String selectSalesCount() {
		String query = "select to_char(count(total_price),'999,999,999') from order_tbl where order_status ='1'";
		String salesCount = jdbc.queryForObject(query, String.class);
		return salesCount;
	}

	public String selectAvgSales() {
		String query = "select to_char(avg(total_price),'999,999,999') from order_tbl where order_status ='1'";
		String avgSales = jdbc.queryForObject(query, String.class);
		return avgSales;
	}
	
	
	//카테고리별 매출 조회ㅠ ㅠ
	public List selectCategorySales(int year, String strMonth, int category) {
		String query = "select category_ref, category_no, category_name, sum(total_price)" + 
				"from (select category_no, category_name, order_no, total_price, category_ref, order_date from order_tbl left join product_category using (category_no) where order_status ='1' and order_date like ?)" + 
				"group by category_no, category_ref, category_name order by category_ref";
		String date = year+"-"+strMonth+"%";
		List list = jdbc.query(query, categorySalesRowMapper, date);
		return list;
	}
	
}
