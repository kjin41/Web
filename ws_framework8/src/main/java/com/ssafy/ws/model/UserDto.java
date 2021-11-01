package com.ssafy.ws.model;

public class UserDto {
	private String id;
	private String name;
	private String pass;
	private String rec_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRec_id() {
		return rec_id;
	}

	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}

	@Override
	public String toString() {
		return this.id + "\t" + this.name + "\t" + this.pass + "\t" + this.rec_id;
	}
	
	

}
