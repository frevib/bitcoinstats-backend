package app.controller;

import app.model.BitcoinPricesResponse;
import app.service.DatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.concurrent.ExecutionException;

@RestController
public class BitcoinStatsController {

	private static final Logger LOG = LoggerFactory.getLogger(BitcoinStatsController.class);

	@Autowired
	private DatabaseService databaseService;

	@RequestMapping("/")
	public String test() {
		return "test me!";
	}

	@RequestMapping("/populate")
	public boolean populateDatabase() throws ExecutionException, InterruptedException {
		databaseService.populateDatabase();
		return true;
	}

	@RequestMapping("/getbtprices")
	public BitcoinPricesResponse getBitcoinPrices(@QueryParam("order") String order) {
		BitcoinPricesResponse bitcoinPricesResponse = databaseService.findAll("hourly_price");

		return bitcoinPricesResponse;
	}


	@ExceptionHandler(Exception.class)
	public void handleExceptions(Exception e) {
		LOG.error("--- something terrible happened....!", e);
	}

}
