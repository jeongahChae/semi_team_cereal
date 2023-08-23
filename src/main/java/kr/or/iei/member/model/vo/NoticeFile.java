package kr.or.iei.member.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
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
