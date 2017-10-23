package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlecture.webapp.dao.NoticeFileDao;
import com.newlecture.webapp.entity.NoticeFile;

public class MyBatisNoticeFileDao implements NoticeFileDao {
	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public List<NoticeFile> getListByNoticeId(String noticeId) {
		NoticeFileDao noticeFileDao = sqlSession.getMapper(NoticeFileDao.class); //매핑객체 구현
		List<NoticeFile> list = noticeFileDao.getListByNoticeId(noticeId); // xml�� ������ getList �޼ҵ带 �������� ��.
		return list;
	}

	@Override
	public int insert(NoticeFile noticeFile) {
		NoticeFileDao noticeFileDao = sqlSession.getMapper(NoticeFileDao.class);
		return noticeFileDao.insert(noticeFile);
	}

	@Override
	public int update(NoticeFile noticeFile) {
		NoticeFileDao noticeFileDao = sqlSession.getMapper(NoticeFileDao.class);
		return noticeFileDao.update(noticeFile);
	}

	@Override
	public int delete(String id) {
		NoticeFileDao noticeFileDao = sqlSession.getMapper(NoticeFileDao.class);
		return noticeFileDao.delete(id);
	}
	
	
}
