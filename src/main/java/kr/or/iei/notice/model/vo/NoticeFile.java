package kr.or.iei.notice.model.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeFile {
	private int fileNo;
	private int noticeNo;
	private String filename;
	private String filepath;
}
