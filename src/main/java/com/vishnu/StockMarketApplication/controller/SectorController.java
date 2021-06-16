package com.vishnu.StockMarketApplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishnu.StockMarketApplication.dto.CompanyDto;
import com.vishnu.StockMarketApplication.dto.SectorDto;
import com.vishnu.StockMarketApplication.service.SectorService;

@RestController
@RequestMapping("/sector")
@CrossOrigin
public class SectorController {

	@Autowired
	private SectorService sectorService;
	
	@GetMapping("/sectors")
	public ResponseEntity<List<SectorDto>> getAllSectors(){
		return ResponseEntity.ok(sectorService.getAllSectors());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SectorDto> getSectorById(@PathVariable String id){
		return ResponseEntity.ok(sectorService.getSectorById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<SectorDto> addSector(@RequestBody SectorDto sectorDto){
		return ResponseEntity.ok(sectorService.addSector(sectorDto));
	}
	
	@PutMapping("/update")
	public ResponseEntity<SectorDto> editSector(@RequestBody SectorDto sectorDto){
		return ResponseEntity.ok(sectorService.updateSector(sectorDto));
	}
	
	@DeleteMapping("/{id}")
	public void deleteSectorById(@PathVariable String id) {
		sectorService.deleteById(id);
	}
	
	@PostMapping("/{sectorName}/companies")
	public void addCompanyToSector(@PathVariable String sectorName,CompanyDto companyDto) {
		sectorService.addCompanyToSector(sectorName, companyDto);
	}
}
