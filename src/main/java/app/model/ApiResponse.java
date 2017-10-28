package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiResponse {

	@JsonProperty("Response")
	private String responseStatus;

	@JsonProperty("Type")
	private	int type;

	@JsonProperty("Aggregated")
	private boolean aggregated;

	@JsonProperty("Data")
	private List<BitcoinData> bitcoinDataList;

	public String getResponseStatus() {
		return responseStatus;
	}

	public int getType() {
		return type;
	}

	public boolean isAggregated() {
		return aggregated;
	}

	public List<BitcoinData> getBitcoinDataList() {
		return bitcoinDataList;
	}

	@Override
	public String toString() {
		return "ApiResponse{" +
				"responseStatus='" + responseStatus + '\'' +
				", type=" + type +
				", aggregated=" + aggregated +
				", bitcoinDataList=" + bitcoinDataList +
				'}';
	}
}
