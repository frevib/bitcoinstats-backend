package app.service;

import app.model.ApiResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FetchDataService {

	private final RestTemplate restTemplate;

	public FetchDataService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public ApiResponse getBitcoinData(String url) {
		ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);

		return response;
	}
}
