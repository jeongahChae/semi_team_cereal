package kr.or.iei.adminPage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderAdmin {
	private int orderNo;
	private int memberNo;
	private String productName;
	private int orderAmount;
	private String memberAddr;
	private int orderStatus; //1:결제대기 /2:결제완료 /3:주문취소 /4:배송준비중 /5:배송중 /6:배송완료 /7:교환 /8:반품
	private String orderDate;
	private int totalPrice;
	private int totalPoint;
	private String memberName;
}
