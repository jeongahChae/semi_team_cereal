package kr.or.iei.personalQna.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalQnaComment {
	private int personalCommentNo;
	private String personalCommentWriter;
	private String personalCommentContent;
	private String personalCommentDate;
}
