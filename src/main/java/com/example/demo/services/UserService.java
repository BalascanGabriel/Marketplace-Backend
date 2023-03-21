package com.example.demo.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DaoUser;
import com.example.demo.model.User;

@Service
public class UserService {
	 private final DaoUser userDao;
	 
	 @Autowired
	 private UtilService utilService;

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
	 
	 public Optional<User> findByToken(String token) {
		 return this.userDao.findByToken(token);
	 }
	 
	 public Optional<User> findByUsername(String username) {
	        return userDao.findByUsername(username);
	    }
	 
	 public String authenticate(String username, String password) {
	        Optional<User> userOptional = userDao.findByUsernameAndPassword(username, password);
	        if (userOptional.isPresent()) {
	            User user = userOptional.get();
	           
	            String tokenResult = null;
//	            return user.getPassword().equals(password);
	            if(user.getToken() == null) {
	            	tokenResult = utilService.generateRandomString(10);
	            	 user.setToken(tokenResult);
	            	 userDao.save(user);
	            }else {
	            	tokenResult = user.getToken();
	            }
	            
	            
	            return tokenResult;
	        }
	        return null;
	    }


	
}
