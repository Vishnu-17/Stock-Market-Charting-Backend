package com.vishnu.StockMarketApplication.mapper;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import com.vishnu.StockMarketApplication.dto.SectorDto;
import com.vishnu.StockMarketApplication.model.Sector;

@Component
public class SectorMapper {

	public SectorDto toSectorDto(Sector sector) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(sector, SectorDto.class);
	}
	
	public Sector toSector(SectorDto sectorDto) {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper.map(sectorDto,Sector.class);
	}
	
	public List<SectorDto> toSectorDtos(List<Sector> sectors){
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return Arrays.asList(mapper.map(sectors, SectorDto[].class));
	}
}
