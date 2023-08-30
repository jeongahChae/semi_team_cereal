package kr.or.iei.myPage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
	private int orderNo;
	private String orderDate;
	private int totalPrice;
	private int totalPoint;
	private int categoryNo;
	private int orderStatus; //1:결제대기 /2:결제완료 /3:주문취소 /4:배송준비중 /5:배송중 /6:배송완료 /7:교환 /8:반품
	private int memberNo;
	private String memberAddr;
	private String memberName;
	private int orderedPno;
	private int productFinalPrice;
	private int count;
	private int optionNo;
}//Order
