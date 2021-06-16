package com.vishnu.StockMarketApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockExchangeDto {
	private String id;
	private String name;
	private String description;
	private String address;
	private String remarks;
}
