package kr.or.iei.myPage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
	private long orderNo;
	private String orderDate;
	private int totalPrice;
	private int totalPoint;
	private int categoryNo;
	private int orderStatus; //1:결제대기 /2:결제완료 /3:주문취소 /4:배송준비중 /5:배송중 /6:배송완료 /7:교환 /8:반품
	private int orderedPno;
	private int count;
	private int optionNo;
	private String optionName;
	private int optionAmount;

	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberPhone;
	private String memberEmail;
	private String memberAddr;
	private String memberGender;
	private String birthDate;
	private String enrollDate;
	private int memberLevel;
	private String memberPwnew;
	private String detail;
	
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
	private int productFinalPrice1;
}//Order
