package com.xyzxc.pricing.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.xyzxc.pricing.data.Currencies;
import com.xyzxc.pricing.data.ExgVal;

@FeignClient(name="exchange-service")
public interface CurrencyExchangeFeign {

	@GetMapping("/currexg/from/{from}/to/{to}")
	public ExgVal getExchangeValue(@PathVariable("from") Currencies from, @PathVariable("to") Currencies to);

}
