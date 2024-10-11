package com.xyzxc.pricing.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xyzxc.pricing.data.Currencies;
import com.xyzxc.pricing.data.ExgVal;
import com.xyzxc.pricing.data.Price;
import com.xyzxc.pricing.service.PricingService;

import reactor.core.publisher.Mono;

@RestController
public class PricingController {

	public WebClient webClient = WebClient.create();

	@Autowired
	private PricingService pricingService;

	@Autowired
	private RestTemplate restTemplate;

	List<Price> priceList = new ArrayList<Price>();

	@GetMapping("/price/exchangeserverinfo")
	public String getExchangeServerInfo() {
		System.out.println("exchange------");
		return restTemplate.getForObject("http://localhost:8004/currexg/port", String.class);
	}

	public String fallbackMethod() {
		System.out.println("bfwefweffallback");
		return "null";
	}

	@GetMapping("/price/{productid}")
	public Mono<Price> getPriceDetails(@PathVariable Long productid) {
		Mono<Price> price = Mono.just(getPriceInfo(productid));

//		Mono<ExgVal> exgVal = webClient.get().uri("http://localhost:8004/currexg/from/USD/to/YEN").retrieve()
//				.bodyToMono(ExgVal.class);

		Mono<ExgVal> exgVal = pricingService.getExchangeValue(Currencies.USD, Currencies.YEN);

		// Mono<ExgVal> exgVal =
		// pricingService.getExchangeValueReactive(Currencies.USD,Currencies.YEN);

		return Mono.zip(price, exgVal)
				.map(tuple -> new Price(tuple.getT1().getPriceID(), tuple.getT1().getProductID(),
						tuple.getT1().getOriginalPrice(),
						Math.multiplyExact(tuple.getT2().getExgVal(), tuple.getT1().getDiscountedPrice())));

	}

	private Price getPriceInfo(Long productid) {
		populatepriceList();

		for (Price p : priceList) {
			if (productid.equals(p.getProductID())) {

				return p;
			}
		}

		return null;
	}

	private void populatepriceList() {
		priceList.clear();
		priceList.add(new Price(201L, 101l, 1999, 999));
		priceList.add(new Price(202L, 102l, 199, 19));
		priceList.add(new Price(203L, 103l, 1222, 600));
	}

}
