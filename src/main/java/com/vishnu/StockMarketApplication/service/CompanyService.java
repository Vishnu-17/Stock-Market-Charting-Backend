package com.vishnu.StockMarketApplication.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vishnu.StockMarketApplication.dto.CompanyDto;
import com.vishnu.StockMarketApplication.dto.IpoDto;
import com.vishnu.StockMarketApplication.dto.StockPriceDto;
//@Service
public interface CompanyService {
	public List<CompanyDto> getAllCompanies();
	public CompanyDto addCompany(CompanyDto companyDto);
	public CompanyDto updateCompany(CompanyDto companyDto);
	public void deleteById(String id);
	public CompanyDto getCompanyById(String id);
	public CompanyDto addIpoToCompany(String companyName, IpoDto ipoDto);
	public List<IpoDto> getCompanyIpoDetails(String id);
	public CompanyDto addStockPriceToCompany(String companyCode, StockPriceDto stockPriceDto);
	public List<StockPriceDto> getStockPrices(String id);
}
