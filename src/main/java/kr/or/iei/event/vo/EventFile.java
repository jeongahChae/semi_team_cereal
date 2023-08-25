package kr.or.iei.event.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventFile {
	private int fileNo;
	private int eventNo;
	private String filename;
	private String filepath;
}
