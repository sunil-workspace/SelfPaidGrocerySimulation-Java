package com.selfpaidgrocerysystemservices.service.impl;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfpaidgrocerysystemservices.cache.service.CacheService;
import com.selfpaidgrocerysystemservices.data.jdbc.repo.impl.MembershipJdbcRepositoryImpl;
import com.selfpaidgrocerysystemservices.data.mongo.repo.impl.MembershipMongoRepositoryImpl;
import com.selfpaidgrocerysystemservices.dto.Member;
import com.selfpaidgrocerysystemservices.dto.MongoMember;
import com.selfpaidgrocerysystemservices.service.ValidateMemberIdService;

@Service
public class ValidateMemberIdServiceImpl implements ValidateMemberIdService {

	private static Logger logger = LoggerFactory.getLogger(ValidateMemberIdServiceImpl.class);

	@Autowired
	MembershipJdbcRepositoryImpl membershipJdbcRepository;

	@Autowired
	MembershipMongoRepositoryImpl membershipMongoRepository;
	
	@Autowired
	private CacheService cacheService;

	/*@Override
	public boolean verifyMemberId(int memberNumber) {

		List<Member> list = membershipRepository.findAllMembers();

		list.forEach((member) ->{
			System.out.println(member.toString());
			if(member.getMEMBERSHIP_NUM() == memberNumber && member.getSTATUS().equals("ACTIVE")) {
				return true;
			}
		});
		return false;


		for(Member member: list) {
			System.out.println(member.toString());
			if(member.getMEMBERSHIP_NUM() == memberNumber && member.getSTATUS().equals("ACTIVE")) {
				return true;
			}
		}
		return false;
	}*/

	@Override
	public JSONObject verifyMemberIdNew(int memberNumber) {
		String isValid = "false";
		JSONObject jsonObj = new JSONObject();
		try {
			List<Member> members = membershipJdbcRepository.findMember(memberNumber);

			if(members.size()>0) {
				isValid = "true";
				jsonObj.put("membershipId", members.get(0).getMEMBERSHIP_ID());
				jsonObj.put("memberName", members.get(0).getMEMBER_NAME());
			}
			jsonObj.put("isValidMember", isValid);
			
			/*if("true".equals(isValid)) {
				cacheService.putMemberIdDetails("Y", String.valueOf(memberNumber));
			} else {
				cacheService.putMemberIdDetails("N", null);
			}*/
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in verifyMemberIdNew method " + e.getMessage());
		}
		return jsonObj;
	}

	@Override
	public JSONObject verifyMemberIdFromMongoDB(int memberNumber) {

		String isValid = "false";
		JSONObject jsonObj = new JSONObject();
		try {			
			MongoMember members = membershipMongoRepository.findMember(String.valueOf(memberNumber));

			if(members != null) {
				isValid = "true";
			}
			jsonObj.put("isValidMember", isValid);
		} catch(Exception e) {
			e.printStackTrace();
			logger.error("Exception occured in verifyMemberIdNew method " + e.getMessage());
		}
		return jsonObj;
	}



}
