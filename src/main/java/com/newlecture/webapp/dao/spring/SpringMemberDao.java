package com.newlecture.webapp.dao.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.entity.Member;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

public class SpringMemberDao implements MemberDao {

	@Autowired
	private JdbcTemplate template;
	
	@Override
	public int insert(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(String id, String pwd, String name, String nicName, String phone, String email) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Member get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int pointUp(String id) {
		String sql ="update Member set point = point + 10 where id=?";
		
		return template.update(sql, id);
	}

}
