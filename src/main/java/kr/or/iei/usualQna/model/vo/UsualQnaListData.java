package kr.or.iei.usualQna.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsualQnaListData {
	private List usualQnaList;
	private String pageNavi;
}
