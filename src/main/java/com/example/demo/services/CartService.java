package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoCart;
import com.example.demo.dao.DaoUser;
import com.example.demo.exceptions.InvalidUserException;
import com.example.demo.model.Cart;
import com.example.demo.model.User;

@Service
public class CartService {

	@Autowired
	private DaoCart cartDao;

	@Autowired
	private DaoUser daoUser;

	public List<Object[]> findAll(Integer id) {

//		return cartDao.getCartItemsForUser(id);
		return null;
	}

	public Iterable<Cart> findAll() {
		return cartDao.findAll();
	}

	public List<Cart> findAllByUser(int userId) {
		// prin daoUser se incarca the actual User object (2)
		Optional<User> userul = daoUser.findById(userId);
		if (!userul.isPresent()) {
			throw new RuntimeException("No user found!!!");
		}

		// putem apela metoda din DAO care "stie" (pentru ca avem ManyToOne) cum sa faca
		// join
		return cartDao.findByUser(userul.get());
	}

	public List<Cart> findAllByUserDupaId(int userId) {
		return cartDao.findByUserId(userId);

	}
	
	public List<Cart> findMyCart(String token) throws InvalidUserException{
		Optional<User> opUser = daoUser.findByToken(token);
		if(!opUser.isPresent()) {
			throw new InvalidUserException();
		}
		return cartDao.findByUserToken(token);
	}

}
