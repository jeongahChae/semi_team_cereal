package kr.or.iei.order.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
	private int orderNo;
	private int memberNo;
	private String productName;
	private int orderAmount;
	private String memberAddr;
	private String orderStatus;
	private String orderDate;
	private int totalPrice;
	private int totalPoint;
}//Order
