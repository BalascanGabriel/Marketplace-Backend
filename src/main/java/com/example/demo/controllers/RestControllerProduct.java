package com.example.demo.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DaoProduct;
import com.example.demo.model.Product;

@RestController
@RequestMapping("/rest/product")
@CrossOrigin(value = {"http://localhost:4200/"})
public class RestControllerProduct {

	@Autowired
	private DaoProduct dao;
	
	@GetMapping("/all")
	public Iterable<Product> findAll(){
		return dao.findAll();
	}
	
	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product product) {
		product.setDateAdded(new Date());
		return dao.save(product);
	}
	
	@DeleteMapping("/delete/{id}")
	public Product deleteProduct(@PathVariable("id") int idProduct) {
		Product theProduct = dao.findById(idProduct).get();
		dao.delete(theProduct);
		return theProduct;
	}
	
}
