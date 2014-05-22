package model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.db.ProductsDbMock;
import model.devices.ReceiptPrinter;
import commons.data.Product;
import commons.observers.ProductScanObserver;

public class ProductSaleDbModel implements ProductSaleModel {
	
	private static final String EXIT = "exit";
	private Set<ProductScanObserver> productScanObservers;
	private ProductsDbMock productsDbMock;
	private ReceiptPrinter receiptPrinter;
	private List<Product> scannedProducts;
	
	public ProductSaleDbModel(ProductsDbMock productsDbMock, ReceiptPrinter receiptPrinter) {
		this.productsDbMock = productsDbMock;
		this.receiptPrinter = receiptPrinter;
		productScanObservers = new HashSet<>();
		scannedProducts = new LinkedList<>();
	}
	
	@Override
	public void addProductScanObserver(ProductScanObserver productScanObserver) {
		productScanObservers.add(productScanObserver);
	}
	
	@Override
	public void notifyBarCodeScanned(String barCode) {
		if (barCode == null || barCode.isEmpty()) {
			notifyProductScanError("Invalid bar-code");
		} else if (EXIT.equals(barCode)) {
			double totalPrice = countTotalPrice();
			notifyAllProductsScanned(totalPrice);
			receiptPrinter.printReceipt(scannedProducts, totalPrice);
			scannedProducts.clear();
		} else if (productsDbMock.containsProduct(barCode)) {
			Product product = productsDbMock.getProduct(barCode);
			scannedProducts.add(product);
			notifyProductScanned(product);
		} else {
			notifyProductScanError("Product not found");
		}
	}
	
	private double countTotalPrice() {
		double totalPrice = 0;
		for (Product product : scannedProducts) {
			totalPrice += product.getPrice();
		}
		return totalPrice;
	}

	private void notifyProductScanError(String errorMessage) {
		for (ProductScanObserver productScanObserver : productScanObservers) {
			productScanObserver.notifyProductScanError(errorMessage);
		}
	}
	
	private void notifyProductScanned(Product product) {
		for (ProductScanObserver productScanObserver : productScanObservers) {
			productScanObserver.notifyProductScanned(product);
		}
	}
	
	private void notifyAllProductsScanned(double totalPrice) {
		for (ProductScanObserver productScanObserver : productScanObservers) {
			productScanObserver.notifyAllProductsScanned(totalPrice);
		}
	}

}
