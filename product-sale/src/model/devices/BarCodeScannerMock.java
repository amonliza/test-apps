package model.devices;

import java.util.HashSet;
import java.util.Set;

import model.observers.BarCodeScannerObserver;

public class BarCodeScannerMock {
	
	private Set<BarCodeScannerObserver> barCodeScannerObservers;
	
	public BarCodeScannerMock() {
		barCodeScannerObservers = new HashSet<>();
	}
	
	public void addBarCodeScannerObserver(BarCodeScannerObserver barCodeScannerObserver) {
		barCodeScannerObservers.add(barCodeScannerObserver);
	}
	
	public void scanBarCode(String barCode) {
		for (BarCodeScannerObserver barCodeScannerObserver : barCodeScannerObservers) {
			barCodeScannerObserver.notifyBarCodeScanned(barCode);
		}
	}

}
