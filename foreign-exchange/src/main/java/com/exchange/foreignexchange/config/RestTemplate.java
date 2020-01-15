package com.exchange.foreignexchange.config;

public class RestTemplate extends org.springframework.web.client.RestTemplate {

	private static RestTemplate instance = null;

	private RestTemplate() {

	}

	public static RestTemplate getInstance() {
		if (instance == null) {
			instance = new RestTemplate();
		}
		return instance;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Can not cloneable !");
	}
}
