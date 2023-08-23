package kr.or.iei.event.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WinnerBoard {

	private int winNo;
	private String winTitle;
	private String winContent;
	private String winWriter;
	private int readCount;
	private String regDate;
	private String winImage;
}
