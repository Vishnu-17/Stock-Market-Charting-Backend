package com.vishnu.StockMarketApplication.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vishnu.StockMarketApplication.dao.StockExchangeRepository;
import com.vishnu.StockMarketApplication.dto.CompanyDto;
import com.vishnu.StockMarketApplication.dto.StockExchangeDto;
import com.vishnu.StockMarketApplication.mapper.CompanyMapper;
import com.vishnu.StockMarketApplication.mapper.StockExchangeMapper;
import com.vishnu.StockMarketApplication.model.StockExchange;
import com.vishnu.StockMarketApplication.service.StockExchangeService;
@Service
public class StockExchangeImpl implements StockExchangeService{

	@Autowired
	private StockExchangeRepository stockExchangeRepository;
	
	@Autowired
	private StockExchangeMapper stockExchangeMapper;
	
	@Autowired
	private CompanyMapper companyMapper;
	
	@Override
	public List<StockExchangeDto> getAllStockExchanges() {
		return stockExchangeMapper.toStockExchangeDtos(stockExchangeRepository.findAll());
	}
	
	@Override
	public StockExchangeDto getStockExchangeById(String id) {
		Optional<StockExchange> stockExchange = stockExchangeRepository.findById(id);
		if(!stockExchange.isPresent()) {
			return null;
		}
		return stockExchangeMapper.toStockExchangeDto(stockExchange.get());
	}

	@Override
	public StockExchangeDto addStockExchange(StockExchangeDto stockExchangeDto) {
		StockExchange stockExchange = stockExchangeMapper.toStockExchange(stockExchangeDto);
		stockExchange = stockExchangeRepository.save(stockExchange);
		return stockExchangeMapper.toStockExchangeDto(stockExchange);
	}

	@Override
	public StockExchangeDto updateStockExchange(StockExchangeDto stockExchangeDto) {
		if(getStockExchangeById(stockExchangeDto.getId()) == null) {
			return null;
		}
		StockExchange stockExchange = stockExchangeMapper.toStockExchange(stockExchangeDto);
		stockExchange = stockExchangeRepository.save(stockExchange);
		return stockExchangeMapper.toStockExchangeDto(stockExchange);
	}

	@Override
	public void deleteById(String id) {
		stockExchangeRepository.deleteById(id);
	}

	@Override
	public List<CompanyDto> getCompanies(String id) {
		Optional<StockExchange> stockExchange = stockExchangeRepository.findById(id);
		if(!stockExchange.isPresent()) {
			return null;
		}
		return companyMapper.toCompanyDtos(stockExchange.get().getCompanies());
	}

	@Override
	public StockExchangeDto addCompanyToStockExchange(String stockExchangeName, CompanyDto companyDto) {
		List<StockExchange> stockExchanges = stockExchangeRepository.findByName(stockExchangeName);
		StockExchange stockExchange = stockExchanges.get(0);
		if(stockExchange == null) {
			return null;
		}
		stockExchange.getCompanies().add(companyMapper.toCompany(companyDto));
		stockExchange = stockExchangeRepository.save(stockExchange);
		return stockExchangeMapper.toStockExchangeDto(stockExchange);
	}

	

}
