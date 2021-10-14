package com.ssafy.guestbook.model.service;

import com.ssafy.guestbook.model.MemberDto;

public interface MemberService {

	int idCheck(String id) throws Exception;
	void registerMember(MemberDto memberDto) throws Exception;
	MemberDto login(String id, String pass) throws Exception;
	
//	MemberDto getMember(String id);
//	void updateMember(MemberDto memberDto);
//	void deleteMember(String id);
}
