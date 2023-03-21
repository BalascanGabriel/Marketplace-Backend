package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.RandomStringProvider;

@RequestMapping("/rest/random")
@RestController
public class RestRandomController {

//	RandomStringProvider rsp = new RandomStringProvider();
	@Autowired
	private RandomStringProvider rsp;
	
	@GetMapping("/test")
	public String generareSirRandom() {
		
		return rsp.getARandomString();
	}
	
}
