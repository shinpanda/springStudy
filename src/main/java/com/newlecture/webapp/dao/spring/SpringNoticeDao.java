package com.newlecture.webapp.dao.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeView;

public class SpringNoticeDao implements NoticeDao {

	private JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	@Override
	public List<NoticeView> getList(int page, String field, String query) {
		String sql = "select * from NoticeView where "+field+" like ?  order by regDate DESC limit ?, 10";
		int index = (page - 1) * 10;

		//List<NoticeView> list = template.queryForList(sql, new Object[] { index }, NoticeView.class);
		
		//List<NoticeView> list = template.queryForList(sql, new Object[] {String.format("%%%s%%", query), index}, NoticeView.class);
		
		List<NoticeView> list = template.query(sql, new Object[] {String.format("%%%s%%", query), index }, BeanPropertyRowMapper.newInstance(NoticeView.class));
		//List<NoticeView> list = template.query(sql, new Object[] {"%"+query+"%", index }, BeanPropertyRowMapper.newInstance(NoticeView.class));
		return list;
	}

	@Override
	public int getCount() {
		String sql="select count(id) `count` from NoticeView";
		
//		//int count = template.queryForObject(sql, BeanPropertyRowMapper.newInstance(Integer.class));
		int count = template.queryForObject(sql, Integer.class);
		return count;
	}

	@Override
	public NoticeView get(String id) {
		String sql = "select * from NoticeView where id = ?";

		/*
		 * NoticeView notice = template.queryForObject(sql, new Object[] {id},
		 * BeanPropertyRowMapper.newInstance(NoticeView.class));
		 */
		NoticeView notice = template.queryForObject(sql, new Object[] { id }, 
				new RowMapper<NoticeView>() {

					@Override
					public NoticeView mapRow(ResultSet rs, int rowNum) throws SQLException {
						NoticeView notice = new NoticeView();
						notice.setId(rs.getString("id"));
						notice.setTitle(rs.getString("title")+"¾Æ¾Æ");						
						notice.setWriterId(rs.getString("writerId"));
						notice.setContent(rs.getString("content").replaceAll("\n", "<br/>"));
						notice.setRegDate(rs.getDate("regDate"));
						notice.setHit(0);
						return notice;
					}

		});

		/*
		 * DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 * dataSource.setDriverClassName("com.mysql.jdbc.Driver"); dataSource.setUrl(
		 * "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&useSSL=false&characterEncoding=UTF-8"
		 * ); dataSource.setUsername("sist"); dataSource.setPassword("cclass");
		 */
		/*
		 * JdbcTemplate template = new JdbcTemplate();
		 * template.setDataSource(dataSource);
		 * 
		 * NoticeView notice = template.queryForObject(sql,
		 * BeanPropertyRowMapper.newInstance(NoticeView.class));
		 */

		return notice;
	}

	@Override
	public int update(String id, String title, String content, String fileName) {
		
		return 0;
	}

	@Override
	public NoticeView getPrev(String id) {
		String sql="SELECT * FROM NoticeView where id < cast(? as unsigned) order by regDate DESC limit 1";
		
		NoticeView notice = template.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(NoticeView.class));
		return notice;
	}

	@Override
	public NoticeView getNext(String id) {
		String sql="SELECT * FROM NoticeView where id > cast(? as unsigned) order by regDate ASC limit 1";
		
		NoticeView notice = template.queryForObject(sql, new Object[] {id}, BeanPropertyRowMapper.newInstance(NoticeView.class));
		return notice;
	}

	@Override
	public int insert(String title, String content, String writerId) {
		String sql = "insert into Notice(id, title, content, writerId)" + 
				"values (select IFNULL(max(cast(id as unsigned)), 0)+1 from Notice n, ?, ?, ?)";
		template.execute(sql);
		return 0;
	}

	@Override
	public int insert(Notice notice) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getNextId() {
		String sql = "select IFNULL(max(cast(id as unsigned)), 0)+1 from Notice";
		
		String nextId = template.queryForObject(sql, String.class);
		return null;
	}

}
