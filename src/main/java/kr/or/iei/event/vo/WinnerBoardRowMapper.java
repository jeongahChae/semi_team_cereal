package kr.or.iei.event.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class WinnerBoardRowMapper implements RowMapper<WinnerBoard>{

	@Override
	public WinnerBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
		WinnerBoard wb = new WinnerBoard();
		wb.setReadCount(rs.getInt("read_count"));
		wb.setRegDate(rs.getString("reg_date"));
		wb.setWinContent(rs.getString("win_content"));
		wb.setWinImage(rs.getString("win_image"));
		wb.setWinNo(rs.getInt("win_no"));
		wb.setWinTitle(rs.getString("win_title"));
		wb.setWinWriter(rs.getString("win_writer"));
		return wb;
	}

}
