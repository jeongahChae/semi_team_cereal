package kr.or.iei.adminPage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dashboard {
	private String totalSales;	//총 매출액(원)
	private String salesCount;	//총 주문(건수)
	private String avgSales;	//건당 평균 매출액(원)
}
