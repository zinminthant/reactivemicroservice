package com.xyzxc.pricing.feign;

import com.xyzxc.pricing.data.Currencies;
import com.xyzxc.pricing.data.ExgVal;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import reactor.core.publisher.Mono;

@Headers({ "Accept: application/json" })
public interface CurrencyExchangeFeignReactive {

	@RequestLine("GET /currexg/from/{from}/to/{to}")
	@Headers("Content-Type: application/json")
	Mono<ExgVal> getExchangeValue(@Param("from") Currencies from, @Param("to") Currencies to);

}
