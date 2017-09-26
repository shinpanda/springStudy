package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.NoticeView;

public class MyBatisNoticeDao implements NoticeDao {

	@Override
	public List<NoticeView> getList(int page,String field, String query) {
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
		// TODO Auto-generated method stub
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
