package model.devices;

import java.util.List;

import commons.data.Product;

public class ReceiptPrinter {
	
	public void printReceipt(List<Product> products, double totalPrice) {
		System.out.println();
		System.out.println("Printing receipt:");
		for (Product product : products) {
			System.out.println(product.getName()+" "+product.getPrice());
		}
		System.out.println();
		System.out.println("Total price: "+totalPrice);
	}

}
