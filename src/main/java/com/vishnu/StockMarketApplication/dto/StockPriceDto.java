package com.vishnu.StockMarketApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StockPriceDto {
	private String id;
	private String companyCode;
	private String stockExchangeName;
	private double price;
	private String date;
	private String time;
}
