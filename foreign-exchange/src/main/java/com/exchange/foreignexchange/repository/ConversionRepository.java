package com.exchange.foreignexchange.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exchange.foreignexchange.model.Conversion;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long>{
	
	Page<Conversion> findAll(Pageable pageable);
	
	List<Conversion> findAllByDate(Date date);
	
}
