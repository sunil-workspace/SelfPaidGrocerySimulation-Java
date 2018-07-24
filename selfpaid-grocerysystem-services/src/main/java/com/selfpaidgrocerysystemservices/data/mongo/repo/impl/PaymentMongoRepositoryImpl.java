package com.selfpaidgrocerysystemservices.data.mongo.repo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.selfpaidgrocerysystemservices.data.mongo.repo.PaymentMongoRepository;
import com.selfpaidgrocerysystemservices.dto.Payment;

@Repository
public class PaymentMongoRepositoryImpl implements PaymentMongoRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void savePaymentDetails(Payment paymentDetails) {
		try {
			mongoTemplate.save(paymentDetails, "payment_details");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
