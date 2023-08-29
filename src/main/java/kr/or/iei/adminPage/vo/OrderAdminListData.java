package kr.or.iei.adminPage.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderAdminListData {
	private List orderList;
	private String pageNavi;
	private int totalCount;
}
