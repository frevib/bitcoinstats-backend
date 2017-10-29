package app.service;

import app.model.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class FetchDataService {

	private static final Logger LOG = LoggerFactory.getLogger(FetchDataService.class);

	private final RestTemplate restTemplate;

	public FetchDataService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Async
	public CompletableFuture<ApiResponse> getBitcoinData(String url) {
		LOG.info("Fetching bitcoin price information from external API.");

		ApiResponse response = restTemplate.getForObject(url, ApiResponse.class);
		return CompletableFuture.completedFuture(response);
	}
}
