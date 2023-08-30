package kr.or.iei.personalQna.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonalQnaFile {
	private int fileNo;
	private int qnaNo;
	private String filename;
	private String filepath;
}
