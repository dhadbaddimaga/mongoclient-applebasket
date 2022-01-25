package com.basket.apple.mongotemplate.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.basket.apple.mongotemplate.service.BasketService;

@RestController
@RequestMapping("/baskets")
public class BasketController {

	@Autowired
	BasketService basketService;
	
	@GetMapping("/")
	public List<Object> getAllBaskets(){
		return basketService.getAllBaskets();
	}
	
	
	@GetMapping("/{id}")
	public List<Object> getBasketbyId(@RequestParam String id){
		
		System.out.println(id);
		return basketService.getBasketbyId(id);
	}
}