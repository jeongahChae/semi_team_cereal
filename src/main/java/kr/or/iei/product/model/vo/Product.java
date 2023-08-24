package kr.or.iei.product.model.vo;

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
}
