package kr.or.iei.event.vo;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class EventRowMapper implements RowMapper<Event> {

	@Override
	public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
		Event e = new Event();
		e.setEndDate(rs.getString("end_date"));
		e.setEventContent(rs.getString("event_content"));
		e.setEventNo(rs.getInt("event_no"));
		e.setEventStatus(rs.getInt("event_status"));
		e.setEventTitle(rs.getString("event_title"));
		e.setEventWriter(rs.getString("event_writer"));
		e.setRegDate(rs.getString("reg_date"));
		e.setStartDate(rs.getString("start_date"));
		e.setThumbnail(rs.getString("thumbnail"));
		return e;
	}

}
