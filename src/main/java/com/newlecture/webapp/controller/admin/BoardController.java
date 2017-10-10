package com.newlecture.webapp.controller.admin;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.entity.Notice;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("notice")
	public String notice(@RequestParam(value="p", defaultValue="1") Integer page, 
			@RequestParam(value="f", defaultValue="title") String field,
			@RequestParam(value="q", defaultValue="") String query,
			Model model) {
		
		
		model.addAttribute("list", noticeDao.getList(page, field, query));

		return "admin.board.notice.list";
	}
	
	@RequestMapping("notice/{id}")
	public String noticeDetail(@PathVariable("id") String id,Model model) {
		
		model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));
		
		
		return "admin.board.notice.detail";
	}
	
	@RequestMapping(value="notice/reg", method=RequestMethod.GET)
	public String noticeReg() {		
		return "admin.board.notice.reg";
	}
	
	@RequestMapping(value="notice/reg", method=RequestMethod.POST)
	public String noticeReg(@RequestParam(defaultValue="") String title,
			@RequestParam(defaultValue="") String content) throws UnsupportedEncodingException {
		//title = new String(title.getBytes("ISO-8859-1"), "UTF-8"); -> 필터에서 처리
		int row = 0;
		String writerId = "newlec";
		
		row = noticeDao.insert(title, content, writerId);
		//row = noticeDao.insert(new Notice(title, content, writerId));
		
		// System.out.println(row); -> 1
		
		return "redirect: ../notice";
	}
	
	
}
