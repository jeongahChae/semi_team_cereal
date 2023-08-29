package kr.or.iei.adminPage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.iei.adminPage.dao.AdminPageDao;
import kr.or.iei.adminPage.vo.Dashboard;
import kr.or.iei.member.model.vo.MemberListData;
import kr.or.iei.product.model.vo.ProductListData;


@Service
public class AdminPageService {
	@Autowired
	private AdminPageDao adminPageDao;

	public Dashboard selectDashboardData() {
		Dashboard d = new Dashboard();
		d.setTotalSales(adminPageDao.selectTotalSales());	//총 매출액 조회
		d.setSalesCount(adminPageDao.selectSalesCount());	//총 매출 건수 조회
		d.setAvgSales(adminPageDao.selectAvgSales());		//건당 매출액 조회
		return d;
	}

	
	//회원 목록
	public MemberListData selectAllMember(int reqPage) {
		int numPerPage = 10; //한 페이지에 표시되는 게시물 수를 10개로 설정
		int end = reqPage * numPerPage; //끝나는 개수 숫자 /한 페이지에 표시되는 마지막 숫자 /reqPage가 1일 경우: 10
		int start = end - numPerPage +1; //시작하는 개수 숫자 /한 페이지에 표시되는 시작 숫자/reqPage가 1일 경우: 1
		List memberList = adminPageDao.selectAllMember(start, end);
		
		//pageNavi 제작준비
		int totalCount = adminPageDao.selectMemberTotalCount();
		int totalPage = (int)Math.ceil(totalCount/(double)numPerPage);
		//pageNavi 사이즈(넘버 갯수 지정)
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		//pageNavi 제작
		String pageNavi = "<ul class='page-design circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/adminPage/allMemberList?btn=0&reqPage="+1+"'>";//1번페이지로 
			pageNavi += "<span class='material-icons'>first_page</span>";//(|<)이렇게 생김
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/adminPage/allMemberList?btn=0&reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		//pageNavi 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/adminPage/allMemberList?btn=0&reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/adminPage/allMemberList?btn=0&reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				//페이지가 마지막 페이지에 도달했을 경우
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/adminPage/allMemberList?btn=0&reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/adminPage/allMemberList?btn=0&reqPage="+totalPage+"'>";//마지막 페이지로 가기
			pageNavi += "<span class='material-icons'>last_page</span>";//마지막 페이지로 가기 icon
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		MemberListData mld = new MemberListData(memberList, pageNavi, totalCount);
		return mld;
	}


	//등록 상품 현황
	public ProductListData selectAllProduct(int reqPage) {
		int numPerPage = 10; //한 페이지에 표시되는 게시물 수를 10개로 설정
		int end = reqPage * numPerPage; //끝나는 개수 숫자 /한 페이지에 표시되는 마지막 숫자 /reqPage가 1일 경우: 10
		int start = end - numPerPage +1; //시작하는 개수 숫자 /한 페이지에 표시되는 시작 숫자/reqPage가 1일 경우: 1
		List productList = adminPageDao.selectAllProduct(start, end);
		
		//pageNavi 제작준비
		int totalCount = adminPageDao.selectProductTotalCount();
		int totalPage = (int)Math.ceil(totalCount/(double)numPerPage);
		//pageNavi 사이즈(넘버 갯수 지정)
		int pageNaviSize = 5;
		int pageNo = ((reqPage-1)/pageNaviSize)*pageNaviSize+1;
		//pageNavi 제작
		String pageNavi = "<ul class='page-design circle-style'>";
		if(pageNo != 1) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/adminPage/allProductList?btn=1&reqPage="+1+"'>";//1번페이지로 
			pageNavi += "<span class='material-icons'>first_page</span>";//(|<)이렇게 생김
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/adminPage/allProductList?btn=1&reqPage="+(pageNo-1)+"'>";
			pageNavi += "<span class='material-icons'>chevron_left</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		//pageNavi 숫자
		for(int i=0;i<pageNaviSize;i++) {
			if(pageNo == reqPage) {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item active-page' href='/adminPage/allProductList?btn=1&reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}else {
				pageNavi += "<li>";
				pageNavi += "<a class='page-item' href='/adminPage/allProductList?btn=1&reqPage="+pageNo+"'>";
				pageNavi += pageNo;
				pageNavi += "</a>";
				pageNavi += "</li>";
			}
			pageNo++;
			if(pageNo>totalPage) {
				//페이지가 마지막 페이지에 도달했을 경우
				break;
			}
		}
		if(pageNo <= totalPage) {
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/adminPage/allProductList?btn=1&reqPage="+pageNo+"'>";
			pageNavi += "<span class='material-icons'>chevron_right</span>";
			pageNavi += "</a>";
			pageNavi += "</li>";
			pageNavi += "<li>";
			pageNavi += "<a class='page-item' href='/adminPage/allProductList?btn=1&reqPage="+totalPage+"'>";//마지막 페이지로 가기
			pageNavi += "<span class='material-icons'>last_page</span>";//마지막 페이지로 가기 icon
			pageNavi += "</a>";
			pageNavi += "</li>";
		}
		pageNavi += "</ul>";
		
		ProductListData pld = new ProductListData(productList, pageNavi, totalCount);
		return pld;
	}

	public List selectCategorySales(int year, int month, int category) {
		List categorySalesList = adminPageDao.selectCategorySales(year,month, category);
		return categorySalesList;
	}
	
}
