package com.selfpaidgrocerysystemservices.dto;

public class Item {

	private String name;
	private double price;
	private double weight;

	public Item() {
		//no-arg constructor
	}

	public Item(String name, double price, double weight) {
		this.name = name;
		this.price = price;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

}
