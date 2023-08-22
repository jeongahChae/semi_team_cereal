package kr.or.iei.notice.model.vo;

import lombok.Data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeListData {
	private List noticeList;
	private String pageNavi;
}
