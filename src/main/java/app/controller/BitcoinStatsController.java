package app.controller;

import app.service.PopulateDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class BitcoinStatsController {

	private static final Logger LOG = LoggerFactory.getLogger(BitcoinStatsController.class);

	@Autowired
	private PopulateDatabaseService populateDatabaseService;

	@RequestMapping("/")
	public String test() {
		return "test me!";
	}

	@RequestMapping("/populate")
	public boolean populateDatabase() throws ExecutionException, InterruptedException {
		populateDatabaseService.populateDatabase();
		return true;
	}


	@ExceptionHandler(Exception.class)
	public void handleExceptions(Exception e) {
		LOG.error("--- something terrible happened....!", e);
	}

}
