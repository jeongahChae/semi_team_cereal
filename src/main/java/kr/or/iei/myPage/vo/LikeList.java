package kr.or.iei.myPage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeList {
	private int likeNo;
	private int optionNo;
	private String productName;
	private int productPrice;
}//LikeList
