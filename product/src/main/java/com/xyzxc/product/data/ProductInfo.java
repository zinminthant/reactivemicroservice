package com.xyzxc.product.data;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductInfo {
	private Long productID;
	private String productName;
	private String productDesc;
}
