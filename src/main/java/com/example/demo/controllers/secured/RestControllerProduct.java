package com.example.demo.controllers.secured;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.example.demo.dao.DaoCart;
import com.example.demo.dao.DaoPaginareProduct;
import com.example.demo.dao.DaoProduct;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest-secured/product")
@CrossOrigin(value = {"http://localhost:4200/"})
public class RestControllerProduct {

	@Autowired
	private DaoProduct dao;
	
	@Autowired
	private DaoCart daoCart;
	
	@Autowired
	private DaoPaginareProduct daoPaginare;
	
	@Autowired
	private UserService service;
	
	@GetMapping("/all")
	public Iterable<Product> findAll(){
		
		return dao.findAll();
	}
	
	@GetMapping("/all-paginare/{pagina}/{elementePePagina}")
	public Page<Product> findAllCuPaginare(@PathVariable("pagina") int nrPagina,
			@PathVariable("elementePePagina") int nrElemente){
		Page<Product> primaPagina = null;
		Pageable pageable = PageRequest.of(nrPagina, nrElemente, Sort.by("name").ascending());
		primaPagina = daoPaginare.findAll(pageable);
		return primaPagina;
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
	
	@PostMapping("/add-to-cart")
	public ResponseEntity<Cart> addProductToCart(@RequestBody Product productToAdd, HttpServletRequest request) {
		
		// TODO: daca deja avem randul corespunzator in cart (pentru user si pentru product)
			// dorim sa incrementam quantity in loc sa inseram un nou rand
		
		String token = request.getHeader("myToken");
		Optional<User> userLogat = service.findByToken(token);
		if(!userLogat.isPresent()) {
			
			// throw new RuntimeException("UNAUTHORIZED ACCESS TO CART!");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		System.out.println("Adding to cart: " + productToAdd);
		Cart cartRow = new Cart();
		cartRow.setProduct(productToAdd);
		cartRow.setQuantity(1);
		cartRow.setUser(userLogat.get());
		
		return ResponseEntity.ok(daoCart.save(cartRow));
		
	}
	
}
