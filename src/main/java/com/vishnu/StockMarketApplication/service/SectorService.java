package com.vishnu.StockMarketApplication.service;

import java.util.List;

import com.vishnu.StockMarketApplication.dto.CompanyDto;
import com.vishnu.StockMarketApplication.dto.SectorDto;

public interface SectorService {
	public List<SectorDto> getAllSectors();
	public SectorDto addSector(SectorDto sectorDto);
	public SectorDto updateSector(SectorDto sectorDto);
	public void deleteById(String id);
	public SectorDto getSectorById(String id);
	public SectorDto addCompanyToSector(String sectorName, CompanyDto companyDto);
}
