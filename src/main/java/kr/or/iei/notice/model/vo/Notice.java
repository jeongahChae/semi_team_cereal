package kr.or.iei.notice.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notice {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String regDate;
	private String noticeWriter;
}
