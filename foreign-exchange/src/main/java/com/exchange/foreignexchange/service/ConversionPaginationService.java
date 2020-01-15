package com.exchange.foreignexchange.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.exchange.foreignexchange.exception.ConversionPageNotFoundException;
import com.exchange.foreignexchange.model.Conversion;
import com.exchange.foreignexchange.repository.ConversionRepository;

@Service
public class ConversionPaginationService {

    @Autowired
    private ConversionRepository conversionRepository;

    public Page<Conversion> getPaginatedConversion(int pageNumber){
    	
        PageRequest pageable = PageRequest.of(pageNumber - 1, 3);
        Page<Conversion> resultPage = this.conversionRepository.findAll(pageable);
        
        if (pageNumber > resultPage.getTotalPages()) {
            throw new ConversionPageNotFoundException(pageNumber);
        }
        return resultPage;
    }
}
