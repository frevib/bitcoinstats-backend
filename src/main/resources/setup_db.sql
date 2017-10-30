DROP TABLE IF EXISTS bitcoin_data;

CREATE TABLE bitcoin_data (
  id INT PRIMARY KEY AUTO_INCREMENT,
  daily_price DOUBLE,
  hourly_price DOUBLE,
  minutely_price DOUBLE);

CREATE INDEX bitcoin_data_daily_price ON bitcoin_data (daily_price);
CREATE INDEX bitcoin_data_hourly_price ON bitcoin_data (hourly_price);
CREATE INDEX bitcoin_data_minutely_price ON bitcoin_data (minutely_price);