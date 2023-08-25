package kr.or.iei.order.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderListData {
	private List orderList;
	private String pageNavi;
	private int totalCount;
}//OrderListData
