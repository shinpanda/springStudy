package com.newlecture.webapp.service.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.dao.NoticeFileDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeFile;
import com.newlecture.webapp.entity.NoticeView;

//BoardController를 위한 데이터 제공
public class BoardService {

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private NoticeFileDao noticeFileDao;
	
	@Autowired
	private MemberDao memberDao;
	
/*	public List<NoticeView> getNoticeList() {
		return getNoticeList(1, "title", "");
	}
	
	public List<NoticeView> getNoticeList(String field, String query) {
		return getNoticeList(1, field, query);
	}*/
	
	public List<NoticeView> getNoticeList(int page, String field, String query) {
		return noticeDao.getList(page, field, query);
	}
	
	@Transactional
	public int insertAndPointUp(Notice notice) {
		int result = noticeDao.insert(notice);
		result += memberDao.pointUp(notice.getWriterId());
		
		return result;
		
	}
	public NoticeView getNotice(String id) {
		
		return noticeDao.get(id);
	}
	public NoticeView getNoticePrev(String id) {
		
		return noticeDao.getPrev(id);
	}
	public NoticeView getNoticeNext(String id) {
		
		return noticeDao.getNext(id);
	}
	public int update(String id, String title, String content) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getNoticeNextId() {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertFile(NoticeFile noticeFile) {
		return noticeFileDao.insert(noticeFile);
	}

}
