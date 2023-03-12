package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface DaoUser extends CrudRepository<User, Integer>{
	
    Optional<User> findByUsername(String username);

}
