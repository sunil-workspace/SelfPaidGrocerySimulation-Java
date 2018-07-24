package com.selfpaidgrocerysystemservices.dto;

public class MongoMember {

	private String _id;
	private String member_name;
	private String membership_inception_date;
	private String status;

	public MongoMember() {

	}

	public MongoMember(String id, String member_name, String membership_inception_date, String status) {
		super();
		this._id = id;
		this.member_name = member_name;
		this.membership_inception_date = membership_inception_date;
		this.status = status;
	}

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMembership_inception_date() {
		return membership_inception_date;
	}

	public void setMembership_inception_date(String membership_inception_date) {
		this.membership_inception_date = membership_inception_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	

}
