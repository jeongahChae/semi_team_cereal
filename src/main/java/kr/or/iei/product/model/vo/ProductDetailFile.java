package kr.or.iei.product.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ProductDetailFile {
	private int fileNo;
	private String filename;
	private String filepath;
	private int productNo;
}
