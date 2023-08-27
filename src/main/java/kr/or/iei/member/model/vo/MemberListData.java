package kr.or.iei.member.model.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberListData {
	private List memberList;
	private String pageNavi;
	private int totalCount;
}
