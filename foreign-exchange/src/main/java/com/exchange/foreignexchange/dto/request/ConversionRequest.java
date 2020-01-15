package com.exchange.foreignexchange.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonPropertyOrder({ "sourceAmount", "sourceCurrency", "targetCurrency" })
public class ConversionRequest {
		
		@JsonProperty("sourceAmount")
		private Double sourceAmount;
		
		@JsonProperty("sourceCurrency")
		private String sourceCurrency;
		
		@JsonProperty("targetCurrency")
		private String targetCurrency;
		

		public Double getSourceAmount() {
			return this.sourceAmount;
		}

		public void setSourceAmount(Double sourceAmount) {
			this.sourceAmount = sourceAmount;
		}

		public String getSourceCurrency() {
			return this.sourceCurrency;
		}

		public void setSourceCurrency(String sourceCurrency) {
			this.sourceCurrency = sourceCurrency;
		}

		public String getTargetCurrency() {
			return this.targetCurrency;
		}

		public void setTargetCurrency(String targetCurrency) {
			this.targetCurrency = targetCurrency;
		}
		
}
