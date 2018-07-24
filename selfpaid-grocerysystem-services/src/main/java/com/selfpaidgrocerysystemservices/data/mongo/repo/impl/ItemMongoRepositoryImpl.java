package com.selfpaidgrocerysystemservices.data.mongo.repo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.selfpaidgrocerysystemservices.data.mongo.repo.ItemMongoRepository;
import com.selfpaidgrocerysystemservices.dto.Item;
import com.selfpaidgrocerysystemservices.dto.ItemSelected;

@Repository
public class ItemMongoRepositoryImpl implements ItemMongoRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * This method will take itemName and gets all the Item details
	 */
	@Override
	public Item findItemDetails(final String itemName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("item").is(itemName));
		Item item = mongoTemplate.findOne(query, Item.class, "items_price");
		return item;	
	}

	@Override
	public void saveItemDetails(final List<ItemSelected> itemsSelectedList) {
		try {
			mongoTemplate.save(itemsSelectedList, "items_selected");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
