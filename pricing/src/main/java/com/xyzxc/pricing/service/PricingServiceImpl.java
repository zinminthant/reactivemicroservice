package com.xyzxc.pricing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyzxc.pricing.data.Currencies;
import com.xyzxc.pricing.data.ExgVal;
import com.xyzxc.pricing.feign.CurrencyExchangeFeign;

import reactor.core.publisher.Mono;

@Service
public class PricingServiceImpl implements PricingService {

	@Autowired
	private CurrencyExchangeFeign currencyExchangeFeign;

	@Override
	public Mono<ExgVal> getExchangeValue(Currencies from, Currencies to) {

		return Mono.just(currencyExchangeFeign.getExchangeValue(from, to));
	}

}
