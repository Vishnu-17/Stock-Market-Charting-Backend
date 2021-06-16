package com.vishnu.StockMarketApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectorDto {
	private String id;
	private String name;
	private String description;
}
