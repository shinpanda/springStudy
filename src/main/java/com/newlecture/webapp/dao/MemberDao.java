package com.newlecture.webapp.dao;

import com.newlecture.webapp.entity.Member;

public interface MemberDao {


	int insert(Member member);

	int insert(String id, String pwd, String name, String nicName, String phone, String email);

	Member get(String id);
	
	int pointUp(String id);
}
