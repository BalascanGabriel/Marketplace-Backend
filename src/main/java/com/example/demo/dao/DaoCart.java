package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cart;
import com.example.demo.model.User;

@Repository
public interface DaoCart extends CrudRepository<Cart, Integer>{

//	@Query("SELECT p.name, p.price, c.quantity FROM Cart c JOIN c.product p WHERE c.user.id = :userId")
//    List<Object[]> getCartItemsForUser(@Param("userId") Integer id);
	
//	Iterable<Cart> findAllById(Integer id);
	
	// pasul 0: trebuie sa definim regula de "join"
	public List<Cart> findByUser(User userul);
	
	public List<Cart> findByUserId(int userId);
	public List<Cart> findByUserToken(String token);
	
	public Optional<Cart>findByUserIdAndProductId(int userId, int productId);
	
	// public Optional<Cart> findByProductAndUser(Product prod, User user);
	

}
