package com.selfpaidgrocerysystemservices.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface ValidateMemberIdService {
	
	//public boolean verifyMemberId(int memberNumber);
	
	public JSONObject verifyMemberIdNew(int memberNumber);
	
	public JSONObject verifyMemberIdFromMongoDB(int memberNumber);

}
