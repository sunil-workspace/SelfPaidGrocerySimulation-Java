package com.selfpaidgrocerysystemservices.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.selfpaidgrocerysystemservices.dto.Payment;
import com.selfpaidgrocerysystemservices.exceptions.SelfCheckoutException;
import com.selfpaidgrocerysystemservices.service.GenerateReceiptService;
import com.selfpaidgrocerysystemservices.service.ItemDetailsService;
import com.selfpaidgrocerysystemservices.service.PaymentDetailsService;
import com.selfpaidgrocerysystemservices.service.ValidateMemberIdService;

@RestController
public class SelfCheckoutController {

	private static Logger logger = LoggerFactory.getLogger(SelfCheckoutController.class);

	@Autowired
	ValidateMemberIdService validateMemberIdService;

	@Autowired
	ItemDetailsService itemDetailsService;

	@Autowired
	PaymentDetailsService paymentDetailsService;

	@Autowired
	GenerateReceiptService generateReceiptService;

	@Value("${doFetchFromMongo}")
	private boolean doFetchFromMongo;

	@RequestMapping(method=RequestMethod.GET, value="/getItemDetails/{itemName}")
	public ResponseEntity<?> getItemDetails(@PathVariable("itemName") String itemName) {
		logger.info("Item Received: " + itemName);
		JSONObject jsonObj = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
			jsonObj = itemDetailsService.getItemDetailsFromDB(itemName);
			logger.info("Response Json: " + jsonObj);
		} catch (SelfCheckoutException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.OK);	
	}

	@RequestMapping(method=RequestMethod.GET, value="/validateMemberId/{memberId}")
	//@GetMapping("/validateMemberId/{memberId}")
	public ResponseEntity<?> memberIdValidator(@PathVariable("memberId") String memberNumber) {
		logger.info("Inside memberIdValidator");
		logger.info("Member ID Received: " + memberNumber);
		JSONObject jsonObj = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
			if(doFetchFromMongo) {
				jsonObj = validateMemberIdService.verifyMemberIdFromMongoDB(Integer.parseInt(memberNumber));
			} else {
				jsonObj = validateMemberIdService.verifyMemberIdNew(Integer.parseInt(memberNumber));
			}
		} catch(NumberFormatException | ClassCastException | NullPointerException e) {
			logger.error("Exception occured while constructing JSON Object " + e.getMessage());
			try {
				jsonObj.put("isValidMember", "Please enter only numbers");
			} catch (JSONException e1) {
				throw new SelfCheckoutException("Exception occured while constructing JSONObject");
			}
			return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("End of memberIdValidator");
		return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.OK);
	}

	@RequestMapping(method=RequestMethod.POST, value="/postItemDetails", consumes= {"application/json", "application/xml"}, produces="application/json")
	public ResponseEntity<?> postItemDetails(@RequestBody String itemsDetails) {
		logger.info("ItemDetails Received: " + itemsDetails);

		HttpHeaders headers = new HttpHeaders();
		JSONObject responseJson = new JSONObject();

		try {
			responseJson = itemDetailsService.postItemDetailsToDB(itemsDetails);
		} catch (SelfCheckoutException e) {
			e.printStackTrace();
			try {
				responseJson.put("isInserted", false);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return new ResponseEntity<>(responseJson.toString(), headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(responseJson.toString(), headers, HttpStatus.CREATED);	
	}

	@RequestMapping(method=RequestMethod.POST, value="/postPaymentDetails", consumes= {"application/json", "application/xml"}, produces="application/json")
	public ResponseEntity<?> postPaymentDetails(@RequestBody Payment payment) {
		logger.info("PaymentDetails Received: " + payment);

		HttpHeaders headers = new HttpHeaders();
		JSONObject responseJson = new JSONObject();

		try {
			responseJson = paymentDetailsService.postPaymentDetailsToDB(payment);
		} catch (SelfCheckoutException e) {
			e.printStackTrace();
			try {
				responseJson.put("isCardDetailsInserted", false);
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
			return new ResponseEntity<>(responseJson.toString(), headers, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(responseJson.toString(), headers, HttpStatus.CREATED);	
	}

	@RequestMapping(method=RequestMethod.GET, value="/getPaymentDetails/{creditCardDetails}")
	public ResponseEntity<?> getPaymentDetails(@PathVariable("creditCardDetails") String creditCardDetails) {
		logger.info("getPaymentDetails");
		logger.info("CreditCard Details: " + creditCardDetails);
		String isValid = "false";
		JSONObject jsonObj = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {

		} catch(NumberFormatException | ClassCastException | NullPointerException e) {
			logger.error("Exception occured while constructing JSON Object " + e.getMessage());
			try {
				jsonObj.put("isValidMember", "Please enter only numbers");
			} catch (JSONException e1) {
				throw new SelfCheckoutException("Exception occured while constructing JSONObject");
			}
			return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("Is MemberValid: " + isValid);
		logger.info("End of memberIdValidator");
		return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.OK);
	}


	@RequestMapping(method=RequestMethod.GET, value="/generateReceipt")
	public ResponseEntity<?> generateReceipt() {
		logger.info("Inside generateReceipt method");
		String isReceiptGenerated = "false";
		JSONObject jsonObj = new JSONObject();
		HttpHeaders headers = new HttpHeaders();
		try {
			generateReceiptService.generateReceipt();

			//GenerateReceiptService
			jsonObj.put("isReceiptGenerated", "true");
			isReceiptGenerated = "true";

		} catch(NumberFormatException | ClassCastException | NullPointerException e) {
			logger.error("Exception occured while generating receipt" + e.getMessage());
			try {
				jsonObj.put("isReceiptGenerated", "Error while generating receipt");
			} catch (JSONException e1) {
				throw new SelfCheckoutException("Exception occured while generating receipt");
			}
			return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.NOT_FOUND);
		} catch(Exception e) {
			e.printStackTrace();
		}
		logger.info("isReceiptGenerated: " + isReceiptGenerated);
		logger.info("End of generateReceipt");
		return new ResponseEntity<>(jsonObj.toString(), headers, HttpStatus.OK);
	} 


}
