package com.selfpaidgrocerysystemservices.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.selfpaidgrocerysystemservices.dto.Payment;

@Service
public interface PaymentDetailsService {
	
	public JSONObject postPaymentDetailsToDB(Payment payment);

}
