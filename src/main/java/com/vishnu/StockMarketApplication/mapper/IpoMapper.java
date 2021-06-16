package com.vishnu.StockMarketApplication.mapper;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.vishnu.StockMarketApplication.dto.IpoDto;
import com.vishnu.StockMarketApplication.model.Ipo;

@Component
public class IpoMapper {
	public IpoDto toIpoDto(Ipo ipo) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(ipo, IpoDto.class);
	}
	
	public Ipo toIpo(IpoDto ipoDto) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(ipoDto,Ipo.class);
	}
	
	public List<IpoDto> toIpoDtos(List<Ipo> ipos){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return Arrays.asList(mapper.map(ipos, IpoDto[].class));
	}
}
