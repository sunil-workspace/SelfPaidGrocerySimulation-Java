package com.selfpaidgrocerysystemservices.dto;

import java.util.Date;

public class Member {

	private int MEMBERSHIP_ID;
	private String MEMBER_NAME;
	private Date MEMBER_INCEPTION_DT;
	private String STATUS;

	public Member() {
		this.MEMBERSHIP_ID = 100;
	}
	
	public Member(int mEMBERSHIP_ID, String mEMBERSHIP_NAME, Date mEMBER_INCEPTION_DT, String sTATUS) {
		MEMBERSHIP_ID = mEMBERSHIP_ID;
		MEMBER_NAME = mEMBERSHIP_NAME;
		MEMBER_INCEPTION_DT = mEMBER_INCEPTION_DT;
		STATUS = sTATUS;
	}

	public int getMEMBERSHIP_ID() {
		return MEMBERSHIP_ID;
	}
	public void setMEMBERSHIP_ID(int mEMBERSHIP_ID) {
		MEMBERSHIP_ID = mEMBERSHIP_ID;
	}
	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}
	public void setMEMBERSHIP_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}
	public Date getMEMBER_INCEPTION_DT() {
		return MEMBER_INCEPTION_DT;
	}
	public void setMEMBER_INCEPTION_DT(Date mEMBER_INCEPTION_DT) {
		MEMBER_INCEPTION_DT = mEMBER_INCEPTION_DT;
	}
	public String getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	@Override
	public String toString() {
		return "Member [MEMBERSHIP_ID=" + MEMBERSHIP_ID + ", MEMBERSHIP_NAME=" + MEMBER_NAME
				+ ", MEMBER_INCEPTION_DT=" + MEMBER_INCEPTION_DT + ", STATUS=" + STATUS + "]";
	}
	
	

}
