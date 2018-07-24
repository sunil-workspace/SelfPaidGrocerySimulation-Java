package com.selfpaidgrocerysystemservices.data.jdbc.repo;

import java.util.List;

import org.springframework.stereotype.Component;

import com.selfpaidgrocerysystemservices.dto.Item;
import com.selfpaidgrocerysystemservices.dto.ItemSelected;

@Component
public interface ItemJdbcRepository {
	
	public List<Item> getItemDetails(String itemName);
	public boolean postItemDetails(List<ItemSelected> itemsSelectedList, String memberId);

}
