package com.vishnu.StockMarketApplication.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vishnu.StockMarketApplication.model.Sector;

@Repository
public interface SectorRepository extends MongoRepository<Sector, String>{

	List<Sector> findByName(String sectorName);
	
}
