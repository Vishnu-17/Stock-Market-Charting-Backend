package com.vishnu.StockMarketApplication.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vishnu.StockMarketApplication.model.StockExchange;

@Repository
public interface StockExchangeRepository extends MongoRepository<StockExchange, String>{

	StockExchange findByName(String stockExchangeName);

}
