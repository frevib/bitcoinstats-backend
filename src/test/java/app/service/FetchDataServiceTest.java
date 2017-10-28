package app.service;

import app.model.ApiResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FetchDataServiceTest {

	@Autowired
	private FetchDataService fetchDataService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void getBitcoinData() throws Exception {
		String url = "https://min-api.cryptocompare.com/data/histominute?fsym=BTC&tsym=USD&limit=60&aggregate=3&e=CCCAGG";
		ApiResponse response = fetchDataService.getBitcoinData(url);

		assertNotNull(response);
		assertEquals(response.getResponseStatus(), "Success");
		assertEquals(response.getType(), 100);
	}

}