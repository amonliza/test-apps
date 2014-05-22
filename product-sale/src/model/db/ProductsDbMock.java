package model.db;

import java.util.LinkedHashMap;
import java.util.Map;

import commons.data.Product;

public class ProductsDbMock {
	
	private Map<String, Product> products;
	
	public ProductsDbMock() {
		products = new LinkedHashMap<>();
	}
	
	public void addProduct(String barCode, double price, String name) {
		products.put(barCode, new Product(price, name));
	}
	
	public boolean containsProduct(String barCode) {
		return products.containsKey(barCode);
	}
	
	public Product getProduct(String barCode) {
		return products.get(barCode);
	}

}
