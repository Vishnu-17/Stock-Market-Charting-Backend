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
import com.vishnu.StockMarketApplication.controller.SectorController;
import com.vishnu.StockMarketApplication.dto.SectorDto;
import com.vishnu.StockMarketApplication.service.SectorService;

@WebMvcTest(SectorController.class)
public class SectorControllerTests {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	@MockBean
	SectorService sectorService;
	
	SectorDto record_1 = new SectorDto("1","Finance","Money");
	SectorDto record_2 = new SectorDto("2","Health","Well-Being");
	@Test
	public void getAllSectors()throws Exception{
		List<SectorDto> sectors;
		sectors = new ArrayList<>();
		sectors.add(record_1);
		sectors.add(record_2);
		Mockito.when(sectorService.getAllSectors()).thenReturn(sectors);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/sector/sectors")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[1].name", is("Health")));
	}
	
	@Test
	public void getSectorById()throws Exception{
		List<SectorDto> sectors;
		sectors = new ArrayList<>();
		sectors.add(record_1);
		sectors.add(record_2);
		Mockito.when(sectorService.getSectorById("2")).thenReturn(record_2);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/sector/2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Health")));
	}
	
	@Test
	public void addSector()throws Exception{
		List<SectorDto> sectors;
		sectors = new ArrayList<>();
		Mockito.when(sectorService.addSector(record_1)).thenReturn(record_1);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/sector/add")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(record_1));
		
		mockMvc.perform(mockRequest)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.name", is("Finance")));
	}
	
	@Test
	public void deleteSector()throws Exception{
		List<SectorDto> sectors;
		sectors = new ArrayList<>();
		sectors.add(record_1);
		sectors.add(record_2);
		Mockito.when(sectorService.getSectorById(record_1.getId())).thenReturn(record_1);
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/sector/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
