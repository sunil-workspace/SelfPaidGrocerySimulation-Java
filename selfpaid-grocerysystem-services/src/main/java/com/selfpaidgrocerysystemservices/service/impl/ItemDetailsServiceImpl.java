package com.selfpaidgrocerysystemservices.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selfpaidgrocerysystemservices.cache.service.CacheService;
import com.selfpaidgrocerysystemservices.data.jdbc.repo.ItemJdbcRepository;
import com.selfpaidgrocerysystemservices.data.mongo.repo.ItemMongoRepository;
import com.selfpaidgrocerysystemservices.dto.Item;
import com.selfpaidgrocerysystemservices.dto.ItemSelected;
import com.selfpaidgrocerysystemservices.exceptions.SelfCheckoutException;
import com.selfpaidgrocerysystemservices.service.ItemDetailsService;

@Service
public class ItemDetailsServiceImpl implements ItemDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(ItemDetailsServiceImpl.class);

	@Autowired
	ItemJdbcRepository itemJdbcRepository;

	@Autowired
	ItemMongoRepository itemMongoRepository;

	@Autowired
	private CacheService cacheService;

	/*@Override
	public Item getItemDetailsFromDB(String itemName) {

		List<Item> items = itemRepository.getItemDetails(itemName);

		for(Item item : items) {
			if(item.getITEM_NAME().equals(itemName)) {
				return item;
			}
		}
		return null;
	}*/

	@Override
	public JSONObject getItemDetailsFromDB(String itemName) {
		JSONObject jsonObj = new JSONObject();
		try {
			List<Item> items = itemJdbcRepository.getItemDetails(itemName);

			if(items.size()>0) {
				Item item = items.get(0);

				jsonObj.put("NAME", item.getName());
				jsonObj.put("PRICE", item.getPrice());
				jsonObj.put("QUANTITY", 1);
				jsonObj.put("WEIGHT", item.getWeight());
			}
		} catch(SelfCheckoutException | JSONException e) {
			e.printStackTrace();
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return jsonObj;
	}

	@Override
	public JSONObject postItemDetailsToDB(String itemsDetails) {
		JSONObject responseJsonObj = new JSONObject();
		boolean isInserted = false;
		String memberId = "";
		try {
			JSONArray jsonArr = new JSONArray(itemsDetails);
			//JSONArray jsonArr = (JSONArray) jsonObj.get("itemDetails");
			List<ItemSelected> itemsSelectedList = new ArrayList<ItemSelected>();

			/*CacheMember cacheMember = cacheService.getMemberIdDetails();
			if("Y".equals(cacheMember.getIsMember())) {
				memberId = cacheMember.getMemberId();
			}*/
			if(jsonArr.length()>1) {
				for(int i=0; i<jsonArr.length()-1; i++) {
					JSONObject itemDetails = (JSONObject) jsonArr.get(i);

					ItemSelected itemsSelected = new ItemSelected();
					itemsSelected.setNAME(itemDetails.getString("NAME"));

					Double price = (Double) itemDetails.get("PRICE");
					itemsSelected.setPRICE(price);

					int quantity =  (int) itemDetails.get("QUANTITY");;
					itemsSelected.setQUANTITY(quantity);

					Double weight = (Double) itemDetails.get("WEIGHT");
					itemsSelected.setWEIGHT(weight);

					java.sql.Date currentSqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					itemsSelected.setPURCHASED_DATE(currentSqlDate);

					//itemsSelected.setMEMBER_ID(String.valueOf(memberId));

					itemsSelectedList.add(itemsSelected);
				}
				
				JSONObject memberIdJson = (JSONObject) jsonArr.get(jsonArr.length()-1);
				memberId = String.valueOf(memberIdJson.get("membershipId")) ;
				logger.info("MembershipId: " + memberIdJson);
			}


			isInserted = itemJdbcRepository.postItemDetails(itemsSelectedList, memberId);
			responseJsonObj.put("isInserted", isInserted);

		} catch (SelfCheckoutException | JSONException e) {
			e.printStackTrace();
		}	
		return responseJsonObj;
	}

}
