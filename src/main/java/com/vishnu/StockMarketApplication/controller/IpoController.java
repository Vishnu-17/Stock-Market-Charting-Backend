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

import com.vishnu.StockMarketApplication.dto.IpoDto;
import com.vishnu.StockMarketApplication.service.CompanyService;
import com.vishnu.StockMarketApplication.service.IpoService;

@RestController
@RequestMapping("/ipo")
@CrossOrigin
public class IpoController {

	@Autowired
	private IpoService ipoService;
	
	private CompanyService companyService;
	
	@GetMapping("/ipos")
	public ResponseEntity<List<IpoDto>> getAllIpos(){
		return ResponseEntity.ok(ipoService.getAllIpos());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<IpoDto> getIpoById(@PathVariable String id){
		return ResponseEntity.ok(ipoService.getIpoById(id));
	}
	
	@PostMapping("/add")
	public ResponseEntity<IpoDto> addIpo(@RequestBody IpoDto ipoDto){
		IpoDto ipo = new IpoDto();
		IpoDto ipo2 = new IpoDto();
		ResponseEntity<IpoDto> result = ResponseEntity.ok(ipoService.addIpo(ipoDto));
		ipo=result.getBody();
		ipo2.setId(ipo.getId());
		companyService.addIpoToCompany(ipoDto.getCompanyName(), ipo2);
		return result;
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<IpoDto> editIpo(@RequestBody IpoDto ipoDto,@PathVariable String id){
		return ResponseEntity.ok(ipoService.updateIpo(ipoDto,id));
	}
	
	@DeleteMapping("/{id}")
	public void deleteIpoById(@PathVariable String id) {
		ipoService.deleteById(id);
	}
}
