package com.exchange.foreignexchange.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

@Table(name = "CONVERSION")
@Entity
public class Conversion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;
	
    @Column(name = "AMOUNT_IN_TARGET_CURRENCY")
    private Double amountInTargetCurrency;

    @Column(name = "DATE", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date date;

    public Conversion() {
    	
	}
    
    public Conversion(Long transactionId, Double amountInTargetCurrency, Date date) {
		this.transactionId = transactionId;
		this.amountInTargetCurrency = amountInTargetCurrency;
		this.date = date;
	}
    
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Double getAmountInTargetCurrency() {
		return amountInTargetCurrency;
	}
	public void setAmountInTargetCurrency(Double amountInTargetCurrency) {
		this.amountInTargetCurrency = amountInTargetCurrency;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
    
    
    

}
