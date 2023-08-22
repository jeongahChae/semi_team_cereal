package kr.or.iei.aboutUs.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class News {
	private int newsNo;
	private String newsTitle;
	private String newsContent;
	private String press;
	private String regDate;
	
	public String getNewsContentBr() {//게터 추가
		return newsContent.replaceAll("\r\n", "<br>");//엔터를 br태그로 바꾸기
	}
}
