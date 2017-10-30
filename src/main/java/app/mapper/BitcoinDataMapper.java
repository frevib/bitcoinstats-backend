package app.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BitcoinDataMapper {

	@Insert("insert into bitcoin_data (daily_price, hourly_price,minutely_price) " +
			"values (#{daily_price}, #{hourly_price}, #{minutely_price})")
	int insertCheckout(
			@Param("daily_price") double dailyPrice,
			@Param("hourly_price") double hourlyPrice,
			@Param("minutely_price") double minutelyPrice);
}