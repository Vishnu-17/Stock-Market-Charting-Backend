package com.vishnu.StockMarketApplication.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockExchange {
	@Id
	private String id;
	private String name;
	private String description;
	private String address;
	private String remarks;
	
	@DBRef
	private List<Company> companies = new ArrayList<>();
}
