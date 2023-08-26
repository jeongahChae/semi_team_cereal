package kr.or.iei.myPage.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LikeListData {
	private List likeList;
	private String pageNavi;
	private int totalCount;
}
