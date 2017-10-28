package app.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BitcoinData {

	@JsonProperty("time")
	private long time;

	@JsonProperty("close")
	private double close;

	@JsonProperty("high")
	private double high;

	@JsonProperty("low")
	private double low;

	@JsonProperty("open")
	private double open;

	public long getTime() {
		return time;
	}

	public double getClose() {
		return close;
	}

	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	public double getOpen() {
		return open;
	}

	@Override
	public String toString() {
		return "BitcoinData{" +
				"time=" + time +
				", close=" + close +
				", high=" + high +
				", low=" + low +
				", open=" + open +
				'}';
	}
}
