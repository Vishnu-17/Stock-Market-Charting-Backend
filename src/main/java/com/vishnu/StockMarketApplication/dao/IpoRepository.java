package com.vishnu.StockMarketApplication.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.vishnu.StockMarketApplication.model.Ipo;

@Repository
public interface IpoRepository extends MongoRepository<Ipo, String>{

}
