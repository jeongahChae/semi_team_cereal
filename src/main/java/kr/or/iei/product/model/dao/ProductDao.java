package kr.or.iei.product.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.vo.Member;
import kr.or.iei.product.model.vo.Option;
import kr.or.iei.product.model.vo.OptionRowMapper;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductCategory;
import kr.or.iei.product.model.vo.ProductCateogryRowMapper;
import kr.or.iei.product.model.vo.ProductDetailFile;
import kr.or.iei.product.model.vo.ProductDetailFileRowMapper;
import kr.or.iei.product.model.vo.ProductFile;
import kr.or.iei.product.model.vo.ProductFileRowMapper;
import kr.or.iei.product.model.vo.ProductRowMapper;

@Repository
public class ProductDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ProductRowMapper productRowMapper;
	@Autowired
	private ProductFileRowMapper productFileRowMapper;
	@Autowired
	private ProductDetailFileRowMapper productDetailFileRowMapper;
	@Autowired
	private ProductCateogryRowMapper productCategoryRowMapper;
	@Autowired
	private OptionRowMapper optionRowMapper;

	public int insertProduct(Product p) {
		String query = "insert into product values(product_seq.nextval,?,?,?,?,'ESSENTIAL#','무료배송',?,?,?)";
		Object[] params = {p.getProductPrice(),p.getProductName(),p.getProductPercent(),p.getProductPoint(),p.getProductContent(),p.getProductCategory(),p.getProductFinalPrice()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int getProductNo() {
		String query = "select max(product_no) from product";
		int productNo = jdbc.queryForObject(query, Integer.class);
		return productNo;
	}

	public int insertProductFile(ProductFile file) {
		String query = "insert into product_file values(product_file_seq.nextval,?,?,?)";
		Object[] params = {file.getFilename(), file.getFilepath(), file.getProductNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int insertProductDetailFile(ProductDetailFile file) {
		String query = "insert into product_detail_file values(product_detail_file_seq.nextval,?,?,?)";
		Object[] params = {file.getFilename(), file.getFilepath(), file.getProductNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public List selectCategory() {
		String query = "select * from product_category";
		List list = jdbc.query(query, productCategoryRowMapper);
		return list;
	}

	public List selectProductList(int start, int end, int categoryNo) {
		String query = "select * from (select rownum as rnum, p.* from (select * from product where product_category=? order by 1 desc)p) where rnum between ? and ?";
		List list = jdbc.query(query, productRowMapper, categoryNo, start, end);
		return list;
	}

	public int selectProductTotalCount() {
		String query = "select count(*) from product";
		// 단일 값(행1, 열1)을 조회하는 경우
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public Product selectOneProduct(int productNo) {
		String query = "select * from product where product_no=?";
		List list = jdbc.query(query, productRowMapper, productNo);
		return (Product)list.get(0);
	}

	public List selectProductFile(int productNo) {
		String query = "select * from product_file where file_no = (select min(file_no) from product_file where product_no=?)";
		List list = jdbc.query(query, productFileRowMapper, productNo);
		return list;
	}

	public List selectAllProductFile(int productNo) {
		String query = "select * from product_file where product_no=?";
		List list = jdbc.query(query, productFileRowMapper, productNo);
		return list;
	}

	public List selectAllProductDetailFile(int productNo) {
		String query = "select * from product_detail_file where product_no=?";
		List list = jdbc.query(query, productDetailFileRowMapper, productNo);
		return list;
	}

	public int insertOption(Option o, int productNo) {
		String query = "insert into option_tbl values(option_seq.nextval,?,?,?)";
		Object[] params = {productNo, o.getOptionAmount(), o.getOptionName()};
		int option = jdbc.update(query, params);
		return option;
	}

	public List selectAllOption(int productNo) {
		String query = "select * from option_tbl where product_no=?";
		List list = jdbc.query(query, optionRowMapper, productNo);
		return list;
	}

	public String selectCategoryName(int categoryNo) {
		String query = "select category_name from product_category where category_no=?";
		Object[] params = {categoryNo};
		String cateName = jdbc.queryForObject(query, String.class, params);
		return cateName;
	}
		
	
}
