package com.exchange.foreignexchange.dto.response;

import java.util.HashMap;


public class ConversionResponse extends Response {

	private String base;
	private HashMap<String, Double> rates = new HashMap<String, Double>();
	private String date;

	@Override
	public String getBase() {
		return this.base;
	}

	@Override
	public void setBase(String base) {
		this.base = base;
	}

	@Override
	public String getDate() {
		return this.date;
	}

	@Override
	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public HashMap<String, Double> getRates() {
		return this.rates;
	}

	@Override
	public void setRates(HashMap<String, Double> rates) {
		this.rates = rates;
	}
	
}
