package model.observers;

public interface BarCodeScannerObserver {
	
	void notifyBarCodeScanned(String barCode);

}
