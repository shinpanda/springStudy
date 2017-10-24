package com.newlecture.webapp.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mysql.jdbc.Buffer;
import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.dao.NoticeFileDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeFile;
import com.newlecture.webapp.service.admin.BoardService;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {
/*
	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private NoticeFileDao noticeFileDao;
	
	@Autowired
	private MemberDao memberDao;*/
	
	@Autowired
	private BoardService service;

	@RequestMapping("notice")
	public String notice(@RequestParam(value = "p", defaultValue = "1") Integer page,
			@RequestParam(value = "f", defaultValue = "title") String field,
			@RequestParam(value = "q", defaultValue = "") String query, Model model) {

		//model.addAttribute("list", noticeDao.getList(page, field, query));
		//model.addAttribute("list", service.getNoticeList(page, field, query));
		model.addAttribute("list", service.getNoticeList());
		return "admin.board.notice.list";
	}
	@RequestMapping("notice")
	public String notice(Model model) {

		//model.addAttribute("list", noticeDao.getList(page, field, query));
		//model.addAttribute("list", service.getNoticeList(page, field, query));
		model.addAttribute("list", service.getNoticeList());
		return "admin.board.notice.list";
	}
	@RequestMapping("notice")
	public String notice(@RequestParam(value = "f", defaultValue = "title") String field,
			@RequestParam(value = "q", defaultValue = "") String query, Model model) {

		//model.addAttribute("list", noticeDao.getList(page, field, query));
		//model.addAttribute("list", service.getNoticeList(page, field, query));
		model.addAttribute("list", service.getNoticeList());
		return "admin.board.notice.list";
	}


	@RequestMapping("notice/{id}")
	public String noticeDetail(@PathVariable("id") String id, Model model) {

/*		model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));
*/
		model.addAttribute("n", service.getNotice(id));
		model.addAttribute("prev", service.getNoticePrev(id));
		model.addAttribute("next", service.getNoticeNext(id));

		return "admin.board.notice.detail";
	}

	@RequestMapping(value = "notice/edit", method = RequestMethod.GET)
	public String noticeEdit(@RequestParam(value = "id", defaultValue = "1") String id, Model model) {
		//model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("n", service.getNotice(id)); 
		return "admin.board.notice.edit";
	}

/*	@RequestMapping(value = "notice/edit", method = RequestMethod.POST)
	public String noticeEdit(Notice notice, String aa, MultipartFile[] file) {

		String nextId = noticeDao.getNextId();
		int row = 0;
		notice.setWriterId("newlec");
		row = noticeDao.update(notice.getId(), notice.getTitle(), notice.getContent());
		
		return "redirect: ../notice";
	}
*/
	@RequestMapping(value = "notice/edit", method = RequestMethod.POST)
	public String noticeEdit(String id, String title, String content, String aa, MultipartFile[] file) {

		int row = 0;
		
		//row = noticeDao.update(notice.getId(), notice.getTitle(), notice.getContent());
		//row = noticeDao.update(id, title, content);
		row = service.update(id, title, content);
		
		return "redirect: ../notice";
	}

	@RequestMapping(value = "notice/reg", method = RequestMethod.GET)
	public String noticeReg() {
		return "admin.board.notice.reg";
	}

	@RequestMapping(value = "notice/reg", method = RequestMethod.POST)
	public String noticeReg(Notice notice, String aa, MultipartFile[] file/* MultipartFile file */,
			HttpServletRequest request, Principal principal/*
										 * @RequestParam(defaultValue="") String title,
										 * 
										 * @RequestParam(defaultValue="") String content
										 */) throws IOException {
		// title = new String(title.getBytes("ISO-8859-1"), "UTF-8"); -> ���Ϳ��� ó��

		//String nextId = noticeDao.getNextId();
		String nextId = service.getNoticeNextId();
		
		// System.out.println("NextId: "+ nextId);
		int row = 0;
		// String writerId = "newlec";
		notice.setWriterId("newlec");
		
		// 업무명
		// row = noticeDao.insert(title, content, writerId);
		// row = noticeDao.insert(new Notice(title, content, writerId));
		//row = noticeDao.insert(notice);
		row = service.insertAndPointUp(notice);
		
		/*memberDao.pointUp(principal.getName());*/
		

		System.out.println(file.length);

		// if(!file.isEmpty()) {
		for (MultipartFile ff : file) {
			if (!ff.isEmpty()) {
				// System.out.println(file.length);
				// for(int i=0; i<file.length; i++) {
				// 날짜 얻는 방법1
				Date curDate = new Date();
				// curDate.getYear()

				// 날짜 얻는 방법2
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);

				/*
				 * // 날짜 얻는 방법3 SimpleDateFormat fmt = new SimpleDateFormat("YYYY"); String
				 * year2 = fmt.format(curDate);
				 */

				ServletContext ctx = request.getServletContext();
				String path = ctx.getRealPath(String.format("/resource/customer/notice/%d/%s", year, nextId));
				System.out.println(path);
				File f = new File(path); // ����

				if (!f.exists()) {
					if (!f.mkdirs()) // true, false 반환
						System.out.println("디렉토리를 생성할 수 없습니다.");
				}

				// String fileName= file[i].getOriginalFilename();
				String fileName = ff.getOriginalFilename();
				// File f2 = new File(path, file.getOriginalFilename());
				path += File.separator + fileName;
				File f2 = new File(path);
				// InputStream fis = file[i].getInputStream();
				InputStream fis = ff.getInputStream();
				OutputStream fos = new FileOutputStream(f2);

				byte[] buf = new byte[1024];

				int size = 0;

				while ((size = fis.read(buf)) > 0)
					fos.write(buf, 0, size);

				fis.close();
				fos.close();
				//int row2 = noticeFileDao.insert(new NoticeFile(null, fileName, nextId));
				int row2 = service.insertFile(new NoticeFile(null, fileName, nextId));
			}
		}

		// System.out.println(row); -> 1

		return "redirect: ../notice";
	}

}
