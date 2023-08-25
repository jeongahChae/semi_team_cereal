package kr.or.iei.product.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductCategory;
import kr.or.iei.product.model.vo.ProductCateogryRowMapper;
import kr.or.iei.product.model.vo.ProductDetailFile;
import kr.or.iei.product.model.vo.ProductFile;
import kr.or.iei.product.model.vo.ProductRowMapper;

@Repository
public class ProductDao {
	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private ProductRowMapper productRowMapper;
	@Autowired
	private ProductCateogryRowMapper productCategoryRowMapper;

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

	public List selectProductList(int start, int end) {
		String query = "select * from (select rownum as rnum, p.* from (select * from product order by 1 desc)p) where rnum between ? and ?";
		List list = jdbc.query(query, productRowMapper, start, end);
		return list;
	}

	public int selectProductTotalCount() {
		String query = "select count(*) from product";
		// 단일 값(행1, 열1)을 조회하는 경우
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}



	
	
}
