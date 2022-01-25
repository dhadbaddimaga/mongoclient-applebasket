package com.basket.apple.mongotemplate.service;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basket.apple.mongotemplate.repository.BasketRepository;

@Service
public class BasketService {

	@Autowired
	BasketRepository basketRepository;

	public List<Object> getAllBaskets() {
		return basketRepository.getAllBaskets();
	}


	public List<Object> getBasketbyId(String id) {
		return basketRepository.getBasketbyId(id);
	}
}
