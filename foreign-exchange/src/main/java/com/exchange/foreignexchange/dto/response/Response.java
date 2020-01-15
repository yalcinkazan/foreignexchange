package com.exchange.foreignexchange.dto.response;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "base", "rates", "date" })
public abstract class Response {
	
	@JsonProperty("base")
	public abstract String getBase();

	@JsonProperty("base")
	public abstract void setBase(String base);

	@JsonProperty("date")
	public abstract String getDate();

	@JsonProperty("date")
	public abstract void setDate(String date);

	@JsonProperty("rates")
	public abstract HashMap<String, Double> getRates();

	@JsonProperty("rates")
	public abstract void setRates(HashMap<String, Double> rates);
}
