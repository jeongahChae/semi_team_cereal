package kr.or.iei.aboutUs.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsListData {
	private List newsList;
	private String pageNavi;
}
