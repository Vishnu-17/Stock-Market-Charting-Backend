package com.vishnu.StockMarketApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class IpoDto {
	private String id;
	private String companyName;
	private String stockExchangeName;
	private double price;
	private int shares;
	private String openDateTime;
	private String remarks;
}
