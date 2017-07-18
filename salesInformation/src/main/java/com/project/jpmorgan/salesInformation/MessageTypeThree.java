package com.project.jpmorgan.salesInformation;

public class MessageTypeThree {
	private Sale newSale;
	AdjustmentOperations ao;
	protected Sale getNewSale() {
		return newSale;
	}


	protected void setSale(Sale newSale) {
		this.newSale = newSale;
	}


	protected AdjustmentOperations getAo() {
		return ao;
	}


	protected void setAo(AdjustmentOperations ao) {
		this.ao = ao;
	}


	private Sale newSaleDetails;
	
	
	public MessageTypeThree(AdjustmentOperations adjustmentOperations, Sale newSaleDetails) {
		this.ao = adjustmentOperations;
		this.newSaleDetails = newSaleDetails;
	}


	protected Sale getNewSaleDetails() {
		return newSaleDetails;
	}


	protected void setNewSaleDetails(Sale newSaleDetails) {
		this.newSaleDetails = newSaleDetails;
	}
}