package kr.or.iei.product.model.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.digester.SetPropertiesRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.product.model.dao.ProductDao;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductCategory;
import kr.or.iei.product.model.vo.ProductDetailFile;
import kr.or.iei.product.model.vo.ProductFile;
import kr.or.iei.product.model.vo.ProductListData;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	
	@Transactional
	public int insertProduct(Product p, ArrayList<ProductFile> fileList, ArrayList<ProductDetailFile> dfileList) {
		// p는 항상 존재, fileList, dfileList는 파일이 없으면 null, 있으면 list 객체
		double percent = (double)p.getProductPercent();
		double price = (double)p.getProductPrice();
		double total = price-(price*(percent/100));
		int productFinalPrice = (int)Math.round(total);
		p.setProductFinalPrice(productFinalPrice);
		
		double point = price*0.05;
		int productPoint = (int)Math.round(point);
		p.setProductPoint(productPoint);
		System.out.println(p);
		/*

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

	public List selectCategory() {
		List list = productDao.selectCategory();
		return list;
	}

	public ProductListData selectProductList(int reqPage) {
		// 1. 한 페이지 당 게시물 수 지정 -> 16
		int numPerPage = 16;
		// reqPage가 1 -> 1~16
		// reqPage가 2 -> 17~32
		// reqPage가 3 -> 33~48
		int end = reqPage * numPerPage;
		int start = end - numPerPage + 1;
		List productList = productDao.selectProductList(start,end);
		// 2. 페이지 네비게이션 제작
		// 총 게시물 수 조회
		int totalCount = productDao.selectProductTotalCount();
		// 총 페이지 수 조회
		int totalPage = (int)Math.ceil(totalCount/(double)numPerPage);
		
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize) * pageNaviSize + 1;
		
		// 페이지 네비게이션 제작 시작
		String pageNavi = "<ul class='pagination circle-style'>";
		// 이전 버튼 제작
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/notice/list?reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		// 페이지 숫자
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/notice/list?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/notice/list?reqPage="+(pageNo)+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNo++;
			if(pageNo > totalPage) {
				break;
			}
		}
		// 다음 버튼 제작
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/notice/list?reqPage="+(pageNo)+"'>"; // 마지막 페이지에서 +1한 상태로 나왔기 때문에 그냥 pageNo
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		
		pageNavi += "</ul>";
		
		ProductListData pld = new ProductListData(productList, pageNavi);
		
		return pld;
	}




}
