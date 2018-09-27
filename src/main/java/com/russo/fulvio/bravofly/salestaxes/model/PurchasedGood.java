package com.russo.fulvio.bravofly.salestaxes.model;

/**
 * Bean that represents a good that the user what purchase
 * 
 * @author fulvio
 *
 */
public class PurchasedGood {	
	
	/* the price without taxes */
	private double netPrice;
	
	/* the price WITH taxes */
	private double priceWithTaxes;
	
	/* total tax applied  */
	private double goodTotalTax;
	
	/* good type eg: food, medical) */
	private String goodType;	
	

	/**
	 * Constructor
	 */
	public PurchasedGood(String goodType, double goodNetPrice, double goodTotalTax, double priceWithTaxes ) {	
		this.netPrice = goodNetPrice;
		this.goodType = goodType;
		this.goodTotalTax = goodTotalTax;
		this.priceWithTaxes = priceWithTaxes;
	}


	public double getNetPrice() {
		return netPrice;
	}


	public void setNetPrice(double netPrice) {
		this.netPrice = netPrice;
	}


	public double getPriceWithTaxes() {
		return priceWithTaxes;
	}


	public void setPriceWithTaxes(double priceWithTaxes) {
		this.priceWithTaxes = priceWithTaxes;
	}


	public double getGoodTotalTax() {
		return goodTotalTax;
	}


	public void setGoodTotalTax(double goodTotalTax) {
		this.goodTotalTax = goodTotalTax;
	}


	public String getGoodType() {
		return goodType;
	}


	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}



}
