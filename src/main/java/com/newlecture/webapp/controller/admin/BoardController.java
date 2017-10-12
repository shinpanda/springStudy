package com.newlecture.webapp.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import com.newlecture.webapp.dao.NoticeDao;
import com.newlecture.webapp.dao.NoticeFileDao;
import com.newlecture.webapp.entity.Notice;
import com.newlecture.webapp.entity.NoticeFile;

@Controller
@RequestMapping("/admin/board/*")
public class BoardController {

	@Autowired
	private NoticeDao noticeDao;
	
	@Autowired
	private NoticeFileDao noticeFileDao;

	@RequestMapping("notice")
	public String notice(@RequestParam(value = "p", defaultValue = "1") Integer page,
			@RequestParam(value = "f", defaultValue = "title") String field,
			@RequestParam(value = "q", defaultValue = "") String query, Model model) {

		model.addAttribute("list", noticeDao.getList(page, field, query));

		return "admin.board.notice.list";
	}

	@RequestMapping("notice/{id}")
	public String noticeDetail(@PathVariable("id") String id, Model model) {

		model.addAttribute("n", noticeDao.get(id));
		model.addAttribute("prev", noticeDao.getPrev(id));
		model.addAttribute("next", noticeDao.getNext(id));

		return "admin.board.notice.detail";
	}

	@RequestMapping(value = "notice/reg", method = RequestMethod.GET)
	public String noticeReg() {
		return "admin.board.notice.reg";
	}

	@RequestMapping(value = "notice/reg", method = RequestMethod.POST)
	public String noticeReg(Notice notice, String aa, MultipartFile file,
			HttpServletRequest request/*
										 * @RequestParam(defaultValue="") String title,
										 * 
										 * @RequestParam(defaultValue="") String content
										 */) throws IOException {
		// title = new String(title.getBytes("ISO-8859-1"), "UTF-8"); -> 필터에서 처리

		String nextId = noticeDao.getNextId();
		//System.out.println("NextId: "+ nextId);
		
		// 날짜 얻는 방법1
		Date curDate = new Date();
		//curDate.getYear()

		// 날짜 얻는 방법2
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		

		/*// 날짜 얻는 방법3
		SimpleDateFormat fmt = new SimpleDateFormat("YYYY");
		String year2 = fmt.format(curDate);*/

		
		ServletContext ctx = request.getServletContext();
		String path = ctx.getRealPath(String.format("/resource/customer/notice/%d/%s", year, nextId));
		System.out.println(path);
		File f = new File(path); // 폴더
		
		if(!f.exists()) {
			if(!f.mkdirs()) // true, false 반환
				System.out.println("디렉토리를 생성할 수 없습니다.");
		}
		
		String fileName= file.getOriginalFilename();
		//File f2 = new File(path, file.getOriginalFilename());
		path += File.separator+fileName;
		File f2 = new File(path);
		InputStream fis = file.getInputStream();
		OutputStream fos = new FileOutputStream(f2);
		
		byte[] buf = new byte[1024];
		
		int size = 0;
		
		while((size = fis.read(buf))>0)
			fos.write(buf, 0, size);
		
		fis.close();
		fos.close();
		
		int row = 0;
		// String writerId = "newlec";
		notice.setWriterId("newlec");

		// row = noticeDao.insert(title, content, writerId);
		// row = noticeDao.insert(new Notice(title, content, writerId));
		row = noticeDao.insert(notice);
		
		int row2 = noticeFileDao.insert(new NoticeFile(null, fileName, nextId));
		System.out.println(row2);
		// System.out.println(row); -> 1

		return "redirect: ../notice";
	}

}
