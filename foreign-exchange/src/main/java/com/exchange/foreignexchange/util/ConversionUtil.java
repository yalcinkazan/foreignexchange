package com.exchange.foreignexchange.util;

import com.exchange.foreignexchange.dto.request.ConversionRequest;
import com.exchange.foreignexchange.dto.response.ConversionResponse;
import com.exchange.foreignexchange.model.Conversion;

public class ConversionUtil {

	public static Conversion calculate(ConversionRequest conversionRequest, ConversionResponse conversionResponse) {
		if(conversionRequest != null && conversionResponse != null) {
			Conversion conversion = new Conversion();
			conversion.setAmountInTargetCurrency(conversionResponse.getRates().values().iterator().next() * conversionRequest.getSourceAmount());
			conversion.setDate(CommonUtil.getToday());
			return conversion;
		}else {
			throw new IllegalArgumentException("Can Not Calculate The Conversion");
		}
		
	}
	
}
