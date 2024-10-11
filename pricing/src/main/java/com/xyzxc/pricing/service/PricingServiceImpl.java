package com.xyzxc.pricing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xyzxc.pricing.data.Currencies;
import com.xyzxc.pricing.data.ExgVal;
import com.xyzxc.pricing.feign.CurrencyExchangeClient;
import com.xyzxc.pricing.feign.CurrencyExchangeFeignReactive;

import reactivefeign.webclient.WebReactiveFeign;
import reactor.core.publisher.Mono;

@Service
public class PricingServiceImpl implements PricingService {

	@Autowired
	private CurrencyExchangeClient currencyExchangeFeign;

	@Override
	public Mono<ExgVal> getExchangeValue(Currencies from, Currencies to) {

		return Mono.just(currencyExchangeFeign.getExchangeValue(from, to));
	}

	@Override
	public Mono<ExgVal> getExchangeValueReactive(Currencies from, Currencies to) {

		/* Create instance of your API */
		CurrencyExchangeFeignReactive client = WebReactiveFeign // WebClient based reactive feign
				.<CurrencyExchangeFeignReactive>builder()
				.target(CurrencyExchangeFeignReactive.class, "http://exchange-service");

		return client.getExchangeValue(from, to);

	}

	@Override
	@HystrixCommand(fallbackMethod = "fallbackMethod")
	@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
	public String getExchangeServerInfo() {
		// TODO Auto-generated method stub
		return currencyExchangeFeign.getServerInfo();
	}

	public String fallbackMethod() {
		return "9999";
	}

}
