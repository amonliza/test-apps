package launch;

import model.ProductSaleDbModel;
import model.ProductSaleModel;
import model.db.ProductsDbMock;
import model.devices.BarCodeScannerMock;
import model.devices.ReceiptPrinter;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import view.ProductSaleSimpleView;
import view.devices.LCDdisplayMock;


public class ProductSaleTest {
	
	private static BarCodeScannerMock barCodeScannerMock;
	private static ProductSaleSimpleView productSaleSimpleView;
	
	@BeforeClass
	public static void oneTimeSetUp() {
		ProductsDbMock productsDbMock = new ProductsDbMock();
		productsDbMock.addProduct("1", 1.15, "product1");
		productsDbMock.addProduct("2", 3, "product2");
		productsDbMock.addProduct("3", 4.35, "product3");
		ReceiptPrinter receiptPrinter = new ReceiptPrinter();
		ProductSaleModel productSaleModel = new ProductSaleDbModel(productsDbMock, receiptPrinter);
		barCodeScannerMock = new BarCodeScannerMock();
		barCodeScannerMock.addBarCodeScannerObserver(productSaleModel);
		LCDdisplayMock lcdDisplayMock = new LCDdisplayMock();
		productSaleSimpleView = new ProductSaleSimpleView(productSaleModel, lcdDisplayMock);
	}
	
	@Test
	public void testEmptyBarCode() {
		barCodeScannerMock.scanBarCode("");
		Assert.assertEquals("Invalid bar-code", productSaleSimpleView.getMessageOnScreen());
	}
	
	@Test
	public void testInvalidBarCode() {
		barCodeScannerMock.scanBarCode("4");
		Assert.assertEquals("Product not found", productSaleSimpleView.getMessageOnScreen());
	}
	
	@Test
	public void testSingleBarCode() {
		barCodeScannerMock.scanBarCode("2");
		Assert.assertEquals("product2 3", productSaleSimpleView.getMessageOnScreen());
		barCodeScannerMock.scanBarCode("3");
		Assert.assertEquals("product3 4,35", productSaleSimpleView.getMessageOnScreen());
		barCodeScannerMock.scanBarCode("1");
		Assert.assertEquals("product1 1,15", productSaleSimpleView.getMessageOnScreen());
		barCodeScannerMock.scanBarCode("exit");
	}
	
	@Test
	public void testExit() {
		barCodeScannerMock.scanBarCode("2");
		barCodeScannerMock.scanBarCode("3");
		barCodeScannerMock.scanBarCode("1");
		barCodeScannerMock.scanBarCode("exit");
		Assert.assertEquals("Total price: 8,5", productSaleSimpleView.getMessageOnScreen());
	}

}
