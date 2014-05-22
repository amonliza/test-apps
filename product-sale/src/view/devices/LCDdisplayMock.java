package view.devices;

public class LCDdisplayMock {
	
	private String messageOnScreen;
	
	public void showProduct(String name, String price) {
		messageOnScreen = name+" "+price;
	}
	
	public void showError(String errorMessage) {
		messageOnScreen = errorMessage;
	}
	
	public void showTotalPrice(String totalPrice) {
		messageOnScreen = "Total price: "+totalPrice;
	}
	
	public String getMessageOnScreen() {
		return messageOnScreen;
	}

}
