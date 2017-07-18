/**
 * 
 */
package com.project.jpmorgan.salesInformation;

/**
 * @author Rajesh
 *
 */
public class MessageTypeOne{
	@Override
	public String toString() {
		return "MessageTypeOne [sale=" + sale + "]";
	}

	private Sale sale;

	/**
	 * @param sale
	 */
	public MessageTypeOne(Sale sale) {
		this.sale = sale;
	}

	protected Sale getSale() {
		return sale;
	}

	protected void setSale(Sale sale) {
		this.sale = sale;
	}
}
