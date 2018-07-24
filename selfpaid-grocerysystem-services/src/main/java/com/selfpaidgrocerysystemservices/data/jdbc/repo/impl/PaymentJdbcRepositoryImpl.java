package com.selfpaidgrocerysystemservices.data.jdbc.repo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.selfpaidgrocerysystemservices.data.jdbc.repo.PaymentJdbcRepository;
import com.selfpaidgrocerysystemservices.dto.Payment;

@Repository
public class PaymentJdbcRepositoryImpl implements PaymentJdbcRepository {

	private static Logger logger = LoggerFactory.getLogger(PaymentJdbcRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean postPaymentDetails(Payment payment) {

		boolean isInserted = false;
		try {
			String insertSql = "INSERT INTO PAYMENT_DETAILS (MEMBER_ID, NAME_ON_CARD, CARD_NUMBER, CARD_EXPIRY_DATE, SECURITY_CODE, PAYMENT_MODE, PAYMENT_DATE) VALUES (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(insertSql, payment.getMemberId(), payment.getNameOnCard(), payment.getCardNumber(), payment.getCardExpiryDate(), payment.getSecurityCode(), payment.getPaymentMode(), payment.getPaymentDate());
			isInserted = true;
		} catch(Exception e) {
			e.printStackTrace();
			logger.info("Exception occured in PaymentJdbcRepositoryImpl:postPaymentDetails method");
			return isInserted;
		}
		return isInserted;
	}

}
