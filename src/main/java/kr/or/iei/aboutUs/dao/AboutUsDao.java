package kr.or.iei.aboutUs.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.aboutUs.vo.StoreRowMapper;

@Repository
public class AboutUsDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Autowired
	private StoreRowMapper storeRowMapper;

	public List selectAllStore() {
		String query = "select * from store order by store_no";
		List storeList = jdbc.query(query, storeRowMapper);
		return storeList;
	}	
}
