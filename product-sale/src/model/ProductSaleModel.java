package model;

import model.observers.BarCodeScannerObserver;
import commons.observers.ProductScanObserver;

public interface ProductSaleModel extends BarCodeScannerObserver {

	void addProductScanObserver(ProductScanObserver productScanObserver);

}
