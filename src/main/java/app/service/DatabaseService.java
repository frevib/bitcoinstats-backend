package app.service;

import app.mapper.BitcoinDataMapper;
import app.model.ApiResponse;
import app.model.BitcoinData;
import app.model.BitcoinPrices;
import app.model.BitcoinPricesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class DatabaseService {

	private static final Logger LOG = LoggerFactory.getLogger(DatabaseService.class);

	@Value("${daily.prices.url}")
	private String dailyPricesUrl;

	@Value("${hourly.prices.url}")
	private String hourlyPricesUrl;

	@Value("${minutely.prices.url}")
	private String dailyMinutelyPricesUrl;

	private FetchDataService fetchDataService;

	private BitcoinDataMapper bitcoinDataMapper;

	public DatabaseService(FetchDataService fetchDataService, BitcoinDataMapper bitcoinDataMapper) {
		this.fetchDataService = fetchDataService;
		this.bitcoinDataMapper = bitcoinDataMapper;
	}

	public BitcoinPricesResponse findAll(String order) {
		List<BitcoinPrices> bitcoinPricesList = bitcoinDataMapper.findAll(order);

		// Just a separating database and response models, need to filter out columns that are not needed in response.
		return new BitcoinPricesResponse(bitcoinPricesList);
	}

	public boolean populateDatabase() throws ExecutionException, InterruptedException {
		LOG.info("Populating database.");
		CompletableFuture<ApiResponse> dailyPricesPromise = fetchDataService.getBitcoinData(dailyPricesUrl);
		CompletableFuture<ApiResponse> hourlyPricesPromise = fetchDataService.getBitcoinData(hourlyPricesUrl);
		CompletableFuture<ApiResponse> minutelyPricesPromise = fetchDataService.getBitcoinData(dailyMinutelyPricesUrl);
		CompletableFuture.allOf(dailyPricesPromise, hourlyPricesPromise, minutelyPricesPromise).join();

		ApiResponse dailyPriceResponse = dailyPricesPromise.get();
		ApiResponse hourlyPriceResponse = hourlyPricesPromise.get();
		ApiResponse minutelyPriceResponse = minutelyPricesPromise.get();

		insertToDatabase(
				dailyPriceResponse.getBitcoinDataList(),
				hourlyPriceResponse.getBitcoinDataList(),
				minutelyPriceResponse.getBitcoinDataList());
		return true;
	}

	private void insertToDatabase(
			List<BitcoinData> hourlyBitcoinData,
			List<BitcoinData> dailyBitcoinData,
			List<BitcoinData> minutelyBitcoinData) {

		for (int i = 0; i < hourlyBitcoinData.size(); i++) {
			bitcoinDataMapper.insertCheckout(
					dailyBitcoinData.get(i).getOpen(),
					hourlyBitcoinData.get(i).getOpen(),
					minutelyBitcoinData.get(i).getOpen());
		}
	}
}