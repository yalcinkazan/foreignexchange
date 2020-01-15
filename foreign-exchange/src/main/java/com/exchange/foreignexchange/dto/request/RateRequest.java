package com.exchange.foreignexchange.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonPropertyOrder({ "source", "target" })
public class RateRequest {
		
		@JsonProperty("source")
		private String source;
		
		@JsonProperty("target")
		private String target;

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}

		public String getTarget() {
			return target;
		}

		public void setTarget(String target) {
			this.target = target;
		}
		
		
}

