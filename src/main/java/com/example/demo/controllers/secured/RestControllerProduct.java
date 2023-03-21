package com.example.demo.controllers.secured;

import java.util.Date;

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

import com.example.demo.dao.DaoProduct;
import com.example.demo.model.Product;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest-secured/product")
@CrossOrigin(value = {"http://localhost:4200/"})
public class RestControllerProduct {

	@Autowired
	private DaoProduct dao;
	
	@Autowired
	private UserService service;
	
	@GetMapping("/all")
	public Iterable<Product> findAll(){
		return dao.findAll();
	}
	
	@PutMapping("/update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		if(product.getId() == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(dao.save(product));
	}
	
	@PostMapping("/save")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
		if(product.getId() != null) {
			return ResponseEntity.badRequest().build();

		}
		product.setDateAdded(new Date());
		return ResponseEntity.ok(dao.save(product));
	}
	
	@DeleteMapping("/delete/{id}")
	public Product deleteProduct(@PathVariable("id") int idProduct) {
		Product theProduct = dao.findById(idProduct).get();
		dao.delete(theProduct);
		return theProduct;
	}
	
	@GetMapping("/by-id/{id}")
	public Product selectById(@PathVariable("id") int idProduct) {
		Product product = dao.findById(idProduct).get();
		
		return product;
	}
	
}
