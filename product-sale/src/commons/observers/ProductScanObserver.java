package commons.observers;

import commons.data.Product;

public interface ProductScanObserver {
	
	void notifyProductScanError(String errorMessage);
	
	void notifyProductScanned(Product product);
	
	void notifyAllProductsScanned(double totalPrice);

}
