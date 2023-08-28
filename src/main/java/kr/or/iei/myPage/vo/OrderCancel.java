package kr.or.iei.myPage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCancel {
	private int cancelNo;
	private int orderNo;
	private int productNo;
	private String productName;
	private String orderDate;
	private String selectTap;
	private String reason;
}
