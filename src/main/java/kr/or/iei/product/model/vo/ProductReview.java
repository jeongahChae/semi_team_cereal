package kr.or.iei.product.model.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductReview {
	private int reviewNo;
	private String memberId;
	private String reviewContent;
	private String reviewImg;
	private String reviewDate;
	private int orderedPno;
}
