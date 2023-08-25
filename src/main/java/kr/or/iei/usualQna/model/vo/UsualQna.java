package kr.or.iei.usualQna.model.vo;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsualQna {
	private int qnaNo;
	private String qnaCategory;
	private String qnaTitle;
	private String qnaContent;
	private String qnaWriter;
}
