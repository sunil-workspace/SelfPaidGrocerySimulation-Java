package com.selfpaidgrocerysystemservices.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.selfpaidgrocerysystemservices.dto.ItemSelected;

@Service
public interface ItemDetailsService {

	public JSONObject getItemDetailsFromDB(String itemName);
	public JSONObject postItemDetailsToDB(String itemsDetails);
	public List<ItemSelected> getItemsPurchased();

}
