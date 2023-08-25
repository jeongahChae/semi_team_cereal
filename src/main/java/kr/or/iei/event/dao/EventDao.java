package kr.or.iei.event.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.event.vo.Event;
import kr.or.iei.event.vo.EventFile;
import kr.or.iei.event.vo.EventRowMapper;
import kr.or.iei.event.vo.WinnerBoardRowMapper;


@Repository
public class EventDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private EventRowMapper eventRowMapper;
	@Autowired
	private WinnerBoardRowMapper winnerBoardRowMapper;
	
	public List selectWinnerBoardList(int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from winner_board order by 1 desc)n)where rnum between ? and ?";
		List list = jdbc.query(query, winnerBoardRowMapper, start, end);
		return list;
	}
	
	public int selectWinnerBoardListTotalCount() {
		String query = "select count(*) from winner_board";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public int insertEvent(Event e) {
		String query = "insert into event_tbl values (event_seq.nextval, ?, ?,'admin',default, default, ?, ?, ?)";
		//회원 테스트할 때 작성자를 세션값으로 변경필요
		Object[] params = {e.getEventTitle(), e.getEventContent(), e.getStartDate(),e.getEndDate(),e.getThumbnail()};
		int result = jdbc.update(query, params);
		return result;
	}

	public int getEventNo() {
		String query = "select max(event_no) from event_tbl";
		int eventNo = jdbc.queryForObject(query, Integer.class); 
		return eventNo;
	}

	public int insertEventFile(EventFile file) {
		String query = "insert into event_file values(event_file_seq.nextval,?,?,?)";
		Object[] params = {file.getEventNo(),file.getFilename(),file.getFilepath()};
		int result = jdbc.update(query,params);//parent key not found의 오류났을 때 찾아올 곳 : 외래키의 부모테이블(방금만들어져서~)에 데이터가 없어서 발생했음
		return result;
	}

	public List selectEventList() {
		String query = "select * from event_tbl order by 1 desc";
		List list = jdbc.query(query,eventRowMapper);
		return list;
	}

	public Event selectOneEvent(int eventNo) {
		String query = "select * from event_tbl where event_no = ?";
		List list = jdbc.query(query, eventRowMapper,eventNo);
			return (Event)list.get(0);			
	}

	public int updateEvent(Event e) {
		String query = "update event_tbl set event_title = ?, event_content = ?, event_status = ?, start_date = ?, end_date = ?, thumbnail = ? where event_no=?";
		Object[] params = {e.getEventTitle(),e.getEventContent(),e.getEventStatus(), e.getStartDate(), e.getEndDate(), e.getThumbnail(),e.getEventNo()};
		int result = jdbc.update(query,params);
		return result;
	}

	public int deleteEvent(int eventNo) {
		String query = "delete from event_tbl where event_no = ?";
		Object[] params = {eventNo};
		int result = jdbc.update(query, params);
		return result;
	}
}
