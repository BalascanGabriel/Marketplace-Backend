package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.InvalidUserException;
import com.example.demo.model.Cart;
import com.example.demo.services.CartService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rest/cart")
@CrossOrigin(value = {"http://localhost:4200/"})
public class RestControllerCart {

	private final CartService cartService;
	
	public RestControllerCart(CartService cartService) {
		this.cartService = cartService;
	}
	
//	@GetMapping("/{userId}")
//	public List<Object[]> getCartProducts(@PathVariable Integer userId) {
//	    return cartService.findAll(userId);
//	}
//	
	@GetMapping("/all")
	public Iterable<Cart> getAll(){
		return cartService.findAll();
	}

	// NOT OKAY: daca un user "vede" cum se face un request poate retrimite the request din postman for instance
	// i.e. am id-ul unui user = am cart-ul unui user
//	@GetMapping("/by-user-id/{uid}")
//	public List<Cart> getAllByUserId(@PathVariable("uid") int uid){
//		// uid din url -> cartService (1)
//		return cartService.findAllByUser(uid);
//	}
//	
//	@GetMapping("/by-user-id-v2/{uid}")
//	public List<Cart> getAllByUserId2(@PathVariable("uid") int uid){
//		// uid din url -> cartService (1)
//		return cartService.findAllByUser(uid);
//	}
//	
	@GetMapping("/my-cart")
	public ResponseEntity< List<Cart>> getMyCartItems(HttpServletRequest request){
		
		String token = request.getHeader("myToken");
		System.out.println("TOKEN: " + token);
		
		
		if(token == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

		}
		
		if(token.length() == 0) {
			return ResponseEntity.status(HttpStatus.LOCKED).build();
		}
		
		// 1. token e null
		// 2. token e empty
		// 3. token e invalid
		// 4. ???
		
		List<Cart> myCartItems = null;
		try {
			myCartItems = cartService.findMyCart(token);
		} catch (InvalidUserException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(myCartItems);
		
		
	}
}
