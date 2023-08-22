package kr.or.iei.product.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCategory {
	private String categoryMain;
	private String categorySub;
	private String categoryRef;
}
