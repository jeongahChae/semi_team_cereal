package kr.or.iei.notice.model.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class NoticeFileRowMapper implements RowMapper<NoticeFile> {

	@Override
	public NoticeFile mapRow(ResultSet rs, int rowNum) throws SQLException {
		NoticeFile file = new NoticeFile();
		file.setFilename(rs.getString("filename"));
		file.setFileNo(rs.getInt("file_no"));
		file.setFilepath(rs.getString("filepath"));
		file.setNoticeNo(rs.getInt("notice_no"));
		return file;
	}

}
