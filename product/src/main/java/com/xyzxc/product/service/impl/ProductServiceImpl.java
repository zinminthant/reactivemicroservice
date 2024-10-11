package com.xyzxc.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xyzxc.product.data.Inventory;
import com.xyzxc.product.data.Price;
import com.xyzxc.product.feign.InventoryClient;
import com.xyzxc.product.feign.PriceClient;
import com.xyzxc.product.service.ProductService;

import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService
{

	@Autowired
	private PriceClient priceClient;
	
	@Autowired
	private InventoryClient inventoryClient;
	
	@Override
	 public Mono<Price> fetchPriceDetails(Long productId) {
        return Mono.just(priceClient.getPriceDetails(productId));  // Wrap the result in Mono
    }

	@Override
	public Mono<Inventory> fetchInventoryDetails(Long productid) {
		return Mono.just(inventoryClient.getInventoryDetails(productid));
	}

	@Override
	public String getInventoryServicePort() {
		// TODO Auto-generated method stub
		return inventoryClient.getPortInfo();
	}

}
