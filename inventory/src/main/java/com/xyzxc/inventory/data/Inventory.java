package com.xyzxc.inventory.data;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
	private Long inventoryID;
	private Long productID;
	private Boolean inStock;
}
