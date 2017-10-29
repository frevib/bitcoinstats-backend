package app.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BitcoinDataMapper {

	@Insert("insert into bitcoin_data (daily_price) values (#{dailyPrice})")
	int insertCheckout(@Param("dailyPrice") double dailyPrice);
}