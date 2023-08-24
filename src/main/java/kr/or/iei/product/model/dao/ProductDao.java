package kr.or.iei.product.model.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductDetailFile;
import kr.or.iei.product.model.vo.ProductFile;

@Repository
public class ProductDao {
	@Autowired
	private JdbcTemplate jdbc;

	public int insertProduct(Product p) {
		String query = "insert into product values(product_seq.nextval,?,?,?,?,DEFAULT,DEFAULT,?,?,?)";
		Object[] params = {p.getProductPrice(),p.getProductName(),p.getProductPercent(),(int)(p.getProductPrice()*0.05),p.getProductContent(),p.getProductCategory(),p.getProductPrice()-(p.getProductPrice()*(p.getProductPercent()/100))};
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

	
	
}
