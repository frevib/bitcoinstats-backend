package app.model;

public class BitcoinPrices {

	private int id;
	private double dailyPrice;
	private double hourlyPrice;
	private double minutelyPrice;

	public int getId() {
		return id;
	}

	public double getDailyPrice() {
		return dailyPrice;
	}

	public double getHourlyPrice() {
		return hourlyPrice;
	}

	public double getMinutelyPrice() {
		return minutelyPrice;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDailyPrice(double dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	public void setHourlyPrice(double hourlyPrice) {
		this.hourlyPrice = hourlyPrice;
	}

	public void setMinutelyPrice(double minutelyPrice) {
		this.minutelyPrice = minutelyPrice;
	}
}
