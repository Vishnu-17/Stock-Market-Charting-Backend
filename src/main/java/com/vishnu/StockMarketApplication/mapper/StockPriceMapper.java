package com.vishnu.StockMarketApplication.mapper;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.vishnu.StockMarketApplication.dto.StockPriceDto;
import com.vishnu.StockMarketApplication.model.StockPrice;

@Component
public class StockPriceMapper {
	public StockPriceDto toStockPriceDto(StockPrice stockPrice) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(stockPrice, StockPriceDto.class);
	}
	
	public StockPrice toStockPrice(StockPriceDto stockPriceDto) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(stockPriceDto,StockPrice.class);
	}
	
	public List<StockPriceDto> toStockPriceDtos(List<StockPrice> stockPrices){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return Arrays.asList(mapper.map(stockPrices, StockPriceDto[].class));
	}
}
