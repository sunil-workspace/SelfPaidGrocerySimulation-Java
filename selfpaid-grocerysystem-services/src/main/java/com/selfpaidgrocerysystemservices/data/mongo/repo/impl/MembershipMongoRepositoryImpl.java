package com.selfpaidgrocerysystemservices.data.mongo.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.selfpaidgrocerysystemservices.data.mongo.repo.MembershipMongoRepository;
import com.selfpaidgrocerysystemservices.dto.MongoMember;

@Repository
public class MembershipMongoRepositoryImpl implements MembershipMongoRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public MongoMember findMember(String memberNumber) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(memberNumber));
		MongoMember member = mongoTemplate.findOne(query, MongoMember.class, "membership_details");
		return member;
	}

}
