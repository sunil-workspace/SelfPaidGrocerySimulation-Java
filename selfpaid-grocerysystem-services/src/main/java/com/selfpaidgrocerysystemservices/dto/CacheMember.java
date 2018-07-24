package com.selfpaidgrocerysystemservices.dto;

import org.springframework.stereotype.Component;

@Component
public class CacheMember {
	
	private String isMember;
	private String memberId;
	
	public String getIsMember() {
		return isMember;
	}
	public void setIsMember(String isMember) {
		this.isMember = isMember;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
