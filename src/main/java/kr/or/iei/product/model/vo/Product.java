package kr.or.iei.product.model.vo;

import java.text.DecimalFormat;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
	private int productNo;
	private int productPrice;
	private String productName;
	private int productPercent;
	private int productPoint;
	private String productBrand;
	private String productDelfree;
	private int productCategory;
	private String productContent;
	private int productFinalPrice;
	private List fileList;
	private List dfileList;
	private String optionName;
	private int optionAmount;
	private List optionList;
	//private String productOption;
	//private int productAmount;
	
	public String getPpc() {//게터 추가(ProductPriceComma 세자리씩 나눔)
		DecimalFormat df = new DecimalFormat("###,###,###");
		String ppc = df.format(productPrice);
		return ppc;
	}
	
	public String getPfpc() {//게터 추가(ProductFinalPriceComma 세자리씩 나눔)
		DecimalFormat df = new DecimalFormat("###,###,###");
		String pfpc = df.format(productFinalPrice);
		return pfpc;
	}
}
