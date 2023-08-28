package kr.or.iei.myPage.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderCancelListData {
	private List orderCancelList;
	private String PageNavi;
	private int totalCount;
}
