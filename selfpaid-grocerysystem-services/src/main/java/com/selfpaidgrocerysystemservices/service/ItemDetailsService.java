package com.selfpaidgrocerysystemservices.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public interface ItemDetailsService {
	
	public JSONObject getItemDetailsFromDB(String itemName);
	
	public JSONObject postItemDetailsToDB(String itemsDetails);

}
