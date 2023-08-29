package kr.or.iei.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {
	private int cartNo;
	private String memberNo;
	private int optionNo;
	private int count;
	private int productFinalPrice;
	private int point;
}
