package com.vishnu.StockMarketApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectorCompareRequest {
	private String sectorName;
	private String stockExchangeName;
	private String fromPeriod;
	private String toPeriod;
	private String periodicity;
}
