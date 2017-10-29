package app.service;

import app.mapper.BitcoinDataMapper;
import app.model.ApiResponse;
import app.model.BitcoinData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class PopulateDatabaseService {

	private static final Logger LOG = LoggerFactory.getLogger(PopulateDatabaseService.class);

	@Value("${daily.prices.url}")
	private String dailyPricesUrl;

	@Value("${hourly.prices.url}")
	private String hourlyPricesUrl;

	@Value("${minutely.prices.url}")
	private String dailyMinutelyPricesUrl;

	private FetchDataService fetchDataService;

	private BitcoinDataMapper bitcoinDataMapper;

	public PopulateDatabaseService(FetchDataService fetchDataService, BitcoinDataMapper bitcoinDataMapper) {
		this.fetchDataService = fetchDataService;
		this.bitcoinDataMapper = bitcoinDataMapper;
	}

	public boolean populateDatabase() throws ExecutionException, InterruptedException {
		CompletableFuture<ApiResponse> dailyPricesPromise = fetchDataService.getBitcoinData(dailyPricesUrl);
		CompletableFuture.allOf(dailyPricesPromise);

		ApiResponse dailyPriceReponse = dailyPricesPromise.get();


		insertToDatabase(dailyPriceReponse.getBitcoinDataList());
		return true;
	}

	private void insertToDatabase(List<BitcoinData> bitcoinData) {
		for (BitcoinData data : bitcoinData) {
			bitcoinDataMapper.insertCheckout(data.getOpen());
		}
	}
}