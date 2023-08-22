package kr.or.iei.aboutUs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Store {
	private int storeNo;
	private String storeName;
	private String storeAddr;
	private String storePhone;
	private String storeImage;
}
