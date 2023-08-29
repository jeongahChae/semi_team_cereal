package kr.or.iei.order.vo;

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
	
}
