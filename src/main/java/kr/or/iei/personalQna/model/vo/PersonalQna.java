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
	
	//editor 사용시 br은 없어도 괜찮다.(<p>태그로 감싸기 떄문에)
		public String getQnaContentBr() {
			//replaceAll(?,?) 앞에 있는? 를 뒤에 있는 ?로 바꿔줘
			return qnaContent.replaceAll("\r\n", "<br>");
		}
}
