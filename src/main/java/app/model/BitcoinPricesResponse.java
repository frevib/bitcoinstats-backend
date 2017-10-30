package app.model;

import java.util.List;

public class BitcoinPricesResponse {

	private List<BitcoinPrices> bitcoinPrices;

	public BitcoinPricesResponse(List<BitcoinPrices> bitcoinPrices) {
		this.bitcoinPrices = bitcoinPrices;
	}

	public List<BitcoinPrices> getBitcoinPrices() {
		return bitcoinPrices;
	}

	public void setBitcoinPrices(List<BitcoinPrices> bitcoinPrices) {
		this.bitcoinPrices = bitcoinPrices;
	}
}
