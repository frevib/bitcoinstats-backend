package app.mapper;


import app.model.BitcoinPrices;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BitcoinDataMapper {

	@Insert("insert into bitcoin_data (daily_price, hourly_price, minutely_price) " +
			"values (#{daily_price}, #{hourly_price}, #{minutely_price})")
	int insertCheckout(
			@Param("daily_price") double dailyPrice,
			@Param("hourly_price") double hourlyPrice,
			@Param("minutely_price") double minutelyPrice);


	@Results({
			@Result(property = "id", column = "id"),
			@Result(property = "dailyPrice", column = "daily_price"),
			@Result(property = "hourlyPrice", column = "hourly_price"),
			@Result(property = "minutelyPrice", column = "minutely_price")
	})
	@Select("select * from bitcoin_data order by ${order} desc limit 25")
	List<BitcoinPrices> findAll(@Param("order") String order);
}