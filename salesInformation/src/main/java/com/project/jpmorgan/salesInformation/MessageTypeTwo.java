/**
 * 
 */
package com.project.jpmorgan.salesInformation;

/**
 * @author Rajesh
 *
 */
public class MessageTypeTwo {
	protected Sale getSale() {
		return sale;
	}

	protected void setSale(Sale sale) {
		this.sale = sale;
	}

	@Override
	public String toString() {
		return "MessageTypeTwo [sale=" + sale + ", numberOfOccurences="
				+ numberOfOccurences + "]";
	}

	private Sale sale;
	private int numberOfOccurences;
	
	/**
	 * @param sale
	 */
	public MessageTypeTwo(Sale sale, int numberOfOccurences) {
		this.sale = sale;
		this.numberOfOccurences = numberOfOccurences;
	}

	public int getNumberOfOccurences() {
		return numberOfOccurences;
	}

	public void setNumberOfOccurences(int numberOfOccurences) {
		this.numberOfOccurences = numberOfOccurences;
	}
}