package com.vishnu.StockMarketApplication.dao;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vishnu.StockMarketApplication.model.Company;
@Repository
public interface CompanyRepository extends MongoRepository<Company, String>{

	List<Company> findByName(String companyName);

	List<Company> findByCode(String companyCode);
	
}
