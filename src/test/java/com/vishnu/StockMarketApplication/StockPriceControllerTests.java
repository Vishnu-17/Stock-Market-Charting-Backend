package com.vishnu.StockMarketApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishnu.StockMarketApplication.controller.StockPriceController;
import com.vishnu.StockMarketApplication.dto.StockPriceDto;
import com.vishnu.StockMarketApplication.service.StockPriceService;

@WebMvcTest(StockPriceController.class)
public class StockPriceControllerTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@MockBean
	StockPriceService stockPriceService;
	 StockPriceDto record_1 = new StockPriceDto("1","001","BSE",250.00,"16-06-2021","10:30");
	 StockPriceDto record_2 = new StockPriceDto("2","002","NSE",300.00,"15-06-2021","10:30");
	
	 @Test
	 public void getAllStockPrices()throws Exception{
		 List<StockPriceDto> stockPrices = new ArrayList<>();
		 stockPrices.add(record_1);
		 stockPrices.add(record_2);
		 Mockito.when(stockPriceService.getAllStockPrices()).thenReturn(stockPrices);
		 mockMvc.perform(MockMvcRequestBuilders
				 .get("/stock-price/stock-prices")
				 .contentType(MediaType.APPLICATION_JSON))
		 			.andExpect(status().isOk())
		 			.andExpect(jsonPath("$[1].companyCode", is("002")));
	 }
	 
	 @Test
	 public void getStockPriceById()throws Exception{
		 List<StockPriceDto> stockPrices = new ArrayList<>();
		 stockPrices.add(record_1);
		 stockPrices.add(record_2);
		 Mockito.when(stockPriceService.getStockPriceById("1")).thenReturn(record_1);
		 mockMvc.perform(MockMvcRequestBuilders
				 .get("/stock-price/1")
				 .contentType(MediaType.APPLICATION_JSON))
		 		.andExpect(status().isOk())
		 		.andExpect(jsonPath("$.companyCode", is("001")));
	 }
	 
	 @Test
	 public void addStockPrice()throws Exception{
		 Mockito.when(stockPriceService.addStockPrice(record_1)).thenReturn(record_1);
		 MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/stock-price/add")
				 								.contentType(MediaType.APPLICATION_JSON)
				 								.accept(MediaType.APPLICATION_JSON)
				 								.content(this.mapper.writeValueAsString(record_1));
		 mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$.companyCode", is("001")));
	 }
	 
	 @Test
	 public void deleteStockPrice()throws Exception{
		 List<StockPriceDto> stockPrices = new ArrayList<>();
		 stockPrices.add(record_1);
		 Mockito.when(stockPriceService.getStockPriceById(record_1.getId())).thenReturn(record_1);
		 mockMvc.perform(MockMvcRequestBuilders
				 .delete("/stock-price/1")
				 .contentType(MediaType.APPLICATION_JSON))
		 		.andExpect(status().isOk());
	 }
}
