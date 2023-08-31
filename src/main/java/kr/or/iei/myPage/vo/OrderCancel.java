package kr.or.iei.myPage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCancel {
	private int cancelNo;
	private Long orderNo;
	private String orderDate;
	private int orderStatus;
	private String reason;
	private String optionName;
	private int optionAmount;
	private String memberName;
	private String memberAddr;
	private String productName;
	private int count;
	private int orderedPno;
}
