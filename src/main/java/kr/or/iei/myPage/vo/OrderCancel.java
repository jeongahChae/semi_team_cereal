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
	private String productName;
	private String optionName;
	private int orderCount;
	private int orderStatus;
	private String reason;
	private int memberNo;
	private String memberName;
	private String memberAddr;
}
