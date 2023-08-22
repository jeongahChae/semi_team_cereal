package kr.or.iei.product.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductQna {
	private int pqnaNo;
	private String memberId;
	private int optionNo;
	private String pqnaTitle;
	private String pqnaContent;
	private String pqnaDate;
}
