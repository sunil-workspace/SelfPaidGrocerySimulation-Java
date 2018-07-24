package com.selfpaidgrocerysystemservices.data.mongo.repo;

import com.selfpaidgrocerysystemservices.dto.Payment;

public interface PaymentMongoRepository {

	public void savePaymentDetails(Payment paymentDetails);

}
