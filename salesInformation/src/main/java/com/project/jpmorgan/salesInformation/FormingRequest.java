/**
 * 
 */
package com.project.jpmorgan.salesInformation;

import com.google.gson.Gson;

/**
 * @author vmatcha
 *
 */
public class FormingRequest {

	/**
	 * 
	 */
	public FormingRequest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sale sale = new Sale();
    	sale.setProduct(new Product("Apple"));
    	sale.setValue(10);
    	Sale newSale = new Sale();
    	newSale.setProduct(new Product("Apple"));
    	newSale.setValue(0.20);
    	Message message = new Message();
    	MessageTypeOne mTT = new MessageTypeOne(newSale);
    	message.setMessageType("MessageTypeOne");
    	message.setMessageTypeOne(mTT);
    	
    	Gson gson = new Gson();
        String json = gson.toJson(message);
        System.out.println(json);

	}

}
