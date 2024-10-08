package com.xyzxc.pricing.service;

import com.xyzxc.pricing.data.Currencies;
import com.xyzxc.pricing.data.ExgVal;

import reactor.core.publisher.Mono;

public interface PricingService {

	Mono<ExgVal> getExchangeValue(Currencies from, Currencies to);
}
