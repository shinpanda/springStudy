package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeView;

public class MyBatisNoticeDao implements NoticeDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<NoticeView> getList(int page,String field, String query) {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class); //매퍼 객체
		List<NoticeView> list = noticeDao.getList(page, field, query); // xml에 구현된 getList 메소드를 가져오는 것.
		return list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public NoticeView get(String id) {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		NoticeView noticeView = noticeDao.get(id);
		return noticeView;
	}

	@Override
	public int update(String id, String title, String content) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public NoticeView getPrev(String id) {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		NoticeView noticeView = noticeDao.getPrev(id);
		return noticeView;
	}

	@Override
	public NoticeView getNext(String id) {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		NoticeView noticeView = noticeDao.getNext(id);
		return noticeView;
	}

	@Override
	public int insert(String title, String content, String writerId) {
		return insert(new Notice(title, content, writerId));
	}

	@Override
	public int insert(Notice notice) {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		return noticeDao.insert(notice);
		
	}

	@Override
	public String getNextId() {
		NoticeDao noticeDao = sqlSession.getMapper(NoticeDao.class);
		return noticeDao.getNextId();
		
	}


}
