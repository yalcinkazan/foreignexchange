package com.exchange.foreignexchange.model;

public class Rate {
	
	private Double exchangeRate;
	
	public Rate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Double getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(Double result) {
		this.exchangeRate = result;
	}
	
	
}
