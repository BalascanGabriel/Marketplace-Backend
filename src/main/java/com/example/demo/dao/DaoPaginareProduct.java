package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface DaoPaginareProduct extends PagingAndSortingRepository<Product, Integer>{

}
