package com.vishnu.StockMarketApplication;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vishnu.StockMarketApplication.controller.CompanyController;
import com.vishnu.StockMarketApplication.dao.CompanyRepository;
import com.vishnu.StockMarketApplication.dto.CompanyDto;
import com.vishnu.StockMarketApplication.dto.IpoDto;
import com.vishnu.StockMarketApplication.dto.StockPriceDto;
import com.vishnu.StockMarketApplication.model.Company;
import com.vishnu.StockMarketApplication.model.Ipo;
import com.vishnu.StockMarketApplication.model.StockPrice;
import com.vishnu.StockMarketApplication.service.CompanyService;


//@SpringBootTest
@WebMvcTest(CompanyController.class)
public class CompanyControllerTests {
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@MockBean
	CompanyService companyService;
	
	List<Ipo> ipos = new ArrayList<Ipo>();
	List<StockPrice> stockPrices= new ArrayList<StockPrice>();
	CompanyDto Record_1 = new CompanyDto("1","Apple","002","5B","Tim Cook","Steve","BSE","Finance","Products"); 
	CompanyDto Record_2 = new CompanyDto("2","Samsung","003","4B","Tim Cook","Steve","BSE","Finance","Products");
	@Test
	public void get_All() throws Exception{
		List<CompanyDto> companies;
		companies = new ArrayList<>();
		companies.add(Record_1);
		companies.add(Record_2);
		Mockito.when(companyService.getAllCompanies()).thenReturn(companies);
		
		mockMvc.perform(MockMvcRequestBuilders
					.get("/company/companies")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					//.andExpect(jsonPath("$.name", is("Samsung")));
					.andExpect(jsonPath("$[1].name",is("Samsung")));
	}
	@Test
	public void get_Id() throws Exception{
		List<CompanyDto> companies;
		companies = new ArrayList<>();
		companies.add(Record_1);
		companies.add(Record_2);
		Mockito.when(companyService.getCompanyById("1")).thenReturn(Record_1);
		
		mockMvc.perform(MockMvcRequestBuilders
					.get("/company/1")
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.name", is("Apple")));
			//		.andExpect(jsonPath("$",hasSize(1)));
	}
	
	@Test
	public void createCompany() throws Exception {
		
	    CompanyDto record = new CompanyDto("3","Microsoft","003","7B","Nadella","Bill Gates","BSE","Finance","Products");

	    Mockito.when(companyService.addCompany(record)).thenReturn(record);

	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/company/add")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(record));

	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue()))
	            .andExpect(jsonPath("$.name", is("Microsoft")));
	    }
	@Test
	public void deleteCompanyById() throws Exception {
	    Mockito.when(companyService.getCompanyById(Record_2.getId())).thenReturn((Record_2));

	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/company/2")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	
	
	
}
