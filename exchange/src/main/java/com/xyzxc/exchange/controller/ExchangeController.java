package com.xyzxc.exchange.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyzxc.exchange.data.Currencies;
import com.xyzxc.exchange.data.ExgVal;

@RestController
public class ExchangeController {
	@GetMapping("/currexg/from/{from}/to/{to}")
	public ExgVal getInventoryDetails(@PathVariable("from") Currencies from, @PathVariable("to") Currencies to) {
		
		ExgVal exgVal = null;

		if (Currencies.USD == from && Currencies.INR == to) {
			exgVal = new ExgVal(901L, from, to, 60);
		} else if (Currencies.USD == from && Currencies.YEN == to) {
			exgVal = new ExgVal(901L, from, to, 105);
		}

		return exgVal;
	}
}
