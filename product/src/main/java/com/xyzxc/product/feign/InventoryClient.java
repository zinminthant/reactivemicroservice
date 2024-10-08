package com.xyzxc.product.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.xyzxc.product.data.Inventory;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

	@GetMapping("/inventory/{productid}")
	public Inventory getInventoryDetails(@PathVariable Long productid);
}
