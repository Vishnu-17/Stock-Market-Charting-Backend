package com.vishnu.StockMarketApplication.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vishnu.StockMarketApplication.model.Sector;

@Repository
public interface SectorRepository extends MongoRepository<Sector, String>{

	Sector findByName(String sectorName);
	
}
