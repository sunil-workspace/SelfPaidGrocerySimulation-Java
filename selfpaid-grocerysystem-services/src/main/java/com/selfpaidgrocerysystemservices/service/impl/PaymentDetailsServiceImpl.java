package com.selfpaidgrocerysystemservices.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfpaidgrocerysystemservices.data.jdbc.repo.PaymentJdbcRepository;
import com.selfpaidgrocerysystemservices.data.mongo.repo.PaymentMongoRepository;
import com.selfpaidgrocerysystemservices.dto.Payment;
import com.selfpaidgrocerysystemservices.exceptions.SelfCheckoutException;
import com.selfpaidgrocerysystemservices.service.PaymentDetailsService;

@Service
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

	private static Logger logger = LoggerFactory.getLogger(PaymentDetailsServiceImpl.class);

	@Autowired
	PaymentJdbcRepository paymentJdbcRepository;

	@Autowired
	PaymentMongoRepository paymentMongoRepository;

	@Override
	public JSONObject postPaymentDetailsToDB(Payment payment) {
		JSONObject responseJsonObj = new JSONObject();
		boolean isInserted = false;
		try {			
			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddhhmmssMs");
			String currentDateTime = dateFormat.format(currentDate);
			payment.setMemberId("Guest"+currentDateTime);
			//payment.setPaymentDate(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
			payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
			isInserted = paymentJdbcRepository.postPaymentDetails(payment);
			responseJsonObj.put("isCardDetailsInserted", isInserted);
			logger.info("Payment details are inserted into Database");
		} catch (SelfCheckoutException | JSONException e) {
			e.printStackTrace();
			logger.info("Error occured in PaymentDetailsServiceImpl:postPaymentDetailsToDB method");
		}	
		return responseJsonObj;
	}

}
