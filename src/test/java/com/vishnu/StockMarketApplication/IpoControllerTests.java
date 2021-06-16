package com.vishnu.StockMarketApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
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
import com.vishnu.StockMarketApplication.controller.IpoController;
import com.vishnu.StockMarketApplication.dto.IpoDto;
import com.vishnu.StockMarketApplication.service.IpoService;

@WebMvcTest(IpoController.class)
public class IpoControllerTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@MockBean
	IpoService ipoService;
	
	IpoDto record_1 = new IpoDto("1","Apple","BSE",300.00,2500,"21-10-2020 09:30","AAA");
	IpoDto record_2 = new IpoDto("2","Samsung","NSE",200.00,2000,"21-10-2020 09:30","AAA");
	@Test
	public void getAllIpos()throws Exception{
		List<IpoDto> ipos;
		ipos = new ArrayList<>();
		ipos.add(record_1);
		ipos.add(record_2);
		Mockito.when(ipoService.getAllIpos()).thenReturn(ipos);
		
		mockMvc.perform(MockMvcRequestBuilders
					.get("/ipo/ipos")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}
	@Test
	public void getIpoById()throws Exception{
		List<IpoDto> ipos;
		ipos = new ArrayList<>();
		ipos.add(record_1);
		ipos.add(record_2);
		Mockito.when(ipoService.getIpoById("1")).thenReturn(record_1);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/ipo/1")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk())
				.andExpect(jsonPath("$.stockExchangeName", is("BSE")));
	}
	@Test
	public void createIpo()throws Exception{
		List<IpoDto> ipos;
		ipos = new ArrayList<>();
		ipos.add(record_1);
		ipos.add(record_2);
		IpoDto record_3 = new IpoDto("3","Microsoft","BSE",250.00,2000,"31-10-2020 10:30","AAA");
		Mockito.when(ipoService.addIpo(record_3)).thenReturn(record_3);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/ipo/add")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(record_3));
		
		mockMvc.perform(mockRequest)
					.andExpect(status().isOk())
					//.andExpect(jsonPath("$[2]", notNullValue()))
					.andExpect(jsonPath("$.companyName", is("Microsoft")));
	}
	@Test
	public void deleteIpo()throws Exception{
		List<IpoDto> ipos;
		ipos = new ArrayList<>();
		ipos.add(record_1);
		ipos.add(record_2);
		Mockito.when(ipoService.getIpoById(record_2.getId())).thenReturn(record_2);
		
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/ipo/2")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
	}
}
