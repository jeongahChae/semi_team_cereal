package kr.or.iei.order.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Point {
	private int pointNo;
	private int memberNo;
	private int plusPoint;
	private int minusPoint;

}
