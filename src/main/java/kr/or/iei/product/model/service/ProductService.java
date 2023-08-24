package kr.or.iei.product.model.service;

import java.util.ArrayList;

import org.apache.tomcat.util.digester.SetPropertiesRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.product.model.dao.ProductDao;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductDetailFile;
import kr.or.iei.product.model.vo.ProductFile;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Transactional
	public int insertProduct(Product p, ArrayList<ProductFile> fileList, ArrayList<ProductDetailFile> dfileList) {
		// p는 항상 존재, fileList, dfileList는 파일이 없으면 null, 있으면 list 객체
		/*
		int productFinalPrice = p.getProductPrice()-(p.getProductPrice()*(p.getProductPercent()/100));
		int productPoint = (int)(p.getProductPrice()*0.05);
		p.setProductPoint(productPoint);
		p.setProductFinalPrice(productFinalPrice);
		*/
		int result = productDao.insertProduct(p);
		if(fileList != null) {
			int productNo = productDao.getProductNo();
			for(ProductFile file : fileList) {
				file.setProductNo(productNo);
				result += productDao.insertProductFile(file);
			}
		}
		if(dfileList != null) {
			int productNo = productDao.getProductNo();
			for(ProductDetailFile file : dfileList) {
				file.setProductNo(productNo);
				result += productDao.insertProductDetailFile(file);
			}
		}
		return result;
	}

}
