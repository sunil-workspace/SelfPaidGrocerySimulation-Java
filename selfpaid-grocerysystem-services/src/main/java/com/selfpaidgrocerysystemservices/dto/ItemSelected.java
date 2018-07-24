package com.selfpaidgrocerysystemservices.dto;

import java.sql.Date;

public class ItemSelected {

	private String NAME;
	private double PRICE;
	private int QUANTITY;
	private double WEIGHT;
	private Date PURCHASED_DATE;
	private String MEMBER_ID;

	public ItemSelected() {}

	public ItemSelected(String nAME, double pRICE, int qUANTITY, double wEIGHT, Date pURCHASED_DATE, String mEMBER_ID) {
		NAME = nAME;
		PRICE = pRICE;
		QUANTITY = qUANTITY;
		WEIGHT = wEIGHT;
		PURCHASED_DATE = pURCHASED_DATE;
		MEMBER_ID = mEMBER_ID;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public double getPRICE() {
		return PRICE;
	}

	public void setPRICE(double pRICE) {
		PRICE = pRICE;
	}

	public int getQUANTITY() {
		return QUANTITY;
	}

	public void setQUANTITY(int qUANTITY) {
		QUANTITY = qUANTITY;
	}

	public double getWEIGHT() {
		return WEIGHT;
	}

	public void setWEIGHT(double wEIGHT) {
		WEIGHT = wEIGHT;
	}

	public Date getPURCHASED_DATE() {
		return PURCHASED_DATE;
	}

	public void setPURCHASED_DATE(Date pURCHASED_DATE) {
		PURCHASED_DATE = pURCHASED_DATE;
	}

	public String getMEMBER_ID() {
		return MEMBER_ID;
	}

	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}

	@Override
	public String toString() {
		return "ItemSelected [NAME=" + NAME + ", PRICE=" + PRICE + ", QUANTITY=" + QUANTITY + ", WEIGHT=" + WEIGHT
				+ ", PURCHASED_DATE=" + PURCHASED_DATE + ", MEMBER_ID=" + MEMBER_ID + "]";
	}


}


