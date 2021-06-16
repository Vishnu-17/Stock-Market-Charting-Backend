package com.vishnu.StockMarketApplication.service;

import java.text.ParseException;
import java.util.List;

import com.vishnu.StockMarketApplication.dto.CompanyCompareRequest;
import com.vishnu.StockMarketApplication.dto.SectorCompareRequest;
import com.vishnu.StockMarketApplication.dto.StockPriceDto;

public interface StockPriceService {
	public List<StockPriceDto> getAllStockPrices();
	public StockPriceDto addStockPrice(StockPriceDto stockPriceDto);
	public StockPriceDto updateStockPrice(StockPriceDto stockPriceDto);
	public void deleteById(String id);
	public StockPriceDto getStockPriceById(String id);
	public List<StockPriceDto> getStockPricesForCompanyComparison(String from,String to,String code,String stockExchangeName)throws ParseException;
	public List<StockPriceDto> getStockPricesForSectorComparison(SectorCompareRequest compareRequest)throws ParseException;
}
