/**
 * 
 */
package com.project.jpmorgan.salesInformation;

import java.util.List;

/**
 * @author Rajesh
 *
 */
public class Sale {
	@Override
	public String toString() {
		return "Sale [product=" + product + ", value=" + value + "]";
	}
	private Product product;
	private double value;
	/**
	 * 
	 */
	public Sale() {
		// TODO Auto-generated constructor stub
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
