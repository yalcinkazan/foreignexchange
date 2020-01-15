package com.exchange.foreignexchange.util;

import com.exchange.foreignexchange.dto.response.RateResponse;
import com.exchange.foreignexchange.model.Rate;

public class RateUtil {
	
	public static <T extends RateResponse> Rate getRateResponse(T rateResponse) {
		if(rateResponse != null) {
			if(((RateResponse) rateResponse).getRates().values().iterator().hasNext()) {
				return new Rate(((RateResponse) rateResponse).getRates().values().iterator().next());
			}
		}
		return null;
	}
	
}
