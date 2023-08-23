package kr.or.iei.event.vo;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Event {

	private int eventNo;
	private String eventTitle;
	private String eventContent;
	private String eventWriter;
	private int eventStatus;//1:진행중(기본값), 2:종료
	private String regDate;
	private String startDate;
	private String endDate;
	private String thumbnail;
	private List fileList;
	
	public String geteventContentBr() {//게터 추가
		return eventContent.replaceAll("\r\n", "<br>");//엔터를 br태그로 바꾸기
	}
}
