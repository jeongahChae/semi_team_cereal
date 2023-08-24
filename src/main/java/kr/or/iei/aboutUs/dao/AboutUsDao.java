package kr.or.iei.aboutUs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.aboutUs.vo.News;
import kr.or.iei.aboutUs.vo.NewsRowMapper;
import kr.or.iei.aboutUs.vo.StoreRowMapper;

@Repository
public class AboutUsDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private StoreRowMapper storeRowMapper;
	@Autowired
	private NewsRowMapper newsRowMapper;

	public List selectAllStore() {
		String query = "select * from store order by store_no";
		List storeList = jdbc.query(query, storeRowMapper);
		return storeList;
	}

	public List selectNewsList(int start, int end) {
		String query = "select * from (select rownum as rnum, n.* from (select * from news_tbl order by 1 desc)n)where rnum between ? and ?";
		List list = jdbc.query(query, newsRowMapper, start, end);
		return list;
	}

	public int selectNewsTotalCount() {
		String query = "select count(*) from news_tbl";
		int totalCount = jdbc.queryForObject(query, Integer.class);
		return totalCount;
	}

	public News selectOneNotice(int newsNo) {
		String query = "select * from news_tbl where news_no=?";
		List list = jdbc.query(query, newsRowMapper, newsNo);
		return (News)list.get(0);
	}

	public int insertNews(News n) {
		String query = "insert into news_tbl values (news_seq.nextval, ?, ?, ?, default)";
		Object[] params = {n.getNewsTitle(), n.getNewsContent(), n.getPress()};
		int result = jdbc.update(query, params);
		return result;
	}	
}
