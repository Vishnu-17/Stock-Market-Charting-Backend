package com.vishnu.StockMarketApplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vishnu.StockMarketApplication.dto.IpoDto;
//@Service
public interface IpoService {
	public List<IpoDto> getAllIpos();
	public IpoDto addIpo(IpoDto ipoDto);
	public IpoDto updateIpo(IpoDto ipoDto,String id);
	public void deleteById(String id);
	public IpoDto getIpoById(String id);
}
