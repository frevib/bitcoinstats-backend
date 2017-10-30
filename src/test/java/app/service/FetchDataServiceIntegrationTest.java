package app.service;

import app.model.ApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CompletableFuture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FetchDataServiceIntegrationTest {

	@Autowired
	private FetchDataService fetchDataService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getBitcoinData() throws Exception {
		// prepare
		String url = "https://min-api.cryptocompare.com/data/histominute?fsym=BTC&tsym=USD&limit=60&aggregate=3&e=CCCAGG";

		// run
		CompletableFuture<ApiResponse> responsePromise = fetchDataService.getBitcoinData(url);
		CompletableFuture.allOf(responsePromise);
		ApiResponse response = responsePromise.get();

		// verify
		assertNotNull(response);
		assertEquals(response.getResponseStatus(), "Success");
		assertEquals(response.getType(), 100);
	}
}