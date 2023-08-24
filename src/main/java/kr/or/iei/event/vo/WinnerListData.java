package kr.or.iei.event.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WinnerListData {

	private List winnerBoardList;
	private int totalPage;
}
