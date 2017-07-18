package com.project.jpmorgan.salesInformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.google.gson.Gson;

/**
 * @author Venkat This class is used to process sales messages received from
 *         external resources through File option
 */
public class SalesInformation {
	private static final String MESSAGE_TYPE_ONE = "MessageTypeOne";
	private static final String MESSAGE_TYPE_TWO = "MessageTypeTwo";
	private static final String MESSAGE_TYPE_THREE = "MessageTypeThree";
	int i = 0;
	Map<Product, Integer> productsMap = null;
	Map<Product, Double> priceMap = null;
	Map<Product, Double> adjustmentMap = null;
	Map<Integer, Message> messageMap = null;

	public static void main(String[] args) {
		SalesInformation salesInformation = new SalesInformation();
		salesInformation.initializeRequiredDataElements();
		boolean isProcessedSuccessfully = salesInformation
				.processSalesInformationFromFile("SalesMessages.txt");
		if (isProcessedSuccessfully)
			System.out.println("Processed file successfully");
		else
			System.out.println("Error while processing file successfully");
	}

	/**
	 * Initializes datastructures used in the application
	 */
	protected void initializeRequiredDataElements() {
		this.productsMap = new HashMap<>();
		this.priceMap = new HashMap<>();
		this.adjustmentMap = new HashMap<>();
		this.messageMap = new HashMap<>();

	}

	/**
	 * @param file
	 * @return boolean This method processes messages defined in the file and
	 *         returns true if successful else returns false
	 */
	protected boolean processSalesInformationFromFile(String fileName) {
		SalesInformation salesInformation = new SalesInformation();
		File file = salesInformation.readFile(fileName);
		Gson gson = new Gson();
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				Message message = gson.fromJson(line, Message.class);
				messageMap.put(++i, message);
				processMessage(message, productsMap, priceMap, adjustmentMap);
				if (i % 10 == 0) {
					Iterator<Entry<Product, Double>> it = priceMap.entrySet()
							.iterator();
					while (it.hasNext()) {
						Map.Entry<Product, Double> pair = (Map.Entry<Product, Double>) it
								.next();
						Product product = (Product) pair.getKey();
						System.out.println("Total sales for product--->"
								+ product.getProductName()
								+ " with total value is " + pair.getValue());
					}
				}
				if (i % 50 == 0) {
					System.out.println("Application is pausing...");
					System.out
							.println("Application is not accepting new messages...");
					Iterator<Entry<Product, Double>> it = adjustmentMap
							.entrySet().iterator();
					while (it.hasNext()) {
						Map.Entry<Product, Double> pair = (Map.Entry<Product, Double>) it
								.next();
						Product product = (Product) pair.getKey();
						System.out.println("Product--->"
								+ product.getProductName()
								+ " with adjusted value is " + pair.getValue());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occurred while processing.");
			return false;
		} finally {
			if (scanner != null)
				scanner.close();
		}
		return true;
	}

	/**
	 * @param fileName
	 * @return This method reads file from the specified location
	 */
	protected File readFile(String fileName) {
		File file = null;
		if (null != fileName && !fileName.isEmpty()) {
			URL url = Thread.currentThread().getContextClassLoader()
					.getResource(fileName);
			if (null != url)
				file = new File(url.getFile());
		}
		return file;

	}

	/**
	 * @param message
	 * @param productsMap
	 * @param priceMap
	 * @param adjustmentMap
	 *            This method is used to process messages and apply the actions
	 *            defined as per the messages.
	 */
	private static void processMessage(Message message,
			Map<Product, Integer> productsMap, Map<Product, Double> priceMap,
			Map<Product, Double> adjustmentMap) {
		String messageType = message.getMessageType();
		if (messageType.equals(MESSAGE_TYPE_ONE)) {
			MessageTypeOne mto = message.getMessageTypeOne();
			Sale sale = mto.getSale();
			if (productsMap.containsKey(sale.getProduct())) {
				double value = priceMap.get(sale.getProduct());
				int numberOfProducts = productsMap.get(sale.getProduct());
				productsMap.put(sale.getProduct(), numberOfProducts + 1);
				priceMap.put(sale.getProduct(), value + sale.getValue());
			} else {
				productsMap.put(sale.getProduct(), 1);
				priceMap.put(sale.getProduct(), sale.getValue());
			}
		} else if (messageType.equals(MESSAGE_TYPE_TWO)) {
			MessageTypeTwo mto = message.getMessageTypeTwo();
			Sale sale = mto.getSale();
			int numberOfOccurrences = mto.getNumberOfOccurences();
			for (int i = 0; i < numberOfOccurrences; i++) {
				if (productsMap.containsKey(sale.getProduct())) {
					double value = priceMap.get(sale.getProduct());
					int numberOfProducts = productsMap.get(sale.getProduct());
					productsMap.put(sale.getProduct(), numberOfProducts + 1);
					priceMap.put(sale.getProduct(), value + sale.getValue());
				} else {
					productsMap.put(sale.getProduct(), 1);
					priceMap.put(sale.getProduct(), sale.getValue());
				}
			}
		} else if (messageType.equals(MESSAGE_TYPE_THREE)) {
			MessageTypeThree mto = message.getMessageTypeThree();
			AdjustmentOperations adOperation = mto.getAo();
			Sale sale = mto.getNewSaleDetails();
			switch (adOperation.name().toString()) {
			case "ADD":
				if (productsMap.containsKey(sale.getProduct())) {
					double value = priceMap.get(sale.getProduct());
					int numberOfProducts = productsMap.get(sale.getProduct());
					priceMap.put(sale.getProduct(), value
							+ (sale.getValue() * numberOfProducts));
					adjustmentMap.put(sale.getProduct(), sale.getValue());
					break;
				}
			case "SUBTRACT":
				if (productsMap.containsKey(sale.getProduct())) {
					double value = priceMap.get(sale.getProduct());
					int numberOfProducts = productsMap.get(sale.getProduct());
					priceMap.put(sale.getProduct(), value
							- (sale.getValue() * numberOfProducts));
					adjustmentMap.put(sale.getProduct(), sale.getValue());
					break;
				}
			case "MULTIPLY":
				if (productsMap.containsKey(sale.getProduct())) {
					double value = priceMap.get(sale.getProduct());
					int numberOfProducts = productsMap.get(sale.getProduct());
					adjustmentMap.put(sale.getProduct(), sale.getValue());
					priceMap.put(sale.getProduct(), value
							* (sale.getValue() * numberOfProducts));
					break;
				}
			default: {
				System.out
						.println("Currently supports only ADD, Delete and Multiply Operations.");
				break;
			}
			}
		} else {
			System.out.println("Invalid Request. Message Type doesn't exist.");
		}
	}

	private File errorMessageForFile(File file) {
		if (null == file) {
			System.out.println("Please enter a valid file name");
			System.exit(0);
		}
		return null;
	}
}
