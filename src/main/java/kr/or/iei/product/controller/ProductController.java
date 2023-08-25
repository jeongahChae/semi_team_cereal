package kr.or.iei.product.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.FileUtil;
import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductCategory;
import kr.or.iei.product.model.vo.ProductDetailFile;
import kr.or.iei.product.model.vo.ProductFile;
import kr.or.iei.product.model.vo.ProductListData;

@Controller
@RequestMapping(value="/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Value("${file.root}")
	private String root;
	@Value("${file.root2}")
	private String root2;
	
	@Autowired
	private FileUtil fileUtil;
	
	
	@GetMapping(value="/productList")
	public String productList(Model model, int reqPage) {
		ProductListData pld = productService.selectProductList(reqPage);
		model.addAttribute("productList", pld.getProductList());
		model.addAttribute("pageNavi", pld.getPageNavi());
		return "product/productList";
	}
	
	@GetMapping(value="/filedown")
	public void filedown(ProductFile file, HttpServletResponse response) {
		String savepath = root2+"product/";
		fileUtil.downloadFile(savepath, file.getFilename(), file.getFilepath(), response);
	}
	
	@GetMapping(value="/filedown2")
	public void filedown(ProductDetailFile file, HttpServletResponse response) {
		String savepath = root2+"product-detail/";
		fileUtil.downloadFile(savepath, file.getFilename(), file.getFilepath(), response);
	}
	
	@GetMapping(value="/writeFrm")
	public String productWriteFrm() {
		return "product/writeFrm";
	}
	
	@ResponseBody
	@GetMapping(value="/category")
	public List category() {
		List list = productService.selectCategory();
		return list;
	}
	
	@PostMapping(value="/write")
	public String productWrite(Product p, ProductCategory pc, MultipartFile[] upfile, MultipartFile[] upfile2, Model model) {
		ArrayList<ProductFile> fileList = null;
		ArrayList<ProductDetailFile> dfileList = null;
		System.out.println(upfile.length);
		System.out.println(upfile2.length);
		if(!upfile[0].isEmpty()) {
			fileList = new ArrayList<ProductFile>();
			String savepath = root2+"product/";
			System.out.println("savepath : " + savepath);
			for(MultipartFile file : upfile) {
				String filename = file.getOriginalFilename();
				System.out.println("filename : " + filename);
				// 중복 파일명 체크
				String filepath = fileUtil.getFilepath(savepath, filename);
				System.out.println("filepath : " + filepath);
				// 실제 폴더에 파일을 업로드
				File uploadFile = new File(savepath+filepath);
				try {
					file.transferTo(uploadFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 파일 업로드 끝
				ProductFile pf = new ProductFile();
				pf.setFilename(filename);
				pf.setFilepath(filepath);
				fileList.add(pf);
			}
		}
		
		if(!upfile2[0].isEmpty()) {
			dfileList = new ArrayList<ProductDetailFile>();
			String savepath = root2+"product-detail/";
			System.out.println("savepath : " + savepath);
			for(MultipartFile file : upfile2) {
				String filename = file.getOriginalFilename();
				System.out.println("filename : " + filename);
				// 중복 파일명 체크
				String filepath = fileUtil.getFilepath(savepath, filename);
				System.out.println("filepath : " + filepath);
				// 실제 폴더에 파일을 업로드
				File uploadFile2 = new File(savepath+filepath);
				try {
					file.transferTo(uploadFile2);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 파일 업로드 끝
				ProductDetailFile pdf = new ProductDetailFile();
				pdf.setFilename(filename);
				pdf.setFilepath(filepath);
				dfileList.add(pdf);
			}
		}
		p.setProductCategory(pc.getCategoryNo());
		int result = productService.insertProduct(p, fileList, dfileList);
		System.out.println(p);
		if((fileList == null && dfileList == null && result == 1) || (fileList != null && dfileList == null && result == (fileList.size()+1)) || (dfileList != null && fileList == null  && result == (dfileList.size()+1)) || (dfileList != null && fileList != null && result == ((dfileList.size()+fileList.size()+1)))) {
			model.addAttribute("title", "상품 등록 성공");
			model.addAttribute("msg", "상품이 등록되었습니다.");
			model.addAttribute("icon", "success");
		}else {
			model.addAttribute("title", "상품 등록 실패");
			model.addAttribute("msg", "상품 등록 실패");
			model.addAttribute("icon", "error");
		}
		model.addAttribute("loc", "/product/productList");
		return "common/msg";
	}
}
