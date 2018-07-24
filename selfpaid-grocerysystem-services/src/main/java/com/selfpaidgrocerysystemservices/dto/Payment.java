package com.selfpaidgrocerysystemservices.dto;

import java.sql.Timestamp;

public class Payment {

	private String memberId;
	private String nameOnCard;
	private long cardNumber;
	private String cardExpiryDate;
	private int securityCode;
	private String paymentMode;
	private Timestamp paymentDate;

	public Payment() {
		//no-arg constructor
	}

	public Payment(String memberId, String nameOnCard, long cardNumber, String cardExpiryDate, int securityCode,
			String paymentMode, Timestamp paymentDate) {
		this.memberId = memberId;
		this.nameOnCard = nameOnCard;
		this.cardNumber = cardNumber;
		this.cardExpiryDate = cardExpiryDate;
		this.securityCode = securityCode;
		this.paymentMode = paymentMode;
		this.paymentDate = paymentDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardExpiryDate() {
		return cardExpiryDate;
	}

	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}

	public int getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(int securityCode) {
		this.securityCode = securityCode;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

}
