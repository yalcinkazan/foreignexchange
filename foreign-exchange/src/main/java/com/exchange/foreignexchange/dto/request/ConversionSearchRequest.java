package com.exchange.foreignexchange.dto.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "transactionId", "date", "pageNumber" })
public class ConversionSearchRequest {
	
	@JsonProperty("transactionId")
	private Long transactionId;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonProperty("date")
	private Date date;
	
	@JsonProperty("pageNumber")
	private Integer pageNumber;

	public Long getTransactionId() {
		return this.transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	} 
	
	
}
