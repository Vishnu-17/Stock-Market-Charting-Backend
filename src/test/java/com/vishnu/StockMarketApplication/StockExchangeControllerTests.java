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
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishnu.StockMarketApplication.controller.StockExchangeController;
import com.vishnu.StockMarketApplication.dto.StockExchangeDto;
import com.vishnu.StockMarketApplication.service.StockExchangeService;

@WebMvcTest(StockExchangeController.class)
public class StockExchangeControllerTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@MockBean
	StockExchangeService stockExchangeService;
	StockExchangeDto record_1 = new StockExchangeDto("1","BSE","Buisness","Dalal St.","AAA");
	StockExchangeDto record_2 = new StockExchangeDto("2","NSE","Buisness","Delhi","AAA");
	
	@Test
	public void getAllStockExchanges()throws Exception{
		List<StockExchangeDto> stockExchanges;
		stockExchanges = new ArrayList<>();
		stockExchanges.add(record_1);
		stockExchanges.add(record_2);
		Mockito.when(stockExchangeService.getAllStockExchanges()).thenReturn(stockExchanges);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/stock-exchange/stock-exchanges")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].name", is("NSE")));
	}
	
	@Test
	public void getStockExchangeById()throws Exception{
		List<StockExchangeDto> stockExchanges;
		stockExchanges = new ArrayList<>();
		stockExchanges.add(record_1);
		stockExchanges.add(record_2);
		Mockito.when(stockExchangeService.getStockExchangeById("1")).thenReturn(record_1);
		mockMvc.perform(MockMvcRequestBuilders
				.get("/stock-exchange/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("BSE")));
	}
	
	@Test
	public void addStockExchange()throws Exception{
		Mockito.when(stockExchangeService.addStockExchange(record_1)).thenReturn(record_1);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/stock-exchange/add")
													.contentType(MediaType.APPLICATION_JSON)
													.accept(MediaType.APPLICATION_JSON)
													.content(this.mapper.writeValueAsString(record_1));
		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$.name", is("BSE")));
	}
	
	@Test
	public void deleteStockExchange()throws Exception{
		List<StockExchangeDto> stockExchanges;
		stockExchanges = new ArrayList<>();
		stockExchanges.add(record_1);
		stockExchanges.add(record_2);
		Mockito.when(stockExchangeService.getStockExchangeById(record_1.getId())).thenReturn(record_1);
		mockMvc.perform(MockMvcRequestBuilders.delete("/stock-exchange/1")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
}
