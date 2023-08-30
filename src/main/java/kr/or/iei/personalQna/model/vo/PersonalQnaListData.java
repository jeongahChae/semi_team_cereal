package kr.or.iei.personalQna.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalQnaListData {
	private List personalQnaList;
	private String pageNavi;
}
