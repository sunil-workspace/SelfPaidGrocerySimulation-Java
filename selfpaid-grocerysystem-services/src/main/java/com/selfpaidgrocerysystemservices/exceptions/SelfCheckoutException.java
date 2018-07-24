package com.selfpaidgrocerysystemservices.exceptions;

public class SelfCheckoutException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String errorMsg;

	public SelfCheckoutException(String msg){
		this.errorMsg = msg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
}
