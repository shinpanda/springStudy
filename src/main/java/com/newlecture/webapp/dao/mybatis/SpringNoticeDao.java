/*package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.NoticeView;

public class SpringNoticeDao implements NoticeDao {
	@Autowired
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	

	@Override
	public List<NoticeView> getList(int page, String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NoticeView get(String no) {
		String sql = "select * from NoticeView where id=?";
		
		JdbcTemplate jdbcTemplate  = new JdbcTemplate(dataSource);
		jdbcTemplate.queryForObject(sql, new Object[] {}, NoticeView.class);
		
		return null;
	}

	@Override
	public int update(String id, String title, String content, String fileName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(String title, String content, String fileName) {
		// TODO Auto-generated method stub
		return 0;
	}

}
*/