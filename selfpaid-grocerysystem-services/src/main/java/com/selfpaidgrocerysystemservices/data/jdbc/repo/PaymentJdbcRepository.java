package com.selfpaidgrocerysystemservices.data.jdbc.repo;

import org.springframework.stereotype.Component;

import com.selfpaidgrocerysystemservices.dto.Payment;

@Component
public interface PaymentJdbcRepository {
	
	public boolean postPaymentDetails(Payment paymentDetails);

}
