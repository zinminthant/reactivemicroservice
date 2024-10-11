package com.xyzxc.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.xyzxc.product.data.Price;

@FeignClient(name="gateway-service")
public interface PriceClient {
	@GetMapping("/price/{productid}")
	Price getPriceDetails(@PathVariable("productid") Long productId);
}
