package com.ssafy.guestbook.model.dao;

import com.ssafy.guestbook.model.MemberDto;

public interface MemberDao {
	int idCheck(String id) throws Exception;
	void registerMember(MemberDto memberDto) throws Exception;
	MemberDto login(String id, String pass) throws Exception;
	
//	MemberDto getMember(String id);
//	void updateMember(MemberDto memberDto);
//	void deleteMember(String id);
}
