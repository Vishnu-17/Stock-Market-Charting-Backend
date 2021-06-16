package com.vishnu.StockMarketApplication.dao;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vishnu.StockMarketApplication.model.Company;
@Repository
public interface CompanyRepository extends MongoRepository<Company, String>{

	Company findByName(String companyName);

	Company findByCode(String companyCode);
	
}
