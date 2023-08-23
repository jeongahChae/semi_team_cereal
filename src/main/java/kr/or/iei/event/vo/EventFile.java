package kr.or.iei.event.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventFile {
	private int fileNo;
	private int noticeNo;
	private String filename;
	private String filepath;
}
