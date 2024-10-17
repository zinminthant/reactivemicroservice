package com.xyzxc.exchange.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyzxc.exchange.data.Currencies;
import com.xyzxc.exchange.data.ExgVal;

import reactor.core.publisher.Mono;

@RestController
@RefreshScope
public class ExchangeController {
	
	
	
	@Value("${server.port}")
	private String port;
	
	
	@Value("${message}")
	private String message;
	
	
	@GetMapping("/currexg/message")
	public String getMessage() {
		return message;
	}
	
	
	
	@GetMapping("/currexg/port")
	public String getServerInfo() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return port;
	}
	
	
	
	@GetMapping("/currexg/from/{from}/to/{to}")
	public Mono<ExgVal> getExchangeValue(@PathVariable("from") Currencies from, @PathVariable("to") Currencies to) {
		
		ExgVal exgVal = null;

		if (Currencies.USD == from && Currencies.INR == to) {
			exgVal = new ExgVal(901L, from, to, 60);
		} else if (Currencies.USD == from && Currencies.YEN == to) {
			exgVal = new ExgVal(901L, from, to, 105);
		}

		return Mono.just(exgVal);
	}
}
