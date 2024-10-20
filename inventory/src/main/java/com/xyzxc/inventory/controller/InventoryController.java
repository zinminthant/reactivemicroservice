package com.xyzxc.inventory.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.xyzxc.inventory.data.Inventory;

import reactor.core.publisher.Mono;

@RestController
public class InventoryController {

	List<Inventory> inventoryList = new ArrayList<Inventory>();

	@Value("${server.port}")
	private String port;

	public InventoryController() {
		populateInventoryList();
	}

	@GetMapping("/inventory/getport")
	public String getPortInfo() {

		return port;
	}

	@GetMapping("/inventory/{productid}")
	public Mono<Inventory> getInventoryDetails(@PathVariable Long productid) {
		Mono<Inventory> inventory = Mono.just(getInventoryInfo(productid));

		return inventory;
	}

	private Inventory getInventoryInfo(Long productid) {

		for (Inventory i : inventoryList) {
			if (productid.equals(i.getProductID())) {
				return i;
			}
		}

		return null;
	}

	private void populateInventoryList() {
		inventoryList.clear();
		inventoryList.add(new Inventory(301L, 101l, true));
		inventoryList.add(new Inventory(302L, 102l, true));
		inventoryList.add(new Inventory(303L, 103l, false));
	}

}
