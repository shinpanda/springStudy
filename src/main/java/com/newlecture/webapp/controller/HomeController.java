package com.newlecture.webapp.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.newlecture.webapp.entity.NoticeFile;

@Controller
@RequestMapping("/*")
public class HomeController {

	@RequestMapping("index")
	public String index() {
		return "home.index";
	}

	@RequestMapping("upload")
	@ResponseBody
	public String upload(String title, MultipartFile file, HttpServletRequest request) throws IOException {

		System.out.println(title);
		System.out.println(file.getOriginalFilename());

		if (!file.isEmpty()) {

			ServletContext ctx = request.getServletContext();
			String path = ctx.getRealPath("/resource/upload");

			String fileName = file.getOriginalFilename();
			path += File.separator + fileName;
			File f = new File(path);
			
			InputStream fis = file.getInputStream();
			OutputStream fos = new FileOutputStream(f);

			byte[] buf = new byte[1024];

			int size = 0;

			while ((size = fis.read(buf)) > 0)
				fos.write(buf, 0, size);

			fis.close();
			fos.close();
		}

		return "aa";
	}
}
