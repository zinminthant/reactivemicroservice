package com.xyzxc.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value="price-client",url="http://localhost:8002/price/")
public interface PriceClient {

}
