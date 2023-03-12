package com.example.demo.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoUser;
import com.example.demo.model.User;

@Service
public class UserService {
	 private final DaoUser userDao;

	 public UserService(DaoUser userDao) {
	     this.userDao = userDao;
	 }
	 
	 public Iterable<User> findAll() {
			// TODO Auto-generated method stub
			return userDao.findAll();
		}
	 
	 public User register(User user) {
	        user.setDateAdded(new Date());
	        return userDao.save(user);
	    }
	 
	 public Optional<User> findByUsername(String username) {
	        return userDao.findByUsername(username);
	    }
	 
	 public boolean authenticate(String username, String password) {
	        Optional<User> userOptional = userDao.findByUsername(username);
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	            return user.getPassword().equals(password);
	        }
	        return false;
	    }


	
}
