package com.exchange.foreignexchange.controller;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exchange.foreignexchange.config.RestTemplate;
import com.exchange.foreignexchange.dto.request.ConversionRequest;
import com.exchange.foreignexchange.dto.request.ConversionSearchRequest;
import com.exchange.foreignexchange.dto.request.RateRequest;
import com.exchange.foreignexchange.dto.response.ConversionResponse;
import com.exchange.foreignexchange.dto.response.RateResponse;
import com.exchange.foreignexchange.exception.ConversionNotFoundException;
import com.exchange.foreignexchange.exception.RestRuntimeException;
import com.exchange.foreignexchange.model.Conversion;
import com.exchange.foreignexchange.model.Rate;
import com.exchange.foreignexchange.repository.ConversionRepository;
import com.exchange.foreignexchange.service.ConversionPaginationService;
import com.exchange.foreignexchange.util.ConversionUtil;
import com.exchange.foreignexchange.util.RateUtil;

@RestController
public class MainController {

	@Autowired
	private ConversionRepository conversionRepository;
	
	@Autowired
	private ConversionPaginationService conversionPaginationService;
	
	@Value("${api.url}")
	private String API;
	
	@Value("${date.format}")
	private String DATE_FORMAT;
	
	@RequestMapping(path = "/rate", method = RequestMethod.POST)
	public Rate getExchangeRate (@Valid @RequestBody RateRequest rateRequest){
		try {
			RateResponse rateResponse = RestTemplate.getInstance().getForObject(this.API + "/latest?base=" + rateRequest.getSource() + "&symbols=" + rateRequest.getTarget(), RateResponse.class);
		    Rate rate = RateUtil.getRateResponse(rateResponse);
			return rate;
		} catch (Exception e) {
			if(rateRequest.getSource() == null || rateRequest.getTarget() == null){
				throw new RestRuntimeException("Some paramater were missing !");
			}else {
				e.printStackTrace();
				throw new RestRuntimeException("Please check parameter accuracy !");
			}
		}
	}
	
	@RequestMapping(path = "/conversion", method = RequestMethod.POST)
	public Conversion getConversion (@Valid @RequestBody ConversionRequest conversionRequest){
		try {
			ConversionResponse conversionResponse = RestTemplate.getInstance().getForObject(this.API + "/latest?base=" + conversionRequest.getSourceCurrency() + "&symbols=" + conversionRequest.getTargetCurrency(), ConversionResponse.class);
		    Conversion conversion = ConversionUtil.calculate(conversionRequest, conversionResponse);
			return this.conversionRepository.save(conversion);// Save To H2 Database
		}catch (Exception e) {
			if(conversionRequest.getSourceAmount() == null || conversionRequest.getSourceCurrency() == null || conversionRequest.getTargetCurrency() == null){
				throw new RestRuntimeException("Some paramater were missing !");
			}else {
				throw new RestRuntimeException("Please check parameter accuracy !");
			}
		}
		
	}
	
	@GetMapping(path = "/conversions")
	List<Conversion> getConversions (@RequestBody ConversionSearchRequest conversionSearchReq){
		if(conversionSearchReq.getDate() != null && conversionSearchReq.getPageNumber() != null) {
			String formatDate = new SimpleDateFormat(this.DATE_FORMAT).format(conversionSearchReq.getDate());  
			Page<Conversion> resultPage = this.conversionPaginationService.getPaginatedConversion(conversionSearchReq.getPageNumber());
			return resultPage.getContent().stream().filter(d -> d.getDate().toString().contains(formatDate)).collect(Collectors.toList());
		}else if(conversionSearchReq.getTransactionId() != null) {
			Conversion conversion = this.conversionRepository.findById(conversionSearchReq.getTransactionId()).orElseThrow(() -> new ConversionNotFoundException(conversionSearchReq.getTransactionId()));
			return Arrays.asList(conversion);
		}else {
			throw new RestRuntimeException("Please fill date or id param !");
		}
	}
		
	
}
