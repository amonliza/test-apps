package view;

import java.text.DecimalFormat;

import model.ProductSaleModel;
import view.devices.LCDdisplayMock;
import commons.data.Product;
import commons.observers.ProductScanObserver;

public class ProductSaleSimpleView implements ProductScanObserver {
	
	private final static DecimalFormat DF = new DecimalFormat("#.##");
	private ProductSaleModel productSaleModel;
	private LCDdisplayMock lcdDisplayMock;
	
	public ProductSaleSimpleView(ProductSaleModel productSaleModel, LCDdisplayMock lcdDisplayMock) {
		this.productSaleModel = productSaleModel;
		this.productSaleModel.addProductScanObserver(this);
		this.lcdDisplayMock = lcdDisplayMock;
	}
	
	@Override
	public void notifyProductScanError(String errorMessage) {
		lcdDisplayMock.showError(errorMessage);
	}
	
	@Override
	public void notifyProductScanned(Product product) {
		String formattedPrice = DF.format(product.getPrice());
		lcdDisplayMock.showProduct(product.getName(), formattedPrice);
	}
	
	@Override
	public void notifyAllProductsScanned(double totalPrice) {
		String formattedPrice = DF.format(totalPrice);
		lcdDisplayMock.showTotalPrice(formattedPrice);
	}
	
	public String getMessageOnScreen() {
		return lcdDisplayMock.getMessageOnScreen();
	}

}
