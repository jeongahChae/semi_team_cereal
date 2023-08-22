package kr.or.iei.product.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Option {
	private int optionNo;
	private int productNo;
	private int optionAmount;
	private String optionName;
}
