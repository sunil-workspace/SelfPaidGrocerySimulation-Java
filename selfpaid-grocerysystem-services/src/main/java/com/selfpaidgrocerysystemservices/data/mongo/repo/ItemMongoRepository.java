package com.selfpaidgrocerysystemservices.data.mongo.repo;

import java.util.List;

import com.selfpaidgrocerysystemservices.dto.Item;
import com.selfpaidgrocerysystemservices.dto.ItemSelected;

public interface ItemMongoRepository {// extends MongoRepository<Item, String>{

	public Item findItemDetails(String itemName);
	
	public void saveItemDetails(List<ItemSelected> itemsSelectedList);
	
}
