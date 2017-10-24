package com.newlecture.webapp.dao.spring;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeView;
import com.sun.media.jfxmediaimpl.platform.Platform;

public class SpringNoticeDao implements NoticeDao {
	@Autowired // 속성을 외부와 차단 하여 운영을 자유롭게 /고정되지 않고 자유롭게 사용하겠다.
	private JdbcTemplate template;

	/*
	 * Transaction 처리 방법 1 TransactonManager를 직접 사용하는 방법
	 * 
	 * @Autowired private PlatformTransactionManager transactionManager;
	 */

	// Transaction 처리 방법 2
	/*@Autowired
	private TransactionTemplate transactionTemplate;*/
	/*
	 * @Autowired //고정된 약속 public void setTemplate(JdbcTemplate template) {
	 * this.template = template; }
	 */

	@Override
	public List<NoticeView> getList(int page, String field, String query) {
		String sql = "select * from NoticeView where " + field + " like ?  order by regDate DESC limit ?, 10";
		int index = (page - 1) * 10;

		// List<NoticeView> list = template.queryForList(sql, new Object[]
		// {String.format("%%%s%%", query), index}, NoticeView.class); -> 단순 자료형일 때 사용가능

		List<NoticeView> list = template.query(sql, new Object[] { String.format("%%%s%%", query), index },
				BeanPropertyRowMapper.newInstance(NoticeView.class));
		// List<NoticeView> list = template.query(sql, new Object[] {"%"+query+"%",
		// index }, BeanPropertyRowMapper.newInstance(NoticeView.class));
		return list;
	}

	@Override
	public int getCount() {
		String sql = "select count(id) `count` from NoticeView";

		// //int count = template.queryForObject(sql,
		// BeanPropertyRowMapper.newInstance(Integer.class));
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
		NoticeView notice = template.queryForObject(sql, new Object[] { id }, new RowMapper<NoticeView>() {

			@Override
			public NoticeView mapRow(ResultSet rs, int rowNum) throws SQLException {
				NoticeView notice = new NoticeView();
				notice.setId(rs.getString("id"));
				notice.setTitle(rs.getString("title"));
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
	public int update(String id, String title, String content) {
		String sql = "update Notice set title = ?, content = ? where id = ?";
		int result = template.update(sql, title, content, id);

		/*
		 * int result = template.update(sql, new PreparedStatementSetter() {
		 * 
		 * @Override public void setValues(PreparedStatement st) throws SQLException {
		 * st.setString(1, title); st.setString(2, content); st.setString(3, id); } });
		 */
		return result;
	}

	@Override
	public NoticeView getPrev(String id) {
		String sql = "SELECT * FROM NoticeView where id < cast(? as unsigned) order by regDate DESC limit 1";
		try {
			NoticeView notice = template.queryForObject(sql, new Object[] { id },
					BeanPropertyRowMapper.newInstance(NoticeView.class));
			return notice;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public NoticeView getNext(String id) {
		String sql = "SELECT * FROM NoticeView where id > cast(? as unsigned) order by regDate ASC limit 1";
		try {
			NoticeView notice = template.queryForObject(sql, new Object[] { id },
					BeanPropertyRowMapper.newInstance(NoticeView.class));
			return notice;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public int insert(String title, String content, String writerId) {

		return insert(new Notice(title, content, writerId));
	}

	//Transaction 처리 방법 3과 4번(@Transactional)
	@Override
	//@Transactional
	public int insert(Notice notice) {
		String sql = "insert into Notice(id, title, content, writerId) values (?, ?, ?, ?)";
		String sql1 = "update Member set point = point + 1 where id=?";
		
		int result = 0;
		result = template.update(sql, getNextId(), notice.getTitle(), notice.getContent(), notice.getWriterId());

		result += template.update(sql1, notice.getWriterId());
		
		return result;
	}
	/*@Override
	public int insert(Notice notice) {

		String sql = "insert into Notice(id, title, content, writerId) values (?, ?, ?, ?)";
		String sql1 = "update Member set point = point + 1 where id=?";
		
		int result = 0;
		result = (int)transactionTemplate.execute(new TransactionCallbackWithoutResult() { // 결과값이 없으므로
			//거의 안씀
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				template.update(sql, getNextId(), notice.getTitle(), notice.getContent(), notice.getWriterId());

				 template.update(sql1, notice.getWriterId());
			}
		});

		return result;
	}*/
/*	Transaction 처리 방법 2
 *  TransactionTemplate을 사용하는 방법
 * @Override
	public int insert(Notice notice) {

		String sql = "insert into Notice(id, title, content, writerId) values (?, ?, ?, ?)";
		String sql1 = "update Member set point = point + 1 where id=?";
		
		int result = 0;
		result = (int)transactionTemplate.execute(new TransactionCallbackWithoutResult() { // 결과값이 없으므로
			//거의 안씀
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				template.update(sql, getNextId(), notice.getTitle(), notice.getContent(), notice.getWriterId());

				 template.update(sql1, notice.getWriterId());
			}
		});

		return result;
	}
*/
	/*
	 * Transaction 처리 방법 1 TransactonManager를 직접 사용하는 방법
	 * 
	 * @Override public int insert(Notice notice) {
	 * 
	 * String sql = "insert into Notice(id, title, content, writerId)" +
	 * "values ((select IFNULL(max(cast(id as unsigned)), 0)+1 from Notice n), ?, ?, ?)"
	 * ;
	 * 
	 * String sql =
	 * "insert into Notice(id, title, content, writerId) values (?, ?, ?, ?)";
	 * String sql1 = "update Member set point = point + 1 where id=?";
	 * DefaultTransactionDefinition def = new DefaultTransactionDefinition(); //
	 * 트랜잭션 정의 TransactionStatus state = transactionManager.getTransaction(def); //
	 * 상태값 try { int result = template.update(sql, getNextId(), notice.getTitle(),
	 * notice.getContent(), notice.getWriterId());
	 * 
	 * result += template.update(sql1, notice.getWriterId());
	 * transactionManager.commit(state); return result; } catch (Exception e) {
	 * transactionManager.rollback(state); throw e; } }
	 */

	@Override
	public String getNextId() {
		String sql = "select IFNULL(max(cast(id as unsigned)), 0)+1 from Notice";

		String nextId = template.queryForObject(sql, String.class);
		return nextId;
	}

	
}
