package kr.or.iei.personalQna.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalQna {
	private int qnaNo;
	private String qnaTitle;
	private String qnaContent;
	private String regDate;
	private String qnaStatus;
	private String qnaWriter;
}
