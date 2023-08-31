package kr.or.iei.order.vo;

import java.text.DecimalFormat;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
	private int cartNo;
	private int memberNo;
	private int optionNo;
	private int count;
	private int productFinalPrice;
	private int point;
	private int productPrice;
	private String productName;
	private int productPercent;
	private int productPoint;
	private String productDelfree;
	private int productCategory;
	
	private int productNo;
	private int optionAmount;
	private String optionName;
	
	
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
