package com.selfpaidgrocerysystemservices.data.mongo.repo;

import com.selfpaidgrocerysystemservices.dto.MongoMember;

public interface MembershipMongoRepository {

	public MongoMember findMember(String memberNumber);

}
