package com.xyzxc.product.service;

import com.xyzxc.product.data.Inventory;
import com.xyzxc.product.data.Price;

import reactor.core.publisher.Mono;

public interface ProductService {

	public Mono<Price> fetchPriceDetails(Long productid);
	
	public Mono<Inventory> fetchInventoryDetails(Long productid);
	
	
	public String getInventoryServicePort();


}
